package Vue;

import Modele.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * Actions du bouton Refaire
 * @author bochatom
 */
class RefaireAction implements ActionListener {

    private AireDeJeu aire;
    private JMenuItem btnAnnuler;
    private JMenuItem btnRefaire;
    private JButton annu;
    private JButton ref;
    /**
     * Creation d'une action pour refaire
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
        int[][] GrilleActuelle = aire.getPartie().getPlateau().getGrille();
        Plateau newPlateau = new Plateau();
        newPlateau.setGrille(GrilleActuelle);
        
        Plateau plateau = aire.getPartie().getRefaire().pop();
        aire.getPartie().getAnnuler().push(newPlateau);
        aire.getPartie().getPlateau().setGrille(plateau.getGrille());
        getBtnAnnuler().setEnabled(true);
        annu.setEnabled(true);
        if (aire.getPartie().getRefaire().empty()) {
            getBtnRefaire().setEnabled(false);
            ref.setEnabled(false);
        }
        //aire.getPartie().printStacks(); DEBUG
        aire.repaint();
    }

    /**
     * Recupere l'aire de jeu
     * @return aire, l'aire de jeu
     */
    
    public AireDeJeu getAire() {
        return aire;
    }

    /**
     * Recupere le bouton annuler
     * @return btnAnnuler, le bouton annuler
     */
    
    public JMenuItem getBtnAnnuler() {
        return btnAnnuler;
    }

    /**
     * Recupere le bouton refaire
     * @return btnRefaire, le bouton refaire
     */
    
    public JMenuItem getBtnRefaire() {
        return btnRefaire;
    }

}