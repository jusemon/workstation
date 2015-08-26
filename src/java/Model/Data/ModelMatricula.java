
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

//~--- non-JDK imports --------------------------------------------------------
import Model.DTO.ObjClase;
import Model.DTO.ObjCurso;
import Model.DTO.ObjUsuario;

import Model.JDBC.ConnectionDB;

//~--- JDK imports ------------------------------------------------------------
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String[] Add(ObjUsuario _objUsuario, ObjCurso _objCurso) {
        int cantidadClases = _objCurso.getCantidadClases();
        String[] objReturn = new String[2];
        String sql = "call spContarClasesRestantes(?,?,?)";
        String sql2 = "call spIngresarClase(?,?)";

        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCurso.getIdCurso());
            pStmt.setString(2, _objUsuario.getDocumentoUsuario());
            pStmt.setInt(3, cantidadClases);

            ResultSet rs = pStmt.executeQuery();
            int numero = cantidadClases;

            while (rs.next()) {
                numero = rs.getInt("Restantes");
            }

            if (numero >= 0) {
                pStmt = connection.prepareCall(sql2);

                for (int i = 0; i < cantidadClases; i++) {
                    pStmt.setInt(1, _objCurso.getIdCurso());
                    pStmt.setString(2, _objUsuario.getDocumentoUsuario());

                    ResultSet rs2 = pStmt.executeQuery();

                    while (rs2.next()) {
                        objReturn[0] = rs2.getString("tipo");
                        objReturn[1] = rs2.getString("mensaje");

                        if (objReturn[0].equals("error")) {
                            connection.rollback();

                            return objReturn;
                        }
                    }
                }

                connection.commit();
            } else {
                objReturn[0] = "error";

                if ((cantidadClases + numero) == 0) {
                    objReturn[1] = "Ya se encuentra matriculado a todas las clases de este curso";
                } else {
                    objReturn[1] = "Sólo se puede matricular a " + (cantidadClases + numero) + " clases";
                }
            }

            return objReturn;
        } catch (SQLException sqlE) {
            try {
                objReturn[0] = "error";
                objReturn[1] = "Ha ocurrido un error: " + sqlE;
                connection.rollback();
            } catch (Exception e) {
                objReturn[0] = "error";
                objReturn[1] = "Ha ocurrido un error: " + e;
            }
        }

        return objReturn;
    }

    public Map<String, String> BuscarMatriculaPorDocumentoYIdCurso(String documento, int idCurso) {
        Map<String, String> resultado = new LinkedHashMap<>();
        ResultSet rs = null;
        String sql = "call spConsultarMatriculaPorDocumentoYIdCurso(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, documento);
            pStmt.setInt(2, idCurso);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                resultado.put("documentoUsuario", rs.getString("documentoUsuario"));
                resultado.put("nombreUsuario", rs.getString("nombreUsuario"));
                resultado.put("apellidoUsuario", rs.getString("apellidoUsuario"));
                resultado.put("idCurso", rs.getString("idCurso"));
                resultado.put("precioCurso", rs.getString("precioCurso"));
                resultado.put("precioClase", rs.getString("precioClase"));
                resultado.put("horasPorClase", rs.getString("horasPorClase"));
                resultado.put("nombreCurso", rs.getString("nombreCurso"));
            }
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return resultado;
    }

    public String[] RegistrarAsistencia(List<ObjClase> clases, String documentoUsuario, int beneficiario) {
        String[] objReturn = new String[2];
        String sql = "call spActualizarClase(?,?,?,?)";

        try {
            connection.setAutoCommit(false);

            if (beneficiario == 1) {
                for (ObjClase clase : clases) {
                    pStmt = connection.prepareCall(sql);
                    pStmt.setString(1, clase.getDocumentoUsuario());
                    pStmt.setInt(2, clase.getIdCurso());
                    pStmt.setInt(3, clase.getEstadoPago());
                    pStmt.setInt(4, clase.getCreditoCreado());

                    ResultSet rs = pStmt.executeQuery();

                    while (rs.next()) {
                        objReturn[0] = rs.getString("tipo");
                        objReturn[1] = rs.getString("mensaje");

                        if (objReturn[0].equals("error")) {
                            connection.rollback();

                            return objReturn;
                        }
                    }
                }
            } else {
                int pago = 0;
                String documentoCliente = null;
                String sql2 = "call spGenerarVentaClases(?,?,?,?)";
                int idCurso = 0;

                getStmt();

                for (ObjClase clase : clases) {
                    if (clase.getEstadoPago() == 1) {
                        pago = 1;
                        documentoCliente = clase.getDocumentoUsuario();
                        idCurso = clase.getIdCurso();

                        break;
                    }
                }

                if (pago == 1) {
                    pStmt = connection.prepareCall(sql2);
                    pStmt.setInt(1, clases.size());
                    pStmt.setString(2, documentoUsuario);
                    pStmt.setString(3, documentoCliente);
                    pStmt.setInt(4, idCurso);

                    int execute = pStmt.executeUpdate();

                    if (0 >= execute) {
                        connection.rollback();
                        objReturn[0] = "error";
                        objReturn[1] = "Ha ocurrido un error";

                        return objReturn;
                    }
                }

                for (ObjClase clase : clases) {
                    pStmt = connection.prepareCall(sql);
                    pStmt.setString(1, clase.getDocumentoUsuario());
                    pStmt.setInt(2, clase.getIdCurso());
                    pStmt.setInt(3, clase.getEstadoPago());
                    pStmt.setInt(4, clase.getCreditoCreado());

                    ResultSet rs = pStmt.executeQuery();

                    while (rs.next()) {
                        objReturn[0] = rs.getString("tipo");
                        objReturn[1] = rs.getString("mensaje");

                        if (objReturn[0].equals("error")) {
                            connection.rollback();

                            return objReturn;
                        }
                    }
                }

                connection.commit();

                return objReturn;
            }
        } catch (SQLException sqlE) {
            try {
                objReturn[0] = "error";
                objReturn[1] = "Ha ocurrido un error: " + sqlE;
                connection.rollback();
            } catch (Exception e) {
                objReturn[0] = "error";
                objReturn[1] = "Ha ocurrido un error: " + e;
            }
        }

        return objReturn;
    }

    public String[] asignarEmpresa(String nitEmpresa, String documentoEstudiante) {
        String[] resultado = new String[2];
        String sql = "call spAsignarEmpresaBeneficiario(?,?)";

        try {
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, nitEmpresa);
            pStmt.setString(2, documentoEstudiante);

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                resultado[0] = rs.getString("tipo");
                resultado[1] = rs.getString("mensaje");
            }
        } catch (SQLException ex) {
            resultado[0] = "error";
            resultado[1] = "Ha ocurrido un error en el modelo: " + ex;
        }

        return resultado;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
