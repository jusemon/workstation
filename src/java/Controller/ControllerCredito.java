/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DTO.ObjCredito;
import Model.Data.ModelCredito;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public class ControllerCredito extends HttpServlet {

    ModelCredito daoModelCredito;
    ObjCredito _ObjCredito = new ObjCredito();
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

            String nombre, descripcion, aux, salida, tipo = "";
            int estado = 0, cantidadClases, categoria, id, horasPorClase, precio;

            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Abonar">
                case "Abonar":{
                    
                }
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="Listar los créditos">
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableCredito());
                    break;
                }
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="Cambiar el estado de un crédito">
                case "Estado": {
                    aux = request.getParameter("idCredito");
                    id = Integer.parseInt(aux);
                    salida = cambiarEstado(id);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>
                // <editor-fold defaultstate="collapsed" desc="Consultar un crédito">
                case "Consultar": {
                    aux = request.getParameter("id");
                    id = Integer.parseInt(aux.trim());
                    if (request.getParameter("type") != null) {
                        tipo = request.getParameter("type");
                    }
                    salida = consultar(id);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>
            }
        }
    }

    public String cambiarEstado(int idCredito) {
        int estadoCredito = 0;
        String objReturn = null;
        try {
            daoModelCredito = new ModelCredito();
            ResultSet result;
            result = daoModelCredito.buscarCreditoByID(idCredito);
            while (result.next()) {
                estadoCredito = Integer.parseInt(result.getString("estadoCredito"));
            }
            estadoCredito = estadoCredito > 0 ? 0 : 1;
            _ObjCredito = new ObjCredito();
            _ObjCredito.setIdCredito(idCredito);
            _ObjCredito.setEstadoCredito(estadoCredito);
            objReturn = Mensaje(daoModelCredito.cambiarEstadoCredito(_ObjCredito), "El estado del crédito ha sido actualizado", "Ha ocurrido un error al intentar actualizar el estado del crédito");
        } catch (SQLException | NumberFormatException e) {
            objReturn = Mensaje(false, "", "Ha ocurrido un error en el controlador " + e.getMessage());
        } finally {
            daoModelCredito.Signout();
        }
        return objReturn;
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

    public String consultar(int id) {
        String salida;
        respuesta = new LinkedHashMap<>();
        ResultSet result = null;
        try {
            daoModelCredito = new ModelCredito();
            result = daoModelCredito.buscarCreditoByID(id);
            while (result.next()) {
                respuesta.put("idCredito", result.getString("idCredito"));
                respuesta.put("documentoUsuario", result.getString("documentoUsuario"));
                respuesta.put("fechaInicio", result.getString("fechaInicio"));
                respuesta.put("saldoInicial", result.getString("saldoInicial"));
                respuesta.put("saldoActual", result.getString("saldoActual"));
                respuesta.put("estadoCredito", result.getString("estadoCredito"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());            
            daoModelCredito.Signout();
            salida = new Gson().toJson(Mensaje(false, "", "Ha ocurrido un error en el Controller " + e.getMessage()));
            return salida;
        }
        salida = new Gson().toJson(respuesta);
        return salida;
    }

    public String getTableCredito() {
        List<String[]> lista = new ArrayList<>();
        try {
            ResultSet result = null;
            daoModelCredito = new ModelCredito();
            result = daoModelCredito.ListAll();
            while (result.next()) {
                String[] estado = {"success", "ok"};
                if (result.getInt("estadoCredito") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                String[] arreglo = new String[7];
                arreglo[0] = result.getString("idCredito").trim();
                arreglo[1] = result.getString("documentoUsuario").trim();
                arreglo[2] = result.getString("fechaInicio").trim();
                arreglo[3] = result.getString("saldoInicial").trim();
                arreglo[4] = result.getString("saldoActual").trim();
                arreglo[5] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"credito.estado(" + arreglo[0] + ")\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";

                arreglo[6] = "<div class=\"btn-group\">\n"
                        + "  <button type=\"button\" class=\"btn btn-info btn-block dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "    Acciones <span class=\"caret\"></span>\n"
                        + "  </button>\n"
                        + "  <ul class=\"dropdown-menu\">\n"
                        + "    <li><a class=\"btn btn-success\" href=\"javascript:void(0)\" onclick=\"credito.myAjax('Consultar'," + arreglo[0] + ", '', 'Credito')\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span>Consultar</a></li>\n"
                        + "    <li><a class=\"btn btn-primary \"  href=\"javascript:void(0)\" onclick=\"abono.myAjax('Registrar'," + arreglo[0] + ",'Abonar', 'Credito')\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span>Abonar</a></li>\n"
                        + "  </ul>\n"
                        + "</div>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller " + e.toString());
        } finally {
            daoModelCredito.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
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
