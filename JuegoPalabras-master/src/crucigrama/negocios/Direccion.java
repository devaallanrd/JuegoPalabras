/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.negocios;

import java.util.Arrays;
import java.util.Iterator;

public class Direccion {
    public static final Direccion ACROSS = new Direccion("Across");
    public static final Direccion DOWN = new Direccion("Down");
    private final String name;

    private Direccion(String name) {
        this.name = name;
    }

    public String getNombre() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Direction:" + this.name;
    }

    public static Iterator iterator() {
        return Arrays.asList(ACROSS, DOWN).iterator();
    }
}

