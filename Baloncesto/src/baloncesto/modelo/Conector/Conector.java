/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 9fdam03
 */
public abstract class Conector {

    protected static String getAbsoluteResourceName(String resourceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected static Connection con_mysql_jdbc;
    protected static Connection con_sql;
    protected static ObjectContainer db_4o;

    private static Properties config;

    protected static Connection getCon_mysql_jdbc() throws ClassNotFoundException, SQLException {

        try {
            Conector.setConfig(Conector.getConfig());
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }

        Class.forName("com.mysql.jdbc.Driver");

        Conector.con_mysql_jdbc = (Connection) DriverManager.getConnection("jdbc:mysql://" + config.getProperty("mysql.server") + "/" + config.getProperty("mysql.bd") + "", config.getProperty("mysql.user"), config.getProperty("mysql.pass")); //pruebas","root", "usbw");   

        return con_mysql_jdbc;

    }

    protected static Connection getCon_sql() throws ClassNotFoundException, SQLException {

        try {
            Conector.setConfig(Conector.getConfig());
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Conector.con_sql = DriverManager.getConnection("jdbc:sqlserver://" + config.getProperty("sql.server") + ":" + config.getProperty("sql.port") + ";databaseName=" + config.getProperty("sql.bd") + ";user=" + config.getProperty("sql.user") + ";password=");

        return con_sql;

    }

    protected static ObjectContainer getDb_4o() {

        try {
            Conector.setConfig(Conector.getConfig());
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }

        String BDBaloncesto = config.getProperty("db4o");

        Conector.db_4o = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDBaloncesto);

        return db_4o;

    }

    protected static Properties getConfig() throws FileNotFoundException, IOException {

        FileInputStream file = new FileInputStream("src\\baloncesto\\modelo\\Conector\\config.properties");

        Properties config_ = new Properties();

        config_.load(file);

        return config_;

    }

    protected static void setConfig(Properties config) {
        Conector.config = config;
    }

}
