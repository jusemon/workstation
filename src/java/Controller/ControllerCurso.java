/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import Model.DTO.ObjCurso;
import Model.Data.ModelCurso;
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
 * @author Zack
 */
public class ControllerCurso extends HttpServlet {

    ObjCurso _objCurso = new ObjCurso();
    ModelCurso daoModelCurso = new ModelCurso();

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
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("action") != null) {
            String nombre, descripcion, aux, salida;
            int estado = 0, duracion, categoria, id;
            Map<String, String> respuesta;
            ResultSet result;
            switch (request.getParameter("action")) {
                // <editor-fold defaultstate="collapsed" desc="Registrar un Curso">
                case "Registrar": {
                    daoModelCurso = new ModelCurso();
                    nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    descripcion = new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8");
                    duracion = Integer.parseInt(request.getParameter("dateDuracion"));
                    estado = Integer.parseInt(request.getParameter("ddlEstado"));
                    categoria = Integer.parseInt(request.getParameter("ddlCategoria"));
                    _objCurso.setIdCategoria(categoria);
                    _objCurso.setDescripcion(descripcion);
                    _objCurso.setNombreCurso(nombre);
                    _objCurso.setDuracionCurso(duracion);
                    _objCurso.setEstadoCurso(estado);
                    daoModelCurso.Add(_objCurso);
                    response.sendRedirect("curso.jsp");

                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Consultar un Curso">
                case "Consultar": {
                    daoModelCurso = new ModelCurso();
                    aux = request.getParameter("id");
                    id = Integer.parseInt(aux.trim());
                    try {
                        respuesta = new LinkedHashMap<>();
                        result = daoModelCurso.buscarPorID(id);
                        while (result.next()) {
                            respuesta.put("idCurso", result.getString("idCurso"));
                            respuesta.put("nombreCurso", result.getString("nombreCurso"));
                            respuesta.put("duracionCurso", result.getString("duracionCurso"));
                            respuesta.put("estadoCurso", result.getString("estadoCurso"));
                            respuesta.put("descripcionCurso", result.getString("descripcionCurso"));
                            respuesta.put("nombreCategoriaCurso", result.getString("nombreCategoriaCurso"));
                        }
                        salida = new Gson().toJson(respuesta);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                //</editor-fold>

                case "Estado": {
                    daoModelCurso = new ModelCurso();
                    aux = request.getParameter("id");
                    id = Integer.parseInt(aux.trim());
                    try {
                        result = daoModelCurso.buscarPorID(id);
                        while (result.next()) {
                            estado = Integer.parseInt(result.getString("estadoCurso"));
                        }
                        estado = estado > 0 ? 0 : 1;
                        _objCurso = new ObjCurso();
                        _objCurso.setIdCurso(id);
                        _objCurso.setEstadoCurso(estado);
                        boolean resultado = daoModelCurso.cambiarEstado(_objCurso);
                        respuesta = new LinkedHashMap<>();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        if (resultado) {
                            respuesta.put("clase", "success");
                            respuesta.put("icono", "ok");
                            if (estado == 0) {
                                respuesta.put("clase", "danger");
                                respuesta.put("icono", "remove");
                            }
                            respuesta.put("mensaje", "El estado ha sido actualizado");
                            respuesta.put("tipo", "success");
                            salida = new Gson().toJson(respuesta);
                            response.getWriter().write(salida);
                        } else {
                            respuesta.put("mensaje", "El estado no ha sido actualizado");
                            respuesta.put("tipo", "error");
                            salida = new Gson().toJson(respuesta);
                            response.getWriter().write(salida);
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case "Editar":
                    aux = request.getParameter("idCurso");
                    id = Integer.parseInt(aux.trim());
                    nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    descripcion = new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8");
                    duracion = Integer.parseInt(request.getParameter("dateDuracion"));
                    estado = Integer.parseInt(request.getParameter("ddlEstado"));
                    categoria = Integer.parseInt(request.getParameter("ddlCategoria"));
                    _objCurso.setIdCurso(id);
                    _objCurso.setIdCategoria(categoria);
                    _objCurso.setDescripcion(descripcion);
                    _objCurso.setNombreCurso(nombre);
                    _objCurso.setDuracionCurso(duracion);
                    _objCurso.setEstadoCurso(estado);
                    daoModelCurso.Edit(_objCurso);
                    response.sendRedirect("curso.jsp");
                    break;
                case "Enlistar":
                    response.setContentType("application/text");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableCursos());
                    break;
            }
        }

    }

    public String getTableCursos() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelCurso = new ModelCurso();
            result = daoModelCurso.ListAll();
            while (result.next()) {
                String consulta = result.getString("idCurso") + ",'ControllerCurso','POST', 'Consultar', null";
                String edita = result.getString("idCurso") + ",'ControllerCurso','POST', 'Consultar', 'Editar'";
                String estadoCurso = result.getString("idCurso") + ",'ControllerCurso','POST', 'Estado',this";
                String[] estado = {"success", "ok"};
                if (result.getInt("estadoCurso") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                String[] arreglo = new String[5];
                arreglo[0] = result.getString("idCurso").trim();
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"myAjax(" + estadoCurso + ")\"><span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[3] = "<a class=\"btn-sm btn-success btn-block\" onclick=\"myAjax(" + consulta + ")\"><span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[4] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\"  onclick=\"myAjax(" + edita + ")\"><span class=\"glyphicon glyphicon-edit\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error" + e.getMessage());
        } finally {
            daoModelCurso.Signout();
        }
        String salida = new Gson().toJson(lista);
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

}
