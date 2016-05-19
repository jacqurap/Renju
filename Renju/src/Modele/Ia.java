/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Partie;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author jacqurap
 */
public abstract class Ia extends Joueur {

    public Ia(String nom) {
        super(nom);
    }
    
    public ArrayList<Point> coupsPertinents(Plateau p){
        ArrayList<Point> liste = new ArrayList<>();
        for(int i = 0; i < p.getDimX(); i++){
            for(int j = 0; j < p.getDimY(); j++){
                if(p.getCase(i, j) == Plateau.CASEVIDE && (
                		   i>0 && j>0 && p.getCase(i-1, j-1) != Plateau.CASEVIDE
                        || i>0 && p.getCase(i-1, j) != Plateau.CASEVIDE
                        || i>0 && j<p.getDimY()-1 && p.getCase(i-1, j+1) != Plateau.CASEVIDE
                        || j<p.getDimY()-1 && p.getCase(i, j+1) != Plateau.CASEVIDE
                        || i<p.getDimX()-1 && j<p.getDimY()-1 && p.getCase(i+1, j+1) != Plateau.CASEVIDE
                        || i<p.getDimX()-1 && p.getCase(i+1, j) != Plateau.CASEVIDE
                        || i<p.getDimX()-1 && j>0 && p.getCase(i+1, j-1) != Plateau.CASEVIDE
                        || j>0 && p.getCase(i, j-1) != Plateau.CASEVIDE
                        ))
                    liste.add(new Point(i, j));
            }
        }
        return liste;
    }
    
    public int evaluationCoup(Plateau plateau, Point point, int couleur){
        int i;
        boolean fini = false;
        for (i=1; i<=5 && !fini; i++){
            if(plateau.getCase((int)point.getX()+i, (int)point.getY()) == couleur){
                
            }
        }
        return 0;
    }

    public abstract Point solver(Partie partie);
}
