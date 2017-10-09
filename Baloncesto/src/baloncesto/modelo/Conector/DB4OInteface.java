/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;

/**
 *
 * @author 9fdam04
 */
public class DB4OInteface extends Conector {

    public static Equipo getEquipo(Equipo query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        Equipo e = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            //Devolvemos el equipo
            e = result.next();
        }

        //Cerramos la conexion
        db.close();

        return e;
    }

    public static Jugador getJugador(Jugador query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        Jugador j = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            j = result.next();
        }

        //Cerramos la conexion
        db.close();

        return j;
    }

    public static ArrayList<Jugador> getJugadores(Jugador query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de jugadores
        ArrayList<Jugador> jList = new ArrayList<Jugador>;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Comprobamos si hay resultados
        if (result.size() == 0) {
            //Cerramos la conexion
            db.close();

            return null;
        }

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el jugador
            Jugador j = result.next();

            //Añadimos el jugador al arraylist
            jList.add(j);
        }

        //Cerramos la conexion
        db.close();

        //Devolvemos la lista
        return jList;
    }

    public static void storeJugador(Jugador j) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos el jugador
        db.store(j);

        //cerramos el jugador
        db.close();
    }

    public static void updateJugador(Jugador query, Jugador data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el jugador
            Jugador j = result.next();

            //Indicamos los nuevos datos del jugador
            j.setNombre(data.getNombre() != null ? data.getNombre() : j.getNombre());
            j.setApellido1(data.getApellido1() != null ? data.getApellido1() : j.getApellido1());
            j.setApellido2(data.getApellido2() != null ? data.getApellido2() : j.getApellido2());
            j.setAltura(data.getAltura() != null ? data.getAltura() : j.getAltura());
            j.setPeso(data.getPeso() != null ? data.getPeso() : j.getPeso());
            j.setPosicion(data.getPosicion() != null ? data.getPosicion() : j.getPosicion());
            j.setDescripcion(data.getDescripcion() != null ? data.getDescripcion() : j.getDescripcion());
            j.setEquipo(data.getEquipo != null ? data.getEquipo() : j.getEquipo());

            db.store(j);
        }

        //Cerramos la conexion
        db.close();

    }

    public static void deleteJugador(Jugador query) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el jugador
            Jugador j = result.next();

            //Borramos el jugador
            db.delete(j);
        }

        //Cerramos la bd
        db.close();
    }

}
