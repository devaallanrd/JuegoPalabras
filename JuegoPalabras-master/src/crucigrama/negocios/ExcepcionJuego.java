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
   public class ExcepcionJuego
    extends Exception {
        private final /* synthetic */ JuegoPalabras juego;

        public ExcepcionJuego(JuegoPalabras miJuego) {
            this.juego = miJuego;
        }

        public ExcepcionJuego(JuegoPalabras miJuego, String msg) {
            super(msg);
            this.juego = miJuego;
        }
    }