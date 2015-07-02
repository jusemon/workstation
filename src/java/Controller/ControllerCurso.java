/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Validaciones.Validador;
import com.google.gson.Gson;
import Model.DTO.ObjCurso;
import Model.DTO.ObjInscrito;
import Model.DTO.ObjSeminario;
import Model.Data.ModelCategoriaCurso;
import Model.Data.ModelCurso;
import Model.Data.ModelInscrito;
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
 * @author Zack
 */
public class ControllerCurso extends HttpServlet {

    ObjCurso _objCurso = new ObjCurso();
    ObjSeminario _objSeminario = new ObjSeminario();
    ObjInscrito _objInscrito = new ObjInscrito();
    ModelCurso daoModelCurso;
    ModelCategoriaCurso daoModelCategoriaCurso;
    ModelInscrito daoModelInscrito;
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
            String nombre, descripcion, aux, salida, tipo = "", fechaSeminario = null;
            int estado = 0, cantidadClases, categoria, id, horasPorClase, precio, cupoSeminario = 0;
            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Registrar un Curso o Seminario">
                case "Registrar": {
                    tipo = request.getParameter("tipo");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = registrar(request, tipo);
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

                    tipo = request.getParameter("tipo");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = editar(request, tipo);
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

                // <editor-fold defaultstate="collapsed" desc="Obtener el detalle del Seminario">
                case "DetalleSeminario": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(detalleSeminario(request));
                    break;
                }
                //</editor-fold>
                // <editor-fold defaultstate="collapsed" desc="Obtener el detalle del Seminario">
                case "DetalleAsistentesSeminario": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(detalleSAsistentesSeminario(request));
                    break;
                }
                //</editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Registrar asistente al seminario">
                case "RegistrarAsistente": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(registrarAsistente(request));
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
                String[] arreglo = new String[4];
                arreglo[0] = String.valueOf(result.getInt("idCurso"));
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = "<a class=\"btn btn-block btn-" + estado[0] + "\" href=\"javascript:void(0)\"  onclick=\"curso.myAjax('Estado'," + arreglo[0] + ", '','Curso')\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[3] = "<div class=\"btn-group\">\n"
                        + "  <button type=\"button\" class=\"btn btn-info btn-block dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "    Acciones <span class=\"caret\"></span>\n"
                        + "  </button>\n"
                        + "  <ul class=\"dropdown-menu\">\n"
                        + "    <li><a class=\"btn btn-success\" href=\"javascript:void(0)\" onclick=\"curso.myAjax('Consultar'," + arreglo[0] + ", '', 'Curso')\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span>Consultar</a></li>\n"
                        + "    <li><a class=\"btn btn-primary \"  href=\"javascript:void(0)\" onclick=\"curso.myAjax('Consultar'," + arreglo[0] + ",'Editar', 'Curso')\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span>Editar</a></li>\n"
                        + "  </ul>\n"
                        + "</div>";
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
                String[] arreglo = new String[6];
                arreglo[0] = result.getString("idCurso").trim();
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = "<a class=\"btn btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"seminario.myAjax('Estado'," + arreglo[0] + ", '','Seminario')\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[3] = "<div class=\"btn-group\">\n"
                        + "  <button type=\"button\" class=\"btn btn-info btn-block dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "    Acciones <span class=\"caret\"></span>\n"
                        + "  </button>\n"
                        + "  <ul class=\"dropdown-menu\">\n"
                        + "    <li><a class=\"btn btn-success \" href=\"javascript:void(0)\" onclick=\"seminario.myAjax('Consultar'," + arreglo[0] + ", '','Seminario')\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span> Consultar</a></li>\n"
                        + "    <li><a class=\"btn btn-primary \"  href=\"javascript:void(0)\" onclick=\"seminario.myAjax('Consultar'," + arreglo[0] + ",'Editar','Seminario')\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span> Editar</a></li>\n"
                        + "    <li><a class=\"btn btn-info \"  href=\"javascript:void(0)\" onclick=\"seminario.consultarDetalle(" + arreglo[0] + ")\">"
                        + "<span class=\"glyphicon glyphicon-list-alt\"></span> Detalle</a></li>\n"
                        + "  </ul>\n"
                        + "</div>";
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

    public String registrar(HttpServletRequest request, String tipo) {
        daoModelCurso = new ModelCurso();
        String nombre, descripcion, salida, fechaSeminario = null;
        int estado = 0, cantidadClases, categoria, horasPorClase, precio, cupoSeminario = 0;
        if (Validador.validarString(request.getParameter("txtNombre").trim())
                && Validador.validarString(request.getParameter("txtDescripcion").trim())
                && Validador.validarString(request.getParameter("txtPrecio").trim())
                && Validador.validarString(request.getParameter("ddlEstado"))) {
            nombre = request.getParameter("txtNombre").trim();
            descripcion = request.getParameter("txtDescripcion").trim();
            precio = Integer.parseInt(request.getParameter("txtPrecio").trim());
            estado = Integer.parseInt(request.getParameter("ddlEstado").trim());
            horasPorClase = Integer.parseInt(request.getParameter("txtCantidadHoras").trim());
            if (tipo.equals("Curso")) {
                cantidadClases = Integer.parseInt(request.getParameter("txtCantidadClases").trim());
                categoria = Integer.parseInt(request.getParameter("ddlCategoria").trim());
                _objCurso = new ObjCurso();
                _objCurso.setIdCategoriaCurso(categoria);
                _objCurso.setDescripcionCurso(descripcion);
                _objCurso.setNombreCurso(nombre);
                _objCurso.setCantidadClases(cantidadClases);
                _objCurso.setHorasPorClase(horasPorClase);
                _objCurso.setEstadoCurso(estado);
                _objCurso.setPrecioCurso(precio);
                salida = Mensaje(daoModelCurso.Add(_objCurso), "El Curso ha sido registrado", "Ha ocurrido un error al intentar registrar el Curso");
                daoModelCurso.Signout();
                return (salida);
            } else {
                fechaSeminario = request.getParameter("txtFechaSeminario").trim();
                cupoSeminario = Integer.parseInt(request.getParameter("txtCupoSeminario").trim());
                cantidadClases = 1;
                daoModelCategoriaCurso = new ModelCategoriaCurso();
                categoria = daoModelCategoriaCurso.GetIDCategoriaSeminario();
                daoModelCategoriaCurso.Signout();
                _objSeminario = new ObjSeminario();
                _objSeminario.setDescripcionCurso(descripcion);
                _objSeminario.setNombreCurso(nombre);
                _objSeminario.setHorasPorClase(horasPorClase);
                _objSeminario.setEstadoCurso(estado);
                _objSeminario.setPrecioCurso(precio);
                _objSeminario.setIdCategoriaCurso(categoria);
                _objSeminario.setCantidadClases(cantidadClases);
                _objSeminario.setCupoSeminario(cupoSeminario);
                _objSeminario.setFechaSeminario(formatearFecha(fechaSeminario));
                salida = Mensaje(daoModelCurso.Add(_objSeminario), "El Seminario ha sido registrado", "Ha ocurrido un error al intentar registrar el Seminario");
                daoModelCurso.Signout();
                return (salida);
            }
        } else {
            return Mensaje(false, null, "Uno o mas campos contienen datos erroneos");
        }

    }

    public String editar(HttpServletRequest request, String tipo) {
        daoModelCurso = new ModelCurso();
        String nombre, descripcion, salida, fechaSeminario = null;
        int estado = 0, cantidadClases, categoria, id, horasPorClase, precio, cupoSeminario = 0;
        nombre = request.getParameter("txtNombre").trim();
        descripcion = request.getParameter("txtDescripcion").trim();
        id = Integer.parseInt(request.getParameter("idCurso").trim());
        precio = Integer.parseInt(request.getParameter("txtPrecio").trim());
        estado = Integer.parseInt(request.getParameter("ddlEstado").trim());
        horasPorClase = Integer.parseInt(request.getParameter("txtCantidadHoras").trim());
        if (tipo.equals("Curso")) {
            cantidadClases = Integer.parseInt(request.getParameter("txtCantidadClases").trim());
            categoria = Integer.parseInt(request.getParameter("ddlCategoria").trim());
            _objCurso = new ObjCurso();
            _objCurso.setIdCurso(id);
            _objCurso.setIdCategoriaCurso(categoria);
            _objCurso.setDescripcionCurso(descripcion);
            _objCurso.setNombreCurso(nombre);
            _objCurso.setCantidadClases(cantidadClases);
            _objCurso.setHorasPorClase(horasPorClase);
            _objCurso.setEstadoCurso(estado);
            _objCurso.setPrecioCurso(precio);
            salida = Mensaje(daoModelCurso.Edit(_objCurso), "El Curso ha sido registrado", "Ha ocurrido un error al intentar registrar el Curso");
            daoModelCurso.Signout();
            return (salida);
        } else {
            fechaSeminario = request.getParameter("txtFechaSeminario").trim();
            cupoSeminario = Integer.parseInt(request.getParameter("txtCupoSeminario").trim());
            cantidadClases = 1;
            daoModelCategoriaCurso = new ModelCategoriaCurso();
            categoria = daoModelCategoriaCurso.GetIDCategoriaSeminario();
            daoModelCategoriaCurso.Signout();
            _objSeminario = new ObjSeminario();
            _objSeminario.setIdCurso(id);
            _objSeminario.setDescripcionCurso(descripcion);
            _objSeminario.setNombreCurso(nombre);
            _objSeminario.setHorasPorClase(horasPorClase);
            _objSeminario.setEstadoCurso(estado);
            _objSeminario.setPrecioCurso(precio);
            _objSeminario.setIdCategoriaCurso(categoria);
            _objSeminario.setCantidadClases(cantidadClases);
            _objSeminario.setCupoSeminario(cupoSeminario);
            _objSeminario.setFechaSeminario(formatearFecha(fechaSeminario));
            salida = Mensaje(daoModelCurso.Edit(_objSeminario), "El Seminario ha sido registrado", "Ha ocurrido un error al intentar registrar el Seminario");
            daoModelCurso.Signout();
            return (salida);
        }
    }

    public String formatearFecha(String fecha) {
        String[] salida = new String[4];
        salida[0] = fecha.substring(6, 10);
        salida[1] = fecha.substring(3, 5);
        salida[2] = fecha.substring(0, 2);
        salida[3] = fecha.substring(11);
        return salida[0] + "-" + salida[1] + "-" + salida[2] + " " + salida[3];
    }

    private String detalleSeminario(HttpServletRequest request) {
        int idSeminario = Integer.parseInt(request.getParameter("idSeminario"));
        Map<String, Object> salida = new LinkedHashMap<>();
        daoModelCurso = new ModelCurso();
        Map seminario = daoModelCurso.buscarSeminarioPorID(idSeminario);
        salida.put("seminario", seminario);
        daoModelCurso.Signout();
        return new Gson().toJson(salida);

    }

    private String registrarAsistente(HttpServletRequest request) {
        String documento = null, nombres = null, correo = null, telefono = null;
        int idSeminario;
        if (request.getParameter("ddlIdentificacion") == null | request.getParameter("txtIdentificacion") == null) {
            documento = "";
        } else {
            try {
                documento = request.getParameter("ddlIdentificacion") + request.getParameter("txtIdentificacion");
            } catch (Exception e) {
                documento = "";
            }
        }
        if (Validador.validarNombresCompletos(request.getParameter("txtNombre").trim())
                && Validador.validarTelefono(request.getParameter("txtTelefono").trim())
                && Validador.validarEmail(request.getParameter("txtCorreo").trim())) {
            nombres = request.getParameter("txtNombre").trim();
            telefono = request.getParameter("txtTelefono").trim();
            correo = request.getParameter("txtCorreo").trim();
            idSeminario = Integer.parseInt(request.getParameter("idSeminario").trim());
            _objInscrito = new ObjInscrito();
            _objInscrito.setIdseminario(idSeminario);
            _objInscrito.setDocumento(documento);
            _objInscrito.setNombres(nombres);
            _objInscrito.setTelefono(telefono);
            _objInscrito.setCorreo(correo);
            String aux = "";
            try {
                daoModelInscrito = new ModelInscrito();
                aux = Mensaje(daoModelInscrito.Add(_objInscrito), "Inscripción realizada", "Ha ocurrido un error");
                daoModelInscrito.Signout();
            } catch (Exception e) {
                System.err.println(e);
            }
            return aux;
        }
        return Mensaje(false, "", "Uno o más campos contienen errores");
    }

    private String detalleSAsistentesSeminario(HttpServletRequest request) {
        int idSeminario = Integer.parseInt(request.getParameter("idSeminario"));
        List registrados = new ArrayList();
        String[] auxiliar;
        daoModelCurso = new ModelCurso();
        ResultSet rs = daoModelCurso.ListAsistentes(idSeminario);
        try {
            int contador = 1;
            while (rs.next()) {
                auxiliar = new String[6];
                auxiliar[0] = String.valueOf(contador);
                try {
                    auxiliar[1] = rs.getString("documento").substring(0, 2);
                    auxiliar[2] = rs.getString("documento").substring(2);
                } catch (Exception e) {
                    auxiliar[1] = "NA";
                    auxiliar[2] = "No registrado";
                }

                auxiliar[3] = rs.getString("nombres");
                auxiliar[4] = rs.getString("telefono");
                auxiliar[5] = rs.getString("correo");
                registrados.add(auxiliar);
                contador++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCurso.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            daoModelCurso.Signout();
        }
        String resultado = new Gson().toJson(registrados);
        return "{\"data\":"+resultado+"}";
    }
}
