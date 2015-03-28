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
import Model.DTO.ObjAbono;
/**
 *
 * @author Administrador
 */
public class ModelAbono extends ConnectionDB {
    private PreparedStatement pStmt;

    public ModelAbono(){
        getConnection();
    }
    
    public boolean Add(ObjAbono _objAbono){
        boolean objReturn = false;
        String sql = "call spIngresarAbono(?,?,?)";
        
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setDouble(1, _objAbono.getValorAbono());
            pStmt.setString(1, _objAbono.getFechaPago());
            pStmt.setInt(1, _objAbono.getIdCredito());
            
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}