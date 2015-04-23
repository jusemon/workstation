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
public class ObjEstudiante {

    private String tipoDocumentoAcudiente;
    private String numeroDocumentoAcudiente = "null";
    private String tipoDocumento;
    private int numeroDocumento;
    private String fechaNacimiento;
    private int generoEstudiante;
    private String nombreEstudiante;
    private String apellidoEstudiente;
    private String direccionEstudiante;
    private String telefonoFijo;
    private String telefonoMovil;
    private String emailEstudiante;
    private int estadoEstudiante;

    public String getTipoDocumentoAcudiente() {
        return tipoDocumentoAcudiente;
    }

    public void setTipoDocumentoAcudiente(String tipoDocumentoAcudiente) {
        this.tipoDocumentoAcudiente = tipoDocumentoAcudiente;
    }

    public String getNumeroDocumentoAcudiente() {
        return numeroDocumentoAcudiente;
    }

    public void setNumeroDocumentoAcudiente(String numeroDocumentoAcudiente) {
        this.numeroDocumentoAcudiente = numeroDocumentoAcudiente;
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

    public int getGeneroEstudiante() {
        return generoEstudiante;
    }

    public void setGeneroEstudiante(int generoEstudiante) {
        this.generoEstudiante = generoEstudiante;
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

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiente() {
        return apellidoEstudiente;
    }

    public void setApellidoEstudiente(String apellidoEstudiente) {
        this.apellidoEstudiente = apellidoEstudiente;
    }

    public String getDireccionEstudiante() {
        return direccionEstudiante;
    }

    public void setDireccionEstudiante(String direccionEstudiante) {
        this.direccionEstudiante = direccionEstudiante;
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
    
    public String getEmailEstudiante() {
        return emailEstudiante;
    }

    public void setEmailEstudiante(String emailEstudiante) {
        this.emailEstudiante = emailEstudiante;
    }

    public int getEstadoEstudiante() {
        return estadoEstudiante;
    }

    public void setEstadoEstudiante(int estadoEstudiante) {
        this.estadoEstudiante = estadoEstudiante;
    }

}