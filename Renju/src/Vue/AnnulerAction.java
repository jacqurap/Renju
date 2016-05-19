package Vue;

import Modele.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 * Actions du bouton Annuler
 * @author bochatom
 */
class AnnulerAction implements ActionListener {

    private AireDeJeu aire;
    private JMenuItem btnAnnuler;
    private JMenuItem btnRefaire;
    
    /**
     * Création d'une action d'annulation
     * @param aire l'aire de jeu
     * @param annuler le bouton annuler
     * @param refaire le bouton refaire
     */
    
    public AnnulerAction(AireDeJeu aire, JMenuItem annuler, JMenuItem refaire) {
        this.aire = aire;
        this.btnAnnuler = annuler;
        this.btnRefaire = refaire;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        int[][] GrilleActuelle = aire.getPartie().getPlateau().getGrille();
        Plateau newPlateau = new Plateau();
        newPlateau.setGrille(GrilleActuelle);
        
        Plateau plateau = aire.getPartie().getAnnuler().pop();
        aire.getPartie().getRefaire().push(newPlateau);
        aire.getPartie().getPlateau().setGrille(plateau.getGrille());
        getBtnRefaire().setEnabled(true);
        if(aire.getPartie().getAnnuler().empty()) { 
            getBtnAnnuler().setEnabled(false);
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