/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

/**
 *
 * @author aallanrd
 */
public class Posicion {
     Direccion direccion;
        int x;
        int y;

        public Posicion(int x, int y, Direccion dir) {
            this.direccion = dir;
            this.x = x;
            this.y = y;
        }

        public boolean equals(Posicion p) {
            return this.x == p.x && this.y == p.y && this.direccion.equals(p.direccion);
        }

        @Override
        public String toString() {
            return "Position: (" + this.x + "," + this.y + " " + this.direccion + ")";
        }
}
