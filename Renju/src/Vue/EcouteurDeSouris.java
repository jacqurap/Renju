package Vue;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controleur.Partie;

public class EcouteurDeSouris implements MouseListener {

	private AireDeJeu aire;
	

	public EcouteurDeSouris( AireDeJeu a) {
		// TODO Auto-generated constructor stub
		aire = a;

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
            if(!aire.getPartie().isPartieFinie()){
                int caseSize = (aire.getHeight()/16);
		int x = (int) ((e.getX()-(caseSize/2))/caseSize);
		int y = (int) ((e.getY()-(caseSize/2))/caseSize);
                
		System.out.println("x " +x+ " y " +y);
		if((x<15 && y<15) && aire.getPartie().click(new Point(x,y))){ //15 pour les coups en dehors de la zone de jeu
			aire.repaint();}
		
		/*if(aire.getPartie().getNbCoups() %2 == 0){
            interfacejeu.getTfJ1().setForeground(Color.RED);
            interfacejeu.getTfJ2().setForeground(Color.BLACK);
    	}
    	else{
    		interfacejeu.getTfJ2().setForeground(Color.RED);
            interfacejeu.getTfJ1().setForeground(Color.BLACK);
    	}*/

            }
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public AireDeJeu getAire() {
		return aire;
	}

	public void setAire(AireDeJeu aire) {
		this.aire = aire;
	}



}
