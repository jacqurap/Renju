/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author jacqurap
 */
public class Plateau {

    public static final int CASEVIDE = 0;
    public static final int CASEBLANCHE = 1;
    public static final int CASENOIRE = 2;
    public static final int DEFAULT_DIMX = 15;
    public static final int DEFAULT_DIMY = 15;

    private int dimX;
    private int dimY;
    private int[][] grille;

    public Plateau(int x, int y) {
        this.dimX = x;
        this.dimY = y;
        this.grille = new int[x][y];

    }

    public Plateau() {
        //this.dimX = DEFAULT_DIMX;
        //this.dimY = DEFAULT_DIMY;
        //this.grille = new int[DEFAULT_DIMX][DEFAULT_DIMY];
        this(DEFAULT_DIMX, DEFAULT_DIMY);
    }

    public int getCase(int x, int y) {
        return grille[x][y];
    }

    public void setCase(int x, int y, int value) {
        grille[x][y] = value;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }
    
    public int[][] getGrille() {
        return this.grille;
    }
    
    public void setGrille(int[][] g) {
        this.grille = g;
    }

    // clone avec le constructeur suivant
    public Plateau clone() {
        return new Plateau(grille.clone());
    }

    private Plateau(int[][] grille) { //private car ne doit etre utilise que depuis cet objet (pour le clone)
        this.grille = grille;
        this.dimX = grille.length;
        this.dimY = grille[0].length;
    }
}
