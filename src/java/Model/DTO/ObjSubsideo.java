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
public class ObjSubsideo {
    private int idSubsideo;
    private String nitEmpresa;
    private int idCliente;
    private String fechaAsignacion;
    private double valorAsignacion;

    public int getIdSubsideo() {
        return idSubsideo;
    }

    public void setIdSubsideo(int idSubsideo) {
        this.idSubsideo = idSubsideo;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public double getValorAsignacion() {
        return valorAsignacion;
    }

    public void setValorAsignacion(double valorAsignacion) {
        this.valorAsignacion = valorAsignacion;
    }
    
}
