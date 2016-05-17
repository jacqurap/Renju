/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacqurap
 */
public class Partie {
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

	public Partie(String classJ1, String nomJ1, String classJ2, String nomJ2){

		this.plateau = new Plateau();
		joueur1 = new Humain(nomJ1);
		joueur2 = new Humain(nomJ2);
		/* creation des joueur en foction de leur classe sans condition a ralonge, et ça c'est la CLASS  !!!!!!  si ça c'est pas une blague trop badass ...*/
		/*
		try {
			this.joueur1 = (Joueur) Class.forName(classJ1).getConstructor(String.class).newInstance(nomJ1);
			this.joueur2 = (Joueur) Class.forName(classJ2).getConstructor(String.class).newInstance(nomJ2);
		} catch (Exception ex) {
			Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
		}*/
	}


	public boolean click(Point p){ //TODO

		if( (joueur1 instanceof Humain && nbCoups % 2 == 0 ) || joueur2 instanceof Humain && nbCoups % 2 == 1 ){

			if(nbCoups == 0 && p.x==7 && p.y == 7){
				action(p);
				return true;
			}

			else if(nbCoups == 1 && ( p.x == 6 && (p.y ==6 || p.y == 7 || p.y == 8)
					|| ( p.x == 7 && (p.y == 6 || p.y == 8))
					|| ( p.x == 8 && (p.y ==6 || p.y == 7 || p.y == 8)))){
				action(p);
				return true;
			}

			else if(nbCoups>1 && plateau.getCase(p.x, p.y) == plateau.CASEVIDE){
				action(p);
				return true;
			}
		}
		return false;
	}




	public boolean action(Point p){ //TODO
		if(nbCoups % 2 == 0)
			plateau.setCase(p.x, p.y, plateau.CASENOIRE);
		else
			plateau.setCase(p.x, p.y, plateau.CASEBLANCHE);
		partieFini(p);
		incNbCoups();
		return true;
	}

	public void partieFini(Point p){
		int n =1;
		int i=1;
		if(nbCoups%2==0){
			//horizontale
			while(n<5 && plateau.getCase(p.x+i, p.y)== plateau.CASENOIRE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x-i, p.y)== plateau.CASENOIRE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
			
			//verticale
			n=1;
			i=1;
			while(n<5 && plateau.getCase(p.x, p.y+i)== plateau.CASENOIRE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x, p.y-i)== plateau.CASENOIRE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
			
			//diagonale1
			n=1;
			i=1;
			while(n<5 && plateau.getCase(p.x+i, p.y+i)== plateau.CASENOIRE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x-i, p.y-i)== plateau.CASENOIRE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
			
			//diagonale2
			n=1;
			i=1;
			while(n<5 && plateau.getCase(p.x+i, p.y-i)== plateau.CASENOIRE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x-i, p.y+i)== plateau.CASENOIRE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
		}
		else{
			//horizontale
			while(n<5 && plateau.getCase(p.x+i, p.y)== plateau.CASEBLANCHE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x-i, p.y)== plateau.CASEBLANCHE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
			
			//verticale
			n=1;
			i=1;
			while(n<5 && plateau.getCase(p.x, p.y+i)== plateau.CASEBLANCHE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x, p.y-i)== plateau.CASEBLANCHE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
			
			//diagonale
			n=1;
			i=1;
			while(n<5 && plateau.getCase(p.x+i, p.y+i)== plateau.CASEBLANCHE){
				n++;
				i++;
			}
			i=1;
			while(n<5 && plateau.getCase(p.x-i, p.y-i)== plateau.CASEBLANCHE){
				n++;
				i++;
			}
			if(n==5){
				partieFinie = true;
				j1Win = true;
			}
		}
		System.out.println(partieFinie);
	}

	public ArrayList<Point> coupsJouables(){
		return coupsJouables(this.plateau);
	}


	public ArrayList<Point> coupsJouables(Plateau p){ //TODO
		ArrayList<Point> coupJouables = new ArrayList<Point>();
		
		if(nbCoups == 0 ){
			coupJouables.add(new Point(7,7));
		}

		else if(nbCoups == 1 ){
			coupJouables.add(new Point(6,7));
			coupJouables.add(new Point(8,7));
			coupJouables.add(new Point(7,6));
			
			coupJouables.add(new Point(7,8));
			coupJouables.add(new Point(6,6));
			coupJouables.add(new Point(8,8));
			
			coupJouables.add(new Point(8,6));
			coupJouables.add(new Point(6,8));
		}

		else {
			for(int i=0;i<15;i++){
				for(int j=0;j<15;j++){
					if(plateau.getCase(i, j)== plateau.CASEVIDE)
						coupJouables.add(new Point(i,j));
				}
			}
		}
		return coupJouables;
	}

	public Joueur getJoueur1(){
		return joueur1;
	}


	public Joueur getJoueur2(){
		return joueur2;
	}


	public Plateau getPlateau(){
		return plateau;
	}


	public int getNbCoups(){
		return nbCoups;
	}


	public void incNbCoups(){
		nbCoups++;
	}


	public void decNbCoups(){
		nbCoups--;
	}
}
