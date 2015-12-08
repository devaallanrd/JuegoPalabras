/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.modelo;

/**
 *
 * @author Pedro
 */
public class Tiempo {
    int id;
    float duracion;
    int jugador;
    String jugadorName;
    int categoria;

    public Tiempo(int id, float duracion, int jugador, int categoria) {
        this.id = id;
        this.duracion = duracion;
        this.jugador = jugador;
        this.categoria = categoria;
    }

    public Tiempo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getJugadorName() {
        return jugadorName;
    }

    public void setJugadorName(String jugadorName) {
        this.jugadorName = jugadorName;
    }
    

    @Override
    public String toString() {
        return "tiempo{" + "id=" + id + ", duracion=" + duracion + ", jugador=" + jugador + ", categoria=" + categoria + '}';
    }
    
    
    
}
