/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;


/**
 *
 * @author Mathys_Yael
 */
public class Epee extends Objet {
    
    private final String caracteristique = "dA";
    
    public Epee(String n, int dObj, Point2D p){        
        super(n,dObj, p);

    }   
    
    public Epee(Epee e){
        this(e.getNom(), e.getEffet(), e.getPos());
    }
    
    public Epee(){
        this("Epée", 30, new Point2D(0,0));
    }
    
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
    
    @Override
    public void retirer(Personnage perso){
        perso.setDegAtt(perso.getDegAtt()-this.getEffet());
    }
    
    @Override
    public boolean estExpirer(World world){
        return false;
    }
    
    /** Crée une copie indépendante de l'epee
    * @return une copie de l'epee
    */
    @Override
    public Epee copie(){
        return new Epee(this);
    }
    
    @Override
    public String getCaracteristique() {
        return caracteristique;
    }
    
}