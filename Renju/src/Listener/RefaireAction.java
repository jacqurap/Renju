package Listener;

import Modele.*;
import Vue.AireDeJeu;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * Actions du bouton Refaire
 *
 * @author bochatom
 */
public class RefaireAction implements ActionListener {

    private AireDeJeu aire;
    private JMenuItem btnAnnuler;
    private JMenuItem btnRefaire;
    private JButton annu;
    private JButton ref;

    /**
     * Creation d'une action pour refaire
     *
     * @param aire l'aire de jeu
     * @param annuler le bouton annuler
     * @param refaire le bouton refaire
     */
    public RefaireAction(AireDeJeu aire, JMenuItem annuler, JMenuItem refaire, JButton annu, JButton ref) {
        this.aire = aire;
        this.btnAnnuler = annuler;
        this.btnRefaire = refaire;
        this.annu = annu;
        this.ref = ref;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Point point = aire.getPartie().getRefaire().pop();
        aire.getPartie().getAnnuler().push(point);
        if (aire.getPartie().getNbCoups() % 2 == 0) {
            aire.getPartie().getPlateau().setCase(point.x, point.y, Plateau.CASENOIRE);
        } else {
            aire.getPartie().getPlateau().setCase(point.x, point.y, Plateau.CASEBLANCHE);
        }
        aire.getPartie().partieFini(point);
        aire.getPartie().incNbCoups();
        getBtnAnnuler().setEnabled(true);
        annu.setEnabled(true);
        if (aire.getPartie().getRefaire().empty()) {
            getBtnRefaire().setEnabled(false);
            ref.setEnabled(false);
        }
        if (aire.getPartie().isIa1() || aire.getPartie().isIa2()) {
        	 Point point2 = aire.getPartie().getRefaire().pop();
             aire.getPartie().getAnnuler().push(point2);
             if (aire.getPartie().getNbCoups() % 2 == 0) {
                 aire.getPartie().getPlateau().setCase(point2.x, point2.y, Plateau.CASENOIRE);
             } else {
                 aire.getPartie().getPlateau().setCase(point2.x, point2.y, Plateau.CASEBLANCHE);
             }
             aire.getPartie().partieFini(point2);
             aire.getPartie().incNbCoups();
             getBtnAnnuler().setEnabled(true);
             annu.setEnabled(true);
             if (aire.getPartie().getRefaire().empty()) {
                 getBtnRefaire().setEnabled(false);
                 ref.setEnabled(false);
        }
        }
        aire.repaint();
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
