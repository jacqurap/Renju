package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Vue.*;


public class PopupsListener implements ActionListener {
	
	//private int val;
	private JDialog popup;
	private Fenetre fenetre;
	private int val;
	private JComponent jcomp;

	
	public PopupsListener(JDialog pop) {
        this.popup= pop;
        //this.val = v;

    }
	
	public PopupsListener(JDialog pop, Fenetre f, int v ) {
        this.popup= pop;
        this.fenetre=f;
        this.val = v;

    }
	
	public PopupsListener(JComponent jc, int v ) {
        this.jcomp= jc;
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
			
		case 2 :
			fenetre.changePanel(fenetre.getINTERFACEPANEL());
            popup.dispose();
            break;
            
		case 3:
			if (!jcomp.isEnabled()) {
                jcomp.setEnabled(true);
            }
			break;
		
		case 4 :
			jcomp.setVisible(true);
			break;
			
		case 5 :
			jcomp.setVisible(false);
			break;
		
		case 6 : 
			AireDeJeu aire = new AireDeJeu(fenetre.getAire().getJoueur1(), fenetre.getAire().getNomJoueur1(), fenetre.getAire().getJoueur2(), fenetre.getAire().getNomJoueur2(),fenetre);
            fenetre.setAire(aire);
            fenetre.changePanel(fenetre.getACCUEILPANEL()); //pour ne pas freeze pendant le removeAll
            fenetre.refreshInterface();
            fenetre.changePanel(fenetre.getINTERFACEPANEL());
            popup.dispose();
            break;
          
		case 7 :
			fenetre.changePanel(fenetre.getACCUEILPANEL());
            popup.dispose();
            break;
            
		default: 
			popup.dispose();
			break;
		}
            
		
			
		
	}
}
