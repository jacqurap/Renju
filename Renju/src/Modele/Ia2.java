/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Partie;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * L'IA (Niveau 3) qui joue avec un arbre MiniMax
 *
 * @author jacqurap
 */
public class Ia2 extends Ia {

    /**
     * Creation de l'IA (Niveau 3)
     *
     * @param nom le nom de l'IA
     */
    public Ia2(String nom) {
        super(nom);
        this.profondeur = 4;
        listeDeListe = (ArrayList<Point>[]) new ArrayList[profondeur];
        for (int i = 0; i < profondeur; i++) {
            listeDeListe[i] = new ArrayList<Point>(255);
        }
    }
}
