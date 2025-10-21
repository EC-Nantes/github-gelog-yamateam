
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 * Type d'objet du jeu, se déplaçant seul, couvrant une zone rectangulaire et faisant des dégâts aux créatures se trouvant dans cette zone
 * @author yaelv
 */
public class NuageToxique extends Objet implements Deplacable, Combattant {

    /**nombre de cases auquel le nuage se déplace à chaque tour*/
    private int vitesse;
    /**taille du nuage en x*/
    private int longueur;
    /**taille du nuage en y*/
    private int largeur;
    
    /**
     * Constructeur principal
     * @param e int
     * @param v int
     * @param l1 int
     * @param l2 int
     * @param p Point2D Sa position
     */
    public NuageToxique(int e,int v,int l1,int l2, Point2D p){
        super("Nuage Toxique",e, p);
        this.vitesse = v;
        this.longueur=l1;
        this.largeur=l2;
       
    }
    
    /**
     * Constructeur par défaut
     */
    public NuageToxique(){
        this(1,2,5,4, new Point2D(0,0));
    }
    
    /**
     * Constructeur de copie
     * @param n NuageToxique
     */
    public NuageToxique(NuageToxique n){
        this(n.getEffet(),n.getVitesse(),n.getLongueur(),n.getLargeur(), n.getPos());
    }
    
    /** 
     * Crée une copie indépendante de la créature 
     * @return un nuage toxique
     */
    @Override
    public NuageToxique copie(){
        return new NuageToxique(this);
    }
    
    /**
     * deplace le nuage d'un nombre de cases égal à sa vitesse dans une des 8 directions (ne peut pas rester immobile)
     */
    @Override
    public void deplacer() {
        System.out.println("===== "+ this.getNom() +" se déplace =====");
        int dx, dy;
        Random randInt = new Random();
        do { //évite le non déplacement et permet de se déplacer aléatoirement parmis les 8 cases autours de la créature
            dx = randInt.nextInt(3);
            dy = randInt.nextInt(3);
        } while (dx == 1 && dy == 1);

        getPos().translate(vitesse*(dx - 1),vitesse*(dy - 1));
        System.out.println("Nouvelle position : " + this.getPos().toString());
    }
    
    /**
     * inflige des dégâts à la créature passée en argument qui a été testée comme présente dans la zone du nuage
     * @param c La créature à combattre
     */
    @Override
    public void combattre(Creature c) {
        System.out.println("Vous êtes dans un nuage toxique, vous avez subi : "+this.getEffet()+" dégats");
        c.subirDegat(this.getEffet());
    }
    
    /**
     * implémente une méthode imposée par l'interface utilisable 
     * @param perso
     * @param world
     */
    @Override
    public void utiliser(Personnage perso, World world){}

    /**
     *
     * @param perso
     */
    @Override
    public void retirer(Personnage perso){}

    /**
     *
     * @return
     */
    @Override
    public String getNom(){return "Nuage Toxique";}

    /**
     *
     * @return
     */
    @Override
    public String getCaracteristique(){return "dA";}

    /**
     *
     * @return
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     *
     * @return
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     *
     * @return
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Implémente une méthode imposée par l'interface qui ne sera pas utilisée car le nuage n'expire jamais
     * @param world
     * @return false
     */
    @Override
    public boolean estExpirer(World world) {return false;}
    
    
}
