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
public class Ia3 extends Ia{
    
    public Ia3(String nom){
        super(nom);
    }
    
    @Override
    public Point solver(Partie partie){
        ArrayList<Point> listCoupsGagnant = new ArrayList<Point>();
        ArrayList<Point> listCoupsNonPerdant = new ArrayList<Point>();
        
        ArrayList<Point> listCoupsPertinents = this.coupsPertinents(partie.getPlateau());
        Random rand = new Random();
        
        for(int i = 0; i < listCoupsPertinents.size(); i++){
            
        }
    	return listCoupsPertinents.get(rand.nextInt(listCoupsPertinents.size()-1));
    }
}
