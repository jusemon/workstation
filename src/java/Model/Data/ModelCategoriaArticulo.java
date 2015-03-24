/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCategoriaArticulo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.JDBC.ConnectionDB;

/**
 *
 * @author Administrador
 */
public class ModelCategoriaArticulo extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCategoriaArticulo() {
        getConnection();
    }

    public boolean Add(ObjCategoriaArticulo _objCategoriaArticulo) {
        boolean objReturn = false;
        String sql = "call spIngresarCategoriaArticulo (?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCategoriaArticulo.getNombreCategoriaArticulo());
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
        String sql = "SELECT `idCategoriaArticulo`,"
                + "`nombreCategoriaArticulo`"
                + "FROM `tblcategoriaarticulo`";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public ResultSet Buscar (int idCategoriaArticulo) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT `nombreCategoriaArticulo` ,"
                + "FROM `tblcategoriaarticulo` WHERE `idCategoriaArticulo` = "+idCategoriaArticulo;
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
