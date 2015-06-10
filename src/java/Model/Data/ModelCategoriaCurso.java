/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCategoriaCurso;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class ModelCategoriaCurso extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCategoriaCurso() {
        getConnection();
    }

    public Map<String, String> Add(ObjCategoriaCurso _objCategoriaCurso) {
        Map <String, String>objReturn = new LinkedHashMap<>();
        String sql = "call spIngresarCategoriaCurso(?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCategoriaCurso.getNombreCategoriaCurso());
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {                 
                objReturn.put("mensaje",rs.getString("mensaje"));
                objReturn.put("tipo", rs.getString("tipo"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "call spConsultarCategoriaCursos()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Edit(ObjCategoriaCurso _objCategoriaCurso)
    {
        boolean objReturn = false;
        String sql = "call spActualizarCategoriaCurso(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCategoriaCurso.getIdCategoriaCurso());
            pStmt.setString(2, _objCategoriaCurso.getNombreCategoriaCurso());
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public int GetIDCategoriaSeminario() {
        int resultado = 0;
        ResultSet rs = null;
        String sql = "call spConsultarIDCategoriaSeminario()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultado=rs.getInt("idCategoriaCurso");
            }
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return resultado;
    }
}
