/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author ian-frederickabondo
 */
public abstract class Nourriture extends ElementDeJeu implements Utilisable {
    private String type; //bonus ou malus
    private int effet; // par exemple 2 si +2
    private int nb_tours;
    private String caracteristique;// par exemple degat
    private int tourExpiration;
    
    public Nourriture(){
        super("feuille d'épinard",new Point2D(0,0));
        this.type = "bonus";
        this.effet = 2;
        this.nb_tours = 3;
        this.caracteristique = "pVie";
    }
    
    public Nourriture(String n, String t, int e, int nb, String c, Point2D p){
        super(n,p);
        this.type = t;
        this.effet = e;
        this.nb_tours = nb;
        this.caracteristique = c;
    }
    
    public Nourriture(Nourriture n){
        this(n.getNom(),n.getType(),n.getEffet(),n.getNb_tours(),n.getCaracteristique(),n.getPos());
    }
    
    @Override
    public abstract Nourriture copie();
    
    @Override
    public abstract void utiliser(Personnage perso, World world);
    
    @Override
    public abstract void retirer(Personnage perso);
    
    @Override
    public boolean estExpirer(World world){
        return world.getCompteurTour()>= this.tourExpiration;
    }    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public int getEffet() {
        return effet;
    }

    public void setEffet(int effet) {
        this.effet = effet;
    }

    public int getNb_tours() {
        return nb_tours;
    }

    public void setNb_tours(int nb_tours) {
        this.nb_tours = nb_tours;
    }
    
    @Override
    public String getCaracteristique() {
        return caracteristique;
    }

    public void setCaractéristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public int getTourExpiration() {
        return tourExpiration;
    }

    public void setTourExpiration(int tourExpiration) {
        this.tourExpiration = tourExpiration;
    }
    
}
