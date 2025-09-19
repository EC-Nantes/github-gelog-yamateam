/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.projet.objet;

/**
 *
 * @author Max
 */
public class Point2D {

    /**
     *Création des coordonnées accesibles en privé x,y 
     */
    private int x;
    private int y;
    private Object math;

    public Point2D(int x, int y) {
        /**
         * Constructeur d'un point en 2D
         * entier x et y
         * Point au coordonées x,y
         */
        this.x = x;
        this.y = y;
    }
    
    public Point2D() {
        /**
         * Construction d'un point à l'origine (un choix)
         * Sans paramètres
         * Point au coordonnées [0;0]
         */
        this.x = 0 ;
        this.y = 0 ;
    }
    
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void affiche(){
        // Possibilité d'afficher le nom du point ?
        // Par exemple : le point p2 est aux coordonnées...
    System.out.println("Le point est aux coordonées : [" + this.x + ";" + this.y + "]");
     }
    
    public void translation(int t){
        this.x +=t;
        this.y +=t;
    }
    
    public void recopie(Point2D p){
        this.x = p.getX();
        this.y = p.getY();
    }
    // La recopie fonctionne également avec le programme ci-dessous
    // Possibilité de le mettre ? A la place de recopie ? (pour avoir sous le même nom, toutes les constructions de points)
    //public void Point2D(Point2D p){
        //this.x = p.getX();
        //this.y = p.getY();
    //}
}
