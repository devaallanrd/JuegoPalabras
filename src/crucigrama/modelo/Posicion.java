/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.modelo;

import crucigrama.negocios.Direccion;

/**
 *
 * @author aallanrd
 */
public class Posicion {
        private Direccion direccion;
        private int x;
        private int y;

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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
        
}
