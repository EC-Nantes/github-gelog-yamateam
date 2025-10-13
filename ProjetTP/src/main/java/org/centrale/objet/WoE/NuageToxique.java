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
    
    private int degAtt;
    
    private int vitesse;
    
    private int longueur;
    
    private int largeur;
    
   
    public NuageToxique(int a,int v,int l1,int l2){
        degAtt=a;
        vitesse=v;
        longueur=l1;
        largeur=l2;
        this.setNom("Nuage Toxique");
    }
    
    public NuageToxique(){
        this(1,2,5,4);
    }
    
    
    
    /** Crée une copie indépendante de la créature 
     * @return un archer
     */
    @Override
    public NuageToxique copie(){
        return new NuageToxique();
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
        c.subirDegat(this.degAtt);
    }
    
}

