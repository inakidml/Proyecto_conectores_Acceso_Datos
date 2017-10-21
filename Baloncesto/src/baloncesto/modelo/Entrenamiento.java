/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 9fdam02
 */
public class Entrenamiento {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";
    private String conector;

    private Jugador jugador;
    private TipoEntrenamiento tipoEntrenamiento;
    private String fecha;
    private String duracion;

    public Entrenamiento() {
    }

    public Entrenamiento(Jugador jugador, TipoEntrenamiento tipoEntrenamiento, String fecha, String duracion) {
        this.jugador = jugador;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public Entrenamiento(String fecha, String duracion) {
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public TipoEntrenamiento getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(TipoEntrenamiento tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" + "jugador=" + jugador.getNombre() + ", tipoEntrenamiento=" + tipoEntrenamiento.getTipo() + ", fecha=" + fecha + ", duracion=" + duracion + '}';
    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public boolean save() {
        Boolean result = false;

        try {
            switch (this.conector) {
                case db4oConector:
                    result = DB4OInteface.insertEntrenamiento(this);
                    break;
                case mysqlConector:
                    result = SQLInterface.insertEntrenamiento(this, conector);
                    break;
                case sqlServerConector:
                    result = SQLInterface.insertEntrenamiento(this, conector);
                    break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
