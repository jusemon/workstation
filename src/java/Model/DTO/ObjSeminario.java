/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

/**
 *
 * @author Administrador
 */
public class ObjSeminario {
    private int idSeminario;
    private String nombreSeminario;

    public int getIdSeminario() {
        return idSeminario;
    }

    public void setIdSeminario(int idSeminario) {
        this.idSeminario = idSeminario;
    }

    public String getNombreSeminario() {
        return nombreSeminario;
    }

    public void setNombreSeminario(String nombreSeminario) {
        this.nombreSeminario = nombreSeminario;
    }

    public int getDuracionSeminario() {
        return duracionSeminario;
    }

    public void setDuracionSeminario(int duracionSeminario) {
        this.duracionSeminario = duracionSeminario;
    }

    public int getPrecioSeminario() {
        return precioSeminario;
    }

    public void setPrecioSeminario(int precioSeminario) {
        this.precioSeminario = precioSeminario;
    }

    public int getEstadoSeminario() {
        return estadoSeminario;
    }

    public void setEstadoSeminario(int estadoSeminario) {
        this.estadoSeminario = estadoSeminario;
    }
    private int duracionSeminario;
    private int precioSeminario;
    private int estadoSeminario;
            
    
    
}
