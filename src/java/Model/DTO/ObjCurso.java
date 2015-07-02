package Model.DTO;

/**
 * @author Zack
 * @version 1.0
 */
public class ObjCurso {

    private int idCurso;
    private String nombreCurso;
    private int cantidadClases;
    private int horasPorClase;
    private int estadoCurso;
    private String descripcionCurso;
    private int precioCurso;
    private int cupoSeminario;
    private String fechaSeminario;
    private int idCategoriaCurso;

    public int getCupoSeminario() {
        return cupoSeminario;
    }

    public void setCupoSeminario(int cupoSeminario) {
        this.cupoSeminario = cupoSeminario;
    }

    public String getFechaSeminario() {
        return fechaSeminario;
    }

    public void setFechaSeminario(String fechaSeminario) {
        this.fechaSeminario = fechaSeminario;
    }

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
