/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Validaciones.Validador;
import Model.DTO.ObjClase;
import Model.DTO.ObjCredito;
import Model.DTO.ObjCurso;
import Model.DTO.ObjDetalleCredito;
import Model.DTO.ObjMovimiento;
import Model.DTO.ObjUsuario;
import Model.Data.ModelCredito;
import Model.Data.ModelEmpresa;
import Model.Data.ModelEstudiante;
import Model.Data.ModelMatricula;
import Model.Data.ModelPreinscripcion;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Administrador
 */
public class ControllerMatricula extends HttpServlet {

    ModelEstudiante daoModelEstudiante;
    ModelEmpresa daoModelEmpresa;
    ModelMatricula daoModelMatricula;
    ModelCredito daoModelCredito;
    ModelPreinscripcion daoModelPreinscripcion;
    ObjClase _objClase = new ObjClase();
    ObjCurso _objCurso = new ObjCurso();
    ObjUsuario _objUsuario = new ObjUsuario();
    ObjDetalleCredito _objDetalleCredito = new ObjDetalleCredito();
    ObjMovimiento _objMovimiento = new ObjMovimiento();

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
            switch (request.getParameter("action")) {
                case "Consultar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(Consultar(request));
                    break;
                }
                case "Registrar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(Registrar(request));
                    break;
                }
                case "Estado": {
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableMatriculas());
                    break;
                }

                // <editor-fold defaultstate="collapsed" desc="Preinscribir a un Curso o Seminario">
                case "Preinscribir": {
                    int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                    String documentoUsuario = request.getParameter("documentoUsuario");
                    String tipo = request.getParameter("tipo");
                    String salida = null;
                    if (documentoUsuario == null) {
                        salida = Mensaje(false, null, "Debes estar registrado y con la sesion iniciada");
                    } else {
                        salida = presincribir(idCurso, documentoUsuario);
                    }
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                case "RegistrarAsistencia": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(RegistrarAsistencia(request));
                    break;
                }
                case "getOptionsBeneficio": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getOptionsBeneficio());
                    break;
                }
                case "asignarEmpresa": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(asigarEmpresa(request));
                    break;
                }
                case "ConsultarPreinscripcionesCliente": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(ConsultarPreincripcionesCliente(request));
                    break;
                }
            }
        }
    }

    private String getTableMatriculas() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        String[] arreglo;
        try {
            daoModelMatricula = new ModelMatricula();
            result = daoModelMatricula.ListMatriculados();
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
            daoModelMatricula.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    private String Registrar(HttpServletRequest request) {
        if (Validador.validarDocumento(request.getParameter("txtDocumento")) & Validador.validarNumero(request.getParameter("idCursoMatricula")) & Validador.validarNumero(request.getParameter("txtClases"))) {
            _objUsuario = new ObjUsuario();
            _objCurso = new ObjCurso();
            _objUsuario.setDocumentoUsuario(request.getParameter("txtDocumento"));
            _objCurso.setIdCurso(Integer.parseInt(request.getParameter("idCursoMatricula")));
            _objCurso.setCantidadClases(Integer.parseInt(request.getParameter("txtClases")));
            Map<String, String> objetos = new LinkedHashMap<>();
            daoModelMatricula = new ModelMatricula();
            String[] respuestaBD = daoModelMatricula.Add(_objUsuario, _objCurso);
            daoModelMatricula.Signout();
            objetos.put("tipo", respuestaBD[0]);
            objetos.put("mensaje", respuestaBD[1]);
            return new Gson().toJson(objetos);
        }
        return Mensaje(false, null, "Uno o más campos contienen datos incorrectos.");
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

    public String Mensaje(String entrada[]) {
        Map<String, String> mensaje = new LinkedHashMap<>();
        mensaje.put("mensaje", entrada[0]);
        mensaje.put("tipo", entrada[1]);
        String salida = new Gson().toJson(mensaje);
        return salida;
    }

    private String presincribir(int id, String documentoUsuario) {
        try {
            daoModelPreinscripcion = new ModelPreinscripcion();
            return Mensaje(daoModelPreinscripcion.Add(id, documentoUsuario));
        } catch (Exception e) {
            return Mensaje(false, "", "Ha Ocurrido un Error");
        } finally {
            daoModelPreinscripcion.Signout();
        }
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

    private String Consultar(HttpServletRequest request) {
        String documento = request.getParameter("documentoUsuario");
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        daoModelMatricula = new ModelMatricula();
        Map<String, String> respuesta = daoModelMatricula.BuscarMatriculaPorDocumentoYIdCurso(documento, idCurso);
        daoModelMatricula.Signout();
        String salida = new Gson().toJson(respuesta);
        return salida;
    }

    private String RegistrarAsistencia(HttpServletRequest request) {
        daoModelCredito = new ModelCredito();
        int beneficiario = 0;
        int precioClase = Integer.parseInt(request.getParameter("precio"));
        String documentoUsuario = request.getParameter("documentoUsuario");
        String documentoCliente = request.getParameter("txtDocumento");
        ModelEstudiante daoModelEstudiante = new ModelEstudiante();
        ResultSet rs = daoModelEstudiante.buscarPorID(documentoCliente);
        String estadoPago = "on";
        try {
            if (rs.next()) {
                beneficiario = rs.getInt("estadoBeneficiario");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        if (beneficiario == 0) {
            estadoPago = request.getParameter("estadoPago") != null ? request.getParameter("estadoPago") : "off";
        }
        int idCurso = Integer.parseInt(request.getParameter("idCursoMatricula"));
        int numeroClases = Integer.parseInt(request.getParameter("txtClases"));
        List<ObjClase> clases = new ArrayList();
        for (int i = 0; i < numeroClases; i++) {
            _objClase = new ObjClase();
            _objClase.setDocumentoUsuario(documentoCliente);
            _objClase.setIdCurso(idCurso);
            _objClase.setPrecioClase(precioClase);
            _objClase.setEstadoPago((estadoPago.equals("on") ? 1 : 0));
            _objClase.setCreditoCreado((estadoPago.equals("off") ? 1 : 0));
            if (_objClase.getCreditoCreado() == 1) {
                ObjCredito _objCredito = new ObjCredito();
                ResultSet rs2 = null;
                try {
                    rs2 = daoModelCredito.buscarCreditoByDocumento(_objClase.getDocumentoUsuario());

                } catch (Exception e) {
                    System.out.println(e);
                }
                if (rs2 != null) {
                    try {
                        if (!rs2.next()) {
                            _objCredito.setDocumentoUsuario(documentoCliente);
                            _objCredito.setSaldoInicial(50000);
                            _objCredito.setSaldoActual(50000 - _objClase.getPrecioClase());
                            _objMovimiento.setDocumentoUsuario(documentoUsuario);
                            _objMovimiento.setDocumentoAuxiliar(documentoCliente);
                            daoModelCredito.Add(_objCredito, _objMovimiento);
                        } else {
                            while (rs2.next()) {
                                _objCredito.setDocumentoUsuario(rs2.getString("documentoUsuario"));
                                _objCredito.setSaldoInicial(rs2.getDouble("saldoInicial"));
                                _objCredito.setSaldoActual(rs2.getDouble("saldoActual"));
                                _objCredito.setEstadoCredito(rs2.getInt("estadoCredito"));
                                _objCredito.setIdCredito(rs2.getInt("idCredito"));
                                _objCredito.setFechaInicio(rs2.getString("fechaInicio"));
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerMatricula.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                daoModelCredito.Signout();
            }
            clases.add(_objClase);
        }

        daoModelMatricula = new ModelMatricula();
        String[] aux = daoModelMatricula.RegistrarAsistencia(clases, documentoUsuario, beneficiario);
        daoModelMatricula.Signout();
        Map<String, String> respuesta = new LinkedHashMap<>();
        respuesta.put("tipo", aux[0]);
        respuesta.put("mensaje", aux[1]);
        String salida = new Gson().toJson(respuesta);
        return salida;
    }

    private String getOptionsBeneficio() {
        Map<String, List> resultados = new LinkedHashMap<>();
        List<Map<String, String>> aux;
        Map<String, String> estudiantes;
        Map<String, String> empresas;
        ResultSet rs = null;
        daoModelEstudiante = new ModelEstudiante();
        daoModelEmpresa = new ModelEmpresa();
        try {
            rs = daoModelEmpresa.ListAll();
            aux = new ArrayList<>();
            while (rs.next()) {
                empresas = new LinkedHashMap<>();
                empresas.put("id", rs.getString("nitEmpresa"));
                empresas.put("text", rs.getString("nitEmpresa") + " " + rs.getString("nombreEmpresa"));
                aux.add(empresas);
            }
            resultados.put("empresas", aux);
        } catch (Exception ex) {
            empresas = new LinkedHashMap<>();
            empresas.put("tipo", "error");
            empresas.put("mensaje", "Ha ocurrido un error al consultar las empresas: " + ex.toString());
            daoModelEmpresa.Signout();
            return new Gson().toJson(empresas);
        }
        try {
            rs = daoModelEstudiante.ListAll();
            aux = new ArrayList<>();
            while (rs.next()) {
                estudiantes = new LinkedHashMap<>();
                estudiantes.put("id", rs.getString("documentoUsuario"));
                estudiantes.put("text", rs.getString("documentoUsuario") + " " + rs.getString("nombreUsuario") + " " + rs.getString("apellidoUsuario"));
                aux.add(estudiantes);
            }
            resultados.put("estudiantes", aux);
        } catch (Exception ex) {
            estudiantes = new LinkedHashMap<>();
            estudiantes.put("tipo", "error");
            estudiantes.put("mensaje", "Ha ocurrido un error al consultar los estudiantes: " + ex.toString());
            daoModelEmpresa.Signout();
            daoModelEstudiante.Signout();
            return new Gson().toJson(estudiantes);
        }
        daoModelEmpresa.Signout();
        daoModelEstudiante.Signout();
        return new Gson().toJson(resultados);
    }

    private String asigarEmpresa(HttpServletRequest request) {
        String nitEmpresa;
        String documentoEstudiante;
        if (Validador.validarString(request.getParameter("nitEmpresa")) && Validador.validarString(request.getParameter("documentoEstudiante"))) {
            nitEmpresa = request.getParameter("nitEmpresa");
            documentoEstudiante = request.getParameter("documentoEstudiante");
            daoModelMatricula = new ModelMatricula();
            String resultado[] = daoModelMatricula.asignarEmpresa(nitEmpresa, documentoEstudiante);
            daoModelMatricula.Signout();
            return Mensaje(resultado);
        } else {
            return Mensaje(false, "", "Ha ocurrido un error con los datos seleccionados");
        }
    }

    private String ConsultarPreincripcionesCliente(HttpServletRequest request) {
        List<String[]> salida = new ArrayList<>();
        Map<String, Object> aux;
        daoModelPreinscripcion = new ModelPreinscripcion();
        String documento = request.getParameter("documentoUsuario");
        try {
            ResultSet rs = daoModelPreinscripcion.ConsultarPreinscripcionesPorID(documento);
            while (rs.next()) {
                aux = new LinkedHashMap<>();
                aux.put("documentoUsuario", rs.getString("documentoUsuario"));
                String[] arreglo = new String[5];
                arreglo[0] = rs.getString("tipo").trim();
                arreglo[1] = rs.getString("nombreCurso").trim();
                arreglo[2] = rs.getString("fecha").trim();
                arreglo[3] = "<a class=\"btn-sm btn-success btn-block \" href=\"javascript:void(0)\"  onclick=\"preincripcion.consultar(" + rs.getString("idCurso").trim() + ")\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[4] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"preincripcion.cancelar('" + rs.getString("documentoUsuario").trim() + "'," + rs.getString("idCurso").trim() + ")\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                salida.add(arreglo);
            }
        } catch (Exception ex) {
            aux = new LinkedHashMap<>();
            aux.put("tipo", "error");
            aux.put("mensaje", "Ha ocurrido un error: " + ex);
            return new Gson().toJson(aux);
        }
        aux = new LinkedHashMap<>();
        aux.put("data", salida);
        return new Gson().toJson(aux);
    }
}
