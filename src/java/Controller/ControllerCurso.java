/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import Model.DTO.ObjCurso;
import Model.Data.ModelCategoriaCurso;
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
    ModelCategoriaCurso daoModelCategoriaCurso = new ModelCategoriaCurso();
    Map<String, String> respuesta;
    ResultSet result;

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
            String nombre, descripcion, aux, salida, tipo = "";
            int estado = 0, cantidadClases, categoria, id, horasPorClase, precio;
            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Registrar un Curso">
                case "Registrar": {
                    tipo = request.getParameter("tipo");
                    nombre = request.getParameter("txtNombre").trim();
                    descripcion = request.getParameter("txtDescripcion").trim();
                    precio = Integer.parseInt(request.getParameter("txtPrecio").trim());
                    estado = Integer.parseInt(request.getParameter("ddlEstado").trim());
                    horasPorClase = Integer.parseInt(request.getParameter("txtCantidadHoras").trim());
                    if (tipo.equals("Seminario")) {
                        cantidadClases = 1;
                        categoria = daoModelCategoriaCurso.GetIDCategoriaSeminario();
                    } else {
                        cantidadClases = Integer.parseInt(request.getParameter("txtCantidadClases").trim());
                        categoria = Integer.parseInt(request.getParameter("ddlCategoria").trim());
                    }
                    _objCurso.setIdCategoriaCurso(categoria);
                    _objCurso.setDescripcionCurso(descripcion);
                    _objCurso.setNombreCurso(nombre);
                    _objCurso.setCantidadClases(cantidadClases);
                    _objCurso.setHorasPorClase(horasPorClase);
                    _objCurso.setEstadoCurso(estado);
                    _objCurso.setPrecioCurso(precio);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = Mensaje(daoModelCurso.Add(_objCurso), "El Curso ha sido registrado", "Ha ocurrido un error al intentar registrar el Curso");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Consultar un Curso">
                case "Consultar": {
                    aux = request.getParameter("id");
                    id = Integer.parseInt(aux.trim());
                    if (request.getParameter("type") != null) {
                        tipo = request.getParameter("type");
                    }
                    salida = consultar(id, tipo);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Cambiar el estado de un Curso o Seminario">
                case "Estado": {
                    aux = request.getParameter("id");
                    id = Integer.parseInt(aux);
                    tipo = request.getParameter("type");
                    salida = cambiarEstado(id, tipo);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Editar un Curso">
                case "Editar": {
                    aux = request.getParameter("idCurso");
                    id = Integer.parseInt(aux.trim());
                    tipo = request.getParameter("tipo");
                    nombre = request.getParameter("txtNombre").trim();
                    descripcion = request.getParameter("txtDescripcion").trim();
                    precio = Integer.parseInt(request.getParameter("txtPrecio").trim());
                    estado = Integer.parseInt(request.getParameter("ddlEstado").trim());
                    horasPorClase = Integer.parseInt(request.getParameter("txtCantidadHoras").trim());
                    cantidadClases = Integer.parseInt(request.getParameter("txtCantidadClases").trim());
                    if (tipo.equals("Seminario")) {
                        categoria = daoModelCategoriaCurso.GetIDCategoriaSeminario();
                    } else {
                        categoria = Integer.parseInt(request.getParameter("ddlCategoria").trim());
                    }
                    _objCurso.setIdCurso(id);
                    _objCurso.setIdCategoriaCurso(categoria);
                    _objCurso.setDescripcionCurso(descripcion);
                    _objCurso.setNombreCurso(nombre);
                    _objCurso.setCantidadClases(cantidadClases);
                    _objCurso.setHorasPorClase(horasPorClase);
                    _objCurso.setEstadoCurso(estado);
                    _objCurso.setPrecioCurso(precio);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = Mensaje(daoModelCurso.Edit(_objCurso), "El Curso ha sido actualizado", "Ha ocurrido un error al intentar actualizar el Curso");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Preinscribir a un Curso o Seminario">
                case "Preinscribir": {
                    int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                    String documentoUsuario = request.getParameter("documentoUsuario");
                    tipo = request.getParameter("tipo");
                    if (documentoUsuario == null) {
                        salida = Mensaje(false, null, "Debes estar registrado y con la sesion iniciada");
                    } else {
                        salida = presincribir(idCurso, tipo, documentoUsuario);
                    }
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Enlistar los Cursos">
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableCursos());
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Enlistar los Seminarios">
                case "EnlistarSeminarios": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableSeminarios());
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Enlistar los Cursos Disponibles">
                case "cursosDisponibles": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getCursosDisponibles());
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Enlistar los Seminarios Disponibles">
                case "seminariosDisponibles": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getSeminariosDisponibles());
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Obtener las opciones de los Cursos">
                case "getOptionsCursos": {
                    response.setContentType("application/text");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getOptionsCursos());
                    break;
                }
                //</editor-fold>

            }
        }
    }

    public String consultar(int id, String tipo) {
        String salida;
        respuesta = new LinkedHashMap<>();
        result = null;
        try {
            if (tipo.equals("Seminario")) {
                result = daoModelCurso.buscarSeminarioPorID(id);
            } else {
                result = daoModelCurso.buscarCursoPorID(id);
            }
            while (result.next()) {
                respuesta.put("idCurso", result.getString("idCurso"));
                respuesta.put("nombreCurso", result.getString("nombreCurso"));
                respuesta.put("cantidadClases", result.getString("cantidadClases"));
                respuesta.put("horasPorClase", result.getString("horasPorClase"));
                respuesta.put("estadoCurso", result.getString("estadoCurso"));
                respuesta.put("precioCurso", result.getString("precioCurso"));
                respuesta.put("descripcionCurso", result.getString("descripcionCurso"));
                respuesta.put("idCategoriaCurso", result.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", result.getString("nombreCategoriaCurso"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            Mensaje(false, "", "Ha ocurrido un error en el Controller " + e.getMessage());
        }
        salida = new Gson().toJson(respuesta);
        return salida;
    }

    public String cambiarEstado(int id, String tipo) {
        int estado = 0;
        try {
            if (tipo == null) {
                result = daoModelCurso.buscarCursoPorID(id);
            } else if (tipo.equals("Seminario")) {
                result = daoModelCurso.buscarSeminarioPorID(id);
            } else {
                result = daoModelCurso.buscarCursoPorID(id);
            }
            while (result.next()) {
                estado = Integer.parseInt(result.getString("estadoCurso"));
            }
            estado = estado > 0 ? 0 : 1;
            _objCurso = new ObjCurso();
            _objCurso.setIdCurso(id);
            _objCurso.setEstadoCurso(estado);
            return Mensaje(daoModelCurso.cambiarEstado(_objCurso), "El estado ha sido actualizado", "Ha ocurrido un error al intentar actualizar el estado");

        } catch (Exception e) {
            return Mensaje(false, "", "Ha ocurrido un error en el controller " + e.getMessage());
        }
    }

    private String presincribir(int id, String tipo, String documentoUsuario) {
        if (tipo.equals("Seminario")) {
            return Mensaje(daoModelCurso.Preincribir(id, documentoUsuario), "Has sido preincrito al Seminario.", "Ha ocurrido un error durante la preinscripcion");
        } else {
            return Mensaje(daoModelCurso.Preincribir(id, documentoUsuario), "Has sido preincrito al Curso.", "Ha ocurrido un error durante la preinscripcion");
        }
    }

    public String getCursosDisponibles() {
        List<Map> lista = null;
        respuesta = null;
        try {
            result = daoModelCurso.ListCursosDisponibles();
            lista = new ArrayList<>();
            while (result.next()) {
                respuesta = new LinkedHashMap<>();
                respuesta.put("idCurso", result.getString("idCurso"));
                respuesta.put("nombreCurso", result.getString("nombreCurso"));
                respuesta.put("cantidadClases", result.getString("cantidadClases"));
                respuesta.put("horasPorClase", result.getString("horasPorClase"));
                respuesta.put("estadoCurso", result.getString("estadoCurso"));
                respuesta.put("descripcionCurso", result.getString("descripcionCurso"));
                respuesta.put("precioCurso", result.getString("precioCurso"));
                respuesta.put("idCategoriaCurso", result.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", result.getString("nombreCategoriaCurso"));
                lista.add(respuesta);
            }
        } catch (Exception e) {
            respuesta = new LinkedHashMap<>();
            respuesta.put("mensaje", "Ups, al parecer ha ocurrio un error: " + e + ".");
            respuesta.put("tipo", "error");
            lista.add(respuesta);

        } finally {
        }
        String salida = new Gson().toJson(lista);
        return salida;

    }

    public String getSeminariosDisponibles() {
        List<Map> lista = null;
        respuesta = null;
        try {
            result = daoModelCurso.ListSeminariosDisponibles();
            lista = new ArrayList<>();
            while (result.next()) {
                respuesta = new LinkedHashMap<>();
                respuesta.put("idCurso", result.getString("idCurso"));
                respuesta.put("nombreCurso", result.getString("nombreCurso"));
                respuesta.put("cantidadClases", result.getString("cantidadClases"));
                respuesta.put("horasPorClase", result.getString("horasPorClase"));
                respuesta.put("estadoCurso", result.getString("estadoCurso"));
                respuesta.put("descripcionCurso", result.getString("descripcionCurso"));
                respuesta.put("precioCurso", result.getString("precioCurso"));
                respuesta.put("idCategoriaCurso", result.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", result.getString("nombreCategoriaCurso"));
                lista.add(respuesta);
            }
        } catch (Exception e) {
            respuesta = new LinkedHashMap<>();
            respuesta.put("mensaje", "Ups, al parecer ha ocurrio un error: " + e + ".");
            respuesta.put("tipo", "error");
            lista.add(respuesta);

        } finally {
        }
        String salida = new Gson().toJson(lista);
        return salida;

    }

    public String getTableCursos() {
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelCurso = new ModelCurso();
            result = daoModelCurso.ListAll();
            while (result.next()) {
                String[] estado = {"success", "ok"};
                if (result.getInt("estadoCurso") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                String[] arreglo = new String[5];
                arreglo[0] = result.getString("idCurso").trim();
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"curso.myAjax('Estado'," + arreglo[0] + ", '','Curso')\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[3] = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"curso.myAjax('Consultar'," + arreglo[0] + ", '', 'Curso')\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[4] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\" onclick=\"curso.myAjax('Consultar'," + arreglo[0] + ",'Editar', 'Curso')\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller " + e.toString());
        } finally {
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    public String getTableSeminarios() {
        List<String[]> lista = new ArrayList<>();
        try {
            result = daoModelCurso.ListAll("Seminarios");
            while (result.next()) {
                String[] estado = {"success", "ok"};
                if (result.getInt("estadoCurso") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                String[] arreglo = new String[5];
                arreglo[0] = result.getString("idCurso").trim();
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"seminario.myAjax('Estado'," + arreglo[0] + ", '','Seminario')\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[3] = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"seminario.myAjax('Consultar'," + arreglo[0] + ", '','Seminario')\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[4] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\" onclick=\"seminario.myAjax('Consultar'," + arreglo[0] + ",'Editar','Seminario')\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller " + e.toString());
        } finally {
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    public String getOptionsCursos() {
        String OptionsCursos = "";
        try {
            daoModelCurso = new ModelCurso();
            result = daoModelCurso.ListAll();
            while (result.next()) {
                OptionsCursos += "<option value=\"" + result.getString("idCurso").trim() + "\">" + result.getString("nombreCurso").trim() + "</option>";
            }

        } catch (Exception e) {
            OptionsCursos = "Ha Ocurrido un error 2" + e.getMessage();
        }

        return OptionsCursos;
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

}
