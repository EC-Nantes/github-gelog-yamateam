/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/** Implémente le joueur du monde, son perso, son inventaire, ses effets actifs et les fonctions qui lui permettent de jouer.
 * 
 *
 * @author yaelv, mathys
 */
public class Joueur {
    /**
     *
     */
    private Personnage perso;
    /**
     *
     */
    private ArrayList<Utilisable> inventaire = new ArrayList<>();
    /**
     *
     */
    private ArrayList<Utilisable> effets = new ArrayList<>();

    /**
     *
     * @return perso
     */
    public Personnage getPerso() {
        return perso;
    }

    /**
     *
     * @param perso
     */
    public void setPerso(Personnage perso) {
        this.perso = perso;
    }
    
    /**Permet au joueur de créer son perso après avoir au préalable choisi son nom et son type (Guerrier/archer).
     *
     * @param nom
     * @param type
     * @return
     */
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
                System.out.println("Erreur : la classe n'est pas valide ('Guerrier' ou 'Archer'), recommencez");
                return false;
            }
                 
                       
        }
    }
    
    /**Permet de combattre à distance ou au CaC une créature choisie.
     *
     * @param world
     */
    public void combattre(World world){
        
        Random randInt = new Random();
        
        Creature opps = this.choisir_adversaire(world);
        
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
                    
                    //mort du opps
                    if (opps.getPtVie() <= 0){
                        world.getListe_perso().remove(opps);
                        world.getMonde()[opps.getPos().getX()][opps.getPos().getY()] = null;
                    }
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
        else if (perso.getPos().distance(opps.getPos())>1.5 && perso.getPos().distance(opps.getPos()) <= perso.getDistAttMax() && ((Archer)perso).getNbFleches() > 0){
            System.out.println("===== "+ perso.getNom() + " combat à distance " + opps.getNom() + " =====");
            int RandAtt = randInt.nextInt(100) + 1;
            ((Archer)perso).setNbFleches(((Archer)perso).getNbFleches()-1);
            
            if (RandAtt <= perso.getPageAtt()){
                System.out.println(opps.getNom() +" subit " + perso.getDegAtt() + " dmg");
                opps.subirDegat(perso.getDegAtt());
                System.out.println(opps.getNom() + ": Pv = " + opps.getPtVie());
                
                //mort du opps
                if (opps.getPtVie() <= 0){
                        world.getListe_perso().remove(opps);
                        world.getMonde()[opps.getPos().getX()][opps.getPos().getY()] = null;
                }
            }
            else{
                System.out.println(perso.getNom() +" a raté son attaque !");
            }
        }
    }
    
    /**Permet au joueur de choisir une créature parmis celles accessibles autour de lui.
     *
     * @param world
     * @return
     */
    public Creature choisir_adversaire(World world){
        System.out.println("------ Combattre : Choix adversaire ------");
        System.out.println("Choisir le numéro de l'adversaire :");
        ArrayList<Creature> liste_adversaire = new ArrayList<>();
        
        int k = 0;
        for(Creature c : world.getListe_perso()){
            if(perso.getPos().distance(c.getPos()) <= (perso.getDistAttMax()+0.5) && c !=perso){
                System.out.println(k + ". " + c.getNom() + " (Classe : "+ c.getClass().getSimpleName() + ", Pt de vie : " + c.getPtVie() + ") "  + c.getPos().toString());
                liste_adversaire.add(c);
                k = k+1;
            }
        }
        
        if(k == 0){
            return null;
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
                System.out.println("Erreur : le numéro n'est pas un entier ou ne fait pas partie de la liste, recommencez");
                sc.nextLine();
            }
        }
        
        return opps;
    }
    
    /**Permet au joueur de se déplacer sur une case libre adjacente à sa position et d'en récupérer le potentiel objet.
     *
     * @param world
     * @return
     */
    public Point2D deplacer(World world){
        System.out.println("------ Se déplacer : Choix position ------");
        System.out.println("Rentrer une position libre ( . ) au format 'x y' ('-1' si toutes les cases sont occupées)");
        
        Scanner sc = new Scanner(System.in);
        boolean a_choisi = false;
        Point2D dep = new Point2D();
        
        while(!a_choisi){
            
            try{
                int x = sc.nextInt();
                
                if(x == -1){ // aucune case dispo
                    return dep; //dep sera null
                }
                
                int y = sc.nextInt();
                
                dep.setX(x);
                dep.setY(y);
                
                //test si la case est libre et adjacente au perso
                if((world.getMonde()[x][y] == null || world.getMonde()[x][y] instanceof Utilisable) && perso.getPos().distance(dep)<1.5){
                    System.out.println("Déplacement sur la case ["+x+","+y+"]");
                    perso.setPos(dep);
                    
                    a_choisi = true;
                }
                else if(perso.getPos().distance(dep) > 1.5){
                    System.out.println("Erreur : la case n'est pas adjacente, recommencez");
                }
                else{
                    System.out.println("Erreur : la case n'est pas libre, recommencez");
                }
                
                
            }catch(Exception e){
                System.out.println("Erreur : la position n'est pas au bon format, recommencez");
                sc.nextLine();
            }
        }
        
        if(world.getMonde()[dep.getX()][dep.getY()] instanceof Utilisable obj){
            if(obj instanceof NuageToxique n){
                n.combattre(perso);
            }
            else{
                System.out.println(obj.getNom() + " a été rammasé(e) (type : "+ obj.getClass().getSimpleName() + ", " + obj.getCaracteristique() +" : " + obj.getEffet() + ") ");
                inventaire.add((obj.copie()));
            }
        }
        
        return dep;
    }
    
    /**Permet au joueur de regarder/utiliser les items qu'il a récupéré dans son inventaire.
     *
     * @param monde
     */
    public void RegarderInventaire(World monde){
        System.out.println("------ Inventaire ------");
        
        if(inventaire.isEmpty()){
            System.out.println("L'inventaire est vide...");
            return;
        }
        
        System.out.println("Rentrer le numéro de l'objet à utiliser (-1 pour ne rien utiliser) :");
        
        int k = 0;
        for(Utilisable u : inventaire){
            System.out.println(k + ". " + u.getNom() + " (type : "+ u.getClass().getSimpleName() + ", " + u.getCaracteristique() +" : " + u.getEffet() + ") ");
            k = k+1;
        }
        
        Scanner sc = new Scanner(System.in);
        boolean a_choisi = false;
        
        while(!a_choisi){
            try{
                int i = sc.nextInt();
                
                if(i==-1){
                    break;
                }
                
                Utilisable obj = inventaire.get(i); //levera une exception si i est en dehors de la taille de la liste
                obj.utiliser(perso, monde);
                inventaire.remove(i);
                effets.add(obj);

                a_choisi = true;
                
            }catch(Exception e){
                System.out.println("Erreur : vous n'avez pas rentrer un nombre valable, recommencez");
                sc.nextLine();
            }
        }
        
        
    }
    
    /**Affiche une grille 7*7 autour du joueur pour lui permettre de se repérer dans le monde.
     *
     * @param monde
     */
    public void afficherGrille(ElementDeJeu[][] monde) {
        int nb_lignes = monde.length;
        int nb_colonnes = monde[0].length;
        int xJ = perso.getPos().getX();
        int yJ = perso.getPos().getY();

        // Affichage indices des colonnes
        System.out.print("    "); // espace pour l'indice de ligne
        for (int dy = -3; dy <= 3; dy++) {
            int y = yJ + dy;
            System.out.print(String.format("%-4s", (y >= 0 && y < nb_colonnes) ? y : ".")); 
        }
        System.out.println();
        System.out.print("    "); // ligne séparatrice
        for (int dy = -3; dy <= 3; dy++) {
            System.out.print("____");
        }
        System.out.println();

        // Parcours des lignes autour du joueur
        for (int dx = -3; dx <= 3; dx++) {
            int x = xJ + dx;
            // indice de la ligne
            System.out.print((x >= 0 && x < nb_lignes ? x : ".") + " | ");

            for (int dy = -3; dy <= 3; dy++) {
                int y = yJ + dy;
                if (x >= 0 && x < nb_lignes && y >= 0 && y < nb_colonnes) {
                    if (dx == 0 && dy == 0) {
                        System.out.print("You "); // joueur au centre
                    } else if (monde[x][y] == null) {
                        System.out.print(" .  "); // case vide
                    } else {
                        System.out.print(monde[x][y].getClass().getSimpleName().substring(0,3) + " ");
                    }
                } else {
                    System.out.print("XXX "); // hors limites
                }
            }
            System.out.println();
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Utilisable> getInventaire() {
        return inventaire;
    }

    /**
     *
     * @param inventaire
     */
    public void setInventaire(ArrayList<Utilisable> inventaire) {
        this.inventaire = inventaire;
    }

    /**
     *
     * @return
     */
    public ArrayList<Utilisable> getEffets() {
        return effets;
    }

    /**
     *
     * @param effets
     */
    public void setEffets(ArrayList<Utilisable> effets) {
        this.effets = effets;
    }
    
    
    
    
}
