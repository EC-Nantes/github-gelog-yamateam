
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Type d'objet
 * @author dytri
 */
public class PotionSoin extends Objet implements Utilisable{
    /***/
    private final String caracteristique = "pVie";
    
    /**
     * Constructeur principal
     * @param n String son nom
     * @param pSoin int le nombre de points de vie qu'elle soigne
     * @param p Point2D sa position
     */
    public PotionSoin(String n, int pSoin, Point2D p){
        super(n,pSoin, p);
    }   
    
    /**
     * Constructeur de copie
     * @param e PotionSoin
     */
    public PotionSoin(PotionSoin e){
        this(e.getNom(), e.getEffet(), e.getPos());
    }
    
    /**
     * Constructeur par défaut
     */
    public PotionSoin(){
        this("Potion de Soin", 30, new Point2D(0,0));
    }
    
    /**
     * Permet au personnage passé en argument d'utiliser la potion et donc de se rajouter des PV
     * @param perso
     * @param world
     */
    @Override
    public void utiliser(Personnage perso, World world){
        perso.setPtVie(perso.getPtVie()+this.getEffet());
        System.out.println("Une potion de soin (+" + this.getEffet() + "pv) a été utilisée par " + perso.getNom());
    }
    
    /**
     * Implémente une méthode imposée par l'interface mais la potion de soin s'utilise instantanément
     * @param perso
     */
    @Override
    public void retirer(Personnage perso){
        
    }
    
    /**
     * Implémente une méthode imposée par l'interface mais la potion de soin s'utilise instantanément
     * @param world
     * @return true
     */
    @Override
    public boolean estExpirer(World world){
        return true;
    }

    /** 
    * Crée une copie indépendante de l'objet 
    * @return une copie de la potion
    */
    @Override
    public PotionSoin copie(){
        return new PotionSoin(this);
    }
    
    /**
     * 
     * @return
     */
    @Override
    public String getCaracteristique() {
        return caracteristique;
    }
    
    

    
}
