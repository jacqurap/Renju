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
    long timer = 30; //temps en ms entre l'affichage de 2 lettres
    ImageIcon icon = new ImageIcon("/home/r/rohautb/TestAnimation/loading.gif");
    JLabel copyLabel = new JLabel(icon);
    /**
     * @param pan = panneau où s'affichera le message
     * @param message = message a afficher
     */
    public void win(JPanel pan, String message){
        Font font = new Font("Calibri", Font.BOLD, 40);
        JLabel game = new JLabel("", JLabel.CENTER);
        pan.setOpaque(false);
        String gj;
        gj = message;
        game.setForeground(Color.WHITE);
        game.setFont(font);
        pan.add(game);
        for (int i = 0; i <= gj.length(); i++){
                game.setText(gj.substring(0,i));
                pan.repaint();
                try {
                  Thread.sleep(timer);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
        }
    }
    
    public void loading(final JPanel pan){
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
