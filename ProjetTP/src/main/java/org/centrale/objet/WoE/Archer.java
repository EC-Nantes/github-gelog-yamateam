/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author dytri
 */
public class Archer extends Personnage implements Combattant {
    
    private int nbFleches;
    
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
     * @param nbFl
     */
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFl){
        super(n,pV,dA,pPar,paAtt,paPar,dMax,p);
        nbFleches = nbFl;
    }
    
    /**
     *
     * @param a
     */
    public Archer(Archer a){
        this(a.getNom(), a.getPtVie(),a.getDegAtt(),a.getPtPar(), a.getPageAtt(), a.getPagePar(),a.getDistAttMax(),new Point2D(a.getPos()), a.getNbFleches());
    }
    
    /**
     *
     */
    public Archer(){
        this("Mohammed",100,50,50,75,25,10,new Point2D(0,0), 12);  
    }
    
    public Archer(String n){
        this(n,100,50,50,75,25,10,new Point2D(0,0), 12);  
    }

    /**
     *
     * @param nbFleches
     */
    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }
    
    @Override
    public void affiche(){
        super.affiche();
        System.out.println("Nombre de flèche : " + this.getNbFleches());
    }

    /** Crée une copie indépendante de la créature 
     * @return un archer
     */
    @Override
    public Archer copie(){
        return new Archer(this);
    }
    
    /**
     *
     * @return le nombre de flèches
     */
    public int getNbFleches() {
        return nbFleches;
    }
    
    /**
     * @param c Créature à combattre
     * 
     * Fait se combattre cet objet avec la créature rentrée en paramètre.
     * Deux styles de combat selon la distance entre les créatures concernées : 
     * - Au Corps à Corps (CaC) si elles sont adjacentes
     * - A distance si elles ne le sont pas et que la distance d'attaque de l'attaquant est suffisante
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
        else if (this.getPos().distance(c.getPos())>1.5 && this.getPos().distance(c.getPos()) <= this.getDistAttMax() && this.getNbFleches() > 0){
            System.out.println("===== "+ this.getNom() + " combat à distance " + c.getNom() + " =====");
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= this.getPageAtt()){
                System.out.println(this.getNom() +" a réussi son attaque !");
                System.out.println(c.getNom() +" subit " + this.getDegAtt() + " dmg");
                c.subirDegat(this.getDegAtt());
                this.setNbFleches(this.getNbFleches() - 1);
                System.out.println(c.getNom() + ": Pv = " + c.getPtVie());
                System.out.println(this.getNom() + ": NbFleche = " + this.getNbFleches());
            }
            else{
                System.out.println(this.getNom() +" a raté son attaque !");
            }
        }
    }

}
