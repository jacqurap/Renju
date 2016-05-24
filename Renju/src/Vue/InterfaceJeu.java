package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.Partie;
import Modele.Humain;
import Modele.Plateau;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceJeu extends JPanel {

    private static final JMenuItem ITEMANNU = new JMenuItem("Annuler");
    private static final JMenuItem ITEMREFA = new JMenuItem("Refaire");
    private static final JButton btnAnnuler = new JButton("Annuler");
    private static final JButton btnRefaire = new JButton("Refaire");
    public InterfaceJeu( Fenetre f, final AireDeJeu aire) {
        this.setLayout(new BorderLayout());
        final Popups popup = new Popups(f);

        // Menu Partie
        JMenu menu_partie = new JMenu("Partie");

        JMenuItem item_sauv = new JMenuItem("Sauvegarder");
        item_sauv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popSauver();
            }
        });
        menu_partie.add(item_sauv);

        JMenuItem item_char = new JMenuItem("Charger");
        item_char.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popCharger();
            }
        });
        menu_partie.add(item_char);

        menu_partie.addSeparator();
        ITEMANNU.setEnabled(false); //non visible de base
        menu_partie.add(ITEMANNU);

        ITEMREFA.setEnabled(false);
        menu_partie.add(ITEMREFA);

        menu_partie.addSeparator();
        JMenuItem item_reco = new JMenuItem("Recommencer");
        item_reco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popRecommencer();
            }
        });
        menu_partie.add(item_reco);

        JMenuItem item_aban = new JMenuItem("Abandonner");
        item_aban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popAbandonner();
            }
        });
        menu_partie.add(item_aban);

        // Menu Parametres
        JMenu menu_param = new JMenu("Paramètres");

        // liste de Thèmes
        JMenu menu_them = new JMenu("Thème du plateau");
        menu_param.add(menu_them);

        ButtonGroup grpTheme = new ButtonGroup();
        JRadioButtonMenuItem rdTrad = new JRadioButtonMenuItem("Traditionnel");
        menu_them.add(rdTrad);
        grpTheme.add(rdTrad);
        JRadioButtonMenuItem rdMarb = new JRadioButtonMenuItem("Marbre");
        menu_them.add(rdMarb);
        grpTheme.add(rdMarb);

        // Liste de Langues
        JMenu menu_lang = new JMenu("Langue");
        menu_param.add(menu_lang);

        ButtonGroup grpLang = new ButtonGroup();
        JRadioButtonMenuItem rdFran = new JRadioButtonMenuItem("Français");
        menu_lang.add(rdFran);
        grpLang.add(rdFran);
        JRadioButtonMenuItem rdAngl = new JRadioButtonMenuItem("Anglais");
        menu_lang.add(rdAngl);
        grpLang.add(rdAngl);

        menu_param.addSeparator();

        // Liste de options d'affichage
        JCheckBoxMenuItem cbHist = new JCheckBoxMenuItem("Historique");
        menu_param.add(cbHist);
        JCheckBoxMenuItem cbNbCoupRest = new JCheckBoxMenuItem("Coups Restants");
        menu_param.add(cbNbCoupRest);
        JCheckBoxMenuItem cbNumCases = new JCheckBoxMenuItem("Numéros des cases");
        menu_param.add(cbNumCases);

        // Aide
        JMenu menu_aide = new JMenu("Aide");
        JMenuItem aideJeu = new JMenuItem("Aide de jeu");
        aideJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.popRegles();
            }
        });
        menu_aide.add(aideJeu);
        JMenuItem aPropos = new JMenuItem("A propos");
        menu_aide.add(aPropos);

        JMenuBar barre = new JMenuBar();
        barre.add(menu_partie);
        barre.add(menu_param);
        barre.add(menu_aide);
        this.add(barre, BorderLayout.NORTH);
        /*
         JPanel paneJoueurs = new JPanel();
         paneJoueurs.setLayout(new BorderLayout());
         addComponent(paneJoueurs);

         JPanel paneJoueur1 = new JPanel();
         paneJoueur1.setLayout(new BoxLayout(paneJoueur1, BoxLayout.Y_AXIS));
         addComponent(paneJoueur1, paneJoueurs);

         JPanel paneJoueur2 = new JPanel();
         paneJoueur2.setLayout(new BoxLayout(paneJoueur2, BoxLayout.Y_AXIS));
         addComponent(paneJoueur2, paneJoueurs);
         */
        JPanel paneJoueurs = new JPanel();
        paneJoueurs.setLayout(new GridLayout(0, 1));
        addComponent(paneJoueurs);

        JLabel tfJ1 = new JLabel("Joueur 1");
        JLabel tfJ2 = new JLabel("Joueur 2");
        paneJoueurs.add(tfJ1);
        paneJoueurs.add(tfJ2);

        
        addButton(btnAnnuler);
        btnAnnuler.setEnabled(false);
        
        addButton(btnRefaire);
        AnnulerAction annu = new AnnulerAction(aire, getITEMANNU(), getITEMREFA(),btnAnnuler,btnRefaire);
        RefaireAction ref = new RefaireAction(aire, getITEMANNU(), getITEMREFA(),btnAnnuler,btnRefaire);
        btnAnnuler.addActionListener(annu);
        ITEMANNU.addActionListener(annu);
        ITEMREFA.addActionListener(ref);
        btnRefaire.addActionListener(ref);
        btnRefaire.setEnabled(false);
        paneJoueurs.add(btnAnnuler);
        paneJoueurs.add(btnRefaire);

        this.add(paneJoueurs, BorderLayout.EAST);
        this.add(aire, BorderLayout.CENTER);

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

    private void addButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(button);
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
    public static JButton getButAnnuler(){
    	return btnAnnuler;
    }
    public static JButton getButRefaire(){
    	return btnRefaire;
    }

}
