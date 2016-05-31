package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controleur.Partie;
import Vue.*;

public class InterfaceJeuListener implements ActionListener {

    private int val;
    private Popups popup;
    private InterfaceJeu interjeu;
    private String theme;

    public InterfaceJeuListener(Popups pop, InterfaceJeu ij, int v) {
        this.popup = pop;
        this.interjeu = ij;
        this.val = v;

    }

    public InterfaceJeuListener(Popups pop, int v) {
        this.popup = pop;
        this.val = v;

    }

    public InterfaceJeuListener(int v, InterfaceJeu ij, String theme) {
        this.val = v;
        this.interjeu = ij;
        this.theme = theme;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (val) {
            case 1:
                popup.popSauver(interjeu.getAire().getPartie());
                break;
            case 2:
                popup.popCharger(interjeu.getAire().getPartie());
                break;
            case 3:
                popup.popRecommencer();
                break;
            case 4:
                popup.popAbandonner();
                break;
            case 5:
                popup.popRegles();
                break;
            case 6:
                interjeu.getAire().setThemePlateau(theme);
                interjeu.getAire().repaint();
            default:;
        }

    }
}
