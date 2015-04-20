/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjSeminario;
import Model.Data.ModelSeminario;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zack
 */
public class ControllerSeminario extends HttpServlet {

    ObjSeminario _objSeminario = new ObjSeminario();
    ModelSeminario daoModelSeminario;

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
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "Registrar": {
                    daoModelSeminario = new ModelSeminario();
                    String nombreSeminario = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8").trim();
                    int duracionSeminario = Integer.parseInt(request.getParameter("txtDuracion").trim());
                    int estado = Integer.parseInt(request.getParameter("ddlEstado").trim());
                    _objSeminario.setNombreSeminario(nombreSeminario);
                    _objSeminario.setDuracionSeminario(duracionSeminario);
                    _objSeminario.setEstadoSeminario(estado);
                    daoModelSeminario.Add(_objSeminario);
                    break;
                }
                case "Editar": {
                    daoModelSeminario = new ModelSeminario();
                    int idSeminario = Integer.parseInt(request.getParameter("idSeminario"));
                    String nombreSeminario = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    int duracionSeminario = Integer.parseInt(request.getParameter("txtDuracion"));
                    int estado = Integer.parseInt(request.getParameter("ddlEstado"));
                    _objSeminario.setIdSeminario(idSeminario);
                    _objSeminario.setNombreSeminario(nombreSeminario);
                    _objSeminario.setDuracionSeminario(duracionSeminario);
                    _objSeminario.setEstadoSeminario(estado);
                    daoModelSeminario.Edit(_objSeminario);
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(getTableSeminario());
                    break;
                }
            }
        }
    }

    public String getTableSeminario() {
        ResultSet result;
        String[] arreglo;
        List<String[]> lista = new ArrayList<>();
        daoModelSeminario = new ModelSeminario();
        try {
            result = daoModelSeminario.listAll();
            while (result.next()) {
                arreglo = new String[5];
                arreglo[0] = result.getString("idSeminario").trim();
                arreglo[1] = result.getString("nombreSeminario").trim();
                arreglo[2] = result.getString("duracionSeminario").trim();
                String[] estado = {"success", "ok"};
                if (result.getInt("estadoSeminario") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                arreglo[3] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block \" href=\"javascript:void(0)\"  onclick=\"estado()\">\n"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                arreglo[4] = "<a  class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"editar()\">\n"
                        + "<span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error" + e.getMessage());
        } finally {
            daoModelSeminario.Signout();
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
    }// </editor-fold>

}
