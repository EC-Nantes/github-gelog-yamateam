/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author Mathys_Yael
 */
public class Objet {
    
    private String nom;
    private Point2D pos;
    private Creature detenteur;
    
    public Objet(String n, Point2D p, Creature det){
        this.nom = n;
        this.pos = p;
        this.detenteur = det;
    }
    
    public Objet(Objet obj){
        this(obj.getNom(), obj.getPos(), obj.getDetenteur());
    }
    
    public Objet(){
        this("Objet", new Point2D(0,0),null);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public Creature getDetenteur() {
        return detenteur;
    }

    public void setDetenteur(Creature detenteur) {
        this.detenteur = detenteur;
    }
    
    
    public void utiliser(){
    }
    
    public void affiche(){
        if (this.getDetenteur() == null){
            System.out.println("===== "+ this.getNom() +" =====");
            System.out.println("Position : " + this.getPos().toString());
        }
    }
    
}
