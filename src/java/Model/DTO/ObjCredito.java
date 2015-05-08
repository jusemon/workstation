package Model.DTO;


/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:14 a. m.
 */
public class ObjCredito {

    private int idCredito;
    private String fechaInicio = "";
    private int saldoInicial = 0;
    private int saldoActual = 0;
    private int estadoCredito;
    private int idCategoriaCredito;
    private String documentoUsuario;

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(int saldoActual) {
        this.saldoActual = saldoActual;
    }

    public int getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(int estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    public int getIdCategoriaCredito() {
        return idCategoriaCredito;
    }

    public void setIdCategoriaCredito(int idCategoriaCredito) {
        this.idCategoriaCredito = idCategoriaCredito;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

}//end ObjCredito
