
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Validaciones.Validador;

import Model.DTO.ObjCredito;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjMovimiento;
import Model.DTO.ObjUsuario;
import Model.DTO.ObjVenta;

import Model.Data.ModelCredito;
import Model.Data.ModelVenta;

import com.google.gson.Gson;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

//~--- JDK imports ------------------------------------------------------------
import java.io.IOException;
import java.io.OutputStream;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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
public class ControllerVenta extends HttpServlet {

    ObjVenta _objVenta = new ObjVenta();
    ObjUsuario _objUsuario = new ObjUsuario();
    List<ObjDetalleMovimiento> listOjbDetalleMovimientos = new ArrayList<>();
    ObjDetalleMovimiento _objDetalleMovimiento = new ObjDetalleMovimiento();
    ModelCredito daoModelCredito;
    ObjCredito _objCredito;
    ObjMovimiento _objMovimiento;
    ModelVenta daoModelVenta;

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
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
            String action = request.getParameter("action");

            switch (action) {

                case "Registrar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    String documentoUsuario = (request.getParameter("documentoUsuario"));
                    String documentoCliente;
                    String nombreCliente;
                    int numeroVenta;
                    boolean credito;
                    credito = Boolean.valueOf(request.getParameter("credito"));

                    if (Validador.validarDocumento(request.getParameter("documentoCliente"))
                            & Validador.validarNombresCompletos(request.getParameter("txtNombreCliente"))
                            & Validador.validarNumero(request.getParameter("txtNumeroVenta"))) {
                        documentoCliente = (request.getParameter("documentoCliente"));
                        nombreCliente = (request.getParameter("txtNombreCliente"));
                        numeroVenta = Integer.parseInt(request.getParameter("txtNumeroVenta"));
                    } else {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(Utilidades.mensaje(false, null, "Ha ingresado datos incorrectos"));
                        break;
                    }

                    int length = Integer.parseInt(request.getParameter("size"));
                    int totalCompra = Integer.parseInt(request.getParameter("txtTotalVenta"));
                    listOjbDetalleMovimientos = new ArrayList<>();

                    for (int i = 0; i < length; i++) {
                        _objDetalleMovimiento = new ObjDetalleMovimiento();
                        _objDetalleMovimiento.setIdArticulo(Integer.parseInt(request.getParameter("lista[" + i
                                + "][idArticulo]")));
                        _objDetalleMovimiento.setCantidad(Integer.parseInt(request.getParameter("lista[" + i
                                + "][cantidad]")));
                        _objDetalleMovimiento.setPrecioArticulo(Integer.parseInt(request.getParameter("lista[" + i
                                + "][precioArticulo]")));
                        _objDetalleMovimiento.setTotalDetalleMovimiento(_objDetalleMovimiento.getCantidad()
                                * _objDetalleMovimiento.getPrecioArticulo());
                        _objDetalleMovimiento.setDescuento(length);
                        listOjbDetalleMovimientos.add(_objDetalleMovimiento);
                    }

                    _objUsuario.setDocumentoUsuario(documentoUsuario);
                    _objVenta.setIdVenta(numeroVenta);
                    _objVenta.setDocumentoCliente(documentoCliente);
                    _objVenta.setNombreCliente(nombreCliente);
                    _objVenta.setTotalVenta(totalCompra);

                    if (credito) {
                        daoModelCredito = new ModelCredito();
                        _objCredito = new ObjCredito();
                        _objMovimiento = new ObjMovimiento();
                        ResultSet rs2;
                        try {
                            String mensaje = null;
                            String tipo = null;
                            rs2 = daoModelCredito.buscarCreditoByDocumento(_objVenta.getDocumentoCliente());
                            while (rs2.next()) {
                                if (rs2.getString("tipo").equals("success")) {
                                    _objCredito.setDocumentoUsuario(rs2.getString("documentoUsuario"));
                                    _objCredito.setSaldoInicial(rs2.getDouble("saldoInicial"));
                                    _objCredito.setSaldoActual(rs2.getDouble("saldoActual"));
                                    _objCredito.setEstadoCredito(rs2.getInt("estadoCredito"));
                                    _objCredito.setIdCredito(rs2.getInt("idCredito"));
                                    _objCredito.setFechaInicio(rs2.getString("fechaInicio"));
                                    _objMovimiento.setDocumentoUsuario(documentoUsuario);
                                    _objMovimiento.setDocumentoAuxiliar(documentoCliente);
                                    String salida = Utilidades.mensaje(daoModelCredito.update(_objCredito, _objMovimiento, listOjbDetalleMovimientos,
                                            "Venta", _objVenta.getTotalVenta()), "Se ha registrado la venta y actualizado el crédito",
                                            "Ha ocurrido un error al intentar registrar la venta y actualizar el crédito");
                                    response.getWriter().write(salida);
                                    break;
                                } else {
                                    _objCredito.setDocumentoUsuario(documentoCliente);
                                    _objCredito.setSaldoInicial(50000);
                                    _objCredito.setSaldoActual(50000 - _objVenta.getTotalVenta());
                                    _objMovimiento.setDocumentoUsuario(documentoUsuario);
                                    _objMovimiento.setDocumentoAuxiliar(documentoCliente);
                                    String salida = Utilidades.mensaje(daoModelCredito.Add(_objCredito, _objMovimiento, listOjbDetalleMovimientos, "Venta"), 
                                            "Se ha registrado la venta y se ha generado un crédito", 
                                            "Ha ocurrido un error al intentar registrar la venta y generar el crédito");
                                    response.getWriter().write(salida);
                                    break;
                                }

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ControllerMatricula.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        daoModelCredito.Signout();
                    } else {
                        daoModelVenta = new ModelVenta();

                        String salida = Utilidades.mensaje(daoModelVenta.Add(_objVenta, _objUsuario, listOjbDetalleMovimientos),
                                "La venta ha sido registrada", "Ha ocurrido un error");
                        daoModelVenta.Signout();
                        response.getWriter().write(salida);
                    }

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
                        Map<String, String> venta = (Map) material.get("Venta");
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

                        headerDerecha.add(new Chunk("Id. de la Venta: ", helveticaBold));
                        headerDerecha.add(new Chunk(venta.get("numeroVenta") + "\n"));
                        headerDerecha.add(new Chunk("Nombre del Cliente: ", helveticaBold));
                        headerDerecha.add(new Chunk(venta.get("nombreCliente") + "\n"));
                        headerDerecha.add(new Chunk("Documento del Cliente: ", helveticaBold));
                        headerDerecha.add(new Chunk(venta.get("documentoCliente") + "\n"));
                        headerDerecha.add(new Chunk("Fecha Venta: ", helveticaBold));
                        headerDerecha.add(new Chunk(venta.get("fechaVenta") + "\n"));

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
                        PdfPCell tituloCell = new PdfPCell(new Phrase("Detalle de Venta", helveticaBold));

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
                        int total = Integer.parseInt(venta.get("totalVenta"));
                        float subtotal = (total / 1.16f);
                        float iva = (subtotal - total);

                        headerIzquierda = new Paragraph();
                        headerIzquierda.add(new Chunk("Subtotal: ", helveticaBold));
                        headerIzquierda.add(new Chunk(currencyFormatter.format(subtotal)));
                        headerIzquierda.add(new Chunk("Total: ", helveticaBold));
                        headerIzquierda.add(new Chunk(currencyFormatter.format(total)));

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
                arreglo[5]
                        = "<a class=\"btn-sm btn-success btn-block\" href=\"javascript:void(0)\" onclick=\"venta.consultar("
                        + result.getString("idMovimiento") + ")\">"
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

    private String consultarDetalle(int id) {
        Map<String, Object> lista = new LinkedHashMap<>();
        List<Map> lista2 = new ArrayList<>();
        Map<String, String> resultado = null;

        daoModelVenta = new ModelVenta();

        try {
            ResultSet[] result = daoModelVenta.ConsultarVenta(id);

            while (result[0].next()) {
                resultado = new LinkedHashMap<>();
                resultado.put("idMovimiento", result[0].getString("idMovimiento"));
                resultado.put("fechaVenta", result[0].getString("fechaVenta"));
                resultado.put("totalVenta", result[0].getString("totalVenta"));
                resultado.put("documentoUsuario", result[0].getString("documentoUsuario"));
                resultado.put("numeroVenta", result[0].getString("numeroVenta"));
                resultado.put("nombreCliente", result[0].getString("nombreCliente"));
                resultado.put("documentoCliente", result[0].getString("documentoCliente"));
                lista.put("Venta", resultado);
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
            System.err.println(e);
        }

        daoModelVenta.Signout();

        String salida = new Gson().toJson(lista);

        return salida;
    }

    private String getContador() {
        daoModelVenta = new ModelVenta();

        String numero = daoModelVenta.consultarContador();

        daoModelVenta.Signout();

        Map<String, String> respuesta = new LinkedHashMap<>();

        respuesta.put("numero", numero);

        String salida = new Gson().toJson(respuesta);

        return salida;
    }

    private Map reporte(int id) {
        Map<String, Object> lista = new LinkedHashMap<>();
        List<Map> lista2 = new ArrayList<>();
        Map<String, String> resultado;

        daoModelVenta = new ModelVenta();

        try {
            ResultSet[] result = daoModelVenta.ConsultarVenta(id);

            while (result[0].next()) {
                resultado = new LinkedHashMap<>();
                resultado.put("idMovimiento", result[0].getString("idMovimiento"));
                resultado.put("fechaVenta", result[0].getString("fechaVenta"));
                resultado.put("totalVenta", result[0].getString("totalVenta"));
                resultado.put("documentoUsuario", result[0].getString("documentoUsuario"));
                resultado.put("numeroVenta", result[0].getString("numeroVenta"));
                resultado.put("nombreCliente", result[0].getString("nombreCliente"));
                resultado.put("documentoCliente", result[0].getString("documentoCliente"));
                lista.put("Venta", resultado);
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

            lista.put("Detalle", lista2);
        } catch (Exception e) {
        }

        daoModelVenta.Signout();

        return lista;
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
    }    // </editor-fold>
}
