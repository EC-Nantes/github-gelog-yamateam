/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public class Point2D {

    /**
     *Création des coordonnées accesibles en privé x,y 
     */
    private int x;
    private int y;

    /**
     *
     * @param x
     * @param y
     */
    public Point2D(int x, int y) {
        /**
         * Constructeur d'un point en 2D
         * entier x et y
         * Point au coordonées x,y
         */
        this.x = x;
        this.y = y;
    }
    
    /**
     *
     * @param p
     */
    public Point2D(Point2D p)
    {
        x = p.getX();   
        y = p.getY();
    }
    
    /**
     *
     */
    public Point2D() {
        /**
         * Construction d'un point à l'origine (un choix)
         * Sans paramètres
         * Point au coordonnées [0;0]
         */
        this.x = 0 ;
        this.y = 0 ;
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }
    
    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }
    
    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     *
     */
    public void affiche(){
        // Possibilité d'afficher le nom du point ?
        // Par exemple : le point p2 est aux coordonnées...
        System.out.println("[" + this.x + ";" + this.y + "]");
     }
    
    /**
     *
     * @param dx
     * @param dy
     */
    public void translate(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    /**
     * Donne la distance entre deux points du plan
     * 
     * @param p1    Point n°1
     * @param p2    Point n°2
     * 
     * @return distance entre p1 et p2 (float)
     */
    public double distance(Point2D p1, Point2D p2){
        double d = Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
        return d;
    }
    
}
