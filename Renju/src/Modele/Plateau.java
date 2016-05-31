/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.Serializable;
import java.util.Random;

/**
 * Le plateau
 *
 * @author jacqurap
 */
public class Plateau implements Serializable{

    public static final int CASEVIDE = 0;
    public static final int CASEBLANCHE = 1;
    public static final int CASENOIRE = 2;
    public static final int DEFAULT_DIMX = 15;
    public static final int DEFAULT_DIMY = 15;

    private int dimX;
    private int dimY;
    private int[][] grille;
    private int[][] numerocoup;

    public int rand;
    
    /**
     * Creation d'un plateau
     *
     * @param x le nombre d'intersection en horizontal
     * @param y le nombre d'intersection en vertical
     */
    public Plateau(int x, int y) {
        this.dimX = x;
        this.dimY = y;
        this.grille = new int[x][y];
        this.numerocoup= new int[x][y];
Random r = new Random(System.currentTimeMillis());
rand = r.nextInt();
    }

    /**
     * Constructeur par defaut
     */
    public Plateau() {
        //this.dimX = DEFAULT_DIMX;
        //this.dimY = DEFAULT_DIMY;
        //this.grille = new int[DEFAULT_DIMX][DEFAULT_DIMY];
        this(DEFAULT_DIMX, DEFAULT_DIMY);
    }

    /**
     * Recupere la valeur d'une intersection
     *
     * @param x la position en x
     * @param y la position en y
     * @return grille[x][y], la valeur de l'intersection
     */
    public int getCase(int x, int y) {
        return grille[x][y];
    }

    /**
     * Attribue une valeur a une intersection
     *
     * @param x la position en x
     * @param y la position en y
     * @param value la valeur a attribuer
     */
    public void setCase(int x, int y, int value) {
        grille[x][y] = value;
    }
    
    public int getNumero(int x, int y) {
        return numerocoup[x][y];
    }

    /**
     * Attribue un numero de coup a une intersection
     *
     * @param x la position en x
     * @param y la position en y
     * @param value la valeur a attribuer
     */
    public void setNumero(int x, int y, int value) {
        numerocoup[x][y] = value;
    }
    

    /**
     * Recupere la dimension du plateau en x
     *
     * @return dimX
     */
    public int getDimX() {
        return dimX;
    }

    /**
     * Recupere la dimension du plateau en y
     *
     * @return dimY
     */
    public int getDimY() {
        return dimY;
    }

    /**
     * Recupere la grille du plateau
     *
     * @return grille
     */
    public int[][] getGrille() {
        return this.grille;
    }

    /**
     * Attribue une grille au plateau
     *
     * @param g la grille a attribuer
     */
    public void setGrille(int[][] g) {
        this.grille = g;
    }

    /**
     * Recupere la couleur de l'adversaire
     *
     * @param couleur la couleur de la pierre du joueur courant
     * @return la couleur de la pierre de l'adversaire
     */
    public int getAutreCouleur(int couleur) {
        return (couleur == CASEBLANCHE) ? CASENOIRE : CASEBLANCHE;
    }

    // clone avec le constructeur suivant
    public Plateau clone() {
        Plateau newPlateau = new Plateau(dimX, dimY);
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimX; j++) {
                newPlateau.setCase(i, j, grille[i][j]);
                newPlateau.setNumero(i, j, numerocoup[i][j]);
            }
        }
        System.out.println("this.rand = " + this.rand + " \t newrand = " + newPlateau.rand);
        return newPlateau;
    }

	public int[][] getNumerocoup() {
		return numerocoup;
	}

	public void setNumerocoup(int[][] numerocoup) {
		this.numerocoup = numerocoup;
	}

 
}
