/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.*;
import Model.Data.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public class ControllerCliente extends HttpServlet {

    public ModelEstudiante daoModelCliente = new ModelEstudiante();
    public ObjEstudiante _objCliente = new ObjEstudiante();
    public ModelAcudiente daoModelAcudiente = new ModelAcudiente();
    public ObjAcudiente _objAcudiente = new ObjAcudiente();

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
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "Registrar": {
                    break;
                }
            }
        }
    }

    public String getTableClientes() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            result = daoModelCliente.ListAll();
            String[] arreglo;
            while (result.next()) {
                arreglo = new String[7];
                arreglo[0] = result.getString("tipoDocumento").trim();
                arreglo[1] = result.getString("numeroDocumento").trim();
                arreglo[2] = result.getString("nombreCliente").trim();
                arreglo[3] = result.getString("fechaNacimiento").trim();
                arreglo[5] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"cliente.myAjax('Consultar'," + result.getString("tipoDocumento") + ", " + result.getInt("numeroDocumento") + " )\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\"  onclick=\"cliente.myAjax('Editar'," + result.getString("tipoDocumento") + ", " + result.getInt("numeroDocumento") + " )\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-search\"></span></a>";                
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error" + e.getMessage());
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
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
