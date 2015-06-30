/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjIncrito;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelInscrito extends ConnectionDB {

    PreparedStatement pStmt;

    public ModelInscrito() {
        getConnection();
    }

    public boolean Add(ObjIncrito _objIncrito) {
        boolean objReturn = false;
        String sql = "call spIngresarInscritoSeminario(?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objIncrito.getDocumento());
            pStmt.setString(2, _objIncrito.getNombres());
            pStmt.setString(3, _objIncrito.getTelefono());
            pStmt.setString(4, _objIncrito.getCorreo());
            pStmt.setInt(5, _objIncrito.getIdseminario());
            if (pStmt.executeUpdate()>0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
}
