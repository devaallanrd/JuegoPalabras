package crucigrama.dao;



import crucigrama.modelo.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
public class JugadorDAO {
        
     public boolean registrar(Jugador jugador) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = ConexionDAO.conexion();
            String sql = "INSERT INTO jugador(nombre, contraseña, )"
                    + " VALUES (?,?)";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, jugador.getNombre());
            stmt.setString(2, jugador.getPass());
            ;

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }

        return false;
    }

    public Jugador autentificarJugador(String name, String pass) {
        String sql = "SELECT id, nombre, contraseña "
                + "  FROM public.jugador WHERE nombre = ? and contraseña = ?";
        
        try (Connection c = ConexionDAO.conexion()) {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                
                stmt.setString(1, name);
                stmt.setString(2, pass);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    
                    return cargarJugador(rs);
                }

            } catch (Exception e) {
                System.out.println("1: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("2: " + e.getMessage());
        }
        return new Jugador();

    }

    private Jugador cargarJugador(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rs.getInt("id"));
        j.setNombre(rs.getString("nombre"));
        j.setPass(rs.getString("contraseña"));
        return j;
    }
    
//    public Jugador  buscarJugador(String name, String pass){
//        
//        Jugador jug = null;
//        if(name.equals("Allan")&&(pass.equals("1234"))){
//            jug =  new Jugador(1,"Allan", "1234", 0);
//        }
//        else{
//          jug = null;
//        }
//        return jug;
//    }
    
}

