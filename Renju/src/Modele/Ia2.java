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
public class Ia2 extends Ia{
    
    public Ia2(String nom){
        super(nom);
    }
    
    public Point solver(Partie partie){
        ArrayList<Point> listCoupsGagnant = new ArrayList<Point>();
        ArrayList<Point> listCoupsNonPerdant = new ArrayList<Point>();
        
        ArrayList<Point> listCoupsJouables = partie.coupsJouables();
        Random rand = new Random();
        
        for(int i = 0; i < listCoupsJouables.size(); i++){
            
        }
    	return listCoupsJouables.get(rand.nextInt(listCoupsJouables.size()-1));
    }
}
