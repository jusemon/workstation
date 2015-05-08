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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zack
 */
public class ModelModulo extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelModulo() {
        getConnection();
    }

    public ResultSet ListByUser(String user) throws Exception {
        ResultSet rs = null;
        String sql = "CALL `spConsultarUsuarioModulo` (?)";
        pStmt = connection.prepareCall(sql);
        try {
            getStmt();
            pStmt.setString(1, user);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT `idmodulo`, `enlace`, `nombre` FROM `tblmodulo`";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return rs;
    }

    public String[] convertirRSaArray(ResultSet entrada) {
        String[] salida = null;
        List aux = new ArrayList();
        try {
            while (entrada.next()) {
                aux.add(entrada.getString("enlace"));
            }
            salida = new String[aux.size()];

            for (int i = 0; i < salida.length; i++) {
                salida[i] = (String) aux.get(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return salida;
    }

}
