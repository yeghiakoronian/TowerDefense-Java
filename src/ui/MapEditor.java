package ui;

import javax.swing.SwingUtilities;

import ui.editor.MainFrame;

/**
 * this class is used  for game driver when there is a need for editing map
 * @author team5
 *
 */
public class MapEditor {

	private MapEditor() {
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
