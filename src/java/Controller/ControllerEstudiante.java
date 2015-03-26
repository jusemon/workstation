/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCliente;
import Model.Data.ModelCliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zack
 */
public class ControllerEstudiante extends HttpServlet {

    ObjCliente _objCliente = new ObjCliente();
    ModelCliente daoModelCliente = new ModelCliente();

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
            if (request.getParameter("action").equals("Registrar")) {
                _objCliente.setTipoDocumento(request.getParameter("ddlIdentificacion"));
                _objCliente.setNumeroDocumento(Integer.parseInt(request.getParameter("txtIdentificacion")));
                _objCliente.setNombreCliente(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));
                _objCliente.setApellidoCliente(new String(request.getParameter("txtApellido").getBytes("ISO-8859-1"), "UTF-8"));
                _objCliente.setGeneroCliente(Integer.parseInt(request.getParameter("radioGenero")));
                _objCliente.setFechaNacimiento(request.getParameter("dateFechaNacimiento"));
                _objCliente.setDireccionCliente(request.getParameter("txtDireccion"));
                _objCliente.setTelefonoFijo(new String(request.getParameter("txtTelefono").getBytes("ISO-8859-1"), "UTF-8"));
                _objCliente.setTelefonoMovil(new String(request.getParameter("txtCelular").getBytes("ISO-8859-1"), "UTF-8"));
                _objCliente.setEmailCliente(new String(request.getParameter("txtCorreo").getBytes("ISO-8859-1"), "UTF-8"));
                _objCliente.setEstadoCliente(Integer.parseInt(request.getParameter("radioEstado")));
                daoModelCliente.Add(_objCliente);
            }
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

}
