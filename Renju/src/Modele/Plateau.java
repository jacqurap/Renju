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
    private int[][] plateau;
    
    public Plateau(int x, int y){
        this.dimX = x;
        this.dimY = y;
        this.plateau = new int[x][y];
    }
    public Plateau(){
        new Plateau(DEFAULT_DIMX, DEFAULT_DIMY);
    }
    
    
    public int getCase(int x, int y){
        return plateau[x][y];
    }
    public void setCase(int x, int y, int value){
        plateau[x][y] = value;
    }
    
    public int getDimX(){
        return dimX;
    }
    public int getDimY(){
        return dimY;
    }
    
    // clone avec le constructeur suivant
    public Plateau clone(){
        return new Plateau(plateau.clone());
    }
    private Plateau(int[][] plateau){ //private car ne doit etre utilise que depuis cet objet (pour le clone)
        this.plateau = plateau;
    }
}
