/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Partie;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Le modele IA
 *
 * @author jacqurap
 */
public abstract class Ia extends Joueur {

    ArrayList<Point>[] listeDeListe;
    
    protected int profondeur;
    
    /**
     * Creation d'une IA
     *
     * @param nom le nom du joueur ordinateur
     */
    public Ia(String nom) {
        super(nom);
    }

    /**
     * Liste les differents coups pertinents
     *
     * @param p le plateau de la partie
     * @return liste, la liste des coups pertinents
     */
    
    public ArrayList<Point> coupsPertinents(Plateau p, int nbCoups) {
        ArrayList<Point> liste = new ArrayList<>();
        coupsPertinents(p, nbCoups, liste);
        return liste;
    }
    
    public void coupsPertinents(Plateau p, int nbCoups, ArrayList<Point> liste) {
        if (nbCoups == 0) {
            liste.add(new Point(7, 7));
        } else if (nbCoups == 1) {
            liste.add(new Point(6, 7));
            liste.add(new Point(8, 7));
            liste.add(new Point(7, 6));
            liste.add(new Point(7, 8));
            liste.add(new Point(6, 6));
            liste.add(new Point(8, 8));
            liste.add(new Point(8, 6));
            liste.add(new Point(6, 8));
        } else {
            for (int i = 0; i < p.getDimX(); i++) {
                for (int j = 0; j < p.getDimY(); j++) {
                    if (p.getCase(i, j) == Plateau.CASEVIDE && (i > 0 && j > 0 && p.getCase(i - 1, j - 1) != Plateau.CASEVIDE
                            || i > 0 && p.getCase(i - 1, j) != Plateau.CASEVIDE
                            || i > 0 && j < p.getDimY() - 1 && p.getCase(i - 1, j + 1) != Plateau.CASEVIDE
                            || j < p.getDimY() - 1 && p.getCase(i, j + 1) != Plateau.CASEVIDE
                            || i < p.getDimX() - 1 && j < p.getDimY() - 1 && p.getCase(i + 1, j + 1) != Plateau.CASEVIDE
                            || i < p.getDimX() - 1 && p.getCase(i + 1, j) != Plateau.CASEVIDE
                            || i < p.getDimX() - 1 && j > 0 && p.getCase(i + 1, j - 1) != Plateau.CASEVIDE
                            || j > 0 && p.getCase(i, j - 1) != Plateau.CASEVIDE)) {
                        liste.add(new Point(i, j));
                    }
                }
            }
        }
    }
    
    /**
     * Evalue le coup a jouer
     *
     * @param plateau le plateau de la partie
     * @param point le point du coup a jouer
     * @param couleur la couleur de la pierre
     * @return valeur, le poids du coup a jouer
     */
    public static int evaluationCoup(Plateau plateau, Point point, int couleur) {
        int i;
        int valeur;
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
        int trouhorizontalg = 0, trouhorizontald = 0, trouverticalh = 0, trouverticalb = 0, troudiagonalMontanteg = 0, troudiagonalMontanted = 0, troudiagonalDescendanteg = 0, troudiagonalDescendanted = 0;
        int casee;
        for (i = 1; i <= 5; i++) {
            if (point.getX() + i < plateau.getDimX()) {
                casee = plateau.getCase((int) point.getX() + i, (int) point.getY());
                if (isHorizontalDroite) {
                    if (casee == couleur) {
                        if (isJuxtHorizontalDroite) {
                            juxtHorizontal++;
                        }
                         if (trouhorizontald < 2) {
                            horizontal++;
                         }
                    } else if (casee == Plateau.CASEVIDE) {
                        isJuxtHorizontalDroite = false;
                        trouhorizontald++;
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
                        if (trouhorizontalg < 2) {
                            horizontal++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isJuxtHorizontalGauche = false;
                        trouhorizontalg++;
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
                        if (trouverticalh < 2) {
                            vertical++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isJuxtVerticalHaut = false;
                        trouverticalh++;
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
                        if (trouverticalb < 2) {
                            vertical++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isJuxtVerticalBas = false;
                        trouverticalb++;
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
                        if (troudiagonalMontanted < 2) {
                            diagonalMontante++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isDiagonalDroiteHaut = false;
                        troudiagonalMontanted++;
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
                        if (troudiagonalDescendanted < 2) {
                            diagonalDescendante++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isDiagonalDroiteBas = false;
                        troudiagonalDescendanted++;
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
                        if (troudiagonalDescendanteg < 2) {
                            diagonalDescendante++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isDiagonalGaucheHaut = false;
                        troudiagonalDescendanteg++;
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
                        if (troudiagonalMontanteg < 2) {
                            diagonalMontante++;
                        }
                    } else if (casee == Plateau.CASEVIDE) {
                        isDiagonalGaucheBas = false;
                        troudiagonalMontanteg++;
                    } else {
                        isDiagonalGaucheBas = false;
                        isDiagonalGaucheBasBloque = true;
                    }
                }
            }
        }
        
        int nb33NonBloque = 0;
        if((juxtVertical == 2 && vertical != 3 || vertical == 2) && !isVerticalBasBloque && !isVerticalHautBloque){
            nb33NonBloque++;
        }
        if((juxtHorizontal == 2 && horizontal!=3 || horizontal == 2) && !isHorizontalGaucheBloque && !isHorizontalDroiteBloque){
            nb33NonBloque++;
        }
        if((juxtDiagonalDescendante == 2 && diagonalDescendante != 3 || diagonalDescendante == 2) && !isDiagonalGaucheHautBloque && !isDiagonalDroiteBasBloque){
            nb33NonBloque++;
        }
        if((juxtDiagonalMontante == 2 && diagonalMontante != 3 || diagonalMontante == 2) && !isDiagonalGaucheBasBloque && !isDiagonalDroiteHautBloque){
            nb33NonBloque++;
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
        
        if((couleur == Plateau.CASENOIRE) 
                && (juxtHorizontal > 4              /// ligne de plus de 5 pions
                        || juxtVertical > 4 
                        || juxtDiagonalDescendante > 4 
                        || juxtDiagonalMontante > 4)){
            return Integer.MIN_VALUE;
        }
        
        if (juxtHorizontal >= 4 || juxtVertical >= 4 || juxtDiagonalDescendante >= 4 || juxtDiagonalMontante >= 4) {
            return Integer.MAX_VALUE;
        }
        
        if((couleur == Plateau.CASENOIRE) 
                && ( nb33NonBloque >= 2          /// 3 * 3 non bloque
                    || nb44NonBloque >= 2           /// 4 * 4 non double bloque                
                )){
            return Integer.MIN_VALUE;
        }
        
        
        valeur = 1000 * (nb33NonBloque + nb44NonBloque) ^ 5 + horizontal ^ 3 + vertical ^ 3 + diagonalDescendante ^ 3 + diagonalMontante ^ 3;
        return valeur;
    }

    public static int evaluationPlateau(Plateau plateau, int couleur) {
        int i, j;
        int valeur = 0;
        int tmp;
        Point p = new Point();
        for (i = 0; i < plateau.getDimX(); i++) {
            for (j = 0; j < plateau.getDimX(); j++) {
                if (plateau.getCase(i, j) != Plateau.CASEVIDE) {
                    p.setLocation(i, j);
                    if (plateau.getCase(i, j) == Plateau.CASENOIRE) {
                        
                        tmp = evaluationCoup(plateau, p, Plateau.CASENOIRE);
                        if(tmp == Integer.MAX_VALUE){
                            if(couleur == Plateau.CASENOIRE)
                                return tmp;
                            else
                                return -tmp;
                            
                        }
                        if(tmp == Integer.MIN_VALUE) //en cas de defaite pour cause de tabou
                            return tmp;
                        else if(couleur == Plateau.CASENOIRE)
                        	valeur += tmp;
                        else
                        	valeur -= tmp;
                    } else // if (plateau.getCase(i, j) == Plateau.CASEBLANCHE) {
                    {
                        tmp = evaluationCoup(plateau, p, Plateau.CASEBLANCHE);
                        if(tmp == Integer.MAX_VALUE){
                            if(couleur == Plateau.CASEBLANCHE)
                                return -tmp;
                            else
                                return tmp;
                        }
                        if(couleur == Plateau.CASEBLANCHE) 
                        	valeur += tmp;
                        else
                        	valeur -= tmp;
                    }
                    
                }
            }
        }
        return valeur;
    }

    /**
     * Fonction de resolution
     *
     * @param partie
     */
    public Point solver(Partie partie) {
        int meilleur;
        int couleur;
        Plateau plateau = partie.getPlateau().clone();
        ArrayList<ValeurCoup> listeCoupsValeur = new ArrayList<>();
        if (partie.getNbCoups() % 2 == 0) {
            couleur = Plateau.CASENOIRE;
        } else {
            couleur = Plateau.CASEBLANCHE;
        }
        listeDeListe[profondeur - 1].clear();
        coupsPertinents(plateau, partie.getNbCoups(), listeDeListe[profondeur - 1]);
        for (Point p : listeDeListe[profondeur - 1]) {
            plateau.setCase(p.x, p.y, couleur);
            listeCoupsValeur.add(new ValeurCoup(p, minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), false, partie.getNbCoups(), Integer.MIN_VALUE, Integer.MAX_VALUE)));
            plateau.setCase(p.x, p.y, Plateau.CASEVIDE);
        }
        Collections.sort(listeCoupsValeur);
        Collections.reverse(listeCoupsValeur);

        int valeurMax = listeCoupsValeur.get(0).valeur;
        int range = valeurMax;
        int i = 1;
        while (i < listeCoupsValeur.size() && listeCoupsValeur.get(i).valeur >= range) {
            i++;
        }
        Random rand = new Random();
        if (i > 1) {
            return listeCoupsValeur.get(rand.nextInt(i - 1)).point;
        }
        return listeCoupsValeur.get(0).point;
    }

    /**
     * Creation d'une partie de l'arbre MiniMax
     *
     * @param plateau le plateau de la partie
     * @param point les coordonnee du coup a jouer
     * @param profondeur la profondeur de l'arbre
     * @param couleur la couleur du joueur a cette profondeur
     * @param maximiser faut-il maximiser ou minimiser a cette profondeur
     * @return l'evaluation minimax du coups rentre en parametre "point"
     */
    public int minimax(Plateau plateau, Point point, int profondeur, int couleur, boolean maximiser, int nbCoups, int alpha, int beta) {
        int meilleur;
        int valeur;
        int fin = evaluationPlateau(plateau, plateau.getAutreCouleur(couleur));
        if (fin == Integer.MAX_VALUE) { // le joueur gagne
            if (!maximiser) {
                return Integer.MAX_VALUE - (this.profondeur - profondeur);
            } else {
                return Integer.MIN_VALUE + (this.profondeur - profondeur);
            }
        }
        if (fin == Integer.MIN_VALUE) { // noir joue un tabou
            if (!maximiser) {
                return Integer.MIN_VALUE + (this.profondeur - profondeur);
            } else {
                return Integer.MAX_VALUE - (this.profondeur - profondeur);
            }
        }
        if (profondeur == 0) {
            if (!maximiser) {
                return fin;
            } else {
                return -fin;
            }
        }
        if (maximiser) {
            meilleur = Integer.MIN_VALUE;
            listeDeListe[profondeur - 1].clear();
            coupsPertinents(plateau, nbCoups, listeDeListe[profondeur - 1]);
            for (Point p : listeDeListe[profondeur - 1]) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), false, nbCoups + 1, alpha, beta);
                meilleur = Math.max(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
                if (beta <= meilleur) {
                    return meilleur;
                }
                alpha = Math.min(alpha, valeur);
            }
            return meilleur;
        } else { //minimiser
            meilleur = Integer.MAX_VALUE;
            listeDeListe[profondeur - 1].clear();
            coupsPertinents(plateau, nbCoups, listeDeListe[profondeur - 1]);
            for (Point p : listeDeListe[profondeur - 1]) {
                plateau.setCase((int) p.getX(), (int) p.getY(), couleur);
                valeur = minimax(plateau, p, profondeur - 1, plateau.getAutreCouleur(couleur), true, nbCoups + 1, alpha, beta);
                meilleur = Math.min(meilleur, valeur);
                plateau.setCase((int) p.getX(), (int) p.getY(), Plateau.CASEVIDE);
                if (alpha >= meilleur) {
                    return meilleur;
                }
                beta = Math.max(beta, valeur);
            }
            return meilleur;
        }
    }

}

/**
 * structure de donnee permettant d'associer un point a une valeur
 */
class ValeurCoup implements Comparable<ValeurCoup> {

    Point point;
    int valeur;

    /**
     * Creation du poids d'un coup
     * @param p un point de coordonnee (x,y)
     * @param v une valeur
     */
    public ValeurCoup(Point p, int v) {
        this.point = p;
        this.valeur = v;
    }

    @Override
    public int compareTo(ValeurCoup autre) {
        return Integer.compare(this.valeur , autre.valeur);
    }
    
    public String toString(){
        return ("p = " + point + " v = " + valeur);
    }
}
