/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjClase;
import Model.Data.ModelCurso;
import Model.Data.ModelClase;
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
public class ControllerClase extends HttpServlet {

    ObjClase _objClase = new ObjClase();
    ModelClase daoModelClase = new ModelClase();
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
        if (request.getParameter("action") != null) {
            int estado = 0;
            SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoFechaSalida = new SimpleDateFormat("yyyy-MM-dd");
            switch (request.getParameter("action")) {
                
                // <editor-fold defaultstate="collapsed" desc="Registrar una Clase">
                case "Registrar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    try {
                        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                        String fecha = request.getParameter("dateFecha");
                        estado = Integer.parseInt(request.getParameter("estadoFicha"));
                        _objClase.setIdCurso(idCurso);
                        _objClase.setFecha(formatoFechaSalida.format(formatoFechaEntrada.parse(fecha)));
                        daoModelClase = new ModelClase();
                        String salida = Mensaje(daoModelClase.Add(_objClase), "La Ficha ha sido registrada", "Ha ocurrido un error al intentar registrar la ficha");
                        daoModelClase.Signout();
                        response.getWriter().write(salida);
                    } catch (ParseException ex) {
                        String salida = Mensaje(false, "", "Ha ocurrido un error en el formato de la fecha");
                        response.getWriter().write(salida);
                    }
                    break;
                }
                //</editor-fold>     
                
                // <editor-fold defaultstate="collapsed" desc="Editar una Clase">
                case "Editar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    int idFicha = Integer.parseInt(request.getParameter("idFicha"));
                    int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                    String fecha = request.getParameter("dateFecha");
                    int cupos = Integer.parseInt(request.getParameter("txtCupos"));
                    int precio = Integer.parseInt(request.getParameter("txtPrecio"));
                    estado = Integer.parseInt(request.getParameter("estadoFicha"));
                    daoModelClase = new ModelClase();
                    String salida = Mensaje(daoModelClase.Edit(_objClase), "La Ficha ha sido actualizada", "Ha ocurrido un error al intentar actualizar la ficha");
                    daoModelClase.Signout();
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>           
                
                // <editor-fold defaultstate="collapsed" desc="Cambiar el estado de una Clase">
                case "Estado": {
                    daoModelClase = new ModelClase();
                    String id = request.getParameter("id");
                    
                    try {
                        ResultSet result = daoModelClase.buscarPorDocumentoUsuario(id);
                        while (result.next()) {
                            estado = Integer.parseInt(result.getString("estado"));
                        }
                        estado = estado > 0 ? 0 : 1;
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        //String salida = Mensaje(daoModelFicha.cambiarEstado(_objClase), "El estado ha sido actualizado", "Ha ocurrido un error al intentar actualizar el estado");
                        daoModelClase.Signout();
                        //response.getWriter().write(salida);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                //</editor-fold>
                
                // <editor-fold defaultstate="collapsed" desc="Enlistar las Clases según el Estudiante">
                case "EnlistarClases": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableClases());
                    break;
                }
                //</editor-fold>
                
                // <editor-fold defaultstate="collapsed" desc="Enlistar las Fichas disponibles">
                case "fichasDisponibles": {
                    try {
                        List<Map> lista = new ArrayList<>();
                        Map<String, String> respuesta;
                        daoModelClase = new ModelClase();
                        ResultSet result = daoModelCurso.ListCursosDisponibles();
                        while (result.next()) {
                            respuesta = new LinkedHashMap<>();
                            respuesta.put("idFicha", result.getString("idFicha"));
                            respuesta.put("cuposDisponibles", result.getString("cuposDisponibles"));
                            respuesta.put("fechaInicio", result.getString("fechaInicio"));
                            respuesta.put("precioFicha", result.getString("precioFicha"));
                            respuesta.put("nombreCurso", result.getString("nombreCurso"));
                            lista.add(respuesta);
                        }
                        daoModelClase.Signout();
                        String salida = new Gson().toJson(lista);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);
                        break;
                    } catch (SQLException ex) {
                        System.err.println("Ha ocurrido un error en el controllerFicha" + ex.getMessage());

                    }
                }
                //</editor-fold>
                
            }
        }
    }

    public String getTableClases() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            int contador = 0;
            daoModelClase = new ModelClase();
            result = daoModelClase.ListAll();
            while (result.next()) {
                String[] estado = {"success", "ok"};
                if (result.getInt("estado") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                String[] arreglo = new String[7];
                arreglo[0] = result.getString("idFicha").trim();
                arreglo[1] = result.getString("nombreCurso").trim();
                arreglo[2] = result.getString("cuposDisponibles").trim();
                arreglo[3] = result.getString("precioFicha").trim();
                arreglo[4] = result.getString("fechaInicio").trim();
                arreglo[5] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"ficha.myAjax('Estado'," + arreglo[0] + ")\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"ficha.editar(" + contador + "," + result.getInt("estado") + ", " + result.getInt("idCurso") + ")\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                lista.add(arreglo);
                contador++;
            }
        } catch (SQLException e) {
            System.err.println("Ha Ocurrido un error de SQL " + e.getMessage());
        } finally {
            daoModelClase.Signout();
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
}
