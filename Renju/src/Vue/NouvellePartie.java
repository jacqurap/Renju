package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.Partie;
import Listener.AnnulerVersAccueilListener;
import Listener.SelectJoueurListener;
import Modele.Humain;
import Modele.Plateau;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class NouvellePartie extends JPanel {

    private JTextField txtFdj1;
    private JTextField txtFdj2;
    private ButtonGroup rbItemJ1;
    private ButtonGroup rbItemJ2;
    private ButtonGroup iAJ1;
    private ButtonGroup iAJ2;

    public NouvellePartie(Fenetre f) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sélection des joueurs");
        addComponent(title);

        JPanel paneJoueurs = new JPanel();
        paneJoueurs.setLayout(new BoxLayout(paneJoueurs, BoxLayout.X_AXIS));
        addComponent(paneJoueurs, this);

        JPanel paneJoueur1 = new JPanel();
        paneJoueur1.setLayout(new BoxLayout(paneJoueur1, BoxLayout.Y_AXIS));
        addComponent(paneJoueur1, paneJoueurs);

        JPanel paneJoueur2 = new JPanel();
        paneJoueur2.setLayout(new BoxLayout(paneJoueur2, BoxLayout.Y_AXIS));
        addComponent(paneJoueur2, paneJoueurs);

        //addComponent((JComponent) Box.createRigidArea(new Dimension(0, 200)), paneJoueur1);
        JLabel noir = new JLabel("Noir");

        addComponent(noir, paneJoueur1);

        JPanel paneNomJoueur1 = new JPanel();
        addComponent(paneNomJoueur1, paneJoueur1);

        JLabel nomJ1 = new JLabel("Nom : ");
        addComponent(nomJ1, paneNomJoueur1);
        JTextField j1 = new JTextField("J1", 10);
        addComponent(j1, paneNomJoueur1);
        txtFdj1 = j1;

        JPanel paneIaJ1 = new JPanel();
        ButtonGroup groupIaJ1 = new ButtonGroup();
        JRadioButton facileJ1 = new JRadioButton("Facile");
        groupIaJ1.add(facileJ1);
        addComponent(facileJ1, paneIaJ1);
        JRadioButton moyenJ1 = new JRadioButton("Moyen");
        groupIaJ1.add(moyenJ1);
        addComponent(moyenJ1, paneIaJ1);
        JRadioButton difficileJ1 = new JRadioButton("Difficile");
        groupIaJ1.add(difficileJ1);
        iAJ1 = groupIaJ1;
        addComponent(difficileJ1, paneIaJ1);
        facileJ1.setSelected(true);
        paneIaJ1.setVisible(false);

        ButtonGroup rbMenuItemJ1 = new ButtonGroup();
        JRadioButton rbJoueurJ1 = new JRadioButton("Joueur");
        rbJoueurJ1.setSelected(true);

        rbJoueurJ1.addActionListener(new SelectJoueurListener(0, paneIaJ1, f));

        JRadioButton rbOrdinateurJ1 = new JRadioButton("Ordinateur");

        rbOrdinateurJ1.addActionListener(new SelectJoueurListener(1, paneIaJ1, f));

        rbMenuItemJ1.add(rbJoueurJ1);
        rbMenuItemJ1.add(rbOrdinateurJ1);
        rbItemJ1 = rbMenuItemJ1;

        JPanel paneSelJoueur1 = new JPanel();
        addComponent(paneSelJoueur1, paneJoueur1);
        addComponent(paneIaJ1, paneJoueur1);

        addComponent(rbJoueurJ1, paneSelJoueur1);
        addComponent(rbOrdinateurJ1, paneSelJoueur1);

        //addComponent((JComponent) Box.createRigidArea(new Dimension(0, 200)), paneJoueur2);
        JLabel blanc = new JLabel("Blanc");
        addComponent(blanc, paneJoueur2);

        JPanel paneNomJoueur2 = new JPanel();
        addComponent(paneNomJoueur2, paneJoueur2);

        JLabel nomJ2 = new JLabel("Nom : ");
        addComponent(nomJ2, paneNomJoueur2);
        JTextField j2 = new JTextField("J2", 10);
        addComponent(j2, paneNomJoueur2);
        txtFdj2 = j2;

        JPanel paneIaJ2 = new JPanel();
        ButtonGroup groupIaJ2 = new ButtonGroup();
        JRadioButton facileJ2 = new JRadioButton("Facile");
        groupIaJ2.add(facileJ2);
        addComponent(facileJ2, paneIaJ2);
        JRadioButton moyenJ2 = new JRadioButton("Moyen");
        groupIaJ2.add(moyenJ2);
        addComponent(moyenJ2, paneIaJ2);
        JRadioButton difficileJ2 = new JRadioButton("Difficile");
        groupIaJ2.add(difficileJ2);
        iAJ2 = groupIaJ2;
        addComponent(difficileJ2, paneIaJ2);
        facileJ2.setSelected(true);

        ButtonGroup rbMenuItemJ2 = new ButtonGroup();
        JRadioButton rbJoueurJ2 = new JRadioButton("Joueur");
        rbJoueurJ2.addActionListener(new SelectJoueurListener(0, paneIaJ2, f));
        JRadioButton rbOrdinateurJ2 = new JRadioButton("Ordinateur");
        rbOrdinateurJ2.addActionListener(new SelectJoueurListener(1, paneIaJ2, f));
        rbOrdinateurJ2.setSelected(true);
        rbMenuItemJ2.add(rbJoueurJ2);
        rbMenuItemJ2.add(rbOrdinateurJ2);
        rbItemJ2 = rbMenuItemJ2;

        JPanel paneSelJoueur2 = new JPanel();
        addComponent(paneSelJoueur2, paneJoueur2);
        addComponent(paneIaJ2, paneJoueur2);

        addComponent(rbJoueurJ2, paneSelJoueur2);
        addComponent(rbOrdinateurJ2, paneSelJoueur2);

        JPanel paneBoutons = new JPanel();
        paneBoutons.setLayout(new BoxLayout(paneBoutons, BoxLayout.X_AXIS));
        addComponent(paneBoutons, paneJoueurs);
        JButton btnOk = new JButton("Ok");
        addComponent(btnOk, paneBoutons);
        //  btnOk.addActionListener(new SelectJoueurListener(getSelectedButtonText(rbMenuItemJ1), getSelectedButtonText(rbMenuItemJ2), j1.getText(), j2.getText(), getSelectedButtonText(groupIaJ1), getSelectedButtonText(groupIaJ2), 2));
        btnOk.addActionListener(new SelectJoueurListener(this, 2, f));
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new AnnulerVersAccueilListener(f));
        /* btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.changePanel(Fenetre.ACCUEILPANEL);
            }
        });*/

 /*
        noir.setFont(font);
        blanc.setFont(font);
        nomJ1.setFont(font);
        nomJ2.setFont(font);
        rbJoueurJ1.setFont(font);
        rbOrdinateurJ1.setFont(font);
        rbJoueurJ2.setFont(font);
        rbOrdinateurJ2.setFont(font);
         */
        addComponent(btnRetour, paneBoutons);
        /*
        fNouvPartie.pack();
        fNouvPartie.setLocationRelativeTo(null);
        fNouvPartie.setVisible(true);
         */

    }

    private void addComponent(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
    }

    private void addComponent(JComponent component, Container pane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(component);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    /**
     * @return the txtFdj1
     */
    public JTextField getTxtFdj1() {
        return txtFdj1;
    }

    /**
     * @param txtFdj1 the txtFdj1 to set
     */
    public void setTxtFdj1(JTextField txtFdj1) {
        this.txtFdj1 = txtFdj1;
    }

    /**
     * @return the txtFdj2
     */
    public JTextField getTxtFdj2() {
        return txtFdj2;
    }

    /**
     * @param txtFdj2 the txtFdj2 to set
     */
    public void setTxtFdj2(JTextField txtFdj2) {
        this.txtFdj2 = txtFdj2;
    }

    /**
     * @return the rbItemJ1
     */
    public ButtonGroup getRbItemJ1() {
        return rbItemJ1;
    }

    /**
     * @param rbItemJ1 the rbItemJ1 to set
     */
    public void setRbItemJ1(ButtonGroup rbItemJ1) {
        this.rbItemJ1 = rbItemJ1;
    }

    /**
     * @return the rbItemJ2
     */
    public ButtonGroup getRbItemJ2() {
        return rbItemJ2;
    }

    /**
     * @param rbItemJ2 the rbItemJ2 to set
     */
    public void setRbItemJ2(ButtonGroup rbItemJ2) {
        this.rbItemJ2 = rbItemJ2;
    }

    /**
     * @return the iAJ1
     */
    public ButtonGroup getiAJ1() {
        return iAJ1;
    }

    /**
     * @param iAJ1 the iAJ1 to set
     */
    public void setiAJ1(ButtonGroup iAJ1) {
        this.iAJ1 = iAJ1;
    }

    /**
     * @return the iAJ2
     */
    public ButtonGroup getiAJ2() {
        return iAJ2;
    }

    /**
     * @param iAJ2 the iAJ2 to set
     */
    public void setiAJ2(ButtonGroup iAJ2) {
        this.iAJ2 = iAJ2;
    }

}
