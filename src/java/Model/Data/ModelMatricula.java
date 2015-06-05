/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCompra;
import Model.DTO.ObjCurso;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelMatricula extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelMatricula() {
        getConnection();
    }

    public ResultSet ListAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Add(ObjUsuario _objUsuario, ObjCurso _objCurso) {
        int cantidadClases = _objCurso.getCantidadClases();
        boolean objReturn = false;
        String sql = "call spIngresarClase(?,?)";

        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            for (int i = 0; i < cantidadClases; i++) {
                pStmt.setInt(1, _objCurso.getIdCurso());
                pStmt.setString(2, _objUsuario.getDocumentoUsuario());              
                if (pStmt.executeUpdate() > 0) {
                    objReturn = true;
                } else {
                    connection.rollback();
                    break;
                }
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

}
