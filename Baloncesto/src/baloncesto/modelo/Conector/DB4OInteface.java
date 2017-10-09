/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;

import baloncesto.modelo.Entrenamiento;
import baloncesto.modelo.Incidencia;
import baloncesto.modelo.Jugador;
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
        ArrayList<Jugador> jList = new ArrayList<Jugador>();

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

    public static void insertJugador(Jugador j) {
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
            Jugador j = (Jugador) result.next();

            //Indicamos los nuevos datos del jugador
            j.setNombre(data.getNombre() != null ? data.getNombre() : j.getNombre());
            j.setApellido(data.getApellido() != null ? data.getApellido() : j.getApellido());
            j.setApellido2(data.getApellido2() != null ? data.getApellido2() : j.getApellido2());
            j.setAltura(data.getAltura() != 0 ? data.getAltura() : j.getAltura());
            j.setPeso(data.getPeso() != 0 ? data.getPeso() : j.getPeso());
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
            Jugador j = (Jugador) result.next();

            //Borramos el jugador
            db.delete(j);
        }

        //Cerramos la bd
        db.close();
    }

    public static Incidencia getIncidencia(Incidencia query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        Incidencia i = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            i = (Incidencia) result.next();
        }

        //Cerramos la conexion
        db.close();

        return i;
    }
    
        public static ArrayList<Incidencia> getIncidencias(Incidencia query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de incidencias
        ArrayList<Incidencia> iList = new ArrayList<Incidencia>();

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
            //Obtenemos la incidencia
            Incidencia i = (Incidencia) result.next();

            //Añadimos la incidencia al arraylist
            iList.add(i);
        }

        //Cerramos la conexion
        db.close();

        //Devolvemos la lista
        return iList;
    }

    public static void insertIncidencia(Incidencia i) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos la incidencia
        db.store(i);

        //cerramos la conexion
        db.close();
    }

    public static void updateIncidencia(Incidencia query, Incidencia data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el jugador
            Incidencia i = (Incidencia) result.next();

            //Indicamos los nuevos datos del la incidencia

            db.store(i);
        }

        //Cerramos la conexion
        db.close();
    }

    public static void deleteIncidencia(Incidencia query) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos la incidencia
            Incidencia i = (Incidencia) result.next();

            //Borramos el jugador
            db.delete(i);
        }

        //Cerramos la bd
        db.close();
    }
    
       public static Entrenamiento getEntrenamiento(Entrenamiento query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        Entrenamiento e = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            e = (Entrenamiento) result.next();
        }

        //Cerramos la conexion
        db.close();

        return e;
    }
    
        public static ArrayList<Entrenamiento> getEntrenamientos(Entrenamiento query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de incidencias
        ArrayList<Entrenamiento> eList = new ArrayList<Entrenamiento>();

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
            //Obtenemos la incidencia
            Entrenamiento e = (Entrenamiento) result.next();

            //Añadimos la incidencia al arraylist
            eList.add(e);
        }

        //Cerramos la conexion
        db.close();

        //Devolvemos la lista
        return eList;
    }

    public static void insertEntrenamiento(Entrenamiento e) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos la incidencia
        db.store(e);

        //cerramos la conexion
        db.close();
    }

    public static void updateEntrenamiento(Entrenamiento query, Entrenamiento data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el jugador
            Entrenamiento e = (Entrenamiento) result.next();

            //Indicamos los nuevos datos del la incidencia

            db.store(e);
        }

        //Cerramos la conexion
        db.close();
    }

    public static void deleteEntrenamiento(Entrenamiento query) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos la incidencia
            Entrenamiento e = (Entrenamiento) result.next();

            //Borramos el jugador
            db.delete(e);
        }

        //Cerramos la bd
        db.close();
    }

}
