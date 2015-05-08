package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:13 a. m.
 */
public class ObjArticulo {

    private int idArticulo;
    private int idCategoriaArticulo;
    private String descripcionArticulo;
    private int cantidadDisponible;
    private int precioCompra;
    private int precioVenta;

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdCategoriaArticulo() {
        return idCategoriaArticulo;
    }

    public void setIdCategoriaArticulo(int idCategoriaArticulo) {
        this.idCategoriaArticulo = idCategoriaArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

}//end ObjArticulo
