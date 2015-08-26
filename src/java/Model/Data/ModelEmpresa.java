
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

//~--- non-JDK imports --------------------------------------------------------
import Model.DTO.ObjEmpresa;

import Model.JDBC.ConnectionDB;

//~--- JDK imports ------------------------------------------------------------
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class ModelEmpresa extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelEmpresa() {
        getConnection();
    }

    public Map<String, String> Add(ObjEmpresa _objEmpresa) {
        Map<String, String> objReturn = new LinkedHashMap<>();
        String sql = "call spIngresarEmpresa(?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objEmpresa.getNitEmpresa());
            pStmt.setString(2, _objEmpresa.getNombreEmpresa());
            pStmt.setString(3, _objEmpresa.getDireccionEmpresa());
            pStmt.setString(4, _objEmpresa.getNombreContacto());
            pStmt.setString(5, _objEmpresa.getTelefonoContacto());
            pStmt.setString(6, _objEmpresa.getEmailContacto());

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                objReturn.put("mensaje", rs.getString("mensaje"));
                objReturn.put("tipo", rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "call spListarEmpresas";

        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public ResultSet buscarPorNIT(String nitEmpresa) {
        ResultSet rs = null;
        String sql = "call spConsultarEmpresabyNIT(?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, nitEmpresa);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public boolean Edit(ObjEmpresa _objEmpresa) {
        boolean objReturn = false;
        String sql = "call spActualizarEmpresa(?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objEmpresa.getNitEmpresa());
            pStmt.setString(2, _objEmpresa.getNombreEmpresa());
            pStmt.setString(3, _objEmpresa.getDireccionEmpresa());
            pStmt.setString(4, _objEmpresa.getNombreContacto());
            pStmt.setString(5, _objEmpresa.getTelefonoContacto());
            pStmt.setString(6, _objEmpresa.getEmailContacto());

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


//~ Formatted by Jindent --- http://www.jindent.com
