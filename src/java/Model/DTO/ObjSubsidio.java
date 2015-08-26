package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:15 a. m.
 */
public class ObjSubsidio {

    private String fechaAsignacion = "";
    private int idSubsidio;
    private int valorSubsidio;
    private String nitEmpresa;
    private String documentoUsuario;

    public int getIdSubsidio() {
        return idSubsidio;
    }

    public void setIdSubsidio(int idSubsidio) {
        this.idSubsidio = idSubsidio;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public int getValorSubsidio() {
        return valorSubsidio;
    }

    public void setValorSubsidio(int valorSubsidio) {
        this.valorSubsidio = valorSubsidio;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }
}    // end ObjSubsidio


//~ Formatted by Jindent --- http://www.jindent.com
