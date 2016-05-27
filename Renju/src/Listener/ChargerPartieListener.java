package Listener;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controleur.Partie;
import Vue.*;

public class ChargerPartieListener implements ActionListener {

    private int numSlot;
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

        //affichage du panel interface de jeu
        f.changePanel(f.getINTERFACEPANEL());
    }

    /**
     * @return the numSlot
     */
    public int getNumSlot() {
        return numSlot;
    }

    /**
     * @param numSlot the numSlot to set
     */
    public void setNumSlot(int numSlot) {
        this.numSlot = numSlot;
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
