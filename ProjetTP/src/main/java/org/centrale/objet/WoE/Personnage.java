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
    
    public void recupererObjet(Objet o){
        if (this.getPos().distance(o.getPos()) == 0 && o.getDetenteur() == null){
            o.setDetenteur(this);
        }
    }
    
    public void utiliserObjet(Objet o){
        if (this == o.getDetenteur()){
            o.utiliser();
            o.setDetenteur(new Creature());
        }
    }
   
    
}
