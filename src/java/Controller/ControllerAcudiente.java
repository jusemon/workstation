/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjAcudiente;
import Model.Data.ModelAcudiente;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author Zack
 */
public class ControllerAcudiente extends HttpServlet {

    ObjAcudiente _objAcudiente;
    ModelAcudiente daoModelAcudiente = new ModelAcudiente();
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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "Registrar": {
                    try {
                        //Beneficiario 0->No Subvencionado 1->Subvencionado?
                        response.setContentType("application/json");
                        String tipoDocumento = request.getParameter("ddlIdentificacion").trim();
                        long numeroIdentificacion = Long.parseLong(request.getParameter("txtIdentificacion").trim());
                        String identificacion = tipoDocumento + numeroIdentificacion;
                        String nombre = request.getParameter("txtNombre").trim();
                        String apellido = request.getParameter("txtApellido").trim();
                        String telefonoFijo = request.getParameter("txtTelefono").trim();
                        String fechaNacimiento = request.getParameter("dateFechaNacimiento").trim();
                        _objAcudiente = new ObjAcudiente();
                        _objAcudiente.setDocumentoAcudiente(identificacion);
                        _objAcudiente.setNombreAcudiente(nombre + " " + apellido);
                        _objAcudiente.setTelefonoAcudiente(telefonoFijo);
                        try {
                            _objAcudiente.setFechaNacimiento(formatoFechaSalida.format(formatoFechaEntrada.parse(fechaNacimiento)));
                        } catch (ParseException ex) {
                            String salida = Mensaje(false, null, "A ocurrido un error al intentar registrar al acudiente");
                            response.getWriter().write(salida);
                            break;
                        }
                        String salida = Mensaje(daoModelAcudiente.Add(_objAcudiente), "El acudiente ha sido registrado", "A ocurrido un error al intentar registrar al acudiente");
                        response.getWriter().write(salida);

                    } catch (NumberFormatException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.getWriter().write(getTableAcudientes());
                    break;
                }
            }
        }

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

    private String getTableAcudientes() {
        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        try {
            result = daoModelAcudiente.ListAll();
            String[] arreglo;
            while (result.next()) {
                arreglo = new String[5];
                arreglo[0] = result.getString("tipoDocumento").trim();
                arreglo[1] = result.getString("numeroDocumento").trim();
                arreglo[2] = result.getString("nombreAcudiente").trim();
                arreglo[3] = result.getString("telefono").trim();
                arreglo[4] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"acudiente.myAjax('Editar'," + result.getString("tipoDocumento") + ", " + result.getInt("numeroDocumento") + " )\">\n"
                        + "                                                <span class=\"glyphicon glyphicon-search\"></span></a>";
                lista.add(arreglo);
            }

        } catch (Exception e) {
            System.err.println("Ha Ocurrido un error" + e.getMessage());
        } finally {
            daoModelAcudiente.Signout();
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

}
