/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCurso;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ModelCurso extends ConnectionDB {

    private PreparedStatement pStmt;

    public ModelCurso() {
        getConnection();
    }

    public boolean Add(ObjCurso _objCurso) {
        boolean objReturn = false;
        String sql = "call spIngresarCurso(?,?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCurso.getNombreCurso());
            pStmt.setInt(2, _objCurso.getCantidadClases());
            pStmt.setInt(3, _objCurso.getHorasPorClase());
            pStmt.setInt(4, _objCurso.getEstadoCurso());
            pStmt.setString(5, _objCurso.getDescripcionCurso());
            pStmt.setInt(6, _objCurso.getPrecioCurso());
            pStmt.setInt(7, _objCurso.getIdCategoriaCurso());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet buscarCursoPorID(int ID) {
        ResultSet rs = null;
        String sql = "call spConsultarCursoPorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarSeminarioPorID(int ID) {
        ResultSet rs = null;
        String sql = "call spConsultarSeminarioPorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Edit(ObjCurso _objCurso) {
        boolean objReturn = false;
        String sql = "call spActualizarCurso(?,?,?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCurso.getIdCurso());
            pStmt.setString(2, _objCurso.getNombreCurso());
            pStmt.setInt(3, _objCurso.getCantidadClases());
            pStmt.setInt(4, _objCurso.getHorasPorClase());
            pStmt.setInt(5, _objCurso.getEstadoCurso());
            pStmt.setString(6, _objCurso.getDescripcionCurso());
            pStmt.setInt(7, _objCurso.getPrecioCurso());
            pStmt.setInt(8, _objCurso.getIdCategoriaCurso());
            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public boolean cambiarEstado(ObjCurso _objCurso) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoCurso(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCurso.getIdCurso());
            pStmt.setInt(2, _objCurso.getEstadoCurso());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public ResultSet ListCursosDisponibles() {
        ResultSet rs = null;
        String sql = "call spConsultarCursosDisponibles()";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet ListAll() throws Exception {
        ResultSet rs = null;
        String sql = "call spConsultarCursos()";
        try {
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet ListAll(String seminarios) throws Exception {
        ResultSet rs = null;
        String sql = "call spConsultarSeminarios()";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public ResultSet ListSeminariosDisponibles() {
        ResultSet rs = null;
        String sql = "call spConsultarSeminariosDisponibles()";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return rs;
    }

    public boolean Preincribir(int id, String documentoUsuario) {
        boolean objReturn = false;
        String sql = "call spIngresarPreinscripcion(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, documentoUsuario);
            pStmt.setInt(2, id);

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
