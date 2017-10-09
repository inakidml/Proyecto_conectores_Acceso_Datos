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
public class Incidencia {

    private int id;
    private String tipo;
    private String sancion;
    private String descripcion;
    private String fecha;
    private String duracion;

    public Incidencia() {
    }

    
    public Incidencia(int id, String tipo, String sancion, String descripcion, String fecha, String duracion) {
        this.id = id;
        this.tipo = tipo;
        this.sancion = sancion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.duracion = duracion;
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

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

}
