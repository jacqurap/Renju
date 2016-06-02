package Vue;

import Listener.AnnulerAction;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controleur.Partie;
import Listener.InterfaceJeuListener;
import Listener.RefaireAction;
import Modele.Humain;
import Modele.Plateau;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterfaceJeu extends JPanel {

    private static final JMenuItem ITEMANNU = new JMenuItem("Annuler");
    private static final JMenuItem ITEMREFA = new JMenuItem("Refaire");
    private static final JButton btnAnnuler = new JButton("Annuler");
    private static final JButton btnRefaire = new JButton("Refaire");
    private AireDeJeu aire;
    private JLabel tfJ1;
    private JLabel tfJ2;
    private JLabel coupRestJ1;
    private JLabel coupRestJ2;
    private JPanel paneJoueurs;

    public InterfaceJeu(Fenetre f, AireDeJeu aire) {
        this.aire = aire;
        this.setLayout(new BorderLayout());
        final Popups popup = new Popups(f);

        // Menu Partie
        JMenu menu_partie = new JMenu("Partie");

        JMenuItem item_sauv = new JMenuItem("Sauvegarder");
        item_sauv.addActionListener(new InterfaceJeuListener(popup, this, 1));
        menu_partie.add(item_sauv);

        JMenuItem item_char = new JMenuItem("Charger");
        item_char.addActionListener(new InterfaceJeuListener(popup, this, 2));
        menu_partie.add(item_char);

        menu_partie.addSeparator();
        ITEMANNU.setEnabled(false); //non visible de base
        menu_partie.add(ITEMANNU);

        ITEMREFA.setEnabled(false);
        menu_partie.add(ITEMREFA);

        menu_partie.addSeparator();
        JMenuItem item_reco = new JMenuItem("Recommencer");
        item_reco.addActionListener(new InterfaceJeuListener(popup, 3));
        menu_partie.add(item_reco);

        JMenuItem item_aban = new JMenuItem("Abandonner");
        item_aban.addActionListener(new InterfaceJeuListener(popup, 4));
        menu_partie.add(item_aban);

        // Menu Parametres
        JMenu menu_param = new JMenu("Paramètres");

        // liste de Th�mes
        JMenu menu_them = new JMenu("Thème du plateau");
        menu_param.add(menu_them);

        ButtonGroup grpTheme = new ButtonGroup();
        JRadioButtonMenuItem rdTrad = new JRadioButtonMenuItem("Bois");
        menu_them.add(rdTrad);
        rdTrad.addActionListener(new InterfaceJeuListener(6, this, "Bois"));
        rdTrad.setSelected(true);
        grpTheme.add(rdTrad);
        JRadioButtonMenuItem rdMarb = new JRadioButtonMenuItem("Marbre");
        menu_them.add(rdMarb);
        rdMarb.addActionListener(new InterfaceJeuListener(6, this, "Marbre"));
        grpTheme.add(rdMarb);
        JRadioButtonMenuItem rdMarin = new JRadioButtonMenuItem("Marin");
        menu_them.add(rdMarin);
        rdMarin.addActionListener(new InterfaceJeuListener(6, this, "Marin"));
        grpTheme.add(rdMarin);

        // Liste de Langues
        JMenu menu_lang = new JMenu("Langue");
        menu_param.add(menu_lang);

        ButtonGroup grpLang = new ButtonGroup();
        JRadioButtonMenuItem rdFran = new JRadioButtonMenuItem("Français");
        rdFran.setSelected(true);
        menu_lang.add(rdFran);
        grpLang.add(rdFran);
        JRadioButtonMenuItem rdAngl = new JRadioButtonMenuItem("Anglais");
        menu_lang.add(rdAngl);
        grpLang.add(rdAngl);

        menu_param.addSeparator();

        // Liste de options d'affichage
        JCheckBoxMenuItem cbHist = new JCheckBoxMenuItem("Historique");
        cbHist.setSelected(true);
        cbHist.addActionListener(new InterfaceJeuListener(7, this, cbHist));
        menu_param.add(cbHist);
        JCheckBoxMenuItem cbNbCoupRest = new JCheckBoxMenuItem("Coups Restants");
        cbNbCoupRest.addActionListener(new InterfaceJeuListener(9, this, cbNbCoupRest));
        menu_param.add(cbNbCoupRest);
        JCheckBoxMenuItem cbNumCases = new JCheckBoxMenuItem("Numéros des cases");
        cbNumCases.setSelected(true);
        cbNumCases.addActionListener(new InterfaceJeuListener(8, this, cbNumCases));
        menu_param.add(cbNumCases);

        // Aide
        JMenu menu_aide = new JMenu("Aide");
        JMenuItem aideJeu = new JMenuItem("Aide de jeu");
        aideJeu.addActionListener(new InterfaceJeuListener(popup, 5));
        menu_aide.add(aideJeu);
        JMenuItem aPropos = new JMenuItem("A propos");
        aPropos.addActionListener(new InterfaceJeuListener(popup, 10));
        menu_aide.add(aPropos);

        JMenuBar barre = new JMenuBar();
        barre.add(menu_partie);
        barre.add(menu_param);
        barre.add(menu_aide);
        this.add(barre, BorderLayout.NORTH);

        //dans la fenêtre
        paneJoueurs = new JPanel(); //éléments autres que l'aire de jeu
        paneJoueurs.setOpaque(false);
        paneJoueurs.setLayout(new GridLayout(3, 1));
        //addComponent(paneJoueurs);

        ImageIcon icone1 = createImageIcon("../Ressources/Pion_Noir.png", "Pion noir du joueur 1");
        ImageIcon icone2 = createImageIcon("../Ressources/Pion_Blanc.png", "Pion blanc du joueur 2");
        coupRestJ1 = new JLabel("", JLabel.CENTER);
        coupRestJ2 = new JLabel("", JLabel.CENTER);

        Font font = new Font("Calibri", Font.BOLD, 25);
        tfJ1 = new JLabel("", (ImageIcon) icone1, JLabel.CENTER);
        tfJ1.setFont(font);
        tfJ2 = new JLabel("", icone2, JLabel.CENTER);
        tfJ2.setFont(font);
        tfJ1.setForeground(Color.RED); // Subrillance de depart
        // tfJ1.add
        // SurbrillanceTour(tfJ1, tfJ2, aire);
        JPanel paneJ1 = new JPanel();
        paneJ1.setOpaque(false);
        paneJ1.setLayout(new GridLayout(4, 1));
        paneJ1.add(Box.createHorizontalGlue());
        paneJ1.add(tfJ1);
        paneJ1.add(coupRestJ1);
        paneJ1.add(Box.createHorizontalGlue());

        JPanel paneJ2 = new JPanel();
        paneJ2.setLayout(new GridLayout(4, 1));
        paneJ2.add(Box.createHorizontalGlue());
        paneJ2.setOpaque(false);
        paneJ2.add(tfJ2);
        paneJ2.add(coupRestJ2);
        paneJ2.add(Box.createHorizontalGlue());
        
        JPanel paneBoutons = new JPanel();
        paneBoutons.setOpaque(false);
        paneBoutons.setLayout(new FlowLayout());
        addButton(btnAnnuler, paneBoutons);
        btnAnnuler.setEnabled(false);
        addButton(btnRefaire, paneBoutons);
        AnnulerAction annu = new AnnulerAction(this.getAire(), getITEMANNU(), getITEMREFA(), btnAnnuler, btnRefaire);
        RefaireAction ref = new RefaireAction(this.getAire(), getITEMANNU(), getITEMREFA(), btnAnnuler, btnRefaire);
        btnAnnuler.addActionListener(annu);
        ITEMANNU.addActionListener(annu);
        ITEMREFA.addActionListener(ref);
        btnRefaire.addActionListener(ref);
        btnRefaire.setEnabled(false);
        paneJoueurs.add(paneBoutons);
        paneJoueurs.add(paneJ1);
        paneJoueurs.add(paneJ2);
        
        /*
        paneContentJoueurs = new JPanel(new GridLayout(1,3));
        paneContentJoueurs.add(Box.createHorizontalGlue());
        paneContentJoueurs.add(paneJoueurs);
        paneContentJoueurs.add(Box.createHorizontalGlue());

        */
        this.add(paneJoueurs, BorderLayout.EAST);
        this.add(this.aire, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension d = getParent().getSize();
        /*try {
            BufferedImage img = ImageIO.read(new File("../Ressources/fondpop.jpg"));
            g.drawImage(img, 0, 0, d.width, d.height, null);
        } catch (IOException ex) {
            Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);*/
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, d.width, d.height);
        //}
    }

    private void addComponent(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        //this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(component);
    }

    private void addComponent(JComponent component, Container pane) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(component);
    }

    private void addButton(final JButton button, Container pane) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Calibri", Font.BOLD, 25));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        //button.setForeground(Color.white);
        pane.add(button);
        pane.add(Box.createVerticalGlue());
    }

    /**
     * @return the ITEMANNU
     */
    public static JMenuItem getITEMANNU() {
        return ITEMANNU;
    }

    /**
     * @return the ITEMREFA
     */
    public static JMenuItem getITEMREFA() {
        return ITEMREFA;
    }

    public static JButton getButAnnuler() {
        return btnAnnuler;
    }

    public static JButton getButRefaire() {
        return btnRefaire;
    }

    /**
     * @return the aire
     */
    public AireDeJeu getAire() {
        return aire;
    }

    /**
     * @param aire the aire to set
     */
    public void setAire(AireDeJeu aire) {
        this.aire = aire;
    }

    /**
     * @return the tfJ1
     */
    public JLabel getTfJ1() {
        return tfJ1;
    }

    /**
     * @param tfJ1 the tfJ1 to set
     */
    public void setTfJ1(JLabel tfJ1) {
        this.tfJ1 = tfJ1;
    }

    /**
     * @return the tfJ2
     */
    public JLabel getTfJ2() {
        return tfJ2;
    }

    /**
     * @param tfJ2 the tfJ2 to set
     */
    public void setTfJ2(JLabel tfJ2) {
        this.tfJ2 = tfJ2;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /*  public void SurbrillanceTour(JLabel J1, JLabel J2, AireDeJeu aire){
    	if(aire.getPartie().getNbCoups() %2 == 0){
            J1.setForeground(Color.RED);
    	}
    	else{
    		 J2.setForeground(Color.RED);
    	}
    	
    }*/
    /**
     * @return the coupRestJ2
     */
    public JLabel getCoupRestJ2() {
        return coupRestJ2;
    }

    /**
     * @param coupRestJ2 the coupRestJ2 to set
     */
    public void setCoupRestJ2(JLabel coupRestJ2) {
        this.coupRestJ2 = coupRestJ2;
    }

    /**
     * @return the coupRestJ1
     */
    public JLabel getCoupRestJ1() {
        return coupRestJ1;
    }

    /**
     * @param coupRestJ1 the coupRestJ1 to set
     */
    public void setCoupRestJ1(JLabel coupRestJ1) {
        this.coupRestJ1 = coupRestJ1;
    }

    /**
     * @return the paneContentJoueurs
     */
    public JPanel getPaneContentJoueurs() {
        return paneJoueurs;
    }

    /**
     * @param paneContentJoueurs the paneContentJoueurs to set
     */
    public void setPaneContentJoueurs(JPanel paneContentJoueurs) {
        this.paneJoueurs = paneContentJoueurs;
    }
}
