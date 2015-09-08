/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zack
 */
public class Utilidades {

    private final static Gson gson = new Gson();

    public static String mensaje(boolean entrada, String mensajeSuccess, String mensajeError) {
        Map<String, String> mensaje = new LinkedHashMap<>();
        if (entrada) {
            mensaje.put("mensaje", mensajeSuccess);
            mensaje.put("tipo", "success");
        } else {
            mensaje.put("mensaje", mensajeError);
            mensaje.put("tipo", "error");
        }
        String salida = new Gson().toJson(mensaje);
        return salida;
    }

    public static String mensaje(String entrada[]) {
        Map<String, String> mensaje = new LinkedHashMap<>();

        mensaje.put("mensaje", entrada[0]);
        mensaje.put("tipo", entrada[1]);

        String salida = new Gson().toJson(mensaje);

        return salida;
    }

    public static String toJson(PreparedStatement pStmt)
            throws IOException {
        try {
            List list = new ArrayList();
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
            return gson.toJson(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getClass().getName(), e);
        }
    }

    public static String toDataTableJSON(String jsonObject) {
        
        return "{\"data\":"+jsonObject+"}";
    }
}
