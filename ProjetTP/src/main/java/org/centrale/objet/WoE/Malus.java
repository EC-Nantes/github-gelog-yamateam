/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv, mathys
 */
public class Malus extends Nourriture {

    /**Constructeur par défaut.
     *
     */
    public Malus(){
        this.setNom("feuille de choux");
        this.setType("malus"); 
        this.setEffet(2); 
        this.setNb_tours(3);
        this.setCaractéristique("dA");
        this.setPos(new Point2D(0,0)); 
    }

    /**Constructeur avec paramètres.
     *
     * @param n
     * @param t
     * @param e
     * @param nb
     * @param c
     * @param p
     */
    public Malus(String n, String t, int e, int nb, String c, Point2D p){
        super(n,t,e,nb,c,p);
    }
    
    /**Constructeur de copie
     *
     * @param b
     */
    public Malus(Malus b){
        this(b.getNom(), b.getType(), b.getEffet(), b.getNb_tours(), b.getCaracteristique(), b.getPos());
    }
    
     /**Applique l'effet de l'objet au perso
     * 
     * @param perso
     * @param world 
     */
    @Override
    public void utiliser(Personnage perso, World world){
        switch(this.getCaracteristique()){
            case "dA":
                perso.setDegAtt(perso.getDegAtt()-this.getEffet());
                break;
            case "pVie":
                perso.setPtVie(perso.getPtVie()-this.getEffet());
                break;    
            case "pPar":
                perso.setPtPar(perso.getPtPar()-this.getEffet());
                break;
            case "paAtt": // on part du proincipe que les pourcentage peuvent dépasser 100
                perso.setPageAtt(perso.getPageAtt()-this.getEffet());
                break;
            case "paPar":
                perso.setPagePar(perso.getPagePar()-this.getEffet());
                break;
            case "dMax":
                perso.setDistAttMax(perso.getDistAttMax()-this.getEffet());
                break;
            default:
                System.out.println("effet inconnu");
                break;
        }
        
        //Calcul tour expiration
        this.setTourExpiration(world.getCompteurTour()+this.getNb_tours());
        System.out.println("Le bonus " + this.getNom() + " a été appliqué à " + perso.getNom() + " pour " + this.getTourExpiration() + " tours");
    }
    
    /**Retire l'effet de l'objet au perso
     * 
     * @param perso
     */
    @Override
    public void retirer(Personnage perso){
        switch(this.getCaracteristique()){
            case "pVie"://l'effet ptVie n'est pas retirable
                
                break;             
            case "dA":
                perso.setDegAtt(perso.getDegAtt()+this.getEffet());
                break;
            case "pPar":
                perso.setPtPar(perso.getPtPar()+this.getEffet());
                break;
            case "paAtt": // on part du proincipe que les pourcentage peuvent dépasser 100
                perso.setPageAtt(perso.getPageAtt()+this.getEffet());
                break;
            case "paPar":
                perso.setPagePar(perso.getPagePar()+this.getEffet());
                break;
            case "dMax":
                perso.setDistAttMax(perso.getDistAttMax()+this.getEffet());
                break;
        }
    }
    
    /**
     * Fais une copie indépendante de l'objet
     * @return utilisable_copie
     */
    @Override
    public Malus copie(){
        return new Malus(this);
    }
}
