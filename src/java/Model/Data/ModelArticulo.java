/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.DTO.ObjArticulo;

/**
 *
 * @author Lorenzo
 */
public class ModelArticulo extends ConnectionDB {
    private PreparedStatement pStmt;
    
    public ModelArticulo(){
        getConnection();
    }
    
    public boolean Add(ObjArticulo _objArticulo){
        boolean objReturn = false;
        String sql = "INSERT INTO `tblarticulo`(`idArticulo`, `idCategoriaArticulo`, `descripcionArticulo`, `cantidadDisponible`, `precioUnitario`) VALUES (?,?,?,?,?)";
        
        try {
            getStmt();
            pStmt = connection.prepareStatement(sql);
            pStmt.setInt(1, _objArticulo.getIdCategoriaArticulo());
            pStmt.setString(2, _objArticulo.getDescripcionArticulo());
            pStmt.setInt(3, _objArticulo.getCantidadDisponible());
            pStmt.setDouble(4, _objArticulo.getPrecioUnitario());
            
              int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }
    
     public ResultSet ListAll() throws Exception {

        ResultSet rs = null;
        String sql ="SELECT `idArticulo`, `idCategoriaArticulo`,"
                + "`descripcionArticulo`, `cantidadDisponible`,"
                + "`precioUnitario` "
                + "FROM `tblarticulo`" ;
           try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }
    
}
