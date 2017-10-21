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

}
