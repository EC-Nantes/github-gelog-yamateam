
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Type de personnage non jouable, ne combat pas
 * @author yaelv
 */
public class Paysan extends Personnage {
    
    /**
     * Constructeur principal
     * @param n String son nom
     * @param pV int 
     * @param dA int dégâts d'attaque
     * @param pPar int points de parade
     * @param paAtt int pourcentage de réussite d'attaque
     * @param paPar int pourcentage de réussite de parade
     * @param dMax int distance maximale d'attaque
     * @param p Point2D sa position
     */
    public Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
    }
    
    /**
     * Constructeur de copie
     * @param p
     */
    public Paysan(Paysan p){
        this(p.getNom(), p.getPtVie(),p.getDegAtt(),p.getPtPar(), p.getPageAtt(), p.getPagePar(),p.getDistAttMax(),new Point2D(p.getPos()));
    }
    
    /**
     * Constructeur par défaut
     */
    public Paysan(){
        this("Jean",100,50,50,75,25,3,new Point2D(0,0));  
    }
    
    /**
     * Constructeur en initialisant seulement son nom
     * @param nom
     */
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
