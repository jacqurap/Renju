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
 *
 * @author jacqurap
 */
public class Ia3 extends Ia {
    
    

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
            listeCoupsValeur.add(new ValeurCoup(p, minimax(partie.getPlateau(), p, 3, couleur, false)));
        }
        for (ValeurCoup vc: listeCoupsValeur){
            System.out.println(vc);
        }
        Collections.sort(listeCoupsValeur);
        Collections.reverse(listeCoupsValeur);
        int valeurMax = listeCoupsValeur.get(0).valeur;
        int range = valeurMax;
        System.out.println(range);
        int i = 1;
        while (i < listeCoupsValeur.size() && listeCoupsValeur.get(i).valeur >= range) {
            i++;
        }
        for (ValeurCoup vc: listeCoupsValeur){
            System.out.println(vc);
        }
        System.out.println(i);
        Random rand = new Random();
        return listeCoupsValeur.get(rand.nextInt(i)).point;
    }

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
            if (evalCourant == LETHAL) {
                return evalCourant;
            }
            meilleur = - LETHAL;
            for (Point p : coupsPertinents(plateau)) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), false);
                meilleur = Integer.max(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
                if(meilleur == LETHAL){
                    return meilleur;
                }
            }
            return meilleur + evalCourant;
        } else {
            evalCourant = evaluationCoup(plateau, point, couleur);
            if (evalCourant == LETHAL) {
                return - LETHAL;
            }
            meilleur =  - LETHAL;
            for (Point p : coupsPertinents(plateau)) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), true);
                meilleur = Integer.max(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
                if(meilleur == LETHAL){
                    return meilleur;
                }
            }
            return meilleur - evalCourant;
        }
    }
}

class ValeurCoup implements Comparable<ValeurCoup> {

    Point point;
    int valeur;

    public ValeurCoup(Point p, int v) {
        this.point = p;
        this.valeur = v;
    }

    @Override
    public int compareTo(ValeurCoup autre) {
        return this.valeur - autre.valeur;
    }
    
    public String toString(){
        return ("p = " + point + " v = " + valeur);
    }
}
