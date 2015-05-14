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
import Model.DTO.ObjCredito;
import Model.Data.ModelCredito;

import com.google.gson.Gson;
import com.sun.xml.internal.fastinfoset.EncodingConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */

public class ControllerCredito extends HttpServlet {
    
    ModelCredito daoModelCredito;
    ObjCredito _ObjCredito = new ObjCredito();
    Map<String, String> respuesta;
    ResultSet result;
    

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
        
        if (request.getParameter("action")!= null){
            
            String nombre, descripcion, aux, salida, tipo = "";
            int estado = 0, cantidadClases, categoria, id, horasPorClase, precio;
            
            switch (request.getParameter("action")){
                
                // <editor-fold defaultstate="collapsed" desc="Registrar un crédito">
                case "Registrar": {
                                               
                    daoModelCredito = new ModelCredito();
                    
                    int idCategoriaCredito = Integer.parseInt(request.getParameter("ddlCategoriaCredito").trim());
                    _ObjCredito.setIdCategoriaCredito(idCategoriaCredito);
                    String documentoUsuario = request.getParameter("txtDocumentoUsuario").trim();
                    _ObjCredito.setDocumentoUsuario(documentoUsuario);
                    String fechaInicio = request.getParameter("txtFechaInicio").trim();
                    _ObjCredito.setFechaInicio(fechaInicio);
                    double saldoInicial = Double.parseDouble(request.getParameter("txtSaldoInicial").trim());
                    _ObjCredito.setSaldoInicial(saldoInicial);
                    double saldoActual = Double.parseDouble(request.getParameter("txtSaldoActual").trim());
                    _ObjCredito.setSaldoActual(saldoActual);
                    int estadoCredito = Integer.parseInt(request.getParameter("ddlEstadoCredito").trim());
                    _ObjCredito.setEstadoCredito(estadoCredito);
                    
                    salida = Mensaje(daoModelCredito.Add(_ObjCredito), "El crédito ha sido registrado", "Ha ocurrido un error al intentar registrar el crédito");
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                

                // <editor-fold defaultstate="collapsed" desc="Listar las Empresas">
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableCredito());
                    break;
                }
                // </editor-fold>
            }        
        }
    }
    
    public String cambiarEstado(int idCredito, int idCategoriaCredito) {
        ResultSet result;
        int estadoCredito = 0;
        try {
            daoModelCredito = new ModelCredito();
            if (idCategoriaCredito != 1){
                result = daoModelCredito.buscarTipoCredito1(idCredito);
            } else if (idCategoriaCredito == 2) {
                result = daoModelCredito.buscarTipoCredito2(idCredito);
            } else {
                result = daoModelCredito.buscarTipoCredito1(idCredito);
            }
            while (result.next()) {
                estadoCredito = Integer.parseInt(result.getString("ddlEstadoCredito"));
            }
            estadoCredito = estadoCredito > 0 ? 0 : 1;
            _ObjCredito = new ObjCredito();
            _ObjCredito.setIdCredito(idCredito);
            _ObjCredito.setEstadoCredito(estadoCredito);
            return Mensaje(daoModelCredito.cambiarEstadoCredito(_ObjCredito), "El estado del crédito ha sido actualizado", "Ha ocurrido un error al intentar actualizar el estado");

        } catch (Exception e) {
            return Mensaje(false, "", "Ha ocurrido un error en el controlador " + e.getMessage());
        }
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
    
    public String consultar(int id, String tipo) {
        String salida;
        respuesta = new LinkedHashMap<>();
        result = null;
        try {
            if (tipo.equals("Clase")) {
                result = daoModelCredito.buscarTipoCredito1(id);
            } else {
                result = daoModelCredito.buscarTipoCredito2(id);
            }
            while (result.next()) {
                respuesta.put("idCredito", result.getString("idCurso"));
                respuesta.put("nombreCurso", result.getString("nombreCurso"));
                respuesta.put("cantidadClases", result.getString("cantidadClases"));
                respuesta.put("horasPorClase", result.getString("horasPorClase"));
                respuesta.put("estadoCurso", result.getString("estadoCurso"));
                respuesta.put("precioCurso", result.getString("precioCurso"));
                respuesta.put("descripcionCurso", result.getString("descripcionCurso"));
                respuesta.put("idCategoriaCurso", result.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", result.getString("nombreCategoriaCurso"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            Mensaje(false, "", "Ha ocurrido un error en el Controller " + e.getMessage());
        }
        salida = new Gson().toJson(respuesta);
        return salida;
    }
    
    public String getTableCredito() {
        List<String[]> lista = new ArrayList<>();
        try {
            daoModelCredito = new ModelCredito();
            result = daoModelCredito.ListAll();
            while (result.next()) {
                String[] estado = {"success", "ok"};
                if (result.getInt("estadoCredito") == 0) {
                    estado[0] = "danger";
                    estado[1] = "remove";
                }
                String[] arreglo = new String[8];
                arreglo[0] = result.getString("idCredito").trim();
                arreglo[1] = result.getString("documentoUsuario").trim();
                arreglo[2] = result.getString("idCategoriaCredito").trim();
                arreglo[3] = result.getString("fechaInicio").trim();
                arreglo[4] = result.getString("saldoInicial").trim();
                arreglo[5] = result.getString("saldoActual").trim();
                arreglo[6] = "<a class=\"btn-sm btn-" + estado[0] + " btn-block\" href=\"javascript:void(0)\"  onclick=\"credito.myAjax('Estado'," + arreglo[0] + ", '','Credito')\">"
                        + "<span class=\"glyphicon glyphicon-" + estado[1] + "\"></span></a>";
                lista.add(arreglo);
            }
        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error en el controller " + e.toString());
        } finally {
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
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
