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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author bochatom
 */
public class ChargerPartie extends JPanel {

    ButtonGroup grpPartie = new ButtonGroup();
    Fenetre fenetre;
    Accueil acc;

    public ChargerPartie(Fenetre f) {
        this.fenetre = f;
        this.acc = new Accueil(fenetre);
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
                this.addButton(rb);
                vide++;
            }
        }
        if (vide == 0) {
            //petite triche pour avoir le texte et le bouton retour centrés
            JButton btnVide = new JButton("Aucune sauvegarde disponible");
            this.addButton(btnVide);
            btnVide.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
            btnVide.setOpaque(false);
            btnVide.setForeground(Color.white);
        }
        JButton btnAnnuler = new JButton("Retour à l'accueil");
        btnAnnuler.addActionListener(new AnnulerVersAccueilListener(fenetre));
        this.addButton(btnAnnuler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension d = getParent().getSize();
        try {
            BufferedImage img = ImageIO.read(new File("src/Ressources/acceuil.jpg"));
            g.drawImage(img, 0, 0, d.width, d.height, null);
        } catch (IOException ex) {
            Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, d.width, d.height);
        }
    }

    private void addButton(final JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(new Color(247, 128, 104));
        button.setForeground(Color.white);
        button.setBorderPainted(false);
        button.setSelected(false);
        button.setFont(new Font("Calibri", Font.BOLD, 25));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(252, 148, 119));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(247, 128, 104));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.add(button);
        this.add(Box.createVerticalGlue());
    }
}
