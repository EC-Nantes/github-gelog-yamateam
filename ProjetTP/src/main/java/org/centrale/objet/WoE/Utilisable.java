/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public interface Utilisable {  
    public void utiliser(Personnage perso, World world);
    public void retirer(Personnage perso);
    public boolean estExpirer(World world);
    public String getNom();
    public String getCaracteristique();
    public int getEffet();
    public Utilisable copie();
    public void setPos(Point2D pos);
    
}
