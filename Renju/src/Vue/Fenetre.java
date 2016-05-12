package Vue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Fenetre implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		AireDeJeu aire = new AireDeJeu();
		frame.add(aire);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(800, 800);
	    frame.setVisible(true);
		aire.repaint();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Fenetre());
    }
}
