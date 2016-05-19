/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import java.util.Stack;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;

/**
 * La partie
 * @author jacqurap
 */

public class Partie implements Serializable{

	public static final int JOUEUR_HUMAIN = 0;
	public static final int JOUEUR_IA_1 = 1;
	public static final int JOUEUR_IA_2 = 2;
	public static final int JOUEUR_IA_3 = 3;

	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private int nbCoups;
	private boolean partieFinie = false;
	private boolean j1Win = false;
	private boolean j2Win = false;
	private Stack<Plateau> annuler;
	private Stack<Plateau> refaire;
	private boolean ia1=false;
	
	/**
	 * Determine si le joueur 1 est un ordinateur
	 * @return ia1, true si l'ordinateur 1 est un ordinateur 
	 */
	
	public boolean isIa1() {
		return ia1;
	}
	
	/**
	 * Determine si le joueur 2 est un ordinateur
	 * @return ia2, true si l'ordinateur 2 est un ordinateur 
	 */
	
	public boolean isIa2() {
		return ia2;
	}

	private boolean ia2=false;

	/**
	 * Creation d'une partie
	 * @param classJ1 type du joueur 1
	 * @param nomJ1 nom du joueur 1
	 * @param classJ2 type du joueur 2
	 * @param nomJ2 nom du joueur 2
	 */
	
	public Partie(String classJ1, String nomJ1, String classJ2, String nomJ2) {

		this.plateau = new Plateau();
		this.annuler = new Stack<>();
		this.refaire = new Stack<>();
		/* creation des joueur en foction de leur classe sans condition a ralonge, et ça c'est la CLASS  !!!!!!  si ça c'est pas une blague trop badass ...*/
		try {
			this.joueur1 = (Joueur) Class.forName(classJ1).getConstructor(String.class).newInstance(nomJ1);
			this.joueur2 = (Joueur) Class.forName(classJ2).getConstructor(String.class).newInstance(nomJ2);
			if(joueur1 instanceof Ia){
				ia1=true;
			}
			if(joueur2 instanceof Ia){
				ia2=true;
			}
		} catch (Exception ex) {
			Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Action des IAs
	 */
	
	public void joueIa(){

		if(nbCoups%2==0 && ia1){
			action(((Ia) joueur1).solver(this));
		}
		else if(nbCoups%2==1 && ia2){
			action(((Ia) joueur2).solver(this));
		}

	}
	
	/**
	 * Action des joueurs au clic sur le point p
	 * @param p le point de coordonnee (x,y)
	 * @return true, quand l'action est faite, false sinon
	 */

	public boolean click(Point p) { //TODO

		if ((joueur1 instanceof Humain && nbCoups % 2 == 0) || joueur2 instanceof Humain && nbCoups % 2 == 1) {

			if (nbCoups == 0 && p.x == 7 && p.y == 7) {
				action(p);
				return true;
			} else if (nbCoups == 1 && (p.x == 6 && (p.y == 6 || p.y == 7 || p.y == 8)
					|| (p.x == 7 && (p.y == 6 || p.y == 8))
					|| (p.x == 8 && (p.y == 6 || p.y == 7 || p.y == 8)))) {
				action(p);
				return true;
			} else if (nbCoups > 1 && plateau.getCase(p.x, p.y) == plateau.CASEVIDE) {
				action(p);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Action sur le plateau de jeu
	 * @param p le point de coordonnee (x,y)
	 * @return true, quand l'action est faite
	 */

	public boolean action(Point p) { //TODO
		if (nbCoups % 2 == 0) {
			plateau.setCase(p.x, p.y, plateau.CASENOIRE);
		} else {
			plateau.setCase(p.x, p.y, plateau.CASEBLANCHE);
		}
		if(!refaire.isEmpty())
			refaire.clear();
		annuler.push(plateau);
		partieFini(p);
		incNbCoups();
		return true;
	}

	/**
	 * Action des coups de jeu
	 * @param p le point de coordonnee (x,y)
	 */
	
	public void partieFini(Point p) {
		int n = 1;
		int i = 1;
		int casee;
		if (nbCoups % 2 == 0)
			casee = plateau.CASENOIRE;
		else
			casee = plateau.CASEBLANCHE;
		//horizontale
		while (n < 5 && p.x + i < plateau.getDimX() && plateau.getCase(p.x + i, p.y) == casee) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && plateau.getCase(p.x - i, p.y) == casee) {
			n++;
			i++;
		}
		if (n == 5) {
			partieFinie = true;
			j1Win = true;
		}

		//verticale
		n = 1;
		i = 1;
		while (n < 5 && p.y + i < plateau.getDimY() && plateau.getCase(p.x, p.y + i) == casee) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.y - i >= 0 && plateau.getCase(p.x, p.y - i) == casee) {
			n++;
			i++;
		}
		if (n == 5) {
			partieFinie = true;
			j1Win = true;
		}

		//diagonale1
		n = 1;
		i = 1;
		while (n < 5 && p.x + i < plateau.getDimX() && p.y + i < plateau.getDimY() && plateau.getCase(p.x + i, p.y + i) == casee) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && p.y - i >= 0 && plateau.getCase(p.x - i, p.y - i) == casee) {
			n++;
			i++;
		}
		if (n == 5) {
			partieFinie = true;
			j2Win = true;
		}

		//diagonale2
		n = 1;
		i = 1;
		while (n < 5 && p.x + i < plateau.getDimX() && p.y - i >= 0 && plateau.getCase(p.x + i, p.y - i) == casee) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && p.y + i < plateau.getDimY() && plateau.getCase(p.x - i, p.y + i) == casee) {
			n++;
			i++;
		}
		if (n == 5) {
			partieFinie = true;
			j2Win = true;
		}

		System.out.println(partieFinie);
	}
	
	/**
	 * Liste des coups jouables sur le plateau en cours
	 * @return coupsJouables
	 */

	public ArrayList<Point> coupsJouables() {
		return coupsJouables(this.plateau);
	}
	
	/**
	 Liste des coups jouables sur un plateau
	 * @param p un plateau de jeu
	 * @return coupJouables
	 */

	public ArrayList<Point> coupsJouables(Plateau p) { //TODO
		ArrayList<Point> coupJouables = new ArrayList<Point>();

		if (nbCoups == 0) {
			coupJouables.add(new Point(7, 7));
		} else if (nbCoups == 1) {
			coupJouables.add(new Point(6, 7));
			coupJouables.add(new Point(8, 7));

			coupJouables.add(new Point(7, 6));
			coupJouables.add(new Point(7, 8));

			coupJouables.add(new Point(6, 6));
			coupJouables.add(new Point(8, 8));

			coupJouables.add(new Point(8, 6));
			coupJouables.add(new Point(6, 8));
		} else {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (plateau.getCase(i, j) == plateau.CASEVIDE) {
						coupJouables.add(new Point(i, j));
					}
				}
			}
		}
		return coupJouables;
	}

	/**
	 * Recupere le joueur 1
	 * @return joueur1
	 */
	
	public Joueur getJoueur1() {
		return joueur1;
	}

	/**
	 * Recupere le joueur 2
	 * @return joueur2
	 */
	
	public Joueur getJoueur2() {
		return joueur2;
	}

	/**
	 * Recupere le plateau de jeu
	 * @return plateau
	 */
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * Recupere le nombre de coups joues
	 * @return nbCoups
	 */

	public int getNbCoups() {
		return nbCoups;
	}

	/**
	 * Incremente le nombre de coups
	 */
	
	public void incNbCoups() {
		nbCoups++;
	}
	
	/**
	 * Decremente le nombre de coups
	 */

	public void decNbCoups() {
		nbCoups--;
	}

	/**
	 * Determine si la partie est terminee
	 * @return partieFinie, retourne true si la partie est finie, false sinon
	 */
	public boolean isPartieFinie() {
		return partieFinie;
	}

	/**
	 * Determine si le joueur 1 a gagne la partie
	 * @return j1Win, retourne true si joueur 1 gagne, false sinon
	 */
	public boolean isJ1Win() {
		return j1Win;
	}

	/**
	 * Determine si le joueur 2 a gagne la partie
	 * @return j2Win, retourne true si joueur 2 gagne, false sinon
	 */
	
	public boolean isJ2Win() {
		return j2Win;
	}

	/**
	 * Recupere la pile annuler
	 * @return annuler
	 */
	
	public Stack<Plateau> getAnnuler(){
		return annuler;
	}

	/**
	 * Recupere la pile refaire
	 * @return annuler
	 */
	
	public Stack<Plateau> getRefaire(){
		return refaire;
	}
}
