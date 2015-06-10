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
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "Registrar": {
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
                        response.setContentType("application/json");
                        daoModelUsuario = new ModelUsuario();
                        String salida = Mensaje(daoModelUsuario.Add(_objUsuario), nombre + " ha sido registrado existosamente", "A ocurrido un error al intentar registrar al estudiante");
                        daoModelUsuario.Signout();
                        response.getWriter().write(salida);
                    } catch (NumberFormatException | IOException | ParseException e) {
                        response.setContentType("application/json");
                        String salida = Mensaje(false, "", "A ocurrido un error" + e.getMessage());
                        response.getWriter().write(salida);
                    }
                    break;
                }
                case "Consultar": {
                    String id = request.getParameter("id");
                    try {
                        Map<String, String> respuesta = new LinkedHashMap<>();
                        daoModelUsuario = new ModelUsuario();
                        ResultSet result = daoModelUsuario.buscarPorID(id);
                        while (result.next()) {
                            respuesta.put("tipoDocumento", result.getString("documentoUsuario").substring(0, 2));
                            respuesta.put("numeroDocumento", result.getString("documentoUsuario").substring(2));
                            respuesta.put("fechaNacimiento", result.getString("fechaNacimiento"));
                            respuesta.put("nombreUsuario", result.getString("nombreUsuario"));
                            respuesta.put("apellidoUsuario", result.getString("apellidoUsuario"));
                            respuesta.put("emailUsuario", result.getString("emailUsuario"));
                            respuesta.put("password", result.getString("password"));
                            respuesta.put("estadoUsuario", result.getString("estadoUsuario"));
                            respuesta.put("idrol", result.getString("idrol"));
                        }
                        daoModelUsuario.Signout();
                        String salida = new Gson().toJson(respuesta);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);
                    } catch (SQLException | IOException e) {
                        response.setContentType("application/json");
                        String salida = Mensaje(false, "", "A ocurrido un error" + e.getMessage());
                        response.getWriter().write(salida);
                    }
                    break;
                }
                case "Editar": {

                    break;
                }
                case "Matricular": {

                    break;
                }
                case "Preinscribir": {

                    break;
                }
                case "Estado": {

                    break;
                }
                case "getOptionsClientes": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getOptionsClientes());
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

}
