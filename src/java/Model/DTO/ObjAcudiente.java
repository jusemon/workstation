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
public class ObjAcudiente {
    private int tipoDocumento;
    private String numeroDocumento;
    private String nombreAcudiante;
    private String telefonoAcudiante;

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreAcudiante() {
        return nombreAcudiante;
    }

    public void setNombreAcudiante(String nombreAcudiante) {
        this.nombreAcudiante = nombreAcudiante;
    }

    public String getTelefonoAcudiante() {
        return telefonoAcudiante;
    }

    public void setTelefonoAcudiante(String telefonoAcudiante) {
        this.telefonoAcudiante = telefonoAcudiante;
    }
   
}
