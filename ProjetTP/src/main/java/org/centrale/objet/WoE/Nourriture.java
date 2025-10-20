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
    private String nom;
    private String type; //bonus ou malus
    private int effet; // par exemple 2 si +2
    private int nb_tours;
    private String caracteristique;// par exemple degat
    private Point2D pos;
    private int tourExpiration;
    
    public Nourriture(){
        this.nom = "feuille d'épinard";
        this.type = "bonus";
        this.effet = 2;
        this.nb_tours = 3;
        this.caracteristique = "pVie";
        this.pos = new Point2D(0,0);
    }
    
    public Nourriture(String n, String t, int e, int nb, String c, Point2D p){
        this.nom = n;
        this.type = t;
        this.effet = e;
        this.nb_tours = nb;
        this.caracteristique = c;
        this.pos = p;
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
    
    @Override
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Point2D getPos() {
        return pos;
    }
    
    @Override
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public int getTourExpiration() {
        return tourExpiration;
    }

    public void setTourExpiration(int tourExpiration) {
        this.tourExpiration = tourExpiration;
    }
    
}
