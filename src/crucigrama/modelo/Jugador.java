/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.modelo;

/**
 *
 * @author aallanrd
 */
public class Jugador {
    
    int id ;
    String nombre;
    String pass;
    int time;

    public Jugador() {
    }

    public Jugador(int id,String nombre, String pass, int time) {
        this.id= id;
        this.nombre = nombre;
        this.pass = pass;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", time=" + time + '}';
    }
    
    
}
