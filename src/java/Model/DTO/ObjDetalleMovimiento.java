package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 */
public class ObjDetalleMovimiento {

    private int idDetalleMovimiento;
    private int idArticulo;
    private int cantidad;
    private int descuento = 0;
    private int totalDetalleMovimiento;
    private int idMovimiento;
    private int precioArticulo;

    public int getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(int precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
