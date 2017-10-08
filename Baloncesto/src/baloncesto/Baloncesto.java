/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import baloncesto.modelo.Conector.Conector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author 9fdam03
 */
public class Baloncesto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // TODO code application logic here
        
        Conector obj_con = new Conector();
        obj_con.getCon_sql();
      
        
        
    }
    
}
