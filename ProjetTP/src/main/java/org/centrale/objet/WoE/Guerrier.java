/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author yaelv
 */
public class Guerrier extends Personnage{
    
    
    /**
     *
     * @param n
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param dMax
     * @param p
     */
    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
    }
    
    /**
     *
     * @param guerr
     */
    public Guerrier(Guerrier guerr){
        this(guerr.getNom(), guerr.getPtVie(),guerr.getDegAtt(),guerr.getPtPar(), guerr.getPageAtt(), guerr.getPagePar(),guerr.getDistAttMax(),new Point2D(guerr.getPos()));
    }
    
    /**
     *
     */
    public Guerrier(){
        this("Général DJOMBY",200,20,50,75,25,1,new Point2D(0,0));  
    }
    
    public Guerrier(String n){
        this(n,200,20,50,75,25,1,new Point2D(0,0));  
    }

    /**
     * @param c
     * Fait ce combattre cet objet avec la créature rentrée en paramètre
     */
    public void combattre(Creature c){
        Random randInt = new Random();
        
        if (this.getPos().distance(c.getPos()) <= 1.5){
            System.out.println("===== "+ this.getNom() + " combat au CaC " + c.getNom() + " =====");
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= this.getPageAtt()){
                System.out.println(this.getNom() +" a réussi son attaque !");
                int RandDef = randInt.nextInt(100) + 1;
                
                if (RandDef > c.getPagePar()){
                    System.out.println(c.getNom() +" subit " + this.getDegAtt() + " dmg");
                    c.subirDegat(this.getDegAtt());
                    System.out.println(c.getNom() + ": Pv = " + c.getPtVie());
                }
                else{
                    System.out.println(c.getNom() +" a paré l'attaque !");
                    System.out.println(c.getNom() +" subit " + (this.getDegAtt()-c.getPtPar()) + " dmg");
                    c.subirDegat(this.getDegAtt()-c.getPtPar());
                    System.out.println(c.getNom() + ": Pv = " + c.getPtVie());
                }
            }
            else {
                System.out.println(this.getNom() +" a raté son attaque !");
            }
        }
        else if (this.getPos().distance(c.getPos())>1.5 && this.getPos().distance(c.getPos()) <= this.getDistAttMax()){
            System.out.println("===== "+ this.getNom() + " combat à distance " + c.getNom() + " =====");
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= this.getPageAtt()){
                System.out.println(c.getNom() +" subit " + this.getDegAtt() + " dmg");
                c.subirDegat(this.getDegAtt());
                System.out.println(c.getNom() + ": Pv = " + c.getPtVie());
            }
            else{
                System.out.println(this.getNom() +" a raté son attaque !");
            }
        }
    }
}



