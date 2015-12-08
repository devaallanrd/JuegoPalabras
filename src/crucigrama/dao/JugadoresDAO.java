
package crucigrama.dao;

import crucigrama.modelo.Jugador;
/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
public class JugadoresDAO {
    
    
    
    public Jugador  buscarJugador(String name, String pass){
        
        Jugador jug = null;
        if(name.equals("Allan")&&(pass.equals("1234"))){
            jug =  new Jugador(1,"Allan", "1234", 0);
        }
        else{
          jug = null;
        }
        return jug;
    }
}
