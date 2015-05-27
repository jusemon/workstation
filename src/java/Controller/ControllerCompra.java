/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCompra;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjUsuario;
import Model.Data.ModelCompra;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siete
 */
public class ControllerCompra extends HttpServlet {

    ModelCompra daoModelCompra;
    ObjCompra _objCompra = new ObjCompra();
    ObjUsuario _objUsuario = new ObjUsuario();
    List<ObjDetalleMovimiento> listObjDetalleMovimientos = new ArrayList<>();
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
            //int estado = 0;
            String action = request.getParameter("action");
            switch (action) {
                case "Registrar": {
                    String documentoUsuario = (request.getParameter("documentoUsuario"));
                    String facturaProveedor = (request.getParameter("txtNumeroFactura"));
                    String nombreProveedor = (request.getParameter("txtNombre"));
                    int lenght = Integer.parseInt(request.getParameter("size"));
                    int totalCompra = Integer.parseInt(request.getParameter("txtTotalCompra"));
                    listObjDetalleMovimientos = new ArrayList<>();
                    for (int i = 0; i < lenght; i++) {
                        _objDetalleMovimiento = new ObjDetalleMovimiento();
                        _objDetalleMovimiento.setIdArticulo(Integer.parseInt(request.getParameter("lista[" + i + "][idArticulo]")));
                        _objDetalleMovimiento.setCantidad(Integer.parseInt(request.getParameter("lista[" + i + "][cantidad]")));
                        _objDetalleMovimiento.setPrecioArticulo(Integer.parseInt(request.getParameter("lista[" + i + "][precioArticulo]")));
                        _objDetalleMovimiento.setTotalDetalleMovimiento(_objDetalleMovimiento.getCantidad() * _objDetalleMovimiento.getPrecioArticulo());
                        _objDetalleMovimiento.setDescuento(lenght);
                        listObjDetalleMovimientos.add(_objDetalleMovimiento);
                    }
                    _objUsuario.setDocumentoUsuario(documentoUsuario);
                    _objCompra.setFacturaProveedor(facturaProveedor);
                    _objCompra.setNombreProveedor(nombreProveedor);
                    _objCompra.setTotalCompra(totalCompra);
                    daoModelCompra = new ModelCompra();
                    String salida = Mensaje(daoModelCompra.Add(_objCompra, _objUsuario, listObjDetalleMovimientos), "La compra ha sido registrada", "Ha ocurrido un error");
                    daoModelCompra.Signout();
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
                    response.getWriter().write(getTableCompra());
                    break;
                }
                case "Imprimir": {
                    response.setContentType("application/pdf");
                    try {
                        String text = "Hola Mundo";
                        int id = Integer.parseInt(request.getParameter("id"));
                        Map material = reporte(id);
                        Map compra = (Map) material.get("Compra");
                        List detalle =  (List) material.get("Detalle");
                        Document document = new Document();
                        OutputStream os = response.getOutputStream();
                        PdfWriter.getInstance(document, os);
                        document.open();
                        document.add(new Paragraph("Nombre del Proveedor: "+compra.get("nombreProveedor")));
                        document.close();
                        response.setHeader("Content-Disposition", "attachment;filename=\"reporte.pdf\"");
                        response.setContentType("application/pdf");
                        os.flush();
                        os.close();
                    } catch (DocumentException de) {
                        throw new IOException(de.getMessage());
                    }

                }

            }
        }

    }

    public String getTableCompra() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelCompra = new ModelCompra();
            result = daoModelCompra.ListAll();
            while (result.next()) {
                String[] arreglo = new String[5];
                arreglo[0] = result.getString("facturaProveedor").trim();
                arreglo[1] = result.getString("nombreProveedor").trim();
                arreglo[2] = result.getString("fechaCompra").trim();
                arreglo[3] = result.getString("totalCompra").trim();
                arreglo[4] = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"compra.consultar(" + result.getString("idMovimiento") + ")\">"
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

    private String consultarDetalle(int id) {
        Map<String, Object> lista = new LinkedHashMap<>();
        List<Map> lista2 = new ArrayList<>();
        Map<String, String> resultado = null;
        daoModelCompra = new ModelCompra();
        try {
            ResultSet[] result = daoModelCompra.ConsultarCompra(id);
            while (result[0].next()) {
                resultado = new LinkedHashMap<>();
                resultado.put("idMovimiento", result[0].getString("idMovimiento"));
                resultado.put("fechaCompra", result[0].getString("fechaCompra"));
                resultado.put("totalCompra", result[0].getString("totalCompra"));
                resultado.put("documentoUsuario", result[0].getString("documentoUsuario"));
                resultado.put("facturaProveedor", result[0].getString("facturaProveedor"));
                resultado.put("nombreProveedor", result[0].getString("nombreProveedor"));
                lista.put("Compra", resultado);
            }
            while (result[1].next()) {
                resultado = new LinkedHashMap<>();
                resultado.put("idDetalleMovimiento", result[1].getString("idDetalleMovimiento"));
                resultado.put("idArticulo", result[1].getString("idArticulo"));
                resultado.put("descripcionArticulo", result[1].getString("descripcionArticulo"));
                resultado.put("cantidad", result[1].getString("cantidad"));
                resultado.put("descuento", result[1].getString("descuento"));
                resultado.put("totalDetalleMovimiento", result[1].getString("totalDetalleMovimiento"));
                resultado.put("idMovimiento", result[1].getString("idMovimiento"));
                resultado.put("precioArticulo", result[1].getString("precioArticulo"));
                lista2.add(resultado);
            }
            //listas.put("Compra",lista);
            lista.put("Detalle", lista2);
        } catch (Exception e) {
        }
        daoModelCompra.Signout();
        String salida = new Gson().toJson(lista);
        return salida;
    }
    private Map reporte(int id) {
        Map<String, Object> lista = new LinkedHashMap<>();
        List<Map> lista2 = new ArrayList<>();
        Map<String, String> resultado = null;
        daoModelCompra = new ModelCompra();
        try {
            ResultSet[] result = daoModelCompra.ConsultarCompra(id);
            while (result[0].next()) {
                resultado = new LinkedHashMap<>();
                resultado.put("idMovimiento", result[0].getString("idMovimiento"));
                resultado.put("fechaCompra", result[0].getString("fechaCompra"));
                resultado.put("totalCompra", result[0].getString("totalCompra"));
                resultado.put("documentoUsuario", result[0].getString("documentoUsuario"));
                resultado.put("facturaProveedor", result[0].getString("facturaProveedor"));
                resultado.put("nombreProveedor", result[0].getString("nombreProveedor"));
                lista.put("Compra", resultado);
            }
            while (result[1].next()) {
                resultado = new LinkedHashMap<>();
                resultado.put("idDetalleMovimiento", result[1].getString("idDetalleMovimiento"));
                resultado.put("idArticulo", result[1].getString("idArticulo"));
                resultado.put("descripcionArticulo", result[1].getString("descripcionArticulo"));
                resultado.put("cantidad", result[1].getString("cantidad"));
                resultado.put("descuento", result[1].getString("descuento"));
                resultado.put("totalDetalleMovimiento", result[1].getString("totalDetalleMovimiento"));
                resultado.put("idMovimiento", result[1].getString("idMovimiento"));
                resultado.put("precioArticulo", result[1].getString("precioArticulo"));
                lista2.add(resultado);
            }
            //listas.put("Compra",lista);
            lista.put("Detalle", lista2);
        } catch (Exception e) {
        }
        daoModelCompra.Signout();
        return lista;
    }
    

}
