/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

import crucigrama.dao.PalabrasDAO;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import crucigrama.modelo.Cuadro;
import crucigrama.modelo.Palabra;
import java.awt.List;
import java.util.Map;

public class CuadroBO {

    //Control de palabras
    PalabrasDAO controlPalabras;
    Cuadro[][] crucigrama;
    JuegoPalabras juego;

    LinkedList<Palabra> palabras = new LinkedList<>();
    int x = 4;
    int y = 4;

    public CuadroBO(List listaPistas, JPanel panel, int cat,boolean res) {

        controlPalabras = new PalabrasDAO(cat);
        CargarPistas(listaPistas);
        crucigrama = new Cuadro[13][13];
        for (int i = 0; i < crucigrama.length; i++) {
            for (int j = 0; j < crucigrama[i].length; j++) {
                crucigrama[i][j] = new Cuadro();
            }
        }
        getMejorJuego();
        pintarMatriz(panel,res);
    }
    
    
    private void CargarPistas(List lista){
        int c = 0;
        while(c!=controlPalabras.getListaPalabras().size()){
            lista.add(controlPalabras.getListaPalabras().get(c));
        }
    }
    
    
    private void getMejorJuego() {
        Map<String, String> listaPalabras = controlPalabras.getListaPalabras();
        juego = new JuegoPalabras(listaPalabras, listaPalabras.size());

        LinkedList<Palabra> construirMejorJuego = juego.getConstruirMejorJuego();

        try {
            int c = 0;
            while (c != construirMejorJuego.size()) {
                Palabra get = construirMejorJuego.get(c);
                colocar(get.getWord(), get.getY(), get.getX(), c, get.getDir());
                //  System.out.println("Colocada" + get.getWord()  );
                c++;
            }
        } catch (Exception e) {

        }
    }

    //Coloca una palabra en la matriz principal
    public Cuadro[][] colocar(String word, int pY, int pX, int pista, String dir) {
        int lengthTotal = word.length() + pX;
        if (lengthTotal <= 13) {

            int wordC = 0;
            while (wordC != word.length()) {

                Cuadro c = new Cuadro(pista, word.charAt(wordC), false);

                if (dir.equals("Horizontal")) {
                    this.crucigrama[pY][pX + wordC] = c;
                    //System.out.println(dir + "/" + word + " : Horizontal Colocado");
                } else {
                    this.crucigrama[pY + wordC][pX] = c;
                    //System.out.println(dir + "/" + word + " : Vertical Colocado");
                }
                wordC++;
            }

        } else {
            //System.out.println(word + " :  Sobrepasa Matriz");
            return null;
        }
        return this.crucigrama;
    }

    public Cuadro[][] getCrucigrama() {
        return crucigrama;
    }

    private void pintarMatriz(JPanel pnlCrucigrama,boolean resolver) {

        pnlCrucigrama.removeAll();
        pnlCrucigrama.setVisible(false);

        for (int i = 0; i < crucigrama.length; i++) {
            for (int j = 0; j < crucigrama[i].length; j++) {
                Cuadro c = crucigrama[i][j];

                JLabel label = new JLabel();
                label.setText(c.getPista() == 0 ? "" : String.valueOf(c.getPista()));
                label.setHorizontalAlignment(SwingConstants.CENTER);
//                
                JTextField txt = new JTextField();
                txt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                txt.setHorizontalAlignment(JTextField.CENTER);
                if(resolver){
                 txt.setText(c.getLetra() + "");
                }
               
                txt.setBorder(null);
              
                if (c.getLetra()==' '){
                    txt.setBackground(Color.DARK_GRAY);
                    txt.setOpaque(true);
                    txt.setFocusable(false);
                }
                pnlCrucigrama.add(label, new AbsoluteConstraints(x, y, 12, 12));
                pnlCrucigrama.add(txt, new AbsoluteConstraints(x, y, 33, 33));
                x += 30;
                txt.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        if (txt.getText().length() == 1) {
                            evt.consume();
                            char keyChar = evt.getKeyChar();
                            //System.out.println(keyChar + "/" + c.getLetra());
                            if (keyChar == c.getLetra()) {

                                txt.setText(c.getLetra() + "");
                            }
                        }
                    }
                });
            }
            y += 30;
            x = 5;
        }
        pnlCrucigrama.setVisible(true);
        pnlCrucigrama.setBackground(Color.BLACK);
        pnlCrucigrama.setOpaque(true);

    }

    public void setCrucigrama(Cuadro[][] crucigrama) {
        this.crucigrama = crucigrama;
    }

    public void resolver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void verificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
