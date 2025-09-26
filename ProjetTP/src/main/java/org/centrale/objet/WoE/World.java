package org.centrale.objet.WoE;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yaelv
 */
public class World {
    public Archer robin;
    public Paysan peon;
    public Lapin bugs1;
    public Lapin bugs2;

    public World() {
        robin = new Archer();
        peon = new Paysan();
        bugs1 = new Lapin();
        bugs2 = new Lapin();
    }
    
    public void creerMondeAlea(){
        
        /*
        List<Point2D> liste = new ArrayList();
        Random aleaInt = new Random();
        
        int posx = aleaInt.nextInt(101)-50;
        int posy = aleaInt.nextInt(101)-50;
        Point2D point = new Point2D(posx,posy);
        liste.add(point);
        
        for(int i=1;i<5;i++){
            while (liste.contains(point)){
                posx = aleaInt.nextInt(101)-50;
                posy = aleaInt.nextInt(101)-50;
                point.setPosition(posx,posy);
            }
        }
        
        robin.setPos(liste.get(0));
        peon.setPos(liste.get(1));
        bugs1.setPos(liste.get(2));
        bugs2.setPos(liste.get(3));
*/      Random randInt = new Random();
        
        Point2D p1 = new Point2D();
        Point2D p2 = new Point2D();
        Point2D p3 = new Point2D();
        Point2D p4 = new Point2D();
        
        p1.setX(randInt.nextInt(101)-50);
        p1.setY(randInt.nextInt(101)-50);
        
        do { //évite que deux persos soient sur la même case
            p2.setX(randInt.nextInt(101)-50);
            p2.setY(randInt.nextInt(101)-50);
        } while (p2 != p1);
        
        do { //évite que deux persos soient sur la même case
            p3.setX(randInt.nextInt(101)-50);
            p3.setY(randInt.nextInt(101)-50);
        } while (p3 != p1 && p3 != p2);
        
        do { //évite que deux persos soient sur la même case
            p4.setX(randInt.nextInt(101)-50);
            p4.setY(randInt.nextInt(101)-50);
        } while (p4 != p1 && p4 != p2 && p4 != p3);
        
    }

        
}


