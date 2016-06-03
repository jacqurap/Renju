/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Listener.AnnulerVersAccueilListener;
import Listener.ChargerPartieListener;
import Listener.SlotChargerListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author bochatom
 */
public class ChargerPartie extends JPanel {

    ButtonGroup grpPartie = new ButtonGroup();
    Fenetre fenetre;

    public ChargerPartie(Fenetre f) {
        this.fenetre = f;
        RefreshSaves();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void RefreshSaves() {
        //suppression des anciennes donnees
        this.removeAll();
        //ajouts donnees sauvegardes
        final Popups pop = new Popups(fenetre);
        pop.Sauvegarde();
        int vide = 0;
        for (int i = 1; i < 10; i++) {
            if (pop.getSlot()[i - 1] != null) {
                String val = (pop.getSlot()[i - 1].toString().substring(pop.getSavePath().length() + 2, (int) pop.getSlot()[i - 1].toString().length() - 4));
                JButton rb = new JButton(val);
                rb.addActionListener(new ChargerPartieListener(fenetre, i));
                grpPartie.add(rb);
                this.add(rb);
                vide++;
            }
        }
        if (vide == 0) {
            JLabel warning = new JLabel("Aucune sauvegarde disponible");
            this.add(warning);
        }
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new AnnulerVersAccueilListener(fenetre));
        this.add(btnAnnuler);

    }
}
