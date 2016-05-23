/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author bochatom
 */
public class ChargerPartie extends JPanel {

    public ChargerPartie(final Fenetre f) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.INTERFACEPANEL);
            }
        });
        btnCharger.setEnabled(false);

        ButtonGroup grpPartie = new ButtonGroup();
        for (int i = 1; i <= 10; i++) {
            String val = String.valueOf(i);
            JRadioButton rb = new JRadioButton("Slot " + val);
            rb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!btnCharger.isEnabled())
                    btnCharger.setEnabled(true);
                }
            });
            grpPartie.add(rb);
            this.add(rb);
        }

        this.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.ACCUEILPANEL);
            }
        });
        this.add(btnAnnuler);

    }
}
