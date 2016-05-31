package Listener;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controleur.Partie;
import Vue.AireDeJeu;

public class EcouteurDeSouris implements MouseListener {

    private AireDeJeu aire;

    public EcouteurDeSouris(AireDeJeu a) {
        aire = a;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!aire.getPartie().isPartieFinie()) {
            int caseSize = (aire.getHeight() / 16);
            int x = (int) ((e.getX() - (caseSize / 2)) / caseSize);
            int y = (int) ((e.getY() - (caseSize / 2)) / caseSize);
            if ((x < 15 && y < 15) && aire.getPartie().click(new Point(x, y))) { //15 pour les coups en dehors de la zone de jeu
                aire.repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public AireDeJeu getAire() {
        return aire;
    }

    public void setAire(AireDeJeu aire) {
        this.aire = aire;
    }

}
