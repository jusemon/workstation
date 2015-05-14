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
    
    
    public ResultSet buscarTipoCredito1(int idCredito) {
        ResultSet rs = null;
        String sql = "call spConsultarCreditoByTipoCredito1(?)";
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
        
    public ResultSet buscarTipoCredito2(int idCredito) {
        ResultSet rs = null;
        String sql = "call spConsultarCreditoByTipoCredito2(?)";
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
    
    public boolean cambiarEstadoCredito(ObjCredito _objCredito) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoCredito(?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setInt(2,_objCredito.getIdCategoriaCredito());
            pStmt.setInt(3, _objCredito.getEstadoCredito());

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
        String sql = "call spListarCreditos()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }
        
}
