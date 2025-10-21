/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** Permet de découper une ligne avec le signe '|' en liste de groupe de mots 
 *
 * @author yaelv, Mathys
 */
public class ListStringTokenizer {
    
    /**Découpe la ligne en utilisant un StringTokenizer.
     *
     * @param ligne
     * @return liste de groupe de mots qui étaient séparés par '|' dans la ligne
     */
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
