/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjVenta;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjUsuario;
import Model.Data.ModelVenta;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 *
 * @author siete
 */
public class ControllerVenta extends HttpServlet {

    ModelVenta daoModelVenta;
    ObjVenta _objVenta = new ObjVenta();
    ObjUsuario _objUsuario = new ObjUsuario();
    List<ObjDetalleMovimiento> listOjbDetalleMovimientos = new ArrayList<>();
    ObjDetalleMovimiento _objDetalleMovimiento = new ObjDetalleMovimiento();

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
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String action = request.getParameter("action");
            switch (action) {
                case "Registrar": {
                    String documentoUsuario = (request.getParameter("documentoUsuario"));
                    String documentoCliente = (request.getParameter("documentoCliente"));
                    String nombreCliente = (request.getParameter("txtNombreCliente"));
                    int idVenta = Integer.parseInt(request.getParameter("txtNumeroVenta"));
                    int lenght = Integer.parseInt(request.getParameter("size"));
                    int totalCompra = Integer.parseInt(request.getParameter("txtTotalVenta"));
                    listOjbDetalleMovimientos = new ArrayList<>();
                    for (int i = 0; i < lenght; i++) {
                        _objDetalleMovimiento = new ObjDetalleMovimiento();
                        _objDetalleMovimiento.setIdArticulo(Integer.parseInt(request.getParameter("lista[" + i + "][idArticulo]")));
                        _objDetalleMovimiento.setCantidad(Integer.parseInt(request.getParameter("lista[" + i + "][cantidad]")));
                        _objDetalleMovimiento.setPrecioArticulo(Integer.parseInt(request.getParameter("lista[" + i + "][precioArticulo]")));
                        _objDetalleMovimiento.setTotalDetalleMovimiento(_objDetalleMovimiento.getCantidad() * _objDetalleMovimiento.getPrecioArticulo());
                        _objDetalleMovimiento.setDescuento(lenght);
                        listOjbDetalleMovimientos.add(_objDetalleMovimiento);
                    }
                    _objUsuario.setDocumentoUsuario(documentoUsuario);
                    _objVenta.setIdVenta(idVenta);
                    _objVenta.setDocumentoCliente(documentoCliente);
                    _objVenta.setNombreCliente(nombreCliente);
                    _objVenta.setTotalVenta(totalCompra);
                    daoModelVenta = new ModelVenta();
                    String salida = Mensaje(daoModelVenta.Add(_objVenta, _objUsuario, listOjbDetalleMovimientos), "La venta ha sido registrada", "Ha ocurrido un error");
                    daoModelVenta.Signout();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                case "Consultar": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(consultarDetalle(id));
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableVenta());
                    break;
                }
                case "Contador": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getContador());
                    break;
                }
            }
        }
    }

    public String getTableVenta() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelVenta = new ModelVenta();
            result = daoModelVenta.ListAll();
            while (result.next()) {
                String[] arreglo = new String[6];
                arreglo[0] = result.getString("numeroVenta").trim();
                arreglo[2] = result.getString("documentoCliente").trim();
                arreglo[3] = result.getString("nombreCliente").trim();
                arreglo[1] = result.getString("fechaVenta").trim();
                arreglo[4] = result.getString("totalVenta").trim();
                arreglo[5] = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"venta.consultar(" + result.getString("idMovimiento") + ")\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                lista.add(arreglo);
            }
            daoModelVenta.Signout();
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller venta " + e.toString());
            for (StackTraceElement strings : e.getStackTrace()) {
                System.out.println(strings);
            }
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

    private int consultarDetalle(int id) {
        return -1;
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

    private String getContador() {
        daoModelVenta = new ModelVenta();
        String numero = daoModelVenta.consultarContador();
        daoModelVenta.Signout();
        Map<String, String> respuesta = new LinkedHashMap<>();
        respuesta.put("numero", numero);
        String salida = new Gson().toJson(respuesta);
        return salida;
    }

}
