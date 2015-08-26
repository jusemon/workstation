package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:15 a. m.
 */
public class ObjPreinscripcion {

    private int idPreinscripcion;
    private int estado;
    private String documentoUsuario;
    private int idCurso;

    public int getIdPreinscripcion() {
        return idPreinscripcion;
    }

    public void setIdPreinscripcion(int idPreinscripcion) {
        this.idPreinscripcion = idPreinscripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}    // end ObjPreinscripcion


//~ Formatted by Jindent --- http://www.jindent.com
