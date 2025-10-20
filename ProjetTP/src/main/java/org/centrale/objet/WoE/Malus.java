/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author ian-frederickabondo
 */
public class Malus extends Nourriture {
    public Malus(){
        this.setNom("feuille de choux");
        this.setType("malus"); 
        this.setEffet(2); 
        this.setNb_tours(3);
        this.setCaractéristique("dA");
        this.setPos(new Point2D(0,0)); 
    }
    public Malus(String n, String t, int e, int nb, String c, Point2D p){
        super(n,t,e,nb,c,p);
    }
    
    public Malus(Malus b){
        this(b.getNom(), b.getType(), b.getEffet(), b.getNb_tours(), b.getCaracteristique(), b.getPos());
    }
    
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
    
    /** Crée une copie indépendante de l'epee
    * @return une copie de l'epee
    */
    @Override
    public Malus copie(){
        return new Malus(this);
    }
}
