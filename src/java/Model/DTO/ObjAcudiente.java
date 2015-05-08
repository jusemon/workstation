package Model.DTO;

import java.sql.Date;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:13 a. m.
 */
public class ObjAcudiente {

    private String documentoAcudiente;
    private String nombreAcudiente;
    private String telefonoAcudiente;
    private Date fechaNacimiento;

    public String getDocumentoAcudiente() {
        return documentoAcudiente;
    }

    public void setDocumentoAcudiente(String documentoAcudiente) {
        this.documentoAcudiente = documentoAcudiente;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public String getTelefonoAcudiente() {
        return telefonoAcudiente;
    }

    public void setTelefonoAcudiente(String telefonoAcudiente) {
        this.telefonoAcudiente = telefonoAcudiente;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}//end ObjAcudiente
