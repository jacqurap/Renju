package Vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Fenetre implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		
		frame.setSize(650, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AireDeJeu aire = new AireDeJeu();
		frame.addMouseListener(new EcouteurDeSouris(aire));
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(aire, BorderLayout.CENTER);	
	    
		//JMenu menu_file = new JMenu("Partie");
		//JMenuItem item_Nv_Part = new JMenuItem("Nouvelle Partie");
		//menu_file.add(item_Nv_Part);
		//item_Nv_Part.setEnabled(false);
		//JMenuBar barre = new JMenuBar();
        //barre.add(menu_file);
        //frame.setJMenuBar(barre);
		
		frame.setVisible(true);
		aire.repaint();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Fenetre());
    }
}
