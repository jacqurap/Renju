package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import Controleur.Partie;
import Modele.Humain;
import Modele.Plateau;

public class AireDeJeu extends JComponent{
	
	public Partie partie;
	
	public AireDeJeu(){
		partie = new Partie("Modele.Humain", "toto", "Modele.Ia1", "titi");
	}
	
	@Override
    public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
        drawable.setColor(Color.PINK);
        drawable.fillRect(0, 0, 640, 640);
        drawable.setColor(Color.black);
        for (int i = 0;i<15;i++ ){
        	drawable.drawLine(i*40+20, 20, i*40+20, 580);
        	drawable.drawLine(20, i*40+20, 580, i*40+20);
        }
        for(int i = 0 ; i < 15 ; i++){
        	for(int j=0; j < 15 ; j++){
        		if(partie.getPlateau().getCase(i, j) == Plateau.CASEBLANCHE){
        			drawable.setColor(Color.white);
        			drawable.drawOval(i*40+10, j*40+10, 20, 20);
        		}
        		else if(partie.getPlateau().getCase(i, j) == Plateau.CASENOIRE){
        			drawable.setColor(Color.BLACK);
        			drawable.drawOval(i*40+10, j*40+10, 20, 20);
        		}
        	}
        }
	}
	
    public Partie getPartie() {
        return this.partie;
    }
}
