/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjUsuario;
import Model.Data.ModelUsuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zack
 */
public class ControllerUsuario extends HttpServlet {

    public ObjUsuario _objUsuario = new ObjUsuario();
    ModelUsuario daoModelUsuario = new ModelUsuario();
    SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoFechaSalida = new SimpleDateFormat("yyyy-MM-dd");

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
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "Registrar": {
                                        try {
                        String tipoDocumento = request.getParameter("ddlIdentificacion").trim();
                        long numeroIdentificacion = Long.parseLong(request.getParameter("txtIdentificacion").trim());
                        String identificacion = tipoDocumento + numeroIdentificacion;
                        String nombre = request.getParameter("txtNombre").trim();
                        String apellido = request.getParameter("txtApellido").trim();
                        String fechaNacimiento = request.getParameter("dateFechaNacimiento").trim();
                        String correo = request.getParameter("txtCorreo").trim();
                        int estado = 1;
                        int rol = 3;
                        String pass = request.getParameter("txtPass").trim();                        
                        _objUsuario.setDocumentoUsuario(identificacion);
                        _objUsuario.setNombreUsuario(nombre);
                        _objUsuario.setApellidoUsuario(apellido);
                        _objUsuario.setFechaNacimiento(formatoFechaSalida.format(formatoFechaEntrada.parse(fechaNacimiento)));
                        _objUsuario.setEmailUsuario(correo);
                        _objUsuario.setEstadoUsuario(estado);
                        _objUsuario.setPassword(pass);
                        _objUsuario.setIdrol(rol);
                        response.setContentType("application/json");
                        daoModelUsuario = new ModelUsuario();
                        String salida = Mensaje(daoModelUsuario.Add(_objUsuario), "El estudiante ha sido registrado", "A ocurrido un error al intentar registrar al estudiante");
                        response.getWriter().write(salida);
                    } catch (NumberFormatException | IOException e) {
                        System.out.println("Ha ocurrido un error en el Controller Usuario " + e.getMessage());
                    } catch (ParseException ex) {
                        response.setContentType("application/json");
                        String salida = Mensaje(false, "", "A ocurrido un error con la fecha de nacimiento");
                        response.getWriter().write(salida);
                    }
                    break;
                }
                case "Consultar": {

                    break;
                }
                case "Editar": {

                    break;
                }
                case "Matricular": {

                    break;
                }
                case "Preinscribir": {

                    break;
                }
                case "Estado": {

                    break;
                }
                case "Enlistar": {

                    break;
                }
            }
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
