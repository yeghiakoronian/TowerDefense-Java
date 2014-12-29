package ui.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.Constants;
import core.applicationservice.mapservices.connectivity.imp.StartEndChecker;
/**
 * this class is used for a game frame. Is the main part of user interface
 * @author vika
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private MapEditorPanel mapPanel;
	private int width;
	private int height;
	private JTextField widthTextField;
	private JTextField heightTextField;
	JDialog mapSizeDialog;

	/**
	 * Constructs Editor's main frame. 
	 */
	public MainFrame() {
		setup();
		setUpMenuBar();
		setVisible(true);
	}

	/**
	 * this method is used for initial  set up parameters, like layout of a game frame
	 */
	private void setup() {
		setTitle(Constants.EDITOR_TITLE);
		refresh();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		mapPanel = new MapEditorPanel(8,8);
	}

	/**
	 * this method is used when there is a need to change the size of a game frame 
	 */
	private void refresh() {
		setSize(700, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

	/**
	 * this method constracts a menue for a game
	 */
	private void setUpMenuBar() {
		menuBar = new JMenuBar();

		mapMenu = new JMenu(Constants.MAP_MENU);

		newMenuItem = new JMenuItem(Constants.DESIGN_MAP);
		openMenuItem = new JMenuItem(Constants.LOAD_MAP);
		saveMenuItem = new JMenuItem(Constants.SAVE_MAP);

		newMenuItem.addActionListener(this);
		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);

		mapMenu.add(newMenuItem);
		mapMenu.add(openMenuItem);
		mapMenu.add(saveMenuItem);

		menuBar.add(mapMenu);

		setJMenuBar(menuBar);
		
	}

	/** 
	 * <b>Based on the menu, an action is selected.</b>
	 */
	public void actionPerformed(ActionEvent event) {
		String menuItem = event.getActionCommand();

		switch (menuItem) {
		case Constants.DESIGN_MAP:
			getNewMapSize();
			break;
		case Constants.LOAD_MAP:
			mapPanel.loadMap();
			continueMapDesign();
			break;
		case Constants.SAVE_MAP:
			mapPanel.saveMap();
			break;
		case Constants.OK:
			setMapSize();
			break;
		case Constants.CANCEL:
			closeMapSizeDialog();
			break;
		}

	}

	/**
	 * is used when the size of a map is set and dialog has to be closed
	 */
	private void closeMapSizeDialog() {
		mapSizeDialog.dispose();
		
	}

	/**
	 * is used when external user needs to change the size of a map
	 */
	private void setMapSize() {
		try {
			width = (new Integer(widthTextField.getText())).intValue();
			height = (new Integer(heightTextField.getText())).intValue();
			StartEndChecker checker = new StartEndChecker();
			if(!checker.isCorrectSize(height, width))
				throw new Exception();
			startMapDesign();
			closeMapSizeDialog();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Please enter correct Size!", "Alert",
			        JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * is called when used needs to design a new map for a game
	 */
	private void startMapDesign() {
		mapPanel.setGridSize(width, height);
		mapPanel.setMapSize(width, height);
		continueMapDesign();
	}

	/**
	 * is used when user wants to make change on akready designed map
	 */
	private void continueMapDesign() {
		add(mapPanel, BorderLayout.CENTER);
		refresh();
		
	}

	/**
	 * <b>This method creates a dialog to allow the user to input the map size.</b>
	 */
	private void getNewMapSize() {
		mapSizeDialog = new JDialog(this, true); // parent, isModal
		mapSizeDialog.setTitle(Constants.MAP_SIZE);
		mapSizeDialog.setSize(200, 150);
		mapSizeDialog.setLocationRelativeTo(this);

		widthTextField = new JTextField("10", 1);
		heightTextField = new JTextField("10", 1);

		JLabel widthLable = new JLabel(Constants.WIDTH);
		JLabel heightLable = new JLabel(Constants.HEIGHT);

		JButton okButton = new JButton(Constants.OK);
		JButton cancelButton = new JButton(Constants.CANCEL);

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setSize(100, 200);

		// This section is to layout the form
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createSequentialGroup().addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(widthLable)
										.addComponent(heightLable)
										.addComponent(cancelButton)))
				.addGroup(
						layout.createSequentialGroup().addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(widthTextField)
										.addComponent(heightTextField)
										.addComponent(okButton))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(widthLable)
								.addComponent(widthTextField))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(heightLable)
								.addComponent(heightTextField))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(cancelButton)
								.addComponent(okButton)));
		panel.setLayout(layout);
		// Form layout done!

		mapSizeDialog.add(panel);
		mapSizeDialog.setVisible(true);

	}
}
