/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author dytri
 */
public class PotionSoin extends Objet implements Utilisable{
    
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
    
    @Override
    public void utiliser(){
        System.out.println("===== "+ this.getDetenteur().getNom() + " utilise " + this.getNom() + " =====");
        this.getDetenteur().setPtVie(this.getDetenteur().getPtVie()+ptSoin);
        System.out.println(this.getDetenteur().getNom() + ": Pv = " + this.getDetenteur().getPtVie());
    }   
    
    @Override
    public void affiche(){
        if (this.getDetenteur() == null){
            super.affiche();
            System.out.println("Points de Soin : " + ptSoin);
        }
    }

    /** Crée une copie indépendante de la créature 
     * @return une potion de soin
     */
    @Override
    public PotionSoin copie(){
        return new PotionSoin(this);
    }
}
