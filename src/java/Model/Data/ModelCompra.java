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
import Model.DTO.ObjCompra;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjUsuario;
import java.util.List;

/**
 *
 * @author Lorenzo
 */
public class ModelCompra extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCompra() {
        getConnection();
    }

    public boolean Add(ObjCompra _objCompra, ObjUsuario _objUsuario, List<ObjDetalleMovimiento> _listObjDetalleMovimientos) {
        boolean objReturn = false;
        String sql1 = "call spIngresarCompra (?,?,?,?)";
        String sql2 = "call spIngresarDetalleCompra (?,?,?,?,?)";

        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql1);
            pStmt.setString(1, _objCompra.getFacturaProveedor());
            pStmt.setString(2, _objCompra.getNombreProveedor());
            pStmt.setInt(3, _objCompra.getTotalCompra());
            pStmt.setString(4, _objUsuario.getDocumentoUsuario());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
                pStmt = connection.prepareCall(sql2);
                for (ObjDetalleMovimiento _objDetalle : _listObjDetalleMovimientos) {
                    pStmt.setInt(1, _objDetalle.getIdArticulo());
                    pStmt.setInt(2, _objDetalle.getCantidad());
                    pStmt.setInt(3, _objDetalle.getDescuento());
                    pStmt.setInt(4, _objDetalle.getTotalDetalleMovimiento());
                    pStmt.setInt(5, _objDetalle.getPrecioArticulo());
                    if (pStmt.executeUpdate() > 0) {
                        objReturn = true;
                    } else {
                        connection.rollback();
                    }
                }
            } else {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            try {
                connection.rollback();
            } catch (Exception e) {
                System.out.println(sqlE.getMessage());
            }
        }
        return objReturn;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = " call spConsultarCompras()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;

    }

    public boolean Edit(ObjCompra _objCompra) {
        boolean objReturn = false;
        String sql = " call spActualizarCompra(?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCompra.getFacturaProveedor());
            pStmt.setString(2, _objCompra.getNombreProveedor());
            pStmt.setString(3, _objCompra.getFechaCompra());
            pStmt.setInt(4, _objCompra.getTotalCompra());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

}
