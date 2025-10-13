/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author mathys,yaël
 */
public class TestWoE {
    
    public static void main(String[] args){
        
        World w = new World(10);
        w.creerMondeAlea();
        
        
        System.out.println("Sélectionnez son nombre de points de vie :");
        Scanner inputReader = new Scanner(System.in);
        int pv;
        try{
            pv = parseInt(inputReader.next());
            w.getListe_perso().get(0).setPtVie(pv);
        }catch(NumberFormatException e){
            System.out.println("Sélectionnez un nombre entier :");
        }
        
        
        
                
    }    
}
    
    
 
