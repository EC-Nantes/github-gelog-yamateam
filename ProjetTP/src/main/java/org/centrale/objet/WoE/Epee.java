
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;


/**
 * Un type d'objet
 * @author Mathys_Yael
 */
public class Epee extends Objet implements Utilisable{
    
    /**description du paramètre sur lequel l'objet agit*/
    private final String caracteristique = "dA";
    
    /**
     * Constructeur principal
     * @param n
     * @param dObj
     * @param p
     */
    public Epee(String n, int dObj, Point2D p){        
        super(n,dObj, p);

    }   
    
    /**
     * Constructeur de copie
     * @param e
     */
    public Epee(Epee e){
        this(e.getNom(), e.getEffet(), e.getPos());
    }
    
    /**
     * Constructeur par défaut
     */
    public Epee(){
        this("Epée", 30, new Point2D(0,0));
    }
    
    /**
     * le personnage s'équipe de l'épée, potentiellement en retirant l'épée qu'il avait déjà équipé
     * @param perso
     * @param world
     */
    @Override
    public void utiliser(Personnage perso, World world){
        
        //retire l'épée déjà équipée et la remet dans l'inventaire
        for(Utilisable u : world.getJoueur().getEffets()){
            if(u instanceof Epee){
                world.getJoueur().getInventaire().add(u);
                world.getJoueur().getEffets().remove(u);
                u.retirer(perso);
                break;
            }
        }
        
        perso.setDegAtt(perso.getDegAtt()+this.getEffet());
        System.out.println("L'épée '" + this.getNom() + "' a été équipée par " + perso.getNom());
    }
    
    /**
     * déséquipe l'épée au personnage
     * @param perso
     */
    @Override
    public void retirer(Personnage perso){
        perso.setDegAtt(perso.getDegAtt()-this.getEffet());
    }
    
    /**
     * implémente une méthode imposée par l'interface utilisable, mais l'épée n'expire jamais
     * @param world
     * @return boolean false
     */
    @Override
    public boolean estExpirer(World world){
        return false;
    }
    
    /** 
    * Crée une copie indépendante de l'epee
    * @return une copie de l'epee
    */
    @Override
    public Epee copie(){
        return new Epee(this);
    }
    
    /**
     * Retourne la caracteristique sur lequel l'épée agit
     * @return String caracteristique
     */
    @Override
    public String getCaracteristique() {
        return caracteristique;
    }
    
}
