package Listener;

import Modele.*;
import Vue.AireDeJeu;
import Vue.AireDeJeu;
import Vue.AireDeJeu;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * Actions du bouton Annuler
 *
 * @author bochatom
 */
public class AnnulerAction implements ActionListener {

    private AireDeJeu aire;
    private JMenuItem btnAnnuler;
    private JMenuItem btnRefaire;
    private JButton annu;
    private JButton ref;

    /**
     * Creation d'une action d'annulation
     *
     * @param aire l'aire de jeu
     * @param annuler le bouton annuler
     * @param refaire le bouton refaire
     */
    public AnnulerAction(AireDeJeu aire, JMenuItem annuler, JMenuItem refaire, JButton annu, JButton ref) {
        this.aire = aire;
        this.btnAnnuler = annuler;
        this.btnRefaire = refaire;
        this.annu = annu;
        this.ref = ref;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //int[][] GrilleActuelle = aire.getPartie().getPlateau().getGrille();
        //Plateau newPlateau = new Plateau();
        //newPlateau.setGrille(GrilleActuelle);

        //Plateau newPlateau = aire.getPartie().getPlateau().clone();
        System.out.println("annulerAction : touria = " +aire.getPartie().isTourIa());
        if (!aire.getPartie().isTourIa() && (!aire.getPartie().isIa1() || aire.getPartie().getNbCoups() > 1)) {
            Point point = aire.getPartie().getAnnuler().pop();
            aire.getPartie().getRefaire().push(point);
            //aire.getPartie().getPlateau().setGrille(plateau.getGrille());
            aire.getPartie().getPlateau().setCase(point.x, point.y, Plateau.CASEVIDE);
            //aire.getPartie().setPlateau(newPlateau);
            aire.getPartie().decNbCoups();
            aire.getPartie().setPartieFinie(false);
            aire.getPartie().setJ1Win(false);
            aire.getPartie().setJ2Win(false);
            getBtnRefaire().setEnabled(true);
            ref.setEnabled(true);
            if (aire.getPartie().getAnnuler().empty()) {
                getBtnAnnuler().setEnabled(false);
                annu.setEnabled(false);
            }
            //aire.getPartie().printStacks(); DEBUG

            if (aire.getPartie().isIa1() || aire.getPartie().isIa2()) {
                point = aire.getPartie().getAnnuler().pop();
                aire.getPartie().getRefaire().push(point);
                //aire.getPartie().getPlateau().setGrille(plateau.getGrille());
                aire.getPartie().getPlateau().setCase(point.x, point.y, Plateau.CASEVIDE);
                //aire.getPartie().setPlateau(newPlateau);
                aire.getPartie().decNbCoups();
                aire.getPartie().setPartieFinie(false);
                aire.getPartie().setJ1Win(false);
                aire.getPartie().setJ2Win(false);
                
                getBtnRefaire().setEnabled(true);
                ref.setEnabled(true);
                if (aire.getPartie().getAnnuler().empty()) {
                    getBtnAnnuler().setEnabled(false);
                    annu.setEnabled(false);
                }
            }if (aire.getPartie().isIa1() && aire.getPartie().getNbCoups() % 2 == 0) {
                    aire.getPartie().setTouria(true);
                }
                if (aire.getPartie().isIa2() && aire.getPartie().getNbCoups() % 2 == 1) {
                    aire.getPartie().setTouria(true);
                }
            aire.repaint();
        }

    }

    /**
     * Recupere l'aire de jeu
     *
     * @return aire, l'aire de jeu
     */
    public AireDeJeu getAire() {
        return aire;
    }

    /**
     * Recupere le bouton annuler
     *
     * @return btnAnnuler, le bouton annuler
     */
    public JMenuItem getBtnAnnuler() {
        return btnAnnuler;
    }

    /**
     * Recupere le bouton refaire
     *
     * @return btnRefaire, le bouton refaire
     */
    public JMenuItem getBtnRefaire() {
        return btnRefaire;
    }

}
