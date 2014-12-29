package ui.game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 * @author Team5
 *
 */
public class EmptyBarPanel extends JPanel {

	/**
	 * Create the panel, for the empty bar
	 */
	public EmptyBarPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 10;
		setPreferredSize(dim);
		setBackground(Color.BLACK);
	}

}
