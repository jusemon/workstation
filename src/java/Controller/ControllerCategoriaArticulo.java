/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCategoriaArticulo;
import Model.Data.ModelCategoriaArticulo;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lorenzo
 */
public class ControllerCategoriaArticulo extends HttpServlet {

    public ModelCategoriaArticulo daoModelCategoriaArticulo;
    public ObjCategoriaArticulo _objCategoriaArticulo = new ObjCategoriaArticulo();

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
                    daoModelCategoriaArticulo = new ModelCategoriaArticulo();
                    String nombreCategoriaArticulo = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    _objCategoriaArticulo.setNombreCategoriaArticulo(nombreCategoriaArticulo);
                    daoModelCategoriaArticulo.Add(_objCategoriaArticulo);
                    response.sendRedirect("articulo.jsp");
                    break;
                }
                case "Editar": {
                    daoModelCategoriaArticulo = new ModelCategoriaArticulo();
                    int idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoriaArticulo"));
                    String nombreCategoriaArticulo = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    _objCategoriaArticulo.setIdCategoriaArticulo(idCategoriaArticulo);
                    _objCategoriaArticulo.setNombreCategoriaArticulo(nombreCategoriaArticulo);
                    daoModelCategoriaArticulo.Edit(_objCategoriaArticulo);
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/text");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableCategoriaArticulo());
                    break;
                }
            }
            response.sendRedirect("articulo.jsp");
        }
    }

    public String getTableCategoriaArticulo() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        daoModelCategoriaArticulo = new ModelCategoriaArticulo();
        try {
            result = daoModelCategoriaArticulo.ListAll();

            while (result.next()) {
                String[]arreglo = new String[3];
                arreglo[0] = result.getString("idCategoriaArticulo").trim();
                arreglo[1] = result.getString("nombreCategoriaArticulo").trim();
                arreglo[2] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"editar()\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error" + e.getMessage());
        } finally {
            daoModelCategoriaArticulo.Signout();
        }
        String salida = new Gson().toJson(lista);
        return salida;
    }

    public String getOptionsCategorias() {
        ResultSet result;
        String OptionsCategorias = "";
        daoModelCategoriaArticulo = new ModelCategoriaArticulo();
        try {
            result = daoModelCategoriaArticulo.ListAll();

            while (result.next()) {
                OptionsCategorias += "<option value=\"" + result.getString("idCategoriaArticulo").trim() + "\">" + result.getString("nombreCategoriaArticulo").trim() + "</option>";
            }

        } catch (Exception e) {
            OptionsCategorias = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelCategoriaArticulo.Signout();
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
