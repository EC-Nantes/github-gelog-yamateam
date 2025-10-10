package org.centrale.objet.WoE;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yaelv, mathys
 */


public class World {

    /**
     * Taille du monde
     */
    private int taille;        
    /**
     * Grille du jeu
     */
    private Personnage monde[][];
    /**
     * Liste des personnages du jeu
     */
    private LinkedList<Personnage> liste_perso;
    
    /**
     * 
     * Crée un monde de taille n*n
     * @param n
     */    
    public World(int n) {
        this.taille=n;
        this.monde = new Personnage[taille][taille];
        liste_perso  = new LinkedList<>();
    }
    
    /**
     * 
     * Crée un monde de taille 100*100
     */    
    public World() {
        this.taille=100;
        this.monde = new Personnage[taille][taille];
        liste_perso  = new LinkedList<>();
    }
    
    

    public LinkedList<Personnage> getListe_perso() {
        return liste_perso;
    }
     
    
    
/**
 * 
 * Place nb guerriers à des positions aléatoires distinctes
 * 
 * @param nb
 */        
    public void creerMondeAlea(int nb){
        
        Random aleaInt = new Random();
        
        Point2D p = new Point2D();
        p.setX(aleaInt.nextInt(taille));
        p.setY(aleaInt.nextInt(taille));
        
        for(int i=0;i<nb;i++){
            
            while(monde[p.getX()][p.getY()] != null){
                p.setX(aleaInt.nextInt(taille));
                p.setY(aleaInt.nextInt(taille));
            }
            liste_perso.add(new Guerrier());
            liste_perso.get(i).setPos(p);
            monde[p.getX()][p.getY()] = liste_perso.get(i);
            
        }

    }
    
    
    public void tourDeJeu(){
        
    }
    
    public void afficheWorld(){
        
    }
}

