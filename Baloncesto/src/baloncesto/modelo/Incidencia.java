/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import baloncesto.vista.VistaEntrenamientos;
import baloncesto.vista.VistaIncidencias;
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
public class Incidencia {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";
    private String conector;

    private int idJugador;
    //private TipoIncidencia tipoIncidencia;
    private int idTipo;
    //private Jugador jugador;
    private String Fecha;

    public Incidencia() {
    }

    public Incidencia(TipoIncidencia tipoIncidencia, Jugador jugador, String Fecha) {
        setTipoIncidencia(tipoIncidencia);
        setJugador(jugador);
        this.Fecha = Fecha;
    }

    public Incidencia(String Fecha) {
        this.Fecha = Fecha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idJugador;
        hash = 17 * hash + this.idTipo;
        hash = 17 * hash + Objects.hashCode(this.Fecha);
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
        final Incidencia other = (Incidencia) obj;
        if (this.idJugador != other.idJugador) {
            return false;
        }
        if (this.idTipo != other.idTipo) {
            return false;
        }
        if (!Objects.equals(this.Fecha, other.Fecha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "conector=" + conector + ", idJugador=" + idJugador + ", idTipo=" + idTipo + ", Fecha=" + Fecha + '}';
    }

    public TipoIncidencia getTipoIncidencia() {
        TipoIncidencia t = new TipoIncidencia(idTipo, "", "", "");
        List<TipoIncidencia> tiposIncidencias = new ArrayList<TipoIncidencia>();
        switch (conector) {
            case mysqlConector:
            case sqlServerConector: {
                try {
                    tiposIncidencias = SQLInterface.getTiposIncidencias(conector);
                } catch (SQLException ex) {
                    Logger.getLogger(VistaIncidencias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case db4oConector:
                tiposIncidencias = DB4OInteface.getTiposIncidencias(new TipoIncidencia());
                break;
            default:
                throw new AssertionError();
        }
        return tiposIncidencias.get(tiposIncidencias.indexOf(t));

    }

    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        if (tipoIncidencia != null) {
            this.idTipo = tipoIncidencia.getId();
        }
    }

    public Jugador getJugador() {

        Jugador j = new Jugador(idJugador, "", "", "", 0, 0, "", "");
        List<Jugador> jugadores = new ArrayList<Jugador>();
        switch (conector) {
            case mysqlConector:
            case sqlServerConector: {
                try {
                    jugadores = SQLInterface.getJugadores(conector);
                } catch (SQLException ex) {
                    Logger.getLogger(VistaEntrenamientos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case db4oConector:
                jugadores = DB4OInteface.getJugadores(new Jugador());
                break;
            default:
                throw new AssertionError();
        }
        return jugadores.get(jugadores.indexOf(j));
    }

    public void setJugador(Jugador jugador) {
        if (jugador != null) {
            this.idJugador = jugador.getId();
        }
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
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
                    result = DB4OInteface.insertIncidencia(this);
                    break;
                case mysqlConector:
                    result = SQLInterface.insertIncidencia(this, conector);
                    break;
                case sqlServerConector:
                    result = SQLInterface.insertIncidencia(this, conector);
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
                    result = DB4OInteface.deleteIncidencia(this);
                    break;
                case mysqlConector:
                    result = SQLInterface.deleteIncidencia(this.getTipoIncidencia().getId(), this.getJugador().getId(), this.Fecha, this.conector);
                    break;
                case sqlServerConector:
                    result = SQLInterface.deleteIncidencia(this.getTipoIncidencia().getId(), this.getJugador().getId(), this.Fecha, this.conector);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Incidencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
