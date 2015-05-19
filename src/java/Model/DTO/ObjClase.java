package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:13 a. m.
 */
public class ObjClase {

    private int idClase;
    private String fecha;
    private int estadoPago;
    private int estadoAsistencia;
    private int creditoCreado;
    private float precioClase;
    private int idCurso;
    private String documentoUsuario;

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(int estadoPago) {
        this.estadoPago = estadoPago;
    }

    public int getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(int estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public int getCreditoCreado() {
        return creditoCreado;
    }

    public void setCreditoCreado(int creditoCreado) {
        this.creditoCreado = creditoCreado;
    }

    public float getPrecioClase() {
        return precioClase;
    }

    public void setPrecioClase(float precioClase) {
        this.precioClase = precioClase;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

}//end ObjClase
