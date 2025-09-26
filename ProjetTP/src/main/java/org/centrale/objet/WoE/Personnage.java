/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.objet.WoE;
import java.util.Random;

/**
 * Représente un personnage du jeu World of ECN
 * <p>
 * Un personnage est défini par son nom, ses points de vie, ses statistiques d’attaque et de défense, ainsi que sa position dans le plan
 * </p>
 *
 * @author Mathys&Yael
 */

public class Personnage {

    //--- Attributs : ---
    /** Nom du personnage */
    private String nom;
    /** Points de vie du personnage */
    private int ptVie;
    /** Dégâts d'attaque */
    private int degAtt;
    /** Points de parade */
    private int ptPar;
    /** Pourcentage de réussite d’une attaque */
    private int pageAtt;
    /** Pourcentage de réussite d’une parade */
    private int pagePar;
    /** Distance maximale à laquelle le personnage peut attaquer */
    private int distAttMax;
    /** Position du personnage */
    private Point2D pos;
    
    
    // --- Constructeurs : ---
    /**
     * Constructeur principal
     *
     * @param n        Nom du personnage
     * @param pV       Points de vie
     * @param dA       Dégâts d'attaque
     * @param pPar     Points de parade
     * @param paAtt    Pourcentage d'attaque
     * @param paPar    Pourcentage de parade
     * @param dMax     Distance maximale d’attaque
     * @param p        Position initiale
     */
    
    public Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
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
     * @param perso Personnage à copier
     */
    public Personnage(Personnage perso){
        this(perso.getNom(), perso.getPtVie(),perso.getDegAtt(),perso.getPtPar(), perso.getPageAtt(), perso.getPagePar(),perso.getDistAttMax(),perso.getPos());
    }
    
     /**
     * Constructeur par défaut
     */
    public Personnage(){
        this("Jean",100,50,50,75,25,3,new Point2D(0,0));  
    }
    
    // --- Méthodes : ---
    
     /**
     * Déplace le personnage d'une case aléatoire autour de sa position actuelle
     */
        
    public void deplace() {
        int dx, dy;
        Random randInt = new Random();
        do { //évite le non déplacement et permet de se déplacer aléatoirement parmis les 8 cases autours du perso
            dx = randInt.nextInt(3);
            dy = randInt.nextInt(3);
        } while (dx == 1 && dy == 1);

    pos.translate(dx - 1, dy - 1);
}
    
    public String getNom() {
        return nom;
    }

    public int getPtVie() {
        return ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
     /**
     * Affiche dans la console les informations détaillées du personnage
     */

    public void affiche() {
    System.out.println("===== Personnage =====");
    System.out.println("Nom : " + nom);
    System.out.println("Points de vie : " + ptVie);
    System.out.println("Dégâts d'attaque : " + degAtt);
    System.out.println("Points de parade : " + ptPar);
    System.out.println("Pourcentage attaque : " + pageAtt + "%");
    System.out.println("Pourcentage parade : " + pagePar + "%");
    System.out.println("Distance max attaque : " + distAttMax);
    pos.affiche();
    System.out.println("======================");
    }
    
}
