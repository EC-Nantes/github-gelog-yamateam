/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv
 */
public class Paysan extends Personnage {
    
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
    public Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
    }
    
    /**
     *
     * @param p
     */
    public Paysan(Paysan p){
        this(p.getNom(), p.getPtVie(),p.getDegAtt(),p.getPtPar(), p.getPageAtt(), p.getPagePar(),p.getDistAttMax(),new Point2D(p.getPos()));
    }
    
    /**
     *
     */
    public Paysan(){
        this("Jean",100,50,50,75,25,3,new Point2D(0,0));  
    }
    
    public Paysan(String nom){
        this(nom,100,50,50,75,25,3,new Point2D(0,0));  
    }
    
    /** Crée une copie indépendante de la créature 
     * @return un paysan 
     */
    @Override
    public Paysan copie(){
        return new Paysan(this);
    }
}