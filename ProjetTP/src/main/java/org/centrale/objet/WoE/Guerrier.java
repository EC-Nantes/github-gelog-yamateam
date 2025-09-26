/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv
 */
public class Guerrier extends Personnage{
    
    
    /**
     *
     * @param n
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param dMax
     * @param p
     */
    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
    }
    
    /**
     *
     * @param guerr
     */
    public Guerrier(Guerrier guerr){
        this(guerr.getNom(), guerr.getPtVie(),guerr.getDegAtt(),guerr.getPtPar(), guerr.getPageAtt(), guerr.getPagePar(),guerr.getDistAttMax(),new Point2D(guerr.getPos()));
    }
    
    /**
     *
     */
    public Guerrier(){
        this("Général DJOMBY",200,20,50,75,25,1,new Point2D(0,0));  
    }

    /**
     * @param c
     * Fait ce combattre cet objet avec la créature rentrée en paramètre
     */
    public void combattre(Creature c){
        
    }
}
