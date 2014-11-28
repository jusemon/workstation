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
public class ObjInscripcion {
    private int idInscripcion;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdSeminario() {
        return idSeminario;
    }

    public void setIdSeminario(int idSeminario) {
        this.idSeminario = idSeminario;
    }

    public String getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(String fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    private int idSeminario;
    private String fechaAsistencia;
    private int idVenta;
}
