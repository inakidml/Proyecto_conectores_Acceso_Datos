/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import baloncesto.modelo.Conector.Conector;
import baloncesto.modelo.Conector.DB4OInteface;
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // TODO code application logic here      
        //Lanzamos la seleccion de los conectores con la Base de datos
        ArrayList<Equipo> arrL_equipos = new ArrayList<>();

        //Creamos los datos (Equipo, Tipos de Entrenamientos y Tipos de incidencias)
        DB4OInteface.setDBData();

        //Obtenmos el equipde desde DB4o
        arrL_equipos.add(DB4OInteface.getEquipo(new Equipo()));

        /*
        arrL_equipos.add(mikelDB4O());
        arrL_equipos.add(mikelMYSQL());
        arrL_equipos.add(mikelSQL());
         */
        
        lanzarVistaPrincipal(arrL_equipos);
    }

    public static void lanzarVistaPrincipal(ArrayList<Equipo> arrL_equipos) {
        Baloncesto.vP = new vistaPrincipal(arrL_equipos);
        vP.setLocationRelativeTo(vP);
        vP.setVisible(true);
    }
}
