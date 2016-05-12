package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class AireDeJeu extends JComponent{
	
	public AireDeJeu(){
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
        drawable.setColor(Color.DARK_GRAY);
        drawable.fillRect(0, 0, 640, 640);
        drawable.setColor(Color.black);
        for (int i = 0;i<16;i++ ){
        	drawable.drawLine(i*40+20, 20, i*40+20, 620);
        	drawable.drawLine(20, i*40+20, 620, i*40+20);
        }
	}
}
