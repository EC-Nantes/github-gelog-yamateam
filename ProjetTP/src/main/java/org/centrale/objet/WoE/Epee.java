/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author Mathys_Yael
 */
public class Epee extends Objet {
    
    private int degObj;
    private int durab;
    
    public Epee(String n, Point2D p, Creature det, int dObj, int dur){
        super(n, p, det);
        this.degObj = dObj;
        this.durab = dur;
    }   
    
    public Epee(Epee e){
        this(e.getNom(), e.getPos(), e.getDetenteur(), e.getDegObj(), e.getDurab());
    }
    
    public Epee(){
        this("Epée", new Point2D(0,0), null, 30, 100);
    }

    public int getDegObj() {
        return degObj;
    }

    public void setDegObj(int degObj) {
        this.degObj = degObj;
    }

    public int getDurab() {
        return durab;
    }

    public void setDurab(int durab) {
        this.durab = durab;
    }
    
}
