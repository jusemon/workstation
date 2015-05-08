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
import Model.DTO.ObjArticulo;

/**
 *
 * @author Lorenzo
 */
public class ModelArticulo extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelArticulo() {
        getConnection();
    }

    public boolean Add(ObjArticulo _objArticulo) {
        boolean objReturn = false;
        String sql = "call spIngresarArticulo(?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objArticulo.getIdCategoriaArticulo());
            pStmt.setString(2, _objArticulo.getDescripcionArticulo());
            pStmt.setInt(3, _objArticulo.getCantidadDisponible());
            pStmt.setInt(4, _objArticulo.getPrecioCompra());
            pStmt.setInt(5, _objArticulo.getPrecioVenta());

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
        String sql = "call spListarArticulos()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public boolean Edit(ObjArticulo _objArticulo) {
        boolean objReturn = false;
        String sql = "call spActualizarArticulo(?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objArticulo.getIdArticulo());
            pStmt.setInt(2, _objArticulo.getIdCategoriaArticulo());
            pStmt.setString(3, _objArticulo.getDescripcionArticulo());
            pStmt.setInt(4, _objArticulo.getCantidadDisponible());
            pStmt.setInt(5, _objArticulo.getPrecioCompra());
            pStmt.setInt(6, _objArticulo.getPrecioVenta());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet buscarPorNombre(String nombreArticulo) {
        ResultSet rs = null;
        String sql = "call spConsultarArticuloByNombre(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, nombreArticulo);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

}
