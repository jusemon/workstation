/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCategoriaCurso;
import Model.Data.ModelCategoriaCurso;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public class ControllerCategoriaCurso extends HttpServlet {

    ModelCategoriaCurso daoModelCategoriaCurso;
    ObjCategoriaCurso _objCategoriaCurso = new ObjCategoriaCurso();

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
                daoModelCategoriaCurso = new ModelCategoriaCurso();
                String nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                _objCategoriaCurso.setNombreCategoriaCurso(nombre);
                daoModelCategoriaCurso.Add(_objCategoriaCurso);
                response.sendRedirect("curso.jsp");
            }
        }

    }

    public String getTableCategoriaCurso() {

        ResultSet result;
        String tableCategoriaarticulos = "";
        daoModelCategoriaCurso = new ModelCategoriaCurso();
        try {
            result = daoModelCategoriaCurso.ListAll();

            while (result.next()) {
                tableCategoriaarticulos += "<tr>";
                tableCategoriaarticulos += "<td class=\"text-center\">" + result.getString("idtblCategoriaCurso").trim() + "</td>";
                tableCategoriaarticulos += "<td class=\"text-center\">" + result.getString("nombreCategoriaCurso").trim() + "</td>";
                tableCategoriaarticulos += "<td class=\"text-center\"><a class=\"btn-sm btn-primary btn-block \"  data-toggle=\"modal\"  data-target=\"#articulos\" href=\"javascript:void(0)\"  onclick=\"consultar()\">\n"
                        + "<span class=\"glyphicon glyphicon-pencil\"></span></a>\n</td>";
                tableCategoriaarticulos += "</tr>";

            }
        } catch (Exception e) {
            tableCategoriaarticulos = "Ha ocurrido un error" + e.getMessage();
        } finally {
            daoModelCategoriaCurso.Signout();
        }
        return tableCategoriaarticulos;
    }

    public String getOptionsCategorias() {
        ResultSet result;
        String OptionsCategorias = "";
        daoModelCategoriaCurso = new ModelCategoriaCurso();
        try {
            result = daoModelCategoriaCurso.ListAll();

            while (result.next()) {
                OptionsCategorias += "<option value=\"" + result.getString("idtblCategoriaCurso").trim() + "\">" + result.getString("nombreCategoriaCurso").trim() + "</option>";
            }

        } catch (Exception e) {
            OptionsCategorias = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelCategoriaCurso.Signout();
        }

        return OptionsCategorias;
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
