/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

/**
 *
 * @author 9fdam02
 */
public class TipoIncidencia {
    private int id;
    private String tipo;
    private String sancion;
    private String descripcion;

    public TipoIncidencia() {
    }

    public TipoIncidencia(int id, String tipo, String sancion, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.sancion = sancion;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoIncidencia{" + "id=" + id + ", tipo=" + tipo + ", sancion=" + sancion + ", descripcion=" + descripcion + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the sancion
     */
    public String getSancion() {
        return sancion;
    }

    /**
     * @param sancion the sancion to set
     */
    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
