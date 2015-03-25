/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjUsuario;
import Model.Data.ModelModulo;
import Model.Data.ModelUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class ControllerLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response // * @throws ServletException if a
     * servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ObjUsuario _objUsuario = new ObjUsuario();
    ModelUsuario _modelUsuario = new ModelUsuario();
    ModelModulo _modelModulo = new ModelModulo();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("Action").equals("Iniciar Sesion")) {
                HttpSession session = request.getSession();
                String nombre = request.getParameter("nom");
                String pass = request.getParameter("pass");

                if (comprobarUsuario(nombre, pass)) {
                    session.setAttribute("usuario", nombre);
                    session.setAttribute("pass", pass);
                    session.setAttribute("correo", _objUsuario.getEmail());
                    try {
                        String[] aux = _modelModulo.convertirRSaArray(_modelModulo.ListByUser(_objUsuario.getId()));
                        session.setAttribute("derechos", aux);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    response.sendRedirect("index.jsp?confirmacion=1");

                } else {
                    response.sendRedirect("index.jsp?error=1");
                    
                }
            } else if (request.getParameter("Action").equals("Cerrar Sesion")) {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        }
    }

    public boolean comprobarUsuario(String nombre, String pass) {
        ResultSet rs = null;
        _objUsuario.setNombre(nombre);
        _objUsuario.setPassword(pass);
        try {
            rs = _modelUsuario.Find(_objUsuario);
            while (rs.next()) {
                if (rs.getString("nombreUsuario").equals(nombre) && rs.getString("password").equals(pass)) {
                    _objUsuario.setId(rs.getInt("idusuario"));
                    _objUsuario.setEmail(rs.getString("email"));
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    //Este metodo imprime la barra superior segun los privilegios del usuario logueado
    public String imprimirBarra(String nombre, String pass) {
        ResultSet result = null;
        String barraModulos = "";
        if (!comprobarUsuario(nombre, pass)) {
            barraModulos += "            <li id=\"btnnuestro\" class=\"\"><a href=\"nuestro.jsp\">Nuestros Cursos</a></li>\n"
                    + "            <li id=\"btnacerca\" class=\"\"><a href=\"acerca.jsp\">Acerca de Nosotros</a></li>";
            return barraModulos;
        }
        try {
            comprobarUsuario(nombre, pass);
            System.err.println(_objUsuario.getId());
            result = _modelModulo.ListByUser(_objUsuario.getId());
            String btn = null;
            while (result.next()) {
                btn = "btn" + result.getString("enlace");
                btn = btn.replace(".jsp", "");
                barraModulos += "";
                barraModulos += "<li id=\"" + btn + "\"><a href=\"" + result.getString("enlace") + "\">" + result.getString("nombre") + "</a></li>";
                barraModulos += "";

            }
        } catch (Exception e) {
            barraModulos = "Ha ocurrido un error" + e.getMessage();
        } finally {
            _modelModulo.Signout();
        }
        return barraModulos;
    }

    //Con este metodo compruebo los derechos del usuario y permito su entrada
    public boolean comprobarEntrada(Object derechos, String urlActual) {
        try {
            if (urlActual.endsWith("index.jsp") || urlActual.endsWith("acerca.jsp") || urlActual.endsWith("nuestro.jsp")) {
                return true;
            }
            if (derechos instanceof String[]) {
                String[] result = (String[]) derechos;
                for (int i = 0; i < result.length; i++) {
                    if (urlActual.endsWith(result[i])) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
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
