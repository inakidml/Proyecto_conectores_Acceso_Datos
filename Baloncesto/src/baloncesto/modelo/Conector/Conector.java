/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.mysql.jdbc.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 *
 * @author 9fdam03
 */
public class Conector {

    private static String getAbsoluteResourceName(String resourceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private com.mysql.jdbc.Connection con_mysql_jdbc;
    private java.sql.Connection con_sql;
    private ObjectContainer db_4o;    
    
    private Properties config;

    public Conector() {
               
    }

    public Connection getCon_mysql_jdbc() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        
        System.out.println( config.getProperty("sql.server"));

        this.con_mysql_jdbc = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.223.122/mydb","root", "root"); //pruebas","root", "usbw");   
        
        return con_mysql_jdbc;
        
    }

    public java.sql.Connection getCon_sql() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        this.con_sql = DriverManager.getConnection("jdbc:sqlserver://172.20.223.123:1433;databaseName=Baloncesto;user=sa;password=");
        
        return con_sql;
        
    }

    public ObjectContainer getDb_4o() {     
        
        String BDBaloncesto = "DBBaloncesto.yap";
        
        this.db_4o = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDBaloncesto);
            
        return db_4o;
        
    }       

    private void getConfig() throws FileNotFoundException, IOException {          
        
        FileInputStream fis = new FileInputStream("src\\baloncesto\\modelo\\Conector\\config.properties");
        
        this.config = new Properties();
        
        this.config.load(fis);
     
    }    
   
 }
