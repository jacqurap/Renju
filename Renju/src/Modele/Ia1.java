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
 */
public class Ia1 extends Ia{
    
    public Ia1(String nom){
        super(nom);
    }
    
    public Point solver(Partie partie){
        ArrayList<Point> listCoupsJouables = partie.coupsJouables();
        Random rand = new Random();
    	return listCoupsJouables.get(rand.nextInt(listCoupsJouables.size()-1));
    }
}
