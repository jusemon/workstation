/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrador
 */
public class Validador {
    
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_NUMERO = "^[0-9]{1,}";
    private static final String PATTERN_NOMBRE = "^([ÁÉÍÓÚáéíóúñÑa-zA-Z]{3,15})+([ ]{1})?([ÁÉÍÓÚáéíóúñÑa-zA-Z]{3,15})?";
    private static final String PATTERN_FECHA = "^(0[1-9]|1[0-9]|3[01]).(0[1-9]|1[0-2]).([0-9]{4})";
    private static final String PATTERN_TIPO_DOCUMENTO = "^(C{2}|TI{1}|CE{1}|RC{1})";

    /**
     * Valida el email que se ingresa con una expresion regular
     *
     * @param email email a validar
     * @return true email valido, sino false
     */
    public static boolean validarEmail(String email) {
        if (email==null) {
            return false;
        }
        // Compila la expresion regular en un pattern
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Busca que el email cumpla con la expresion regular
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Valida el numero que se ingresa con una expresion regular
     *
     * @param numero Numero a validar
     * @return true numero valido, sino false
     */
    public static boolean validarNumero(String numero) {
        if (numero==null) {
            return false;
        }
        // Compila la expresion regular en un pattern
        Pattern pattern = Pattern.compile(PATTERN_NUMERO);
        // Busca que el numero cumpla con la expresion regular
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }

    /**
     * Valida el nombre que se ingresa con una expresion regular
     *
     * @param nombre Nombre a validar
     * @return true nombre valido, sino false
     */
    public static boolean validarNombre(String nombre) {
        if (nombre==null) {
            return false;
        }
        // Compila la expresion regular en un pattern
        Pattern pattern = Pattern.compile(PATTERN_NOMBRE);
        // Busca que el nombre cumpla con la expresion regular
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    /**
     * Valida la fecha que se ingresa con una expresion regular
     *
     * @param fecha Fecha a validar
     * @return true fecha valida, sino false
     */
    public static boolean validarFecha(String fecha) {
        if (fecha==null) {
            return false;
        }
        // Compila la expresion regular en un pattern
        Pattern pattern = Pattern.compile(PATTERN_FECHA);
        // Busca que la fecha cumpla con la expresion regular
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }
    /**
     * Valida el email que se ingresa con una expresion regular
     *
     * @param tipo Tipo de documento que se va a validar, se permiten CC:Cedula de ciudadania, TI: Tarjeta de identidad, CE: Cedula de estrangeria y RC: Registro civil
     * @return true email valido, sino false
     */
    public static boolean validarTipoDocumento(String tipo) {
        if (tipo==null) {
            return false;
        }
        // Compila la expresion regular en un pattern
        Pattern pattern = Pattern.compile(PATTERN_TIPO_DOCUMENTO);
        // Busca que la fecha cumpla con la expresion regular
        Matcher matcher = pattern.matcher(tipo);
        return matcher.matches();
    }

    public static boolean validarDocumento(String documento) {
        if (documento==null) {
            return false;
        }
        // Compila la expresion regular en un pattern
        Pattern pattern = Pattern.compile(PATTERN_TIPO_DOCUMENTO);
        Pattern pattern2 = Pattern.compile(PATTERN_NUMERO);
        // Busca que la fecha cumpla con la expresion regular
        Matcher matcher = pattern.matcher(documento.substring(0, 2));
        Matcher matcher2 = pattern2.matcher(documento.substring(2));
        return (matcher.matches()&&matcher2.matches());
    }

}
