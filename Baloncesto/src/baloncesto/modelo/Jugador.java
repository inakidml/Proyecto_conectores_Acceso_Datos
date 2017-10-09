/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 9fdam02
 */
public class Jugador {

    private int id;
    private String nombre;
    private String apellido;
    private String apellido2;
    private float altura;
    private float peso;
    private String posicion;
    private String descripcion;
    private Equipo equipo;

    private List<Entrenamiento> entrenamientos;
    private List<Incidencia> incidencias;

    public Jugador() {
        entrenamientos = new ArrayList<>();
        incidencias = new ArrayList<>();
    }

    public Jugador(int id, String nombre, String apellido, String apellido2, float altura, float peso, String posicion, String descripcion, Equipo equipo) {
        entrenamientos = new ArrayList<>();
        incidencias = new ArrayList<>();
        
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.altura = altura;
        this.peso = peso;
        this.posicion = posicion;
        this.descripcion = descripcion;
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", apellido2=" + apellido2 + ", altura=" + altura + ", peso=" + peso + ", posicion=" + posicion + ", descripcion=" + descripcion + ", equipo=" + equipo + ", entrenamientos=" + entrenamientos + ", incidencias=" + incidencias + '}';
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the altura
     */
    public float getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * @return the posicion
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


    /**
     * @return the entrenamientos
     */
    public List<Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }

    /**
     * @return the incidencias
     */
    public List<Incidencia> getIncidencias() {
        return incidencias;
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
