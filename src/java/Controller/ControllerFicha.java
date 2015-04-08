/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjFicha;
import Model.Data.ModelCurso;
import Model.Data.ModelFicha;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zack
 */
public class ControllerFicha extends HttpServlet {

    ObjFicha _objFicha = new ObjFicha();
    ModelFicha daoModelFicha = new ModelFicha();
    ModelCurso daoModelCurso = new ModelCurso();

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
                int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                Date fecha = Date.valueOf(request.getParameter("dateFecha"));
                int cupos = Integer.parseInt(request.getParameter("txtCupos"));
                int precio = Integer.parseInt(request.getParameter("txtPrecio"));
                _objFicha.setIdCurso(idCurso);
                _objFicha.setFechaInicio(fecha);
                _objFicha.setCuposDisponibles(cupos);
                _objFicha.setPrecioFicha(precio);
                daoModelFicha.Add(_objFicha);
                response.sendRedirect("curso.jsp");
            }
        }

    }

    public String getTableFichas() {
        ResultSet result;
        String tableFichas = "";
        try {
            result = daoModelFicha.ListAll();
            while (result.next()) {
                tableFichas += "<tr>";
                tableFichas += "<td class=\"text-center\">" + result.getString("idFicha").trim() + "</td>";
                tableFichas += "<td class=\"text-center\">" + result.getString("nombreCurso").trim() + "</td>";
                tableFichas += "<td class=\"text-center\">" + result.getString("cuposDisponibles").trim() + "</td>";
                tableFichas += "<td class=\"text-center\">" + result.getString("precioFicha").trim() + "</td>";
                tableFichas += "<td class=\"text-center\">" + result.getString("fechaInicio").trim() + "</td>";
                String [] estado = {"success", "ok"};
                if (result.getInt("estado")==0) {
                    estado[0] = "danger";
                    estado[1] = "cancel";
                }
                tableFichas += "<td class=\"text-center\"><a class=\"btn-sm btn-"+estado[0]+" btn-block \" href=\"javascript:void(0)\"  onclick=\"estado()\">\n" 
                        +"<span class=\"glyphicon glyphicon-"+estado[1]+"\"></span></a>\n" 
                        + "</td>";
                tableFichas += "<td class=\"text-center\"><a class=\"btn-sm btn-primary btn-block \"  data-toggle=\"modal\"  data-target=\"#articulo\" href=\"javascript:void(0)\"  onclick=\"consultar()\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-pencil\"></span></a>\n</td>";
                tableFichas += "</tr>";
            }
        } catch (Exception e) {
            tableFichas = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelFicha.Signout();
        }

        return tableFichas;
    }

    public String getOptionsCursos() {
        ResultSet result;
        String OptionsCursos = "";

        try {
            result = daoModelCurso.ListAll();

            while (result.next()) {
                OptionsCursos += "<option value=\"" + result.getString("idCurso").trim() + "\">" + result.getString("nombreCurso").trim() + "</option>";
            }

        } catch (Exception e) {
            OptionsCursos = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelCurso.Signout();
        }

        return OptionsCursos;
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
