/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public class TestWoE {
    
    public static void main(String[] args){
        World w = new World();
        w.creerMondeAlea();
        
        w.robin.affiche();
        w.peon.affiche();
        w.bugs1.affiche();
        w.bugs2.affiche();
        
        
        w.robin.deplace();
        w.peon.deplace();
        w.bugs1.deplace();
        w.bugs2.deplace();
        
        
        w.robin.affiche();
        w.peon.affiche();
        w.bugs1.affiche();
        w.bugs2.affiche();
        
    }
    
    
}
