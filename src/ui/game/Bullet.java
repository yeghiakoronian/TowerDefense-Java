package ui.game;

import java.awt.Graphics;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import core.contract.MapConstants;

/**
 * 
 * @author team5
 *
 */
public class Bullet extends JComponent implements Runnable {
	public int xC = 0, yC = 0;
	int xPos = 50;
	int x, y;
	Icon critterImg;

	Icon pathImg;

	/**
	 * public constructor of the class
	 * 
	 * @param initX destination x for shooting
	 * @param initY destination y for shooting
	 */
	public Bullet(int initX, int initY) {
		this.x = initX;
		this.y = initY;
		this.xPos = initX;

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("bullet.png").getFile());
		// critterImg = new ImageIcon(file.getPath()).getImage();
		this.critterImg = new ImageIcon(file.getPath());

		file = new File(classLoader.getResource(MapConstants.PATH_IMG)
				.getFile());
		pathImg = new ImageIcon(file.getPath());
	}

	/**
	 * computes the current position for shooting
	 */
	public void physic() {
		if (xPos > x + 500) {
			xPos = x;
		}
		xPos++;
	}

	/** 
	 * 
	 */
	public void run() {

		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			if (xPos > x + 200) {
				xPos = x;
			}
			xPos++;
		}
	}

	/**
	 * draws a bullet in a current given position
	 */
	public void paintComponent(Graphics g) {

		draw(g);

	}

	/**
	 * draws the bullet
	 * @param g - graphics object for drawing
	 */
	public void draw(Graphics g) {
		super.paintComponent(g);

		critterImg.paintIcon(this, g, xPos, y);

	}
}
