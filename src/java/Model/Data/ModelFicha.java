/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjFicha;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelFicha extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelFicha() {
        getConnection();
    }

    public boolean Add(ObjFicha _objFicha) {
        boolean objReturn = false;
        String sql = "call spIngresarFicha(?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objFicha.getIdCurso());
            pStmt.setInt(2, _objFicha.getCuposDisponibles());
            pStmt.setDate(3, _objFicha.getFechaInicio());
            pStmt.setInt(4, _objFicha.getPrecioFicha());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListAll() {

        ResultSet rs = null;
        String sql = "call spConsultarFichas()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

}
