package ui.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *  
 * @author Team5
 * <b>This class is responsible for drawing a critter. It receives an image (icon) and the coordinates of where it's supposed to be displayed.</b>
 */
@SuppressWarnings("serial")
public class CritterShape extends JComponent {


	public CritterShape() {

	}

	/**
	 * this method draws the image.
	 * @param g Graphics component
	 * @param image Critter image
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param hp Critter life
	 */
	public void draw(Graphics g, Icon image, int x, int y, int hp){
		super.paintComponent(g);
		Font  f= new Font("serif",Font.BOLD,12);
		g.setColor(Color.RED);
		g.setFont(f);
		g.drawString(new Integer(hp).toString(), x, y-5);
		image.paintIcon(this, g, x, y);
		g.setColor(Color.BLACK);


	}
}
