/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author David
 */
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DTO.ObjEmpresa;
import Model.Data.ModelEmpresa;

public class ControllerEmpresa extends HttpServlet {

    ModelEmpresa daoModelEmpresa;
    ObjEmpresa _objEmpresa = new ObjEmpresa();

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
            switch (request.getParameter("action")) {
                case "Registrar": {
                    daoModelEmpresa = new ModelEmpresa();
                    String nitEmpresa = new String(request.getParameter("txtNitEmpresa").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setNitEmpresa(nitEmpresa);
                    String nombreEmpresa = new String(request.getParameter("txtNombreEmpresa").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setNombreEmpresa(nombreEmpresa);
                    String direccionEmpresa = new String(request.getParameter("txtDireccionEmpresa").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setDireccionEmpresa(direccionEmpresa);
                    String nombreContacto = new String(request.getParameter("txtNombreContacto").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setNombreContacto(nombreContacto);
                    String telefonoContacto = new String(request.getParameter("txtTelefonoContacto").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setTelefonoContacto(telefonoContacto);
                    String emailContacto = new String(request.getParameter("txtEmailContacto").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setEmailContacto(emailContacto);
                    daoModelEmpresa.Add(_objEmpresa);

                    break;
                }
                case "Editar": {
                    daoModelEmpresa = new ModelEmpresa();
                    String nitEmpresa = new String(request.getParameter("txtNitEmpresa").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setNitEmpresa(nitEmpresa);
                    String nombreEmpresa = new String(request.getParameter("txtNombreEmpresa").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setNombreEmpresa(nombreEmpresa);
                    String direccionEmpresa = new String(request.getParameter("txtDireccionEmpresa").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setDireccionEmpresa(direccionEmpresa);
                    String nombreContacto = new String(request.getParameter("txtNombreContacto").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setNombreContacto(nombreContacto);
                    String telefonoContacto = new String(request.getParameter("txtTelefonoContacto").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setTelefonoContacto(telefonoContacto);
                    String emailContacto = new String(request.getParameter("txtEmailContacto").getBytes("ISO-8859-1"), "UTF-8");
                    _objEmpresa.setEmailContacto(emailContacto);
                    daoModelEmpresa.Update(_objEmpresa);
                    break;
                }
            }
            response.sendRedirect("empresa.jsp");
        }
    }

    public String getTableEmpresa() {
        ResultSet result;
        String tableEmpresa = "";
        try {
            daoModelEmpresa = new ModelEmpresa();
            result = daoModelEmpresa.ListAll();
            while (result.next()) {
                tableEmpresa += "<tr data-tipo=\"empresa\" >";
                tableEmpresa += "<td class=\"text-center\">" + result.getString("nitEmpresa").trim() + "</td>";
                tableEmpresa += "<td class=\"text-center\">" + result.getString("nombreEmpresa").trim() + "</td>";
                tableEmpresa += "<td class=\"text-center\">" + result.getString("direccionEmpresa").trim() + "</td>";
                tableEmpresa += "<td class=\"text-center\">" + result.getString("nombreContacto").trim() + "</td>";
                tableEmpresa += "<td class=\"text-center\">" + result.getString("telefonoContacto").trim() + "</td>";
                tableEmpresa += "<td class=\"text-center\">" + result.getString("emailContacto").trim() + "</td>";
                tableEmpresa += "<td class=\"text-center\"><a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"editarEmpresa()\">\n"
                        + "<span class=\"glyphicon glyphicon-pencil\"></span></a>\n</td>";
                tableEmpresa += "</tr>";
            }
        } catch (Exception e) {
            tableEmpresa = "Ha ocurrido un error." + e.getMessage();
        } finally {
            daoModelEmpresa.Signout();
        }

        return tableEmpresa;
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
