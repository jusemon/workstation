
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

//~--- non-JDK imports --------------------------------------------------------
import Model.DTO.ObjClase;

import Model.JDBC.ConnectionDB;

//~--- JDK imports ------------------------------------------------------------
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelClase extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelClase() {
        getConnection();
    }

    public String[] Add(ObjClase _objClase) {
        String[] objReturn = new String[2];
        String sql = "call spIngresarClase(?,?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objClase.getIdCurso());
            pStmt.setString(2, _objClase.getFecha());
            pStmt.setString(3, _objClase.getDocumentoUsuario());
            pStmt.setInt(4, _objClase.getEstadoAsistencia());
            pStmt.setInt(5, _objClase.getEstadoPago());
            pStmt.setInt(6, _objClase.getCreditoCreado());
            pStmt.setFloat(7, _objClase.getPrecioClase());

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                objReturn[0] = rs.getString("tipo");
                objReturn[1] = rs.getString("mensaje");

                if (objReturn[0].equals("error")) {
                    return objReturn;
                }
            }
        } catch (SQLException e) {
            objReturn[0] = "error";
            objReturn[1] = "Ha ocurrido un error: " + e;
        }

        return objReturn;
    }

    public boolean Edit(ObjClase _objFicha) {
        boolean objReturn = false;
        String sql = "call spActualizarClase(?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objFicha.getIdClase());
            pStmt.setInt(2, _objFicha.getIdCurso());
            pStmt.setString(3, _objFicha.getFecha());
            pStmt.setString(4, _objFicha.getDocumentoUsuario());
            pStmt.setInt(5, _objFicha.getEstadoAsistencia());
            pStmt.setInt(6, _objFicha.getEstadoPago());
            pStmt.setInt(7, _objFicha.getCreditoCreado());
            pStmt.setFloat(8, _objFicha.getPrecioClase());

            int updateCount = pStmt.executeUpdate();

            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }

    public ResultSet ListAll() {
        ResultSet rs = null;
        String sql = "call spConsultarClasesCurso()";

        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public boolean cambiarEstadoPago(ObjClase _objFicha) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoPagoClase(?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objFicha.getIdClase());

            int updateCount = pStmt.executeUpdate();

            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }

    public ResultSet buscarPorDocumentoUsuario(String ID) {
        ResultSet rs = null;
        String sql = "call spConsultarClasePorDocumentoUsuario(?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, ID);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
