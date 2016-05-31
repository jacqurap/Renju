package Vue;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Controleur.Partie;
import Modele.Humain;
import Modele.Plateau;
import java.util.*;
import java.util.logging.*;

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

        partie = new Partie(J1, nomJ1, J2, nomJ2);
        if (this.partie.isIa1() || this.partie.isIa2()) {
            Timer t = new Timer();
            TimerTask tache = new TimerTask() {
                @Override
                public void run() {
                    if (!partie.isPartieFinie() && partie.isTourIa()) {
                        partie.joueIa();
                        repaint();
                    }
                }
            };
            t.scheduleAtFixedRate(tache, 1000, 2000);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        // Surbrillance des Joueurs
        if (this.getPartie().getNbCoups() % 2 == 0) {
            fenetre.getInterjeu().getTfJ1().setForeground(Color.RED);
            fenetre.getInterjeu().getTfJ2().setForeground(Color.BLACK); //.setForeground(Color.RED);
        } else {
            fenetre.getInterjeu().getTfJ2().setForeground(Color.RED);
            fenetre.getInterjeu().getTfJ1().setForeground(Color.BLACK);
        }

        Graphics2D drawable = (Graphics2D) g;
        drawable.setStroke(new BasicStroke(2)); //épaisseur des lignes
        int largeurAire = getParent().getSize().height - 23;

        //thème du terrain
        try {
            BufferedImage img = ImageIO.read(new File("Ressources/" + getThemePlateau() + ".png"));
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

        //positionne les pierres
        drawable.setStroke(new BasicStroke(1)); //épaisseur du contour des pierres blanches
        int pierreRayon = (int) ((double) ligneSpace / (double) 1.5);
        int decalPierre = pierreRayon / 2;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (partie.getPlateau().getCase(i, j) == Plateau.CASEBLANCHE) {
                    drawable.setColor(Color.white);
                    drawable.fillOval((i + 1) * ligneSpace - decalPierre, (j + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);

                    if (partie.getPlateau().getNumero(i, j) == partie.getNbCoups()) {
                        drawable.setColor(Color.RED);
                        drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace, (j + 1) * ligneSpace);
                    } else {
                        drawable.setColor(Color.BLACK);
                        drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace, (j + 1) * ligneSpace);
                    }

                    drawable.setColor(Color.BLACK);
                    drawable.drawOval((i + 1) * ligneSpace - decalPierre, (j + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);
                } else if (partie.getPlateau().getCase(i, j) == Plateau.CASENOIRE) {
                    drawable.setColor(Color.BLACK);
                    drawable.fillOval((i + 1) * ligneSpace - decalPierre, (j + 1) * ligneSpace - decalPierre, pierreRayon, pierreRayon);

                    if (partie.getPlateau().getNumero(i, j) == partie.getNbCoups()) {
                        drawable.setColor(Color.RED);
                        drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace, (j + 1) * ligneSpace);
                    } else {
                        drawable.setColor(Color.WHITE);
                        drawable.drawString(String.valueOf((partie.getPlateau().getNumero(i, j))), (i + 1) * ligneSpace, (j + 1) * ligneSpace);
                    }

                }
                // System.out.println(String.valueOf(partie.getNbCoups()));
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
}
