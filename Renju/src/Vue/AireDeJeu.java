package Vue;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Controleur.Partie;
import Listener.PopupsListener;
import Modele.Humain;
import Modele.Plateau;
import java.util.*;
import java.util.logging.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * L'aire de jeu
 */
public class AireDeJeu extends JComponent {

    private Partie partie;
    private String joueur1;
    private String joueur2;
    private String nomJoueur1;
    private String nomJoueur2;
    private Fenetre fenetre;
    private String themePlateau;
    private boolean numCase;
    private boolean historique;
    private boolean coupsRestants;
    private boolean victoireAffiche;
    public static Color Rose = new Color(255, 0, 255);

    /**
     * Creation de l'aire de jeu
     */
    public AireDeJeu(String J1, String nomJ1, String J2, String nomJ2, Fenetre f) {
        this.joueur1 = J1;
        this.nomJoueur1 = nomJ1;
        this.joueur2 = J2;
        this.nomJoueur2 = nomJ2;
        this.fenetre = f;
        this.themePlateau = "Bois";
        this.numCase = true;
        this.historique = true;
        this.coupsRestants = false;
        this.victoireAffiche = false;

        partie = new Partie(J1, nomJ1, J2, nomJ2);
        if (this.partie.isIa1() || this.partie.isIa2()) {
            Timer t = new Timer();
            TimerTask tache = new TimerTask() {
                @Override
                public void run() {
                    if (!partie.isPartieFinie() && partie.isTourIa()) {
                        fenetre.getInterjeu().Loading(true);
                        partie.joueIa();
                        repaint();
                        fenetre.getInterjeu().Loading(false);
                    }
                }
            };
            t.scheduleAtFixedRate(tache, 1000, 2000);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;
        drawable.setStroke(new BasicStroke(2)); //épaisseur des lignes
        Dimension d = getParent().getParent().getSize();
        int largeurAire = d.height - 23;

        int tailleFont = largeurAire / 45;
        g.setFont(new Font("Calibri", Font.BOLD, tailleFont));

        // Surbrillance des Joueurs
        if (this.getPartie().getNbCoups() % 2 == 0) {
            fenetre.getInterjeu().getTfJ1().setForeground(Color.GREEN);
            fenetre.getInterjeu().getTfJ2().setForeground(Color.BLACK);
        } else {
            fenetre.getInterjeu().getTfJ2().setForeground(Color.GREEN);
            fenetre.getInterjeu().getTfJ1().setForeground(Color.BLACK);
        }

        //thème du terrain
        try {
            BufferedImage img = ImageIO.read(new File("src/Ressources/" + getThemePlateau() + ".png"));
            drawable.drawImage(img, 0, 0, largeurAire, largeurAire, null);
        } catch (IOException ex) {
            Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
            drawable.setColor(Color.LIGHT_GRAY);
            drawable.fillRect(0, 0, largeurAire, largeurAire);
        }

        //trace les lignes 
        int ligneSpace = (largeurAire / 16);
        drawable.setColor(Color.black);
        for (int i = 0; i < 15; i++) {
            drawable.drawLine((i + 1) * ligneSpace, ligneSpace, (i + 1) * ligneSpace, 15 * ligneSpace);
            drawable.drawLine(ligneSpace, (i + 1) * ligneSpace, 15 * ligneSpace, (i + 1) * ligneSpace);
        }
        //trace les petits cercles de repère
        int circleSize = ligneSpace / 5;
        drawable.fillOval(4 * ligneSpace - (circleSize / 2), 4 * ligneSpace - (circleSize / 2), circleSize, circleSize);
        drawable.fillOval(12 * ligneSpace - (circleSize / 2), 4 * ligneSpace - (circleSize / 2), circleSize, circleSize);
        drawable.fillOval(4 * ligneSpace - (circleSize / 2), 12 * ligneSpace - (circleSize / 2), circleSize, circleSize);
        drawable.fillOval(12 * ligneSpace - (circleSize / 2), 12 * ligneSpace - (circleSize / 2), circleSize, circleSize);
        drawable.fillOval(8 * ligneSpace - (circleSize / 2), 8 * ligneSpace - (circleSize / 2), circleSize, circleSize);

        // Nombre Coup Restant
        if (isCoupsRestants()) {
            if (this.getPartie().getNbCoups() % 2 == 0) {
                fenetre.getInterjeu().getCoupRestJ1().setText(String.valueOf(60 - this.getPartie().getNbCoups() / 2) + " coups restants");
                fenetre.getInterjeu().getCoupRestJ2().setText(String.valueOf(60 - this.getPartie().getNbCoups() / 2) + " coups restants");
            } else {
                fenetre.getInterjeu().getCoupRestJ1().setText(String.valueOf(60 - (this.getPartie().getNbCoups() / 2) - 1) + " coups restants");
                fenetre.getInterjeu().getCoupRestJ2().setText(String.valueOf(60 - this.getPartie().getNbCoups() / 2) + " coups restants");
            }
        } else {
            fenetre.getInterjeu().getCoupRestJ1().setText("");
            fenetre.getInterjeu().getCoupRestJ2().setText("");
        }

        //trace numéro des cases
        if (isNumCase()) {
            for (int i = 1; i < 16; i++) {
                drawable.drawString(String.valueOf(i), ligneSpace / 5, largeurAire - i * ligneSpace);
                drawable.drawString(Character.toString((char) (i + 64)), i * ligneSpace, largeurAire - ligneSpace / 5);
            }
        }

        //positionne les pierres
        int pierreRayon = (int) ((double) ligneSpace / (double) 1.5);
        int decalPierre = pierreRayon / 2;

        drawable.setColor(Rose);

        //les coups obligatoires pré-déssinés
        if (partie.getNbCoups() == 0) {
            // Centre
            drawable.drawOval((7 + 1) * ligneSpace - decalPierre, (7 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
        } else if (partie.getNbCoups() == 1) {
            // Centre + (1,0)
            drawable.drawOval((8 + 1) * ligneSpace - decalPierre, (7 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (1,1)
            drawable.drawOval((8 + 1) * ligneSpace - decalPierre, (8 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (0,1)
            drawable.drawOval((7 + 1) * ligneSpace - decalPierre, (8 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (-1,1)
            drawable.drawOval((6 + 1) * ligneSpace - decalPierre, (8 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (-1,0)
            drawable.drawOval((6 + 1) * ligneSpace - decalPierre, (7 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (-1,-1)
            drawable.drawOval((6 + 1) * ligneSpace - decalPierre, (6 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (0,-1)
            drawable.drawOval((7 + 1) * ligneSpace - decalPierre, (6 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
            // Centre + (1,-1)
            drawable.drawOval((8 + 1) * ligneSpace - decalPierre, (6 + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
        }

        drawable.setStroke(new BasicStroke(1)); //épaisseur du contour des pierres blanches
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                //décalage pour numéro de coup
                int taille = partie.getPlateau().getNumero(i, j);
                if (taille < 10) {
                    taille = 1;
                } else if (taille < 100) {
                    taille = 2;
                } else {
                    taille = 3;
                }

                if (partie.getPlateau().getCase(i, j) == Plateau.CASEBLANCHE) {
                    drawable.setColor(Color.white);
                    drawable.fillOval((i + 1) * ligneSpace - decalPierre, (j + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
                    if (historique) {
                        if (partie.getPlateau().getNumero(i, j) == partie.getNbCoups()) {
                            drawable.setColor(Color.RED);
                            drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace - (tailleFont / 4) * taille, (j + 1) * ligneSpace + (tailleFont / 4));
                        } else {
                            drawable.setColor(Color.BLACK);
                            drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace - (tailleFont / 4) * taille, (j + 1) * ligneSpace + (tailleFont / 4));
                        }
                    }
                    drawable.setColor(Color.BLACK);
                    drawable.drawOval((i + 1) * ligneSpace - decalPierre, (j + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
                } else if (partie.getPlateau().getCase(i, j) == Plateau.CASENOIRE) {
                    drawable.setColor(Color.BLACK);
                    drawable.fillOval((i + 1) * ligneSpace - decalPierre, (j + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
                    if (historique) {
                        if (partie.getPlateau().getNumero(i, j) == partie.getNbCoups()) {
                            drawable.setColor(Color.RED);
                            drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace - (tailleFont / 4) * taille, (j + 1) * ligneSpace + (tailleFont / 4));
                        } else {
                            drawable.setColor(Color.WHITE);
                            drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace - (tailleFont / 4) * taille, (j + 1) * ligneSpace + (tailleFont / 4));
                        }
                    }

                }
                // System.out.println(String.valueOf(partie.getNbCoups()));
            }

        }

        if (!partie.isPartieFinie()) {
            victoireAffiche = false; //si la partie s'est deja termine une fois et qu'on a utilise "annuler"
        }
        if (partie.isPartieFinie() && !victoireAffiche) { //message de fin de partie
            victoireAffiche = true;
            JDialog pop = new JDialog();
            JPanel panePop = new JPanel(){
                protected void paintComponent(Graphics g) {
                    try {
                        BufferedImage img = ImageIO.read(new File("src/Ressources/fondpop.jpg"));
                        g.drawImage(img, 0, 0, this.getSize().width, this.getSize().height, null);
                    } catch (IOException ex) {
                        Logger.getLogger(AireDeJeu.class.getName()).log(Level.SEVERE, null, ex);
                        g.setColor(Color.BLUE);
                        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
                    }
                }
            };
            panePop.setLayout(new GridLayout(2, 1));
            pop.setTitle("Fin de la partie");
            final JPanel paneAni = new JPanel();
            panePop.add(paneAni);
            JPanel paneButtons = new JPanel();
            paneButtons.setOpaque(false);
            paneButtons.add(Box.createHorizontalGlue());
            JButton reco = new JButton("Recommencer");
            reco.addActionListener(new PopupsListener(pop, fenetre, 6));
            addButton(reco,paneButtons,pop);
            paneButtons.add(Box.createRigidArea(new Dimension(30,0)));
            JButton aban = new JButton("Retourner au menu");
            aban.addActionListener(new PopupsListener(pop, fenetre, 7));
            addButton(aban,paneButtons,pop);
            paneButtons.add(Box.createHorizontalGlue());
            panePop.add(paneButtons);
            
            panePop.add(paneButtons);
            pop.add(panePop);
            JDialog.setDefaultLookAndFeelDecorated(true);
            pop.setSize(500, 250);
            pop.setLocationRelativeTo(null);
            pop.setVisible(true);
            Thread th = new Thread(new Runnable() {
                public void run() {
                    if (partie.isJ1Win()) {
                        win(paneAni, "Victoire de " + partie.getJoueur1().getNom() + " !");
                    } else if (partie.isJ2Win()) {
                        win(paneAni, "Victoire de " + partie.getJoueur2().getNom() + " !");
                    } else {
                        win(paneAni, "Egalite !");
                    }
                }
            });
            th.start();

        }
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
        
    /**
     * animation de victoire
     *
     */
    public void win(JPanel pan, String message) {
        Font font = new Font("Calibri", Font.BOLD, 40);
        JLabel game = new JLabel("", JLabel.CENTER);
        pan.setOpaque(false);
        String gj;
        gj = message;
        game.setForeground(Color.WHITE);
        game.setFont(font);
        pan.add(game);
        for (int i = 0; i <= gj.length(); i++) {
            game.setText(gj.substring(0, i));
            pan.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        
    public Partie getPartie() {
        return this.partie;
    }

    public String getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(String joueur1) {
        this.joueur1 = joueur1;
    }

    public String getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(String joueur2) {
        this.joueur2 = joueur2;
    }

    public String getNomJoueur1() {
        return nomJoueur1;
    }

    public void setNomJoueur1(String nomJoueur1) {
        this.nomJoueur1 = nomJoueur1;
    }

    public String getNomJoueur2() {
        return nomJoueur2;
    }

    public void setNomJoueur2(String nomJoueur2) {
        this.nomJoueur2 = nomJoueur2;
    }

    public void setPartie(Partie p) {
        this.partie = p;
    }

    /**
     * @return the themePlateau
     */
    public String getThemePlateau() {
        return themePlateau;
    }

    /**
     * @param themePlateau the themePlateau to set
     */
    public void setThemePlateau(String themePlateau) {
        this.themePlateau = themePlateau;
    }

    /**
     * @return the numCase
     */
    public boolean isNumCase() {
        return numCase;
    }

    /**
     * @param numCase the numCase to set
     */
    public void setNumCase(boolean numCase) {
        this.numCase = numCase;
    }

    /**
     * @return the historique
     */
    public boolean isHistorique() {
        return historique;
    }

    /**
     * @param historique the historique to set
     */
    public void setHistorique(boolean historique) {
        this.historique = historique;
    }

    /**
     * @return the coupsRestants
     */
    public boolean isCoupsRestants() {
        return coupsRestants;
    }

    /**
     * @param coupsRestants the coupsRestants to set
     */
    public void setCoupsRestants(boolean coupsRestants) {
        this.coupsRestants = coupsRestants;
    }

}
