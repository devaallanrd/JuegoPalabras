/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.negocios;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.Map;

public interface IJuegoPalabras
extends Serializable {
    public String[] getListaPalabras();

    public ICeldas getCelda(int var1, int var2);

   // public Map getPistas(Direccion var1);

    public Dimension getDimension();
}

