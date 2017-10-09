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
    int id;
    String tipo;
    String descripcion;

    
    public Entrenamiento() {
    }

    public Entrenamiento(int id, String tipo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Entrenamiento{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + '}';
    }
    
}
