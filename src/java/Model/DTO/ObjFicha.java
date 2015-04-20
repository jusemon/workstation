/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

import java.sql.Date;

/**
 *
 * @author Administrador
 */
public class ObjFicha {

    private int idficha;
    private int idCurso;
    private int cuposDisponibles;
    private int precioFicha;
    private Date fechaInicio;
    private int estado;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrecioFicha() {
        return precioFicha;
    }

    public void setPrecioFicha(int precioFicha) {
        this.precioFicha = precioFicha;
    }

    public int getIdficha() {
        return idficha;
    }

    public void setIdficha(int idficha) {
        this.idficha = idficha;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}
