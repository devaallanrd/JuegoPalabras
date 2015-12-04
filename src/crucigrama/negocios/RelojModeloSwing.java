/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.Date;

/**
 * Modelo de reloj utilizando javax.swing.Timer.
 */
public class RelojModeloSwing extends Observable
 {
     /**
      * Lanza un timer cada segundo, avisando a los observadores de este
      * modelo del cambio. 
      */
     public RelojModeloSwing()
     {
         Timer timer = new Timer (1000, new ActionListener ()
         {
             public void actionPerformed(ActionEvent e)
             {
                 setChanged();
                 notifyObservers (new Date());
             }
         });
         timer.start();
     }
     
     /**
      * Main para prueba de esta clase.
      */
     public static void main (String [] args)
     {
         RelojModeloSwing modelo = new RelojModeloSwing();
         modelo.addObserver(new Observer()
         {
             public void update (Observable unObservable, Object dato)
             {
                 System.out.println (dato);
             }
         });
         
         // Espera de 10 segundos para que el programa no termine
         // inmediatamente
         try
         {
            Thread.currentThread().sleep (5000);
         } catch (Exception e)
         {
         }
     }
}
