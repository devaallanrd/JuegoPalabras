/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.presentacion;

import crucigrama.negocios.ICeldas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JComponent;

public class CeldaCajaJuego
extends JComponent {
    private static final Font NUMBER_FONT = new Font("SansSerif", 0, 10);
    private static final Font KEY_FONT = new Font("SansSerif", 0, 16);
    private boolean keyShown;
    private final ICeldas cell;
    private final char[] numCa;
    private final char[] keyCa;

    public CeldaCajaJuego(ICeldas c) {
        this.cell = c;
        String uix = String.valueOf(c.getIndexUsuario());
        this.numCa = new char[uix.length()];
        uix.getChars(0, uix.length(), this.numCa, 0);
        this.keyCa = new char[]{(char)c.getContenido()};
    }

    @Override
    public void paintComponent(Graphics g) {
        Dimension size = this.getSize();
        g.setColor(Color.black);
        if (this.cell.getContenido() == -1) {
            g.fillRect(0, 0, size.width, size.height);
        } else {
           
           
            g.drawRect(0, 0, size.width - 1, size.height - 1);
            
            
            if (this.cell.getIndexUsuario() >= 0) {
                g.setFont(NUMBER_FONT);
                int ascent = g.getFontMetrics(NUMBER_FONT).getAscent();
                g.drawChars(this.numCa, 0, this.numCa.length, 2, 2 + ascent);
              //  System.out.println(text + "/" +this.numCa.length  );
            }
            if (this.keyShown) {
                g.setFont(KEY_FONT);
                FontMetrics fm = g.getFontMetrics(KEY_FONT);
                int ascent = fm.getAscent();
                int width = fm.charsWidth(this.keyCa, 0, this.keyCa.length);
                g.drawChars(this.keyCa, 0, this.keyCa.length, 
                        (size.width - width) / 2, (size.height + ascent) / 2);
               //System.out.println(text + "x:" +(size.width - width) +" y:" + ((size.height + ascent) / 2)  );
               
            }
        }
    }

    public void setLetraMostrada(boolean ks) {
        this.keyShown = ks;
    }

    public boolean isKeyShown() {
        return this.keyShown;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(33, 33);
    }

   
}

