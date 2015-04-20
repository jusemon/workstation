/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import com.google.gson.Gson;
import Model.DTO.ObjAbono;
import Model.Data.ModelAbono;
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
 * @author David
 */
public class ControllerAbono extends HttpServlet {

    ModelAbono daoModelAbono;
    ObjAbono _objAbono = new ObjAbono();

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
            String salida, aux;
            int id;
            Map<String, String> respuesta;
            ResultSet result;
            switch (request.getParameter("action")) {
                // <editor-fold defaultstate="collapsed" desc="Registrar un abono">
                case "Registrar":{
                    daoModelAbono = new ModelAbono();
                    
                    int idCredito = Integer.parseInt(request.getParameter("txtIdCredito"));
                    double valorAbono = Double.parseDouble(request.getParameter("txtValorAbono"));
                    String fechaPago = new String(request.getParameter("dateFechaPago").getBytes("ISO-8859-1"), "UTF-8");
                    
                    _objAbono.setIdCredito(idCredito);
                    _objAbono.setValorAbono(valorAbono);
                    _objAbono.setFechaPago(fechaPago);
                    
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    salida = Mensaje(daoModelAbono.Add(_objAbono), "El abono ha sido registrado", "Ha ocurrido un error al intentar registrar el abono");
                    daoModelAbono.Signout();
                    response.getWriter().write(salida);
                    break;
                }
                //</editor-fold>
                // <editor-fold defaultstate="collapsed" desc="Consultar un abono por crédito">
                case "Consultar": {
                    daoModelAbono = new ModelAbono();
                    aux = request.getParameter("id");
                    id = Integer.parseInt(aux.trim());
                    try {
                        respuesta = new LinkedHashMap<>();
                        result = daoModelAbono.buscarPorCredito(id);
                        while (result.next()) {
                            respuesta.put("idAbono", result.getString("idAbono"));
                            respuesta.put("idCredito", result.getString("idCredito"));
                            respuesta.put("valorAbono", result.getString("valorAbono"));
                            respuesta.put("fechaPago", result.getString("fechaPago"));                            
                        }
                        salida = new Gson().toJson(respuesta);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(salida);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                //</editor-fold>
                
                // <editor-fold defaultstate="collapsed" desc="Editar un abono">
                
                /*case "Editar":{
                    daoModelAbono = new ModelAbono();
                    int idAbono = Integer.parseInt(request.getParameter("idAbono"));
                    //String nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    _objAbono.setIdAbono(idAbono);
                    daoModelAbono.Edit(_objAbono);
                    
                    break;
                }
                */
                //</editor-fold>
                
                // <editor-fold defaultstate="collapsed" desc="Listar abonos">
                case "Enlistar":
                {
                    response.setContentType("application/text");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableAbono());
                    break;
                }
                //</editor-fold>
            }
            response.sendRedirect("caja.jsp");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="getTableAbono">
    public String getTableAbono() {
        
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
                
        try {
            daoModelAbono = new ModelAbono();
            result = daoModelAbono.ListAll();

            while (result.next()) {
                
                String[] arreglo = new String[4];
                arreglo[0] = result.getString("idAbono").trim();
                arreglo[1] = result.getString("idCredito").trim();
                arreglo[2] = result.getString("valorAbono").trim();
                arreglo[3] = result.getString("fechaPago").trim();
                lista.add(arreglo);
                /*
                tableAbono += "<tr>";
                tableAbono += "<td class=\"text-center\"><a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"editar()\">\n"
                        + "<span class=\"glyphicon glyphicon-pencil\"></span></a>\n</td>";
                tableAbono += "</tr>";
                */
                
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error" + e.getMessage());
        } finally {
            daoModelAbono.Signout();
        }
        
        String salida = new Gson().toJson(lista);
        salida = "{\"aaData\":" + salida + "}";
        return salida;        
    }
    //</editor-fold>

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

