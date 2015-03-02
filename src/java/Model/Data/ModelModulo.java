/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Zack
 */
public class ModelModulo extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelModulo() {
        getConnection();
    }
       
    public ResultSet ListByUser(int user) throws Exception{
        ResultSet rs= null;
        String sql = "CALL `spConsultarUsuarioModulo` (?)";
        pStmt = connection.prepareCall(sql);
        try {
            getStmt();
            pStmt.setInt(1, user);
            rs = pStmt.executeQuery();
        } catch (Exception e) {
            System.err.println("SQLException: "+e.getMessage());
        }
        return rs;
    }
    
        public ResultSet ListAll() throws Exception{
        ResultSet rs= null;
        String sql = "SELECT `idmodulo`, `enlace`, `nombre` FROM `tblmodulo`";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.err.println("SQLException: "+e.getMessage());
        }
        return rs;
    }
    
    
    
}
