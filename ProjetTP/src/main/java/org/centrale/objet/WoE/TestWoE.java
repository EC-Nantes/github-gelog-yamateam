/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author mathys,yaël
 */
public class TestWoE {
    
    public static void main(String[] args){
        
        World w = new World(10000);
        w.creerMondeAlea(1000000);


        long tpsDebut1 = System.nanoTime();
        int totPV = 0;
        for(int i=0;i<w.getListe_perso().size();i++){
            totPV += w.getListe_perso().get(i).getPtVie();
        }
        long tpsFin1 = System.nanoTime();
        System.out.println("Temps écoulé (ns): " + (tpsFin1-tpsDebut1));
        System.out.println(totPV);

                
        long tpsDebut2 = System.nanoTime();
        totPV = 0;
        for(Personnage p : w.getListe_perso()){
            totPV += p.getPtVie();
        }
        long tpsFin2 = System.nanoTime();
        System.out.println("Temps écoulé boucle itéré(ns): " + (tpsFin2-tpsDebut2));

    }
}
    
 
