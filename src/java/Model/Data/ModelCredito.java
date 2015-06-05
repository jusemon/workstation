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
    
    public ResultSet buscarCreditoByID(int idCredito) {
        ResultSet rs = null;
        String sql = "call spConsultarCreditoByID(?)";
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
    public ResultSet buscarCreditoByDocumento(String documentoUsuario) {
        ResultSet rs = null;
        String sql = "call spConsultarCreditoByDocumento(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, documentoUsuario);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }    
    public boolean cambiarEstadoCredito(ObjCredito _objCredito) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoCredito(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());            
            pStmt.setInt(2, _objCredito.getEstadoCredito());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
    public boolean Update(ObjCredito _objCredito) {
        boolean objReturn = false;
        String sql = "call spActualizarCredito(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());            
            pStmt.setDouble(2, _objCredito.getSaldoActual());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }  
    
    public boolean cambiarEstado(ObjCredito _objCredito) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoCredito(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setInt(2, _objCredito.getEstadoCredito());

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
