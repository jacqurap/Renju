package Vue;

import java.awt.*;
import javax.swing.*;

public class Fenetre {


    private JPanel cards;
    private static JFrame frame;
    private String ACCUEILPANEL = "Card with home panel";
    private String NOUVPARTIEPANEL = "Card with new game panel";
    private String CHARPARTIEPANEL = "Card with loading game panel";
    private String INTERFACEPANEL = "Card with game panel";
    private AireDeJeu aire;
    private InterfaceJeu interjeu;
    private JPanel panelInterface;

    public void addComponentToPane(Container pane) {

        setAire(new AireDeJeu("Modele.Humain", "Joueur1", "Modele.Humain", "Joueur2", this));
        Accueil acc = new Accueil(this);
        NouvellePartie np = new NouvellePartie(this);
        ChargerPartie cp = new ChargerPartie(this);
        setPanelInterface(new JPanel());
        getPanelInterface().setLayout(new BorderLayout());

        //Create the panel that contains the "cards".
        setCards(new JPanel(new CardLayout()));
        getCards().add(acc, getACCUEILPANEL());
        getCards().add(np, getNOUVPARTIEPANEL());
        getCards().add(cp, getCHARPARTIEPANEL());
        getCards().add(getPanelInterface(), getINTERFACEPANEL());

        //pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(getCards(), BorderLayout.CENTER);
    }

  
	public void changePanel(String panelName) {
        CardLayout cl = (CardLayout) (getCards().getLayout());
        cl.show(getCards(), panelName);
    }
    
    public void refreshInterface() {
        getPanelInterface().removeAll();
        InterfaceJeu ij = new InterfaceJeu(this, aire);
        ij.getTfJ1().setText(aire.getNomJoueur1());
        ij.getTfJ2().setText(aire.getNomJoueur2());
        interjeu =ij;
        aire.addMouseListener(new EcouteurDeSouris(aire));
        getPanelInterface().add(ij);
    }

	public void closeGame() {
        getFrame().dispose();
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setFrame(new JFrame("Renju"));
                getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Create and set up the content pane.
                Fenetre fenetre = new Fenetre();
                fenetre.addComponentToPane(getFrame().getContentPane());

                //Display the window.
                getFrame().setLocationRelativeTo(null);
                getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
                getFrame().setVisible(true);
            }
        });
    }
    /**
     * @return the frame
     */
    public static JFrame getFrame() {
        return frame;
    }

    /**
     * @param aFrame the frame to set
     */
    public static void setFrame(JFrame aFrame) {
        frame = aFrame;
    }
    /**
     * @return the cards
     */
    public JPanel getCards() {
        return cards;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(JPanel cards) {
        this.cards = cards;
    }

    /**
     * @return the ACCUEILPANEL
     */
    public String getACCUEILPANEL() {
        return ACCUEILPANEL;
    }

    /**
     * @param ACCUEILPANEL the ACCUEILPANEL to set
     */
    public void setACCUEILPANEL(String ACCUEILPANEL) {
        this.ACCUEILPANEL = ACCUEILPANEL;
    }

    /**
     * @return the NOUVPARTIEPANEL
     */
    public String getNOUVPARTIEPANEL() {
        return NOUVPARTIEPANEL;
    }

    /**
     * @param NOUVPARTIEPANEL the NOUVPARTIEPANEL to set
     */
    public void setNOUVPARTIEPANEL(String NOUVPARTIEPANEL) {
        this.NOUVPARTIEPANEL = NOUVPARTIEPANEL;
    }

    /**
     * @return the CHARPARTIEPANEL
     */
    public String getCHARPARTIEPANEL() {
        return CHARPARTIEPANEL;
    }

    /**
     * @param CHARPARTIEPANEL the CHARPARTIEPANEL to set
     */
    public void setCHARPARTIEPANEL(String CHARPARTIEPANEL) {
        this.CHARPARTIEPANEL = CHARPARTIEPANEL;
    }

    /**
     * @return the INTERFACEPANEL
     */
    public String getINTERFACEPANEL() {
        return INTERFACEPANEL;
    }

    /**
     * @param INTERFACEPANEL the INTERFACEPANEL to set
     */
    public void setINTERFACEPANEL(String INTERFACEPANEL) {
        this.INTERFACEPANEL = INTERFACEPANEL;
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
     * @return the panelInterface
     */
    public JPanel getPanelInterface() {
        return panelInterface;
    }

    /**
     * @param panelInterface the panelInterface to set
     */
    public void setPanelInterface(JPanel panelInterface) {
        this.panelInterface = panelInterface;
    }
    
    public InterfaceJeu getInterjeu() {
  		return interjeu;
  	}


  	public void setInterjeu(InterfaceJeu interjeu) {
  		this.interjeu = interjeu;
  	}



}
