/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjUsuario;
import Model.DTO.ObjDetalleUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelEstudiante extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelEstudiante() {
        getConnection();
    }

    public boolean Add(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        boolean objReturn = false;
        String sql = "call spIngresarEstudiante (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
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

    public ResultSet buscarPorID(int ID, String tipo) {
        ResultSet rs = null;
        String sql = "call spConsultarEstudiantePorID(?, ?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            pStmt.setString(2, tipo);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Edit(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        boolean objReturn = false;
        String sql = "call spActualizarEstudiante(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
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
