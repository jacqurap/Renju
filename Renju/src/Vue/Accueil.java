package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.*;
import Modele.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JPanel {

    public Accueil(final Fenetre f) {

        final Popups popup = new Popups(f);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btnNouvellePartie = new JButton("Démarrer une partie");
        btnNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.NOUVPARTIEPANEL);
            }
        });
        addButton(btnNouvellePartie);

        JButton btnChargerPartie = new JButton("Charger une partie");
        btnChargerPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.CHARPARTIEPANEL);
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

    }

    private void addButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(button);
    }

}
