/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Représente tous les éléments pouvant varier au cours du jeu
 * @author yaelv
 */
public abstract class ElementDeJeu {
    /**position de l'élément*/
    private Point2D pos;
    /**nom de l'élément*/
    private String nom;

    /**
     * Constructeur principal
     * @param n String son nom
     * @param p Point2D Sa position
     */
    public ElementDeJeu(String n, Point2D p){
        nom=n;
        pos=p;             
    }
                            
    /**
     *
     * @return
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
     /** 
     * Crée une copie indépendante de l'élément
     * @return un élément indépendant
     */
    public abstract ElementDeJeu copie();    
}
