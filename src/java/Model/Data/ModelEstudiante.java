
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjUsuario;
import Model.DTO.ObjDetalleUsuario;
import Model.JDBC.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juan Sebastian Montoya
 */
public class ModelEstudiante extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelEstudiante() {
        getConnection();
    }

    public boolean Add(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        boolean objReturn = false;
        String sql = "call spIngresarDetalleEstudiante(?,?,?)";
        String sql2 = "call spIngresarEstudiante(?,?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objDetalleUsuario.getDireccionUsuario());
            pStmt.setString(2, _objDetalleUsuario.getTelefonoMovil());
            pStmt.setInt(3, _objDetalleUsuario.getGeneroUsuario());
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
                pStmt = connection.prepareCall(sql2);
                pStmt.setString(1, _objUsuario.getDocumentoUsuario());
                pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
                pStmt.setString(3, _objUsuario.getNombreUsuario());
                pStmt.setString(4, _objUsuario.getApellidoUsuario());
                pStmt.setString(5, _objUsuario.getTelefonoFijo());
                pStmt.setString(6, _objUsuario.getEmailUsuario());
                pStmt.setString(7, _objUsuario.getPassword());
                pStmt.setInt(8, _objUsuario.getEstadoUsuario());
                pStmt.setString(9, _objUsuario.getDocumentoAcudiente());
                if (pStmt.executeUpdate() > 0) {
                    objReturn = true;
                } else {
                    connection.rollback();
                }

            } else {
                connection.rollback();
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

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "call spConsultarEstudiantes()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarPorID(String ID) {
        ResultSet rs = null;
        String sql = "call spConsultarEstudiantePorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, ID);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarPreinscritoPorID(String ID) {
        ResultSet rs = null;
        String sql = "call spConsultarPreinscritoPorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, ID);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Edit(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        boolean objReturn = false;
        String sql = "call spActualizarEstudiante(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objUsuario.getDocumentoUsuario());
            pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
            pStmt.setString(3, _objUsuario.getNombreUsuario());
            pStmt.setString(4, _objUsuario.getApellidoUsuario());
            pStmt.setString(5, _objUsuario.getEmailUsuario());
            pStmt.setString(6, _objUsuario.getPassword());
            pStmt.setInt(7, _objUsuario.getEstadoUsuario());
            pStmt.setString(8, _objUsuario.getDocumentoAcudiente());
            pStmt.setString(9, _objDetalleUsuario.getDireccionUsuario());
            pStmt.setString(10, _objUsuario.getTelefonoFijo());
            pStmt.setString(11, _objDetalleUsuario.getTelefonoMovil());
            pStmt.setInt(12, _objDetalleUsuario.getGeneroUsuario());
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListPreinscritos() {
        ResultSet rs = null;
        String sql = "call spConsultarPreinscritos()";
        try {
            getStmt();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public String[] AddInscrito(ObjUsuario _objUsuario, ObjDetalleUsuario _objDetalleUsuario) {
        String[] objReturn = new String[2];
        String sql = "call spIngresarDetalleEstudiante(?,?,?,?)";
        String sql2 = "call spIngresarEstudianteApartirDePreinscrito(?,?,?,?,?,?,?,?)";
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objDetalleUsuario.getDireccionUsuario());
            pStmt.setString(2, _objDetalleUsuario.getTelefonoMovil());
            pStmt.setInt(3, _objDetalleUsuario.getGeneroUsuario());
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                pStmt = connection.prepareCall(sql2);
                pStmt.setString(1, _objUsuario.getDocumentoUsuario());
                pStmt.setDate(2, Date.valueOf(_objUsuario.getFechaNacimiento()));
                pStmt.setString(3, _objUsuario.getNombreUsuario());
                pStmt.setString(4, _objUsuario.getApellidoUsuario());
                pStmt.setString(5, _objUsuario.getTelefonoFijo());
                pStmt.setString(6, _objUsuario.getEmailUsuario());
                pStmt.setString(7, _objUsuario.getPassword());
                pStmt.setInt(8, _objUsuario.getEstadoUsuario());
                pStmt.setString(9, _objUsuario.getDocumentoAcudiente());
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    objReturn[0] = rs.getString("tipo");
                    objReturn[1] = rs.getString("mensaje");
                    if (objReturn[0].equals("error")) {
                        connection.rollback();
                        return objReturn;
                    }
                }
            } else {
                objReturn[0] = "error";
                objReturn[1] = "Ha ocurrido un error al ingresar el detalle";
                connection.rollback();
                return objReturn;
            }
            connection.commit();
        } catch (SQLException sqlE) {
            objReturn[0] = "error";
            objReturn[1] = "Ha ocurrido un error: " + sqlE;
            try {
                connection.rollback();
            } catch (Exception e) {
                objReturn[0] = "error";
                objReturn[1] = "Ha ocurrido un error: " + e;
            }
        }
        return objReturn;
    }
}
