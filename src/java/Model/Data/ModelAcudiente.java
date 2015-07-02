/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjAcudiente;
import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelAcudiente extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelAcudiente() {
        getConnection();
    }

    public boolean Add(ObjAcudiente _objAcudiente, String documentoEstudiante) {
        boolean objReturn = false;
        String sql = "call spIngresarAcudiente(?,?,?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objAcudiente.getDocumentoAcudiente());
            pStmt.setString(2, _objAcudiente.getNombreAcudiente());
            pStmt.setString(3, _objAcudiente.getTelefonoAcudiente());
            pStmt.setDate(4, Date.valueOf(_objAcudiente.getFechaNacimiento()));
            pStmt.setString(5, documentoEstudiante);

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
        String sql = "call spConsultarAcudientes()";
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
        String sql = "call spConsultarAcudientePorID(?,?)";
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

    public boolean Edit(ObjAcudiente _objAcudiente) {
        boolean objReturn = false;
        String sql = "call spActualizarAcudiente(?,?)";

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
