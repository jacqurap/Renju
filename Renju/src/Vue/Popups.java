/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author bochatom
 */
public class Popups {

    public Fenetre f;

    public Popups(final Fenetre f) {
        this.f = f;
    }

    public void popRegles() {
        final JDialog popRegle = new JDialog();
        popRegle.setTitle("Aide de jeu");

        JDialog.setDefaultLookAndFeelDecorated(true);
        popRegle.setSize(500, 200);
        popRegle.setLocationRelativeTo(null);
        popRegle.setLayout(new FlowLayout());
        popRegle.add(new JLabel("Coups interdits"));

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRegle.dispose();
            }
        });
        popRegle.add(btnRetour);

        popRegle.setVisible(true);
    }

    public void popQuitterMenu() {
        final JDialog popQuitterMenu = new JDialog();
        popQuitterMenu.setTitle("Quitter");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popQuitterMenu.setSize(500, 200);
        popQuitterMenu.setLocationRelativeTo(null);
        popQuitterMenu.setLayout(new FlowLayout());

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.closeGame();
                popQuitterMenu.dispose();
                System.exit(0);
            }
        });

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popQuitterMenu.dispose();
            }
        });

        popQuitterMenu.add(new JLabel("Voulez-vous vraiment quitter l'application ?"));
        popQuitterMenu.add(btnQuitter);
        popQuitterMenu.add(btnAnnuler);
        popQuitterMenu.setVisible(true);
    }

    public void popCharger() {
        final JDialog popCharger = new JDialog();
        popCharger.setTitle("Charger");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popCharger.setSize(500, 200);

        popCharger.setLocationRelativeTo(null);
        popCharger.setLayout(new FlowLayout());

        for (int i = 1; i <= 10; i++) {
            String val = String.valueOf(i);
            popCharger.add(new JButton("Slot " + val));
        }
        JLabel warning = new JLabel("Attention vous allez perdre la partie en cours !");
        popCharger.add(warning);
        JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.INTERFACEPANEL);
                popCharger.dispose();
            }
        });
        popCharger.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popCharger.dispose();
            }
        });
        popCharger.add(btnAnnuler);

        popCharger.setVisible(true);
    }

    public void popSauver() {
        final JDialog popSauver = new JDialog();
        popSauver.setTitle("Sauvegarder");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popSauver.setSize(500, 200);

        popSauver.setLocationRelativeTo(null);
        popSauver.setLayout(new FlowLayout());

        final JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popSauver.dispose();
            }
        });
        btnSauvegarder.setEnabled(false);

        final JLabel warning = new JLabel("Attention une partie est déjà sauvegardée sur ce slot, vous allez l'écraser !");
        warning.setVisible(false);

        ButtonGroup grpSlot = new ButtonGroup();
        for (int i = 1; i <= 10; i++) {
            String val = String.valueOf(i);
            JRadioButton slot = new JRadioButton("Slot " + val);
            slot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!btnSauvegarder.isEnabled()) {
                        btnSauvegarder.setEnabled(true);
                    }
                }
            });
            if (i < 5) { //pour tester
                slot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        warning.setVisible(true);
                    }
                });
            }
            if (i >= 5) {
                slot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        warning.setVisible(false);
                    }
                });
            }
            grpSlot.add(slot);
            popSauver.add(slot);
        }

        popSauver.add(warning);

        JLabel labelNom = new JLabel("Nom de la sauvegarde :");
        popSauver.add(labelNom);
        JTextField nom = new JTextField("Sauvegarde", 20);
        popSauver.add(nom);

        popSauver.add(btnSauvegarder);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popSauver.dispose();
            }
        });
        popSauver.add(btnAnnuler);

        popSauver.setVisible(true);
    }

    public void popRecommencer() {
        final JDialog popRecommencer = new JDialog();
        popRecommencer.setTitle("Recommencer");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popRecommencer.setSize(500, 200);

        popRecommencer.setLocationRelativeTo(null);
        popRecommencer.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie en cours sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment recommencer la partie ?");
        popRecommencer.add(warning);

        JButton btnRecommencer = new JButton("Recommencer");
        btnRecommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRecommencer.dispose();
            }
        });
        popRecommencer.add(btnRecommencer);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRecommencer.dispose();
            }
        });
        popRecommencer.add(btnAnnuler);

        popRecommencer.setVisible(true);
    }

    public void popAbandonner() {
        final JDialog popAbandonner = new JDialog();
        popAbandonner.setTitle("Abandonner");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popAbandonner.setSize(500, 200);

        popAbandonner.setLocationRelativeTo(null);
        popAbandonner.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment abandonner la partie en cours, et retourner au menu principale ?");
        popAbandonner.add(warning);

        JButton btnAbandonner = new JButton("Abandonner");
        btnAbandonner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.ACCUEILPANEL);
                popAbandonner.dispose();
            }
        });
        popAbandonner.add(btnAbandonner);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popAbandonner.dispose();
            }
        });
        popAbandonner.add(btnAnnuler);

        popAbandonner.setVisible(true);
    }

}
