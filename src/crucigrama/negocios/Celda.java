/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

import java.io.Serializable;

public class Celda implements ICeldas, Serializable {
        private int contents;
        private int userIndex;
        private String wordAcross;
        private String wordDown;

        public Celda(int contents, int userIndex) {
            this.contents = contents;
            this.userIndex = userIndex;
        }

        @Override
        public int getContenido() {
            return this.contents;
        }

        public void setContents(int contents) {
            this.contents = contents;
        }

        @Override
        public int getIndexUsuario() {
            return this.userIndex;
        }

        public void setUserIndex(int ui) {
            this.userIndex = ui;
        }

        public String getWord(Direccion direccion) {
            if (direccion == Direccion.Horizontal) {
                return this.wordAcross;
            }
            return this.wordDown;
        }

        public void setPalabra(String palabra, Direccion direccion) {
            if (direccion == Direccion.Horizontal) {
                this.wordAcross = palabra;
            } else {
                this.wordDown = palabra;
            }
        }
    }
