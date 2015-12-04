package crucigrama.presentacion;

/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

import crucigrama.negocios.IJuegoPalabras;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;

public class DesplegarJuego
extends JComponent {
    private CajaJuego cajaJuego;
    private Pistas cajaPistas;
    private final IJuegoPalabras juego;

    public DesplegarJuego(IJuegoPalabras p) {
        this.juego = p;
        
        //Si no sabe que es GridBagLayout  
        //lealo aqui: https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        GridBagLayout gbl = new GridBagLayout();   this.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        
        //Obtener el Panel de Juego
        CajaJuego pb = this.getCajaJuego();
        gbl.setConstraints(pb, gbc);
        this.add(pb);
        ++gbc.gridy;
        
        //Obtener el Panel de Pistas
        Pistas cb = this.getCajaPistas();
        gbl.setConstraints(cb, gbc);
        this.add(cb);
    }

    private CajaJuego getCajaJuego() {
        if (this.cajaJuego == null) {
            this.cajaJuego = new CajaJuego(this.getJuego());
        }
        return this.cajaJuego;
    }
    
    //Obtiene las pistas 
    private Pistas getCajaPistas() {
        if (this.cajaPistas == null) {
            this.cajaPistas = new Pistas(this.getJuego());
        }
        return this.cajaPistas;
    }

    public boolean estanMostrandoPistas() {
        return this.getCajaPistas().isVisible();
    }

    public void setMostrarPistas(boolean cs) {
        this.getCajaPistas().setVisible(cs);
    }

    public boolean estanMostrandoLetras() {
        return this.getCajaJuego().seMuestran();
    }

    public void setMuestraLetras(boolean s) {
        this.getCajaJuego().setMuestraLetra(s);
    }

    public void imprimir() {
    }

    public IJuegoPalabras getJuego() {
        return this.juego;
    }

    
}

