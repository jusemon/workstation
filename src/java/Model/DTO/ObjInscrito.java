
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

/**
 *
 * @author Administrador
 */
public class ObjInscrito {

    private int idincrito;
    private String documento;
    private String nombres;
    private String telefono;
    private String correo;
    private int idseminario;

    public int getIdincrito() {
        return idincrito;
    }

    public void setIdincrito(int idincrito) {
        this.idincrito = idincrito;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdseminario() {
        return idseminario;
    }

    public void setIdseminario(int idseminario) {
        this.idseminario = idseminario;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
