package Listener;

import Vue.Popups;
import Vue.NouvellePartie;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controleur.Partie;
import Vue.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class ChargerPartieListener implements ActionListener {

    private int SaveNum;
    private Fenetre f;

    public ChargerPartieListener(Fenetre f) {
        setF(f);
    }

    public ChargerPartieListener(Fenetre f, int numSlot) {
        setF(f);
        setNumSlot(numSlot);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //chargement de la partie
        final Popups pop = new Popups(f);
        pop.Sauvegarde();
        pop.setSaveNum(SaveNum);
        f.changePanel(f.getINTERFACEPANEL());
        f.refreshInterface();
        //affichage du panel interface de jeu
        f.changePanel(f.getINTERFACEPANEL());
        f.getAire().setPartie(pop.Charger());
        
        //lancement thread de l'ia
        if (f.getAire().getPartie().isIa1() || f.getAire().getPartie().isIa2()) {
            Timer t = new Timer();
            TimerTask tache = new TimerTask() {
                @Override
                public void run() {
                    if (!f.getAire().getPartie().isPartieFinie() && f.getAire().getPartie().isTourIa()) {
                        f.getAire().getPartie().joueIa();
                        f.getAire().repaint();
                    }
                }
            };
            t.scheduleAtFixedRate(tache, 1000, 2000);
        }
        // Annuler / Refaire après chargement de la partie
        if (!f.getAire().getPartie().getAnnuler().isEmpty()) {
            InterfaceJeu.getButAnnuler().setEnabled(true);
        } else {
            InterfaceJeu.getButAnnuler().setEnabled(false);
        }
        if (!f.getAire().getPartie().getRefaire().isEmpty()) {
            InterfaceJeu.getButRefaire().setEnabled(true);
        } else {
            InterfaceJeu.getButRefaire().setEnabled(false);
        }
        f.getInterjeu().getTfJ1().setText(f.getAire().getPartie().getJoueur1().getNom());
        f.getInterjeu().getTfJ2().setText(f.getAire().getPartie().getJoueur2().getNom());
        f.getAire().repaint();
    }

    /**
     * @return the numSlot
     */
    public int getNumSlot() {
        return SaveNum;
    }

    /**
     * @param numSlot the numSlot to set
     */
    public void setNumSlot(int numSlot) {
        this.SaveNum = numSlot;
    }

    /**
     * @return the f
     */
    public Fenetre getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(Fenetre f) {
        this.f = f;
    }
}
