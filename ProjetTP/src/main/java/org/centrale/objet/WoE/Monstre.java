

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
/**
 * Monstre du jeu
 * @author yaelv
 */
public abstract class Monstre extends Creature {
    

    /**
     * Constructeur principal
     * @param n
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param dMax
     * @param p
     */
    public Monstre(String n, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int dMax, Point2D p) {
        super(n,ptVie, degAtt, ptPar, pageAtt, pagePar, dMax, p);
    }

    /**
     * Constructeur par défaut
     */
    public Monstre() {
        this("Monstre",100,10,2,60,40,1,new Point2D());
    }
    
    /**
     * Constructeur en initialisant le nom
     * @param n
     */
    public Monstre(String n) {
        this(n,100,10,2,60,40,1,new Point2D());
    }
    
    /**
     * Constructeur de copie
     * @param m
     */
    public Monstre(Monstre m) {
        this(m.getNom(),m.getPtVie(),m.getDegAtt(),m.getPtPar(), m.getPageAtt(), m.getPagePar(),m.getDistAttMax(),new Point2D(m.getPos()));
    }
    
    /**
     * Affiche dans le terminal les caractéristiques du monstre
     */
    @Override
    public void affiche(){
        System.out.println("===== Monstre =====");
        System.out.println("Points de vie : " + this.getPtVie());
        System.out.println("Dégâts d'attaque : " + this.getDegAtt());
        System.out.println("Points de parade : " + this.getPtPar());
        System.out.println("Pourcentage attaque : " + this.getPageAtt() + "%");
        System.out.println("Pourcentage parade : " + this.getPagePar() + "%");
        System.out.println("Distance max attaque : " + this.getDistAttMax());
        this.getPos().affiche();
        System.out.println("======================");
    }
}
