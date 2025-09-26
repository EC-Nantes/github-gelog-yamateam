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
 * @author Mathys_Yael
 */

public class Personnage extends Creature{


    
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
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);           
    }
    
     /**
     * Constructeur de copie
     *
     * @param perso Personnage à copier
     */
    public Personnage(Personnage perso){
        this(perso.getNom(), perso.getPtVie(),perso.getDegAtt(),perso.getPtPar(), perso.getPageAtt(), perso.getPagePar(),perso.getDistAttMax(),new Point2D(perso.getPos()));
    }
    
     /**
     * Constructeur par défaut
     */
    public Personnage(){
        this("Jean",100,50,50,75,25,3,new Point2D(0,0));  
    }
    
    public Personnage(String n){
        this(n,100,50,50,75,25,3,new Point2D(0,0));
    }
    
    // --- Méthodes : ---
    
     /**
     * Déplace le personnage d'une case aléatoire autour de sa position actuelle
     */        
    @Override
    public void deplace() {
        int dx, dy;
        Random randInt = new Random();
        do { //évite le non déplacement et permet de se déplacer aléatoirement parmis les 8 cases autours du perso
            dx = randInt.nextInt(3);
            dy = randInt.nextInt(3);
        } while (dx == 1 && dy == 1);

    this.getPos().translate(dx - 1, dy - 1);
    }
    

     /**
     * Affiche dans la console les informations détaillées du personnage
     */
    @Override
    public void affiche() {
        System.out.println("===== Personnage =====");
        System.out.println("Nom : " + this.getNom());
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