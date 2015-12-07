/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import crucigrama.modelo.Palabra;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class JuegoPalabrasResp implements IJuegoPalabras {
    
  
   
    
    private Map listaPalabras; //Investigar Map
    private Random rand;
    private ICeldas[][] cuadricula;
    private boolean siguiente = true;
    LinkedList<Palabra> construirMejorJuego;
    
    

    public JuegoPalabrasResp(String[] list) {
//        this(listaPalabras, init, true);
        this.actualizaTam();
        int retries = 0;
        do {
            try {
                construirMejorJuego = this.construirMejorJuego(list);
                retries = 0;
            }
            catch (Exception e) {
                //System.out.println(e);
                this.limpiarJuego();
                if ((double)(++retries) <= 1000.0) continue;
                retries = 1;
                this.actualizaTam();
            }
        } while (retries > 0);
        this.setUserIndices();
    }

    public LinkedList<Palabra> getConstruirMejorJuego() {
        return construirMejorJuego;
    }

    
    
    JuegoPalabrasResp(Map listaPalabras, long init, boolean test) {
        this.listaPalabras = listaPalabras;
        this.rand = new Random(init);
    }

    void genMatrix(Dimension dim) {
        this.genMatrix(dim.width, dim.height);
    }

    void genMatrix(int x, int y) {
        this.cuadricula = new ICeldas[x][y];
        int i = 0;
        while (i < x) {
            int j = 0;
            while (j < y) {
                this.cuadricula[i][j] = new Celda(-1, -1);
                ++j;
            }
            ++i;
        }
    }

    public Posicion obtenerMejorPosicion(String word) {
        int x = -1;
        int y = -1;
        Direccion dir = null;
        int puntos = -1;
        int area = this.cuadricula.length * this.cuadricula[0].length / 4;
        int i = 0;
        while (i < this.cuadricula.length) {
            int j = 0;
            while (j < this.cuadricula[0].length) {
                int nuevosPuntos;
                if (this.posicionPermitida(word, i, j, Direccion.Vertical) && 
                        ((    nuevosPuntos = this.puntosPalabra(word, i, j, Direccion.Vertical)) > puntos 
                          ||  nuevosPuntos == puntos && this.rand.nextInt(area) != 0))
                {
                    
                    puntos = nuevosPuntos;
                    x = i;
                    y = j;
                    dir = Direccion.Vertical;
                }
                if (this.posicionPermitida(word, i, j, Direccion.Horizontal) && 
                        ((nuevosPuntos = this.puntosPalabra(word, i, j, Direccion.Horizontal)) > puntos 
                        || nuevosPuntos == puntos && this.rand.nextInt(area) != 0)) {
                    puntos = nuevosPuntos;
                    x = i;
                    y = j;
                    dir = Direccion.Horizontal;
                }
                ++j;
            }
            ++i;
        }
        if (puntos == -1) {
            return null;
        }
       
        return new Posicion(x, y, dir);
    }

    private void limpiarJuego() {
        int i = 0;
        while (i < this.cuadricula.length) {
            int j = 0;
            while (j < this.cuadricula[0].length) {
                Celda cell = (Celda)this.getCelda(i, j);
                cell.setContents(-1);
                cell.setPalabra(null, Direccion.Horizontal);
                cell.setPalabra(null, Direccion.Vertical);
                ++j;
            }
            ++i;
        }
    }

   // String[] list = new String[3];
    
    //--------------------------------------------------------------------------------------
    
    private LinkedList<Palabra> construirMejorJuego(String[] list) {
        
      //  String[] hsbc =  new String[this.listaPalabras.keySet().size()];
       // String[] hsbc =  new String[this.list.length];
       // String[] list = (String[]) this.listaPalabras.keySet().toArray(hsbc);
       // String[] lista = (String[]) this.list.toArray(hsbc); 
       
     
        
        System.out.println(Arrays.toString(list) +"/ "+ list.length);
        LinkedList <Palabra> lista_mejorJuego = new LinkedList<>();
        
        int remaining = list.length;
        boolean[] used = new boolean[list.length];
        int[] scores = new int[list.length];
        int i = 0;
        while (i < list.length) {
            used[i] = false;
            ++i;
        }
        this.limpiarJuego();
        
        while (remaining > 0) {
            Posicion pos;
            int choose = 0;
            i = 0;
            while (i < list.length) {
                scores[i] = -1;
                
                ++i;
            }
           
            int n = 0;
            while (n < (list.length)) {
               
                if (!used[n] && (pos = this.obtenerMejorPosicion(list[n])) != null) {
                    scores[n] = this.puntosPalabra(list[n], pos);
                  
                }
                ++n;
            }
          
            int numbest = 0;
            int bestscore = scores[0];
            n = 0;
            while (n < list.length) {
                if (scores[n] == bestscore) {
                    ++numbest;
                }
              
                if (scores[n] > bestscore) {
                    bestscore = scores[n];
                    numbest = 1;
                }
                ++n;
            }
            if (bestscore < 0) {
                //throw new ExcepcionJuego(this);
            }
            int k = this.rand.nextInt(numbest);
            numbest = 0;
            n = 0;
           
            while (n < list.length) {
                if (scores[n] == bestscore) {
                    if (numbest == k) {
                        choose = n;
                    }
                    ++numbest;
                }
                ++n;
            }
            pos = this.obtenerMejorPosicion(list[choose]);
            this.colocarPalabra(list[choose], pos);
            lista_mejorJuego.add(new Palabra(list[choose], pos.x, pos.y, pos.direccion.getNombre()));
            used[choose] = true;
            --remaining;
        }
        return lista_mejorJuego;
    }

    void construirJuego() {
        //this.listaPalabras
        //ArrayList words = new ArrayList(this.listaPalabras.keyS);
       
       // LinkedList<String> words = new LinkedList(this.listaPalabras);
       LinkedList words = new LinkedList(Arrays.asList(this.listaPalabras)); 
       
        Collections.shuffle(words, this.rand);
        Iterator it = words.iterator();
        while (it.hasNext()) {
            String nextWord = (String)it.next();
            Posicion pos = this.obtenerMejorPosicion(nextWord);
           
            if (pos == null) {
               // throw new ExcepcionJuego(this);
            }
          
                this.colocarPalabra(nextWord, pos);
        }
    }

    int contarLetras() {
        int count = 0;
         LinkedList words = new LinkedList(Arrays.asList(this.listaPalabras)); 
       // Iterator it = this.listaPalabras.keySet().iterator();
        Iterator it = words.iterator();
        while (it.hasNext()) {
            String nextWord = (String)it.next();
            count += nextWord.length();
        }
        return count;
    }

    Dimension getTamMinimo() {
        int minArea = (int)Math.ceil((double)this.contarLetras() * 1.3333333333333333);
        int y = (int)Math.ceil(Math.sqrt(minArea));
        int x = Math.max(this.getPalabraMasLarga(), y);
        y = Math.min(y, (int)Math.ceil((double)minArea / (double)x));
        y = Math.max(y, this.getPalabraMasCorta());
        return new Dimension(x, y);
    }

    private void actualizaTam() {
        if (this.cuadricula == null) {
            Dimension dim = this.getTamMinimo();
            this.genMatrix(dim.width, dim.height);
        } else {
            Dimension dim = this.getDimension();
            if (this.siguiente) {
                ++dim.width;
            } else {
                ++dim.height;
            }
            this.siguiente = !this.siguiente;
            this.genMatrix(dim);
        }
    }

    int getPalabraMasLarga() {
        int masLarga = 0;
         LinkedList words = new LinkedList(Arrays.asList(this.listaPalabras)); 
       // Iterator it = this.listaPalabras.keySet().iterator();
        Iterator it = words.iterator();
        while (it.hasNext()) {
            String nextWord = (String)it.next();
            masLarga = Math.max(masLarga, nextWord.length());
        }
        return masLarga;
    }

    int getPalabraMasCorta() {
        int len = Integer.MAX_VALUE;
          LinkedList words = new LinkedList(Arrays.asList(this.listaPalabras)); 
        if (words.size() == 0) {
            return 0;
        }
       Iterator it = words.iterator();
        while (it.hasNext()) {
            len = Math.min(len, ((String)it.next()).length());
        }
        return len;
    }

    void colocarPalabra(String word, int x, int y, Direccion direction) {
        ((Celda)this.getCelda(x, y)).setPalabra(word, direction);
        word = word.toUpperCase();
        if (direction == Direccion.Horizontal) {
            int i = 0;
            while (i < word.length()) {
                ((Celda)this.getCelda(x + i, y)).setContents(word.charAt(i));
                ++i;
            }
        } else {
            int j = 0;
            while (j < word.length()) {
                ((Celda)this.getCelda(x, y + j)).setContents(word.charAt(j));
                ++j;
            }
        }
    }

    void colocarPalabra(String word, Posicion pos) {
        this.colocarPalabra(word, pos.x, pos.y, pos.direccion);
    }

    boolean cabePalabra(String word, int x, int y, Direccion direccion) {
        int len = word.length();
        if (direccion == Direccion.Horizontal) {
            return x + len <= this.cuadricula.length;
        }
        return y + len <= this.cuadricula[0].length;
    }

    boolean checkLetrasPrevias(String palabra, int x, int y, Direccion direccion) {
        int len = palabra.length();
        if (direccion == Direccion.Horizontal) {
            return x >= 1 && this.getCelda(x - 1, y).getContenido() != -1 
                    || x + len < this.cuadricula.length 
                    && this.getCelda(x + len, y).getContenido() != -1;
        }
        return y >= 1 && this.getCelda(x, y - 1).getContenido() != -1 
                || y + len < this.cuadricula[0].length 
                && this.getCelda(x, y + len).getContenido() != -1;
    }

    boolean comparaChoques(String palabra, int x, int y, Direccion direccion) {
        return this.contarLetrasChoque(palabra, x, y, direccion) != -1;
    }

    private int contarLetrasChoque(String palabra, int x, int y, Direccion direccion) {
        int len = palabra.length();
        int count = 0;
        if (direccion == Direccion.Horizontal) {
            int i = 0;
            while (i < len) {
                if (this.getCelda(x + i, y).getContenido() != -1) {
                    if (this.getCelda(x + i, y).getContenido() == Character.toUpperCase(palabra.charAt(i))) {
                        ++count;
                    } else {
                        return -1;
                    }
                }
                ++i;
            }
        } else {
            int i = 0;
            while (i < len) {
                if (this.getCelda(x, y + i).getContenido() != -1) {
                    if (this.getCelda(x, y + i).getContenido() == Character.toUpperCase(palabra.charAt(i))) {
                        ++count;
                    } else {
                        return -1;
                    }
                }
                ++i;
            }
        }
        return count;
    }

    boolean checkLetrasAdyacentes(String palabra, int x, int y, Direccion direccion) {
        if (direccion == Direccion.Horizontal) {
            int i = x;
            while (i < x + palabra.length()) {
                if (this.getCelda(i, y).getContenido() == -1 && (y > 0 && this.getCelda(i, y - 1).getContenido() != -1 || y + 1 < this.cuadricula[0].length && this.getCelda(i, y + 1).getContenido() != -1)) {
                    return true;
                }
                ++i;
            }
            return false;
        }
        int i = y;
        while (i < y + palabra.length()) {
            if (this.getCelda(x, i).getContenido() == -1 && (x > 0 && this.getCelda(x - 1, i).getContenido() != -1 || x + 1 < this.cuadricula.length && this.getCelda(x + 1, i).getContenido() != -1)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    boolean checkCaminoPalabra(String palabra, int x, int y, Direccion direccion) {
        int len = palabra.length();
        if (direccion == Direccion.Horizontal) {
            int i = x;
            while (i < x + len) {
                if (this.getPalabraEn(i, y, direccion) != null) {
                    return false;
                }
                ++i;
            }
        } else {
            int i = y;
            while (i < y + len) {
                if (this.getPalabraEn(x, i, direccion) != null) {
                    return false;
                }
                ++i;
            }
        }
        return true;
    }

    boolean posicionPermitida(String palabra, int x, int y, Direccion direccion) {
        return      this.cabePalabra(palabra, x, y, direccion)
                && !this.checkLetrasPrevias(palabra, x, y, direccion) 
                && !this.checkLetrasAdyacentes(palabra, x, y, direccion) 
                && this.comparaChoques(palabra, x, y, direccion) 
                && this.checkCaminoPalabra(palabra, x, y, direccion);
    }

    private void setUserIndices() {
        int k = 1;
        int i = 0;
       try{       
           while (i < this.cuadricula[0].length) {
            int j = 0;
            while (j < this.cuadricula.length) {
                if (this.getPalabraEn(j, i, Direccion.Horizontal) != null 
                        || this.getPalabraEn(j, i, Direccion.Vertical) != null) {
                    ((Celda)this.getCelda(j, i)).setUserIndex(k++);
                }
                ++j;
            }
            ++i;
        }}catch(Exception e){
            
        }
    }

    int puntosPalabra(String word, Posicion pos) {
        return this.puntosPalabra(word, pos.x, pos.y, pos.direccion);
    }

    int puntosPalabra(String word, int x, int y, Direccion direction) {
        word = word.toUpperCase();
        int score = direction.equals(Direccion.Vertical) ? 1 : 0;
        int count = this.contarLetrasChoque(word, x, y, direction);
        int len = word.length();
        if (direction == Direccion.Horizontal) {
            if (y != 0 && y != this.cuadricula[0].length - 1) {
                ++score;
            }
            if (this.getCelda(x, y).getContenido() == word.charAt(0)) {
                --score;
            }
            if (this.getCelda(x + len - 1, y).getContenido() == word.charAt(len - 1)) {
                --score;
            }
            score += count * 3;
        } else {
            if (x != 0 && x != this.cuadricula.length - 1) {
                ++score;
            }
            if (this.getCelda(x, y).getContenido() == word.charAt(0)) {
                --score;
            }
            if (this.getCelda(x, y + len - 1).getContenido() == word.charAt(len - 1)) {
                --score;
            }
            score += count * 6;
        }
        return score;
    }

    String getPalabraEn(int x, int y, Direccion direccion) {
        return ((Celda)this.getCelda(x, y)).getWord(direccion);
    }

    @Override
    public Map getListaPalabras() {
        return this.listaPalabras;
    }

    @Override
    public ICeldas getCelda(int x, int y) {
        return this.cuadricula[x][y];
    }

    @Override
    public Map getPistas(Direccion d) {
        TreeMap esperado = new TreeMap();
        int i = 0;
        while (i < this.cuadricula[0].length) {
            int j = 0;
            while (j < this.cuadricula.length) {
                String palabra = this.getPalabraEn(j, i, d);
                if (palabra != null) {
                    esperado.put(this.getCelda(j, i).getIndexUsuario(),
                    this.listaPalabras.get(palabra));
                }
                ++j;
            }
            ++i;
        }
        return esperado;
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(this.cuadricula.length, this.cuadricula[0].length);
    }

   

    
 

    

}

