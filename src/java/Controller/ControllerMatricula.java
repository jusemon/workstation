/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjMatricula;
import Model.Data.ModelEmpresa;
import Model.Data.ModelMatricula;
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
 * @author Administrador
 */
public class ControllerMatricula extends HttpServlet {

    ModelMatricula daoModelMatricula = new ModelMatricula();
    ObjMatricula _objMatricula = new ObjMatricula();

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
        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
                case "Consultar": {
                    break;
                }
                case "Estado": {
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableMatriculas());
                    break;
                }
                case "Seleccion": {

                    break;
                }
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

    private String getTableMatriculas() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        int contador = 0;
        String[] arreglo;
        try {
             daoModelMatricula = new ModelMatricula();
             result = daoModelMatricula.ListAll();
            while (result.next()) {
                arreglo = new String[7];
                arreglo[0] = result.getString("nitEmpresa").trim();
                arreglo[1] = result.getString("nombreEmpresa").trim();
                arreglo[2] = result.getString("direccionEmpresa").trim();
                arreglo[3] = result.getString("nombreContacto").trim();
                arreglo[4] = result.getString("telefonoContacto").trim();
                arreglo[5] = result.getString("emailContacto").trim();
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"empresa.editar(" + contador + ")\">"
                        + "<span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
                contador++;
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error." + e.getMessage());
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

}
