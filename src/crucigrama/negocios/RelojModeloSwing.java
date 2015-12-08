/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

import java.util.Observable;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.Date;

/**
 * Modelo de reloj utilizando javax.swing.Timer.
 */
public class RelojModeloSwing extends Observable
 {
    
    Timer timer;
    int time ;
     /**
      * Lanza un timer cada segundo, avisando a los observadores de este
      * modelo del cambio. 
      */
     public RelojModeloSwing()
     {
         timer = new Timer (1000, new ActionListener ()
         {
             public void actionPerformed(ActionEvent e)
             {
                 setChanged();
                 notifyObservers (new Date());
                 time = time + 1;
             }
         });
         timer.start();
     }

    void stop() {
       timer.stop();
    }

    public int getTime() {
        return time;
    }
     
    
}
