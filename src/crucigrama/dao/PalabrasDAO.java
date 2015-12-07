/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author aallanrd
 */
public class PalabrasDAO {
    
     Map<String, String> listaPalabras = new HashMap<>();
       

    public PalabrasDAO() {
            initPalabras();
    }
    private void initPalabras(){
        listaPalabras.put("Allan", "It's me");
        listaPalabras.put("Rojas", "It's me");
        listaPalabras.put("Duran", "It's me");
    }

    public Map<String, String> getListaPalabras() {
        return listaPalabras;
    }

    public void setListaPalabras(Map<String, String> listaPalabras) {
        this.listaPalabras = listaPalabras;
    }

   

  
    
        
}
