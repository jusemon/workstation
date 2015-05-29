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
import Model.DTO.ObjVenta;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjUsuario;
import java.util.List;

/**
 *
 * @author lorenzo
 */
public class ModelVenta extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelVenta() {
        getConnection();
    }
    
    public boolean Add(ObjVenta _objVenta, ObjUsuario _objUsuario, List<ObjDetalleMovimiento> _listObjDetalleMovimientos){
        boolean objReturn = false;
        String sql1 = "call spIngresarVenta (?,?,?,?)";
        String sql2 = "call spIngresarDetalleVenta (?,?,?,?,?,)";
        
        try{
         getStmt();
                 
                 
        }
    }
    
    

}
