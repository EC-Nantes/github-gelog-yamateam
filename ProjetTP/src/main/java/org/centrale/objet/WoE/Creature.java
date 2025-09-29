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
public class Creature {
    
    /** Nom du Creature */
    private String nom;
    /** Points de vie du Creature */
    private int ptVie;
    /** Dégâts d'attaque */
    private int degAtt;
    /** Points de parade */
    private int ptPar;
    /** Pourcentage de réussite d’une attaque */
    private int pageAtt;
    /** Pourcentage de réussite d’une parade */
    private int pagePar;
    /** Distance maximale à laquelle le Creature peut attaquer */
    private int distAttMax;
    /** Position du Creature */
    private Point2D pos;
    
    
    // --- Constructeurs : ---
    /**
     * Constructeur principal
     *
     * @param n        Nom du Creature
     * @param pV       Points de vie
     * @param dA       Dégâts d'attaque
     * @param pPar     Points de parade
     * @param paAtt    Pourcentage d'attaque
     * @param paPar    Pourcentage de parade
     * @param dMax     Distance maximale d’attaque
     * @param p        Position initiale
     */
    public Creature(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        nom = n;
        ptVie = pV;
        degAtt = dA;
        ptPar = pPar;
        pageAtt = paAtt;
        pagePar = paPar;
        distAttMax = dMax;
        pos = p;
    }
    
     /**
     * Constructeur de copie
     *
     * @param crea Creature à copier
     */
    public Creature(Creature crea){
        this(crea.getNom(), crea.getPtVie(),crea.getDegAtt(),crea.getPtPar(), crea.getPageAtt(), crea.getPagePar(),crea.getDistAttMax(),new Point2D(crea.getPos()));
    }
    
     /**
     * Constructeur par défaut
     */
    public Creature(){
        this("Jean-Baptiste",100,50,50,75,25,3,new Point2D(0,0));  
    }
    
    // --- Méthodes : ---
    
     /**
     * Déplace le Creature d'une case aléatoire autour de sa position actuelle
     */
        
    public void deplace() {
        int dx, dy;
        Random randInt = new Random();
        do { //évite le non déplacement et permet de se déplacer aléatoirement parmis les 8 cases autours de la créature
            dx = randInt.nextInt(3);
            dy = randInt.nextInt(3);
        } while (dx == 1 && dy == 1);

    pos.translate(dx - 1, dy - 1);
}
    

    /**
     *
     * @return None
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     *
     * @return None
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     *
     * @return
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     *
     * @return
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     *
     * @return
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     *
     * @return
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     *
     * @return
     */
    public Point2D getPos() {
        return pos;
    }


    /**
     *
     * @param ptVie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     *
     * @param degAtt
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     *
     * @param ptPar
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     *
     * @param pageAtt
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     *
     * @param pagePar
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     *
     * @param distAttMax
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
     /**
     * Affiche dans la console les informations détaillées du Creature
     */
    public void affiche() {
        System.out.println("===== "+ nom +" =====");
        System.out.println("Points de vie : " + ptVie);
        System.out.println("Dégâts d'attaque : " + degAtt);
        System.out.println("Points de parade : " + ptPar);
        System.out.println("Pourcentage attaque : " + pageAtt + "%");
        System.out.println("Pourcentage parade : " + pagePar + "%");
        System.out.println("Distance max attaque : " + distAttMax);
        pos.affiche();
    }
    
    public void subirDegat(int deg){
        this.ptVie -= deg;
        if (ptVie <= 0){
            this.ptVie = 0;
            System.out.println(nom + " est Mort");
        }
    }
    
}

