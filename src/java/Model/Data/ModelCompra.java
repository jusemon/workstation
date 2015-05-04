/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.DTO.ObjCompra;

/**
 *
 * @author Lorenzo
 */
public class ModelCompra extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCompra() {
        getConnection();
    }

    public boolean Add(ObjCompra _objCompra) {
        boolean objReturn = false;
        String sql = "call spIngresarCompra (?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCompra.getFacturaProveedor());
            pStmt.setString(2, _objCompra.getNombreProveedor());
            pStmt.setString(3, _objCompra.getFechaCompra());
            pStmt.setInt(4, _objCompra.getTotalCompra());

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
        String sql = " call spConsultarCompras()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;

    }

    public boolean Edit(ObjCompra _objCompra) {
        boolean objReturn = false;
        String sql = " call spActualizarCompra(?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCompra.getFacturaProveedor());
            pStmt.setString(2, _objCompra.getNombreProveedor());
            pStmt.setString(3, _objCompra.getFechaCompra());
            pStmt.setInt(4, _objCompra.getTotalCompra());

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
