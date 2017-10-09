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
public class Entrenamiento {
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

 
    
}
