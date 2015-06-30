/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Validaciones.Validador;
import Model.DTO.ObjUsuario;
import Model.Data.ModelUsuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zack
 */
public class ControllerUsuario extends HttpServlet {

    public ObjUsuario _objUsuario = new ObjUsuario();
    ModelUsuario daoModelUsuario;
    SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoFechaSalida = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "Registrar": {
                    response.getWriter().write(Registrar(request));
                    break;
                }
                case "Consultar": {
                    response.getWriter().write(Consultar(request));
                    break;
                }
                case "Actualizar":
                case "Editar": {
                    response.getWriter().write(Editar(request));
                    break;
                }
                case "MiPerfil": {
                    response.getWriter().write(MiPerfil(request));
                    break;
                }
                case "getOptionsClientes": {
                    response.getWriter().write(getOptionsClientes());
                    break;
                }
                case "EnlistarOperarios": {
                    response.getWriter().write(EnlistarOperarios());
                    break;
                }
            }
        }
    }

    public String Mensaje(boolean entrada, String mensajeSuccess, String mensajeError) {
        Map<String, String> mensaje = new LinkedHashMap<>();
        if (entrada) {
            mensaje.put("mensaje", mensajeSuccess);
            mensaje.put("tipo", "success");

        } else {
            mensaje.put("mensaje", mensajeError);
            mensaje.put("tipo", "error");
        }
        String salida = new Gson().toJson(mensaje);
        return salida;
    }

    private String getOptionsClientes() {
        daoModelUsuario = new ModelUsuario();
        List<Map<String, String>> resultado = daoModelUsuario.ListClientesYEstudiantes();
        daoModelUsuario.Signout();
        List<Object> salida = new ArrayList<>();
        Map<String, String> aux = null;
        String retorno = "";
        if (resultado != null) {
            for (Map<String, String> result : resultado) {
                aux = new LinkedHashMap<>();
                aux.put("id", result.get("tipoDocumento") + result.get("numeroDocumento"));
                aux.put("text", result.get("tipoDocumento") + " " + result.get("numeroDocumento"));
                salida.add(aux);
            }
            retorno = new Gson().toJson(salida);
        } else {
            return retorno;
        }
        return retorno;
    }

    private String Consultar(HttpServletRequest request) {
        String salida;
        String id = request.getParameter("id");
        try {
            Map<String, String> respuesta = new LinkedHashMap<>();
            daoModelUsuario = new ModelUsuario();
            ResultSet result = daoModelUsuario.buscarPorID(id);
            while (result.next()) {
                respuesta.put("tipoDocumento", result.getString("documentoUsuario").substring(0, 2));
                respuesta.put("numeroDocumento", result.getString("documentoUsuario").substring(2));
                try {
                    respuesta.put("fechaNacimiento", formatoFechaEntrada.format(formatoFechaSalida.parse(result.getString("fechaNacimiento"))));
                } catch (ParseException ex) {
                    respuesta.put("fechaNacimiento", (result.getString("fechaNacimiento")));
                }
                respuesta.put("nombreUsuario", result.getString("nombreUsuario"));
                respuesta.put("apellidoUsuario", result.getString("apellidoUsuario"));
                respuesta.put("emailUsuario", result.getString("emailUsuario"));
                respuesta.put("password", result.getString("password"));
                respuesta.put("estadoUsuario", result.getString("estadoUsuario"));
                respuesta.put("idrol", result.getString("idrol"));
            }
            daoModelUsuario.Signout();
            salida = new Gson().toJson(respuesta);
        } catch (SQLException e) {
            salida = Mensaje(false, "", "A ocurrido un error" + e.getMessage());
        }
        return salida;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>   

    private String MiPerfil(HttpServletRequest request) {
        String salida;
        String id = request.getParameter("documentoUsuario").trim();
        try {
            Map<String, String> respuesta = new LinkedHashMap<>();
            daoModelUsuario = new ModelUsuario();
            ResultSet result = daoModelUsuario.buscarPorID(id);
            while (result.next()) {
                respuesta.put("nombreUsuario", result.getString("nombreUsuario"));
                respuesta.put("apellidoUsuario", result.getString("apellidoUsuario"));
            }
            daoModelUsuario.Signout();
            salida = new Gson().toJson(respuesta);
        } catch (SQLException e) {
            salida = Mensaje(false, "", "A ocurrido un error" + e.getMessage());
        }
        return salida;
    }

    private String Editar(HttpServletRequest request) {
        String salida;
        try {
            String tipoDocumento = null;
            if (Validador.validarNumero(request.getParameter("ddlIdentificacion").trim())) {
                System.out.println("ddl true");

            } else {
                System.out.println("ddl false");
            }
            if (Validador.validarTipoDocumento(request.getParameter("txtIdentificacion").trim())) {
                System.out.println("txtIdentificacion true");
            } else {
                System.out.println("txtIdentificacion false");
            }
            tipoDocumento = request.getParameter("ddlIdentificacion").trim();
            long numeroIdentificacion = Long.parseLong(request.getParameter("txtIdentificacion").trim());
            String identificacion = tipoDocumento + numeroIdentificacion;
            String nombre = request.getParameter("txtNombre").trim();
            String apellido = request.getParameter("txtApellido").trim();
            String fechaNacimiento = request.getParameter("dateFechaNacimiento").trim();
            String correo = request.getParameter("txtCorreo").trim();
            int estado = 1;
            int rol = 4;
            String pass = request.getParameter("txtPass").trim();
            _objUsuario.setDocumentoUsuario(identificacion);
            _objUsuario.setNombreUsuario(nombre);
            _objUsuario.setApellidoUsuario(apellido);
            _objUsuario.setFechaNacimiento(formatoFechaSalida.format(formatoFechaEntrada.parse(fechaNacimiento)));
            _objUsuario.setEmailUsuario(correo);
            _objUsuario.setEstadoUsuario(estado);
            _objUsuario.setPassword(pass);
            _objUsuario.setIdrol(rol);
            if (request.getParameter("tipo") != null) {
                if (request.getParameter("tipo").equals("Operario")) {
                    _objUsuario.setIdrol(2);
                }
            }
            daoModelUsuario = new ModelUsuario();
            salida = Mensaje(daoModelUsuario.Edit(_objUsuario), nombre + " ha sido actualizado correctamente", "Ha ocurrido un error al intentar actualizar al usuario");
            daoModelUsuario.Signout();
        } catch (NumberFormatException | ParseException e) {
            salida = Mensaje(false, "", "Ha ocurrido un error" + e.getMessage());
        }
        return salida;
    }

    private String Registrar(HttpServletRequest request) {
        String salida;
        try {
            String tipoDocumento = null;
            if (Validador.validarNumero(request.getParameter("ddlIdentificacion").trim())) {
                System.out.println("ddl true");

            } else {
                System.out.println("ddl false");
            }
            if (Validador.validarTipoDocumento(request.getParameter("txtIdentificacion").trim())) {
                System.out.println("txtIdentificacion true");
            } else {
                System.out.println("txtIdentificacion false");
            }
            tipoDocumento = request.getParameter("ddlIdentificacion").trim();
            long numeroIdentificacion = Long.parseLong(request.getParameter("txtIdentificacion").trim());
            String identificacion = tipoDocumento + numeroIdentificacion;
            String nombre = request.getParameter("txtNombre").trim();
            String apellido = request.getParameter("txtApellido").trim();
            String fechaNacimiento = request.getParameter("dateFechaNacimiento").trim();
            String correo = request.getParameter("txtCorreo").trim();
            int estado = 1;
            int rol = 4;
            String pass = request.getParameter("txtPass").trim();
            _objUsuario.setDocumentoUsuario(identificacion);
            _objUsuario.setNombreUsuario(nombre);
            _objUsuario.setApellidoUsuario(apellido);
            _objUsuario.setFechaNacimiento(formatoFechaSalida.format(formatoFechaEntrada.parse(fechaNacimiento)));
            _objUsuario.setEmailUsuario(correo);
            _objUsuario.setEstadoUsuario(estado);
            _objUsuario.setPassword(pass);
            _objUsuario.setIdrol(rol);
            if (request.getParameter("tipo") != null) {
                if (request.getParameter("tipo").equals("Operario")) {
                    _objUsuario.setIdrol(2);
                }
            }

            daoModelUsuario = new ModelUsuario();
            salida = Mensaje(daoModelUsuario.Add(_objUsuario), nombre + " ha sido registrado correctamente", "Ha ocurrido un error al intentar registrar al usuario");
            daoModelUsuario.Signout();
        } catch (NumberFormatException | ParseException e) {
            salida = Mensaje(false, "", "Ha ocurrido un error" + e.getMessage());
        }
        return salida;
    }

    private String EnlistarOperarios() {
         ResultSet result;
        List<String[]> lista = new ArrayList<>();
        String[] arreglo;
        try {
            daoModelUsuario = new ModelUsuario();
            result = daoModelUsuario.ListOperarios();
            while (result.next()) {
                arreglo = new String[6];
                arreglo[0] = result.getString("documentoUsuario").trim();
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = result.getString("numeroClases").trim();
                //arreglo[3] = result.getString("numeroClasesFaltantes").trim();
                arreglo[3] = result.getString("numeroClasesAsistidas").trim();
                arreglo[4] = "<a class=\"btn-sm btn-success btn-block \" href=\"javascript:void(0)\"  onclick=\"matricula.consultar('" + result.getString("documentoUsuario").trim() + "'," + result.getString("idCurso").trim() + ")\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[5] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"matricula.asistencia('" + result.getString("documentoUsuario").trim() + "'," + result.getString("idCurso").trim() + ")\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error." + e.getMessage());
        } finally {
            daoModelUsuario.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

}
