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
        listaPalabras.put("Pedro", "It's me");
        listaPalabras.put("juan", "It's me");
        listaPalabras.put("Mendez", "It's me");
        listaPalabras.put("Josue", "It's me");
        listaPalabras.put("Arroz", "It's me");
        listaPalabras.put("Boton", "It's me");
        listaPalabras.put("Pez", "It's me");
        listaPalabras.put("Consigo", "It's me");
        listaPalabras.put("Mismo", "It's me");
        listaPalabras.put("Planea", "It's me");
        listaPalabras.put("Sustituir", "It's me");
        listaPalabras.put("con", "It's me");
        listaPalabras.put("aquella", "It's me");
        listaPalabras.put("pasada", "It's me");
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
