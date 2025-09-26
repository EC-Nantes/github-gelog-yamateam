/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author dytri
 */
public class Archer extends Personnage {
    
    private int nbFleches;
    
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
     * @param nbFl
     */
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
        nbFleches = nbFl;
    }
    
    /**
     *
     * @param a
     */
    public Archer(Archer a){
        this(a.getNom(), a.getPtVie(),a.getDegAtt(),a.getPtPar(), a.getPageAtt(), a.getPagePar(),a.getDistAttMax(),new Point2D(a.getPos()), a.getNbFleches());
    }
    
    /**
     *
     */
    public Archer(){
        this("Mohammed",100,50,50,75,25,3,new Point2D(0,0), 12);  
    }
    
    public Archer(String n){
        this(n,100,50,50,75,25,3,new Point2D(0,0), 12);  
    }

    /**
     *
     * @param nbFleches
     */
    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }

    /**
     *
     * @return le nombre de flèches
     */
    public int getNbFleches() {
        return nbFleches;
    }
    
    /**
     * @param c
     * Fait ce combattre cet objet avec la créature rentrée en paramètre
     */
public void combattre(Creature c){
        Random randInt = new Random();
        
        if (this.getPos().distance(c.getPos()) <= 1.5){
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= this.getPageAtt()){
                int RandDef = randInt.nextInt(100) + 1;
                
                if (RandDef > c.getPagePar()){
                    c.subirDegat(this.getDegAtt());
                }
                else{
                    c.subirDegat(this.getDegAtt()-c.getPtPar());
                }
            }
        }
        else if (this.getPos().distance(c.getPos())>1.5 && this.getPos().distance(c.getPos()) <= this.getDistAttMax()){
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= this.getPageAtt()){
                c.subirDegat(this.getDegAtt());
            }
        }
    }

}
