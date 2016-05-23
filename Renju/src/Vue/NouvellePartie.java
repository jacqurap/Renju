package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.Partie;
import Modele.Humain;
import Modele.Plateau;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NouvellePartie extends JPanel {


    public NouvellePartie(final Fenetre f) {

        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sélection des joueurs");
        addComponent(title);

        JPanel paneJoueurs = new JPanel();
        paneJoueurs.setLayout(new BoxLayout(paneJoueurs, BoxLayout.X_AXIS));
        addComponent(paneJoueurs);

        JPanel paneJoueur1 = new JPanel();
        paneJoueur1.setLayout(new BoxLayout(paneJoueur1, BoxLayout.Y_AXIS));
        addComponent(paneJoueur1, paneJoueurs);

        JPanel paneJoueur2 = new JPanel();
        paneJoueur2.setLayout(new BoxLayout(paneJoueur2, BoxLayout.Y_AXIS));
        addComponent(paneJoueur2, paneJoueurs);

        addComponent((JComponent)Box.createRigidArea(new Dimension(0,200)),paneJoueur1);
        JLabel noir = new JLabel("Noir");
        addComponent(noir, paneJoueur1);

        JPanel paneNomJoueur1 = new JPanel();
        //paneJoueur1.setLayout(new BoxLayout(paneNomJoueur1, BoxLayout.X_AXIS));
        addComponent(paneNomJoueur1, paneJoueur1);

        JLabel nomJ1 = new JLabel("Nom : ");
        addComponent(nomJ1, paneNomJoueur1);
        JTextField j1 = new JTextField("J1",10);
        addComponent(j1, paneNomJoueur1);

        ButtonGroup rbMenuItemJ1 = new ButtonGroup();
        JRadioButton rbJoueurJ1 = new JRadioButton("Joueur");
        JRadioButton rbOrdinateurJ1 = new JRadioButton("Ordinateur");
        rbMenuItemJ1.add(rbJoueurJ1);
        rbMenuItemJ1.add(rbOrdinateurJ1);

        JPanel paneSelJoueur1 = new JPanel();
        //paneSelJoueur1.setLayout(new BoxLayout(paneSelJoueur1, BoxLayout.X_AXIS));
        addComponent(paneSelJoueur1, paneJoueur1);

        addComponent(rbJoueurJ1, paneSelJoueur1);
        addComponent(rbOrdinateurJ1, paneSelJoueur1);

        addComponent((JComponent)Box.createRigidArea(new Dimension(0,200)),paneJoueur2);
        JLabel blanc = new JLabel("Blanc");
        addComponent(blanc, paneJoueur2);

        JPanel paneNomJoueur2 = new JPanel();
        //paneNomJoueur2.setLayout(new BoxLayout(paneNomJoueur2, BoxLayout.X_AXIS));
        addComponent(paneNomJoueur2, paneJoueur2);

        JLabel nomJ2 = new JLabel("Nom : ");
        addComponent(nomJ2, paneNomJoueur2);
        JTextField j2 = new JTextField("J2",10);
        addComponent(j2, paneNomJoueur2);

        ButtonGroup rbMenuItemJ2 = new ButtonGroup();
        JRadioButton rbJoueurJ2 = new JRadioButton("Joueur");
        JRadioButton rbOrdinateurJ2 = new JRadioButton("Ordinateur");
        rbMenuItemJ2.add(rbJoueurJ2);
        rbMenuItemJ2.add(rbOrdinateurJ2);

        JPanel paneSelJoueur2 = new JPanel();
        //paneSelJoueur2.setLayout(new BoxLayout(paneSelJoueur2, BoxLayout.X_AXIS));
        addComponent(paneSelJoueur2, paneJoueur2);

        addComponent(rbJoueurJ2, paneSelJoueur2);
        addComponent(rbOrdinateurJ2, paneSelJoueur2);

        
        JPanel paneBoutons = new JPanel();
        paneBoutons.setLayout(new BoxLayout(paneBoutons, BoxLayout.X_AXIS));
        addComponent(paneBoutons);
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.INTERFACEPANEL);
            }
        });
        addComponent(btnOk,paneBoutons);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.ACCUEILPANEL);
            }
        });
        addComponent(btnRetour,paneBoutons);

    }

    private void addComponent(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
    }

    private void addComponent(JComponent component, Container pane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(component);
    }

}
