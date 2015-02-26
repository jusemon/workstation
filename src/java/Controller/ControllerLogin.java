/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjUsuario;
import Model.Data.ModelUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class ControllerLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response // * @throws ServletException if a
     * servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ObjUsuario _objUsuario = new ObjUsuario();
    ModelUsuario _modelUsuario = new ModelUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("Action").equals("Iniciar Sesion")) {
                HttpSession session = request.getSession();
                String nombre = request.getParameter("nom");
                String pass = request.getParameter("pass");

                if (comprobarUsuario(nombre, pass)) {
                    session.setAttribute("usuario", nombre);
                    session.setAttribute("pass", pass);
                    session.setAttribute("correo",_objUsuario.getEmail());
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else if (request.getParameter("Action").equals("Cerrar Sesion")) {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        }
    }

    public boolean comprobarUsuario(String nombre, String pass) {

        _objUsuario.setNombre(nombre);
        _objUsuario.setPassword(pass);
        ResultSet rs = null;

        try {
            rs = _modelUsuario.Find(_objUsuario);
            while (rs.next()) {
                if (rs.getString("nombreUsuario").equals(nombre) && rs.getString("password").equals(pass)) {
                    _objUsuario.setEmail(rs.getString("email"));
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public String imprimirBarra() {
        return null;
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
