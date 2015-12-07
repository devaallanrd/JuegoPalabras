/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.modelo;

/**
 *
 * @author Pedro
 */
public class Cuadro {
    private int pista;
    char  letra;
    private boolean negro;

    public Cuadro() {
         pista = 0;
         letra = ' ';
         negro = false;
    }
    
    

    public Cuadro(int Pista, char letra, boolean negro) {
        this.pista = Pista;
        this.letra = letra;
        this.negro = negro;
    }

   
    public int getPista() {
        return pista;
    }

    public void setPista(int numPista) {
        this.pista = numPista;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public boolean esNegro() {
        return negro;
    }

    public void setNegro(boolean negro) {
        this.negro = negro;
    }

    
}
