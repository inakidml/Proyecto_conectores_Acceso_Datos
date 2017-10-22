/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import baloncesto.vista.VistaEntrenamientos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private int idTipo;
    //private TipoEntrenamiento tipoEntrenamiento;
    private String fecha;
    private String duracion;

    public Entrenamiento() {
    }

    public Entrenamiento(Jugador jugador, TipoEntrenamiento tipoEntrenamiento, String fecha, String duracion) {
        this.jugador = jugador;
        setTipoEntrenamiento(tipoEntrenamiento);
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public Entrenamiento(String fecha, String duracion) {
        this.fecha = fecha;
        this.duracion = duracion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.jugador);
        hash = 47 * hash + this.idTipo;
        hash = 47 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrenamiento other = (Entrenamiento) obj;
        if (this.idTipo != other.idTipo) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.jugador, other.jugador)) {
            return false;
        }
        return true;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public TipoEntrenamiento getTipoEntrenamiento() {

        TipoEntrenamiento t = new TipoEntrenamiento(idTipo, "", "");
        List<TipoEntrenamiento> tiposEntrenamientos = new ArrayList<TipoEntrenamiento>();
        switch (conector) {
            case mysqlConector:
            case sqlServerConector: {
                try {
                    tiposEntrenamientos = SQLInterface.getTipoEntrenamientos(conector);
                } catch (SQLException ex) {
                    Logger.getLogger(VistaEntrenamientos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case db4oConector:
                tiposEntrenamientos = DB4OInteface.getTiposEntrenamientos(new TipoEntrenamiento());
                break;
            default:
                throw new AssertionError();
        }
        return tiposEntrenamientos.get(tiposEntrenamientos.indexOf(t));
    }

    public void setTipoEntrenamiento(TipoEntrenamiento tipoEntrenamiento) {
        if (tipoEntrenamiento != null) {
            this.idTipo = tipoEntrenamiento.getId();
        }
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
        return "Entrenamiento{" + "conector=" + conector + ", jugador=" + jugador + ", idTipo=" + idTipo + ", fecha=" + fecha + ", duracion=" + duracion + '}';
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

    public boolean delete() {
        boolean result = false;

        try {
            switch (this.conector) {
                case db4oConector:
                    result = DB4OInteface.deleteEntrenamiento(this);
                    break;
                case mysqlConector:
                    result = SQLInterface.deleteEntrenamiento(this.getJugador().getId(), this.getTipoEntrenamiento().getId(), this.fecha, this.conector);
                    break;
                case sqlServerConector:
                    result = SQLInterface.deleteEntrenamiento(this.getJugador().getId(), this.getTipoEntrenamiento().getId(), this.fecha, this.conector);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Entrenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
