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
import Model.DTO.ObjVenta;

/**
 *
 * @author Lorenzo
 */
public class ModelVenta  extends ConnectionDB{
    
     private PreparedStatement pStmt;
     
     public ModelVenta(){
     getConnection();
     }
    
     public boolean Add(ObjVenta _objVenta){
         boolean objReturn = false;
         String sql = "call spIngresarVenta (?,?,?,?)";
         
         try {
             getStmt();
             pStmt = connection.prepareCall(sql);
             pStmt.setString(1, _objVenta.getFechaVenta());
             pStmt.setInt(2, _objVenta.getTotalVenta());
             pStmt.setString(3, _objVenta.getCedulaCliente());
             pStmt.setString(4, _objVenta.getNombreCliente());
             
             int updateCount = pStmt.executeUpdate();
             if (updateCount >0){
                 objReturn = true;
             }    
         } catch (SQLException e){
             System.out.println(e.getMessage());
         }
         return objReturn;
     }   
     
     public ResultSet listAll() throws Exception{
         ResultSet rs = null;
         String sql = "call spConsultarVentas()";
         try{
             getStmt();
             rs = stmt.executeQuery(sql);     
         }catch (SQLException e){
             System.err.println("SQLException:" + e.getMessage());
         }
         return rs;
     }

    public ResultSet ListAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

