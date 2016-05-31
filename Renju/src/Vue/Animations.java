/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rohautb
 */
public class Animations {
    long timer = 250; //temps en ms entre l'affichage de 2 lettres
    String message = "Victoire"; //eébut ou fin du message a afficher
    public JPanel pan;
    ImageIcon icon = new ImageIcon("/home/r/rohautb/TestAnimation/loading.gif");
    JLabel copyLabel = new JLabel(icon);
    /**
     * @param pan = panneau où s'affichera le message
     * @param name = pseudo du joueur victorieux
     */
    public void win(JPanel pan, String name){
        Font font = new Font("Courrier New", Font.PLAIN, 40);
        JLabel game = new JLabel("", JLabel.CENTER);
        String gj;
        gj = (message + '\n' + name);
        game.setForeground(Color.BLUE);
        game.setFont(font);
        pan.add(game);
        for (int i = 1; i <= gj.length(); i++){
                game.setText(gj.substring(0,i));
                pan.repaint();
                try {
                  Thread.sleep(timer);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
        }
    }
    
    public void loading(JPanel p){
        this.pan = p;
        pan.add(copyLabel);
	pan.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent me){
			copyLabel.setLocation(me.getX(),me.getY());
			System.out.println(me.getX() + ";" + me.getY());
			pan.repaint();
                        pan.remove(copyLabel);
		}
	});
    }
}
