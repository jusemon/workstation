/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class ModelUsuario extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelUsuario() {
        getConnection();
    }

    public boolean Add(ObjUsuario _objUsuario) {
        boolean objReturn = false;
        String sql = "call spIngresarUsuario (?,?,?,?,?,?,?,?)";
        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
            pStmt.setString(3, _objUsuario.getNombreUsuario());
            pStmt.setString(4, _objUsuario.getApellidoUsuario());
            pStmt.setString(5, _objUsuario.getTelefonoFijo());
            pStmt.setString(6, _objUsuario.getEmailUsuario());
            pStmt.setString(7, _objUsuario.getPassword());
            pStmt.setInt(8, _objUsuario.getEstadoUsuario());
            pStmt.setInt(9, _objUsuario.getIdrol());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT `documentoUsuario`, `fechaNacimiento`, `nombreUsuario`, `apellidoUsuario`, `emailUsuario`, `password`, `estadoUsuario`, `idDetalleUsuario`, `idrol`, `documentoAcudiente` FROM `tblusuario` ";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }

    //Busca el usuario en la base de datos segun su nombre  de usuario y contraseña
    public ResultSet Find(ObjUsuario _objUsuario) {
        ResultSet rs = null;
        String sql = "call spConsultarUsuarioPorPassYCorreo (?,?)";
        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getEmailUsuario());
            pStmt.setString(2, _objUsuario.getPassword());
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarPorID(String id) {
        ResultSet rs = null;
        String sql = "call spConsultarUsuarioPorID(?)";

        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }

    public List<Map<String, String>> ListClientesYEstudiantes() {
        ResultSet rs = null;
        Map<String, String> respuesta = null;
        List<Map<String, String>> salida = new ArrayList<>();
        String sql = "call spConsultarClientesYEstudiantes()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                respuesta = new LinkedHashMap<>();
                respuesta.put("tipoDocumento", rs.getString("documentoUsuario").substring(0, 2));
                respuesta.put("numeroDocumento", rs.getString("documentoUsuario").substring(2));
                respuesta.put("fechaNacimiento", rs.getString("fechaNacimiento"));
                respuesta.put("nombreUsuario", rs.getString("nombreUsuario"));
                respuesta.put("apellidoUsuario", rs.getString("apellidoUsuario"));
                respuesta.put("emailUsuario", rs.getString("emailUsuario"));
                respuesta.put("password", rs.getString("password"));
                respuesta.put("estadoUsuario", rs.getString("estadoUsuario"));
                respuesta.put("idrol", rs.getString("idrol"));
                respuesta.put("idDetalleUsuario", rs.getString("idDetalleUsuario"));
                respuesta.put("direccionUsuario", rs.getString("direccionUsuario"));
                respuesta.put("telefonoFijo", rs.getString("telefonoFijo"));
                respuesta.put("telefonoMovil", rs.getString("telefonoMovil"));
                respuesta.put("generoUsuario", rs.getString("generoUsuario"));
                respuesta.put("documentoAcudiente", rs.getString("documentoAcudiente"));
                respuesta.put("estadoBeneficiario", rs.getString("estadoBeneficiario"));
                salida.add(respuesta);
            }
            return salida;
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    public boolean Edit(ObjUsuario _objUsuario) {
        boolean objReturn = false;
        String sql = "call spActualizarUsuario (?,?,?,?,?,?,?,?,?)";
        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
            pStmt.setString(3, _objUsuario.getNombreUsuario());
            pStmt.setString(4, _objUsuario.getApellidoUsuario());
            pStmt.setString(5, _objUsuario.getTelefonoFijo());
            pStmt.setString(6, _objUsuario.getEmailUsuario());
            pStmt.setString(7, _objUsuario.getPassword());
            pStmt.setInt(8, _objUsuario.getEstadoUsuario());
            pStmt.setInt(9, _objUsuario.getIdrol());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListOperarios() {
        ResultSet rs = null;
        String sql = "call spConsultarOperarios()";
        try {
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }

    public boolean cambiarEstado(ObjUsuario _objUsuario) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoUsuario(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setInt(2, _objUsuario.getEstadoUsuario());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
}
