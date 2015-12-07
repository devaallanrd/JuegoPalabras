/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.negocios;

import java.util.Arrays;
import java.util.Iterator;

public class Direccion {
    public static final Direccion Horizontal = new Direccion("Horizontal");
    public static final Direccion Vertical = new Direccion("Vertical");
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
        return Arrays.asList(Horizontal, Vertical).iterator();
    }
}

