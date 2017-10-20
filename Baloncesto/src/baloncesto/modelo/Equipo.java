/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 9fdam02
 */
public class Equipo {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";
    private String conector;

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
        this.jugadores = new ArrayList<>();
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
        try {
            switch (this.conector) {
                case db4oConector:
                    this.jugadores = DB4OInteface.getJugadores(new Jugador());
                    break;
                case mysqlConector:
                    this.jugadores = SQLInterface.getJugadores(conector);
                    break;
                case sqlServerConector:
                    this.jugadores = SQLInterface.getJugadores(conector);
                    break;
            }

            return jugadores;
        } catch (SQLException ex) {
            Logger.getLogger(Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jugadores;
    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

}
