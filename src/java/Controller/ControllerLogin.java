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

    ObjUsuario _objUsuario = new  ObjUsuario();
    ModelUsuario _modelUsuario = new ModelUsuario();
    ModelModulo _modelModulo = new ModelModulo();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response // * @throws ServletException if a
     * servlet-specific error occurs
     * @throws javax.servlet.ServletException
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try {
            switch (request.getParameter("Action")) {
                case "Iniciar Sesion":
                    {
                        HttpSession session = request.getSession();
                        String email = request.getParameter("nom");
                        String pass = request.getParameter("pass");
                        if (comprobarUsuario(email, pass)) {
                            session.setAttribute("usuario", email);
                            session.setAttribute("pass", pass);
                            session.setAttribute("correo", _objUsuario.getEmailUsuario());
                            session.setAttribute("idRol", _objUsuario.getIdrol());
                            session.setAttribute("objUsuario", _objUsuario);
                            try {                                
                                String[] aux = _modelModulo.convertirRSaArray(_modelModulo.ListByUser(_objUsuario.getEmailUsuario()));
                                session.setAttribute("derechos", aux);
                                session.setAttribute("isConsulta", false);
                                session.setAttribute("resultado", null);
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                            
                            response.sendRedirect("index.jsp?mensaje=1");
                            
                        } else {
                            response.sendRedirect("index.jsp?mensaje=2");
                            
                        }       break;
                    }
                case "Cerrar Sesion":
                    {
                        HttpSession session = request.getSession();
                        session.invalidate();
                        _objUsuario = null;
                        response.sendRedirect("index.jsp");
                        break;
                    }
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public boolean comprobarUsuario(String email, String pass) {
        ResultSet rs;        
        _objUsuario.setEmailUsuario(email);
        _objUsuario.setPassword(pass);
        try {
            rs = _modelUsuario.Find(_objUsuario);
            while (rs.next()) {
                if (rs.getString("emailUsuario").equalsIgnoreCase(email) && rs.getString("password").equals(pass)) {
                    _objUsuario.setDocumentoUsuario(rs.getString("documentoUsuario"));
                    _objUsuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    _objUsuario.setIdrol(rs.getInt("idRol"));
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    //Este metodo imprime la barra superior segun los privilegios del usuario logueado
    public String imprimirBarra(String email, String pass) {
        ResultSet result;
        String barraModulos = "";
        if (!comprobarUsuario(email, pass)) {
            barraModulos += "            <li id=\"btnnuestro\" class=\"\"><a href=\"nuestro.jsp\">Nuestros Cursos</a></li>\n"
                    + "            <li id=\"btnacerca\" class=\"\"><a href=\"acerca.jsp\">Acerca de Nosotros</a></li>";
            return barraModulos;
        }
        try {
            comprobarUsuario(email, pass);
            result = _modelModulo.ListByUser(_objUsuario.getEmailUsuario());
            String btn;
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
                for (String result1 : result) {
                    if (urlActual.endsWith(result1)) {
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
