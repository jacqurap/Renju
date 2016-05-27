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

    public ChargerPartie(Fenetre f) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener(new ChargerPartieListener(f));
        btnCharger.setEnabled(false);

        ButtonGroup grpPartie = new ButtonGroup();
        for (int i = 1; i <= 10; i++) {
            String val = String.valueOf(i);
            JRadioButton rb = new JRadioButton("Slot " + val);
            rb.addActionListener(new SlotChargerListener(btnCharger));
            grpPartie.add(rb);
            this.add(rb);
        }

        this.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new AnnulerVersAccueilListener(f));
        this.add(btnAnnuler);

    }
}
