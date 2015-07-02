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
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lorenzo
 */
public class ControllerArticulo extends HttpServlet {

    public ModelArticulo daoModelArticulo;
    public ObjArticulo _objArticulo = new ObjArticulo();   

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
            try {
                switch (action) {
                    //<editor-fold defaultstate="collapsed" desc="Registrar un Articulo">
                    case "Registrar": {
                        String descripcionArticulo = request.getParameter("txtDescripcion");
                        int precioCompra = Integer.parseInt(request.getParameter("txtPrecioCompra"));
                        int precioVenta = Integer.parseInt(request.getParameter("txtPrecioVenta"));
                        int idCategoriaArticulo;
                        idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoria"));
                        _objArticulo.setIdCategoriaArticulo(idCategoriaArticulo);
                        _objArticulo.setDescripcionArticulo(descripcionArticulo);
                        _objArticulo.setPrecioCompra(precioCompra);
                        _objArticulo.setPrecioVenta(precioVenta);
                        daoModelArticulo = new ModelArticulo();
                        String salida = Mensaje(daoModelArticulo.Add(_objArticulo), "Artículo registrado correctamente", "Ha ocurrido un error al intentar registrar el artículo");
                        daoModelArticulo.Signout();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);

                        break;
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="Editar un Articulo">
                    case "Editar": {
                        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
                        String descripcionArticulo = request.getParameter("txtDescripcion");
                        int precioVenta = Integer.parseInt(request.getParameter("txtPrecioVenta"));
                        int idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoria"));
                        _objArticulo.setIdArticulo(idArticulo);
                        _objArticulo.setIdCategoriaArticulo(idCategoriaArticulo);
                        _objArticulo.setDescripcionArticulo(descripcionArticulo);
                        _objArticulo.setPrecioVenta(precioVenta);
                        daoModelArticulo = new ModelArticulo();
                        String salida = Mensaje(daoModelArticulo.Edit(_objArticulo), "Artículo actualizado sorrectamente", "Ha ocurrido un error al intentar actualizar el artículo");
                        daoModelArticulo.Signout();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);
                        break;
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="Consultar un Articulo por ID">
                    case "Consultar": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(consultarArticulo(id));
                        break;
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="Consultar Codigo">
                    case "Contador": {
                        daoModelArticulo = new ModelArticulo();
                        String resultado = daoModelArticulo.consultarContador();
                        daoModelArticulo.Signout();
                        Map<String, String> salida = new LinkedHashMap<>();
                        salida.put("idArticulo", resultado);
                        String respuesta = new Gson().toJson(salida);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(respuesta);
                        break;
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="Obtener las opciones de Articulos">
                    case "getOptionsArticulos": {
                        String tipo = request.getParameter("tipo");
                        String resultado = getOptionsArticulos(tipo);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(resultado);
                        break;
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="Enlistar todos los Articulos">
                    case "Enlistar": {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(getTableArticulo());
                        break;
                    }
                    //</editor-fold>
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
            daoModelArticulo = new ModelArticulo();
            result = daoModelArticulo.ListAll();
            while (result.next()) {
                String[] arreglo = new String[7];
                arreglo[0] = result.getString("idArticulo").trim();
                arreglo[1] = result.getString("nombreCategoriaArticulo").trim();
                arreglo[2] = result.getString("descripcionArticulo").trim();
                arreglo[3] = result.getString("cantidadDisponible").trim();
                arreglo[4] = result.getString("precioCompra").trim();
                arreglo[5] = result.getString("precioVenta").trim();
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \"  href=\"javascript:void(0)\"  onclick=\"articulo.editar(" + contador + ")\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
                contador++;
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error" + e.getMessage());
        } finally {
            daoModelArticulo.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    private String getOptionsArticulos(String tipo) {
        ResultSet result;
        List<Map> salida = new ArrayList<>();
        Map<String, String> articulo;
        try {
            daoModelArticulo = new ModelArticulo();
            if (tipo != null) {
                if (tipo.equals("Venta")) {
                    result = daoModelArticulo.ListOnlyExistencias();
                } else {
                    result = daoModelArticulo.ListAll();
                }
            } else {
                result = daoModelArticulo.ListAll();
            }
            while (result.next()) {
                articulo = new LinkedHashMap<>();
                articulo.put("id", result.getString("idArticulo"));
                articulo.put("text", result.getString("descripcionArticulo"));
                salida.add(articulo);
            }
        } catch (Exception ex) {
            articulo = new LinkedHashMap<>();
            articulo.put("tipo", "error");
            articulo.put("mensaje", "Ha ocurrido un error: " + ex);
        } finally {
            daoModelArticulo.Signout();
        }
        String respuesta = new Gson().toJson(salida);
        return respuesta;
    }

    private String consultarArticulo(int id) {
        ResultSet result = null;
        Map<String, String> salida = new LinkedHashMap<>();
        try {
            daoModelArticulo = new ModelArticulo();
            result = daoModelArticulo.consultarPorID(id);
            while (result.next()) {
                salida.put("idArticulo", result.getString("idArticulo"));
                salida.put("descripcionArticulo", result.getString("descripcionArticulo"));
                salida.put("cantidadDisponible", result.getString("cantidadDisponible"));
                salida.put("precioVenta", result.getString("precioVenta"));
                salida.put("precioCompra", result.getString("precioCompra"));
            }
        } catch (Exception e) {
            salida.put("tipo", "error");
            salida.put("mensaje", "Ha ocurrido un error: " + e);
        }
        String respuesta = new Gson().toJson(salida);
        return respuesta;
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
