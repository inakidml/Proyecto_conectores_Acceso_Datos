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
   
      private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o"; 
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

}
