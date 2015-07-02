package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 */
public class ObjAbono {

    private int idAbono;
    private int valorAbono = 0;
    private String fechaPago = "";
    private int idCredito;

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public int getValorAbono() {
        return valorAbono;
    }

    public void setValorAbono(int valorAbono) {
        this.valorAbono = valorAbono;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

}//end ObjAbono
