/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public class PotionSoin extends Objet{
    
    private int ptSoin;
    
    public PotionSoin(String n, Point2D p, Creature det, int pSoin){
        super(n, p, det);
        this.ptSoin = pSoin;
    }   
    
    public PotionSoin(PotionSoin e){
        this(e.getNom(), e.getPos(), e.getDetenteur(), e.getPtSoin());
    }
    
    public PotionSoin(){
        this("Potion de Soin", new Point2D(0,0), null, 30);
    }

    public int getPtSoin() {
        return ptSoin;
    }

    public void setPtSoin(int ptSoin) {
        this.ptSoin = ptSoin;
    }
    

    
}
