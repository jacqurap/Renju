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
import Vue.*;
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

	private boolean ia2=false;
	private boolean touria= false;
	private boolean tabou = true;

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
		/* creation des joueur en foction de leur classe sans condition a ralonge, et ca c'est la CLASS  !!!!!!  si ca c'est pas une blague trop badass ...*/
		try {
			this.joueur1 = (Joueur) Class.forName(classJ1).getConstructor(String.class).newInstance(nomJ1);
			this.joueur2 = (Joueur) Class.forName(classJ2).getConstructor(String.class).newInstance(nomJ2);
			if(joueur1 instanceof Ia){
				ia1=true;
				touria = true;
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
		Plateau oldp = plateau.clone();
		if (nbCoups % 2 == 0) {
			plateau.setCase(p.x, p.y, plateau.CASENOIRE);
			if(ia2)
				touria=true;
		} else {
			plateau.setCase(p.x, p.y, plateau.CASEBLANCHE);
			if(ia1)
				touria=true;
		}
		//System.out.println(Ia.evaluationPlateau(plateau));
		annuler.push(oldp);
		InterfaceJeu.getITEMANNU().setEnabled(true);
		InterfaceJeu.getITEMREFA().setEnabled(false);
		InterfaceJeu.getButAnnuler().setEnabled(true);
		InterfaceJeu.getButRefaire().setEnabled(false);
		refaire.clear();
		partieFini(p);
		incNbCoups();
		return true;
	}

	/**
	 * Action des coups de jeu
	 * @param p le point de coordonnee (x,y)
	 */

	public void partieFini(Point p) {
		int couleur;
		if (nbCoups % 2 == 0){
			couleur = plateau.CASENOIRE;
			if(tabou)
				partieFiniTabou(this.plateau, p, couleur);
		}
		else
			couleur = plateau.CASEBLANCHE;
		partieFiniSansTabou(p, this.plateau, couleur);

	}
	public int partieFini(Point p, Plateau plateau, int couleur,boolean tabou) {
		if(tabou && couleur == Plateau.CASENOIRE)
			return partieFiniTabou(plateau, p, couleur);
		else 
			return partieFiniSansTabou(p, plateau, couleur);
	}

	public int partieFiniSansTabou(Point p, Plateau plateau, int couleur) {
		int n = 1;
		int i = 1;

		//horizontale
		while (n < 5 && p.x + i < plateau.getDimX() && plateau.getCase(p.x + i, p.y) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && plateau.getCase(p.x - i, p.y) == couleur) {
			n++;
			i++;
		}
		if (n == 5) {
			return couleur;
		}

		//verticale
		n = 1;
		i = 1;
		while (n < 5 && p.y + i < plateau.getDimY() && plateau.getCase(p.x, p.y + i) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.y - i >= 0 && plateau.getCase(p.x, p.y - i) == couleur) {
			n++;
			i++;
		}
		if (n == 5) {
			return couleur;
		}

		//diagonale1
		n = 1;
		i = 1;
		while (n < 5 && p.x + i < plateau.getDimX() && p.y + i < plateau.getDimY() && plateau.getCase(p.x + i, p.y + i) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && p.y - i >= 0 && plateau.getCase(p.x - i, p.y - i) == couleur) {
			n++;
			i++;
		}
		if (n == 5) {
			return couleur;
		}

		//diagonale2
		n = 1;
		i = 1;
		while (n < 5 && p.x + i < plateau.getDimX() && p.y - i >= 0 && plateau.getCase(p.x + i, p.y - i) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && p.y + i < plateau.getDimY() && plateau.getCase(p.x - i, p.y + i) == couleur) {
			n++;
			i++;
		}
		if (n == 5) {
			return couleur;
		}
		return 0;
	}
	/*
	public int partieFiniTabou(Point p, Plateau plateau, int couleur) {
		int n = 1;
		int i = 1;

		//horizontale
		while ( p.x + i < plateau.getDimX() && plateau.getCase(p.x + i, p.y) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (p.x - i >= 0 && plateau.getCase(p.x - i, p.y) == couleur) {
			n++;
			i++;
		}
		if(n>5 && couleur == Plateau.CASENOIRE)
			return plateau.getAutreCouleur(couleur);
		if (n >= 5) {
			return couleur;
		}

		//verticale
		n = 1;
		i = 1;
		while (p.y + i < plateau.getDimY() && plateau.getCase(p.x, p.y + i) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (p.y - i >= 0 && plateau.getCase(p.x, p.y - i) == couleur) {
			n++;
			i++;
		}

		if (n == 5) {
			return couleur;
		}

		//diagonale1
		n = 1;
		i = 1;
		while (n < 5 && p.x + i < plateau.getDimX() && p.y + i < plateau.getDimY() && plateau.getCase(p.x + i, p.y + i) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && p.y - i >= 0 && plateau.getCase(p.x - i, p.y - i) == couleur) {
			n++;
			i++;
		}
		if (n == 5) {
			return couleur;
		}

		//diagonale2
		n = 1;
		i = 1;
		while (n < 5 && p.x + i < plateau.getDimX() && p.y - i >= 0 && plateau.getCase(p.x + i, p.y - i) == couleur) {
			n++;
			i++;
		}
		i = 1;
		while (n < 5 && p.x - i >= 0 && p.y + i < plateau.getDimY() && plateau.getCase(p.x - i, p.y + i) == couleur) {
			n++;
			i++;
		}
		if (n == 5) {
			return couleur;
		}
		return 0;
	}
	 */
	public int partieFiniTabou(Plateau plateau, Point point, int couleur) {
		int i;
		// ces boolean permettent de tester si les pions du joueur sont juxtaposes (pour tester la victoire)
		boolean isJuxtHorizontalGauche = true, isJuxtHorizontalDroite = true, isJuxtVerticalHaut = true, isJuxtVerticalBas = true, isJuxtDiagonalDroiteHaut = true, isJuxtDiagonalGaucheBas = true, isJuxtDiagonalDroiteBas = true, isJuxtDiagonalGaucheHaut = true;
		// ces boolean permettent de s'assurer de l'absence de pion adverse entre mes pions
		boolean isHorizontalGauche = true, isHorizontalDroite = true, isVerticalHaut = true, isVerticalBas = true, isDiagonalDroiteHaut = true, isDiagonalGaucheBas = true, isDiagonalDroiteBas = true, isDiagonalGaucheHaut = true;
		// ces int permettent de compter mes pions juxtaposes (la valeur 5 signifie donc la victoire)
		int juxtHorizontal = 0, juxtVertical = 0, juxtDiagonalMontante = 0, juxtDiagonalDescendante = 0;
		// ces int permettent de compter mes pions sur la ligne/colonne/diagonale, plus cette valeur est elevee, mieux c'est pour le joueur courant;
		int horizontal = 0, vertical = 0, diagonalMontante = 0, diagonalDescendante = 0;
		// ces boolean servent a savoir si une ligne est bloque pour detecter les tabous
		boolean isHorizontalGaucheBloque = false, isHorizontalDroiteBloque = false, isVerticalHautBloque = false, isVerticalBasBloque = false, isDiagonalDroiteHautBloque = false, isDiagonalGaucheBasBloque = false, isDiagonalDroiteBasBloque = false, isDiagonalGaucheHautBloque = false;

		int casee;
		for (i = 1; i <= 5; i++) {
			if (point.getX() + i < plateau.getDimX()) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY());
				if (isHorizontalDroite) {
					if (casee == couleur) {
						if (isJuxtHorizontalDroite) {
							juxtHorizontal++;
						}
						horizontal++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtHorizontalDroite = false;
					} else {
						isHorizontalDroite = false;
						isHorizontalGaucheBloque = true;
					}
				}
			}
			if (point.getX() - i >= 0) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY());
				if (isHorizontalGauche) {
					if (casee == couleur) {
						if (isJuxtHorizontalGauche) {
							juxtHorizontal++;
						}
						horizontal++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtHorizontalGauche = false;
					} else {
						isHorizontalGauche = false;
						isHorizontalGaucheBloque = true;
					}
				}
			}
			if (point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX(), (int) point.getY() + i);
				if (isVerticalHaut) {
					if (casee == couleur) {
						if (isJuxtVerticalHaut) {
							juxtVertical++;
						}
						vertical++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtVerticalHaut = false;
					} else {
						isVerticalHaut = false;
						isVerticalHautBloque = true;
					}
				}
			}
			if (point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX(), (int) point.getY() - i);
				if (isVerticalBas) {
					if (casee == couleur) {
						if (isJuxtVerticalBas) {
							juxtVertical++;
						}
						vertical++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtVerticalBas = false;
					} else {
						isVerticalBas = false;
						isVerticalBasBloque = true;
					}
				}
			}
			if (point.getX() + i < plateau.getDimY() && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY() + i);
				if (isDiagonalDroiteHaut) {
					if (casee == couleur) {
						if (isJuxtDiagonalDroiteHaut) {
							juxtDiagonalMontante++;
						}
						diagonalMontante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalDroiteHaut = false;
					} else {
						isDiagonalDroiteHaut = false;
						isDiagonalDroiteHautBloque = true;
					}
				}
			}
			if (point.getX() + i < plateau.getDimY() && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY() - i);
				if (isDiagonalDroiteBas) {
					if (casee == couleur) {
						if (isJuxtDiagonalDroiteBas) {
							juxtDiagonalDescendante++;
						}
						diagonalDescendante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalDroiteBas = false;
					} else {
						isDiagonalDroiteBas = false;
						isDiagonalDroiteBasBloque = true;
					}
				}
			}
			if (point.getX() - i >= 0 && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY() + i);
				if (isDiagonalGaucheHaut) {
					if (casee == couleur) {
						if (isJuxtDiagonalGaucheHaut) {
							juxtDiagonalDescendante++;
						}
						diagonalDescendante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalGaucheHaut = false;
					} else {
						isDiagonalGaucheHaut = false;
						isDiagonalGaucheHautBloque = true;
					}
				}
			}
			if (point.getX() - i >= 0 && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY() - i);
				if (isDiagonalGaucheBas) {
					if (casee == couleur) {
						if (isJuxtDiagonalGaucheBas) {
							juxtDiagonalMontante++;
						}
						diagonalMontante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalGaucheBas = false;
					} else {
						isDiagonalGaucheBas = false;
						isDiagonalGaucheBasBloque = true;
					}
				}
			}
		}

		int nb33NonBloque = 0;
		if(juxtVertical == 2 && !isVerticalBasBloque && !isVerticalHautBloque){
			nb33NonBloque++;
		}
		if(juxtHorizontal == 2 && !isHorizontalGaucheBloque && !isHorizontalDroiteBloque){
			nb33NonBloque++;
		}
		if(juxtDiagonalDescendante == 2 && !isDiagonalGaucheHautBloque && !isDiagonalDroiteBasBloque){
			nb33NonBloque++;
		}
		if(juxtDiagonalMontante == 2 && !isDiagonalGaucheBasBloque && !isDiagonalDroiteHautBloque){
			nb33NonBloque++;
		}

		System.out.println("nb33NonBloque  :  "+ nb33NonBloque);
		if(nb33NonBloque == 1 && juxtVertical == 2 && !isVerticalBasBloque && !isVerticalHautBloque){
			for(i = 1; i<=2;i++){
				if(point.y+i < plateau.getDimY() && plateau.getCase(point.x, point.y+i)== Plateau.CASENOIRE && nonbloq33(new Point(point.x,point.y+i),plateau,couleur,0)){
					nb33NonBloque++;
					break;
				}
				if(point.y-i > 0 && plateau.getCase(point.x, point.y-i)== Plateau.CASENOIRE && nonbloq33(new Point(point.x,point.y-i),plateau,couleur,0)){
					nb33NonBloque++;
					break;
				}
			}
		}
		if(nb33NonBloque == 1 && juxtHorizontal == 2 && !isHorizontalGaucheBloque && !isHorizontalDroiteBloque){
			for(i = 1; i<=2;i++){
				if(point.x +i < plateau.getDimX() && plateau.getCase(point.x+i, point.y)== Plateau.CASENOIRE && nonbloq33(new Point(point.x+i,point.y),plateau,couleur,1)){
					nb33NonBloque++;
					break;
				}
				if(point.x -i > 0 && plateau.getCase(point.x-i, point.y)== Plateau.CASENOIRE && nonbloq33(new Point(point.x-i,point.y),plateau,couleur,1)){
					nb33NonBloque++;
					break;
				}
			}
		}
		if(nb33NonBloque == 1 && juxtDiagonalDescendante == 2 && !isDiagonalGaucheHautBloque && !isDiagonalDroiteBasBloque){
			for(i = 1; i<=2;i++){
				if(point.x < plateau.getDimX() && point.y > 0 && plateau.getCase(point.x+i, point.y-i)== Plateau.CASENOIRE && nonbloq33(new Point(point.x+i,point.y-i),plateau,couleur,3)){
					nb33NonBloque++;
					break;
				}
				if(point.x-i > 0 && point.y +i < plateau.getDimY() && plateau.getCase(point.x-i, point.y+i)== Plateau.CASENOIRE && nonbloq33(new Point(point.x-i,point.y+i),plateau,couleur,3)){
					nb33NonBloque++;
					break;
				}
			}
		}
		if(nb33NonBloque == 1 && juxtDiagonalMontante == 2 && !isDiagonalGaucheBasBloque && !isDiagonalDroiteHautBloque){
			for(i = 1; i<=2;i++){
				if(point.x+i < plateau.getDimX() && point.y+i < plateau.getDimY() && plateau.getCase(point.x+i, point.y+i)== Plateau.CASENOIRE && nonbloq33(new Point(point.x+i,point.y+i),plateau,couleur,2)){
					nb33NonBloque++;
					break;
				}
				if(point.x-i < 0 && point.y-1 < 0 && plateau.getCase(point.x-i, point.y-i)== Plateau.CASENOIRE && nonbloq33(new Point(point.x-i,point.y-i),plateau,couleur,2)){
					nb33NonBloque++;
					break;
				}
			}
		}


		int nb44NonBloque = 0;
		if(juxtVertical == 3 && (!isVerticalBasBloque || !isVerticalHautBloque)){
			nb44NonBloque++;
		}
		if(juxtHorizontal == 3 && (!isHorizontalGaucheBloque || !isHorizontalDroiteBloque)){
			nb44NonBloque++;
		}
		if(juxtDiagonalDescendante == 3 && (!isDiagonalGaucheHautBloque || !isDiagonalDroiteBasBloque)){
			nb44NonBloque++;
		}
		if(juxtDiagonalMontante == 3 && (!isDiagonalGaucheBasBloque || !isDiagonalDroiteHautBloque)){
			nb44NonBloque++;
		}
		
		
		if(nb44NonBloque == 1 && juxtVertical == 3 && (!isVerticalBasBloque || !isVerticalHautBloque)){
			for(i = 1 ; i <= 3; i++){
				if(point.y+i < plateau.getDimY() && plateau.getCase(point.x, point.y+i)== Plateau.CASENOIRE && nonbloq44(new Point(point.x,point.y+i),plateau,couleur,0)){
					nb44NonBloque++;
					break;
				}
				if(point.y > 0 && plateau.getCase(point.x, point.y-i)== Plateau.CASENOIRE && nonbloq44(new Point(point.x,point.y-i),plateau,couleur,0)){
					nb44NonBloque++;
					break;
				}
			}
		}
		if(nb44NonBloque == 1 && juxtHorizontal == 3 && (!isHorizontalGaucheBloque || !isHorizontalDroiteBloque)){
			for(i = 1; i<=3;i++){
				if(point.x +i < plateau.getDimX() && plateau.getCase(point.x+i, point.y)== Plateau.CASENOIRE && nonbloq44(new Point(point.x+i,point.y),plateau,couleur,1)){
					nb44NonBloque++;
					break;
				}
				if(point.x -i > 0 && plateau.getCase(point.x-i, point.y)== Plateau.CASENOIRE && nonbloq44(new Point(point.x-i,point.y),plateau,couleur,1)){
					nb44NonBloque++;
					break;
				}
			}
		}
		if(nb44NonBloque == 1 && juxtDiagonalDescendante == 3 && (!isDiagonalGaucheHautBloque || !isDiagonalDroiteBasBloque)){
			for(i = 1; i<=3;i++){
				if(point.x < plateau.getDimX() && point.y > 0 && plateau.getCase(point.x+i, point.y-i)== Plateau.CASENOIRE && nonbloq44(new Point(point.x+i,point.y-i),plateau,couleur,3)){
					nb44NonBloque++;
					break;
				}
				if(point.x-i > 0 && point.y +i < plateau.getDimY() && plateau.getCase(point.x-i, point.y+i)== Plateau.CASENOIRE && nonbloq44(new Point(point.x-i,point.y+i),plateau,couleur,3)){
					nb44NonBloque++;
					break;
				}
			}
		}
		if(nb44NonBloque == 1 && juxtDiagonalMontante == 3 && (!isDiagonalGaucheBasBloque || !isDiagonalDroiteHautBloque)){
			for(i = 1; i<=3;i++){
				if(point.x+i < plateau.getDimX() && point.y+i < plateau.getDimY() && plateau.getCase(point.x+i, point.y+i)== Plateau.CASENOIRE && nonbloq44(new Point(point.x+i,point.y+i),plateau,couleur,2)){
					nb44NonBloque++;
					break;
				}
				if(point.x-i < 0 && point.y-1 < 0 && plateau.getCase(point.x-i, point.y-i)== Plateau.CASENOIRE && nonbloq44(new Point(point.x-i,point.y-i),plateau,couleur,2)){
					nb44NonBloque++;
					break;
				}
			}
		}
		if((couleur == Plateau.CASENOIRE) 
				&& (juxtHorizontal > 4              /// ligne de plus de 5 pions
						|| juxtVertical > 4 
						|| juxtDiagonalDescendante > 4 
						|| juxtDiagonalMontante > 4
						|| nb33NonBloque >= 2          /// 3 * 3 non bloque
						|| nb44NonBloque >= 2           /// 4 * 4 non double bloque

						)){
			partieFinie = true;
			j2Win = true;
			return 2;
		}
		if (juxtHorizontal >= 4 || juxtVertical >= 4 || juxtDiagonalDescendante >= 4 || juxtDiagonalMontante >= 4) {
			partieFinie = true;
			if(couleur == Plateau.CASENOIRE){
				j1Win = true;
				return 1;
			}
			else{
				j2Win = true;
				return 2;
			}
		}
		return 0;
	}
	/**
	 * Liste des coups jouables sur le plateau en cours
	 * @return coupsJouables
	 * vert =0, hor =1, diagmont = 2, daigdesc = 3
	 */

	public boolean nonbloq33(Point point, Plateau plateau, int couleur, int dir){
		int i;
		// ces boolean permettent de tester si les pions du joueur sont juxtaposes (pour tester la victoire)
		boolean isJuxtHorizontalGauche = true, isJuxtHorizontalDroite = true, isJuxtVerticalHaut = true, isJuxtVerticalBas = true, isJuxtDiagonalDroiteHaut = true, isJuxtDiagonalGaucheBas = true, isJuxtDiagonalDroiteBas = true, isJuxtDiagonalGaucheHaut = true;
		// ces boolean permettent de s'assurer de l'absence de pion adverse entre mes pions
		boolean isHorizontalGauche = true, isHorizontalDroite = true, isVerticalHaut = true, isVerticalBas = true, isDiagonalDroiteHaut = true, isDiagonalGaucheBas = true, isDiagonalDroiteBas = true, isDiagonalGaucheHaut = true;
		// ces int permettent de compter mes pions juxtaposes (la valeur 5 signifie donc la victoire)
		int juxtHorizontal = 0, juxtVertical = 0, juxtDiagonalMontante = 0, juxtDiagonalDescendante = 0;
		// ces int permettent de compter mes pions sur la ligne/colonne/diagonale, plus cette valeur est elevee, mieux c'est pour le joueur courant;
		int horizontal = 0, vertical = 0, diagonalMontante = 0, diagonalDescendante = 0;
		// ces boolean servent a savoir si une ligne est bloque pour detecter les tabous
		boolean isHorizontalGaucheBloque = false, isHorizontalDroiteBloque = false, isVerticalHautBloque = false, isVerticalBasBloque = false, isDiagonalDroiteHautBloque = false, isDiagonalGaucheBasBloque = false, isDiagonalDroiteBasBloque = false, isDiagonalGaucheHautBloque = false;
		System.out.println(point);
		int casee;
		for (i = 1; i <= 5; i++) {
			if (dir != 1 && point.getX() + i < plateau.getDimX()) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY());
				if (isHorizontalDroite) {
					if (casee == couleur) {
						if (isJuxtHorizontalDroite) {
							juxtHorizontal++;
						}
						horizontal++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtHorizontalDroite = false;
					} else {
						isHorizontalDroite = false;
						isHorizontalGaucheBloque = true;
					}
				}
			}
			if (dir != 1 && point.getX() - i >= 0) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY());
				if (isHorizontalGauche) {
					if (casee == couleur) {
						if (isJuxtHorizontalGauche) {
							juxtHorizontal++;
						}
						horizontal++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtHorizontalGauche = false;
					} else {
						isHorizontalGauche = false;
						isHorizontalGaucheBloque = true;
					}
				}
			}
			if (dir != 0 && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX(), (int) point.getY() + i);
				if (isVerticalHaut) {
					if (casee == couleur) {
						if (isJuxtVerticalHaut) {
							juxtVertical++;
						}
						vertical++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtVerticalHaut = false;
					} else {
						isVerticalHaut = false;
						isVerticalHautBloque = true;
					}
				}
			}
			if (dir != 0 && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX(), (int) point.getY() - i);
				if (isVerticalBas) {
					if (casee == couleur) {
						if (isJuxtVerticalBas) {
							juxtVertical++;
						}
						vertical++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtVerticalBas = false;
					} else {
						isVerticalBas = false;
						isVerticalBasBloque = true;
					}
				}
			}
			if (dir != 2 && point.getX() + i < plateau.getDimY() && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY() + i);
				if (isDiagonalDroiteHaut) {
					if (casee == couleur) {
						if (isJuxtDiagonalDroiteHaut) {
							juxtDiagonalMontante++;
						}
						diagonalMontante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalDroiteHaut = false;
					} else {
						isDiagonalDroiteHaut = false;
						isDiagonalDroiteHautBloque = true;
					}
				}
			}
			if (dir != 2 && point.getX() + i < plateau.getDimY() && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY() - i);
				if (isDiagonalDroiteBas) {
					if (casee == couleur) {
						if (isJuxtDiagonalDroiteBas) {
							juxtDiagonalDescendante++;
						}
						diagonalDescendante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalDroiteBas = false;
					} else {
						isDiagonalDroiteBas = false;
						isDiagonalDroiteBasBloque = true;
					}
				}
			}
			if (dir != 3 && point.getX() - i >= 0 && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY() + i);
				if (isDiagonalGaucheHaut) {
					if (casee == couleur) {
						if (isJuxtDiagonalGaucheHaut) {
							juxtDiagonalDescendante++;
						}
						diagonalDescendante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalGaucheHaut = false;
					} else {
						isDiagonalGaucheHaut = false;
						isDiagonalGaucheHautBloque = true;
					}
				}
			}
			if (dir != 3 && point.getX() - i >= 0 && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY() - i);
				if (isDiagonalGaucheBas) {
					if (casee == couleur) {
						if (isJuxtDiagonalGaucheBas) {
							juxtDiagonalMontante++;
						}
						diagonalMontante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalGaucheBas = false;
					} else {
						isDiagonalGaucheBas = false;
						isDiagonalGaucheBasBloque = true;
					}
				}
			}
		}
		if(juxtVertical == 2 && !isVerticalBasBloque && !isVerticalHautBloque){
			return true;
		}
		if(juxtHorizontal == 2 && !isHorizontalGaucheBloque && !isHorizontalDroiteBloque){
			return true;
		}
		if(juxtDiagonalDescendante == 2 && !isDiagonalGaucheHautBloque && !isDiagonalDroiteBasBloque){
			return true;
		}
		if(juxtDiagonalMontante == 2 && !isDiagonalGaucheBasBloque && !isDiagonalDroiteHautBloque){
			return true;
		}
		return false;
	}

	public boolean nonbloq44(Point point, Plateau plateau, int couleur, int dir){
		int i;
		// ces boolean permettent de tester si les pions du joueur sont juxtaposes (pour tester la victoire)
		boolean isJuxtHorizontalGauche = true, isJuxtHorizontalDroite = true, isJuxtVerticalHaut = true, isJuxtVerticalBas = true, isJuxtDiagonalDroiteHaut = true, isJuxtDiagonalGaucheBas = true, isJuxtDiagonalDroiteBas = true, isJuxtDiagonalGaucheHaut = true;
		// ces boolean permettent de s'assurer de l'absence de pion adverse entre mes pions
		boolean isHorizontalGauche = true, isHorizontalDroite = true, isVerticalHaut = true, isVerticalBas = true, isDiagonalDroiteHaut = true, isDiagonalGaucheBas = true, isDiagonalDroiteBas = true, isDiagonalGaucheHaut = true;
		// ces int permettent de compter mes pions juxtaposes (la valeur 5 signifie donc la victoire)
		int juxtHorizontal = 0, juxtVertical = 0, juxtDiagonalMontante = 0, juxtDiagonalDescendante = 0;
		// ces int permettent de compter mes pions sur la ligne/colonne/diagonale, plus cette valeur est elevee, mieux c'est pour le joueur courant;
		int horizontal = 0, vertical = 0, diagonalMontante = 0, diagonalDescendante = 0;
		// ces boolean servent a savoir si une ligne est bloque pour detecter les tabous
		boolean isHorizontalGaucheBloque = false, isHorizontalDroiteBloque = false, isVerticalHautBloque = false, isVerticalBasBloque = false, isDiagonalDroiteHautBloque = false, isDiagonalGaucheBasBloque = false, isDiagonalDroiteBasBloque = false, isDiagonalGaucheHautBloque = false;
		System.out.println(point);
		int casee;
		for (i = 1; i <= 5; i++) {
			if (dir != 1 && point.getX() + i < plateau.getDimX()) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY());
				if (isHorizontalDroite) {
					if (casee == couleur) {
						if (isJuxtHorizontalDroite) {
							juxtHorizontal++;
						}
						horizontal++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtHorizontalDroite = false;
					} else {
						isHorizontalDroite = false;
						isHorizontalGaucheBloque = true;
					}
				}
			}
			if (dir != 1 && point.getX() - i >= 0) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY());
				if (isHorizontalGauche) {
					if (casee == couleur) {
						if (isJuxtHorizontalGauche) {
							juxtHorizontal++;
						}
						horizontal++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtHorizontalGauche = false;
					} else {
						isHorizontalGauche = false;
						isHorizontalGaucheBloque = true;
					}
				}
			}
			if (dir != 0 && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX(), (int) point.getY() + i);
				if (isVerticalHaut) {
					if (casee == couleur) {
						if (isJuxtVerticalHaut) {
							juxtVertical++;
						}
						vertical++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtVerticalHaut = false;
					} else {
						isVerticalHaut = false;
						isVerticalHautBloque = true;
					}
				}
			}
			if (dir != 0 && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX(), (int) point.getY() - i);
				if (isVerticalBas) {
					if (casee == couleur) {
						if (isJuxtVerticalBas) {
							juxtVertical++;
						}
						vertical++;
					} else if (casee == Plateau.CASEVIDE) {
						isJuxtVerticalBas = false;
					} else {
						isVerticalBas = false;
						isVerticalBasBloque = true;
					}
				}
			}
			if (dir != 2 && point.getX() + i < plateau.getDimY() && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY() + i);
				if (isDiagonalDroiteHaut) {
					if (casee == couleur) {
						if (isJuxtDiagonalDroiteHaut) {
							juxtDiagonalMontante++;
						}
						diagonalMontante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalDroiteHaut = false;
					} else {
						isDiagonalDroiteHaut = false;
						isDiagonalDroiteHautBloque = true;
					}
				}
			}
			if (dir != 2 && point.getX() + i < plateau.getDimY() && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX() + i, (int) point.getY() - i);
				if (isDiagonalDroiteBas) {
					if (casee == couleur) {
						if (isJuxtDiagonalDroiteBas) {
							juxtDiagonalDescendante++;
						}
						diagonalDescendante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalDroiteBas = false;
					} else {
						isDiagonalDroiteBas = false;
						isDiagonalDroiteBasBloque = true;
					}
				}
			}
			if (dir != 3 && point.getX() - i >= 0 && point.getY() + i < plateau.getDimY()) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY() + i);
				if (isDiagonalGaucheHaut) {
					if (casee == couleur) {
						if (isJuxtDiagonalGaucheHaut) {
							juxtDiagonalDescendante++;
						}
						diagonalDescendante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalGaucheHaut = false;
					} else {
						isDiagonalGaucheHaut = false;
						isDiagonalGaucheHautBloque = true;
					}
				}
			}
			if (dir != 3 && point.getX() - i >= 0 && point.getY() - i >= 0) {
				casee = plateau.getCase((int) point.getX() - i, (int) point.getY() - i);
				if (isDiagonalGaucheBas) {
					if (casee == couleur) {
						if (isJuxtDiagonalGaucheBas) {
							juxtDiagonalMontante++;
						}
						diagonalMontante++;
					} else if (casee == Plateau.CASEVIDE) {
						isDiagonalGaucheBas = false;
					} else {
						isDiagonalGaucheBas = false;
						isDiagonalGaucheBasBloque = true;
					}
				}
			}
		}
		if(juxtVertical == 3 && (!isVerticalBasBloque || !isVerticalHautBloque)){
			return true;
		}
		if(juxtHorizontal == 3 && (!isHorizontalGaucheBloque || !isHorizontalDroiteBloque)){
			return true;
		}
		if(juxtDiagonalDescendante == 3 && (!isDiagonalGaucheHautBloque || !isDiagonalDroiteBasBloque)){
			return true;
		}
		if(juxtDiagonalMontante == 3 && (!isDiagonalGaucheBasBloque || !isDiagonalDroiteHautBloque)){
			return true;
		}
		return false;
	}

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
		public boolean isTourIa() {
			return touria;
		}

		public void setPlateau(Plateau p) {
			this.plateau = p;
		}
	}
