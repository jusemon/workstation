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
public class ObjCliente {

    private int tipoDocumentoAcudiente;
    private String numeroDocumentoAcudiente;
    private String idCliente;
    private int tipoCliente;
    private String tipoDocumento;
    private int numeroDocumento;
    private String fechaNacimiento;
    private int generoCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String direccionCliente;
    private String telefonoFijo;
    private String telefonoMovil;
    private String emailCliente;
    private int estadoCliente;

    public int getTipoDocumentoAcudiente() {
        return tipoDocumentoAcudiente;
    }

    public void setTipoDocumentoAcudiente(int tipoDocumentoAcudiente) {
        this.tipoDocumentoAcudiente = tipoDocumentoAcudiente;
    }

    public String getNumeroDocumentoAcudiente() {
        return numeroDocumentoAcudiente;
    }

    public void setNumeroDocumentoAcudiente(String numeroDocumentoAcudiente) {
        this.numeroDocumentoAcudiente = numeroDocumentoAcudiente;
    }

    
    
    public String getIdCliente() {
        return idCliente;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public int getGeneroCliente() {
        return generoCliente;
    }

    public void setGeneroCliente(int generoCliente) {
        this.generoCliente = generoCliente;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }
    
    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(int estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

}
