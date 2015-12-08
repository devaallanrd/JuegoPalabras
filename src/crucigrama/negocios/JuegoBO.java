/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import crucigrama.modelo.Jugador;

/**
 *
 * @author aallanrd
 */
public class JuegoBO {
    
Jugador jugador;
CuadroBO cuadroBO;


    int time;

    
    public JuegoBO(Jugador j, JPanel pnlC,String cat) {
       cuadroBO = new CuadroBO(pnlC,cat); 
       jugador = j;
    }

   
   
    public void initTimer(JLabel label_timer){
         RelojModeloSwing modelo = new RelojModeloSwing();
         modelo.addObserver((Observable unObservable, Object dato) -> {
            
            label_timer.setText(time+"");
            time = time +1;
           
         });
    }
    
}
