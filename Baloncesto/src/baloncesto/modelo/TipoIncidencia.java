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
public class TipoIncidencia {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";
    private static final String db4oConector = "db4o";
    private String conector;
    
    private int id;
    private String tipo;
    private String sancion;
    private String descripcion;

    public TipoIncidencia() {
    }

    public TipoIncidencia(int id, String tipo, String sancion, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.sancion = sancion;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoIncidencia{" + "id=" + id + ", tipo=" + tipo + ", sancion=" + sancion + ", descripcion=" + descripcion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final TipoIncidencia other = (TipoIncidencia) obj;
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
     * @return the sancion
     */
    public String getSancion() {
        return sancion;
    }

    /**
     * @param sancion the sancion to set
     */
    public void setSancion(String sancion) {
        this.sancion = sancion;
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
    
    public void save(){
        switch (this.conector) {
            case db4oConector:
                DB4OInteface.insertTipoIncidencia(this);
                break;
            case mysqlConector:
//                    SQLInterface.insertTipoIncidencia(this);
                break;
            case sqlServerConector:
//                    SQLInterface.insertTipoIncidencia(this);
                break;
        }
    }
}
