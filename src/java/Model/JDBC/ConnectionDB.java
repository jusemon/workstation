/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrador
 */
public class ConnectionDB {

    protected Connection connection;
    protected Statement stmt;
    private final String login = "root";
    private final String password = "";
    private final String database = "dbworkstationsoftware";
    private final String port = "3306";
    private final String url = "jdbc:mysql://127.0.0.1:" + port + "/" + database;
    private String errorString;

    /**
     * Constructor de ConnectionDB
     */
    public ConnectionDB() {
        getConnection();
    }

    public String getUrlConnection() {
        return url;
    }

    /**
     * Permite retornar la conexión
     *
     * @return La conexion
     */
    public Connection getConnection() {
        connection = null;
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexión
            connection = DriverManager.getConnection(this.url, this.login, this.password);
            stmt = connection.createStatement();
            System.out.println("Conectado");
        } catch (SQLException e) {
            errorString = "Ha ocurrido un error inesperado al conectarse con la base de datos(DB).";
            System.out.println(errorString);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            errorString = "Ha ocurrido un error inesperado al conectarse con la base de datos(driver).";
            System.out.println(errorString);
            System.out.println(e);
        } catch (Exception e) {
            errorString = "Ha ocurrido un error inesperado al conectarse con la base de datos(Java).";
            System.out.println(errorString);
            System.out.println(e);
        }

        return connection;
    }

    public void Signout() {
        try {
            stmt.close();
            connection.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            errorString = "Ha ocurrido un error inesperado al desconectarse de la base de datos.";
            System.out.println(errorString);
        }
    }

    public Statement getStmt() {
        return this.stmt;
    }
}
