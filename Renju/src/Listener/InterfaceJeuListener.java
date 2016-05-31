package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controleur.Partie;
import Vue.*;


public class InterfaceJeuListener implements ActionListener {
	
	private int val;
	private Popups popup;

	
	public InterfaceJeuListener(Popups pop, int v) {
        this.popup= pop;
        this.val = v;

    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
            
		switch (val){
		case 1:
			popup.popSauver();
			break;
		case 2:
			popup.popCharger();
			break;
		case 3:
			popup.popRecommencer();
			break;
		case 4:
			popup.popRecommencer();
			break;
		case 5:
			popup.popRegles();
			break;
		}
			
		
	}
}
