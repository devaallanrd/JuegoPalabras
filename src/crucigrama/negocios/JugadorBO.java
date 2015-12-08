/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

import crucigrama.dao.JugadorDAO;
import crucigrama.modelo.Jugador;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class JugadorBO {
    
    JugadorDAO jdao;

    public JugadorBO() {
        jdao = new JugadorDAO();
    }

    public boolean autentificarContrasenna(Jugador u) {
        return u.getPass().equals(u.getPass());

    }

    public boolean registrar(Jugador j) throws SQLException {
        return jdao.registrar(j);
    }

    public Jugador autentificarJugador(String name, String pass) {
        return jdao.autentificarJugador(name,pass);

    }
}
