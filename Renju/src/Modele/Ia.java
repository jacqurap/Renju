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
 * Le modele IA
 *
 * @author jacqurap
 */
public abstract class Ia extends Joueur {

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
        return liste;
    }

    /**
     * Evalue le coup a jouer
     *
     * @param plateau le plateau de la partie
     * @param point le point du coup a jouer
     * @param couleur la couleur de la pierre
     * @return valeur, le poids du coup a jouer
     */
    //Fonction a modifier
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
                    }
                }
            }
        }
        if (juxtHorizontal >= 4 || juxtVertical >= 4 || juxtDiagonalDescendante >= 4 || juxtDiagonalMontante >= 4) {
            return 10000;
        }
        valeur = 3 * (juxtHorizontal ^ 3 + juxtVertical ^ 3 + juxtDiagonalDescendante ^ 3 + juxtDiagonalMontante ^ 3) + horizontal ^ 3 + vertical ^ 3 + diagonalDescendante ^ 3 + diagonalMontante ^ 3;
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
                           // System.out.println("-----------");
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
        //System.out.println("-------  " + valeur);
        return valeur;
    }

    /**
     * Fonction de resolution
     *
     * @param partie
     */
    public abstract Point solver(Partie partie);

}
