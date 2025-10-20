/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public class PotionSoin extends Objet{
    
    private final String caracteristique = "pVie";
    
    public PotionSoin(String n, int pSoin, Point2D p){
        super(n,pSoin, p);
    }   
    
    public PotionSoin(PotionSoin e){
        this(e.getNom(), e.getEffet(), e.getPos());
    }
    
    public PotionSoin(){
        this("Potion de Soin", 30, new Point2D(0,0));
    }
    
    @Override
    public void utiliser(Personnage perso, World world){
        perso.setPtVie(perso.getPtVie()+this.getEffet());
        System.out.println("Une potion de soin (+" + this.getEffet() + "pv) a été utilisée par " + perso.getNom());
    }
    
    @Override
    public void retirer(Personnage perso){
        
    }
    
    @Override
    public boolean estExpirer(World world){
        return true;
    }

    /** Crée une copie indépendante de l'objet 
    * @return une copie de la potion
    */
    @Override
    public PotionSoin copie(){
        return new PotionSoin(this);
    }
    
    @Override
    public String getCaracteristique() {
        return caracteristique;
    }
    
    

    
}