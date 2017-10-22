/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

import baloncesto.modelo.Conector.DB4OInteface;
import baloncesto.modelo.Conector.SQLInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 9fdam02
 */
public class TipoEntrenamiento {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";
    private String conector;

    private int id;
    private String tipo;
    private String descripcion;

    public TipoEntrenamiento() {
    }

    public TipoEntrenamiento(int id, String tipo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoEntrenamiento{" + "id=" + getId() + ", tipo=" + getTipo() + ", descripcion=" + getDescripcion() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoEntrenamiento other = (TipoEntrenamiento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public void save() {
        try {
            switch (this.conector) {

                case db4oConector:
                    DB4OInteface.insertTipoEntrenamiento(this);
                    break;
                case mysqlConector:

                    SQLInterface.insertTipoEntrenamiento(this, conector);

                    break;
                case sqlServerConector:
                    SQLInterface.insertTipoEntrenamiento(this, conector);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEntrenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
