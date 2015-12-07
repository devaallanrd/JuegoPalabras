/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.presentacion;

import crucigrama.negocios.IJuegoPalabras;
import crucigrama.negocios.ICeldas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComponent;

public class CajaJuego
extends JComponent {
    private final IJuegoPalabras puzzle;

    public CajaJuego(IJuegoPalabras p) {
        this.puzzle = p;
        Dimension size = p.getDimension();
        this.setLayout(new GridLayout(size.height, size.width));
        int i = 0;
        while (i < size.height) {
            int j = 0;
            while (j < size.width) {
                ICeldas pc = p.getCelda(j, i);
               
                CeldaCajaJuego pbc = new CeldaCajaJuego(pc);
                pbc.setLetraMostrada(false);
                this.add(pbc);
                ++j;
            }
            ++i;
        }
    }

    public IJuegoPalabras getPuzzle() {
        return this.puzzle;
    }
    
    // Saber si se estan mostrando las letras
    public boolean seMuestran() {
        return ((CeldaCajaJuego)this.getComponent(0)).isKeyShown();
    }

    // Setear el mostrar letras segun el booleando que reciba
    public void setMuestraLetra(boolean s) {
        if (s != this.seMuestran()) {
            Component[] c = this.getComponents();
            int i = 0;
            while (i < c.length) {
                ((CeldaCajaJuego)c[i]).setLetraMostrada(s);
                ++i;
            }
            this.repaint();
        }
    }

    
}

