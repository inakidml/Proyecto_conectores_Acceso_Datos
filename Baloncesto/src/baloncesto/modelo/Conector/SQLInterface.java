/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;

import baloncesto.modelo.Equipo;
import baloncesto.modelo.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikel e
 */
public class SQLInterface extends Conector {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static Connection conn;

    public static Equipo getEquipo(String conector) throws SQLException {
        try {
            Equipo obj_equipo = null;

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM EQUIPO";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj_equipo = (Equipo) parseObject(rs, Equipo.class.getSimpleName());
            }

            conn.close();
            
            obj_equipo.setConector(conector);
            
            return obj_equipo;
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
            Jugador obj_jugador = null;

            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM JUGADOR WHERE idJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                obj_jugador = (Jugador) parseObject(rs, Jugador.class.getSimpleName());
            }

            conn.close();

            return obj_jugador;
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
        if (conector == mysqlConector) {
            return getCon_mysql_jdbc();
        } else if (conector == sqlServerConector) {
            return getCon_sql();
        }
        return null;
    }

    private static Object parseObject(ResultSet rs, String tipo) throws SQLException {
        if (tipo.equals(Equipo.class.getSimpleName())) {
            return new Equipo(rs.getInt("idEquipo"), rs.getString("Nombre"), rs.getInt("Año_Fundacion"), rs.getString("Presidente"), rs.getString("Pabellon"), rs.getString("Patrocinador"));
        } else if (tipo.equals(Jugador.class.getSimpleName())) {

        }

        return null;
    }
}
