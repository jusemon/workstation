package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 */
public class ObjDetalleUsuario {

    private int idDetalleUsuario;
    private String direccionUsuario;
    private String telefonoMovil;
    private int generoUsuario;
    private int estadoBeneficiario;

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

    public int getEstadoBeneficiario() {
        return estadoBeneficiario;
    }

    public void setEstadoBeneficiario(int estadoBeneficiario) {
        this.estadoBeneficiario = estadoBeneficiario;
    }

}//end ObjDetalleUsuario
