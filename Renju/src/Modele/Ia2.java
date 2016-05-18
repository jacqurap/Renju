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
 *
 * @author jacqurap
 * 
 * joue aleatoirement sur une case libre et limitrophe a un pion
 */
public class Ia2 extends Ia{
    
    public Ia2(String nom){
        super(nom);
    }
    
    @Override
    public Point solver(Partie partie){
        ArrayList<Point> listCoupsPertinents = this.coupsPertinents(partie.getPlateau());
        Random rand = new Random();
    	return listCoupsPertinents.get(rand.nextInt(listCoupsPertinents.size()-1));
    }
}
