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
public class NuageToxique extends Objet implements Deplacable, Combattant {

    private int vitesse;
    
    private int longueur;
    
    private int largeur;
    
   
    public NuageToxique(int e,int v,int l1,int l2, Point2D p){
        super("Nuage Toxique",e, p);
        this.vitesse = v;
        this.longueur=l1;
        this.largeur=l2;
       
    }
    
    public NuageToxique(){
        this(1,2,5,4, new Point2D(0,0));
    }
    
    public NuageToxique(NuageToxique n){
        this(n.getEffet(),n.getVitesse(),n.getLongueur(),n.getLargeur(), n.getPos());
    }
    
    /** Crée une copie indépendante de la créature 
     * @return un nuage toxique
     */
    @Override
    public NuageToxique copie(){
        return new NuageToxique(this);
    }
    
    /**
     *
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
    
    

    @Override
    public void combattre(Creature c) {
        System.out.println("Vous êtes dans un nuage toxique, vous avez subi : "+this.getEffet()+" dégats");
        c.subirDegat(this.getEffet());
    }
    
    @Override
    public void utiliser(Personnage perso, World world){}
    @Override
    public void retirer(Personnage perso){}
    @Override
    public boolean estExpirer(World world){return false;}
    @Override
    public String getNom(){return "Nuage Toxique";}
    @Override
    public String getCaracteristique(){return "dA";}

    public int getVitesse() {
        return vitesse;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }
    
    
}