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

    public Popups(Fenetre f) {
        this.f = f;
    }

    public void popRegles() {

        final JDialog popRegle = new JDialog();
        JPanel panel = new JPanel() {
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextArea text = new JTextArea();
        text.setOpaque(false);
        text.setForeground(Color.white);
        text.setFont(new Font("Calibri", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(text);
        popRegle.add(scrollPane);
        JDialog.setDefaultLookAndFeelDecorated(true);
        popRegle.setSize(800, 500);
        popRegle.setLocationRelativeTo(null);
        popRegle.setTitle("Aide de jeu");

        text.setText("I - Généralités\n"
                + "\n"
                + "Le Renju se joue à deux joueurs, sur un plateau quadrillé de 15 lignes par 15.\n"
                + "Les deux joueurs ont 60 pions chacun, l'un jouant les noirs, l'autre les blancs.\n"
                + "Dans tous les cas, le joueur possédant les pions noirs commence.\n"
                + "\n"
                + "II - Déroulement d'une partie\n"
                + "\n"
                + "A tour de rôle, chaque joueur place un pion sur une intersection du plateau.\n"
                + "Le 1er coup noir est au millieu du plateau; le 1er coup blanc se joue sur l'une des 8 intersections\n"
                + "adjacentes du pion noir; les coups suivants ne sont pas restreints.\n"
                + "Le premier joueur qui réussi à aligner 5 pions consécutifs horizontalement, verticalement ou en diagonale\n"
                + "gagne la partie.\n"
                + "Si tous les pions ont été posés sans q'un alignement de cinq pions ne soit effectué, la partie est considérée comme nulle.\n"
                + "\n"
                + "II - Les tabous\n"
                + "\n"
                + "Le joueur noir ayant beaucoup plus de chance de remporter la partie, certaines configurations de pions\n"
                + "lui sont interdit (ce sont des tabous):\n"
                + "\n"
                + "- un croisement de deux lignes de trois pions\n"
                + "- un croisement de deux lignes de quatre pions\n"
                + "- un alignement de plus de cinq pions");

        text.setEditable(false);
        text.setSelectionColor(Color.green);
        popRegle.setVisible(true);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popRegle.dispose();
            }
        });
        panel.add(btnRetour);

        /*      final JDialog popRegle = new JDialog();
         popRegle.setTitle("Aide de jeu");

         JDialog.setDefaultLookAndFeelDecorated(true);
         popRegle.setSize(500, 200);
         popRegle.setLocationRelativeTo(null);
         popRegle.setLayout(new FlowLayout());
         popRegle.add(new JLabel("Coups interdits"));

         JButton btnRetour = new JButton("Retour");
         btnRetour.addActionListener(new PopupsListener(popRegle));
         popRegle.add(btnRetour);

         popRegle.setVisible(true);*/
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

    public void popCharger(Partie partie) {
        Sauvegarde();
        this.part = partie;
        final JDialog popCharger = new JDialog();
        popCharger.setTitle("Charger");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popCharger.setSize(500, 200);

        popCharger.setLocationRelativeTo(null);
        popCharger.setLayout(new FlowLayout());

        JButton btnCharger = new JButton("Charger");
        btnCharger.addActionListener(new PopupsListener(popCharger, f, this, 2));
        btnCharger.setEnabled(false);

        JLabel warning = new JLabel("Attention, en chargeant une partie, la partie courante sera perdue !");
        warning.setVisible(true);

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
            grpSlot.add(slot);
            popCharger.add(slot);
        }

        popCharger.add(warning);

        popCharger.add(btnCharger);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popCharger));
        popCharger.add(btnAnnuler);

        popCharger.setVisible(true);
    }

    public void popSauver(Partie partie) {
        TrouverChemin();
        Sauvegarde();
        this.part = partie;
        JDialog popSauver = new JDialog();
        popSauver.setTitle("Sauvegarder");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popSauver.setSize(500, 200);

        popSauver.setLocationRelativeTo(null);
        popSauver.setLayout(new FlowLayout());

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(new PopupsListener(popSauver, this, 8));
        btnSauvegarder.setEnabled(false);

        JLabel warning = new JLabel("Attention une partie est déjà sauvegardée sur ce slot, vous allez l'écraser !");
        warning.setVisible(false);

        ButtonGroup grpSlot = new ButtonGroup();
        for (index = 1; index < 10; index++) {
            String val;
            if (slot[index - 1] == null) {
                val = (String.valueOf(index) + "e Slot");
            } else {
                val = (index + slot[index - 1].toString().substring(SavePath.length() + 1, (int) slot[index - 1].toString().length() - 4));
            }
            JRadioButton slot = new JRadioButton(val);
            slot.addActionListener(new PopupsListener(btnSauvegarder, this, 3));
            if (this.slot[index - 1] != null) {
                slot.addActionListener(new PopupsListener(warning, this, 4));
            } else {
                slot.addActionListener(new PopupsListener(warning, this, 5));
            }
            grpSlot.add(slot);
            popSauver.add(slot);
        }

        popSauver.add(warning);

        JLabel labelNom = new JLabel("Nom de la sauvegarde :");
        popSauver.add(labelNom);
        JTextField nom = new JTextField("Sauvegarde", 20);
        nom.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                SaveName = ((JTextField) e.getSource()).getText();
            }
        });
        popSauver.add(nom);
        popSauver.add(btnSauvegarder);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popSauver));
        popSauver.add(btnAnnuler);

        popSauver.setVisible(true);
    }

    public void popRecommencer() {
        JDialog popRecommencer = new JDialog();
        popRecommencer.setTitle("Recommencer");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popRecommencer.setSize(500, 200);

        popRecommencer.setLocationRelativeTo(null);
        popRecommencer.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie en cours sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment recommencer la partie ?");
        popRecommencer.add(warning);

        JButton btnRecommencer = new JButton("Recommencer");
        btnRecommencer.addActionListener(new PopupsListener(popRecommencer, f, 6));
        popRecommencer.add(btnRecommencer);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popRecommencer));
        popRecommencer.add(btnAnnuler);

        popRecommencer.setVisible(true);
    }

    public void popAbandonner() {
        JDialog popAbandonner = new JDialog();
        popAbandonner.setTitle("Abandonner");

        JDialog.setDefaultLookAndFeelDecorated(true);

        popAbandonner.setSize(500, 200);

        popAbandonner.setLocationRelativeTo(null);
        popAbandonner.setLayout(new FlowLayout());

        JLabel warning = new JLabel("Attention : la partie sera perdue si elle n'est pas sauvegardée! Voulez-vous vraiment abandonner la partie en cours, et retourner au menu principale ?");
        popAbandonner.add(warning);

        JButton btnAbandonner = new JButton("Abandonner");
        btnAbandonner.addActionListener(new PopupsListener(popAbandonner, f, 7));
        popAbandonner.add(btnAbandonner);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new PopupsListener(popAbandonner));
        popAbandonner.add(btnAnnuler);

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
        System.out.println(size);
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
                System.out.println(num);
                System.out.println(name);
                if (num >= 0 && num < 9) { //name.endsWith(".ser") && 
                    //f = new File(name.substring(size+1, (int)name.length()));
                    this.slot[num] = f;
                }
            } catch (Exception e) {
                System.out.println("got you!"); //debug
            }
        }
    }

    public void Sauvegarder(Partie p) {
        System.out.println("Save initiated, " + SaveNum);
        if (SaveNum >= 0 && SaveNum < 9) {
            System.out.println("index ok");
            try {
                if (erase) {
                    slot[SaveNum - 1].delete();
                }
                System.out.println("Try start");
                //File f = new File(i + "." + p.getJoueur1().getNom() + "-vs-" + p.getJoueur2().getNom() + ".ser");
                SaveName = (SaveNum - 1 + "-" + SaveName + ".ser");
                File fichier = new File(SavePath + SaveName);
                FileOutputStream fos = new FileOutputStream(fichier);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                System.out.println("ok");
                oos.writeObject(p);
                System.out.println("ok");
                oos.close();
                //slot[i] =  fichier;
                SaveName = "Sauvegarde";
                System.out.println("Save Done" + SaveNum);
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
        if (SaveNum >= 0 && SaveNum <= 9) {
            System.out.println(SaveNum);
            try {
                System.out.println(slot[SaveNum - 1]);
                FileInputStream fis = new FileInputStream(slot[SaveNum - 1]);
                System.out.println("recu");
                ObjectInputStream ois = new ObjectInputStream(fis);
                System.out.println("recu");
                Object p = ois.readObject();
                System.out.println("recu");
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
            System.out.println(SavePath);
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
