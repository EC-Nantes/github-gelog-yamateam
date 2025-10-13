package org.centrale.objet.WoE;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


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
    
    private double pAge_perso_max;
    private double pAge_monstre_max;
    private double pAge_objet_max;
    
    /**
     * 
     * Crée un monde de taille n*n
     * @param n
     */    
    public World(int n) {
        this.taille=n;
        this.monde = new Personnage[taille][taille];
        this.liste_perso  = new LinkedList<>();
        this.pAge_perso_max = 0.05;
        this.pAge_monstre_max = 0.1;
        this.pAge_objet_max = 0.1;
    }
    
    /**
     * 
     * Crée un monde de taille 100*100
     */    
    public World() {
        this(100);
    }
    
    

    public LinkedList<Personnage> getListe_perso() {
        return liste_perso;
    }
     
    
    private void ajouterElement(double pAge, ArrayList<ElementDeJeu> liste_type){
        Random aleaInt = new Random();
        
        int n = aleaInt.nextInt((int)Math.floor(pAge*taille*taille) - (int)Math.floor(pAge*taille*taille/2) + 1) + (int)Math.floor(pAge*taille*taille/2);
        
        Point2D p = new Point2D();
        p.setX(aleaInt.nextInt(taille));
        p.setY(aleaInt.nextInt(taille));
        
        for(int i=0;i<n;i++){
            
            while(monde[p.getX()][p.getY()] != null){
                p.setX(aleaInt.nextInt(taille));
                p.setY(aleaInt.nextInt(taille));
            }
            Creature type = liste_type.get(aleaInt.nextInt(liste_type.size()));
            liste_perso.add(type.copie());
            liste_perso.get(liste_type.size() - 1).setPos(p);
            monde[p.getX()][p.getY()] = liste_perso.get(liste_type.size() - 1);
            
        }
    }
    
/**
 * 
 * 
 * 
 * 
 */        
    public void creerMondeAlea(){
        this.ajouterElement(pAge_perso_max, new ArrayList<ElementDeJeu>(List.of(new Guerrier(), new Archer(), new Paysan())));
        this.ajouterElement(pAge_monstre_max, new ArrayList<ElementDeJeu>(List.of(new Loup(), new Lapin())));
        this.ajouterElement(pAge_objet_max, new ArrayList<ElementDeJeu>(List.of(new PotionSoin(), new Epee())));

    }
    
    
    public void tourDeJeu(){
        
    }
    
    public void afficheWorld(){
        
    }
}
