/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

import crucigrama.dao.PalabraDAO;
import crucigrama.modelo.Palabra;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Pedro
 */
public class PalabraBO {
    
    PalabraDAO pdao;

    public PalabraBO() {
        this.pdao = new PalabraDAO();
    }
    public boolean insertarPalabras(Palabra palabra) throws SQLException{
    
        return pdao.insertarPalabras(palabra);
    }

    public LinkedList buscarPalabras(int categoria) {
        return pdao.buscarPalabras(categoria);
    }

    public LinkedList buscarCategorias() {
       return pdao.buscarCategorias();
    }

    
    
}
