/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCliente;
import Model.Data.ModelCliente;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public class ControllerCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ModelCliente daoModelCliente = new ModelCliente();
    public ObjCliente _objCliente = new ObjCliente();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            try {
                String tipoDocumento = String.valueOf(request.getParameter("ddlIdentificacion"));
                int numeroIdentificacion = Integer.parseInt(request.getParameter("txtIdentificacion"));
                String nombre = String.valueOf(request.getParameter("txtNombre"));
                String apellido = String.valueOf(request.getParameter("txtApellido"));
                int genero = Integer.parseInt(request.getParameter("radioGenero"));
                String fechaNacimiento = String.valueOf(request.getParameter("dateFechaNacimiento"));
                String direccion = String.valueOf(request.getParameter("txtDireccion"));
                String telefono = String.valueOf(request.getParameter("txtTelefono"));
                String celular = String.valueOf(request.getParameter("txtCelular").trim());
                String correo = String.valueOf(request.getParameter("txtCorreo"));
                int estado = Integer.parseInt(request.getParameter("radioEstado"));
                //Estado 0->No Subvencionado 1->Subvencionado?
                
                        LocalDate hoy = LocalDate.now();
                        
                        System.out.println(hoy.toString());

//                if(esMenorDeEdad(fechaNacimiento)){
//                    
//                }
//                
                _objCliente.setIdCliente(tipoDocumento, numeroIdentificacion);
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
                int idAcudiente;
                if (request.getParameter("idAcudiente") != null) {
                    idAcudiente = Integer.parseInt(request.getParameter("idAcudiente"));
                    _objCliente.setIdAcudiante(idAcudiente);
                }

                response.sendRedirect("matricula.jsp");

                if (daoModelCliente.Add(_objCliente)) {
                    request.setAttribute("msg", "Accion exitosa");
                    getServletConfig().getServletContext().getRequestDispatcher("/matricula.jsp").forward(request, response);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public String getTableClientes() {

        ResultSet result = null;
        String tableClientes = "";

        try {

            result = daoModelCliente.ListAll();

            while (result.next()) {
                tableClientes += "<tr>";

                tableClientes += "<td class=\"text-center\">" + result.getString("tipoDocumento").toString().trim() + "</td>";
                tableClientes += "<td class=\"text-center\">" + result.getString("numeroDocumento").toString().trim() + "</td>";
                tableClientes += "<td class=\"text-center\">" + result.getString("nombreCliente").toString().trim() + "</td>";
                tableClientes += "<td class=\"text-center\">" + result.getString("generoCliente").toString().trim() + "</td>";
                tableClientes += "<td class=\"text-center\">" + result.getString("estadoEstudiante").toString().trim() + "</td>";
                tableClientes += "<td class=\"text-center\"><a class=\"btn-sm btn-primary btn-block \"  data-toggle=\"modal\"  data-target=\"#matricular\" href=\"javascript:void(0)\"  onclick=\"consultar()\">\n" +
"                                                <span class=\"glyphicon glyphicon-search\"></span></a>\n</td>";

                tableClientes += "</tr>";
            }

        } catch (Exception e) {
            tableClientes = "Ha Ocurrido un error" + e.getMessage();
        } finally {
            daoModelCliente.Signout();
        }

        return tableClientes;
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
