/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public class Archer extends Personnage {
    
    private int nbFleches;
    
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
        nbFleches = nbFl;
    }
    
    public Archer(Archer a){
        this(a.getNom(), a.getPtVie(),a.getDegAtt(),a.getPtPar(), a.getPageAtt(), a.getPagePar(),a.getDistAttMax(),a.getPos(), a.getNbFleches());
    }
    
    public Archer(){
        this("Mohammed",100,50,50,75,25,3,new Point2D(0,0), 12);  
    }

    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }

    public int getNbFleches() {
        return nbFleches;
    }
}
