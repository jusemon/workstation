/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjInscrito;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelInscrito extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelInscrito() {
        getConnection();
    }

    public boolean Add(ObjInscrito _objInscrito) {
        boolean objReturn = false;
        String sql = "call spIngresarInscritoSeminario(?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objInscrito.getDocumento());
            pStmt.setString(2, _objInscrito.getNombres());
            pStmt.setString(3, _objInscrito.getTelefono());
            pStmt.setString(4, _objInscrito.getCorreo());
            pStmt.setInt(5, _objInscrito.getIdseminario());
            if (pStmt.executeUpdate()>0) {
                objReturn = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet EnlistarPorSeminario(int idSeminario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
