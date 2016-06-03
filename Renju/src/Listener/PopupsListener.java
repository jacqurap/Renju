package Listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Modele.Humain;
import Modele.Ia1;
import Modele.Ia2;
import Vue.*;

public class PopupsListener implements ActionListener {

    private JDialog popup;
    private Fenetre fenetre;
    private int val;
    private Component jcomp;
    private Popups fpop;

    public PopupsListener(JDialog pop) {
        this.popup = pop;

    }

    public PopupsListener(JDialog pop, Fenetre f, int v) {
        this.popup = pop;
        this.fenetre = f;
        this.val = v;

    }

    public PopupsListener(Component jc, Popups fp, int v) {
        this.jcomp = jc;
        this.fpop = fp;
        this.val = v;

    }

    public PopupsListener(JDialog pop, Fenetre f, Popups fp, int v) {
        this.popup = pop;
        this.fenetre = f;
        this.fpop = fp;
        this.val = v;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (val) {
            case 1:
                fenetre.closeGame();
                popup.dispose();
                System.exit(0);
                break;

            case 2:
                fenetre.getAire().setPartie(fpop.Charger());
                if(fenetre.getAire().getPartie().getJoueur1() instanceof Humain){
                	fenetre.getAire().setJoueur1("Modele.Humain");
                	fenetre.getAire().setNomJoueur1(fenetre.getAire().getPartie().getJoueur1().getNom());
                }
                else{
                	if(fenetre.getAire().getPartie().getJoueur1() instanceof Ia1){
                		fenetre.getAire().setJoueur1("Modele.Ia1");
                		fenetre.getAire().setNomJoueur1("Facile");
                	}
                	else if(fenetre.getAire().getPartie().getJoueur1() instanceof Ia2){
                		fenetre.getAire().setJoueur1("Modele.Ia2");
                		fenetre.getAire().setNomJoueur1("Moyen");
                	}
                	else{
                    		fenetre.getAire().setJoueur1("Modele.Ia3");
                    		fenetre.getAire().setNomJoueur1("Dificile");
                	}
                }
                if(fenetre.getAire().getPartie().getJoueur2() instanceof Humain){
                	fenetre.getAire().setJoueur2("Modele.Humain");
                	fenetre.getAire().setNomJoueur2(fenetre.getAire().getPartie().getJoueur2().getNom());
                }
                else{
                	if(fenetre.getAire().getPartie().getJoueur2() instanceof Ia1){
                		fenetre.getAire().setJoueur2("Modele.Ia1");
                		fenetre.getAire().setNomJoueur2("Facile");
                	}
                	else if(fenetre.getAire().getPartie().getJoueur2() instanceof Ia2){
                		fenetre.getAire().setJoueur2("Modele.Ia2");
                		fenetre.getAire().setNomJoueur2("Moyen");
                	}
                	else{
                    	fenetre.getAire().setJoueur2("Modele.Ia3");
                    	fenetre.getAire().setNomJoueur1("Dificile");
                	}
                }
                
            	// Annuler / Refaire après chargement de la partie
                if (!fenetre.getAire().getPartie().getAnnuler().isEmpty()) {
                    InterfaceJeu.getButAnnuler().setEnabled(true);
                } else {
                    InterfaceJeu.getButAnnuler().setEnabled(false);

                }
                if (!fenetre.getAire().getPartie().getRefaire().isEmpty()) {
                    InterfaceJeu.getButRefaire().setEnabled(true);
                } else {
                    InterfaceJeu.getButRefaire().setEnabled(false);

                }
                fenetre.getInterjeu().getTfJ1().setText(fenetre.getAire().getPartie().getJoueur1().getNom());
                fenetre.getInterjeu().getTfJ2().setText(fenetre.getAire().getPartie().getJoueur2().getNom());
                fenetre.getAire().repaint();
                popup.dispose();
                fenetre.changePanel(fenetre.getINTERFACEPANEL());
                popup.dispose();
                break;

            case 3:
                if (!jcomp.isEnabled()) {
                    //System.out.println("NUMERO : " + fpop.getSaveNum());
                    jcomp.setEnabled(true);
                }
                break;

            case 4:
                jcomp.setVisible(true);
                fpop.setSaveNum(Character.getNumericValue(((JRadioButton) e.getSource()).getText().charAt(0)));
                fpop.setErase(true);
                break;

            case 5:
                jcomp.setVisible(false);
                fpop.setSaveNum(Character.getNumericValue(((JRadioButton) e.getSource()).getText().charAt(0)));
                fpop.setErase(false);
                break;

            case 6:
                AireDeJeu aire = new AireDeJeu(fenetre.getAire().getJoueur1(), fenetre.getAire().getNomJoueur1(), fenetre.getAire().getJoueur2(), fenetre.getAire().getNomJoueur2(), fenetre);
                fenetre.setAire(aire);
                fenetre.changePanel(fenetre.getACCUEILPANEL()); //pour ne pas freeze pendant le removeAll
                fenetre.refreshInterface();
                fenetre.changePanel(fenetre.getINTERFACEPANEL());
                popup.dispose();
                break;

            case 7:
                fenetre.changePanel(fenetre.getACCUEILPANEL());
                popup.dispose();
                break;

            case 8:
                fpop.Sauvegarder(fpop.getPart());
                ((JDialog) jcomp).dispose();
                break;

            case 9:
                fpop.setSaveNum(Character.getNumericValue(((JRadioButton) e.getSource()).getText().charAt(0)));
                break;

            default:
                popup.dispose();
                break;
        }

    }
}
