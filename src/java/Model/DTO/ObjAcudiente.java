package Model.DTO;

//~--- JDK imports ------------------------------------------------------------
import java.sql.Date;

/**
 * @author Zack
 * @version 1.0
 */
public class ObjAcudiente {

    private String documentoAcudiente;
    private String nombreAcudiente;
    private String telefonoAcudiente;
    private String fechaNacimiento;

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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}    // end ObjAcudiente


//~ Formatted by Jindent --- http://www.jindent.com
