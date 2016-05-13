/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import java.awt.Point;
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
    
    public Partie(String classJ1, String nomJ1, String classJ2, String nomJ2){
        this.plateau = new Plateau();
        
        /* creation des joueur en foction de leur classe sans condition a ralonge, et ça c'est la CLASS  !!!!!!  si ça c'est pas une blague trop badass ...*/
        try {
            this.joueur1 = (Joueur) Class.forName(classJ1).getConstructor(String.class).newInstance(nomJ1);
            this.joueur2 = (Joueur) Class.forName(classJ2).getConstructor(String.class).newInstance(nomJ2);
        } catch (Exception ex) {
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean click(Point p){ //TODO
        if( (joueur1 instanceof Humain && nbCoups % 2 == 0 && nbCoups != 2) || joueur2 instanceof Humain && nbCoups % 2 == 1 || nbCoups == 2){
            action(p);
            return true;
        }
        else return false;
    }
    public boolean action(Point p){ //TODO
        return true;
    }
    
    public Point[] coupsJouables(){
        return coupsJouables(this.plateau);
    }
    public Point[] coupsJouables(Plateau p){ //TODO
        return null;
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