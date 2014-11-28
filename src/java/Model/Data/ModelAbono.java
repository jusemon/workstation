/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjAbono;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;

/**
 *
 * @author Administrador
 */
public class ModelAbono extends ConnectionDB {
    private PreparedStatement pStmr;

    public ModelAbono(PreparedStatement pStmr) {
        getConnection();
    }
    
    public boolean Add(ObjAbono _objAbono){
        boolean objReturn=false;
        return false;
    }
    
    
}
