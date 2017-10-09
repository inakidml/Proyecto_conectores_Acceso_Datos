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
public class Equipo {

    private int id;
    private String nombre;
    private int anoFundacion;
    private String presidente;
    private String pabellon;
    private String patrocinador;

    private List<Jugador> jugadores;

    public Equipo() {
        jugadores = new ArrayList<>();
    }

    public Equipo(int id, String nombre, int anoFundacion, String presidente, String pabellon, String patrocinador) {
        jugadores = new ArrayList<>();
        this.id = id;
        this.nombre = nombre;
        this.anoFundacion = anoFundacion;
        this.presidente = presidente;
        this.pabellon = pabellon;
        this.patrocinador = patrocinador;
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", nombre=" + nombre + ", anoFundacion=" + anoFundacion + ", presidente=" + presidente + ", pabellon=" + pabellon + ", patrocinador=" + patrocinador + ", jugadores=" + jugadores + '}';
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
     * @return the anoFundacion
     */
    public int getAnoFundacion() {
        return anoFundacion;
    }

    /**
     * @param anoFundacion the anoFundacion to set
     */
    public void setAnoFundacion(int anoFundacion) {
        this.anoFundacion = anoFundacion;
    }

    /**
     * @return the presidente
     */
    public String getPresidente() {
        return presidente;
    }

    /**
     * @param presidente the presidente to set
     */
    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    /**
     * @return the pabellon
     */
    public String getPabellon() {
        return pabellon;
    }

    /**
     * @param pabellon the pabellon to set
     */
    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }

    /**
     * @return the patrocinador
     */
    public String getPatrocinador() {
        return patrocinador;
    }

    /**
     * @param patrocinador the patrocinador to set
     */
    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    /**
     * @return the jugadores
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

}
