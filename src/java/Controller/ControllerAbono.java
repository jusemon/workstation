/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import Model.DTO.ObjCredito;
import Model.DTO.ObjMovimiento;
import Model.Data.ModelAbono;
import Model.Data.ModelCredito;
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
 * @author David
 */
public class ControllerAbono extends HttpServlet {

    ModelCredito daoModelCredito;    
    ObjCredito _objCredito = new ObjCredito();
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
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") != null) {
            String salida, aux;
            int id;
            Map<String, String> respuesta;
            ResultSet result;
            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Registrar un abono">
                case "Registrar": {
                    daoModelCredito = new ModelCredito();

                    int idCredito = Integer.parseInt(request.getParameter("txtIdCredito"));
                    int valorAbono = Integer.parseInt(request.getParameter("txtValorAbono"));

                    _objCredito.setIdCredito(idCredito);
                    _objMovimiento.setTotalMovimiento(valorAbono);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = Mensaje(daoModelCredito.AddAbono(_objCredito, _objMovimiento, null, null, valorAbono), "El abono ha sido registrado", "Ha ocurrido un error al intentar registrar el abono");
                    daoModelCredito.Signout();
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>
            }
            response.sendRedirect("caja.jsp");
        }
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
