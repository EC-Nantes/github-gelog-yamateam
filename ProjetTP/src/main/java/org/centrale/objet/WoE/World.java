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
        
    }
        
}

