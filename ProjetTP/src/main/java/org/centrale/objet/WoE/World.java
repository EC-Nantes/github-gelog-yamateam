package org.centrale.objet.WoE;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**Implémente le monde avec ses paramètres, ses éléments de jeu sur une grille n*n et les différentes fonctions qui proposent des choix au joueur.
 *
 * @author yaelv, mathys
 */


public class World {

    /**
     * 
     */
    private int taille;        
    /**
     * 
     */
    private ElementDeJeu monde[][];
    /**
     * 
     */
    private ArrayList<Creature> liste_perso;
    
    private Joueur joueur;

    /**
     *
     */
    public int compteurTour;
    
    /**
     *
     */
    private double pAge_perso_max;
    /**
     * 
     */
    private double pAge_monstre_max;
    /**
     * 
     */
    private double pAge_objet_max;
    
    /**
     * 
     * Crée un monde de taille n*n.
     * @param n
     */    
    public World(int n) {
        this.joueur = new Joueur();
        this.taille=n;
        this.monde = new ElementDeJeu[taille][taille];
        this.liste_perso  = new ArrayList<>();
        this.pAge_perso_max = 0.05;
        this.pAge_monstre_max = 0.1;
        this.pAge_objet_max = 0.1;
        this.compteurTour = 1;
         
    }
    
    /**
     * 
     * Crée un monde de taille 100*100 par défaut.
     */    
    public World() {
        this(100);
    }
    
    /**Permet au joueur de choisir de créer une nouvelle partie ou bien d'en charger une depuis un fichier texte dans le dossier de saves.
     *
     */
    public void choixMonde(){
        System.out.println("------ Création du monde ------");
        
        Scanner sc = new Scanner(System.in);
        boolean a_choisi_action = false;
        
        while(!a_choisi_action){
            System.out.println("Choisir : '1' : Nouvelle partie ou '2' : Charger une partie" );
            
            try{
                int i = sc.nextInt();
                
                switch(i){
                    case 1 :
                        a_choisi_action = true;
                        break;
                        
                    case 2: //Charger une partie
                        File dossier = new File("saves_WoE");
                        
                        //créer le dossier de sauvegarde si il n'existe pas déjà
                        if(!dossier.exists()){
                            dossier.mkdir();
                        }
                        
                        File[] fichiers = dossier.listFiles();
                        
                        if(fichiers.length == 0){ //Test aucune sauvegarde
                            System.out.println("Aucune sauvegarde disponible...");
                            System.out.println("Création d'une nouvelle partie...");
                            a_choisi_action = true;
                            break;
                        }
                        
                        System.out.println("------ Liste parties ------");
                        System.out.println("Choisir le numéro d'une sauvegarde :");
                        int k=0;
                        for (File f : fichiers) {
                            System.out.println(k + ". " + f.getName());
                            k++;
                        }

                        chargementPartie("saves_WoE/" + fichiers[sc.nextInt()].getName());
                        
                        a_choisi_action = true;
                        break;
                        
                    default:
                        System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                        break;
                }
                
            }catch(Exception e){
                System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                sc.nextLine();
            }
        }
    }
    
    /**Permet au joueur de choisir le nom (xyz.txt) de sa sauvegarde. 
     *
     * @return nom sauvegarde
     */
    public String choixNomSauvegarde(){
        Scanner sc = new Scanner(System.in);
        boolean a_choisi_action = false;
        String nom_save = "sauvegarde_rapide.txt";
        File dossier = new File("saves_WoE");
        //créer le dossier de sauvegarde si il n'existe pas déjà
        if(!dossier.exists()){
            dossier.mkdir();
        }
                        
        
        while(!a_choisi_action){
            System.out.println("Choisir : '1' : Écraser une sauvegarde existante ou '2' : Nouvelle sauvegarde");
            
            try{
                int i = sc.nextInt();

                switch(i){
                    case 1 ://choisi la sauvegarde a écraser
                        
                        File[] fichiers = dossier.listFiles();
                        
                        if(fichiers.length == 0){ //Test aucune sauvegarde
                            System.out.println("Aucune sauvegarde disponible...");
                            break;
                        }
                        
                        System.out.println("------ Liste sauvegardes ------");
                        System.out.println("Choisir le numéro d'une sauvegarde :");
                        int k=0;
                        for (File f : fichiers) {
                            System.out.println(k + ". " + f.getName());
                            k++;
                        }
                        
                        nom_save = "saves_WoE/"  + fichiers[sc.nextInt()].getName();
                        a_choisi_action = true;
                        break;
                        
                    case 2 ://choisi le nom de la sauvegarde
                        boolean a_choisi_nom = false;
                        
                        while(!a_choisi_nom){
                            System.out.println("Choisir le nom d'une sauvegarde (ex : save.txt) :");
                            nom_save = "saves_WoE/"  + sc.next();

                            if (nom_save.length() < 4 || !nom_save.endsWith(".txt")) { // test la bonne extension
                                System.out.println("Erreur : vous n'avez pas rentré un nom en .txt, recommencez");
                            }
                            else{
                                a_choisi_nom = true;
                            }
                        }
                        
                        a_choisi_action = true;
                        break;
                        
                    default:
                        System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                        break;

                }

            }catch(Exception e){
                System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                sc.nextLine();
            }
        }
        
        return nom_save;
    }
    
    /**Permet au joueur de sauvegarder sa partie dans un fichier texte dans le dossier de saves.
     *
     * @param nom
     */
    public void sauvegardePartie(String nom){
        BufferedWriter fichier = null;
        try{
            fichier = new BufferedWriter(new FileWriter(nom));
            
            fichier.write("Taille|"+taille);
            fichier.newLine();
            fichier.write("CompteurTour|"+compteurTour);
            fichier.newLine();
            for(ElementDeJeu[] ligne_monde : monde){//parcours lignes
                for(ElementDeJeu e : ligne_monde){//parcours colonnes
                    
                    if(e == null || e == joueur.getPerso() ){ //case vide ou perso (pas envie de le stocker comme une créature lambda)
                        continue;
                    }
                    
                    if (e instanceof Creature c){ // element type créature 
                        switch(c.getClass().getSimpleName()){
                            case "Guerrier":
                                fichier.write(c.getClass().getSimpleName() + "|" + c.getNom() + "|" + c.getPtVie() + "|" + c.getDegAtt() + "|" +  c.getPtPar() + "|" +c.getPageAtt() + "|" +c.getPagePar() + "|" +c.getDistAttMax() + "|" +c.getPos().getX() + "|" +c.getPos().getY());
                                fichier.newLine();
                                break;
                            case "Archer":
                                fichier.write(c.getClass().getSimpleName() + "|" + c.getNom() + "|" + c.getPtVie() + "|" + c.getDegAtt() + "|" +  c.getPtPar() + "|" +c.getPageAtt() + "|" +c.getPagePar() + "|" +c.getDistAttMax() + "|" +c.getPos().getX() + "|" +c.getPos().getY() + "|" + ((Archer)c).getNbFleches());
                                fichier.newLine();
                                break;
                            case "Paysan":
                                fichier.write(c.getClass().getSimpleName() + "|" + c.getNom() + "|" + c.getPtVie() + "|" + c.getDegAtt() + "|" +  c.getPtPar() + "|" +c.getPageAtt() + "|" +c.getPagePar() + "|" +c.getDistAttMax() + "|" +c.getPos().getX() + "|" +c.getPos().getY());
                                fichier.newLine();
                                break;
                            case "Loup":
                                fichier.write(c.getClass().getSimpleName() + "|" + c.getNom() + "|" + c.getPtVie() + "|" + c.getDegAtt() + "|" +  c.getPtPar() + "|" +c.getPageAtt() + "|" +c.getPagePar() + "|" +c.getDistAttMax() + "|" +c.getPos().getX() + "|" +c.getPos().getY());
                                fichier.newLine();
                                break;
                            case "Lapin":
                                fichier.write(c.getClass().getSimpleName() + "|" + c.getNom() + "|" + c.getPtVie() + "|" + c.getDegAtt() + "|" +  c.getPtPar() + "|" +c.getPageAtt() + "|" +c.getPagePar() + "|" +c.getDistAttMax() + "|" +c.getPos().getX() + "|" +c.getPos().getY());
                                fichier.newLine();
                                break;
                        }
                    }
                    
                    else{//element de type objet/nourriture
                        switch(e.getClass().getSimpleName()){
                            case "NuageToxique":
                                fichier.write(e.getClass().getSimpleName() + "|" + ((Objet)e).getEffet() + "|" + ((NuageToxique)e).getVitesse() + "|" +((NuageToxique)e).getLongueur() + "|" + ((NuageToxique)e).getLargeur() + "|" +((NuageToxique)e).getPos().getX() + "|" +((NuageToxique)e).getPos().getY());
                                fichier.newLine();
                                break;  
                            case "PotionSoin":
                                fichier.write(e.getClass().getSimpleName() + "|" + ((Objet)e).getNom() + "|" + ((Objet)e).getEffet() + "|" +((Objet)e).getPos().getX() + "|" +((Objet)e).getPos().getY());
                                fichier.newLine();
                                break; 
                            case "Epee":
                                fichier.write(e.getClass().getSimpleName() + "|" + ((Objet)e).getNom() + "|" + ((Objet)e).getEffet() + "|" +((Objet)e).getPos().getX() + "|" +((Objet)e).getPos().getY());
                                fichier.newLine();
                                break; 
                            case "Malus":
                                fichier.write(e.getClass().getSimpleName() + "|" + ((Nourriture)e).getNom() + "|" + ((Nourriture)e).getType() + "|" + ((Nourriture)e).getEffet() + "|" + ((Nourriture)e).getNb_tours() + "|" + ((Nourriture)e).getCaracteristique() + "|" + ((Nourriture)e).getPos().getX() + "|" +((Nourriture)e).getPos().getY());
                                fichier.newLine();
                                break; 
                            case "Bonus":
                                fichier.write(e.getClass().getSimpleName() + "|" + ((Nourriture)e).getNom() + "|" + ((Nourriture)e).getType() + "|" + ((Nourriture)e).getEffet() + "|" + ((Nourriture)e).getNb_tours() + "|" + ((Nourriture)e).getCaracteristique() + "|" + ((Nourriture)e).getPos().getX() + "|" +((Nourriture)e).getPos().getY());
                                fichier.newLine();
                                break; 
                                
                        }
                        
                    }

                }

            }
            
            switch(joueur.getPerso().getClass().getSimpleName()){
                case "Archer":
                    fichier.write("Joueur|" + joueur.getPerso().getClass().getSimpleName() + "|" + joueur.getPerso().getNom() + "|" + joueur.getPerso().getPtVie() + "|" + joueur.getPerso().getDegAtt() + "|" +  joueur.getPerso().getPtPar() + "|" +joueur.getPerso().getPageAtt() + "|" +joueur.getPerso().getPagePar() + "|" +joueur.getPerso().getDistAttMax() + "|" +joueur.getPerso().getPos().getX() + "|" +joueur.getPerso().getPos().getY() + "|" + ((Archer)joueur.getPerso()).getNbFleches());
                    fichier.newLine();
                    break;
                case "Guerrier":
                    fichier.write("Joueur|" + joueur.getPerso().getClass().getSimpleName() + "|" + joueur.getPerso().getNom() + "|" + joueur.getPerso().getPtVie() + "|" + joueur.getPerso().getDegAtt() + "|" +  joueur.getPerso().getPtPar() + "|" +joueur.getPerso().getPageAtt() + "|" +joueur.getPerso().getPagePar() + "|" +joueur.getPerso().getDistAttMax() + "|" +joueur.getPerso().getPos().getX() + "|" +joueur.getPerso().getPos().getY());
                    fichier.newLine();
                    break;
            }
            
            
            for(Utilisable e : joueur.getInventaire()){
                switch(e.getClass().getSimpleName()){
                    case "NuageToxique":
                        fichier.write("Inventaire|" + e.getClass().getSimpleName() + "|" + ((Objet)e).getEffet() + "|" + ((NuageToxique)e).getVitesse() + "|" +((NuageToxique)e).getLongueur() + "|" + ((NuageToxique)e).getLargeur() + "|" +((NuageToxique)e).getPos().getX() + "|" +((NuageToxique)e).getPos().getY());
                        fichier.newLine();
                        break;  
                   case "PotionSoin":
                        fichier.write("Inventaire|" + e.getClass().getSimpleName() + "|" + ((Objet)e).getNom() + "|" + ((Objet)e).getEffet() + "|" +((Objet)e).getPos().getX() + "|" +((Objet)e).getPos().getY());
                        fichier.newLine();
                        break; 
                    case "Epee":
                        fichier.write("Inventaire|" + e.getClass().getSimpleName() + "|" + ((Objet)e).getNom() + "|" + ((Objet)e).getEffet() + "|" +((Objet)e).getPos().getX() + "|" +((Objet)e).getPos().getY());
                        fichier.newLine();
                        break; 
                    case "Malus":
                        fichier.write("Inventaire|" + e.getClass().getSimpleName() + "|" + ((Nourriture)e).getNom() + "|" + ((Nourriture)e).getType() + "|" + ((Nourriture)e).getEffet() + "|" + ((Nourriture)e).getNb_tours() + "|" + ((Nourriture)e).getCaracteristique() + "|" + ((Nourriture)e).getPos().getX() + "|" +((Nourriture)e).getPos().getY());
                        fichier.newLine();
                        break; 
                    case "Bonus":
                        fichier.write("Inventaire|" + e.getClass().getSimpleName() + "|" + ((Nourriture)e).getNom() + "|" + ((Nourriture)e).getType() + "|" + ((Nourriture)e).getEffet() + "|" + ((Nourriture)e).getNb_tours() + "|" + ((Nourriture)e).getCaracteristique() + "|" + ((Nourriture)e).getPos().getX() + "|" +((Nourriture)e).getPos().getY());
                        fichier.newLine();
                        break; 
                                
                }
            }
            
            for(Utilisable e : joueur.getEffets()){
                switch(e.getClass().getSimpleName()){
                    case "NuageToxique":
                        fichier.write("Effets|" + e.getClass().getSimpleName() + "|" + ((Objet)e).getEffet() + "|" + ((NuageToxique)e).getVitesse() + "|" +((NuageToxique)e).getLongueur() + "|" + ((NuageToxique)e).getLargeur() + "|" +((NuageToxique)e).getPos().getX() + "|" +((NuageToxique)e).getPos().getY());
                        fichier.newLine();
                        break;  
                   case "PotionSoin":
                        fichier.write("Effets|" + e.getClass().getSimpleName() + "|" + ((Objet)e).getNom() + "|" + ((Objet)e).getEffet() + "|" +((Objet)e).getPos().getX() + "|" +((Objet)e).getPos().getY());
                        fichier.newLine();
                        break; 
                    case "Epee":
                        fichier.write("Effets|" + e.getClass().getSimpleName() + "|" + ((Objet)e).getNom() + "|" + ((Objet)e).getEffet() + "|" +((Objet)e).getPos().getX() + "|" +((Objet)e).getPos().getY());
                        fichier.newLine();
                        break; 
                    case "Malus":
                        fichier.write("Effets|" + e.getClass().getSimpleName() + "|" + ((Nourriture)e).getNom() + "|" + ((Nourriture)e).getType() + "|" + ((Nourriture)e).getEffet() + "|" + ((Nourriture)e).getNb_tours() + "|" + ((Nourriture)e).getCaracteristique() + "|" + ((Nourriture)e).getPos().getX() + "|" +((Nourriture)e).getPos().getY());
                        fichier.newLine();
                        break; 
                    case "Bonus":
                        fichier.write("Effets|" + e.getClass().getSimpleName() + "|" + ((Nourriture)e).getNom() + "|" + ((Nourriture)e).getType() + "|" + ((Nourriture)e).getEffet() + "|" + ((Nourriture)e).getNb_tours() + "|" + ((Nourriture)e).getCaracteristique() + "|" + ((Nourriture)e).getPos().getX() + "|" +((Nourriture)e).getPos().getY());
                        fichier.newLine();
                        break; 
                                
                }
            }
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{ //ferme le fichier quoiqu'il arrive
            try{
                fichier.flush();
                fichier.close();
                System.out.println("Fichier sauvegardé");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    
    }

    /**Permet au joueur de charger une partie en lisant le fichier .txt et en créant le monde depuis ce dernier.
     *
     * @param nom
     */
    public void chargementPartie(String nom){
        
        ListStringTokenizer tokenizer = new ListStringTokenizer(); 
        
        try{
            String ligne;
            BufferedReader fichier = new BufferedReader(new FileReader(nom));
            ligne = fichier.readLine();
            
            while(ligne != null){
                ArrayList<String> mots = tokenizer.decoupe(ligne);
                
                switch(mots.get(0)){
                    case "Taille":
                        this.taille = Integer.parseInt(mots.get(1));
                        this.monde = new ElementDeJeu[taille][taille];
                        break;
                    case "CompteurTour":
                        compteurTour = Integer.parseInt(mots.get(1));
                        break;
                    case "Guerrier":
                        Guerrier guerrier = new Guerrier(mots.get(1), Integer.parseInt(mots.get(2)), Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)), new Point2D(Integer.parseInt(mots.get(8)),Integer.parseInt(mots.get(9))) );
                        monde[Integer.parseInt(mots.get(8))][Integer.parseInt(mots.get(9))] = guerrier;
                        liste_perso.add(guerrier);
                        break;
                        
                    case "Archer":
                        Archer archer = new Archer(mots.get(1), Integer.parseInt(mots.get(2)), Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)), new Point2D(Integer.parseInt(mots.get(8)),Integer.parseInt(mots.get(9))), Integer.parseInt(mots.get(10)) );
                        monde[Integer.parseInt(mots.get(8))][Integer.parseInt(mots.get(9))] = archer;
                        liste_perso.add(archer);
                        break;
                    case "Paysan":
                        Paysan paysan = new Paysan(mots.get(1), Integer.parseInt(mots.get(2)), Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)), new Point2D(Integer.parseInt(mots.get(8)),Integer.parseInt(mots.get(9))) );
                        monde[Integer.parseInt(mots.get(8))][Integer.parseInt(mots.get(9))] = paysan;
                        liste_perso.add(paysan);
                        break;                   
                    case "Loup":
                        Loup loup = new Loup(mots.get(1), Integer.parseInt(mots.get(2)), Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)), new Point2D(Integer.parseInt(mots.get(8)),Integer.parseInt(mots.get(9))) );
                        monde[Integer.parseInt(mots.get(8))][Integer.parseInt(mots.get(9))] = loup;
                        liste_perso.add(loup);
                        break;  
                    case "Lapin":    
                        Lapin lapin = new Lapin(mots.get(1), Integer.parseInt(mots.get(2)), Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)), new Point2D(Integer.parseInt(mots.get(8)),Integer.parseInt(mots.get(9))) );
                        monde[Integer.parseInt(mots.get(8))][Integer.parseInt(mots.get(9))] = lapin;
                        liste_perso.add(lapin);
                        break;  
                    case "Nuagetoxique":    
                        NuageToxique nuage = new NuageToxique(Integer.parseInt(mots.get(1)),Integer.parseInt(mots.get(2)), Integer.parseInt(mots.get(3)), Integer.parseInt(mots.get(4)), new Point2D(Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6))));
                        monde[Integer.parseInt(mots.get(5))][Integer.parseInt(mots.get(6))] = nuage;
                        break; 
                    case "Potionsoin":  
                        PotionSoin potion = new PotionSoin(mots.get(1),Integer.parseInt(mots.get(2)),new Point2D(Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4))));
                        monde[Integer.parseInt(mots.get(3))][Integer.parseInt(mots.get(4))] = potion;
                        break; 
                    case "Epee":
                        Epee epee = new Epee(mots.get(1),Integer.parseInt(mots.get(2)),new Point2D(Integer.parseInt(mots.get(3)),Integer.parseInt(mots.get(4))));
                        monde[Integer.parseInt(mots.get(3))][Integer.parseInt(mots.get(4))] = epee;
                        break; 
                    case "Malus":
                        Nourriture bonus = new Bonus(mots.get(1),mots.get(2), Integer.parseInt(mots.get(3)), Integer.parseInt(mots.get(4)), mots.get(5), new Point2D(Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7))));
                        monde[Integer.parseInt(mots.get(6))][Integer.parseInt(mots.get(7))] = bonus;
                        break;
                    case "Bonus":
                        Nourriture malus = new Malus(mots.get(1),mots.get(2), Integer.parseInt(mots.get(3)), Integer.parseInt(mots.get(4)), mots.get(5), new Point2D(Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7))));
                        monde[Integer.parseInt(mots.get(6))][Integer.parseInt(mots.get(7))] = malus;
                        break; 
                        
                    case "Joueur":
                        switch(mots.get(1)){
                            case "Guerrier":
                                joueur.setPerso(new Guerrier(mots.get(2), Integer.parseInt(mots.get(3)), Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)),Integer.parseInt(mots.get(8)), new Point2D(Integer.parseInt(mots.get(9)),Integer.parseInt(mots.get(10)))));
                                break;

                            case "Archer":
                                joueur.setPerso(new Archer(mots.get(2), Integer.parseInt(mots.get(3)), Integer.parseInt(mots.get(4)),Integer.parseInt(mots.get(5)),Integer.parseInt(mots.get(6)),Integer.parseInt(mots.get(7)),Integer.parseInt(mots.get(8)), new Point2D(Integer.parseInt(mots.get(9)),Integer.parseInt(mots.get(10))), Integer.parseInt(mots.get(11))));
                                break;
                            default:
                                break;
                        }
                        monde[joueur.getPerso().getPos().getX()][joueur.getPerso().getPos().getY()] = joueur.getPerso();
                        liste_perso.add(joueur.getPerso());
                        
                    case "Inventaire":
                        switch(mots.get(1)){
                            case "Potionsoin":  
                                Utilisable potion_inv = new PotionSoin(mots.get(2),Integer.parseInt(mots.get(3)),new Point2D());
                                joueur.getInventaire().add(potion_inv);
                                break; 
                            case "Epee":
                                Utilisable epee_inv = new Epee(mots.get(2),Integer.parseInt(mots.get(3)),new Point2D());
                                joueur.getInventaire().add(epee_inv);
                                break; 
                            case "Bonus":
                                Utilisable bonus_inv = new Bonus(mots.get(2),mots.get(3), Integer.parseInt(mots.get(4)), Integer.parseInt(mots.get(5)), mots.get(6), new Point2D());
                                joueur.getInventaire().add(bonus_inv);
                                break; 
                            case "Malus":
                                Utilisable malus_inv = new Malus(mots.get(2),mots.get(3), Integer.parseInt(mots.get(4)), Integer.parseInt(mots.get(5)), mots.get(6), new Point2D());
                                joueur.getInventaire().add(malus_inv);
                                break;
                            default:
                                break;
                        }
                        
                    case "Effets":
                        switch(mots.get(1)){
                            case "Potionsoin":  
                                Utilisable potion_effet = new PotionSoin(mots.get(2),Integer.parseInt(mots.get(3)),new Point2D());
                                joueur.getEffets().add(potion_effet);
                                break; 
                            case "Epee":
                                Utilisable epee_effet = new Epee(mots.get(2),Integer.parseInt(mots.get(3)),new Point2D());
                                joueur.getEffets().add(epee_effet);
                                break; 
                            case "Bonus":
                                Utilisable bonus_effet = new Bonus(mots.get(2),mots.get(3), Integer.parseInt(mots.get(4)), Integer.parseInt(mots.get(5)), mots.get(6), new Point2D());
                                joueur.getEffets().add(bonus_effet);
                                break; 
                            case "Malus":
                                Utilisable malus_effet = new Malus(mots.get(2),mots.get(3), Integer.parseInt(mots.get(4)), Integer.parseInt(mots.get(5)), mots.get(6), new Point2D());
                                joueur.getEffets().add(malus_effet);
                                break;
                            default:
                                break;
                        }
                    default :
                        break;
                        
                }
                
                ligne = fichier.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }    
    
    /**
     *
     * @return liste_perso
     */
    public ArrayList<Creature> getListe_perso() {
        return liste_perso;
    }
     
    /**Ajoute (un nombre aléatoire selon pAge pour respecter une proportion) un type d'element de jeu au monde en générant des positions aléatoires et en choisissant un sous-type aléatoirement.
     *
     * @param pAge          Le pourcentage d'élément dans le monde est entre pAge et pAge/2
     * @param liste_type    Contient toutes les sous-classes de l'élément 
     */
    private void ajouterElement(double pAge, ArrayList<ElementDeJeu> liste_type){
        Random aleaInt = new Random();
        
        int n = aleaInt.nextInt((int)Math.floor(pAge*taille*taille) - (int)Math.floor(pAge*taille*taille/2) + 1) + (int)Math.floor(pAge*taille*taille/2);
      
        
        for(int i=0;i<n;i++){
            
            int x = aleaInt.nextInt(taille);
            int y = aleaInt.nextInt(taille);
            
            while(monde[x][y] != null){
                x = aleaInt.nextInt(taille);
                y = aleaInt.nextInt(taille);
            }
            
            //choisis le type de l'élément
            ElementDeJeu type = liste_type.get(aleaInt.nextInt(liste_type.size()));
            ElementDeJeu new_element = type.copie(); //copie indépendante
            new_element.setPos(new Point2D(x,y));
            monde[x][y] = new_element;
            
            if (new_element instanceof Creature new_creature){
                liste_perso.add(new_creature);
            }
            
        }
    }
    
    /**Génère le monde soit en le chargeant soit en générant tous les éléments aléatoirement puis lance un tourDeJeu.
     * 
     */        
    public void creerMondeAlea(){
        choixMonde();
        if(joueur.getPerso() == null){ //si le monde n'est pas déjà créer et qu'il n'a pas été chargé
            creationJoueur();
            this.ajouterElement(pAge_perso_max, new ArrayList<>(List.of(new Guerrier(), new Archer(), new Paysan())));
            this.ajouterElement(pAge_monstre_max, new ArrayList<>(List.of(new Loup(), new Lapin())));
            this.ajouterElement(pAge_objet_max, new ArrayList<>(List.of(new PotionSoin(), new Epee(), new Malus(), new Bonus(), new NuageToxique())));
        }

        tourDeJeu();
    }
    
    /**Effectue un tour de jeu où le joueur peut choisir entre :
     * plusieurs actions définitive (saute de tour après) comme se déplacer et combattre
     * ou bien une action alternative (reste dans le tour) comme regarder son inventaire et ses stats ou bien sauvegarder/quitter la partie.
     *
     */
    public void tourDeJeu(){
        System.out.println("------ Tour de Jeu n°" + compteurTour + " ------");
        joueur.afficherGrille(monde);
        
        Scanner sc = new Scanner(System.in);
        boolean a_choisi_action = false;
        boolean joue = true;
        
        while(!a_choisi_action){
            System.out.println("\n- Choisir une action définitive: '1' : Combattre ou '2' : Se deplacer");
            System.out.println("- Choisir une action alternative: '3' : Inventaire, '4' : Statistiques ou '5' : Pause");
            
            try{
                
                switch (sc.nextInt()){
                    case 1 :
                        joueur.combattre(this);
                        a_choisi_action = true;
                        break;

                    case 2 :
                        Point2D pos_avant_dep = joueur.getPerso().getPos();
                        Point2D pos_apres_dep = joueur.deplacer(this);
                        
                        if(pos_apres_dep != null){
                            monde[pos_avant_dep.getX()][pos_avant_dep.getY()] = null;
                            monde[pos_apres_dep.getX()][pos_apres_dep.getY()] = joueur.getPerso();
                        }
                        
                        a_choisi_action = true;
                        break;
                        
                    case 3 :    
                        joueur.RegarderInventaire(this);
                        sc.nextLine();
                        break;
                     
                    case 4 :    
                        joueur.getPerso().affiche();
                        sc.nextLine();
                        break;
                     
                    case 5 :
                        System.out.println("------ Pause || ------");
                        System.out.println("Choix : '1' : Continuer, '2' : Sauvegarder ou '3' : Quitter");
                        
                        try{
                            int choix = sc.nextInt();
                            switch(choix){
                                case 1: //continue
                                    break;
                                    
                                case 2 : //sauvegarde
                                    sauvegardePartie(choixNomSauvegarde());
                                    break;

                                case 3: //arret de jouer
                                    System.out.println("------ Arret du jeu ------");
                                    joue = false;
                                    a_choisi_action = true;
                                    break;
                                    
                                default:
                                    System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                                    break;
                                    
                            }
                        break;


                        }catch(Exception e){
                            System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                            sc.nextLine();  
                        }
                        
                    default :
                        System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                        break;
                }
            }catch(Exception e){
                System.out.println("Erreur : vous n'avez pas rentré un chiffre valide, recommencez");
                sc.nextLine();
            }
        }
        
        ArrayList<Utilisable> effets = joueur.getEffets();
        //expiration effet
        for (int i = 0; i < effets.size(); i++) {
            Utilisable effet = effets.get(i);

            if (effet.estExpirer(this)) {
                effet.retirer(joueur.getPerso());
                effets.remove(i); // retire de la liste
                System.out.println("L'effet de " + effet.getNom() + " sur " + joueur.getPerso().getNom() + " est terminé.");
            }
        }
        
        compteurTour += 1;
        if(joue){
            tourDeJeu();
        }
        
    }
    
    
    /**Permet au joueur de choisir le nom et la classe de son personnage.
     *
     */
    public void creationJoueur(){
        System.out.println("------------------- Légende -------------------");
        System.out.println("- le joueur (you) sera affiché sur une carte 3x3 des cases adjacentes à ce dernier");
        System.out.println("- les cases vides seront indiquées par .");
        System.out.println("- les cases occupées seront indiquées par les trois premières lettres de la classe occupant la case (ex : LOU, GUE, ARC, LAP)");
        
        Random aleaInt = new Random();
        boolean verif = false;
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("-------------- Choix du personnage --------------");
       
        System.out.println("Entrez le nom de votre personnage :");
        String nom = inputReader.nextLine();
            
        while(!verif){   
            
            System.out.println("Entrez la classe de personnage que vous souhaitez jouer :");
            String classe = inputReader.next();
            
            verif = this.joueur.choixPerso(nom,classe);
        }
        
        // fait apparaitre le joueur sur une case libre du monde
        Point2D p = new Point2D();
        p.setX(aleaInt.nextInt(taille));
        p.setY(aleaInt.nextInt(taille));
        
        while(monde[p.getX()][p.getY()] != null){
                p.setX(aleaInt.nextInt(taille));
                p.setY(aleaInt.nextInt(taille));
        }
        
        joueur.getPerso().setPos(p);
        liste_perso.add(joueur.getPerso());
        
        monde[p.getX()][p.getY()] = joueur.getPerso();
    }

    /**
     *
     * @return compteurTour
     */
    public int getCompteurTour() {
        return compteurTour;
    }

    /**
     *
     * @param compteurTour
     */
    public void setCompteurTour(int compteurTour) {
        this.compteurTour = compteurTour;
    }

    /**
     *
     * @return joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     *
     * @return grille du monde
     */
    public ElementDeJeu[][] getMonde() {
        return monde;
    }
    
}
