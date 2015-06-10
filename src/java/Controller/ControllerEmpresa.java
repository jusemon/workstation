/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author David
 */
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DTO.ObjEmpresa;
import Model.Data.ModelEmpresa;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ControllerEmpresa extends HttpServlet {

    ModelEmpresa daoModelEmpresa;
    ObjEmpresa _objEmpresa = new ObjEmpresa();

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
            switch (request.getParameter("action")) {

                // <editor-fold defaultstate="collapsed" desc="Registrar una Empresa">
                case "Registrar": {
                    daoModelEmpresa = new ModelEmpresa();
                    String nitEmpresa = request.getParameter("txtNitEmpresa").trim();
                    _objEmpresa.setNitEmpresa(nitEmpresa);
                    String nombreEmpresa = request.getParameter("txtNombreEmpresa").trim();
                    _objEmpresa.setNombreEmpresa(nombreEmpresa);
                    String direccionEmpresa = request.getParameter("txtDireccionEmpresa").trim();
                    _objEmpresa.setDireccionEmpresa(direccionEmpresa);
                    String nombreContacto = request.getParameter("txtNombreContacto").trim();
                    _objEmpresa.setNombreContacto(nombreContacto);
                    String telefonoContacto = request.getParameter("txtTelefonoContacto").trim();
                    _objEmpresa.setTelefonoContacto(telefonoContacto);
                    String emailContacto = request.getParameter("txtEmailContacto").trim();
                    _objEmpresa.setEmailContacto(emailContacto);
                    String salida = new Gson().toJson(daoModelEmpresa.Add(_objEmpresa));
                    daoModelEmpresa.Signout();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                // </editor-fold>
                
                // <editor-fold defaultstate="collapsed" desc="Editar una Empresa">
                case "Editar": {
                    daoModelEmpresa = new ModelEmpresa();
                    String nitEmpresa = request.getParameter("txtNitEmpresa").trim();
                    _objEmpresa.setNitEmpresa(nitEmpresa);
                    String nombreEmpresa = request.getParameter("txtNombreEmpresa").trim();
                    _objEmpresa.setNombreEmpresa(nombreEmpresa);
                    String direccionEmpresa = request.getParameter("txtDireccionEmpresa").trim();
                    _objEmpresa.setDireccionEmpresa(direccionEmpresa);
                    String nombreContacto = request.getParameter("txtNombreContacto").trim();
                    _objEmpresa.setNombreContacto(nombreContacto);
                    String telefonoContacto = request.getParameter("txtTelefonoContacto").trim();
                    _objEmpresa.setTelefonoContacto(telefonoContacto);
                    String emailContacto = request.getParameter("txtEmailContacto").trim();
                    _objEmpresa.setEmailContacto(emailContacto);
                    String salida = Mensaje(daoModelEmpresa.Edit(_objEmpresa), "La empresa ha sido actualizada", "Ha ocurrido un error al intentar actualizar la empresa");
                    daoModelEmpresa.Signout();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(salida);
                    break;
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Enlistar las Empresas">
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getTableEmpresa());
                    break;
                }
                // </editor-fold>
            }
        }
    }

    public String getTableEmpresa() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        int contador = 0;
        String[] arreglo;
        try {
            daoModelEmpresa = new ModelEmpresa();
            result = daoModelEmpresa.ListAll();
            while (result.next()) {
                arreglo = new String[7];
                arreglo[0] = result.getString("nitEmpresa").trim();
                arreglo[1] = result.getString("nombreEmpresa").trim();
                arreglo[2] = result.getString("direccionEmpresa").trim();
                arreglo[3] = result.getString("nombreContacto").trim();
                arreglo[4] = result.getString("telefonoContacto").trim();
                arreglo[5] = result.getString("emailContacto").trim();
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"empresa.editar(" + contador + ")\">"
                        + "<span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
                contador++;
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error." + e.getMessage());
        } finally {
            daoModelEmpresa.Signout();
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
