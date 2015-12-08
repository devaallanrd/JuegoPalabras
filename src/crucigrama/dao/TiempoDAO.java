/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.dao;

import crucigrama.modelo.Tiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Pedro
 */
public class TiempoDAO {
    
        public boolean insertarTiempos(Tiempo tiempo) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = ConexionDAO.conexion();
            String sql = "INSERT INTO tiempo(duracion, jugador, categoria )"
                    + " VALUES (?,?,?)";
            stmt = c.prepareStatement(sql);
            stmt.setDouble(1, tiempo.getDuracion());
            stmt.setInt(2, tiempo.getJugador());
            stmt.setInt(3, tiempo.getCategoria());

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
    
          public LinkedList<Tiempo> buscarTiempos(double mejorTiempo) {
        String sql = "SELECT tiempo.id_jugador, tiempo.duracion, jugador.nombre "
                + "FROM public.tiempo INNER JOIN public.jugador "
                + "WHERE jugador.id = tiempo.id_jugador and tiempo.duracion < ?";
        
        try (Connection c = ConexionDAO.conexion()) {
            try (PreparedStatement stmt = c.prepareStatement(sql)) {
                
                stmt.setDouble(1, mejorTiempo);
                ResultSet rs = stmt.executeQuery();
               LinkedList<Tiempo> tlist = new LinkedList<>();
                while (rs.next()) {
                    Tiempo tiempo = new Tiempo();
                    tiempo.setDuracion(rs.getFloat("duracion"));
                    tiempo.setJugadorName(rs.getString("nombre"));
                    tlist.add(tiempo);
                }
                return tlist;

            } catch (Exception e) {
                System.out.println("1: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("2: " + e.getMessage());
        }
        return new LinkedList();

    } 
}
