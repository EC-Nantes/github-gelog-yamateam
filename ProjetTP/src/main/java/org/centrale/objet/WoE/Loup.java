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
public class Loup extends Monstre implements Combattant{

    /**
     * @param n
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param dMax
     * @param p
     */
    public Loup(String n,int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int dMax, Point2D p) {
        super(n, ptVie, degAtt, ptPar, pageAtt, pagePar, dMax, p);
    }

    /**
     *
     */
    public Loup() {
        super("Loup");
    }
    
    public Loup(String n) {
        super(n);
    }

    /**
     *
     * @param l
     */
    public Loup(Loup l) {
        super(l);
    }
    
    /** Crée une copie indépendante de la créature 
     * @return un loup
     */
    @Override
    public Loup copie(){
        return new Loup(this);
    }
    
    /**
     * @param c
     * Fait ce combattre cet objet avec la créature rentrée en paramètre
     */
    @Override
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
                    int deg = this.getDegAtt()-c.getPtPar();
                    if (deg < 0) {
                        deg = 0;
                    }
                    System.out.println(c.getNom() +" a paré l'attaque !");
                    System.out.println(c.getNom() +" subit " + deg + " dmg");
                    c.subirDegat(deg);
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