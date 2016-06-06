package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.*;
import Listener.AccueilListener;
import Modele.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Accueil extends JPanel {

    public Accueil(final Fenetre f) {
        final Popups popup = new Popups(f);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(Box.createVerticalGlue());
        JButton btnNouvellePartie = new JButton("Démarrer une partie");
        btnNouvellePartie.setOpaque(true);
        btnNouvellePartie.addActionListener(new AccueilListener(f, 1));
        addButton(btnNouvellePartie);

        JButton btnChargerPartie = new JButton("Charger une partie");
        btnChargerPartie.setOpaque(true);
        btnChargerPartie.addActionListener(new AccueilListener(f, 2));
        addButton(btnChargerPartie);

        JButton btnAideJeu = new JButton("Consulter les règles");
        btnAideJeu.setOpaque(true);
        btnAideJeu.addActionListener(new AccueilListener(popup, 3));
        addButton(btnAideJeu);

        JButton btnQuitter = new JButton("Quitter le jeu");
        btnQuitter.setOpaque(true);
        btnQuitter.addActionListener(new AccueilListener(popup, 4));
        addButton(btnQuitter);

        this.add(Box.createRigidArea(new Dimension(0, 100)));
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
        button.setOpaque(true);
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
