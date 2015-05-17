/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjVenta;
import Model.Data.ModelVenta;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lorenzo
 */
public class ControllerVenta extends HttpServlet {

    ModelVenta daoModelVenta;
    ObjVenta _objVenta = new ObjVenta();

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
                
            String action = request.getParameter("action");
            switch (request.getParameter("action")) {
                case "Registrar": {
                    String fechaVenta = (request.getParameter("txtFacturaVenta"));
                int totalVenta = Integer.parseInt(request.getParameter("txtTotalVenta"));
                String cedulaCliente = (request.getParameter("txtCedulaCliente"));
                String nombreCliente = (request.getParameter("txtNombreCliente"));
                _objVenta.setFechaVenta(fechaVenta);
                _objVenta.setTotalVenta(totalVenta);
                _objVenta.setCedulaCliente(cedulaCliente);
                _objVenta.setNombreCliente(nombreCliente);
                break;
            }
            case "Editar": {
                String fechaVenta = (request.getParameter("txtFacturaVenta"));
                int totalVenta = Integer.parseInt(request.getParameter("txtTotalVenta"));
                String cedulaCliente = (request.getParameter("txtCedulaCliente"));
                String nombreCliente = (request.getParameter("txtNombreCliente"));
                _objVenta.setFechaVenta(fechaVenta);
                _objVenta.setTotalVenta(totalVenta);
                _objVenta.setCedulaCliente(cedulaCliente);
                _objVenta.setNombreCliente(nombreCliente);
                break;
            }
            case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableVenta());
                    break;
                }    
            }
        }
    }
    public String getTableVenta(){
        ResultSet result;
        List<String[]> lista = new ArrayList<>() ;
        try{
          int contador = 0;
          daoModelVenta = new ModelVenta();
          result = daoModelVenta.ListAll();
          while (result.next()){
              String[] arreglo = new String[5];
              arreglo[0] = result.getString("facturaVenta").trim();
              arreglo[1] = result.getString("totalVenta").trim();
              arreglo[2] = result.getString("cedulaCliente").trim();
              arreglo[3] = result.getString("nombreCliente").trim();
              arreglo[4] = "<a class=\"btn-sm btn-primary btn-block\" href=\"javascript:void(0)\" onclick=\"compra.editar(" + contador + ")\">"
                        + "<span class=\"glyphicon glyphicon-edit\"></span></a>";
                lista.add(arreglo);
                contador++;
            }
        }catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller venta " + e.toString());
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
        
   
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
