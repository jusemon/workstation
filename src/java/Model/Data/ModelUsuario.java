/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "call spIngresarUsuario (?,?,?,?,?,?,?,?)";
        try {
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
            pStmt.setString(3, _objUsuario.getNombreUsuario());
            pStmt.setString(4, _objUsuario.getApellidoUsuario());
            pStmt.setString(5, _objUsuario.getEmailUsuario());
            pStmt.setString(6, _objUsuario.getPassword());
            pStmt.setInt(7, _objUsuario.getEstadoUsuario());
            pStmt.setInt(8, _objUsuario.getIdrol());
            
            int updateCount = pStmt.executeUpdate();
            if (updateCount>0) {
                objReturn= true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
    
    public ResultSet ListAll() throws Exception{
        ResultSet rs= null;
        String sql = "SELECT `documentoUsuario`, `fechaNacimiento`, `nombreUsuario`, `apellidoUsuario`, `emailUsuario`, `password`, `estadoUsuario`, `idDetalleUsuario`, `idrol`, `documentoAcudiente` FROM `tblusuario` ";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException: "+e.getMessage());
        }
        return rs;
    }
    
    //Busca el usuario en la base de datos segun su nombre  de usuario y contraseña
    public ResultSet Find (ObjUsuario _objUsuario){
    ResultSet rs = null;
    String query = "SELECT `documentoUsuario`, `fechaNacimiento`, `nombreUsuario`, `apellidoUsuario`, `emailUsuario`, `password`, `estadoUsuario`, `idDetalleUsuario`, `idrol`, `documentoAcudiente` FROM `tblusuario`  WHERE `emailUsuario` = '%s' and `password` = '%s'";
    String sql = String.format(query, _objUsuario.getEmailUsuario(), _objUsuario.getPassword());
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException: "+e.getMessage());
        }
        return  rs;
    }

    public ResultSet buscarPorID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
