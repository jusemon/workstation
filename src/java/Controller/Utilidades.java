/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Zack
 */
public class Utilidades {

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
}
