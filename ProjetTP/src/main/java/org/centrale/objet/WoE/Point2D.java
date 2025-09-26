    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.objet.WoE;

public class Point2D {

    /**
     *Création des coordonnées accesibles en privé x,y 
     */
    private int x;
    private int y;

    public Point2D(int x, int y) {
        /**
         * Constructeur d'un point en 2D
         * entier x et y
         * Point au coordonées x,y
         */
        this.x = x;
        this.y = y;
    }
    
    public Point2D(Point2D p)
    {
        x = p.getX();
        y = p.getY();
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
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void affiche(){
        // Possibilité d'afficher le nom du point ?
        // Par exemple : le point p2 est aux coordonnées...
        System.out.println("[" + this.x + ";" + this.y + "]");
     }
    
    public void translate(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    public double distance(Point2D p1, Point2D p2){
        double d = Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
        return d;
    }
    
    
}
