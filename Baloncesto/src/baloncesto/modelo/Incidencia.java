/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import java.sql.SQLException;
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

    private TipoIncidencia tipoIncidencia;
    private Jugador jugador;
    private String Fecha;

    public Incidencia() {
    }

    public Incidencia(TipoIncidencia tipoIncidencia, Jugador jugador, String Fecha) {
        this.tipoIncidencia = tipoIncidencia;
        this.jugador = jugador;
        this.Fecha = Fecha;
    }

    public Incidencia(String Fecha) {
        this.Fecha = Fecha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.tipoIncidencia);
        hash = 59 * hash + Objects.hashCode(this.jugador);
        hash = 59 * hash + Objects.hashCode(this.Fecha);
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
        if (!Objects.equals(this.Fecha, other.Fecha)) {
            return false;
        }
        if (!Objects.equals(this.tipoIncidencia, other.tipoIncidencia)) {
            return false;
        }
        if (!Objects.equals(this.jugador, other.jugador)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "conector=" + conector + ", tipoIncidencia=" + tipoIncidencia + ", jugador=" + jugador + ", Fecha=" + Fecha + '}';
    }

    public TipoIncidencia getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
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
