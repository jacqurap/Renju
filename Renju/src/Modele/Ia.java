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

    public ArrayList<Point> coupsPertinents(Plateau p) {
        ArrayList<Point> liste = new ArrayList<>();
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
        return liste;
    }

    public int evaluationCoup(Plateau plateau, Point point, int couleur) {
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
            return Integer.MAX_VALUE;
        }
        valeur = 3 * (juxtHorizontal + juxtVertical + juxtDiagonalDescendante + juxtDiagonalMontante) + horizontal + vertical + diagonalDescendante + diagonalMontante;
        return valeur;
    }

    public int evaluationPlateau(Plateau plateau) {
        return 0;
    }

    public abstract Point solver(Partie partie);
}
