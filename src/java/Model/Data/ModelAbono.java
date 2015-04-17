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
 * @author David
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
            pStmt.setInt(1, _objAbono.getIdCredito());
            pStmt.setDouble(2, _objAbono.getValorAbono());
            pStmt.setString(3, _objAbono.getFechaPago());
            
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;        
    }
    
    public boolean Edit(ObjAbono _objAbono){
        
        boolean objReturn = false;
        String sql = "call spActualizarAbono(?,?,?)";
        
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objAbono.getIdCredito());
            pStmt.setDouble(2, _objAbono.getValorAbono());
            pStmt.setString(3, _objAbono.getFechaPago());
                        
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        }
        catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }
        return objReturn;
    }
    public ResultSet buscarPorNIT(String idAbono) {
        ResultSet rs = null;
        String sql = "call spConsultarAbonoByCredito(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, idAbono);
            rs = pStmt.executeQuery();            
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }   
    
    public ResultSet ListAll() throws Exception {

        ResultSet rs = null;
        String sql ="call spListarAbonos";
           try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }
}
