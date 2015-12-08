/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

//import crucigrama.dao.JugadoresDAO;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import crucigrama.modelo.Jugador;
import crucigrama.modelo.Tiempo;
import java.awt.List;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aallanrd
 */
public class JuegoBO {

//JugadoresDAO jugdao;  
    JugadorBO jugbo;
    PalabraBO palabras;
    Jugador jugador;
    CuadroBO cuadroBO;
    RelojModeloSwing modeloTiempo;
    TiempoBO tiempo;

    int time;

    public JuegoBO() {
        //jugdao = new JugadoresDAO();
        jugbo = new JugadorBO();
        palabras = new PalabraBO();
        tiempo = new TiempoBO();
    }

    public boolean iniciar(List lista, JPanel pnlC, int cat, JLabel label) {

        if (jugador == null) {

            return false;
        } else {
            cuadroBO = new CuadroBO(lista, pnlC, cat, false);
            initTimer(label);
            return true;
        }
    }

    public boolean resolver(List lista, JPanel pnlC, int cat, JLabel label) {
        if (jugador == null) {

            return false;
        } else {
            cuadroBO = new CuadroBO(lista, pnlC, cat, true);
            stopTimer();
            System.out.println("Time: " + time);
            return true;
        }
    }

    private void initTimer(JLabel label_timer) {

        modeloTiempo = new RelojModeloSwing();
        modeloTiempo.addObserver((Observable unObservable, Object dato) -> {

            label_timer.setText(time + "");
            time = time + 1;

        });
    }

    public Jugador buscarJugador(String name, String pass) {

        //Jugador buscarJugador = jugdao.buscarJugador(name, pass);
        Jugador buscarJugador = jugbo.autentificarJugador(name, pass);
        jugador = buscarJugador;
//        System.out.println(jugador.toString());
        return jugador;
    }

    private void stopTimer() {
        modeloTiempo.stop();

    }

    public void buscarCategorias() {
        palabras.buscarCategorias();
    }

    public LinkedList<Tiempo> buscarMejoresTiempos() {
        return tiempo.buscarTiempos(100);
    }

    public boolean registrarJugador(String name, String pass) {

        try {
            jugbo.registrar(new Jugador(0, name, pass, 0));
            return true;
        } catch (Exception e) {
                return false;
        }
      

    }

   
   
    
}
