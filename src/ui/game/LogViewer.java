package ui.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import core.applicationservice.gameservices.GameLogManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author team5
 *
 */
public class LogViewer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private GameLogManager gameLogManager;
	private JComboBox comboBox;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// try {
	// LogViewer dialog = new LogViewer();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }


	
	/**
	 * Create the dialog
	 * @param gameLogManager is used for game log 
	 */
	public LogViewer(GameLogManager gameLogManager) {
		this.gameLogManager = gameLogManager;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JComboBox comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getLog(comboBox.getSelectedItem());
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(populateList()));
			contentPanel.add(comboBox);
		}
		{
			textArea = new JTextArea();
			textArea.setColumns(50);
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
		getGlobalGameLog();
	}
	
	/**
	 * 
	 * @param selectedItem
	 */
	protected void getLog(Object selectedItem) {
		try {
			if (selectedItem instanceof String) {
				String str = (String) selectedItem;
				if (str == "Global Log") {
					getGlobalGameLog();
				} else if (str == "Collective Tower Log") {
					textArea.setText(gameLogManager.getCollectiveTowerLog());
				} else if (str.substring(0, str.indexOf(":")).equalsIgnoreCase(
						"Wave")) {
					int waveNum = new Integer(
							(str.substring(str.indexOf(":") + 1).trim()))
							.intValue();
					textArea.setText(gameLogManager
							.getIndividualWaveLog(waveNum));
				} else if (str.substring(0, str.indexOf(":")).equalsIgnoreCase(
						"Tower")) {
					String towerId = (str.substring(str.indexOf(":") + 1)
							.trim());
					textArea.setText(gameLogManager
							.getIndividualTowerLog(towerId));
				}
			}
		} catch (Exception ex) {
		}
	}

	/**
	 * @return
	 */
	private Object[] populateList() {
		ArrayList<String> list = new ArrayList<String>();

		ArrayList<String> waves = gameLogManager.getWaves();
		ArrayList<String> towers = gameLogManager.getTowers();

		// list = new String[2+ waves.size()+towers.size()];
		list.add("Global Log");
		list.add("Collective Tower Log");
		list.addAll(waves);
		list.addAll(towers);

		return list.toArray();
	}
	
	/**
	 * 
	 */
	private void getGlobalGameLog() {
		textArea.setText(gameLogManager.getGlobalLog());
	}

}
