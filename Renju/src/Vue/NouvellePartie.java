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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class NouvellePartie extends JPanel {

    private JTextField txtFdj1;
    private JTextField txtFdj2;
    private ButtonGroup rbItemJ1;
    private ButtonGroup rbItemJ2;
    private ButtonGroup iAJ1;
    private ButtonGroup iAJ2;

    public NouvellePartie(Fenetre f) {

        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Sélection des joueurs");
        title.setForeground(Color.white); //Color(247, 128, 104)
        title.setFont(new Font("Calibri", Font.BOLD, 30));

        JPanel paneTout = new JPanel();
        paneTout.add(Box.createRigidArea(new Dimension(0, 50)));
        addComponent(title, paneTout);
        paneTout.setLayout(new BoxLayout(paneTout, BoxLayout.Y_AXIS));
        addComponent(paneTout, this);

        JPanel paneJoueurs = new JPanel();
        paneJoueurs.setLayout(new BoxLayout(paneJoueurs, BoxLayout.X_AXIS));
        addComponent(paneJoueurs, paneTout);

        JPanel paneJoueur1 = new JPanel();
        paneJoueur1.setLayout(new BoxLayout(paneJoueur1, BoxLayout.Y_AXIS));
        addComponent(paneJoueur1, paneJoueurs);

        JPanel paneJoueur2 = new JPanel();
        paneJoueur2.setOpaque(false);
        paneJoueur2.setLayout(new BoxLayout(paneJoueur2, BoxLayout.Y_AXIS));
        addComponent(paneJoueur2, paneJoueurs);

        //addComponent((JComponent) Box.createRigidArea(new Dimension(0, 200)), paneJoueur1);
        JLabel noir = new JLabel("Noir");
        noir.setForeground(Color.white);
        noir.setFont(new Font("Calibri", Font.BOLD, 25));
        addComponent(noir, paneJoueur1);
        //noir.setAlignmentX(RIGHT_ALIGNMENT);

        JPanel paneNomJoueur1 = new JPanel();
        addComponent(paneNomJoueur1, paneJoueur1);

        //JLabel nomJ1 = new JLabel("Nom : ");
        //addComponent(nomJ1, paneNomJoueur1);
        JTextField j1 = new JTextField("J1");
        addComponent(j1, paneNomJoueur1);
        j1.setPreferredSize(new Dimension(200, 30));
        j1.setHorizontalAlignment(JLabel.CENTER);
        j1.setFont(new Font("Calibri", Font.BOLD, 20));
        j1.setOpaque(true);
        txtFdj1 = j1;

        JPanel paneIaJ1 = new JPanel();
        ButtonGroup groupIaJ1 = new ButtonGroup();
        JRadioButton facileJ1 = new JRadioButton("Facile");
        facileJ1.setFont(new Font("Calibri", Font.BOLD, 20));
        facileJ1.setForeground(Color.white);
        facileJ1.setFocusPainted(false);
        groupIaJ1.add(facileJ1);
        addComponent(facileJ1, paneIaJ1);
        JRadioButton moyenJ1 = new JRadioButton("Moyen");
        moyenJ1.setFont(new Font("Calibri", Font.BOLD, 20));
        moyenJ1.setForeground(Color.white);
        moyenJ1.setFocusPainted(false);
        groupIaJ1.add(moyenJ1);
        addComponent(moyenJ1, paneIaJ1);
        JRadioButton difficileJ1 = new JRadioButton("Difficile");
        difficileJ1.setFont(new Font("Calibri", Font.BOLD, 20));
        difficileJ1.setForeground(Color.white);
        difficileJ1.setFocusPainted(false);
        groupIaJ1.add(difficileJ1);
        iAJ1 = groupIaJ1;
        addComponent(difficileJ1, paneIaJ1);
        facileJ1.setSelected(true);
        paneIaJ1.setVisible(false);

        ButtonGroup rbMenuItemJ1 = new ButtonGroup();
        JRadioButton rbJoueurJ1 = new JRadioButton("Joueur");
        rbJoueurJ1.setFont(new Font("Calibri", Font.BOLD, 20));
        rbJoueurJ1.setForeground(Color.white);
        rbJoueurJ1.setFocusPainted(false);
        rbJoueurJ1.setSelected(true);

        rbJoueurJ1.addActionListener(new SelectJoueurListener(0, paneIaJ1, f));

        JRadioButton rbOrdinateurJ1 = new JRadioButton("Ordinateur");
        rbOrdinateurJ1.setFont(new Font("Calibri", Font.BOLD, 20));
        rbOrdinateurJ1.setForeground(Color.white);
        rbOrdinateurJ1.setFocusPainted(false);
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
        blanc.setForeground(Color.white);
        blanc.setFont(new Font("Calibri", Font.BOLD, 25));
        addComponent(blanc, paneJoueur2);
        //noir.setAlignmentX(LEFT_ALIGNMENT);

        JPanel paneNomJoueur2 = new JPanel();
        addComponent(paneNomJoueur2, paneJoueur2);

        //JLabel nomJ2 = new JLabel("Nom : ");
        //addComponent(nomJ2, paneNomJoueur2);
        JTextField j2 = new JTextField("J2");
        addComponent(j2, paneNomJoueur2);
        j2.setPreferredSize(new Dimension(200, 30));
        j2.setHorizontalAlignment(JLabel.CENTER);
        j2.setFont(new Font("Calibri", Font.BOLD, 20));
        j2.setOpaque(true);
        txtFdj2 = j2;

        JPanel paneIaJ2 = new JPanel();
        ButtonGroup groupIaJ2 = new ButtonGroup();
        JRadioButton facileJ2 = new JRadioButton("Facile");
        facileJ2.setFont(new Font("Calibri", Font.BOLD, 20));
        facileJ2.setForeground(Color.white);
        facileJ2.setFocusPainted(false);
        groupIaJ2.add(facileJ2);
        addComponent(facileJ2, paneIaJ2);
        JRadioButton moyenJ2 = new JRadioButton("Moyen");
        moyenJ2.setFont(new Font("Calibri", Font.BOLD, 20));
        moyenJ2.setForeground(Color.white);
        moyenJ2.setFocusPainted(false);
        groupIaJ2.add(moyenJ2);
        addComponent(moyenJ2, paneIaJ2);
        JRadioButton difficileJ2 = new JRadioButton("Difficile");
        difficileJ2.setFont(new Font("Calibri", Font.BOLD, 20));
        difficileJ2.setForeground(Color.white);
        difficileJ2.setFocusPainted(false);
        groupIaJ2.add(difficileJ2);
        iAJ2 = groupIaJ2;
        addComponent(difficileJ2, paneIaJ2);
        facileJ2.setSelected(true);

        ButtonGroup rbMenuItemJ2 = new ButtonGroup();
        JRadioButton rbJoueurJ2 = new JRadioButton("Joueur");
        rbJoueurJ2.setFont(new Font("Calibri", Font.BOLD, 20));
        rbJoueurJ2.setForeground(Color.white);
        rbJoueurJ2.setFocusPainted(false);
        rbJoueurJ2.addActionListener(new SelectJoueurListener(0, paneIaJ2, f));
        JRadioButton rbOrdinateurJ2 = new JRadioButton("Ordinateur");
        rbOrdinateurJ2.setFont(new Font("Calibri", Font.BOLD, 20));
        rbOrdinateurJ2.setForeground(Color.white);
        rbOrdinateurJ2.setFocusPainted(false);
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
        addComponent(paneBoutons, paneTout);
        JButton btnOk = new JButton("Ok");
        addButton(btnOk, paneBoutons);
        //  btnOk.addActionListener(new SelectJoueurListener(getSelectedButtonText(rbMenuItemJ1), getSelectedButtonText(rbMenuItemJ2), j1.getText(), j2.getText(), getSelectedButtonText(groupIaJ1), getSelectedButtonText(groupIaJ2), 2));
        btnOk.addActionListener(new SelectJoueurListener(this, 2, f));
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new AnnulerVersAccueilListener(f));
        paneBoutons.add(Box.createRigidArea(new Dimension(150, 0)));
        addButton(btnRetour, paneBoutons);

        paneTout.add(Box.createRigidArea(new Dimension(0, 50)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension d = getParent().getSize();
        try {
            BufferedImage img = ImageIO.read(new File("../Ressources/acceuil.jpg"));
            g.drawImage(img, 0, 0, d.width, d.height, null);
        } catch (IOException ex) {
            Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, d.width, d.height);
        }
    }

    private void addButton(final JButton button, Container pane) {
        button.setFocusPainted(false);
        button.setBackground(new Color(247, 128, 104));
        button.setForeground(Color.white);
        button.setBorderPainted(false);
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
        pane.add(button);
    }

    private void addComponent(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setOpaque(false);
        this.add(component);
    }

    private void addComponent(JComponent component, Container pane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setOpaque(false);
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
