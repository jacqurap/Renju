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
 * L'IA (Niveau 2) qui
 *joue aleatoirement sur une case libre et limitrophe a un pion
 *@author jacqurap
 */
public class Ia2 extends Ia{
	
	/**
	 * Creation de l'IA (Niveau 2)
	 * @param nom le nom de l'IA
	 */
    
    public Ia2(String nom){
        super(nom);
    }
    
    @Override
    public Point solver(Partie partie){
        ArrayList<Point> listCoupsPertinents = this.coupsPertinents(partie.getPlateau(),partie.getNbCoups());
        Random rand = new Random();
        System.out.println(listCoupsPertinents.size());
        if(listCoupsPertinents.size()==1){
        	return listCoupsPertinents.get(0);
        }
    	return listCoupsPertinents.get(rand.nextInt(listCoupsPertinents.size()-1));
    }
}
