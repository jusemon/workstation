package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:15 a. m.
 */
public class ObjMovimiento {

    private int idMovimiento;
    private String fechaMovimiento = "";
    private int totalMovimiento;
    private int idtipoMovimiento;
    private String documentoUsuario;

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getTotalMovimiento() {
        return totalMovimiento;
    }

    public void setTotalMovimiento(int totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }

    public int getIdtipoMovimiento() {
        return idtipoMovimiento;
    }

    public void setIdtipoMovimiento(int idtipoMovimiento) {
        this.idtipoMovimiento = idtipoMovimiento;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

}//end ObjMovimiento
