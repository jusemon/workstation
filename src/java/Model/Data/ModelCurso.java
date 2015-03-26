/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCurso;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelCurso extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCurso() {
        getConnection();
    }

    public boolean Add(ObjCurso _objCurso) {
        boolean objReturn = false;
        String sql = "call spIngresarCurso(?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCurso.getNombreCurso());
            pStmt.setInt(2, _objCurso.getDuracionCurso());
            pStmt.setInt(3, _objCurso.getEstadoCurso());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListAll() throws Exception {

        ResultSet rs = null;
        String sql = "call spConsultarCursos()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public ResultSet buscarPorID(int ID) {
        ResultSet rs = null;
        String sql = "call spConsultarCursoPorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }
}
