/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Controlador
extends JComponent {
    private final PantallaJuego pantallaJuego;
    private final PantallaPalabras pantallaPalabras;
    private boolean listaPalabras = true;

    public Controlador() throws NoSuchMethodException {
        this.setLayout(new CardLayout());
        
        this.pantallaPalabras = new PantallaPalabras(this);
        
        this.add("wls", this.getListaPalabras());
        
        this.pantallaJuego = new PantallaJuego(this);
        this.add("ps", this.getPantallaJuego());
    }

    private PantallaJuego getPantallaJuego() {
        return this.pantallaJuego;
    }

    private PantallaPalabras getListaPalabras() {
        return this.pantallaPalabras;
    }

    void cambiarPantallas(Map datos) {
        boolean bl = this.listaPalabras = !this.listaPalabras;
        if (this.listaPalabras) {
            this.getListaPalabras().initialize(datos);
        } else {
            try{
            this.getPantallaJuego().initialize(datos);
            }catch(Exception e){
                
            }
        }
        ((CardLayout)this.getLayout()).show(this, this.listaPalabras ? "wls" : "ps");
        Container parent = this.getTop();
        if (parent instanceof Frame) {
           // System.out.println("packing");
            ((Frame)parent).pack();
        }
    }

    Container getTop() {
        return this.getTop(this);
    }

    private Container getTop(Container parent) {
        Container p2 = parent.getParent();
        if (p2 != null) {
            return this.getTop(p2);
        }
        return parent;
    }

    public static void main(String[] argv) throws NoSuchMethodException {
        JFrame f = new JFrame();
        f.setTitle("Juego UTN Pedro");
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());
        // *******************************************************
        c.add((Component)new Controlador(), "Center");
        // *******************************************************
        f.pack();
        f.setDefaultCloseOperation(3);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

