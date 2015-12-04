/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.negocios;

public class CeldasJuego implements ICeldas  {
    private final int contenido;
    private final int IndexUsuario;

    public CeldasJuego(int content, int index) {
        this.contenido = content;
        this.IndexUsuario = index;
    }

    @Override
    public int getContenido() {
        return this.contenido;
    }

    @Override
    public int getIndexUsuario() {
        return this.IndexUsuario;
    }
}

