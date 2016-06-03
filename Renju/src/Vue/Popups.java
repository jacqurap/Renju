/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Partie;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.sun.org.apache.bcel.internal.generic.FMUL;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import Listener.PopupsListener;

/**
 *
 * @author bochatom
 */
public class Popups {

    public Fenetre f;
    public File[] slot = new File[9];
    public Partie part;
    public int SaveNum = 20;
    public String SaveName = "Sauvegarde";
    int index;
    boolean erase = false;
    public String SavePath;
    public static Color fond = new Color(56, 137, 129);

    public Popups(Fenetre f) {
        this.f = f;
    }

    public void popRegles() {

        // JDialog
        final JDialog popRegle = new JDialog();
        JDialog.setDefaultLookAndFeelDecorated(true);
        popRegle.setSize(800, 500);
       // popRegle.setMinimumSize(JFrame.MAXIMIZED_HORIZ & JFrame.MAXIMIZED_VERT);
        popRegle.setLocationRelativeTo(null);
        popRegle.setTitle("Aide de jeu");
        popRegle.setVisible(true);

        // JPanel, et paramrtrage
        JPanel panel = new JPanel();
        panel.setBackground(fond);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        popRegle.add(scrollPane);

        // JTextArea, et parametrage
        JTextArea partie1 = new JTextArea();
        partie1.setOpaque(false);
        partie1.setForeground(Color.white);
        partie1.setFont(new Font("Calibri", Font.BOLD, 15));
        partie1.setEditable(false);
        panel.add(partie1);

        // JTextArea: edition
        partie1.setText("I  - Déroulement d'une partie\n"
                + "\n"
                + "A - Présentation du jeu\n"
                + "\n"
                + "	Le Renju se joue à deux joueurs, sur un plateau quadrillé de 15 lignes par 15.\n"
                + "	Les deux joueurs ont 60 pions chacun, l'un jouant les noirs, l'autre les blancs.\n"
                + "	Dans tous les cas, le joueur possédant les pions noirs commence la partie.\n"
                + "\n"
                + "B - Déroulement d'une partie\n"
                + "\n"
                + "	A tour de rôle, chaque joueur place un pion sur une intersection du plateau.\n"
                + "	Le 1er coup noir est au millieu du plateau; le 1er coup blanc se joue sur l'une des 8 intersections\n"
                + "	adjacentes au pion noir; les coups suivants n'ont pas de restrictions quant à leur placement.\n"
                + "	Le premier joueur qui réussi à aligner 5 pions consécutifs horizontalement, verticalement ou en diagonale\n"
                + "	gagne la partie.\n"
                + "	Si tous les pions ont été posés sans q'un alignement de cinq pions ne soit effectué, la partie est considérée comme nulle.\n"
                + "\n"
                + "II - Les tabous\n"
                + "\n"
                + "	Une des particularités du Renju est que le joueur noir, parce qu'il commence la partie, a beaucoup plus de chances de gagner.\n"
                + "	Il a donc fallut imaginer des coups interdits aux noir, appelés tabous, pour rééquilibrer les chances de victoire.\n"
                + "\n"
                + "A - Terminologies liées aux tabous\n"
                + "\n"
                + "	- Un CINQ est un alignement de cinq pions connexes. Le gagnant est le premier joueur qui réussit à faire un CINQ.\n"
                + "\n");

        // Text 1
        JTextArea trois = new JTextArea();
        trois.setOpaque(false);
        trois.setForeground(Color.white);
        trois.setFont(new Font("Calibri", Font.BOLD, 15));
        trois.setEditable(false);
        panel.add(trois);

        trois.setText("\n\n	- Un TROIS est un alignement de 3 pions connexes (on parle de trois continu), ou avec un seul espace entre deux pions (on parle de trois discontinu),\n"
                + "	  qui menace de devenir un CINQ.\n\n");

        // image 1
        Icon imgTrois = new ImageIcon("src/Ressources/1_Trois.png");
        JLabel img1 = new JLabel();
        img1.setIcon(imgTrois);
        panel.add(img1);

        // Text 2
        JTextArea faux_trois = new JTextArea();
        faux_trois.setOpaque(false);
        faux_trois.setForeground(Color.white);
        faux_trois.setFont(new Font("Calibri", Font.BOLD, 15));
        faux_trois.setEditable(false);
        panel.add(faux_trois);

        faux_trois.setText("\n\n                            Si ces trois pions sont délà bloqués par un pion adverse sur un coté, ou dans un espace, ne sont pas TROIS.\n"
                + "	  On parle alors de FAUX-TROIS.\n"
                + "	  Remarque : On reconnaît un FAUX-TROIS si en jouant un pion de plus, on obtient pas un quatre libre\n"
                + "	\n\n");

        // image 2
        Icon imgFauxTrois = new ImageIcon("src/Ressources/2_Faux_Trois.png");
        JLabel img2 = new JLabel();
        img2.setIcon(imgFauxTrois);
        panel.add(img2);

        // Text 3
        JTextArea autre_faux_trois = new JTextArea();
        autre_faux_trois.setOpaque(false);
        autre_faux_trois.setForeground(Color.white);
        autre_faux_trois.setFont(new Font("Calibri", Font.BOLD, 15));
        autre_faux_trois.setEditable(false);
        panel.add(autre_faux_trois);

        autre_faux_trois.setText("\n");

        // image 3
        Icon imgAutreFauxTrois = new ImageIcon("src/Ressources/3_Autre_Faux_Trois.png");
        JLabel img3 = new JLabel();
        img3.setIcon(imgAutreFauxTrois);
        panel.add(img3);

        // Text 4
        JTextArea quatre = new JTextArea();
        quatre.setOpaque(false);
        quatre.setForeground(Color.white);
        quatre.setFont(new Font("Calibri", Font.BOLD, 15));
        quatre.setEditable(false);
        panel.add(quatre);

        quatre.setText("\n\n	- Un QUATRE est un alignement de 4 pions connexes (on parle de quatre libre), ou avec un seul espace entre deux pions (on parle de quatre discontinu),\n"
                + "	qui menace de devenir un CINQ.\n\n");

        // image 4
        Icon imgQuatre = new ImageIcon("src/Ressources/4_Quatre.png");
        JLabel img4 = new JLabel();
        img4.setIcon(imgQuatre);
        panel.add(img4);

        // Text 5
        JTextArea autre_quatre = new JTextArea();
        autre_quatre.setOpaque(false);
        autre_quatre.setForeground(Color.white);
        autre_quatre.setFont(new Font("Calibri", Font.BOLD, 15));
        autre_quatre.setEditable(false);
        panel.add(autre_quatre);

        autre_quatre.setText("\n");

        // image 5
        Icon imgAutreQuatre = new ImageIcon("src/Ressources/5_Autre_Quatre.png");
        JLabel img5 = new JLabel();
        img5.setIcon(imgAutreQuatre);
        panel.add(img5);

        // Text 6
        JTextArea trois_trois = new JTextArea();
        trois_trois.setOpaque(false);
        trois_trois.setForeground(Color.white);
        trois_trois.setFont(new Font("Calibri", Font.BOLD, 15));
        trois_trois.setEditable(false);
        panel.add(trois_trois);

        trois_trois.setText("\n\n	Comme dit précédemment, pour gagner la partie, il est nécessaire de faire un CINQ. Or pour en faire un, il faut d'abord faire un QUATRE.\n"
                + "	Et pour faire un QUATRE, il faut passer par un TROIS.\n"
                + "	En faisant un TROIS, puis un QUATRE, on ne peut pas gagner, puisque l'adversaire peut facilement bloquer la formation.\n"
                + "	Donc, pour gagner, il faut réaliser un coup comportant deux menaces.\n"
                + "\n"
                + "\n"
                + "B - Les Tabous\n"
                + "\n"
                + "	- On appel un TROIS-TROIS une configuration où deux TROIS sont réalisées de manière SIMULTANE par le MEME COUP.\n\n");

        // image 6
        Icon imgTroisTrois = new ImageIcon("src/Ressources/6_Trois_Trois.png");
        JLabel img6 = new JLabel();
        img6.setIcon(imgTroisTrois);
        panel.add(img6);

        // Text 7
        JTextArea faux_trois_trois = new JTextArea();
        faux_trois_trois.setOpaque(false);
        faux_trois_trois.setForeground(Color.white);
        faux_trois_trois.setFont(new Font("Calibri", Font.BOLD, 15));
        faux_trois_trois.setEditable(false);
        panel.add(faux_trois_trois);

        faux_trois_trois.setText("\n\n	- On appel un FAUX-TROIS-TROIS la même configuration, où l'un des deux alignements contient au moins un FAUX-TROIS.\n\n");

        // image 7
        Icon imgFauxTroisTrois = new ImageIcon("src/Ressources/7_Faux_Trois_Trois.png");
        JLabel img7 = new JLabel();
        img7.setIcon(imgFauxTroisTrois);
        panel.add(img7);

        // Text 8
        JTextArea quatre_trois = new JTextArea();
        quatre_trois.setOpaque(false);
        quatre_trois.setForeground(Color.white);
        quatre_trois.setFont(new Font("Calibri", Font.BOLD, 15));
        quatre_trois.setEditable(false);
        panel.add(quatre_trois);

        quatre_trois.setText("\n\n	- On appel un QUATRE-TROIS une configuration où un QUATRE et un TROIS sont réalisées de manière SIMULTANE par le MEME COUP.\n\n");

        // image 8
        Icon imgQuatreTrois = new ImageIcon("src/Ressources/8_Quatre_Trois.png");
        JLabel img8 = new JLabel();
        img8.setIcon(imgQuatreTrois);
        panel.add(img8);

        // Text 9
        JTextArea quatre_quatre = new JTextArea();
        quatre_quatre.setOpaque(false);
        quatre_quatre.setForeground(Color.white);
        quatre_quatre.setFont(new Font("Calibri", Font.BOLD, 15));
        quatre_quatre.setEditable(false);
        panel.add(quatre_quatre);

        quatre_quatre.setText("\n\n	- On appel un QUATRE-QUATRE une configuration où deux QUATRE sont réalisées de manière SIMULTANE par le MEME COUP.\n\n");

        // image 9
        Icon imgQuatreQuatre = new ImageIcon("src/Ressources/9_Quatre_Quatre.png");
        JLabel img9 = new JLabel();
        img9.setIcon(imgQuatreQuatre);
        panel.add(img9);

        /*
         // Text 10
         JTextArea autre_quatre_quatre = new JTextArea();
         autre_quatre_quatre.setOpaque(false);
         autre_quatre_quatre.setForeground(Color.white);
         autre_quatre_quatre.setFont(new Font("Calibri", Font.BOLD, 15));
         autre_quatre_quatre.setEditable(false);
         panel.add(autre_quatre_quatre);
        
         autre_quatre_quatre.setText("\n");

         // image 10
         Icon imgAutreQuatreQuatre = new ImageIcon("src/Ressources/10_Autre_Quatre_Quatre.png");
         JLabel img10 = new JLabel();
         img10.setIcon(imgAutreQuatreQuatre);
         panel.add(img10);
        
         */
        // Text 11
        JTextArea six_sept = new JTextArea();
        six_sept.setOpaque(false);
        six_sept.setForeground(Color.white);
        six_sept.setFont(new Font("Calibri", Font.BOLD, 15));
        six_sept.setEditable(false);
        panel.add(six_sept);

        six_sept.setText("\n\n	- Un SIX, SEPT, ... est un alignement de six, sept,... pions connexes. Ces configurations ne peuvent être obtenu qu'en un coup.\n\n");

        // image 11
        Icon imgSixSept = new ImageIcon("src/Ressources/11_Six_Et_Sept.png");
        JLabel img11 = new JLabel();
        img11.setIcon(imgSixSept);
        panel.add(img11);

        // Text 12
        JTextArea fin = new JTextArea();
        fin.setOpaque(false);
        fin.setForeground(Color.white);
        fin.setFont(new Font("Calibri", Font.BOLD, 15));
        fin.setEditable(false);
        panel.add(fin);

        fin.setText("\n\n	Le TROIS-TROIS, le QUATRE-QUATRE, et les SIX, SEPT, HUIT et NEUF sont des coups interdits au joueur noir.\n"
                + "	Le QUATRE-TROIS est donc le seul moyen pour le joueur noir de gagner la partie.\n"
                + "\n"
                + "\n\n");

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRegle.dispose();
            }
        });
        panel.add(btnRetour);

        partie1.setCaretPosition(0);

        panel.setVisible(true);
    }

    public void popQuitterMenu() {
        final JDialog popQuitterMenu = new JDialog();
        JPanel pane = new JPanel() {
            protected void paintComponent(Graphics g) {
                Dimension d = getParent().getSize();
                try {
                    BufferedImage img = ImageIO.read(new File("src/Ressources/fondpop.jpg"));
                    g.drawImage(img, 0, 0, d.width, d.height, null);
                } catch (IOException ex) {
                    Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
                    g.setColor(Color.BLUE);
                    g.fillRect(0, 0, d.width, d.height);
                }
            }
        };
        pane.setLayout(new FlowLayout());
        popQuitterMenu.setTitle("Quitter");

        JDialog.setDefaultLookAndFeelDecorated(true);

        
        popQuitterMenu.setLocationRelativeTo(null);
        popQuitterMenu.add(pane);
        pane.setLayout(new BorderLayout());

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new PopupsListener(popQuitterMenu, f, 1));

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popQuitterMenu));

        JPanel paneTxt = new JPanel();
        paneTxt.setOpaque(false);
        paneTxt.setLayout(new FlowLayout());
        JLabel txt = new JLabel("Voulez-vous vraiment quitter l'application ?");
        txt.setForeground(Color.white);
        txt.setFont(new Font("Calibri", Font.BOLD, 25));
        paneTxt.add(Box.createRigidArea(new Dimension(0, 60)));
        paneTxt.add(txt);
        paneTxt.add(Box.createRigidArea(new Dimension(0, 60)));
        pane.add(paneTxt, BorderLayout.NORTH);
        JPanel paneboutons = new JPanel();
        paneboutons.setLayout(new FlowLayout());
        paneboutons.setOpaque(false);
        paneboutons.add(Box.createHorizontalGlue());
        addButton(btnQuitter, paneboutons, popQuitterMenu);
        paneboutons.add(Box.createRigidArea(new Dimension(50, 0)));
        addButton(btnAnnuler, paneboutons, popQuitterMenu);
        paneboutons.add(Box.createHorizontalGlue());
        pane.add(paneboutons, BorderLayout.CENTER);
        popQuitterMenu.pack();
        popQuitterMenu.setVisible(true);
    }
    
    public void popAPropos() {

        final JDialog popAPropos = new JDialog();
        JPanel panelap = new JPanel() {
            protected void paintComponent(Graphics g) {
                Dimension d = getParent().getSize();
                try {
                    BufferedImage img = ImageIO.read(new File("src/Ressources/fondpop.jpg"));
                    g.drawImage(img, 0, 0, d.width, d.height, null);
                } catch (IOException ex) {
                    Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
                    g.setColor(Color.BLUE);
                    g.fillRect(0, 0, d.width, d.height);
                }
            }
        };
        panelap.setLayout(new BoxLayout(panelap, BoxLayout.Y_AXIS));
        JTextArea text = new JTextArea();
        text.setOpaque(false);
        text.setForeground(Color.white);
        text.setFont(new Font("Calibri", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(panelap,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelap.add(text);
        popAPropos.add(scrollPane);
        JDialog.setDefaultLookAndFeelDecorated(true);
        popAPropos.setTitle("A Propos");

        text.setText(
        		"Dans le cadre du projet de fin d'année au semestre 6 à l'IM²AG\n"
                + "Licence 3 Informatique\n"
                + "\n"
        		+"Ce logiciel a été construit par le groupe 4 (Renju) :\n"
                + "\n"
                + "Mathieu BOCHATON\n"
                + "Guillaume CHAPUT\n"
                + "Clément HERESAZ\n"
                + "Raphaël JACQUET\n"
                + "Julien NAVAILS\n"
                + "Benjamin ROHAUT\n"
                + "\n"
                + "Merci à tous les professeurs pour leur accompagnement et leurs conseils.\n"
                + "\n"
                + "© 2016 Groupe 4, IM²AG, Université Grenoble Alpes \n"
                );

        text.setEditable(false);
        text.setSelectionColor(Color.green);
        

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	popAPropos.dispose();
            }
        }
        );
        panelap.add(btnRetour);
        popAPropos.pack();
        popAPropos.setLocationRelativeTo(null);
        popAPropos.setVisible(true);
    }

    public void popCharger(Partie partie) {
        Sauvegarde();
        this.part = partie;
        final JDialog popCharger = new JDialog();
        JPanel paneCharger = new JPanel();
        JPanel paneSlots = new JPanel();
        JPanel paneText = new JPanel();
        JPanel paneBouton = new JPanel();
        		
        popCharger.setTitle("Charger");

        JDialog.setDefaultLookAndFeelDecorated(true);

        

        popCharger.setLocationRelativeTo(null);
        paneCharger.setLayout(new BoxLayout(paneCharger, BoxLayout.Y_AXIS));
        paneSlots.setLayout(new FlowLayout());
        
        paneCharger.setBackground(fond);
       // paneCharger.setForeground(Color.white);
        //paneCharger.setFont(new Font("Calibri", Font.BOLD, 15));
        
        JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener(new PopupsListener(popCharger, f, this, 2));
        btnCharger.setEnabled(false);

        JLabel warning = new JLabel("Attention, en chargeant une partie, la partie courante sera perdue !");
        
        JScrollPane scrollPane = new JScrollPane(paneCharger,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        warning.setVisible(true);
        
        popCharger.add(scrollPane);

        ButtonGroup grpSlot = new ButtonGroup();
        for (index = 1; index < 10; index++) {
            String val;
            boolean ready = true;
            if (slot[index - 1] == null) {
                val = (String.valueOf(index) + "e Slot ");
                ready = false;
            } else {
                val = (index + slot[index - 1].toString().substring(SavePath.length() + 1, (int) slot[index - 1].toString().length() - 4));
            }
            JRadioButton slot = new JRadioButton(val);
            slot.setEnabled(ready);
            slot.addActionListener(new PopupsListener(btnCharger, this, 3));
            slot.addActionListener(new PopupsListener(warning, this, 9));
            slot.setOpaque(false);
            slot.setForeground(Color.white);
            slot.setFont(new Font("Calibri", Font.BOLD, 15));
            grpSlot.add(slot); 
            paneSlots.add(slot);
           //popCharger.add(slot);
        }

        //popCharger.add(warning);

       /// popCharger.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popCharger));
       // popCharger.add(btnAnnuler);
        
        paneBouton.add(btnCharger);
        paneBouton.add(btnAnnuler);
        
        warning.setForeground(Color.white);
        warning.setFont(new Font("Calibri", Font.BOLD, 15));
        
        paneText.add(warning);
        
        
        
        paneCharger.add(paneSlots);
        paneCharger.add(paneText);
        paneCharger.add(paneBouton);
       
        paneBouton.setOpaque(false);
        paneSlots.setOpaque(false);
        paneText.setOpaque(false);
        
        popCharger.setMaximumSize(new Dimension(600, 600));
        
        popCharger.pack();
        popCharger.setVisible(true);
    }

    public void popSauver(Partie partie) {
        TrouverChemin();
        Sauvegarde();
        this.part = partie;
        JDialog popSauver = new JDialog();
        JPanel paneSauver = new JPanel();
        JPanel paneBoutonRadio = new JPanel();
        JPanel paneTexteChamp = new JPanel();
        JPanel paneBouton = new JPanel();	
        JPanel paneavertissement = new JPanel();
        
        popSauver.setTitle("Sauvegarder");

        JDialog.setDefaultLookAndFeelDecorated(true);

        

        popSauver.setLocationRelativeTo(null);
        
        paneSauver.setLayout(new BoxLayout(paneSauver, BoxLayout.Y_AXIS));
        paneBoutonRadio.setLayout(new FlowLayout());
        
        paneSauver.setBackground(fond);

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(new PopupsListener(popSauver, this, 8));
        btnSauvegarder.setEnabled(false);

        JLabel warning = new JLabel("Attention une partie est déjà sauvegardée sur ce slot, vous allez l'écraser !");
        warning.setVisible(false);
        warning.setForeground(Color.white);
        warning.setFont(new Font("Calibri", Font.BOLD, 15));

        ButtonGroup grpSlot = new ButtonGroup();
        for (index = 1; index < 10; index++) {
            String val;
            if (slot[index - 1] == null) {
                val = (String.valueOf(index) + "e Slot");
            } else {
                val = (index + slot[index - 1].toString().substring(SavePath.length() + 1, (int) slot[index - 1].toString().length() - 4));
            }
            JRadioButton slot = new JRadioButton(val);
            slot.setOpaque(false);
            slot.setForeground(Color.white);
            slot.setFont(new Font("Calibri", Font.BOLD, 15));
            slot.addActionListener(new PopupsListener(btnSauvegarder, this, 3));
            if (this.slot[index - 1] != null) {
                slot.addActionListener(new PopupsListener(warning, this, 4));
            } else {
                slot.addActionListener(new PopupsListener(warning, this, 5));
            }
            grpSlot.add(slot);
            
            paneBoutonRadio.add(slot);
            
        }

        JScrollPane scrollPane = new JScrollPane(paneSauver,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        popSauver.add(scrollPane);
        paneavertissement.add(warning);

        JLabel labelNom = new JLabel("Nom de la sauvegarde :");
        labelNom.setForeground(Color.white);
        labelNom.setFont(new Font("Calibri", Font.BOLD, 15));
        paneTexteChamp.add(labelNom);
        JTextField nom = new JTextField("Sauvegarde", 20);
        nom.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                SaveName = ((JTextField) e.getSource()).getText();
            }
        });
        paneTexteChamp.add(nom);
        paneBouton.add(btnSauvegarder);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popSauver));
        paneBouton.add(btnAnnuler);
        
        paneSauver.add(paneBoutonRadio);
        paneSauver.add(paneavertissement);
        paneSauver.add(paneTexteChamp);
        paneSauver.add(paneBouton);
        
        paneBouton.setOpaque(false);
        paneavertissement.setOpaque(false);
        paneBoutonRadio.setOpaque(false);
        paneTexteChamp.setOpaque(false);
        
        popSauver.setMaximumSize(new Dimension(600, 600));
        popSauver.pack();
        

        popSauver.setVisible(true);
    }

    public void popRecommencer() {
    	
        final JDialog popRecommencer = new JDialog();
        JPanel panelRecommencer = new JPanel() /*{
            protected void paintComponent(Graphics g) {
                Dimension d = getParent().getSize();
                try {
                    BufferedImage img = ImageIO.read(new File("src/Ressources/fondpop.jpg"));
                    g.drawImage(img, 0, 0, d.width, d.height, null);
                } catch (IOException ex) {
                    Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
                    g.setColor(Color.BLUE);
                    g.fillRect(0, 0, d.width, d.height);
                }
            }
        }*/;
        panelRecommencer.setBackground(fond);
        panelRecommencer.setLayout(new BoxLayout(panelRecommencer, BoxLayout.Y_AXIS));
        JTextPane warning = new JTextPane();
        warning.setText("Attention : la partie en cours sera perdue si elle n'est pas sauvegardée!\n Voulez-vous vraiment recommencer la partie ?");
        
        StyledDocument doc = warning.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        warning.setOpaque(false);
        warning.setForeground(Color.white);
        warning.setFont(new Font("Calibri", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(panelRecommencer,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        warning.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        panelRecommencer.add(warning);
        warning.setEditable(false);
        popRecommencer.add(scrollPane);
        JDialog.setDefaultLookAndFeelDecorated(true);
        popRecommencer.setTitle("Recommencer");

        /*text.setText(
        		"Dans le cadre du projet de fin d'ann�e au semestre 6 � l'IM�AG\n"
                + "Licence 3 Informatique\n"
                + "\n"
        		+"Ce logiciel a �t� construit par le groupe 4 (Renju) :\n"
                + "\n"
                + "Mathieu BOCHATON\n"
                + "Guillaume CHAPUT\n"
                + "Cl�ment HERESAZ\n"
                + "Rapha�l JACQUET\n"
                + "Julien NAVAILS\n"
                + "Benjamin ROHAUT\n"
                + "\n"
                + "Merci � tous les professeurs pour leur accompagnement et leurs conseils.\n"
                + "\n"
                + "� 2016 Groupe 4, IM�AG, Universit� Grenoble Alpes \n"
                );

        text.setEditable(false);
        text.setSelectionColor(Color.green);*/
        
        JPanel boutonreco = new JPanel();
        boutonreco.setOpaque(false);
        panelRecommencer.add(boutonreco);
        JButton btnRecommencer = new JButton("Recommencer");
        btnRecommencer.addActionListener(new PopupsListener(popRecommencer, f, 6));
        boutonreco.add(btnRecommencer);
        
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popRecommencer));
        boutonreco.add(btnAnnuler);
        
        popRecommencer.pack();
        popRecommencer.setLocationRelativeTo(null);
        popRecommencer.setVisible(true);
    }

    public void popAbandonner() {
        JDialog popAbandonner = new JDialog();
        popAbandonner.setTitle("Retourner au menu");
        JPanel paneAbandonner = new JPanel();

        JDialog.setDefaultLookAndFeelDecorated(true);
        
        paneAbandonner.setBackground(fond);

        popAbandonner.setSize(500, 200);

        popAbandonner.setLocationRelativeTo(null);
        paneAbandonner.setLayout(new BoxLayout(paneAbandonner, BoxLayout.Y_AXIS));

        JTextPane warning = new JTextPane();
        
        //JTextArea w = new JTextArea("Attention : la partie sera perdue si elle n'est pas sauvegardée!");
        ///JTextArea w2 = new JTextArea("Voulez-vous vraiment quitter la partie en cours, et retourner au menu principal ?");
        //popAbandonner.add(warning);
        
        warning.setText("Attention : la partie sera perdue si elle n'est pas sauvegardée!\n Voulez-vous vraiment quitter la partie en cours, et retourner au menu principal ?");

        StyledDocument doc = warning.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        warning.setOpaque(false);
        warning.setForeground(Color.white);
        warning.setFont(new Font("Calibri", Font.BOLD, 15));
        
        JScrollPane scrollPane = new JScrollPane(paneAbandonner,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        warning.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        
        paneAbandonner.add(warning);
        
        warning.setEditable(false);
        popAbandonner.add(scrollPane);

        JPanel boutonreco = new JPanel();
        
        boutonreco.setOpaque(false);
        paneAbandonner.add(boutonreco);
        
        JButton btnAbandonner = new JButton("Retourner au menu");
        btnAbandonner.addActionListener(new PopupsListener(popAbandonner, f, 7));
        boutonreco.add(btnAbandonner);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popAbandonner));
        boutonreco.add(btnAnnuler);

        
        popAbandonner.pack();
        popAbandonner.setLocationRelativeTo(null);
        popAbandonner.setVisible(true);
    }

    @SuppressWarnings("null")

    /**
     * Creation des emplacements de sauvegarde
     */
    public void Sauvegarde() {
        TrouverChemin();
        this.slot = new File[9];
        File chemin = new File(SavePath);
        int size = (int) chemin.toString().length();
        //System.out.println(size);
        File[] files = chemin.listFiles();
        String name;
        String n;
        int num;
        for (File f : files) {
            try {
                num = 20;
                name = f.toString(); //Exception
                n = name.substring(size + 1, size + 2);
                num = Integer.parseInt(n);
                //System.out.println(num);
                //System.out.println(name);
                if (num >= 0 && num < 10) { //name.endsWith(".ser") && 
                    //f = new File(name.substring(size+1, (int)name.length()));
                    this.slot[num] = f;
                }
            } catch (Exception e) {
                //System.out.println("got you!"); //debug
            }
        }
    }

    public void Sauvegarder(Partie p) {
        //System.out.println("Save initiated, " + SaveNum);
        if (SaveNum > 0 && SaveNum < 10) {
            //System.out.println("index ok");
            try {
                if (erase) {
                    slot[SaveNum - 1].delete();
                }
                //System.out.println("Try start");
                //File f = new File(i + "." + p.getJoueur1().getNom() + "-vs-" + p.getJoueur2().getNom() + ".ser");
                SaveName = (SaveNum - 1 + "-" + SaveName + ".ser");
                File fichier = new File(SavePath + SaveName);
                FileOutputStream fos = new FileOutputStream(fichier);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                //System.out.println("ok");
                oos.writeObject(p);
                //System.out.println("ok");
                oos.close();
                //slot[i] =  fichier;
                SaveName = "Sauvegarde";
                //System.out.println("Save Done" + SaveNum);
                SaveNum = 20;
            } catch (IOException | NullPointerException e) {
                System.out.println("Erreur lors de la sauvegarde du fichier");
            }
        }
    }

    /**
     * Charger la partie au slot i
     *
     * @param i le numero de l'emplacement de sauvegarde
     * @return null
     */
    public Partie Charger() {
        if (SaveNum > 0 && SaveNum < 10) {
            //System.out.println(SaveNum);
            try {
               // System.out.println(slot[SaveNum - 1]);
                FileInputStream fis = new FileInputStream(slot[SaveNum - 1]);
                //System.out.println("recu");
                ObjectInputStream ois = new ObjectInputStream(fis);
                //System.out.println("recu");
                Object p = ois.readObject();
                //System.out.println("recu");
                ois.close();
                if (p instanceof Partie) {
                    SaveNum = 20;
                    return (Partie) p;
                }
            } catch (IOException | NullPointerException | ClassNotFoundException e) {
                System.out.println("Erreur lors du chargement du fichier");
            }
        }
        return part;
    }

    public void TrouverChemin() {
        try {
            String path = Popups.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            SavePath = (decodedPath + "Saves/");
            if (!new File(SavePath).exists()) {
                new File(SavePath).mkdirs();
            }
            //System.out.println(SavePath);
        } catch (Exception e) {
            System.out.println("Erreur lors de la definition du chemin");
        }
    }

    public Fenetre getF() {
        return f;
    }

    public void setF(Fenetre f) {
        this.f = f;
    }

    public File[] getSlot() {
        return slot;
    }

    public void setSlot(File[] slot) {
        this.slot = slot;
    }

    public Partie getPart() {
        return part;
    }

    public void setPart(Partie part) {
        this.part = part;
    }

    public int getSaveNum() {
        return SaveNum;
    }

    public void setSaveNum(int saveNum) {
        SaveNum = saveNum;
    }

    public String getSaveName() {
        return SaveName;
    }

    public void setSaveName(String saveName) {
        SaveName = saveName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isErase() {
        return erase;
    }

    public void setErase(boolean erase) {
        this.erase = erase;
    }

    public String getSavePath() {
        return SavePath;
    }

    public void setSavePath(String savePath) {
        SavePath = savePath;
    }

    private void addButton(final JButton button, JComponent comp, final JDialog dialog) {
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
                dialog.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(247, 128, 104));
                dialog.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        comp.add(button);
    }
}
