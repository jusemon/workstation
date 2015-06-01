/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjUsuario;
import Model.DTO.ObjDetalleUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juan Sebastian Montoya
 */
public class ModelEstudiante extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelEstudiante() {
        getConnection();
    }

    public boolean Add(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        boolean objReturn = false;
        String sql = "call spIngresarEstudiante(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
            pStmt.setString(3, _objUsuario.getNombreUsuario());
            pStmt.setString(4, _objUsuario.getApellidoUsuario());
            pStmt.setString(5, _objUsuario.getEmailUsuario());
            pStmt.setString(6, _objUsuario.getPassword());
            pStmt.setInt(7, _objUsuario.getEstadoUsuario());
            pStmt.setString(8, _objUsuario.getDocumentoAcudiente());
            pStmt.setString(9, _objDetalleUsuario.getDireccionUsuario());
            pStmt.setString(10, _objDetalleUsuario.getTelefonoFijo());
            pStmt.setString(11, _objDetalleUsuario.getTelefonoMovil());
            pStmt.setInt(12, _objDetalleUsuario.getGeneroUsuario());
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
        String sql = "call spConsultarEstudiantes()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarPorID(String ID) {
        ResultSet rs = null;
        String sql = "call spConsultarEstudiantePorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, ID);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Edit(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        boolean objReturn = false;
        String sql = "call spActualizarEstudiante(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
            pStmt.setString(3, _objUsuario.getNombreUsuario());
            pStmt.setString(4, _objUsuario.getApellidoUsuario());
            pStmt.setString(5, _objUsuario.getEmailUsuario());
            pStmt.setString(6, _objUsuario.getPassword());
            pStmt.setInt(7, _objUsuario.getEstadoUsuario());
            pStmt.setString(8, _objUsuario.getDocumentoAcudiente());
            pStmt.setString(9, _objDetalleUsuario.getDireccionUsuario());
            pStmt.setString(10, _objDetalleUsuario.getTelefonoFijo());
            pStmt.setString(11, _objDetalleUsuario.getTelefonoMovil());
            pStmt.setInt(12, _objDetalleUsuario.getGeneroUsuario());
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
}
