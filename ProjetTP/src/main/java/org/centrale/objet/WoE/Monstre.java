/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
/**
 *
 * @author yaelv
 */
public class Monstre {
    
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos;

    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D p) {
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = p;
    }

    public Monstre() {
        this(100,10,2,60,40,new Point2D());
    }
    
    public Monstre(Monstre m) {
        this.ptVie = m.getPtVie();
        this.degAtt = m.getDegAtt();
        this.ptPar = m.getPtPar();
        this.pageAtt = m.getPageAtt();
        this.pagePar = m.getPagePar();
        this.pos = m.getPos();
    }
    

    public int getPtVie() {
        return ptVie;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPagePar() {
        return pagePar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public void setPos(Point2D p) {
        this.pos = new Point2D(p);
    }

    public void deplace(){
        Random aleaInt = new Random();
        int movex = aleaInt.nextInt(3)-1;
        int movey = aleaInt.nextInt(3)-1;
        pos.translate(movex,movey);
    }
    
    public void affiche(){
        System.out.println("===== Personnage =====");
        System.out.println("Points de vie : " + ptVie);
        System.out.println("Dégâts d'attaque : " + degAtt);
        System.out.println("Points de parade : " + ptPar);
        System.out.println("Pourcentage attaque : " + pageAtt + "%");
        System.out.println("Pourcentage parade : " + pagePar + "%");
        pos.affiche();
        System.out.println("======================");
    }
}
