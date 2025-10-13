/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author yaelv
 */
public class Lapin extends Monstre {

    /**
     *
     * @param ptVie
     * @param degAtt
     * @param ptPar
     * @param pageAtt
     * @param pagePar
     * @param dMax
     * @param p
     */
    public Lapin(String n,int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int dMax, Point2D p) {
        super(n, ptVie, degAtt, ptPar, pageAtt, pagePar, dMax, p);
    }

    /**
     *
     */
    public Lapin() {
        super("Lapin");
    }
    
    public Lapin(String n) {
        super(n);
    }

    /**
     *
     * @param l
     */
    public Lapin(Lapin l) {
        super(l);
    }
    
    /** Crée une copie indépendante de la créature 
     * @return un lapin
     */
    @Override
    public Lapin copie(){
        return new Lapin(this);
    }
}