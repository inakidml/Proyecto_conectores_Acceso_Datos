/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo.Conector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;


/**
 *
 * @author 9fdam04
 */
public class DB4OInteface extends Conector{
    
    public ArrayList<Jugador> getJugador(Jugador query){
        ObjectContainer db = this.getDb_4o();
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>;
        
        ObjectSet<Jugador> result = db.queryByExample(query);
        
        if(result.size() == 0){
            return null;
        }
        
        while (result.hasNext()) {
            Jugador j = ;
            
        }
        
        
    }
    
    public void storeJugador(Jugador j){
        ObjectContainer db = this.getDb_4o();
        
        db.store(j);
        
        db.close();
    }
    
}
