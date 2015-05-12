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
        
        if (request.getParameter("action")!= null){
            switch (request.getParameter("action")){
                
                // <editor-fold defaultstate="collapsed" desc="Registrar un crédito">
                case "Registrar": {
                    daoModelCredito = new ModelCredito();
                    int idCategoriaCredito = Integer.parseInt(request.getParameter("ddlCategoriaCredito").trim());
                    _ObjCredito.setIdCategoriaCredito(idCategoriaCredito);
                    String documentoUsuario = request.getParameter("txtDocumentoUsuario").trim();
                    _ObjCredito.setDocumentoUsuario(documentoUsuario);
                    String fechaInicio = request.getParameter("txtFechaInicio").trim();
                    _ObjCredito.setFechaInicio(fechaInicio);
                    double saldoInicial = Double.parseDouble(request.getParameter("txtSaldoInicial").trim());
                    _ObjCredito.setSaldoInicial(saldoInicial);
                    double saldoActual = Double.parseDouble(request.getParameter("txtSaldoActual").trim());
                    _ObjCredito.setSaldoActual(saldoActual);
                    
                    String salida = Mensaje(daoModelCredito.Add(_ObjCredito), "La crédito ha sido registrado", "Ha ocurrido un error al intentar registrar el crédito");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
            }        
        }
    }
    
    public String cambiarEstado(int idCredito, int idCategoriaCredito) {
        ResultSet result;
        int estado = 0;
        try {
            daoModelCredito = new ModelCredito();
            if (idCategoriaCredito != 1){
                result = daoModelCredito.buscarTipoCredito1(idCredito);
            } else if (idCategoriaCredito == 2) {
                result = daoModelCredito.buscarTipoCredito2(idCredito);
            } else {
                result = daoModelCredito.buscarTipoCredito1(idCredito);
            }
            while (result.next()) {
                estado = Integer.parseInt(result.getString("estadoCredito"));
            }
            estado = estado > 0 ? 0 : 1;
            _ObjCredito = new ObjCredito();
            _ObjCredito.setIdCredito(idCredito);
            _ObjCredito.setEstadoCredito(estado);
            return Mensaje(daoModelCredito.cambiarEstado(_ObjCredito), "El estado ha sido actualizado", "Ha ocurrido un error al intentar actualizar el estado");

        } catch (Exception e) {
            return Mensaje(false, "", "Ha ocurrido un error en el controlador " + e.getMessage());
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
