package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controleur.Partie;
import Vue.*;


public class AccueilListener implements ActionListener {
	
	private int val;
	private JFrame fen;

	public AccueilListener(int v, JFrame aFermer) {
        this.val = v;
        this.fen = aFermer;

    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
            /*
		switch (val){
		case 1:
			fen.setVisible(false); 
			NouvellePartie p;
			p= new NouvellePartie();
			val =-1;
			break;
		case 2 : 
			fen.setVisible(false);
			ChargerPartie cp;
			cp = new ChargerPartie();
			val =-1;
			break;
		case 3 :
			Popups pop = new Popups();
			pop.popRegles();
			val =-1;
			break;
		case 4 : 
			Popups poq =new Popups();
			poq.popQuitterMenu();
			val =-1;
			break;
		}
			*/
		
	}
}
