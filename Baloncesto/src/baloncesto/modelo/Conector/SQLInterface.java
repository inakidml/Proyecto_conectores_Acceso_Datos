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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikel e
 */
public class SQLInterface extends Conector {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";
    private static Connection conn;

    public static Equipo getEquipo(String conector) throws SQLException {
        try {
            Equipo obj = null;

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM EQUIPO";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            //Recoremos el resultado
            while (rs.next()) {
                //Parseamos el objeto
                obj = (Equipo) parseObject(rs, Equipo.class.getSimpleName());
                
                //Comprobamos si no es null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);
                }
            }
            
            //Cerramos la conexion
            conn.close();
            
            //Devolevos el objeto
            return obj;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return null;
    }

    public static Jugador getJugadorById(int id, String conector) throws SQLException {
        try {
            Jugador obj = null;

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR WHERE idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = (Jugador) parseObject(rs, Jugador.class.getSimpleName());

                if (obj != null) {
                    obj.setEquipo(getEquipo(conector));
                }
            }

            conn.close();

            return obj;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }

        }

        return null;
    }

    public static ArrayList<Jugador> getJugadores(String conector) throws SQLException {
        try {
            ArrayList<Jugador> list = new ArrayList<>();

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Jugador obj = (Jugador) parseObject(rs, Jugador.class.getSimpleName());

                if (obj!= null) {
                    obj.setConector(conector);
                    obj.setEquipo(getEquipo(conector));

                    list.add(obj);
                }
            }

            conn.close();

            return list;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return null;
    }

    public static ArrayList<Entrenamiento> getEntrenamientosByJugador(int idJugador, String conector) throws SQLException {
        try {
            ArrayList<Entrenamiento> list = new ArrayList<>();

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR_has_ENTRENAMIENTO WHERE idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idJugador);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entrenamiento obj = (Entrenamiento) parseObject(rs, Entrenamiento.class.getSimpleName());

                if (obj != null) {
                    obj.setConector(conector);
                    obj.setJugador(getJugadorById(idJugador, conector));
                    list.add(obj);
                }

            }

            conn.close();

            return list;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return null;
    }

    public static ArrayList<TipoEntrenamiento> getTipoEntrenamientos(String conector) throws SQLException {
        try {
            ArrayList<TipoEntrenamiento> lista = new ArrayList<>();

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM ENTRENAMIENTO";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                TipoEntrenamiento tE = new TipoEntrenamiento(rs.getInt(1), rs.getString(2), rs.getString(3));
                lista.add(tE);
            }

            conn.close();

            return lista;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return null;
    }

    private static Connection getConnection(String conector) throws ClassNotFoundException, SQLException {
        if (conector.equals(mysqlConector)) {
            return getCon_mysql_jdbc();
        } else if (conector.equals(sqlServerConector)) {
            return getCon_sql();
        }

        return null;
    }

    private static Object parseObject(ResultSet rs, String tipo) throws SQLException {
        if (tipo.equals(Equipo.class.getSimpleName())) {
            return new Equipo(rs.getInt("idEquipo"), rs.getString("Nombre"), rs.getInt("Año_Fundacion"), rs.getString("Presidente"), rs.getString("Pabellon"), rs.getString("Patrocinador"));
        } else if (tipo.equals(Jugador.class.getSimpleName())) {
            return new Jugador(rs.getInt("idJUGADOR"), rs.getString("Nombre"), rs.getString("Apellido1"), rs.getString("Apellido2"), rs.getFloat("Altura"), rs.getFloat("Peso"), rs.getString("Posicion"), rs.getString("Descripcion"));
        } else if (tipo.equals(Entrenamiento.class.getSimpleName())) {
            return new Entrenamiento(rs.getString("Fecha"), rs.getString("Duracion"));
        } else if (tipo.equals(TipoEntrenamiento.class.getName())) {
            return new TipoEntrenamiento(rs.getInt("idENTRENAMIENTO"), rs.getString("Tipo_Entrenamiento"), rs.getString("Descripcion"));
        } else if (tipo.equals(Incidencia.class.getSimpleName())) {
            return new Incidencia(rs.getString("Fecha"));
        }else if(tipo.equals(TipoIncidencia.class.getSimpleName())){
            return new TipoIncidencia(rs.getInt("idINCIDENCIA"), rs.getString("Tipo_Incidencia"), rs.getString("Sancion"), rs.getString("Descripcion"));
        }

        return null;
    }
}
