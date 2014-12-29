package ui.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import core.domain.account.LifeManager;

/**
 * 
 * @author Team5
 * <b>creates a panel for a game controller</b>
 */
public class GameControllerPanel extends JPanel {

	private JToggleButton tglbtnNewToggleButton_1;
	private String gameState;
	private LayeredMapPanel mapPanel;

	/**
	 * Create the panel , for controlling the game status,
	 * the user can click certain buttons such as new game or critter info
	 * each one of this buttons will call specific object to reinitialize and show for
	 * the user
	 * @param mapPanel receive a panel to create new one
	 */
	public GameControllerPanel(LayeredMapPanel mapPanel) {
		this.gameState = "completed";
		this.mapPanel = mapPanel;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JToggleButton tglbtnNewToggleButton = new JToggleButton("Critter Info");
		tglbtnNewToggleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// mapPanel.critterT.start();

				// LayeredMapPanelOtherItems otherItemsPanel =
				// mapPanel.getOtherItemsPanel();
				// otherItemsPanel.performScene();
			}
		});
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnNewToggleButton.gridx = 0;
		gbc_tglbtnNewToggleButton.gridy = 0;
		add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);

		tglbtnNewToggleButton_1 = new JToggleButton("New Wave");
		GridBagConstraints gbc_tglbtnNewToggleButton_1 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnNewToggleButton_1.gridx = 1;
		gbc_tglbtnNewToggleButton_1.gridy = 0;
		add(tglbtnNewToggleButton_1, gbc_tglbtnNewToggleButton_1);
		tglbtnNewToggleButton_1.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * @param arg0 catches event from mouse once clicked it will start the game
			 */
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Start New Wave");
				// mapPanel.getOtherItemsPanel().mapT.start();
				
				performRequestedAction();
			}
		});

		TowerPanel towerPanel = new TowerPanel();
		GridBagConstraints gbc_towerPanel = new GridBagConstraints();
		gbc_towerPanel.fill = GridBagConstraints.BOTH;
		gbc_towerPanel.gridx = 2;
		gbc_towerPanel.gridy = 0;
		add(towerPanel, gbc_towerPanel);

	}
	
	/**
	 * The user can can pause a game and later resume it
	 * this gets the current state and acts accordingly
	 */
	protected void performRequestedAction() {
		System.out.println(gameState);
		switch (gameState) {
		case "pause":
			mapPanel.getOtherItemsPanel().resumeGame();
			tglbtnNewToggleButton_1.setText("Pause Game");
			this.gameState = "running";
			System.out.println(1);
			break;
		case "running":
			mapPanel.getOtherItemsPanel().pauseGame();
			tglbtnNewToggleButton_1.setText("Resume Game");
			this.gameState = "pause";
			System.out.println(2);
			break;
		case "completed":
			mapPanel.getOtherItemsPanel().startFoolishWave();
			this.gameState = "running";
			System.out.println(3);
			tglbtnNewToggleButton_1.setText("Pause Game");
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * Once a wave is completed , enable the user to start a new wave
	 * and reinitialize some instances
	 * @param waveNumber - the wave number
	 */
	public void wavaCompleted(int waveNumber) {
		this.gameState = "completed";
		tglbtnNewToggleButton_1.setText("New Wave");
//		int waveNum = (mapPanel.getGameInfoPanel()).getWave();
		(mapPanel.getGameInfoPanel()).setWave(waveNumber);
//		(mapPanel.getGameInfoPanel()).setLife(life);
		LifeManager lifeManager = LifeManager.getInstance();
//		lifeManager.addLife(5);
//		(mapPanel.getGameInfoPanel()).setLife(lifeManager.getLife());
		(mapPanel.getGameInfoPanel()).repaint();
	}
}
