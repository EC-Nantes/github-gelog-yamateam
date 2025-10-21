/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.objet.WoE;

/**Interface indiquant les classes qui peuvent être récupérés par le joueur 
 *
 * @author yaelv, mathys
 */
public interface Utilisable {  
     /**Applique l'effet de l'objet au perso
     * 
     * @param perso
     * @param world
     */
    public void utiliser(Personnage perso, World world);
    
    /**Retire l'effet de l'objet au perso
     * 
     * @param perso
     */
    public void retirer(Personnage perso);
   
    /** Dis si l'effet de l'objet est expiré ou non selon le numéro du round
     * 
     * @param world
     * @return bool_expiration
     */
    public boolean estExpirer(World world);
    public String getNom();
    public String getCaracteristique();
    public int getEffet();
    /**
     * Fais une copie indépendante de l'objet
     * @return utilisable_copie
     */
    public Utilisable copie();
    public void setPos(Point2D pos);
    
}
