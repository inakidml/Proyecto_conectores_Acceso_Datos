/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto;

import baloncesto.modelo.Conector.Conector;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author 9fdam03
 */
public class Baloncesto extends Conector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // TODO code application logic here
        Connection con = Conector.getCon_mysql_jdbc();
        
        con.close();
        
    }
}
