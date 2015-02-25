/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Administrador
 */
public class ModelUsuario extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelUsuario() {
        getConnection();
    }

    public boolean Add(ObjUsuario _objUsuario) {
        boolean objReturn = false;
        String sql = "INSERT INTO `tblusuario`(`nombreUsuario`, `password`, `email`, `telefono`, `rol`) VALUES (?,?,?,?,?)";
        try {
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1, _objUsuario.getNombre());
            pStmt.setString(2, _objUsuario.getPassword());
            pStmt.setString(3, _objUsuario.getEmail());
            pStmt.setInt(5, _objUsuario.getTelefono());
            pStmt.setInt(5, _objUsuario.getRol());
            
            int updateCount = pStmt.executeUpdate();
            if (updateCount>0) {
                objReturn= true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
    
    public ResultSet ListAll() throws Exception{
        ResultSet rs= null;
        String sql = "SELECT `idUsuario`, `nombreUsuario`, `password`, `email`, `telefono`, `rol` FROM `tblusuario`";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.err.println("SQLException: "+e.getMessage());
        }
        return rs;
    }
    
    public ResultSet Find (ObjUsuario _objUsuario){
    ResultSet rs = null;
    String query = "SELECT `idUsuario`, `nombreUsuario`, `password`, `email`, `telefono`, `rol` FROM `tblusuario` WHERE `nombreUsuario` = '%s' and `password` '%s'";
    String sql = String.format(query, _objUsuario.getNombre(), _objUsuario.getPassword());
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.err.println("SQLException: "+e.getMessage());
        }
        return  rs;
    }
    
    
}
