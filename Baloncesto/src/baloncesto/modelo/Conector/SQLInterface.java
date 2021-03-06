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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mikel e
 */
public class SQLInterface extends Conector {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";

    public static Equipo getEquipo(String conector) throws SQLException {
        //Objeto equipo
        Equipo obj = null;
        Connection conn = null;

        try {

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM EQUIPO";
            Statement st = conn.createStatement();

            //Ejecutamos la query
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolevos el objeto
        return obj;
    }

    public static Jugador getJugadorById(int id, String conector) throws SQLException, SQLException, SQLException, SQLException {
        //Objeto jugador
        Jugador obj = null;
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR WHERE idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id a la condicion
            ps.setInt(1, id);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el jugador
                obj = (Jugador) parseObject(rs, Jugador.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el equipo
                    obj.setEquipo(getEquipo(conector));
                }
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el jugador
        return obj;
    }

    public static ArrayList<Jugador> getJugadores(String conector) throws SQLException {
        //Lista de jugadores
        ArrayList<Jugador> list = new ArrayList<>();
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR";
            Statement st = conn.createStatement();

            //Ejecutamos la query
            ResultSet rs = st.executeQuery(query);

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el juagador
                Jugador obj = (Jugador) parseObject(rs, Jugador.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el equipo
                    obj.setEquipo(getEquipo(conector));

                    //Añadimos el jugador a la lista
                    list.add(obj);
                }
            }

            //Cerramos la conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    public static boolean insertJugador(Jugador obj, String conector) throws SQLException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO JUGADOR values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getApellido());
            ps.setString(4, obj.getApellido2());
            ps.setFloat(5, obj.getAltura());
            ps.setFloat(6, obj.getPeso());
            ps.setString(7, obj.getPosicion());
            ps.setString(8, obj.getDescripcion());
            ps.setInt(9, obj.getEquipo().getId());

            //Ejecutamos la insert
            int action = ps.executeUpdate();

            //Comprobamos si insert ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerrar conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static boolean updateJugador(Jugador obj, String conector) throws SQLException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "UPDATE JUGADOR SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, Altura = ?, Peso = ?, Posicion = ?, Descripcion = ?, EQUIPO_idEquipo = ? WHERE idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos los datos
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellido());
            ps.setString(3, obj.getApellido2());
            ps.setFloat(4, obj.getAltura());
            ps.setFloat(5, obj.getPeso());
            ps.setString(6, obj.getPosicion());
            ps.setString(7, obj.getDescripcion());
            ps.setInt(8, obj.getEquipo().getId());
            ps.setInt(9, obj.getId());

            //Ejecutamos la update
            int action = ps.executeUpdate();

            //Comprobamos si funcionado correctamente
            if (action > 0) {
                result = true;
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static boolean deleteJugador(int id, String conector) throws SQLException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "DELETE FROM JUGADOR WHERE idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos el id del jugador
            ps.setInt(1, id);

            //Ejecutar la delete
            int action = ps.executeUpdate();

            //Comprobamos si ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerramos la conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static Entrenamiento getEntrenamientoById(int idJugador, int idTipoEntrenamiento, String fecha, String conector) throws SQLException {
        //Objeto entrenamiento  
        Entrenamiento obj = null;
        Connection conn = null;

        try {

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR_has_ENTRENAMIENTO WHERE JUGADOR_idJUGADOR = ? AND ENTRENAMIENTO_idENTRENAMIENTO = ? AND Fecha = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos al where el idJugador y idEntrenamiento
            ps.setInt(1, idJugador);
            ps.setInt(2, idTipoEntrenamiento);

            //Convertimos la fecha
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed_date = format.parse(fecha);
            java.sql.Date sql_date = new java.sql.Date(parsed_date.getTime());

            //Añadimos la fecha el where
            ps.setDate(3, sql_date);

            //Executamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el entrenamiento
                obj = (Entrenamiento) parseObject(rs, Entrenamiento.class.getSimpleName());

                //Comprobamos que no sea null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el jugador
                    obj.setJugador(getJugadorById(idJugador, conector));

                    //Añadimos tipo de entrenamiento
                    obj.setTipoEntrenamiento(getTipoEntrenamientoById(idTipoEntrenamiento, conector));
                }
            }

            //Cerramos la conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el entrenamiento
        return obj;
    }

    public static ArrayList<Entrenamiento> getEntrenamientosByJugador(int idJugador, String conector) throws SQLException {
        //Lista de entrenamientos
        ArrayList<Entrenamiento> list = new ArrayList<>();
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR_has_ENTRENAMIENTO WHERE JUGADOR_idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el idJugador al where
            ps.setInt(1, idJugador);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el tipo de entrenamiento
                Entrenamiento obj = (Entrenamiento) parseObject(rs, Entrenamiento.class.getSimpleName());

                //Obtenemos el tipo de entrenamiento
                int idTipoEntrenamiento = rs.getInt("ENTRENAMIENTO_idENTRENAMIENTO");

                //Comprobamos que no sea null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el jugador
                    obj.setJugador(getJugadorById(idJugador, conector));

                    //Añadimos el tipo de entramiento
                    obj.setTipoEntrenamiento(getTipoEntrenamientoById(idTipoEntrenamiento, conector));

                    //Lo añadimos a la lista
                    list.add(obj);
                }

            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la lista
        return list;
    }

    public static boolean insertEntrenamiento(Entrenamiento obj, String conector) throws SQLException {
        boolean result = false;
        Connection conn = null;
        try {
            //Establecemos la conexion
            conn = getConnection(conector);
            //Query
            String query = "INSERT INTO JUGADOR_has_ENTRENAMIENTO values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            //Indicamos los datos del entrenamiento
            ps.setInt(1, obj.getJugador().getId());
            ps.setInt(2, obj.getTipoEntrenamiento().getId());
            switch (conector) {
                case mysqlConector:
                    //Convertimos la fecha
                    SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = sdf_date.parse(obj.getFecha());
                    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

                    //Añadimos la fecha
                    ps.setDate(3, sqlDate);

                    //Convertimo la duracion
                    SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
                    java.sql.Time sqlTime = new java.sql.Time(sdf_time.parse(obj.getDuracion()).getTime());

                    //Añadimos la duracion
                    ps.setTime(4, sqlTime);
                    break;
                case sqlServerConector:
                    //MS microsoft, con décimas de segundo
                    //Convertimos la fecha
                    SimpleDateFormat sdf_dateMS = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
                    Date parsedDateMS = sdf_dateMS.parse(obj.getFecha() + " 00:00:00.00");
                    java.sql.Timestamp sqlDateMS = new java.sql.Timestamp(parsedDateMS.getTime());

                    //Añadimos la fecha
                    ps.setTimestamp(3, sqlDateMS);

                    //Convertimo la duracion
                    SimpleDateFormat sdf_timeMS = new SimpleDateFormat("hh:mm:ss.SS");
                    java.sql.Time sqlTimeMS = new java.sql.Time(sdf_timeMS.parse(obj.getDuracion() + ".00").getTime());

                    //Añadimos la duracion
                    ps.setTime(4, sqlTimeMS);
                    break;
                default:

            }

            //Ejecutamos la insert
            int action = ps.executeUpdate();

            //Comprobamos si ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerramos la conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static boolean deleteEntrenamiento(int idJugador, int idTipoEntrenamiento, String fecha, String conector) throws SQLException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "DELETE FROM JUGADOR_has_ENTRENAMIENTO WHERE JUGADOR_idJUGADOR = ? AND ENTRENAMIENTO_idENTRENAMIENTO = ? AND Fecha = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos los ids
            ps.setInt(1, idJugador);
            ps.setInt(2, idTipoEntrenamiento);

            //Convertimo la fecha
            if (conector == mysqlConector) {
                //Fecha mysql
                SimpleDateFormat sdf_mysql = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date mysql_date = new java.sql.Date(sdf_mysql.parse(fecha).getTime());

                //Añadimos la fecha
                ps.setDate(3, mysql_date);

            } else if (conector == sqlServerConector) {
                //Fecha sqlServer
                SimpleDateFormat sdf_sqlServer = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
                java.sql.Date sqlServer_date = new java.sql.Date(sdf_sqlServer.parse(fecha + " 00:00:00.00").getTime());

                //Añadimos la fecha 
                ps.setDate(3, sqlServer_date);
            }

            //Ejecutamos el delete
            int action = ps.executeUpdate();

            //Comprobamos si ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultados
        return result;
    }

    public static TipoEntrenamiento getTipoEntrenamientoById(int id, String conector) throws SQLException {
        //Objeto tipo entrenamiento
        TipoEntrenamiento obj = null;
        Connection conn = null;

        try {
            //Obtenemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM ENTRENAMIENTO WHERE idENTRENAMIENTO = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id de jugador a where
            ps.setInt(1, id);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el tipo de entrenamiento
                obj = (TipoEntrenamiento) parseObject(rs, TipoEntrenamiento.class.getSimpleName());

                //Comprobamos que no sea null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);
                }
            }

            //Cerramos conexion
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el objeto
        return obj;
    }

    public static ArrayList<TipoEntrenamiento> getTipoEntrenamientos(String conector) throws SQLException {
        //Lista de tipos de entrenamiento
        ArrayList<TipoEntrenamiento> list = new ArrayList<>();
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM ENTRENAMIENTO";
            Statement st = conn.createStatement();

            //Ejecutamos la query
            ResultSet rs = st.executeQuery(query);

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el tipo de entrenamiento
                TipoEntrenamiento obj = (TipoEntrenamiento) parseObject(rs, TipoEntrenamiento.class.getSimpleName());

                //Comprobamos que no sea null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el objeto a lista
                    list.add(obj);
                }
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la lista
        return list;
    }

    public static boolean insertTipoEntrenamiento(TipoEntrenamiento obj, String conector) throws SQLException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO ENTRENAMIENTO values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getTipo());
            ps.setString(3, obj.getDescripcion());

            //Ejecutamos la insert
            int action = ps.executeUpdate();

            //Comprobamos si insert ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerrar conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static Incidencia getIncidenciaById(int idTipoIncidencia, int idJugador, String conector) throws SQLException {
        //Objeto de incidencia
        Incidencia obj = null;
        Connection conn = null;

        try {
            //Obtener el conector
            conn = getConnection(conector);

            //query
            String query = "SELECT * FROM INCIDENCIA_has_JUGADOR WHERE INCIDENCIA_idINCIDENCIA = ? AND JUGADOR_idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id del tipo de incidencia a la condicion de where
            ps.setInt(1, idTipoIncidencia);

            //Añadimos el id del jugador al where
            ps.setInt(2, idJugador);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obteneos la incidencia
                obj = (Incidencia) parseObject(rs, Incidencia.class.getSimpleName());

                //Comprobamos que no se null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el tipo de incidencia
                    obj.setTipoIncidencia(getTipoIncidenciaById(idTipoIncidencia, conector));

                    //Añadimos el jugador
                    obj.setJugador(getJugadorById(idJugador, conector));
                }
            }

            //Cerramos la conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolver el objeto
        return obj;
    }

    public static ArrayList<Incidencia> getIncidenciasByJugador(int idJugador, String conector) throws SQLException {
        ArrayList<Incidencia> list = new ArrayList<>();
        Connection conn = null;

        try {
            //Obtener la conexion
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM INCIDENCIA_has_JUGADOR WHERE JUGADOR_idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id de jugador al where
            ps.setInt(1, idJugador);

            //Ejecutmos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos la incidencia
                Incidencia obj = (Incidencia) parseObject(rs, Incidencia.class.getSimpleName());

                //Obtenemos el id del tipo de incidencia
                int idTipoIncidencia = rs.getInt("INCIDENCIA_idINCIDENCIA");

                //Comprobamos que no sea null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el juagador
                    obj.setJugador(getJugadorById(idJugador, conector));

                    //Añadimos el tipo de incidencia
                    obj.setTipoIncidencia(getTipoIncidenciaById(idTipoIncidencia, conector));

                    //Añadimos la incidencia a la lista
                    list.add(obj);
                }
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la lista
        return list;
    }

    public static Boolean insertIncidencia(Incidencia obj, String conector) throws SQLException {
        Boolean result = false;
        Connection conn = null;

        try {
            //Obtenemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO INCIDENCIA_has_JUGADOR values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos los datos de la incidencia
            ps.setInt(1, obj.getTipoIncidencia().getId());
            ps.setInt(2, obj.getJugador().getId());

            //Convertimos la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(obj.getFecha());
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            //Añadimos la fecha
            ps.setDate(3, sqlDate);

            //Ejecutamos la insert
            int action = ps.executeUpdate();

            //Comprobamos si ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public static boolean deleteIncidencia(int idTipoIncidencia, int idJugador, String fecha, String conector) throws SQLException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "DELETE FROM INCIDENCIA_has_JUGADOR WHERE INCIDENCIA_idINCIDENCIA = ? AND JUGADOR_idJUGADOR = ? AND Fecha = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos la ids
            ps.setInt(1, idTipoIncidencia);
            ps.setInt(2, idJugador);

            //Convertimo la fecha
            if (conector == mysqlConector) {
                //Fecha mysql
                SimpleDateFormat sdf_mysql = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date mysql_date = new java.sql.Date(sdf_mysql.parse(fecha).getTime());

                //Añadimos la fecha
                ps.setDate(3, mysql_date);

            } else if (conector == sqlServerConector) {
                //Fecha sqlServer
                SimpleDateFormat sdf_sqlServer = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
                java.sql.Date sqlServer_date = new java.sql.Date(sdf_sqlServer.parse(fecha + " 00:00:00.00").getTime());

                //Añadimos la fecha 
                ps.setDate(3, sqlServer_date);
            }

            //Ejecutamos el delete
            int action = ps.executeUpdate();

            //Comprobamos si ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static TipoIncidencia getTipoIncidenciaById(int id, String conector) throws SQLException {
        TipoIncidencia obj = null;
        Connection conn = null;

        try {
            //Obtenemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM INCIDENCIA WHERE idINCIDENCIA = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id del tipo de incidencia al where
            ps.setInt(1, id);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                obj = (TipoIncidencia) parseObject(rs, TipoIncidencia.class.getSimpleName());

                //Comprobamos que no se null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);
                }
            }

            //Cerramos la conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return obj;
    }

    public static ArrayList<TipoIncidencia> getTiposIncidencias(String conector) throws SQLException {
        //Lista de tipos de incidencia
        ArrayList<TipoIncidencia> list = new ArrayList<>();
        Connection conn = null;

        try {
            //Obtemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM INCIDENCIA";
            Statement st = conn.createStatement();

            //Ejecutamos la query
            ResultSet rs = st.executeQuery(query);

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos el tipo incidencia
                TipoIncidencia obj = (TipoIncidencia) parseObject(rs, TipoIncidencia.class.getSimpleName());

                //Comprobamos que no sea null
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                    //Añadimos el tipo de incidencia a la lista
                    list.add(obj);
                }
            }

            //Cerramos conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devovemos la lista
        return list;
    }

    public static boolean insertTipoIncidencia(TipoIncidencia obj, String conector) throws SQLException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO INCIDENCIA values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getTipo());
            ps.setString(3, obj.getSancion());
            ps.setString(4, obj.getDescripcion());

            //Ejecutamos la insert
            int action = ps.executeUpdate();

            //Comprobamos si insert ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerrar conexion
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    private static Connection getConnection(String conector) throws ClassNotFoundException, SQLException {
        if (conector.equals(mysqlConector)) {
            return getCon_mysql_jdbc();
        } else {
            return getCon_sql();
        }
    }

    private static Object parseObject(ResultSet rs, String tipo) throws SQLException {
        if (tipo.equals(Equipo.class.getSimpleName())) {
            //Creamos el objeto equipo
            return new Equipo(rs.getInt("idEquipo"), rs.getString("Nombre"), rs.getInt("Año_Fundacion"), rs.getString("Presidente"), rs.getString("Pabellon"), rs.getString("Patrocinador"));
        } else if (tipo.equals(Jugador.class.getSimpleName())) {
            //Creamos el objeto jugador
            return new Jugador(rs.getInt("idJUGADOR"), rs.getString("Nombre"), rs.getString("Apellido1"), rs.getString("Apellido2"), rs.getFloat("Altura"), rs.getFloat("Peso"), rs.getString("Posicion"), rs.getString("Descripcion"));
        } else if (tipo.equals(Entrenamiento.class.getSimpleName())) {
            //Creamos el objeto entrenamiento
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            return new Entrenamiento(formatDate.format(rs.getDate("Fecha")), rs.getTime("Duracion").toString());
        } else if (tipo.equals(TipoEntrenamiento.class.getSimpleName())) {
            //Creamos objeto tipo entrenamiento
            return new TipoEntrenamiento(rs.getInt("idENTRENAMIENTO"), rs.getString("Tipo_Entrenamiento"), rs.getString("Descripcion"));
        } else if (tipo.equals(Incidencia.class.getSimpleName())) {
            //Creamod el objeto incidencia
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            return new Incidencia(formatDate.format(rs.getDate("Fecha")));
        } else if (tipo.equals(TipoIncidencia.class.getSimpleName())) {
            //Creamo el objeto Tipo incidencia
            return new TipoIncidencia(rs.getInt("idINCIDENCIA"), rs.getString("Tipo_Incidencia"), rs.getString("Sancion"), rs.getString("Descripcion"));
        } else {
            //Devolvemos un null 
            return null;
        }
    }
}
