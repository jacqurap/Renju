package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import Vue.*;

import javax.swing.*;

import Controleur.Partie;
import Vue.*;

public class SelectJoueurListener implements ActionListener {

    private int val;
    private JPanel pan;
    private NouvellePartie nouvpartie;
    private Fenetre f;

    public SelectJoueurListener(int v, JPanel aFermer, Fenetre f) {
        this.val = v;
        this.pan = aFermer;
        this.f = f;
    }

    public SelectJoueurListener(NouvellePartie nPartie, int v, Fenetre f) {
        this.nouvpartie = nPartie;
        this.val = v;
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (val) {
            case 0:
                pan.setVisible(false);
                break;
            case 1:
                pan.setVisible(true);
                break;

            case 2:
                String J1;
                String J2;
                String nomJ1;
                String nomJ2;
                String typeJ1;
                String typeJ2;

                typeJ1 = nouvpartie.getSelectedButtonText(nouvpartie.getRbItemJ1());
                if (typeJ1 == "Joueur") {
                    J1 = "Modele.Humain";
                    if (nouvpartie.getTxtFdj1().getText().length() > 15) {
                        nomJ1 = nouvpartie.getTxtFdj1().getText().substring(0, 15);
                    }else{
                        nomJ1 = nouvpartie.getTxtFdj1().getText();
                    }
                } else {
                    switch (nouvpartie.getSelectedButtonText(nouvpartie.getiAJ1())) {
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
                    nomJ1 = nouvpartie.getSelectedButtonText(nouvpartie.getiAJ1());
                }

                typeJ2 = nouvpartie.getSelectedButtonText(nouvpartie.getRbItemJ2());

                if (typeJ2 == "Joueur") {
                    J2 = "Modele.Humain";
                    if (nouvpartie.getTxtFdj2().getText().length() > 15) {
                        nomJ2 = nouvpartie.getTxtFdj2().getText().substring(0, 15);
                    }else{
                        nomJ2 = nouvpartie.getTxtFdj2().getText();
                    }
                } else {
                    switch (nouvpartie.getSelectedButtonText(nouvpartie.getiAJ2())) {
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
                    nomJ2 = nouvpartie.getSelectedButtonText(nouvpartie.getiAJ2());

                }
                AireDeJeu aire = new AireDeJeu(J1, nomJ1, J2, nomJ2, f);
                f.setAire(aire);
                f.refreshInterface();
                f.changePanel(f.getINTERFACEPANEL());

        }

    }
}
