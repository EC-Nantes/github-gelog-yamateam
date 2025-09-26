/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author Max, Yaël
 */
public class Testpoint2D {
    public static void main(String[] args){
        // On testera dans la suite en affichant les points (donc vérification après execution)
        // Création point sans paramètres
        Point2D p1 = new Point2D();
        // test
        p1.affiche();
        // Création d'un point avec paramètres
        Point2D p2 = new Point2D(1,2);
        // Afficher le point
        p2.affiche();
        // Modifier les coordonées du points
        p2.setPosition(2,3);
        // Verifier la modification
        p2.affiche();
        // Recopie du point dans le premier point
        p1.recopie(p2);
        // test
        p1.affiche();
        //Translation du deuxieme point
        p2.translate(4,5);
        //Verifier la modification
        p2.affiche();
        
        
    
    }
    
}
