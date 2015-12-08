/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.negocios;

import crucigrama.dao.TiempoDAO;
import crucigrama.modelo.Tiempo;
import java.util.LinkedList;

/**
 *
 * @author Pedro
 */
public class TiempoBO {
    
     
    TiempoDAO tdao;

    public TiempoBO() {
        this.tdao = new TiempoDAO();
    }

    public LinkedList<Tiempo> buscarTiempos(double mejorTiempo) {
        return tdao.buscarTiempos(mejorTiempo);
    }

    
}
