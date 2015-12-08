/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crucigrama.dao;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aallanrd
 */
public class PalabrasDAO {
    
     Map<String, String> listaPalabras = new HashMap<>();
       

    public PalabrasDAO(String cat) {
            listaPalabras = new HashMap<>();
            getPalabrasporCategoria(cat);
            
    }
   

    private void getPalabrasporCategoria(String cat){

          
          loadGeneral();

        
    }
    
    public void loadGeneral(){
        listaPalabras.put("Allan", "It's me");
        listaPalabras.put("Rojas", "It's me");
        listaPalabras.put("Duran", "It's me");
        listaPalabras.put("Josue", "It's me");
        listaPalabras.put("Daniel", "It's me");
        listaPalabras.put("Crucigrama", "It's me");
        
    }
//     public void loadDeportes(){
//       
//       
//        listaPalabras.put("Tu", "It's me");
//        listaPalabras.put("Futbol", "It's me");
//        
//        listaPalabras.put("Basket", "It's me");
//    }
//      public void loadGeografia(){
//        listaPalabras.put("CostaRica", "It's me");
//        listaPalabras.put("SanJose", "It's me");
//        listaPalabras.put("Avion", "It's me");
//    }
//       public void loadMusica(){
//        listaPalabras.put("Guitarra", "It's me");
//        listaPalabras.put("Bateria", "It's me");
//        listaPalabras.put("Bajo", "It's me");
//    }
    
    public Map<String, String> getListaPalabras() {
        return listaPalabras;
    }

    public void setListaPalabras(Map<String, String> listaPalabras) {
        this.listaPalabras = listaPalabras;
    }

   

  
    
        
}
