/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

//import crucigrama.dao.JugadoresDAO;
import crucigrama.dao.JugadorDAO;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import crucigrama.modelo.Jugador;

/**
 *
 * @author aallanrd
 */
public class JuegoBO {

//JugadoresDAO jugdao;  
JugadorBO jugbo;
Jugador jugador;
CuadroBO cuadroBO;
RelojModeloSwing modeloTiempo ;


    int time;

    public JuegoBO() {
        //jugdao = new JugadoresDAO();
        jugbo = new JugadorBO();
    }

   
    
    
    public boolean iniciar( JPanel pnlC,int cat,JLabel label) {
      
        if(jugador==null){
           
            return false;
        }else{
           cuadroBO = new CuadroBO(pnlC,cat,false); 
           initTimer(label);
           return true;
        }
    }
    
    public boolean resolver(JPanel pnlC, int cat,JLabel label){
        if(jugador==null){
           
            return false;
        }else{
           cuadroBO = new CuadroBO(pnlC,cat,true); 
           stopTimer();
            System.out.println("Time: " + time);
           return true;
        }
    }

   
   
    private void initTimer(JLabel label_timer){
         
        
         modeloTiempo = new RelojModeloSwing();
         modeloTiempo.addObserver((Observable unObservable, Object dato) -> {
            
            label_timer.setText(time+"");
            time = time +1;
           
         });
    }

    public Jugador buscarJugador(String name, String pass) {
        
        //Jugador buscarJugador = jugdao.buscarJugador(name, pass);
        Jugador buscarJugador = jugbo.autentificarJugador(name,pass);
        jugador = buscarJugador;
//        System.out.println(jugador.toString());
        return jugador;
    }

    private void stopTimer() {
      modeloTiempo.stop();
      
    }

   
   
    
}
