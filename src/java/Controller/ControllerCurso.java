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
    ModelCurso daoModelCurso;
    ModelCategoriaCurso daoModelCategoriaCurso;
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
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") != null) {
            String nombre, descripcion, aux, salida, tipo = "",fechaSeminario;
            int estado = 0, cantidadClases, categoria, id, horasPorClase, precio,  cupoSeminario;
            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Registrar un Curso o Seminario">
                case "Registrar": {
                    daoModelCurso = new ModelCurso();
                    tipo = request.getParameter("tipo");
                    nombre = request.getParameter("txtNombre").trim();
                    descripcion = request.getParameter("txtDescripcion").trim();
                    precio = Integer.parseInt(request.getParameter("txtPrecio").trim());
                    estado = Integer.parseInt(request.getParameter("ddlEstado").trim());
                    horasPorClase = Integer.parseInt(request.getParameter("txtCantidadHoras").trim());
                    fechaSeminario = request.getParameter("txtFechaSeminario").trim();
                    cupoSeminario = Integer.parseInt(request.getParameter("txtCupoSeminario").trim());
                    
                    if (tipo.equals("Seminario")) {
                        cantidadClases = 1;
                        daoModelCategoriaCurso = new ModelCategoriaCurso();
                        categoria = daoModelCategoriaCurso.GetIDCategoriaSeminario();
                        daoModelCategoriaCurso.Signout();
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
                    _objCurso.setFechaSeminario(fechaSeminario);
                    _objCurso.setCupoSeminario (cupoSeminario);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = Mensaje(daoModelCurso.Add(_objCurso), "El curso ha sido registrado", "Ha ocurrido un error al intentar registrar el Curso");
                    daoModelCurso.Signout();
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Consultar un Curso o Seminario">
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
                    daoModelCurso = new ModelCurso();
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
                        daoModelCategoriaCurso = new ModelCategoriaCurso();
                        categoria = daoModelCategoriaCurso.GetIDCategoriaSeminario();
                        daoModelCategoriaCurso.Signout();
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
                    salida = Mensaje(daoModelCurso.Edit(_objCurso), "El curso ha sido actualizado", "Ha ocurrido un error al intentar actualizar el Curso");
                    daoModelCurso.Signout();
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
                    response.setContentType("application/text");
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
        try {
            daoModelCurso = new ModelCurso();
            if (tipo.equals("Seminario")) {
                respuesta = daoModelCurso.buscarSeminarioPorID(id);
            } else {
                respuesta = daoModelCurso.buscarCursoPorID(id);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Mensaje(false, "", "Ha ocurrido un error en el Controller " + e.getMessage());
        } finally {

            daoModelCurso.Signout();
        }
        salida = new Gson().toJson(respuesta);
        return salida;
    }

    public String cambiarEstado(int id, String tipo) {
        respuesta = new LinkedHashMap<>();
        int estado = 0;
        try {
            daoModelCurso = new ModelCurso();
            if (tipo == null) {
                respuesta = daoModelCurso.buscarCursoPorID(id);
            } else if (tipo.equals("Seminario")) {
                respuesta = daoModelCurso.buscarSeminarioPorID(id);
            } else {
                respuesta = daoModelCurso.buscarCursoPorID(id);
            }
            estado = Integer.parseInt(respuesta.get("estadoCurso"));
            estado = estado > 0 ? 0 : 1;
            _objCurso = new ObjCurso();
            _objCurso.setIdCurso(id);
            _objCurso.setEstadoCurso(estado);
            return Mensaje(daoModelCurso.cambiarEstado(_objCurso), "El estado ha sido actualizado", "Ha ocurrido un error al intentar actualizar el estado");

        } catch (Exception e) {
            return Mensaje(false, "", "Ha ocurrido un error en el controller " + e.getMessage());
        } finally {
            daoModelCurso.Signout();
        }
    }

    public String getCursosDisponibles() {
        List<Map> lista = new ArrayList<>();
        respuesta = null;
        try {
            daoModelCurso = new ModelCurso();
            lista = daoModelCurso.ListCursosDisponibles();

        } catch (Exception e) {
            respuesta = new LinkedHashMap<>();
            respuesta.put("mensaje", "Ups, al parecer ha ocurrio un error: " + e + ".");
            respuesta.put("tipo", "error");
            lista.add(respuesta);

        } finally {
            daoModelCurso.Signout();
        }
        String salida = new Gson().toJson(lista);
        return salida;
    }

    public String getSeminariosDisponibles() {
        String resultado = "";
        List<Map> lista = new ArrayList<>();
        respuesta = null;
        try {
            daoModelCurso = new ModelCurso();
            lista = daoModelCurso.ListSeminariosDisponibles();
            for (Map result : lista) {
                resultado += "<div class=\"col-md-6\">\n"
                        + "<div class=\"panel panel-default\">\n"
                        + "<div class=\"panelCursos-Heading\">\n"
                        + "<div class=\"panel-title text-center\">" + result.get("nombreCurso") + "</div>\n"
                        + "</div>\n"
                        + "<div class=\"panel-body\">\n"
                        + "<div class=\"row\">\n"
                        + "<div class=\"col-md-6\">Precio:\n"
                        + "<label id=\"precio\">" + result.get("precioCurso") + "</label>\n"
                        + "</div>\n"
                        + "<div class=\"col-md-6\">Clases:\n"
                        + "<label id=\"clases\">" + result.get("cantidadClases") + "</label>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "<div class=\"row\">\n"
                        + "<div class=\"col-md-6\">Horas (Por Clase):\n"
                        + "<label id=\"horas\">" + result.get("horasPorClase") + "</label>\n"
                        + "</div>\n"
                        + "<div class=\"col-md-5\">\n"
                        + "<button class=\"btn btn-sm btn-default\" id=\"btnPreincripcion\" onclick=\"seminario.preinscripcion(" + result.get("idCurso") + ", this)\">Preinscribirse</button>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "</div>";
            }
        } catch (Exception e) {
            respuesta = new LinkedHashMap<>();
            respuesta.put("mensaje", "Ups, al parecer ha ocurrio un error: " + e + ".");
            respuesta.put("tipo", "error");
            String salida = new Gson().toJson(respuesta);
            return salida;
        } finally {
            daoModelCurso.Signout();
        }
        return resultado;
    }

    public String getTableCursos() {
        List<String[]> lista = new ArrayList<>();
        ResultSet result = null;
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
                arreglo[0] = String.valueOf(result.getInt("idCurso"));
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
            System.err.println("Ha Ocurrido un error en el getTableCursos ");
            for (StackTraceElement lista1 : e.getStackTrace()) {
                System.err.println(lista1);
            }
        } finally {
            daoModelCurso.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    public String getTableSeminarios() {
        List<String[]> lista = new ArrayList<>();
        ResultSet result = null;
        try {
            daoModelCurso = new ModelCurso();
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
            daoModelCurso.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    public String getOptionsCursos() {
        String OptionsCursos = "";
        List<Map> cursos;
        try {
            daoModelCurso = new ModelCurso();
            cursos = daoModelCurso.ListCursosDisponibles();
            for (Map<String, String> curso : cursos) {
                OptionsCursos += "<option value=\"" + curso.get("idCurso").trim() + "\">" + curso.get("nombreCurso").trim() + "</option>";
            }

        } catch (Exception e) {
            OptionsCursos = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelCurso.Signout();
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
