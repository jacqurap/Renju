package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fenetre {

    public JPanel cards;
    static public JFrame frame;
    final static String ACCUEILPANEL = "Card with home panel";
    final static String NOUVPARTIEPANEL = "Card with new game panel";
    final static String CHARPARTIEPANEL = "Card with loading game panel";
    final static String INTERFACEPANEL = "Card with game panel";

    public void addComponentToPane(Container pane) {

        AireDeJeu aire = new AireDeJeu();
        Accueil acc = new Accueil(this);
        NouvellePartie np = new NouvellePartie(this);
        ChargerPartie cp = new ChargerPartie(this);
        InterfaceJeu ij = new InterfaceJeu(this,aire);
        ij.addMouseListener(new EcouteurDeSouris(aire));

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(acc, ACCUEILPANEL);
        cards.add(np, NOUVPARTIEPANEL);
        cards.add(cp, CHARPARTIEPANEL);
        cards.add(ij, INTERFACEPANEL);

        //pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    public void changePanel(String panelName){
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, panelName);
        //frame.pack(); not ok
    }
    
    public void closeGame() {
        frame.dispose();
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Renju");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Fenetre fenetre = new Fenetre();
        fenetre.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
