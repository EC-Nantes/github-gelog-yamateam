/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author dytri
 */
public class ListStringTokenizer {
    
    public ArrayList<String> decoupe(String ligne){
        ArrayList<String> liste_mots = new ArrayList<>();
        String delimiteurs = "|";
        
        StringTokenizer tokenizer = new StringTokenizer(ligne, delimiteurs);
        
        while(tokenizer.hasMoreTokens()){
            String mot = tokenizer.nextToken();

            liste_mots.add(mot);
            
        }
        
        return liste_mots;
    }
    
}
