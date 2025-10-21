/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv, mathys
 */
public abstract class Nourriture extends ElementDeJeu implements Utilisable {
     /**
     * 
     */
    private String type; //bonus ou malus
    /**
     * 
     */
    private int effet; // par exemple 2 si +2
    /**
     * 
     */
    private int nb_tours;
    /**
     * 
     */
    private String caracteristique;// par exemple degat
    /**
     * 
     */
    private int tourExpiration;
    
    
    /**Constructeur par défaut.
     *
     */
    public Nourriture(){
        super("feuille d'épinard",new Point2D(0,0));
        this.type = "bonus";
        this.effet = 2;
        this.nb_tours = 3;
        this.caracteristique = "pVie";
    }
    
    /**Constructeur avec tous les paramètres.
     *
     * @param n     nom
     * @param t     type
     * @param e     effet
     * @param nb    nb de tour effectif
     * @param c     caractéristique affectée
     * @param p     position
     */
    public Nourriture(String n, String t, int e, int nb, String c, Point2D p){
        super(n,p);
        this.type = t;
        this.effet = e;
        this.nb_tours = nb;
        this.caracteristique = c;
    }
    
    /** Constructeur de copie
     *
     * @param n nourriture
     */
    public Nourriture(Nourriture n){
        this(n.getNom(),n.getType(),n.getEffet(),n.getNb_tours(),n.getCaracteristique(),n.getPos());
    }
    
    /**fonction d'utilisable implémentée dans malus et bonus
     * 
     * @return 
     */
    @Override
    public abstract Nourriture copie();
    
    /**fonction d'utilisable implémentée dans malus et bonus
     * 
     * 
     */
    @Override
    public abstract void utiliser(Personnage perso, World world);
    
    /**fonction d'utilisable implémentée dans malus et bonus
     * 
     *  
     */
    @Override
    public abstract void retirer(Personnage perso);
    
    /**fonction d'utilisable implémentée dans malus et bonus
     * 
     * @return 
     */
    @Override
    public boolean estExpirer(World world){
        return world.getCompteurTour()>= this.tourExpiration;
    }    
    
    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     *
     * @return
     */
    public int getNb_tours() {
        return nb_tours;
    }

    /**
     *
     * @param nb_tours
     */
    public void setNb_tours(int nb_tours) {
        this.nb_tours = nb_tours;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getCaracteristique() {
        return caracteristique;
    }

    /**
     *
     * @param caracteristique
     */
    public void setCaractéristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    /**
     *
     * @return
     */
    public int getTourExpiration() {
        return tourExpiration;
    }

    /**
     *
     * @param tourExpiration
     */
    public void setTourExpiration(int tourExpiration) {
        this.tourExpiration = tourExpiration;
    }
    
}
