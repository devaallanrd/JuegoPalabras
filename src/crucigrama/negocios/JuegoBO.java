/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

import crucigrama.dao.JugadoresDAO;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import crucigrama.modelo.Jugador;

/**
 *
 * @author aallanrd
 */
public class JuegoBO {

JugadoresDAO jugdao;    
Jugador jugador;
CuadroBO cuadroBO;


    int time;

    public JuegoBO() {
        jugdao = new JugadoresDAO();
    }

   
    
    
    public boolean iniciar( JPanel pnlC,String cat,JLabel label) {
      
        if(jugador==null){
           
            return false;
        }else{
           cuadroBO = new CuadroBO(pnlC,cat); 
           initTimer(label);
           return true;
        }
    }

   
   
    private void initTimer(JLabel label_timer){
         RelojModeloSwing modelo = new RelojModeloSwing();
         modelo.addObserver((Observable unObservable, Object dato) -> {
            
            label_timer.setText(time+"");
            time = time +1;
           
         });
    }

    public Jugador buscarJugador(String name, String pass) {
        Jugador buscarJugador = jugdao.buscarJugador(name, pass);
        jugador = buscarJugador;
        System.out.println(jugador.toString());
        return jugador;
    }

   
    
}
