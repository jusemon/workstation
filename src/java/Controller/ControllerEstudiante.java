/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Validaciones.Validador;
import Model.DTO.ObjAcudiente;
import Model.DTO.ObjUsuario;
import Model.DTO.ObjDetalleUsuario;
import Model.Data.ModelAcudiente;
import Model.Data.ModelEstudiante;
import Model.Data.ModelClase;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zack
 */
public class ControllerEstudiante extends HttpServlet {

    ModelEstudiante daoModelEstudiante = new ModelEstudiante();
    public ObjUsuario _objUsuario = new ObjUsuario();
    public ObjDetalleUsuario _objDetalleUsuario = new ObjDetalleUsuario();
    public ModelAcudiente daoModelAcudiente = new ModelAcudiente();
    public ObjAcudiente _objAcudiente = new ObjAcudiente();
    ModelClase daoModelFicha = new ModelClase();
    SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoFechaSalida = new SimpleDateFormat("yyyy-MM-dd");
    Map<String, String> respuesta;

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
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                //<editor-fold desc="Registrar un Estudiante" defaultstate="collapsed">
                case "Registrar": {
                    try {
                        Object[] datosEstudiante = ObtenerDatosFormulario(request);
                        if (datosEstudiante == null) {
                            response.setContentType("application/json");
                            String salida = Mensaje(false, "", "Uno o mas campos contienen datos incorrectos.");
                            response.getWriter().write(salida);
                            break;
                        }
                        _objUsuario = (ObjUsuario) datosEstudiante[0];
                        _objDetalleUsuario = (ObjDetalleUsuario) datosEstudiante[1];
                        response.setContentType("application/json");
                        daoModelEstudiante = new ModelEstudiante();
                        String salida = Mensaje(daoModelEstudiante.Add(_objUsuario, _objDetalleUsuario), "El estudiante ha sido registrado", "A ocurrido un error al intentar registrar al estudiante");
                        daoModelEstudiante.Signout();
                        response.getWriter().write(salida);
                    } catch (NumberFormatException | IOException e) {
                        System.out.println("Ha ocurrido un error en el Controller Estudiante" + e.getMessage());
                    } catch (ParseException ex) {
                        response.setContentType("application/json");
                        String salida = Mensaje(false, "", "A ocurrido un error con la fecha de nacimiento");
                        response.getWriter().write(salida);
                    }
                    break;
                }
                //</editor-fold>

                case "Consultar": {
                    String id = request.getParameter("id");
                    String tipo = request.getParameter("tipo");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(Consultar(id, tipo));
                    break;
                }

                case "Editar": {
                    try {
                        String tipoDocumento = request.getParameter("ddlIdentificacion").trim();
                        int numeroIdentificacion = Integer.parseInt(request.getParameter("txtIdentificacion").trim());
                        String identificacion = tipoDocumento + numeroIdentificacion;
                        String nombre = request.getParameter("txtNombre").trim();
                        String apellido = request.getParameter("txtApellido").trim();
                        int genero = Integer.parseInt(request.getParameter("radioGenero").trim());
                        String fechaNacimiento = request.getParameter("dateFechaNacimiento").trim();
                        String direccion = request.getParameter("txtDireccion").trim();
                        String telefono = request.getParameter("txtTelefono").trim();
                        String celular = request.getParameter("txtCelular").trim();
                        String correo = request.getParameter("txtCorreo").trim();
                        int estado = Integer.parseInt(request.getParameter("radioBeneficiario").trim());
                        String pass = request.getParameter("txtPass").trim();
                        //Beneficiario 0->No Subvencionado 1->Subvencionado?
                        String tipoDocAcudiente = "";
                        int numeroDocAcudiente = 0;
                        if (request.getParameter("tipoDocAcudiente") != null && request.getParameter("numeroDocAcudiente") != null) {
                            tipoDocAcudiente = request.getParameter("tipoDocAcudiente").trim();
                            numeroDocAcudiente = Integer.parseInt(request.getParameter("numeroDocAcudiente").trim());
                            String identificacionAcudiente = tipoDocAcudiente + numeroDocAcudiente;
                            _objUsuario.setDocumentoAcudiente(identificacionAcudiente);
                        }
                        _objUsuario.setDocumentoUsuario(identificacion);
                        _objUsuario.setNombreUsuario(nombre);
                        _objUsuario.setApellidoUsuario(apellido);
                        _objDetalleUsuario.setGeneroUsuario(genero);
                        _objUsuario.setFechaNacimiento(formatoFechaSalida.format(formatoFechaEntrada.parse(fechaNacimiento)));
                        _objDetalleUsuario.setDireccionUsuario(direccion);
                        _objDetalleUsuario.setTelefonoFijo(telefono);
                        _objDetalleUsuario.setTelefonoMovil(celular);
                        _objUsuario.setEmailUsuario(correo);
                        _objUsuario.setEstadoUsuario(estado);
                        _objUsuario.setPassword(pass);
                        response.setContentType("application/json");
                        daoModelEstudiante = new ModelEstudiante();
                        String salida = Mensaje(daoModelEstudiante.Edit(_objUsuario, _objDetalleUsuario), "El estudiante ha sido actualizado", "A ocurrido un error al intentar actualizar al estudiante");
                        daoModelEstudiante.Signout();
                        response.getWriter().write(salida);

                    } catch (NumberFormatException | IOException e) {
                        System.out.println(e.getMessage());
                    } catch (ParseException ex) {
                        response.setContentType("application/json");
                        String salida = Mensaje(false, "", "A ocurrido un error con la fecha de nacimiento");
                        response.getWriter().write(salida);
                    }
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.getWriter().write(getTableEstudiantes());
                    break;
                }
                case "EnlistarPreinscritos": {
                    response.setContentType("application/json");
                    response.getWriter().write(getTablePreinscritos());
                    break;
                }
                case "Formalizar Inscripción": {
                    response.setContentType("application/json");
                    try {
                        response.getWriter().write(Formalizar(request));
                    } catch (ParseException ex) {
                        String salida = Mensaje(false, "", "A ocurrido un error con la fecha de nacimiento");
                        response.getWriter().write(salida);
                    }
                    break;
                }
            }
        }
    }

    public String getTableEstudiantes() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelEstudiante.getConnection();
            result = daoModelEstudiante.ListAll();
            String[] arreglo;
            while (result.next()) {
                arreglo = new String[7];
                arreglo[0] = result.getString("documentoUsuario").trim();
                arreglo[1] = result.getString("nombreUsuario").trim();
                arreglo[2] = result.getString("fechaNacimiento").trim();
                arreglo[3] = result.getString("telefonoFijo").trim();
                arreglo[4] = "<a class=\"btn-sm btn-success btn-block \" href=\"javascript:void(0)\"  onclick=\"estudiante.myAjax('Consultar','" + arreglo[0] + "')\">\n"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[5] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"estudiante.myAjax('Consultar','" + arreglo[0] + "', 'Editar')\">\n"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\"  onclick=\"estudiante.myAjax('Consultar','" + arreglo[0] + "', 'Matricular')\">\n"
                        + "<span class=\"glyphicon glyphicon-bookmark\"></span></a>";
                lista.add(arreglo);
            }

        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error enlistando" + e.getMessage());
        } finally {
            daoModelEstudiante.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    public String getTablePreinscritos() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelEstudiante.getConnection();
            result = daoModelEstudiante.ListPreinscritos();
            String[] arreglo;
            while (result.next()) {
                arreglo = new String[7];
                arreglo[0] = result.getString("documentoUsuario").trim();
                arreglo[1] = result.getString("nombreUsuario").trim();
                arreglo[2] = result.getString("nombreCurso").trim();
                arreglo[3] = result.getString("fechaPreincripcion").trim();
                arreglo[4] = result.getString("emailUsuario").trim();
                arreglo[5] = "<a class=\"btn-sm btn-success btn-block \" href=\"javascript:void(0)\"  onclick=\"estudiante.myAjax('Consultar','" + arreglo[0] + "', 'Preinscrito')\">\n"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\"  onclick=\"estudiante.myAjax('Consultar','" + arreglo[0] + "', 'Preinscrito', 'Inscribir')\">\n"
                        + "<span class=\"glyphicon glyphicon-bookmark\"></span></a>";
                lista.add(arreglo);
            }

        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error enlistando" + e.getMessage());
        } finally {
            daoModelEstudiante.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
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

    private String Consultar(String id, String tipo) {
        ResultSet result = null;
        respuesta = new LinkedHashMap<>();
        daoModelEstudiante.getConnection();
        if (tipo != null) {
            if (tipo.equals("Preinscrito")) {
                result = daoModelEstudiante.buscarPreinscritoPorID(id);
            } else {
                tipo = "Inscrito";
                result = daoModelEstudiante.buscarPorID(id);
            }
        } else {
            tipo = "Inscrito";
            result = daoModelEstudiante.buscarPorID(id);
        }
        try {
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
                if (tipo.equals("Inscrito")) {
                    respuesta.put("idDetalleUsuario", result.getString("idDetalleUsuario"));
                    respuesta.put("direccionUsuario", result.getString("direccionUsuario"));
                    respuesta.put("telefonoFijo", result.getString("telefonoFijo"));
                    respuesta.put("telefonoMovil", result.getString("telefonoMovil"));
                    respuesta.put("generoUsuario", result.getString("generoUsuario"));
                    respuesta.put("documentoAcudiente", result.getString("documentoAcudiente"));
                    respuesta.put("estadoBeneficiario", result.getString("estadoBeneficiario"));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            daoModelEstudiante.Signout();
        }
        String salida = new Gson().toJson(respuesta);
        return salida;
    }

    private String Formalizar(HttpServletRequest request) throws ParseException {
        Object[] datosEstudiante = ObtenerDatosFormulario(request);
        _objUsuario = (ObjUsuario) datosEstudiante[0];
        _objDetalleUsuario = (ObjDetalleUsuario) datosEstudiante[1];
        daoModelEstudiante.AddInscrito(_objUsuario, _objDetalleUsuario);
        if (datosEstudiante == null) {
            return Mensaje(false, "", "Uno o mas campos contienen datos incorrectos.");
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object[] ObtenerDatosFormulario(HttpServletRequest request) throws ParseException {
        Object[] resultado = new Object[2];
        if (Validador.validarTipoDocumento(request.getParameter("ddlIdentificacion").trim())
                && Validador.validarNumero(request.getParameter("txtIdentificacion").trim())
                && Validador.validarNombre(request.getParameter("txtNombre").trim())
                && Validador.validarNombre(request.getParameter("txtApellido").trim())
                && Validador.validarBit(request.getParameter("radioGenero").trim())
                && Validador.validarFecha(request.getParameter("dateFechaNacimiento").trim())
                && Validador.validarString(request.getParameter("txtDireccion").trim())
                && Validador.validarTelefono(request.getParameter("txtTelefono").trim())
                && Validador.validarCelular(request.getParameter("txtCelular").trim())
                && Validador.validarEmail(request.getParameter("txtCorreo").trim())
                && Validador.validarBit(request.getParameter("radioBeneficiario").trim())
                && Validador.validarString(request.getParameter("txtPass").trim())) {

            String tipoDocumento = request.getParameter("ddlIdentificacion").trim();
            int numeroIdentificacion = Integer.parseInt(request.getParameter("txtIdentificacion").trim());
            String identificacion = tipoDocumento + numeroIdentificacion;
            String nombre = request.getParameter("txtNombre").trim();
            String apellido = request.getParameter("txtApellido").trim();
            int genero = Integer.parseInt(request.getParameter("radioGenero").trim());
            String fechaNacimiento = request.getParameter("dateFechaNacimiento").trim();
            String direccion = request.getParameter("txtDireccion").trim();
            String telefono = request.getParameter("txtTelefono").trim();
            String celular = request.getParameter("txtCelular").trim();
            String correo = request.getParameter("txtCorreo").trim();
            int estado = Integer.parseInt(request.getParameter("radioBeneficiario").trim());
            String pass = request.getParameter("txtPass").trim();
            //Beneficiario 0->No Subvencionado 1->Subvencionado?
            String tipoDocAcudiente = "";
            int numeroDocAcudiente = 0;
            _objUsuario = new ObjUsuario();
            _objDetalleUsuario = new ObjDetalleUsuario();
            if (request.getParameter("tipoDocAcudiente") != null && request.getParameter("numeroDocAcudiente") != null) {
                tipoDocAcudiente = request.getParameter("tipoDocAcudiente").trim();
                numeroDocAcudiente = Integer.parseInt(request.getParameter("numeroDocAcudiente").trim());
                String identificacionAcudiente = tipoDocAcudiente + numeroDocAcudiente;
                _objUsuario.setDocumentoAcudiente(identificacionAcudiente);
            }
            _objUsuario.setDocumentoUsuario(identificacion);
            _objUsuario.setNombreUsuario(nombre);
            _objUsuario.setApellidoUsuario(apellido);
            _objDetalleUsuario.setGeneroUsuario(genero);
            _objUsuario.setFechaNacimiento(formatoFechaSalida.format(formatoFechaEntrada.parse(fechaNacimiento)));
            _objDetalleUsuario.setDireccionUsuario(direccion);
            _objDetalleUsuario.setTelefonoFijo(telefono);
            _objDetalleUsuario.setTelefonoMovil(celular);
            _objUsuario.setEmailUsuario(correo);
            _objUsuario.setEstadoUsuario(estado);
            _objUsuario.setPassword(pass);
            resultado[0] = _objUsuario;
            resultado[2] = _objDetalleUsuario;
            return resultado;
        } else {
            return null;
        }
    }

}
