/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjSeminario;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelSeminario extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelSeminario() {
        getConnection();
    }

    public boolean Add(ObjSeminario _objSeminario) {
        boolean objReturn = false;
        String sql = "call spIngresarSeminario (?,?,?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objSeminario.getNombreSeminario());
            pStmt.setInt(2, _objSeminario.getDuracionSeminario());
            pStmt.setInt(3, _objSeminario.getEstadoSeminario());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet listAll() throws Exception {

        ResultSet rs = null;
        String sql = "SELECT 'IdSeminario',"
                + "'nombreSeminario',"
                + "'duracionSeminario',"
                + "'estadoSeminario'"
                + "FROM 'tblSeminario'";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;

    }

    public ResultSet Buscar(int idSeminario) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT 'IdSeminario', `nombreSeminario`"
                + "FROM `tblseminario` WHERE `idSeminario` = " + idSeminario;
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
