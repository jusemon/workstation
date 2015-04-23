/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCliente;
import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ModelCliente extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCliente() {
        getConnection();
    }

    public boolean Add(ObjCliente _objCliente) {
        boolean objReturn = false;
        String sql = "call spIngresarCliente (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCliente.getTipoDocumento());
            pStmt.setInt(2, _objCliente.getNumeroDocumento());
            pStmt.setDate(3, Date.valueOf(_objCliente.getFechaNacimiento()));
            pStmt.setInt(4, _objCliente.getGeneroCliente());
            pStmt.setString(5, _objCliente.getNombreCliente());
            pStmt.setString(6, _objCliente.getApellidoCliente());
            pStmt.setString(7, _objCliente.getDireccionCliente());
            pStmt.setString(8, _objCliente.getTelefonoFijo());
            pStmt.setString(9, _objCliente.getTelefonoMovil());
            pStmt.setString(10, _objCliente.getEmailCliente());
            pStmt.setInt(11, _objCliente.getEstadoCliente());
            pStmt.setInt(12, _objCliente.getTipoDocumentoAcudiente());
            pStmt.setString(13, _objCliente.getNumeroDocumentoAcudiente());

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
        String sql = "call spConsultarClientes()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarPorID(int ID, String tipo) {
        ResultSet rs = null;
        String sql = "call spConsultarClientePorID(?, ?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            pStmt.setString(2, tipo);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Edit(ObjCliente _objCliente) {
        boolean objReturn = false;
        String sql = "call spActualizarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCliente.getTipoDocumento());
            pStmt.setInt(2, _objCliente.getNumeroDocumento());
            pStmt.setDate(3, Date.valueOf(_objCliente.getFechaNacimiento()));
            pStmt.setInt(4, _objCliente.getGeneroCliente());
            pStmt.setString(5, _objCliente.getNombreCliente());
            pStmt.setString(6, _objCliente.getApellidoCliente());
            pStmt.setString(7, _objCliente.getDireccionCliente());
            pStmt.setString(8, _objCliente.getTelefonoFijo());
            pStmt.setString(9, _objCliente.getTelefonoMovil());
            pStmt.setString(10, _objCliente.getEmailCliente());
            pStmt.setInt(11, _objCliente.getEstadoCliente());
            pStmt.setInt(12, _objCliente.getTipoDocumentoAcudiente());
            pStmt.setString(13, _objCliente.getNumeroDocumentoAcudiente());
            pStmt = connection.prepareCall(sql);

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
