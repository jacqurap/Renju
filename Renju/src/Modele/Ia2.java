/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Partie;
import static Modele.Ia.evaluationCoup;
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
    }

    @Override
    public Point solver(Partie partie) {
        int meilleur;
        int couleur;
        Plateau plateau = partie.getPlateau().clone();
        ArrayList<ValeurCoup> listeCoupsValeur = new ArrayList<>();
        if (partie.getNbCoups() % 2 == 0) {
            couleur = Plateau.CASENOIRE;
        } else {
            couleur = Plateau.CASEBLANCHE;
        }
        for (Point p : coupsPertinents(plateau, partie.getNbCoups())) {
            plateau.setCase(p.x, p.y, couleur);
            listeCoupsValeur.add(new ValeurCoup(p, minimax(plateau, p, 3, plateau.getAutreCouleur(couleur), false, partie.getNbCoups(), Integer.MIN_VALUE, Integer.MAX_VALUE)));
            plateau.setCase(p.x, p.y, Plateau.CASEVIDE);
        }
        Collections.sort(listeCoupsValeur);
        Collections.reverse(listeCoupsValeur);
        for (int i = 0; i < listeCoupsValeur.size(); i++) {
            System.out.println(listeCoupsValeur.get(i).point + "  " + listeCoupsValeur.get(i).valeur);
        }

        int valeurMax = listeCoupsValeur.get(0).valeur;
        //System.out.println(valeurMax);
        int range = valeurMax;
        //System.out.println(range);
        int i = 1;
        while (i < listeCoupsValeur.size() && listeCoupsValeur.get(i).valeur >= range) {
            i++;
        }
		//for (ValeurCoup vc: listeCoupsValeur){
        //    System.out.println(vc);
        //}
        //System.out.println(i);
        Random rand = new Random();
        if (i > 1) {
            return listeCoupsValeur.get(rand.nextInt(i - 1)).point;
        }
        return listeCoupsValeur.get(0).point;
    }

    /**
     * Creation d'une partie de l'arbre MiniMax
     *
     * @param plateau le plateau de la partie
     * @param point les coordonnee du coup a jouer
     * @param profondeur la profondeur de l'arbre
     * @param couleur la couleur du joueur a cette profondeur
     * @param maximiser faut-il maximiser ou minimiser a cette profondeur
     * @return l'evaluation minimax du coups rentre en parametre "point"
     */
    public int minimax(Plateau plateau, Point point, int profondeur, int couleur, boolean maximiser, int nbCoups, int alpha, int beta) {
        int meilleur;
        int valeur;
        int fin = evaluationCoup(plateau, point, plateau.getAutreCouleur(couleur));
        if (fin == Integer.MAX_VALUE) { // le joueur gagne
            if (!maximiser) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
        if (fin == Integer.MIN_VALUE) { // noir joue un tabou
            if (!maximiser) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (profondeur == 0) {
            return fin;
        }
        if (maximiser) {
            meilleur = Integer.MIN_VALUE;
            for (Point p : coupsPertinents(plateau, nbCoups)) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), false, nbCoups + 1, alpha, beta);
                meilleur = Math.max(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
                if (beta <= meilleur) {
                    return meilleur;
                }
                alpha = Math.min(alpha, valeur);
            }
            return meilleur;
        } else { //minimiser
            meilleur = Integer.MAX_VALUE;
            for (Point p : coupsPertinents(plateau, nbCoups)) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), true, nbCoups + 1, alpha, beta);
                meilleur = Math.min(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
                if (alpha >= meilleur) {
                    return meilleur;
                }
                beta = Math.max(beta, valeur);
            }
            return meilleur;
        }
    }
}
