/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DTO.ObjArticulo;
import Model.Data.ModelArticulo;

/**
 *
 * @author lorenzo
 */
public class ControllerArticulo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    public ModelArticulo daoModelArticulo = new ModelArticulo();
    public ObjArticulo _objArticulo = new ObjArticulo();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action != null){
            
            try {
                
                String descripcionArticulo = String.valueOf(request.getParameter("txtdescripcionArticulo"));
                int cantidadDisponible = Integer.parseInt(request.getParameter("txtcantidadArticulo"));
                int precioUnitario = Integer.parseInt(request.getParameter("txtprecioUnitario"));
                
            int idCategoriaArticulo;
                if (request.getParameter("idCategoriaArticulo") != null) {
                    idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoriaArticulo"));
                    _objArticulo.setIdCategoriaArticulo(idCategoriaArticulo);
             _objArticulo.setDescripcionArticulo(descripcionArticulo);
             _objArticulo.setCantidadDisponible(cantidadDisponible);
             _objArticulo.setPrecioUnitario(precioUnitario);
             
                }

        response.sendRedirect("articulo.jsp");
                
            
            } catch (Exception e) {
                 System.out.println(e.getMessage());
            }
            
        }
       
    }
    public String getTableArticulo(){
        
        ResultSet result = null;
        String tableArticulos = "";
        
        try{
            result = daoModelArticulo.ListAll();
            
             while (result.next()){
                 tableArticulos += "<tr>";
                 tableArticulos += "<td class=\"text-center\">" + result.getString("idCategoriaArticulo").toString().trim() + "</td>";
                 tableArticulos += "<td class=\"text-center\">" + result.getString("descripcionArticulo").toString().trim() + "</td>";
                 tableArticulos += "<td class=\"text-center\">" + result.getString("cantidadDisponible").toString().trim() + "</td>";
                 tableArticulos += "<td class=\"text-center\">" + result.getString("precioUnitario").toString().trim() + "</td>";
                 tableArticulos += "<td class=\"text-center\"><a class=\"btn-sm btn-primary btn-block \"  data-toggle=\"modal\"  data-target=\"#articulo\" href=\"javascript:void(0)\"  onclick=\"consultar()\">\n" +
"                                                <span class=\"glyphicon glyphicon-search\"></span></a>\n</td>";

                tableArticulos += "</tr>";
             }
       } catch (Exception e) {
            tableArticulos = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelArticulo.Signout();
        }

        return tableArticulos;
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
