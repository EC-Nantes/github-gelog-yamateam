/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author yaelv
 */
public class Joueur {
    
    private Personnage perso;
    
    
    public boolean choixPerso(String nom,String type){
        switch(type){
            case "Guerrier" -> {
                this.perso = new Guerrier(nom);
                return true;
            }
            case "Archer" -> {
                this.perso = new Archer(nom);
                return true;
            }
            default -> {
                return false;
            }
                 
                       
        }
    }
    
    public void combattre(LinkedList<Creature> liste_perso){
        Random randInt = new Random();
        
        Creature opps = this.choisir_adversaire(liste_perso);
        
        if (opps == null){
            System.out.println("Il n'y pas d'adversaire disponible");
            return ;
        }
        
        if (perso.getPos().distance(opps.getPos()) <= 1.5){
            System.out.println("===== "+ perso.getNom() + " combat au CaC " + opps.getNom() + " =====");
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= perso.getPageAtt()){
                System.out.println(perso.getNom() +" a réussi son attaque !");
                int RandDef = randInt.nextInt(100) + 1;
                
                if (RandDef > opps.getPagePar()){
                    System.out.println(opps.getNom() +" subit " + perso.getDegAtt() + " dmg");
                    opps.subirDegat(perso.getDegAtt());
                    System.out.println(opps.getNom() + ": Pv = " + opps.getPtVie());
                }
                else{
                    int deg = perso.getDegAtt()-opps.getPtPar();
                    if (deg < 0) {
                        deg = 0;
                    }
                    System.out.println(opps.getNom() +" a paré l'attaque !");
                    System.out.println(opps.getNom() +" subit " + deg + " dmg");
                    opps.subirDegat(deg);
                    System.out.println(opps.getNom() + ": Pv = " + opps.getPtVie());
                }
            }
            else {
                System.out.println(perso.getNom() +" a raté son attaque !");
            }
        }
        else if (perso.getPos().distance(opps.getPos())>1.5 && perso.getPos().distance(opps.getPos()) <= perso.getDistAttMax()){
            System.out.println("===== "+ perso.getNom() + " combat à distance " + opps.getNom() + " =====");
            int RandAtt = randInt.nextInt(100) + 1;
            
            if (RandAtt <= perso.getPageAtt()){
                System.out.println(opps.getNom() +" subit " + perso.getDegAtt() + " dmg");
                opps.subirDegat(perso.getDegAtt());
                System.out.println(opps.getNom() + ": Pv = " + opps.getPtVie());
            }
            else{
                System.out.println(perso.getNom() +" a raté son attaque !");
            }
        }
    }
    
    public Creature choisir_adversaire(LinkedList<Creature> liste_perso){
        System.out.println("------ Combattre : Choix adversaire ------");
        System.out.println("Choisir le numéro de l'adversaire :");
        ArrayList<Creature> liste_adversaire = new ArrayList<>();
        
        for(Creature c : liste_perso){
            if(perso.getPos().distance(c.getPos()) <= perso.getDistAttMax()){
                System.out.println(c.getNom() + " (Pt de vie : " + c.getPtVie() + ") "  + c.getPos().toString());
                liste_adversaire.add(c);
            }
        }
     
        Scanner sc = new Scanner(System.in);
        boolean a_choisi = false;
        Creature opps = null;
        
        while(!a_choisi){
            try{
                int i = sc.nextInt();
                opps = liste_adversaire.get(i);         
                a_choisi = true;
            }catch(Exception e){
                System.out.println("Erreur le numéro n'est pas un entier ou ne fait pas partie de la liste");
            }
        }
        
        
        return opps;
    }
    
    
    
}
