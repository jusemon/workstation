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
import Model.JDBC.ConnectionDB;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import javax.servlet.ServletException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
                    String nombreUsuario = (request.getParameter("txtNombre"));
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
                    _objVenta.setDocumentoCliente(documentoUsuario);
                    _objVenta.setNombreCliente(nombreUsuario);
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
            }
        }


    public String getTableVenta() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelVenta = new ModelVenta();
            result = daoModelVenta.ListAll();
            while (result.next()) {
                String[] arreglo = new String[5];
                arreglo[0] = result.getString("documentoCliente").trim();
                arreglo[1] = result.getString("nombreCliente").trim();
                arreglo[2] = result.getString("fechaVenta").trim();
                arreglo[3] = result.getString("totalVenta").trim();
                arreglo[4] = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"venta.consultar(" + result.getString("idMovimiento") + ")\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller compra " + e.toString());
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
