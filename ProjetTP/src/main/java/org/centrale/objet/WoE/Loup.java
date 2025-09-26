/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv
 */
public class Loup extends Monstre{

    /**
     * @param n
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param dMax
     * @param p
     */
    public Loup(String n,int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int dMax, Point2D p) {
        super(n, ptVie, degAtt, ptPar, pageAtt, pagePar, dMax, p);
    }

    /**
     *
     */
    public Loup() {
        super("Loup");
    }
    
    public Loup(String n) {
        super(n);
    }

    /**
     *
     * @param l
     */
    public Loup(Loup l) {
        super(l);
    }
    
    
    /**
     * @param c
     * Fait ce combattre cet objet avec la créature rentrée en paramètre
     */
    public void combattre(Creature c){
        
    }
        
}   