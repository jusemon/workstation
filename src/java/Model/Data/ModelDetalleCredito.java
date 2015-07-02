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
import Model.DTO.ObjDetalleCredito;
import Model.DTO.ObjCredito;

/**
 *
 * @author David
 */
public class ModelDetalleCredito  extends ConnectionDB{
    
    private PreparedStatement pStmt;
    
    public ModelDetalleCredito(){
        getConnection();
    }
    
    public boolean Add(ObjDetalleCredito _objDetalleCredito){
        boolean objReturn = false;
        String sql = "call spIngresarDetalleCredito(?,?,?,?)";
        
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objDetalleCredito.getIdDetalleCredito());            
            pStmt.setInt(2, _objDetalleCredito.getIdCredito());
            pStmt.setInt(3, _objDetalleCredito.getIdMovimiento());
            
            
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;        
    }    
    
    public ResultSet buscarCreditoByID(int idCredito) {
        ResultSet rs = null;
        String sql = "call spConsultarDetalleCreditoByIdCredito(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, idCredito);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }
        
    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "call spListarDetalleCreditos()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }
        
}

