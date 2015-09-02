/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCredito;
import Model.DTO.ObjMovimiento;
import Model.Data.ModelCredito;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author David
 */
public class ControllerCredito extends HttpServlet {

    ObjCredito _objCredito = new ObjCredito();
    ObjMovimiento _objMovimiento;
    ModelCredito daoModelCredito;
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
            String nombre, descripcion, aux, salida,
                    tipo = "";
            int estado = 0, cantidadClases, categoria, id, horasPorClase, precio;

            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Abonar">
                // <editor-fold defaultstate="collapsed" desc="Registrar un abono">
                case "Abonar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = abonar(request);
                    response.getWriter().write(salida);
                    break;
                }

                // </editor-fold>
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="Consultar Detalle">
                case "ConsultarDetalle": {
                    int idCredito = Integer.parseInt(request.getParameter("idCredito"));
                    String documentoCliente = request.getParameter("documentoCliente");
                    daoModelCredito = new ModelCredito();
                    daoModelCredito.buscarCreditoByID(idCredito);
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

                // </editor-fold>
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

                // </editor-fold>
            }
        }
    }

    private String abonar(HttpServletRequest request) {
        daoModelCredito = new ModelCredito();
        _objMovimiento = new ObjMovimiento();
        int idCredito = Integer.parseInt(request.getParameter("txtIdCredito"));
        int valorAbono = Integer.parseInt(request.getParameter("txtValorAbono"));
        String documentoCliente = request.getParameter("documentoCliente");
        String documentoUsuario = request.getParameter("documentoUsuario");
        _objCredito = new ObjCredito();
        _objCredito.setIdCredito(idCredito);
        _objMovimiento.setTotalMovimiento(valorAbono);
        _objMovimiento.setDocumentoAuxiliar(documentoCliente);
        _objMovimiento.setDocumentoUsuario(documentoUsuario);        
        String salida = Utilidades.mensaje(daoModelCredito.AddAbono(_objCredito, _objMovimiento, null, null, valorAbono),
                "El abono ha sido registrado", "Ha ocurrido un error al intentar registrar el abono");
        daoModelCredito.Signout();
        return salida;
    }

    private String cambiarEstado(int idCredito) {
        int estadoCredito = 0;
        String objReturn = null;

        try {
            daoModelCredito = new ModelCredito();

            ResultSet result;

            result = daoModelCredito.buscarCreditoByID(idCredito);

            while (result.next()) {
                estadoCredito = Integer.parseInt(result.getString("estadoCredito"));
            }

            estadoCredito = (estadoCredito > 0) ? 0 : 1;
            _objCredito = new ObjCredito();
            _objCredito.setIdCredito(idCredito);
            _objCredito.setEstadoCredito(estadoCredito);
            objReturn = Utilidades.mensaje(daoModelCredito.cambiarEstadoCredito(_objCredito),
                    "El estado del crédito ha sido actualizado",
                    "Ha ocurrido un error al intentar actualizar el estado del crédito");
        } catch (SQLException | NumberFormatException e) {
            objReturn = Utilidades.mensaje(false, "", "Ha ocurrido un error en el controlador " + e.getMessage());
        } finally {
            daoModelCredito.Signout();
        }

        return objReturn;
    }

    private String consultar(int id) {
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
            salida = new Gson().toJson(Utilidades.mensaje(false, "", "Ha ocurrido un error en el Controller " + e.getMessage()));

            return salida;
        }

        salida = new Gson().toJson(respuesta);

        return salida;
    }

    private String getTableCredito() {
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

                String[] arreglo = new String[8];

                arreglo[0] = result.getString("idCredito").trim();
                arreglo[1] = result.getString("documentoUsuario").trim();
                arreglo[2] = result.getString("nombre").trim();
                arreglo[3] = result.getString("fechaInicio").trim();
                arreglo[4] = result.getString("saldoInicial").trim();
                arreglo[5] = result.getString("saldoActual").trim();
                arreglo[6] = "<a class=\"btn-sm btn-" + estado[0]
                        + " btn-block\" href=\"javascript:void(0)\"  onclick=\"credito.estado(" + arreglo[0]
                        + ")\">" + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[7]
                        = "<div class=\"btn-group\">\n"
                        + "  <button type=\"button\" class=\"btn btn-info btn-block dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "    Acciones <span class=\"caret\"></span>\n" + "  </button>\n"
                        + "  <ul class=\"dropdown-menu\">\n"
                        + "    <li><a class=\"btn btn-success\" href=\"javascript:void(0)\" onclick=\"credito.consultarDetalle(" + arreglo[0] + ", '" + arreglo[1] + "' )\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span>Consultar</a></li>\n"
                        + "    <li><a class=\"btn btn-primary \"  href=\"javascript:void(0)\" onclick=\"abono.registrar("
                        + arreglo[0] + ", '"+arreglo[1]+"')\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span>Abonar</a></li>\n" + "  </ul>\n" + "</div>";
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
    }    // </editor-fold>

}
