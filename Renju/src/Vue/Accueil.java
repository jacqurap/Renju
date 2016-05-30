package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.*;
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
        btnNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(f.getNOUVPARTIEPANEL());
            }
        });
        addButton(btnNouvellePartie);

        JButton btnChargerPartie = new JButton("Charger une partie");
        btnChargerPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(f.getCHARPARTIEPANEL());
            }
        });
        addButton(btnChargerPartie);

        JButton btnAideJeu = new JButton("Consulter les règles");
        btnAideJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popRegles();
            }
        });
        addButton(btnAideJeu);

        JButton btnQuitter = new JButton("Quitter le jeu");
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popQuitterMenu();
            }
        });
        addButton(btnQuitter);

        this.add(Box.createRigidArea(new Dimension(0, 100)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension d = getParent().getSize();
        try {
            BufferedImage img = ImageIO.read(new File("Ressources/acceuil.jpg"));
            g.drawImage(img, 0, 0, d.width, d.height, null);
        } catch (IOException ex) {
            Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, d.width, d.height);
        }
    }

    private void addButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(247,128,104));
        button.setForeground(Color.white);
        button.setFont(new Font("Big Noodle Titling", Font.BOLD, 25));
        this.add(button);
        this.add(Box.createVerticalGlue());
    }

}
