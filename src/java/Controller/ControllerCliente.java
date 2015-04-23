/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.*;
import Model.Data.*;
import com.google.gson.Gson;
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
 * @author Administrador
 */
public class ControllerCliente extends HttpServlet {

    public ModelEstudiante daoModelCliente = new ModelEstudiante();
    public ObjEstudiante _objCliente = new ObjEstudiante();
    public ModelAcudiente daoModelAcudiente = new ModelAcudiente();
    public ObjAcudiente _objAcudiente = new ObjAcudiente();

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
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "Registrar": {
                    try {
                        String tipoDocumento = new String(request.getParameter("ddlIdentificacion").getBytes("ISO-8859-1"), "UTF-8");
                        int numeroIdentificacion = Integer.parseInt(request.getParameter("txtIdentificacion"));
                        String nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                        String apellido = new String(request.getParameter("txtApellido").getBytes("ISO-8859-1"), "UTF-8");
                        int genero = Integer.parseInt(request.getParameter("radioGenero"));
                        String fechaNacimiento = String.valueOf(request.getParameter("dateFechaNacimiento"));
                        String direccion = new String(request.getParameter("txtDireccion").getBytes("ISO-8859-1"), "UTF-8");
                        String telefono = String.valueOf(request.getParameter("txtTelefono"));
                        String celular = String.valueOf(request.getParameter("txtCelular").trim());
                        String correo = new String(request.getParameter("txtCorreo").getBytes("ISO-8859-1"), "UTF-8");
                        int estado = Integer.parseInt(request.getParameter("radioBeneficiario"));
                        //Beneficiario 0->No Subvencionado 1->Subvencionado?
                        String tipoDocAcudiente = "";
                        int numeroDocAcudiente = 0;
                        if (request.getParameter("tipoDocAcudiente") != null && request.getParameter("numeroDocAcudiente") != null) {
                            tipoDocAcudiente = new String(request.getParameter("tipoDocAcudiente").getBytes("ISO-8859-1"), "UTF-8");
                            numeroDocAcudiente = Integer.parseInt(request.getParameter("numeroDocAcudiente"));
                        }
                        _objCliente.setTipoDocumento(tipoDocumento);
                        _objCliente.setNumeroDocumento(numeroIdentificacion);
                        _objCliente.setNombreCliente(nombre);
                        _objCliente.setApellidoCliente(apellido);
                        _objCliente.setGeneroCliente(genero);
                        _objCliente.setFechaNacimiento(fechaNacimiento);
                        _objCliente.setDireccionCliente(direccion);
                        _objCliente.setTelefonoFijo(telefono);
                        _objCliente.setTelefonoMovil(celular);
                        _objCliente.setEmailCliente(correo);
                        _objCliente.setEstadoCliente(estado);
                        response.setContentType("application/json");
                        String salida = Mensaje(daoModelCliente.Add(_objCliente), "El cliente ha sido registrado", "A ocurrido un error al intentar registrar al cliente");
                        response.getWriter().write(salida);

                    } catch (NumberFormatException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.getWriter().write(getTableClientes());
                    break;
                }
            }
        }
    }

    public String getTableClientes() {

        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            result = daoModelCliente.ListAll();
            String[] arreglo;
            while (result.next()) {
                arreglo = new String[7];
                arreglo[0] = result.getString("tipoDocumento").trim();
                arreglo[1] = result.getString("numeroDocumento").trim();
                arreglo[2] = result.getString("nombreCliente").trim();
                arreglo[3] = result.getString("generoCliente").trim();
                arreglo[4] = result.getString("estadoEstudiante").trim();
                arreglo[5] = "<a class=\"btn-sm btn-primary btn-block \"  data-toggle=\"modal\"  data-target=\"#matricular\" href=\"javascript:void(0)\"  onclick=\"cliente.myAjax('Consultar'," + result.getString("tipoDocumento") + ", " + result.getInt("numeroDocumento") + " )\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-search\"></span></a>";
                arreglo[6] = "<a class=\"btn-sm btn-primary btn-block \"  data-toggle=\"modal\"  data-target=\"#matricular\" href=\"javascript:void(0)\"  onclick=\"cliente.myAjax('Editar'," + result.getString("tipoDocumento") + ", " + result.getInt("numeroDocumento") + " )\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-search\"></span></a>";
                lista.add(arreglo);
            }

        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error" + e.getMessage());
        } finally {
            daoModelCliente.Signout();
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
