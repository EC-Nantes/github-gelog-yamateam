
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Classe abstraite représentant tous les éléments de jeu non vivants
 * @author Mathys_Yael
 */
public abstract class Objet extends ElementDeJeu implements Utilisable{
    
    private int effet;
    
    /**
     *
     * @param n
     * @param eff
     * @param p
     */
    public Objet(String n,int eff, Point2D p){
        super(n,p);
        this.effet = eff;
    }
    
    /**
     *
     * @param obj
     */
    public Objet(Objet obj){
        this(obj.getNom(),obj.getEffet(), obj.getPos());
    }
    
    /**
     *
     */
    public Objet(){
        this("Objet",5, new Point2D(0,0));
    }
    
    /**
     *
     * @return
     */
    @Override
    public abstract Objet copie();
    
    /**
     *
     * @param perso
     * @param world
     */
    @Override
    public abstract void utiliser(Personnage perso, World world);
    
    /**
     *
     * @param perso
     */
    @Override
    public abstract void retirer(Personnage perso);
    
    /**
     *
     */
    public void utiliser(){
    }
    
    /**
     *
     * @return
     */
    public int getEffet() {
        return effet;
    }

    /**
     *
     * @param effet
     */
    public void setEffet(int effet) {
        this.effet = effet;
    }
   
    
    
}
