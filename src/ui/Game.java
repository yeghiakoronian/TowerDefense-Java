package ui;

import javax.swing.SwingUtilities;

import ui.game.MainFrame;

/**
 * this class is used for a game driver
 * @author team5
 *
 */
public class Game {

	private Game() {
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();

			}
		});
	}
}
