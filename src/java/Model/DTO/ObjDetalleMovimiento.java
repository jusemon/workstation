package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:14 a. m.
 */
public class ObjDetalleMovimiento {

    private int idDetalleMovimiento;
    private int idArticulo;
    private int cantidadVendida;
    private int descuento = 0;
    private int totalDetalleMovimiento;
    private int idMovimiento;

    public int getIdDetalleMovimiento() {
        return idDetalleMovimiento;
    }

    public void setIdDetalleMovimiento(int idDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getTotalDetalleMovimiento() {
        return totalDetalleMovimiento;
    }

    public void setTotalDetalleMovimiento(int totalDetalleMovimiento) {
        this.totalDetalleMovimiento = totalDetalleMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

}//end ObjDetalleMovimiento
