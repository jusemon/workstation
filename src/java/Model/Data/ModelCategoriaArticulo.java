/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCategoriaArticulo;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelCategoriaArticulo extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCategoriaArticulo() {
        getConnection();
    }

    public String[] add(ObjCategoriaArticulo _objCategoriaArticulo) {
        String[] objReturn = new String[2];
        String sql = "call spIngresarCategoriaArticulo (?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCategoriaArticulo.getNombreCategoriaArticulo());
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                objReturn[0] = rs.getString("mensaje");
                objReturn[1] = rs.getString("tipo");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT `idCategoriaArticulo`," + "`nombreCategoriaArticulo`" + "FROM `tblcategoriaarticulo`";

        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public boolean Edit(ObjCategoriaArticulo _objCategoriaArticulo) {
        boolean objReturn = false;
        String sql = "call spActualizarCategoriaArticulo(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCategoriaArticulo.getIdCategoriaArticulo());
            pStmt.setString(2, _objCategoriaArticulo.getNombreCategoriaArticulo());

            int updateCount = pStmt.executeUpdate();

            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
