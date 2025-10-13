/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv
 */
public class Joueur {
    
    private Personnage perso;
    
    
    public boolean choixPerso(String nom,String type){
        switch(type){
            case "Guerrier" -> {
                this.perso = new Guerrier(nom);
                return true;
            }
            case "Archer" -> {
                this.perso = new Archer(nom);
                return true;
            }
            default -> {
                return false;
            }
                 
                       
        }
    }
    
    
}
