/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import baloncesto.modelo.Conector.Conector;
import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import baloncesto.modelo.Equipo;
import baloncesto.vista.vistaPrincipal;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 9fdam03
 */
public class Baloncesto extends Conector {

    /**
     * @param args the command line arguments
     */
    private static vistaPrincipal vP;
    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    
    private static Equipo eq_mysql = new Equipo(01, "Tau Ceramica", 1941, "El jefazo 01", "pabellon-1", "patrocinador-1");;
    private static Equipo eq_sql = new Equipo(02, "Unicaja", 1942, "El jefazo 02", "pabellon-2", "patrocinador-2");
    private static Equipo eq_db4o = new Equipo(03, "Barcelona", 1943, "El jefazo 03", "pabellon-3", "patrocinador-3");

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // TODO code application logic here      
        //Lanzamos la seleccion de los conectores con la Base de datos
        ArrayList<Equipo> arrL_equipos = new ArrayList<>();

        //Creamos los datos (Equipo, Tipos de Entrenamientos y Tipos de incidencias)
        DB4OInteface.setDBData();
        
        //Obtenemos el equipo de mysql
        arrL_equipos.add(SQLInterface.getEquipo(mysqlConector));
       
        //Obtenmos el equipde desde DB4o
        arrL_equipos.add(DB4OInteface.getEquipo(new Equipo()));
        arrL_equipos.add(eq_mysql);arrL_equipos.add(eq_sql);arrL_equipos.add(eq_db4o);
        lanzarVistaPrincipal(arrL_equipos);
    }

    public static void lanzarVistaPrincipal(ArrayList<Equipo> arrL_equipos) {
        Baloncesto.vP = new vistaPrincipal(arrL_equipos);
        vP.setLocationRelativeTo(vP);
        vP.setVisible(true);
    }
}
