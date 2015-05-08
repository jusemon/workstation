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
import Model.DTO.ObjCredito;

/**
 *
 * @author David
 */
public class ModelCredito  extends ConnectionDB{
    
    private PreparedStatement pStmt;
    
    public ModelCredito(){
        getConnection();
    }
    
    public boolean Add(ObjCredito _objCredito){
        boolean objReturn = false;
        String sql = "call spIngresarCredito(?,?,?,?,?)";
        
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCredito.getDocumentoUsuario());            
            pStmt.setString(2, _objCredito.getFechaInicio());
            pStmt.setDouble(3, _objCredito.getSaldoInicial());
            pStmt.setDouble(4, _objCredito.getSaldoActual());
            pStmt.setInt(5, _objCredito.getEstadoCredito());
            
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
