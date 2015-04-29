/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DTO.ObjArticulo;
import Model.Data.ModelArticulo;
import Model.Data.ModelCategoriaArticulo;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lorenzo
 */
public class ControllerArticulo extends HttpServlet {
    
    public ModelArticulo daoModelArticulo = new ModelArticulo();
    public ObjArticulo _objArticulo = new ObjArticulo();
    public ModelCategoriaArticulo daoModelCategoriaArticulo = new ModelCategoriaArticulo();

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
            String action = new String(request.getParameter("action").getBytes("ISO-8859-1"), "UTF-8");
            try {
                switch (action) {
                    case "Registrar": {
                        String descripcionArticulo = new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8");
                        int cantidadDisponible = Integer.parseInt(request.getParameter("txtCantidad"));
                        int precioUnitario = Integer.parseInt(request.getParameter("txtPrecio"));
                        int idCategoriaArticulo;
                        if (request.getParameter("idCategoria") != null) {
                            idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoria"));
                            _objArticulo.setIdCategoriaArticulo(idCategoriaArticulo);
                            _objArticulo.setDescripcionArticulo(descripcionArticulo);
                            _objArticulo.setCantidadDisponible(cantidadDisponible);
                            _objArticulo.setPrecioUnitario(precioUnitario);
                            String salida = Mensaje(daoModelArticulo.Add(_objArticulo), "Articulo registrado con exito", "Ha ocurrido un error al intentar registrar el articulo");
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(salida);
                        }
                        break;
                    }
                    case "Editar": {
                        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
                        String descripcionArticulo = new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8");
                        int cantidadDisponible = Integer.parseInt(request.getParameter("txtCantidad"));
                        int precioUnitario = Integer.parseInt(request.getParameter("txtPrecio"));
                        int idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoria"));
                        _objArticulo.setIdArticulo(idArticulo);
                        _objArticulo.setIdCategoriaArticulo(idCategoriaArticulo);
                        _objArticulo.setDescripcionArticulo(descripcionArticulo);
                        _objArticulo.setCantidadDisponible(cantidadDisponible);
                        _objArticulo.setPrecioUnitario(precioUnitario);
                        String salida = Mensaje(daoModelArticulo.Edit(_objArticulo), "Articulo actualizado con exito", "Ha ocurrido un error al intentar actualizar el articulo");
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);
                        
                        break;
                    }
                    case "Consultar": {
                        String nombreBusqueda = new String(request.getParameter("nombreBusqueda").getBytes("ISO-8859-1"), "UTF-8");
                        HttpSession session = request.getSession();
                        session.setAttribute("isConsulta", true);
                        session.setAttribute("resultado", nombreBusqueda);
                        break;
                    }
                    case "Enlistar": {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(getTableArticulo());
                        break;
                    }
                }
            } catch (NumberFormatException ne) {
                System.err.println(ne.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
        }
        
    }
    
    public String getTableArticulo() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        int contador = 0;
        try {
            result = daoModelArticulo.ListAll();
            while (result.next()) {
                String[] arreglo = new String[6];
                arreglo[0] = result.getString("idArticulo").trim();
                arreglo[1] = result.getString("nombreCategoriaArticulo").trim();
                arreglo[2] = result.getString("descripcionArticulo").trim();
                arreglo[3] = result.getString("cantidadDisponible").trim();
                arreglo[4] = result.getString("precioUnitario").trim();
                arreglo[5] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\"  onclick=\"articulo.editar(" + contador + ")\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
                contador++;
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
