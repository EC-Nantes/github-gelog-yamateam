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

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        World w = new World(50);
        w.creerMondeAlea();


        long tpsDebut = System.nanoTime();
        int totPV = 0;
        for(int i=0;i<100;i++){
            totPV += w.getListe_perso().get(i).getPtVie();
        }
        long tpsFin = System.nanoTime();
        System.out.println("Temps écoulé (ns): " + (tpsFin-tpsDebut));

    }


}
