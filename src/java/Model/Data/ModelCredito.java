/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.DTO.ObjCredito;
import Model.DTO.ObjDetalleMovimiento;
import Model.DTO.ObjMovimiento;
import Model.JDBC.ConnectionDB;
import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class ModelCredito extends ConnectionDB {

    private final static Gson gson = new Gson();
    private PreparedStatement pStmt;

    public ModelCredito() {
        getConnection();
    }

    public boolean add(ObjCredito _objCredito, ObjMovimiento _objMovimiento,
            List<ObjDetalleMovimiento> _listObjDetalleMovimientos) {
        boolean objReturn = false;
        String sql = "call spIngresarCredito(?,?,?)";
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCredito.getDocumentoUsuario());
            pStmt.setDouble(2, _objCredito.getSaldoInicial());
            pStmt.setDouble(3, _objCredito.getSaldoActual());
            ResultSet rs = pStmt.executeQuery();
            String[] resultado = new String[2];
            if (rs.next()) {
                resultado[0] = rs.getString("Respuesta");
                resultado[1] = rs.getString("tipo");
                System.out.println(resultado[0] + " Tipo: " + resultado[1]);
            }
            if (resultado[1].equals("success")) {
                objReturn = true;
                String sql2 = "call spIngresarMovimientoCredito(?,?,?)";
                pStmt = connection.prepareCall(sql2);
                pStmt.setDouble(1, _objCredito.getSaldoInicial() - _objCredito.getSaldoActual());
                pStmt.setString(2, _objMovimiento.getDocumentoUsuario());
                pStmt.setString(3, _objMovimiento.getDocumentoAuxiliar());
                int updateCount2 = pStmt.executeUpdate();
                if (updateCount2 > 0) {
                    objReturn = true;
                    String sql3 = "";
                    sql3 = "call spIngresarDetalleVenta(?,?,?,?,?)";
                    pStmt = connection.prepareCall(sql3);
                    for (ObjDetalleMovimiento _objDetalle : _listObjDetalleMovimientos) {
                        pStmt.setInt(1, _objDetalle.getIdArticulo());
                        pStmt.setInt(2, _objDetalle.getCantidad());
                        pStmt.setInt(3, _objDetalle.getDescuento());
                        pStmt.setInt(4, _objDetalle.getTotalDetalleMovimiento());
                        pStmt.setInt(5, _objDetalle.getPrecioArticulo());
                        if (pStmt.executeUpdate() > 0) {
                            objReturn = true;
                        } else {
                            objReturn = false;
                            connection.rollback();
                        }
                    }
                    if (objReturn) {
                        String sql4 = "call spIngresarDetalleCredito()";
                        pStmt = connection.prepareCall(sql4);
                        int updateCount4 = pStmt.executeUpdate();
                        if (updateCount4 > 0) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            for (StackTraceElement throwable : e.getStackTrace()) {
                System.err.println(throwable);
            }
        }
        return objReturn;
    }

    public boolean add(ObjCredito _objCredito, ObjMovimiento _objMovimiento) {
        boolean objReturn = false;
        String sql = "call spIngresarCredito(?,?,?)";
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, _objCredito.getDocumentoUsuario());
            pStmt.setDouble(2, _objCredito.getSaldoInicial());
            pStmt.setDouble(3, _objCredito.getSaldoActual());
            ResultSet rs = pStmt.executeQuery();
            String[] resultado = new String[2];
            if (rs.next()) {
                resultado[0] = rs.getString("Respuesta");
                resultado[1] = rs.getString("tipo");
                System.out.println(resultado[0] + " Tipo: " + resultado[1]);
            }
            if (resultado[1].equals("success")) {
                objReturn = true;
                String sql2 = "call spIngresarMovimientoCredito(?,?,?)";
                pStmt = connection.prepareCall(sql2);
                pStmt.setDouble(1, _objCredito.getSaldoInicial() - _objCredito.getSaldoActual());
                pStmt.setString(2, _objMovimiento.getDocumentoUsuario());
                pStmt.setString(3, _objMovimiento.getDocumentoAuxiliar());
                int updateCount2 = pStmt.executeUpdate();
                if (updateCount2 > 0) {
                    objReturn = true;
                    String sql3 = "call spIngresarDetalleMovimientoCredito(?)";
                    pStmt = connection.prepareCall(sql3);
                    pStmt.setString(1, _objCredito.getDocumentoUsuario());
                    objReturn = (pStmt.executeUpdate() > 0);
                    if (objReturn) {
                        String sql4 = "call spIngresarDetalleCredito()";
                        pStmt = connection.prepareCall(sql4);
                        int updateCount4 = pStmt.executeUpdate();
                        if (updateCount4 > 0) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            for (StackTraceElement throwable : e.getStackTrace()) {
                System.err.println(throwable);
            }
        }
        return objReturn;
    }

    public ResultSet buscarCreditoByID(int idCredito) {
        ResultSet rs = null;
        String sql = "call spConsultarCreditoByID(?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, idCredito);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public String consultarDetalleCreditoByID(int idCredito) {
        String sql = "call spConsultarDetalleCreditoByIdCredito(?)";
        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, idCredito);
            pStmt.execute();
            List list = new ArrayList();
            int saldoInicial = 50000;
            do {
                ResultSet rs = pStmt.getResultSet();

                ResultSetMetaData meta = rs.getMetaData();
                int cc = meta.getColumnCount();
                while (rs.next()) {
                    Map map = new LinkedHashMap<>();
                    for (int i = 1; i <= cc; ++i) {
                        Class<?> type = Class.forName(meta.getColumnClassName(i));
                        map.put(meta.getColumnLabel(i), gson.toJsonTree(rs.getObject(i), type));                        
                    }
                    list.add(map);
                }
            } while (pStmt.getMoreResults());
            Collections.sort(list, new Comparator<Map>(){
                @Override
                public int compare(Map o1, Map o2) {
                    int uno = ((JsonPrimitive)o1.get("Movimiento")).getAsInt();
                    int dos = ((JsonPrimitive)o2.get("Movimiento")).getAsInt();
                    return  Integer.compare(uno, dos);
                }
            });
            for (int i = 0; i < list.size(); i++) {
                Map aux = (Map)list.get(i);
                int debe = Integer.parseInt(aux.get("Debe/Cargo").toString());
                int haber = Integer.parseInt(aux.get("Haber/Abono").toString());
                saldoInicial = saldoInicial - debe + haber;
                aux.replace("Saldo", saldoInicial);
                list.set(i, aux);
            }
            return gson.toJson(list);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelCredito.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ResultSet buscarCreditoByDocumento(String documentoUsuario) {
        ResultSet rs = null;
        String sql = "call spConsultarCreditoByDocumento(?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setString(1, documentoUsuario);
            rs = pStmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public boolean cambiarEstadoCredito(ObjCredito _objCredito) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoCredito(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setInt(2, _objCredito.getEstadoCredito());

            int updateCount = pStmt.executeUpdate();

            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }

    public boolean update(ObjCredito _objCredito, ObjMovimiento _objMovimiento,
            List<ObjDetalleMovimiento> _listObjDetalleMovimientos, String tipo, double precio) {
        boolean objReturn = false;
        String sql = "call spActualizarCredito(?,?)";
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setDouble(2, _objCredito.getSaldoActual() - precio);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.executeQuery();
            String[] resultado = new String[2];
            if (rs.next()) {
                resultado[0] = rs.getString("Respuesta");
                resultado[1] = rs.getString("tipo");
            }
            if (resultado[1].equals("success")) {
                objReturn = true;
                String sql2 = "call spIngresarMovimientoCredito(?,?,?)";
                pStmt = connection.prepareCall(sql2);
                pStmt.setDouble(1, precio);
                pStmt.setString(2, _objMovimiento.getDocumentoUsuario());
                pStmt.setString(3, _objMovimiento.getDocumentoAuxiliar());
                int updateCount2 = pStmt.executeUpdate();
                if (updateCount2 > 0) {
                    objReturn = true;
                    String sql3;
                    if (tipo == null) {
                        sql3 = "call spIngresarDetalleMovimientoCredito(?)";
                        pStmt = connection.prepareCall(sql3);
                        pStmt.setString(1, _objCredito.getDocumentoUsuario());
                    } else {
                        sql3 = "call spIngresarDetalleVenta(?,?,?,?,?)";
                        pStmt = connection.prepareCall(sql3);
                        for (ObjDetalleMovimiento _objDetalle : _listObjDetalleMovimientos) {
                            pStmt.setInt(1, _objDetalle.getIdArticulo());
                            pStmt.setInt(2, _objDetalle.getCantidad());
                            pStmt.setInt(3, _objDetalle.getDescuento());
                            pStmt.setInt(4, _objDetalle.getTotalDetalleMovimiento());
                            pStmt.setInt(5, _objDetalle.getPrecioArticulo());

                            if (pStmt.executeUpdate() > 0) {
                                objReturn = true;
                            } else {
                                objReturn = false;
                                connection.rollback();
                            }
                        }
                    }
                    if (objReturn) {
                        String sql4 = "call spIngresarDetalleCredito()";
                        pStmt = connection.prepareCall(sql4);
                        int updateCount4 = pStmt.executeUpdate();
                        if (updateCount4 > 0) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            for (StackTraceElement throwable : e.getStackTrace()) {
                System.err.println(throwable);
            }
        }
        return objReturn;
    }

    public String[] abonar(ObjCredito _objCredito, ObjMovimiento _objMovimiento) {
        String[] resultado = new String[2];
        String sql = "call spIngresarAbono(?,?,?,?)";
        int valorAbono = _objMovimiento.getTotalMovimiento();
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setDouble(2, valorAbono);
            pStmt.setString(3, _objMovimiento.getDocumentoUsuario());
            pStmt.setString(4, _objMovimiento.getDocumentoAuxiliar());
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                resultado[0] = rs.getString("Respuesta");
                resultado[1] = rs.getString("tipo");
                if (resultado[1].equals("success")) {
                    connection.commit();
                }
                System.out.println("mensaje: " + resultado[0] + " Tipo: " + resultado[1]);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            try {
                connection.rollback();
            } catch (Exception ex) {
                System.err.println(ex);
            }

            for (StackTraceElement throwable : e.getStackTrace()) {
                System.err.println(throwable);
            }
        }

        return resultado;
    }

    public boolean cambiarEstado(ObjCredito _objCredito) {
        boolean objReturn = false;
        String sql = "call spActualizarEstadoCredito(?,?)";

        try {
            getStmt();
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setInt(2, _objCredito.getEstadoCredito());

            int updateCount = pStmt.executeUpdate();

            if (updateCount > 0) {
                objReturn = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return objReturn;
    }

    public ResultSet listAll() throws Exception {
        ResultSet rs = null;
        String sql = "call spListarCreditos()";

        try {
            getStmt();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }

        return rs;
    }

    public boolean update(ObjCredito _objCredito, ObjMovimiento _objMovimiento, int precioClase) {
        String sql = "call spActualizarCredito(?,?)";
        boolean objReturn = false;
        try {
            getStmt();
            connection.setAutoCommit(false);
            pStmt = connection.prepareCall(sql);
            pStmt.setInt(1, _objCredito.getIdCredito());
            pStmt.setDouble(2, _objCredito.getSaldoActual() - precioClase);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.executeQuery();
            String[] resultado = new String[2];
            if (rs.next()) {
                resultado[0] = rs.getString("Respuesta");
                resultado[1] = rs.getString("tipo");
            }
            if (resultado[1].equals("success")) {
                objReturn = true;
                String sql2 = "call spIngresarMovimientoCredito(?,?,?)";
                pStmt = connection.prepareCall(sql2);
                pStmt.setDouble(1, precioClase);
                pStmt.setString(2, _objMovimiento.getDocumentoUsuario());
                pStmt.setString(3, _objMovimiento.getDocumentoAuxiliar());
                int updateCount2 = pStmt.executeUpdate();
                if (updateCount2 > 0) {
                    objReturn = true;
                    String sql3;
                    sql3 = "call spIngresarDetalleMovimientoCredito(?)";
                    pStmt = connection.prepareCall(sql3);
                    pStmt.setString(1, _objCredito.getDocumentoUsuario());
                    objReturn = (pStmt.executeUpdate() > 0);
                    if (objReturn) {
                        String sql4 = "call spIngresarDetalleCredito()";
                        pStmt = connection.prepareCall(sql4);
                        int updateCount4 = pStmt.executeUpdate();
                        if (updateCount4 > 0) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            for (StackTraceElement throwable : e.getStackTrace()) {
                System.err.println(throwable);
            }
        }
        return objReturn;
    }

}
