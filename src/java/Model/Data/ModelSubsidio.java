
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

//~--- non-JDK imports --------------------------------------------------------
import Model.DTO.ObjSubsidio;

import Model.JDBC.ConnectionDB;

//~--- JDK imports ------------------------------------------------------------
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelSubsidio extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelSubsidio() {
        getConnection();
    }

    public boolean Add(ObjSubsidio _objSubsidio) {
        boolean objReturn = false;
        String sql = "call spIngresarBeneficio (?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objSubsidio.getValorSubsidio());
            pStmt.setString(2, _objSubsidio.getNitEmpresa());
            pStmt.setString(3, _objSubsidio.getDocumentoUsuario());

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
