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
public class Ia4 extends Ia {



	/**
	 * Creation de l'IA (Niveau 3)
	 * @param nom le nom de l'IA
	 */

	public Ia4(String nom) {
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
		for (Point p : coupsPertinents(partie.getPlateau(), partie.getNbCoups())) {
			Plateau plateau = partie.getPlateau();
			plateau.setCase(p.x, p.y, couleur);
			listeCoupsValeur.add(new ValeurCoup(p, minimax(partie.getPlateau(), p, 4, partie.getPlateau().getAutreCouleur(couleur), false,partie.getNbCoups(),Integer.MIN_VALUE)));
			plateau.setCase(p.x, p.y, Plateau.CASEVIDE);
		}
		Collections.sort(listeCoupsValeur);
		Collections.reverse(listeCoupsValeur);
		//for(int i = 0 ; i < listeCoupsValeur.size(); i++){
		//System.out.println(listeCoupsValeur.get(i).point + "  " + listeCoupsValeur.get(i).valeur);
		//}

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
		if(i>1)
			return listeCoupsValeur.get(rand.nextInt(i-1)).point;
		return listeCoupsValeur.get(0).point;
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

	public int minimax(Plateau plateau, Point point, int profondeur, int couleur, boolean maximiser,int nbCoups, int alphaBeta) {
		int meilleur;
		int valeur;
		int fin = evaluationCoup(plateau,point, plateau.getAutreCouleur(couleur));
		if( fin == 10000){
			//System.out.println("zetsfdjkdsjbfkjdsb"  + point);
			//for(int a = 0; a < plateau.getDimX(); a++){
			//for (int b = 0; b < plateau.getDimY(); b++){
			//System.out.print(plateau.getCase(a, b) + "  ");
			//}
			//System.out.println();
			//}
			if(!maximiser)
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;
		}
		if (profondeur == 0 ) {
			return evaluationCoup(plateau,point, plateau.getAutreCouleur(couleur));
		}
		if (maximiser) {
			meilleur = Integer.MIN_VALUE;
			for (Point p : coupsPertinents(plateau,nbCoups)) {
				plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
				valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), false, nbCoups+1, meilleur);
				meilleur = Math.max(meilleur, valeur);
				plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
				if (alphaBeta <= meilleur) break;
			}
			return meilleur;
		} else { //minimiser
			meilleur = Integer.MAX_VALUE;
			for (Point p : coupsPertinents(plateau,nbCoups)) {
				plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
				valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), true, nbCoups+1, meilleur);
				meilleur = Math.min(meilleur, valeur);
				plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
				if (alphaBeta >= meilleur) break;
			}
			return meilleur;
		}
	}
}

