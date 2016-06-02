/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Partie;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * L'IA (Niveau 1) qui
 * joue aleatoirement sur une case libre du plateau
 * @author jacqurap
 */
public class Ia1 extends Ia{
    
	/**
	 * Creation de l'IA (Niveau 1)
	 * @param nom le nom de l'IA
	 */
	
    public Ia1(String nom) {
        super(nom);
        this.profondeur = 2;
        listeDeListe = (ArrayList<Point>[]) new ArrayList[profondeur];
        for (int i = 0; i < profondeur; i++) {
            listeDeListe[i] = new ArrayList<Point>(225);
        }
    }
}
