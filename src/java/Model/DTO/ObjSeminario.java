/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

/**
 *
 * @author Usuario
 */
public class ObjSeminario extends ObjCurso {

    private int cupoSeminario;
    private String fechaSeminario;

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

}
