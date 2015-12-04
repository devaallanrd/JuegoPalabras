/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.negocios;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;

public class ExtensionMetodosJuego implements IJuegoPalabras {
    private ICeldas[][] a;

    public ExtensionMetodosJuego() {
        this(0);
    }

    public ExtensionMetodosJuego(long seed) {
        Random rand = new Random(seed);
        this.a = new ICeldas[13][13];
        int letterA = 65;
        int blocknum = 1;
        int i = 0;
        while (i < 10) {
            int j = 0;
            while (j < 10) {
                CeldasJuego pc;
                int mostrarLetra = -1;
                if (!this.primo(blocknum)) {
                    mostrarLetra = rand.nextInt(26) + letterA;
                }
                int userIndex = -1;
                if (i == 0 || j == 0 || this.a[i - 1][j].getContenido() == -1 
                        || this.a[i][j - 1].getContenido() == -1) {
                    userIndex = blocknum;
                }
                this.a[i][j] = pc = new CeldasJuego(mostrarLetra, userIndex);
                ++blocknum;
                ++j;
            }
            ++i;
        }
    }

    private boolean primo(int x) {
        int rooti = (int)Math.sqrt(x);
        int i = 2;
        while (i <= rooti) {
            if ((double)x / (double)i == (double)(x / i)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    
    @Override
    public Map getListaPalabras() {
        Properties list = new Properties();
        Iterator directions = Direccion.iterator();
        while (directions.hasNext()) {
            Iterator words = this.getPistas((Direccion)directions.next()).values().iterator();
            while (words.hasNext()) {
                String word = (String)words.next();
                list.put(word, word);
                System.out.println(word);
            }
        }
        return list;
    }

    @Override
    public ICeldas getCelda(int x, int y) {
        return this.a[y][x];
    }

    @Override
    public Map getPistas(Direccion d) {
        TreeMap<Integer, String> list = new TreeMap<Integer, String>();
        StringBuffer sb = new StringBuffer();
        int wordNum = -1;
        int i = 0;
        while (i < 10) {
            int j = 0;
            while (j < 10) {
                int y;
                int x;
                if (d == Direccion.ACROSS) {
                    x = i;
                    y = j;
                } else {
                    x = j;
                    y = i;
                }
                if (this.a[x][y].getContenido() == -1) {
                    if (sb.length() > 0) {
                        list.put(new Integer(wordNum), sb.toString());
                        sb = new StringBuffer();
                    }
                } else {
                    if (sb.length() == 0) {
                        wordNum = this.a[x][y].getIndexUsuario();
                    }
                    sb.append((char)this.a[x][y].getContenido());
                }
                ++j;
            }
            if (sb.length() > 0) {
                list.put(new Integer(wordNum), sb.toString());
                sb = new StringBuffer();
            }
            ++i;
        }
        return list;
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(this.a[0].length, this.a.length);
    }
}

