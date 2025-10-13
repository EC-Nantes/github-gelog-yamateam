/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv
 */
public abstract class ElementDeJeu {
    private Point2D pos;
    private String nom;

    public ElementDeJeu(String n, Point2D p){
        nom=n;
        pos=p;             
    }
                            
            
    public Point2D getPos() {
        return pos;
    }

    public String getNom() {
        return nom;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
     /** Crée une copie indépendante de l'élément
     * @return un élément indépendant
     */
    public abstract ElementDeJeu copie();    
}
