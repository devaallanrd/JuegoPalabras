/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.dao;

import crucigrama.modelo.Palabra;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author aallanrd
 */
public class PalabrasDAO {
    
     PalabraDAO palabra;
     Map<String, String> listaPalabras = new HashMap<>();
       

    public PalabrasDAO(int cat) {
            listaPalabras = new HashMap<>();
            getPalabrasporCategoria(cat);
            palabra = new PalabraDAO();
            
    }
   

    private LinkedList<Palabra> getPalabrasporCategoria(int cat){
LinkedList<Palabra> buscarPalabras = palabra.buscarPalabras(cat);
        try{
         
         int c = 0;
         while(c!=buscarPalabras.size()){
            Palabra get = buscarPalabras.get(c);
            listaPalabras.put(get.getWord(), get.getPista());
         }
         
        }catch(Exception e){
            System.out.println("Ir a PalabrasDAO- Error Conexion");
        }
        return buscarPalabras;
    }
    
    
    
    public Map<String, String> getListaPalabras() {
        return listaPalabras;
    }

    public void setListaPalabras(Map<String, String> listaPalabras) {
        this.listaPalabras = listaPalabras;
    }

   

  
    
        
}
