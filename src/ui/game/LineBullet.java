package ui.game;

import java.awt.Graphics;

import javax.swing.JComponent;

import core.domain.waves.Position;
/**
 * this class is used for shooting algorithm
 * @author team5
 *
 */
public class LineBullet extends JComponent {
	/**
	 * public constructor of the class
	 */
	public LineBullet(){
	}
	/**
	 * 
	 * @param g Graphics object used for drawing
	 * @param source shooter
	 * @param target target point for shooting 
	 */
	public void draw(Graphics g, Position source, Position target){
		super.paintComponent(g);
		g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
	}
}
