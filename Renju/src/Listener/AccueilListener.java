package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controleur.Partie;
import Vue.*;


public class AccueilListener implements ActionListener {
	
	private Fenetre fenetre;
	private int val;
	private Popups popup;

	public AccueilListener(Fenetre f, int v) {
        this.fenetre = f;
        this.val = v;

    }
	
	public AccueilListener(Popups pop, int v) {
        this.popup= pop;
        this.val = v;

    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
            
		switch (val){
		case 1:
			fenetre.changePanel(fenetre.getNOUVPARTIEPANEL());
			break;
		case 2:
			fenetre.changePanel(fenetre.getCHARPARTIEPANEL());
			break;
		case 3:
			popup.popRegles();
			break;
		case 4:
			popup.popQuitterMenu();
			break;
		}
			
		
	}
}
