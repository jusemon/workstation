/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DTO.ObjCategoriaCurso;
import Model.Data.ModelCategoriaCurso;
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
public class ControllerCategoriaCurso extends HttpServlet {

    ModelCategoriaCurso daoModelCategoriaCurso;
    ObjCategoriaCurso _objCategoriaCurso = new ObjCategoriaCurso();

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
        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
                case "Registrar": {
                    daoModelCategoriaCurso = new ModelCategoriaCurso();
                    String nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    _objCategoriaCurso.setNombreCategoriaCurso(nombre);
                    String salida = Mensaje(daoModelCategoriaCurso.Add(_objCategoriaCurso), "La categoria ha sido registrada", "Ha ocurrido un error al intentar registrar la categoria");
                    response.setContentType("application/json");
                    response.getWriter().write(salida);
                    break;
                }
                case "Editar": {
                    daoModelCategoriaCurso = new ModelCategoriaCurso();
                    int idCategoriaArticulo = Integer.parseInt(request.getParameter("idCategoriaCurso"));
                    String nombre = new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8");
                    _objCategoriaCurso.setIdCategoriaCurso(idCategoriaArticulo);
                    _objCategoriaCurso.setNombreCategoriaCurso(nombre);;
                    String salida = Mensaje(daoModelCategoriaCurso.Edit(_objCategoriaCurso), "La categoria ha sido actualizada", "Ha ocurrido un error al intentar actualizar la categoria");
                    response.setContentType("application/json");
                    response.getWriter().write(salida);
                    break;
                }
                case "Enlistar": {
                    response.setContentType("application/json");
                    response.getWriter().write(getTableCategoriaCurso());
                    break;
                }
                case "getOptionsCategorias": {
                    response.setContentType("application/text");
                    response.getWriter().write(getOptionsCategorias());
                    break;
                }
            }
        }

    }

    public String getTableCategoriaCurso() {

        ResultSet result;
        List<String[]> lista = new ArrayList<>();
        daoModelCategoriaCurso = new ModelCategoriaCurso();
        try {
            result = daoModelCategoriaCurso.ListAll();
            int contador = 0;
            while (result.next()) {
                String[] arreglo = new String[3];
                arreglo[0] = result.getString("idtblCategoriaCurso").trim();
                arreglo[1] = result.getString("nombreCategoriaCurso").trim();
                arreglo[2] = "<a class=\"btn-sm btn-primary btn-block \" href=\"javascript:void(0)\"  onclick=\"categoriaCurso.editar(" + contador + ")\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                lista.add(arreglo);
                contador++;
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error" + e.getMessage());
        } finally {
            daoModelCategoriaCurso.Signout();
        }
        String salida = new Gson().toJson(lista);
        salida = "{\"data\":" + salida + "}";
        return salida;
    }

    public String getOptionsCategorias() {
        ResultSet result;
        String lista = "";
        daoModelCategoriaCurso = new ModelCategoriaCurso();
        try {
            result = daoModelCategoriaCurso.ListAll();
            while (result.next()) {
                lista += ("<option value=\"" + result.getString("idtblCategoriaCurso").trim() + "\">" + result.getString("nombreCategoriaCurso").trim() + "</option>");
            }

        } catch (Exception e) {
            lista = ("Ha Ocurrido un error" + e.getMessage());
        } finally {
            daoModelCategoriaCurso.Signout();
        }
        return lista;
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
