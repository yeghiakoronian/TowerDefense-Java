package ui.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import core.domain.maps.Grid;
/**
 * 
 * @author team5
 *
 */
public class ScoreBoard extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private Grid grid;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			ScoreBoard dialog = new ScoreBoard();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

/**
 * Create the dialog
 * @param grid current grid used
 */
	public ScoreBoard(Grid grid) {
		this.grid = grid;
		setTitle("Score Board");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textArea = new JTextArea(getHighScores());
			textArea.setColumns(30);
			textArea.setRows(10);
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private String getHighScores() {
		return grid.getHighestScores(5);
	}

}
