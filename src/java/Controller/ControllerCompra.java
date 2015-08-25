
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//~--- non-JDK imports --------------------------------------------------------
import Model.DTO.ObjCompra;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjUsuario;

import Model.Data.ModelCompra;

import com.google.gson.Gson;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

//~--- JDK imports ------------------------------------------------------------
import java.io.IOException;
import java.io.OutputStream;

import java.sql.ResultSet;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siete
 */
public class ControllerCompra extends HttpServlet {

    ObjCompra _objCompra = new ObjCompra();
    ObjUsuario _objUsuario = new ObjUsuario();
    List<ObjDetalleMovimiento> listObjDetalleMovimientos = new ArrayList<>();
    ObjDetalleMovimiento _objDetalleMovimiento = new ObjDetalleMovimiento();
    ModelCompra daoModelCompra;

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

            // int estado = 0;
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
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
                        _objDetalleMovimiento.setIdArticulo(Integer.parseInt(request.getParameter("lista[" + i
                                + "][idArticulo]")));
                        _objDetalleMovimiento.setCantidad(Integer.parseInt(request.getParameter("lista[" + i
                                + "][cantidad]")));
                        _objDetalleMovimiento.setPrecioArticulo(Integer.parseInt(request.getParameter("lista[" + i
                                + "][precioArticulo]")));
                        _objDetalleMovimiento.setTotalDetalleMovimiento(_objDetalleMovimiento.getCantidad()
                                * _objDetalleMovimiento.getPrecioArticulo());
                        _objDetalleMovimiento.setDescuento(lenght);
                        listObjDetalleMovimientos.add(_objDetalleMovimiento);
                    }

                    _objUsuario.setDocumentoUsuario(documentoUsuario);
                    _objCompra.setFacturaProveedor(facturaProveedor);
                    _objCompra.setNombreProveedor(nombreProveedor);
                    _objCompra.setTotalCompra(totalCompra);
                    daoModelCompra = new ModelCompra();

                    String salida = Mensaje(daoModelCompra.Add(_objCompra, _objUsuario, listObjDetalleMovimientos),
                            "La compra ha sido registrada", "Ha ocurrido un error");

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

                // <editor-fold defaultstate="collapsed" desc="PDF mediante iText">
                case "Imprimir": {
                    response.setContentType("application/pdf");

                    try {
                        Locale loc = Locale.getDefault();
                        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(loc);

                        // Primero obtengo el id del Movimiento
                        int id = Integer.parseInt(request.getParameter("id"));

                        // Obtengo el reporte a manera de Map
                        Map material = reporte(id);

                        // Topo ese reporte y lo divido, primero en la compra y luego el detalle
                        Map<String, String> compra = (Map) material.get("Compra");
                        List<Map> detalle = (List) material.get("Detalle");

                        // Creo el documento y obtengo el canal de comunicacion con el servidor, para luego enviar el documento.
                        Document document = new Document();
                        OutputStream os = response.getOutputStream();

                        // Creo una instancia a partir del documento y del canal
                        PdfWriter.getInstance(document, os);

                        // Abro el documento
                        document.open();

                        Image logo = Image.getInstance(url + "/public/images/logo.png");

                        logo.scaleAbsolute(new Rectangle(logo.getPlainWidth() / 4, logo.getPlainHeight() / 4));
                        document.add(logo);

                        // Creo una fuente para la letra en negrilla
                        final Font helveticaBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

                        // Escribo y agrego un primer parrafo con los datos basicos de la compra
                        Paragraph headerDerecha = new Paragraph();

                        headerDerecha.add(new Chunk("Nombre del Proveedor: ", helveticaBold));
                        headerDerecha.add(new Chunk(compra.get("nombreProveedor") + "\n"));
                        headerDerecha.add(new Chunk("Factura del Proveedor: ", helveticaBold));
                        headerDerecha.add(new Chunk(compra.get("facturaProveedor") + "\n"));
                        headerDerecha.add(new Chunk("Fecha Compra: ", helveticaBold));
                        headerDerecha.add(new Chunk(compra.get("fechaCompra") + "\n"));

                        // Escribo y agrego un segundo parrafo con los datos basicos de Stelarte
                        Paragraph headerIzquierda = new Paragraph();

                        headerIzquierda.add(new Chunk("Stelarte.Decoracion \n", helveticaBold));
                        headerIzquierda.add(new Chunk("Dirección: ", helveticaBold));
                        headerIzquierda.add(new Chunk("Calle Falsa 123 # 12a34\n"));
                        headerIzquierda.add(new Chunk("Teléfono: ", helveticaBold));
                        headerIzquierda.add(new Chunk("2583697 \n"));

                        // Agrego los dos anteriores parrafos al Header
                        PdfPTable header = new PdfPTable(2);

                        header.getDefaultCell().setBorder(0);
                        header.addCell(headerIzquierda);
                        header.addCell(headerDerecha);
                        header.setWidthPercentage(100f);
                        header.setSpacingAfter(20);
                        document.add(header);

                        // Creo la tabla del detalle
                        PdfPTable tablaDetalle = new PdfPTable(new float[]{1, 3, 2, 2});

                        tablaDetalle.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                        // Creo el titulo, le quito el borde, le digo que ocupara cuatro columnas y que serà centrado
                        PdfPCell tituloCell = new PdfPCell(new Phrase("Detalle de Compra", helveticaBold));

                        tituloCell.setBorder(0);
                        tituloCell.setColspan(4);
                        tituloCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tablaDetalle.addCell(tituloCell);

                        // Aqui creo cada cabecera
                        tablaDetalle.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                        tablaDetalle.addCell(new Phrase("ID", helveticaBold));
                        tablaDetalle.addCell(new Phrase("Nombre", helveticaBold));
                        tablaDetalle.addCell(new Phrase("Cantidad", helveticaBold));
                        tablaDetalle.addCell(new Phrase("Valor", helveticaBold));
                        tablaDetalle.getDefaultCell().setBackgroundColor(null);

                        // Aqui agrego la tabla cada articulo.
                        for (Map<String, String> next : detalle) {
                            tablaDetalle.addCell(next.get("idArticulo"));
                            tablaDetalle.addCell(next.get("descripcionArticulo"));
                            tablaDetalle.addCell(next.get("cantidad"));
                            tablaDetalle.addCell(currencyFormatter.format(Integer.parseInt(next.get("precioArticulo"))));
                        }

                        // Creo el Footer
                        headerIzquierda = new Paragraph();
                        headerIzquierda.add(new Chunk("Total: ", helveticaBold));
                        headerIzquierda.add(
                                new Chunk(currencyFormatter.format(Integer.parseInt(compra.get("totalCompra")))));

                        PdfPCell footerCell = new PdfPCell(headerIzquierda);

                        footerCell.setBorder(0);
                        footerCell.setColspan(4);
                        footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        tablaDetalle.addCell(footerCell);

                        // Establesco el tamaño  y posicion de la tabla, luego la agrego al documento
                        tablaDetalle.setWidthPercentage(100f);
                        tablaDetalle.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        document.add(tablaDetalle);

                        // Cierro el documento y lo envio con flush.
                        document.close();
                        response.setHeader("Content-Disposition", "attachment;filename=\"reporte.pdf\"");
                        os.flush();
                        os.close();
                    } catch (DocumentException de) {
                        throw new IOException(de.getMessage());
                    }

                    break;
                }

                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="PDF mediante iReports">
                case "Imprimir2": {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        String source = url + "/reports/newReport1.jrxml";
                        JasperPrint jasperPrint = null;
                        JasperReport jasperReport = null;
                        JasperDesign jasperDesign = null;

                        System.out.println(source);

                        String reportPath = request.getServletContext().getRealPath("reports") + "\\newReport1.jrxml";

                        jasperDesign = JRXmlLoader.load(reportPath);
                        jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        jasperPrint = JasperFillManager.fillReport(jasperReport, reporte(id),
                                daoModelCompra.getConnection());
                        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                    } catch (Exception ex) {
                        for (StackTraceElement ruta : ex.getStackTrace()) {
                            System.err.println(ruta);
                        }
                    }
                }

                break;

                // </editor-fold>
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
                arreglo[4]
                        = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"compra.consultar("
                        + result.getString("idMovimiento") + ")\">"
                        + "<span class=\"glyphicon glyphicon-search\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller compra " + e.toString());
        } finally {
            daoModelCompra.Signout();
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

//  <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    }    // </editor-fold>

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

            // listas.put("Compra",lista);
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

            // listas.put("Compra",lista);
            lista.put("Detalle", lista2);
        } catch (Exception e) {
        }

        daoModelCompra.Signout();

        return lista;
    }
}
