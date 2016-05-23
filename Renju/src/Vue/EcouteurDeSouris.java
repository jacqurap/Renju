package Vue;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controleur.Partie;

public class EcouteurDeSouris implements MouseListener {

	public AireDeJeu aire;

	public EcouteurDeSouris( AireDeJeu a) {
		// TODO Auto-generated constructor stub
		aire = a;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = (int) e.getX()/40;
		int y = (int) e.getY()/40-1;
		System.out.println("x " +x+ " y " +y);
		if(aire.partie.action(new Point(x,y)))
			aire.repaint();


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

}
