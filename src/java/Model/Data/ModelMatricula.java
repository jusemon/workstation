/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCurso;
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

    public ResultSet ListMatriculados() {
        ResultSet rs = null;
        String sql = "call spConsultarEstudiantesConClasesActivas()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
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
                System.out.println(e.getMessage());
            }
        }
        return objReturn;
    }

}
