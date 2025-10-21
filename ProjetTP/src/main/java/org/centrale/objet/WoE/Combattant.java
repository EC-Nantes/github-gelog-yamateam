/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Interface indiquant les classes qui peuvent combattre
 * 
 * @author yaelv
 */
public interface Combattant {
    /**
     * Fait ce combattre l'objet avec une créature passée en argument
     * 
     * @param c une créature
     */
    public void combattre(Creature c);
}
