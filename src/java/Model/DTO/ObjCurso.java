package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 * @created 08-may.-2015 12:13:14 a. m.
 */
public class ObjCurso {

    private int idCurso;
    private String nombreCurso;
    private int cantidadClases;
    private int horasPorClase;
    private int estadoCurso;
    private String descripcionCurso;
    private int precioCurso;
    private int idCategoriaCurso;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getCantidadClases() {
        return cantidadClases;
    }

    public void setCantidadClases(int cantidadClases) {
        this.cantidadClases = cantidadClases;
    }

    public int getHorasPorClase() {
        return horasPorClase;
    }

    public void setHorasPorClase(int horasPorClase) {
        this.horasPorClase = horasPorClase;
    }

    public int getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(int estadoCurso) {
        this.estadoCurso = estadoCurso;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    public int getPrecioCurso() {
        return precioCurso;
    }

    public void setPrecioCurso(int precioCurso) {
        this.precioCurso = precioCurso;
    }

    public int getIdCategoriaCurso() {
        return idCategoriaCurso;
    }

    public void setIdCategoriaCurso(int idCategoriaCurso) {
        this.idCategoriaCurso = idCategoriaCurso;
    }

}//end ObjCurso
