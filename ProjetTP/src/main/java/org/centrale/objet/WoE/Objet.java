/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author Mathys_Yael
 */
public abstract class Objet extends ElementDeJeu implements Utilisable{
    
    private String nom;
    private Point2D pos;
    private int effet;
    
    public Objet(String n,int eff, Point2D p){
        this.nom = n;
        this.pos = p;
        this.effet = eff;
    }
    
    public Objet(Objet obj){
        this(obj.getNom(),obj.getEffet(), obj.getPos());
    }
    
    public Objet(){
        this("Objet",5, new Point2D(0,0));
    }
    
    @Override
    public abstract Objet copie();
    
    @Override
    public abstract void utiliser(Personnage perso, World world);
    
    @Override
    public abstract void retirer(Personnage perso);

    @Override
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Point2D getPos() {
        return pos;
    }
    
    @Override
    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    public void utiliser(){
    }
    
    @Override
    public int getEffet() {
        return effet;
    }

    public void setEffet(int effet) {
        this.effet = effet;
    }
   
    
    
}