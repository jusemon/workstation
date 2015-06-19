/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCurso;
import Model.DTO.ObjSeminario;
import Model.JDBC.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public boolean Add(ObjSeminario _objSeminario) {
        boolean objReturn = false;
        String sql = "call spIngresarSeminario(?,?,?,?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objSeminario.getNombreCurso());
            pStmt.setInt(2, _objSeminario.getCantidadClases());
            pStmt.setInt(3, _objSeminario.getHorasPorClase());
            pStmt.setInt(4, _objSeminario.getEstadoCurso());
            pStmt.setString(5, _objSeminario.getDescripcionCurso());
            pStmt.setInt(6, _objSeminario.getPrecioCurso());
            pStmt.setString(7, _objSeminario.getFechaSeminario());
            pStmt.setInt(8, _objSeminario.getCupoSeminario());
            pStmt.setInt(9, _objSeminario.getIdCategoriaCurso());

            int updateCount = pStmt.executeUpdate();
            if (updateCount > 0) {
                objReturn = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objReturn;
    }

    public Map<String, String> buscarCursoPorID(int ID) {
        ResultSet rs = null;
        String sql = "call spConsultarCursoPorID(?)";
        Map<String, String> respuesta = new LinkedHashMap<>();
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                respuesta.put("idCurso", rs.getString("idCurso"));
                respuesta.put("nombreCurso", rs.getString("nombreCurso"));
                respuesta.put("cantidadClases", rs.getString("cantidadClases"));
                respuesta.put("horasPorClase", rs.getString("horasPorClase"));
                respuesta.put("estadoCurso", rs.getString("estadoCurso"));
                respuesta.put("precioCurso", rs.getString("precioCurso"));
                respuesta.put("descripcionCurso", rs.getString("descripcionCurso"));
                respuesta.put("idCategoriaCurso", rs.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", rs.getString("nombreCategoriaCurso"));
            }
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return respuesta;
    }

    public Map<String, String> buscarSeminarioPorID(int ID) {
        ResultSet rs = null;
        Map<String, String> respuesta = new LinkedHashMap<>();
        String sql = "call spConsultarSeminarioPorID(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, ID);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                respuesta.put("idCurso", rs.getString("idCurso"));
                respuesta.put("nombreCurso", rs.getString("nombreCurso"));
                respuesta.put("cantidadClases", rs.getString("cantidadClases"));
                respuesta.put("horasPorClase", rs.getString("horasPorClase"));
                respuesta.put("estadoCurso", rs.getString("estadoCurso"));
                respuesta.put("precioCurso", rs.getString("precioCurso"));
                respuesta.put("descripcionCurso", rs.getString("descripcionCurso"));
                respuesta.put("idCategoriaCurso", rs.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", rs.getString("nombreCategoriaCurso"));
                respuesta.put("fechaSeminario", rs.getString("fechaSeminario"));
                respuesta.put("cupoSeminario", rs.getString("cupoSeminario"));
            }
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return respuesta;
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

    public boolean Edit(ObjSeminario _objSeminario) {
        boolean objReturn = false;
        String sql = "call spActualizarSeminario(?,?,?,?,?,?,?,?,?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objSeminario.getIdCurso());
            pStmt.setString(2, _objSeminario.getNombreCurso());
            pStmt.setInt(3, _objSeminario.getCantidadClases());
            pStmt.setInt(4, _objSeminario.getHorasPorClase());
            pStmt.setInt(5, _objSeminario.getEstadoCurso());
            pStmt.setString(6, _objSeminario.getDescripcionCurso());
            pStmt.setInt(7, _objSeminario.getPrecioCurso());
            pStmt.setString(8, _objSeminario.getFechaSeminario());
            pStmt.setInt(9, _objSeminario.getCupoSeminario());
            pStmt.setInt(10, _objSeminario.getIdCategoriaCurso());
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

    public List<Map> ListCursosDisponibles() {
        List<Map> lista = new ArrayList<>();
        ResultSet rs = null;
        Map<String, String> respuesta = null;
        String sql = "call spConsultarCursosDisponibles()";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                respuesta = new LinkedHashMap<>();
                respuesta.put("idCurso", rs.getString("idCurso"));
                respuesta.put("nombreCurso", rs.getString("nombreCurso"));
                respuesta.put("cantidadClases", rs.getString("cantidadClases"));
                respuesta.put("horasPorClase", rs.getString("horasPorClase"));
                respuesta.put("estadoCurso", rs.getString("estadoCurso"));
                respuesta.put("descripcionCurso", rs.getString("descripcionCurso"));
                respuesta.put("precioCurso", rs.getString("precioCurso"));
                respuesta.put("idCategoriaCurso", rs.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", rs.getString("nombreCategoriaCurso"));
                lista.add(respuesta);
            }
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return lista;
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

    public List<Map> ListSeminariosDisponibles() {
        List<Map> lista = new ArrayList<>();
        ResultSet rs = null;
        Map<String, String> respuesta = null;
        String sql = "call spConsultarSeminariosDisponibles()";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                respuesta = new LinkedHashMap<>();
                respuesta.put("idCurso", rs.getString("idCurso"));
                respuesta.put("nombreCurso", rs.getString("nombreCurso"));
                respuesta.put("cantidadClases", rs.getString("cantidadClases"));
                respuesta.put("horasPorClase", rs.getString("horasPorClase"));
                respuesta.put("estadoCurso", rs.getString("estadoCurso"));
                respuesta.put("descripcionCurso", rs.getString("descripcionCurso"));
                respuesta.put("precioCurso", rs.getString("precioCurso"));
                respuesta.put("idCategoriaCurso", rs.getString("idCategoriaCurso"));
                respuesta.put("nombreCategoriaCurso", rs.getString("nombreCategoriaCurso"));
                lista.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        return lista;
    }
}
