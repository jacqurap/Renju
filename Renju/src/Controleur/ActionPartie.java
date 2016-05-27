package Vue;

import Modele.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * Actions du bouton Annuler
 *
 * @author bochatom
 */
class ActionPartie implements ActionListener {

    private String typeJ1;
    private String typeJ2;
    private String nomJ1;
    private String nomJ2;
    private String lvlIa1;
    private String lvlIa2;

    /**
     * Creation d'une action d'annulation
     *
     * @param aire l'aire de jeu
     * @param annuler le bouton annuler
     * @param refaire le bouton refaire
     */
    public ActionPartie(String typeJ1, String typeJ2, String nomJ1, String nomJ2, String lvlIa1, String lvlIa2) {
        this.typeJ1 = typeJ1;
        this.typeJ2 = typeJ2;
        this.nomJ1 = nomJ1;
        this.nomJ2 = nomJ2;
        this.lvlIa1 = lvlIa1;
        this.lvlIa2 = lvlIa2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String J1;
        String J2;
        String nomJ1;
        String nomJ2;
        if (typeJ1 == "Joueur") {
            J1 = "Modele.Humain";
            nomJ1 = this.nomJ1;
        } else {
            switch (lvlIa1) {
                case "Facile":
                    J1 = "Modele.Ia" + 1;
                    break;
                case "Moyen":
                    J1 = "Modele.Ia" + 2;
                    break;
                case "Difficile":
                    J1 = "Modele.Ia" + 3;
                    break;
                default:
                    J1 = "Modele.Ia" + 1;

            }
            nomJ1 = lvlIa1;
        }
        if (typeJ2 == "Joueur") {
            J2 = "Modele.Humain";
            nomJ2 = this.nomJ2;
        } else {
            switch (lvlIa2) {
                case "Facile":
                    J2 = "Modele.Ia" + 1;
                    break;
                case "Moyen":
                    J2 = "Modele.Ia" + 2;
                    break;
                case "Difficile":
                    J2 = "Modele.Ia" + 3;
                    break;
                default:
                    J2 = "Modele.Ia" + 1;

            }
            nomJ2 = lvlIa2;

        }
        AireDeJeu aire = new AireDeJeu(J1, nomJ1, J2, nomJ2);
        
    }

}
