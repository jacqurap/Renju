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
 * @author jacqurap
 */
public class Ia3 extends Ia {

	/**
	 * Creation de l'IA (Niveau 3)
	 * @param nom le nom de l'IA
	 */
	
    public Ia3(String nom) {
        super(nom);
    }

    @Override
    public Point solver(Partie partie) {
        int meilleur;
        int couleur;
        ArrayList<ValeurCoup> listeCoupsValeur = new ArrayList<>();
        if (partie.getNbCoups() % 2 == 0) {
            couleur = Plateau.CASENOIRE;
        } else {
            couleur = Plateau.CASEBLANCHE;
        }
        for (Point p : coupsPertinents(partie.getPlateau())) {
            listeCoupsValeur.add(new ValeurCoup(p, minimax(partie.getPlateau(), p, 5, couleur, true)));
        }
        Collections.sort(listeCoupsValeur);
        Collections.reverse(listeCoupsValeur);
        int valeurMax = listeCoupsValeur.get(0).valeur;
        float range = (float) (valeurMax * 0.9);
        int i = 1;
        while (i < listeCoupsValeur.size() && listeCoupsValeur.get(i).valeur > range) {
            i++;
        }
        Random rand = new Random();
        return listeCoupsValeur.get(rand.nextInt(i)).point;
    }
    
    /**
     * Creation d'une partie de l'arbre MiniMax
     * @param plateau le plateau de la partie
     * @param point les coordonnee du coup a jouer
     * @param profondeur la profondeur de l'arbre
     * @param couleur la couleur de la pierre
     * @param maximiser le joueur a faire gagner
     * @return la branche a parcourir
     */

    public int minimax(Plateau plateau, Point point, int profondeur, int couleur, boolean maximiser) {
        int meilleur;
        int valeur;
        int evalCourant;
        if (profondeur == 0) {
            if (maximiser) {
                return evaluationCoup(plateau, point, couleur);
            } else {
                return -evaluationCoup(plateau, point, couleur);
            }
        }
        if (maximiser) {
            evalCourant = evaluationCoup(plateau, point, couleur);
            if (evalCourant == Integer.MAX_VALUE) {
                return evalCourant;
            }
            meilleur = Integer.MIN_VALUE;
            for (Point p : coupsPertinents(plateau)) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), false);
                meilleur = Integer.max(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
            }
            return meilleur + evalCourant;
        } else {
            evalCourant = evaluationCoup(plateau, point, couleur);
            if (evalCourant == Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            meilleur = Integer.MAX_VALUE;
            for (Point p : coupsPertinents(plateau)) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), true);
                meilleur = Integer.min(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
            }
            return meilleur - evalCourant;
        }
    }
}

/**
 * Le poids d'un coup
 */
class ValeurCoup implements Comparable<ValeurCoup> {

    Point point;
    int valeur;

    /**
     * Creation du poids d'un coup
     * @param p un point de coordonnee (x,y)
     * @param v une valeur
     */
    public ValeurCoup(Point p, int v) {
        this.point = p;
        this.valeur = v;
    }

    @Override
    public int compareTo(ValeurCoup autre) {
        return this.valeur - autre.valeur;
    }
}
