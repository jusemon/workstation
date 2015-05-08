package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:14 a. m.
 */
public class ObjDetalleUsuario {

    private int idDetalleUsuario;
    private String direccionUsuario;
    private String telefonoFijo;
    private String telefonoMovil;
    private int generoUsuario;

    public int getIdDetalleUsuario() {
        return idDetalleUsuario;
    }

    public void setIdDetalleUsuario(int idDetalleUsuario) {
        this.idDetalleUsuario = idDetalleUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public int getGeneroUsuario() {
        return generoUsuario;
    }

    public void setGeneroUsuario(int generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

}//end ObjDetalleUsuario
