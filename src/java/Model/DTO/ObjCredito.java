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
public class ObjCredito {
    private int idCledito;
    private int idCliente;
    private String fechaInicio;
    private double saldoInicial;
    private double saldoActual;
    private int estadoCredito;

    public int getIdCledito() {
        return idCledito;
    }

    public void setIdCledito(int idCledito) {
        this.idCledito = idCledito;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public int getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(int estadoCredito) {
        this.estadoCredito = estadoCredito;
    }
    
}
