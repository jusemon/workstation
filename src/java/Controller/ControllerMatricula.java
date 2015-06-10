/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Validaciones.Validador;
import Model.DTO.ObjClase;
import Model.DTO.ObjCurso;
import Model.DTO.ObjUsuario;
import Model.Data.ModelMatricula;
import Model.Data.ModelPreinscripcion;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
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
 * @author Administrador
 */
public class ControllerMatricula extends HttpServlet {

    ModelMatricula daoModelMatricula = new ModelMatricula();
    ModelPreinscripcion daoModelPreinscripcion = new ModelPreinscripcion();
    ObjClase _objClase = new ObjClase();
    ObjCurso _objCurso = new ObjCurso();
    ObjUsuario _objUsuario = new ObjUsuario();

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
            String[] respuestaBD = daoModelMatricula.Add(_objUsuario, _objCurso);
            objetos.put("tipo", respuestaBD[0]);
            objetos.put("mensaje", respuestaBD[1]);
            return new Gson().toJson(objetos);
        }
        return Mensaje(false, null, "Uno o mas campos contienen datos incorrectos.");
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
            daoModelPreinscripcion.getConnection();
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
        /**
         * txtDocumento:CC1017225673 idCursoMatricula:5 txtClases:1
         * estadoPago:on action:RegistrarAsistencia
         */
        String documentoUsuario = request.getParameter("txtDocumento");
        String estadoPago = request.getParameter("estadoPago") != null ? request.getParameter("estadoPago") : "off";
        int idCurso = Integer.parseInt(request.getParameter("idCursoMatricula"));
        int numeroClases = Integer.parseInt(request.getParameter("txtClases"));
        List<ObjClase> clases = new ArrayList();
        for (int i = 0; i < numeroClases; i++) {
            _objClase = new ObjClase();
            _objClase.setDocumentoUsuario(documentoUsuario);
            _objClase.setIdCurso(idCurso);
            _objClase.setEstadoPago((estadoPago.equals("on") ? 1 : 0));
            _objClase.setCreditoCreado((estadoPago.equals("off") ? 1 : 0));
            clases.add(_objClase);

        }
        daoModelMatricula = new ModelMatricula();
        Map<String, String> respuesta = daoModelMatricula.RegistrarAsistencia(clases);
        daoModelMatricula.Signout();
        String salida = new Gson().toJson(respuesta);
        return salida;        
    }
}
