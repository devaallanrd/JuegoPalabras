
package crucigrama.dao;

import crucigrama.modelo.Palabra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

public class PalabraDAO {
    
        public boolean insertarPalabras(Palabra palabra) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = ConexionDAO.conexion();
            String sql = "INSERT INTO palabra(palabra, pista, id_categoria )"
                    + " VALUES (?,?,?)";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, palabra.getWord());
            stmt.setString(2, palabra.getPista());
            stmt.setInt(3, palabra.getCategoria());

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
    
          public LinkedList<Palabra> buscarPalabras(int categoria) {
        String sql = "SELECT * "
                + "  FROM public.palabra WHERE id_categoria = ?";
        
        try (Connection c = ConexionDAO.conexion()) {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                
                stmt.setInt(1, categoria);
                ResultSet rs = stmt.executeQuery();
               LinkedList<Palabra> plist = new LinkedList<>();
                while (rs.next()) {
                    Palabra palabra = new Palabra();
                    palabra.setWord(rs.getString("palabra"));
                    palabra.setPista(rs.getString("pista"));
                    plist.add(palabra);
                }
                return plist;

            } catch (Exception e) {
                System.out.println("1: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("2: " + e.getMessage());
        }
        return new LinkedList();

    } 

    public LinkedList buscarCategorias() {
    String sql = "SELECT * "
                + "  FROM public.categorias  ";
        
        try (Connection c = ConexionDAO.conexion()) {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                
                
                ResultSet rs = stmt.executeQuery();
               LinkedList<Palabra> plist = new LinkedList<>();
                while (rs.next()) {
                    Palabra palabra = new Palabra();
                    palabra.setWord(rs.getString("palabra"));
                    palabra.setPista(rs.getString("pista"));
                    plist.add(palabra);
                }
                return plist;

            } catch (Exception e) {
                System.out.println("1: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("2: " + e.getMessage());
        }
        return new LinkedList();
    }
}
