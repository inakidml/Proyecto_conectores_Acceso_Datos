/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;

import baloncesto.modelo.Entrenamiento;
import baloncesto.modelo.Equipo;
import baloncesto.modelo.Incidencia;
import baloncesto.modelo.Jugador;
import baloncesto.modelo.TipoEntrenamiento;
import baloncesto.modelo.TipoIncidencia;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;

/**
 *
 * @author 9fdam04
 */
public class DB4OInteface extends Conector {

    /**
     * Obtener el equipo
     *
     * @param query Equipo Query para realizar la busqueda
     * @return Equipo|null Devuelve el equipo que coincida con la query si no
     * coincide devuelve un null
     */
    public static Equipo getEquipo(Equipo query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        Equipo e = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            //Devolvemos el equipo
            e = (Equipo) result.next();
        }

        //Cerramos la conexion
        db.close();

        if (e != null) {
            e.setConector("db4o");
        }

        return e;
    }

    /**
     * Insertar el equipo
     *
     * @param e Equipo Equipo a insertar
     */
    public static void insertEquipo(Equipo e) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos el equipo
        db.store(e);

        //cerramos el equipo
        db.close();
        
        //Indicamos el conector con el que se ha insertado
        e.setConector("db4o");
    }

    /**
     * Modificar el equipo
     *
     * @param query Equipo Equipo que deseamos modificar
     * @param data Equipo Nuevos datos del equipo
     */
    public static void updateEquipo(Equipo query, Equipo data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el equipo
            Equipo e = (Equipo) result.next();

            //Indicamos los nuevos datos del equipo
            e.setNombre(data.getNombre() != null ? data.getNombre() : e.getNombre());
            e.setAnoFundacion(data.getAnoFundacion() != 0 ? data.getAnoFundacion() : e.getAnoFundacion());
            e.setPresidente(data.getPresidente() != null ? data.getPresidente() : e.getPresidente());
            e.setPabellon(data.getPabellon() != null ? data.getPabellon() : e.getPabellon());
            e.setPatrocinador(data.getPatrocinador() != null ? data.getPatrocinador() : e.getPatrocinador());

            db.store(e);
            
            //Indicamos el conector con el que se ha actualizado
            e.setConector("db4o");
        }

        //Cerramos la conexion
        db.close();
    }

    /**
     * Borrar el equipo
     *
     * @param query Equipo Query para indicar el equipo que queremos eliminar
     */
    public static void deleteEquipo(Equipo query) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el equipo
            Equipo e = (Equipo) result.next();

            //Borramos el equipo
            db.delete(e);
        }

        //Cerramos la bd
        db.close();
    }

    public static Jugador getJugador(Jugador query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        Jugador j = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            j = (Jugador) result.next();
        }

        //Cerramos la conexion
        db.close();
        
        if (j != null) {
            j.setConector("db4o");
        }

        return j;
    }

    public static ArrayList<Jugador> getJugadores(Jugador query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de jugadores
        ArrayList<Jugador> jList = new ArrayList<>();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Comprobamos si hay resultados
        if (result.isEmpty()) {
            //Cerramos la conexion
            db.close();

            return null;
        }

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el jugador
            Jugador j = (Jugador) result.next();
            
            if (j != null) {
                j.setConector("db4o");
                //Añadimos el jugador al arraylist
                jList.add(j);
            }            
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
        
        //Indicamos el conector con el que se ha insertado
        j.setConector("db4o");
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
            j.setEquipo(data.getEquipo() != null ? data.getEquipo() : j.getEquipo());

            db.store(j);
            
            //Indicamos el conector con el que se ha actualizado
            j.setConector("db4o");
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
        
        if (i != null) {
            i.setConector("db4o");
        }

        return i;
    }

    public static ArrayList<Incidencia> getIncidencias(Incidencia query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de incidencias
        ArrayList<Incidencia> iList = new ArrayList<>();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Comprobamos si hay resultados
        if (result.isEmpty()) {
            //Cerramos la conexion
            db.close();

            return null;
        }

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos la incidencia
            Incidencia i = (Incidencia) result.next();
            
            if (i != null) {
                i.setConector("db4o");
                //Añadimos la incidencia al arraylist
                iList.add(i);
            }            
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
        
        //Indicamos el conector con el que se ha insertado
        i.setConector("db4o");
    }

    public static void updateIncidencia(Incidencia query, Incidencia data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos la incidencia
            Incidencia i = (Incidencia) result.next();

            //Indicamos los nuevos datos del la incidencia
            i.setFecha(data.getFecha() != null ? data.getFecha() : i.getFecha());

            db.store(i);
            
            //Indicamos el conector con el que se ha actualizado
            i.setConector("db4o");
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

            //Borramos la incidencia
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
        
        if (e != null) {
            e.setConector("db4o");           
        }

        return e;
    }

    public static ArrayList<Entrenamiento> getEntrenamientos(Entrenamiento query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de entrenamientos
        ArrayList<Entrenamiento> eList = new ArrayList<>();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Comprobamos si hay resultados
        if (result.isEmpty()) {
            //Cerramos la conexion
            db.close();

            return null;
        }

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el entranamiento
            Entrenamiento e = (Entrenamiento) result.next();
            
            if (e != null) {
                e.setConector("db4o");
                //Añadimos el entrenamiento al arraylist
                eList.add(e);
            }            
        }

        //Cerramos la conexion
        db.close();

        //Devolvemos la lista
        return eList;
    }

    public static void insertEntrenamiento(Entrenamiento e) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos le entrenamiento
        db.store(e);

        //cerramos la conexion
        db.close();
        
        //Indicamos el conector con el que se ha insertado
        e.setConector("db4o");
    }

    public static void updateEntrenamiento(Entrenamiento query, Entrenamiento data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el entrenamiento
            Entrenamiento e = (Entrenamiento) result.next();

            //Indicamos los nuevos datos del la incidencia
            e.setFecha(data.getFecha() != null ? data.getFecha() : e.getFecha());
            e.setDuracion(data.getDuracion() != null ? data.getDuracion() : e.getDuracion());

            db.store(e);
            
            //Indicamos el conector con el que se ha actualizado
            e.setConector("db4o");
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

            //Borramos el entrenamiento
            db.delete(e);
        }

        //Cerramos la bd
        db.close();
    }

    public static TipoIncidencia getTipoIncidencia(TipoIncidencia query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        TipoIncidencia ti = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            ti = (TipoIncidencia) result.next();
        }

        //Cerramos la conexion
        db.close();
        
        if (ti != null) {
            ti.setConector("db4o");            
        }  

        return ti;
    }

    public static ArrayList<TipoIncidencia> getTiposIncidencias(TipoIncidencia query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de tipos incidencias
        ArrayList<TipoIncidencia> tiList = new ArrayList<>();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Comprobamos si hay resultados
        if (result.isEmpty()) {
            //Cerramos la conexion
            db.close();

            return null;
        }

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos la incidencia
            TipoIncidencia ti = (TipoIncidencia) result.next();

            if (ti != null) {
                ti.setConector("db4o");            
                //Añadimos el tipo incidencia al arraylist
                tiList.add(ti);
            }            
        }

        //Cerramos la conexion
        db.close();

        //Devolvemos la lista
        return tiList;
    }

    public static void insertTipoIncidencia(TipoIncidencia ti) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos la tipo de incidencia
        db.store(ti);

        //cerramos la conexion
        db.close();
        
        //Indicamos el conector con el que se ha insertado
        ti.setConector("db4o");
    }

    public static void updateTipoIncidencia(TipoIncidencia query, TipoIncidencia data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el tipo de incidencia
            TipoIncidencia ti = (TipoIncidencia) result.next();

            //Indicamos los nuevos datos del tipo incidencia
            ti.setTipo(data.getTipo() != null ? data.getTipo() : ti.getTipo());
            ti.setSancion(data.getSancion() != null ? data.getSancion() : ti.getSancion());
            ti.setDescripcion(data.getDescripcion() != null ? data.getDescripcion() : ti.getDescripcion());

            db.store(ti);
            
            //Indicamos el conector con el que se ha actualizado
            ti.setConector("db4o");
        }

        //Cerramos la conexion
        db.close();
    }

    public static void deleteTipoIncidencia(TipoIncidencia query) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el tipo incidencia
            TipoIncidencia ti = (TipoIncidencia) result.next();

            //Borramos el tipo de incidencia
            db.delete(ti);
        }

        //Cerramos la bd
        db.close();
    }

    public static TipoEntrenamiento getTipoEntrenamiento(TipoEntrenamiento query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        TipoEntrenamiento te = null;

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        if (result.size() > 0) {
            te = (TipoEntrenamiento) result.next();
        }

        //Cerramos la conexion
        db.close();
        
        if (te != null) {
            te.setConector("db4o");            
        }

        return te;
    }

    public static ArrayList<TipoEntrenamiento> getTiposEntrenamientos(TipoEntrenamiento query) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Creamoa un array list de tipos de entrenamientos
        ArrayList<TipoEntrenamiento> teList = new ArrayList<>();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Comprobamos si hay resultados
        if (result.isEmpty()) {
            //Cerramos la conexion
            db.close();

            return null;
        }

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el tipo de entrenamiento
            TipoEntrenamiento te = (TipoEntrenamiento) result.next();
            if (te != null) {
                te.setConector("db4o");            
                //Añadimos el tipo de entrenamiento al arraylist
                teList.add(te);
            }            
        }

        //Cerramos la conexion
        db.close();

        //Devolvemos la lista
        return teList;
    }

    public static void insertTipoEntrenamiento(TipoEntrenamiento te) {
        //Abrimos la conexion
        ObjectContainer db = getDb_4o();

        //Guardamos la tipo de entrenamiento
        db.store(te);

        //cerramos la conexion
        db.close();
        
        //Indicamos el conector con el que se ha insertado
        te.setConector("db4o");
    }

    public static void updateTipoEntrenamiento(TipoEntrenamiento query, TipoEntrenamiento data) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el tipo de entrenamiento
            TipoEntrenamiento te = (TipoEntrenamiento) result.next();

            //Indicamos los nuevos datos del tipo de entrenamiento
            te.setTipo(data.getTipo() != null ? data.getTipo() : te.getTipo());
            te.setDescripcion(data.getDescripcion() != null ? data.getDescripcion() : te.getDescripcion());

            db.store(te);
            
            //Indicamos el conector con el que se ha actualizado
            te.setConector("db4o");
        }

        //Cerramos la conexion
        db.close();
    }

    public static void deleteTipoEntrenamiento(TipoEntrenamiento query) {
        //Abrimos los conexion
        ObjectContainer db = getDb_4o();

        //Obtenemos el resultado
        ObjectSet result = db.queryByExample(query);

        //Recorremos el resultado
        while (result.hasNext()) {
            //Obtenemos el tipo de entrenamiento
            TipoEntrenamiento te = (TipoEntrenamiento) result.next();

            //Borramos el tipo de entrenamiento
            db.delete(te);
        }

        //Cerramos la bd
        db.close();
    }

    public static void setDBData() {
        //Obtenemos el equipo
        Equipo e = getEquipo(new Equipo());

        //Comprobamos si no hay un equipo
        if (e == null) {
            //Creamos el equipo
            e = new Equipo(1, "Gran Canaria", 1963, "Enrique Moreno", "	Gran Canaria Arena", "Herbalife");

            //Añadimos el equipo
            insertEquipo(e);
        }
        
        if(e != null && 
                (e.getJugadores() == null 
                            || (e.getJugadores() != null 
                                    && e.getJugadores().size()<=0))
                ){           
            Jugador jugador = new Jugador(1, "Jon", "López", "Garrido", 1.70f, 74.0f, "central", "es un jugador de la ostia");
            DB4OInteface.insertJugador(jugador);                
            Jugador jugador_2 = new Jugador(2, "Mikel", "Ereño", "Ereño", 1.70f, 72.0f, "campista", "es un jugador de la ostia 2");
            DB4OInteface.insertJugador(jugador_2);                
            Jugador jugador_3 = new Jugador(3, "Iñaki", "Iñaki", "Iñaki", 1.70f, 68.0f, "delantero", "es un jugador de la ostia 3");
            DB4OInteface.insertJugador(jugador_3);                
        }

        //Obtenemos los tipos de entrenamiento
        ArrayList<TipoEntrenamiento> arrL_tipoEntre = getTiposEntrenamientos(new TipoEntrenamiento());

        //Comprabos si hay tipos de entrenamiento
        if (arrL_tipoEntre == null) {
            //Creamos los tipos de entrenamiento
            arrL_tipoEntre = new ArrayList<>();
            arrL_tipoEntre.add(new TipoEntrenamiento(1, "Defensivo", "Se practica la defensa"));
            arrL_tipoEntre.add(new TipoEntrenamiento(2, "Ofensivo", "Se practica la ofensa"));
            arrL_tipoEntre.add(new TipoEntrenamiento(3, "Explosivo", "Para cansar"));

            //Recorremos los tipos de entrenamiento
            for (int i = 0; i < arrL_tipoEntre.size(); i++) {
                //Guardamos el tipo de entrenamiento
                insertTipoEntrenamiento(arrL_tipoEntre.get(i));
            }
        }

        //Obtenemos los tipos de incidencias
        ArrayList<TipoIncidencia> arrL_tipoInci = getTiposIncidencias(new TipoIncidencia());

        //Comprabos si hay tipos de incidencias
        if (arrL_tipoInci == null) {
            //Creamos los tipos de incidencias
            arrL_tipoInci = new ArrayList<>();
            arrL_tipoInci.add(new TipoIncidencia(1, "Retraso", "1000", "Llegar tarde"));
            arrL_tipoInci.add(new TipoIncidencia(1, "Insultar", "500", "Insultar al jefe o a los compañeros"));
            arrL_tipoInci.add(new TipoIncidencia(1, "Falta", "10000", "No venir al entrenamiento"));

            //Recorremos los tipos de incidencias
            for (int i = 0; i < arrL_tipoInci.size(); i++) {

                //Guardamos el tipo de incidencias
                insertTipoIncidencia(arrL_tipoInci.get(i));
            }
        }
        //Si el equipo no existe es porque no exitía el aricho .yap 
        //y ahora si podremos llenar con los datos el archivo
        if(e == null){
            setDBData();
        }

    }

}
