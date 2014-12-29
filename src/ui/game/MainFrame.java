package ui.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.Constants;
import core.applicationservice.gameservices.GameLogManager;
import core.applicationservice.gameservices.GameStateManager;
import core.applicationservice.mapservices.MapManager;
import core.domain.account.LifeManager;
import core.domain.maps.Grid;
import core.domain.maps.GridMap;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.waves.Wave;

/**
 * 
 * @author team5
 *
 */
public class MainFrame extends JFrame implements ActionListener {

	// MapPanel mapPanel;
	LayeredMapPanel mapPanel;

	private JPanel contentPane;

	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem openMenuItem;
	private JMenuItem loadGameMenutItem;
	private JMenuItem saveGameMenuItem;

	private GameInfoPanel gameInfoPanel;
	private EmptyBarPanel emptyBarPanel;
	private GameControllerPanel gameControllerPanel;
	private JMenuItem mntmLogViewer;

	private LogViewer logViewerDialog;
	private ScoreBoard scoreBoardDialog;

	private String mapFilePath = "";

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            for running
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		setUpMenuBar();

		setTitle(Constants.GAME_TITLE);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 470);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		gameInfoPanel = new GameInfoPanel();
		contentPane.add(gameInfoPanel, BorderLayout.NORTH);

		emptyBarPanel = new EmptyBarPanel();
		contentPane.add(emptyBarPanel, BorderLayout.WEST);

		EmptyBarPanel emptyBarPanel_1 = new EmptyBarPanel();
		contentPane.add(emptyBarPanel_1, BorderLayout.EAST);

		// mapPanel = new MapPanel();
		mapPanel = new LayeredMapPanel(getMapPanelDimention(), gameInfoPanel,
				this);
		// mapPanel.setBackground(Color.RED);
		contentPane.add(mapPanel, BorderLayout.CENTER);

		gameControllerPanel = new GameControllerPanel(mapPanel);
		contentPane.add(gameControllerPanel, BorderLayout.SOUTH);

		addMouseListener(new Handler(mapPanel));
		addMouseMotionListener(new Handler(mapPanel));

		setSize(713, 508);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		this.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				System.out.println("resized");
				mapPanel.resetSize(getMapPanelDimention());
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * 
	 * @return game control panel object 
	 * 
	 */
	public GameControllerPanel getGameControllerPanel() {
		return gameControllerPanel;
	}

	/**
	 * 
	 * @return computes the actual dimention of a map panel
	 */
	public Dimension getMapPanelDimention() {
		int width = gameInfoPanel.getWidth() - (emptyBarPanel.getWidth() * 2);
		int height = emptyBarPanel.getHeight();

		return new Dimension(width, height);
	}

	/**
	 * 
	 */
	private void setUpMenuBar() {
		menuBar = new JMenuBar();

		mapMenu = new JMenu("Actions");

		openMenuItem = new JMenuItem(Constants.LOAD_MAP);
		openMenuItem.addActionListener(this);
		mapMenu.add(openMenuItem);

		menuBar.add(mapMenu);

		loadGameMenutItem = new JMenuItem(Constants.LOAD_GAME);
		loadGameMenutItem.addActionListener(this);
		mapMenu.add(loadGameMenutItem);

		saveGameMenuItem = new JMenuItem(Constants.SAVE_GAME);
		saveGameMenuItem.addActionListener(this);
		mapMenu.add(saveGameMenuItem);

		mntmLogViewer = new JMenuItem("Log Viewer");
		mntmLogViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// try {

				if (logViewerDialog != null) {
					logViewerDialog.dispose();
				}
				logViewerDialog = new LogViewer(GameLogManager.getInstance());
				logViewerDialog
						.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				logViewerDialog.setSize(500, 500);
				logViewerDialog.setVisible(true);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
			}
		});
		mapMenu.add(mntmLogViewer);

		setJMenuBar(menuBar);

	}
	
	/**
	 * event that specifies which type of acr=tion is performed for a map
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String menuItem = event.getActionCommand();

		switch (menuItem) {
		case Constants.LOAD_MAP:
			loadMap();
			break;
		case Constants.SAVE_GAME:
			saveGame();
			break;
		case Constants.LOAD_GAME:
			loadGame();
			break;
		}

	}
	
	/**
	 * loads map from a file
	 */
	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				MapManager mapManager = new MapManager();
				mapFilePath = openFile.getSelectedFile().getAbsolutePath();
				GridMap grid = new GridMap(
						(Grid) mapManager.loadMapFromFile(mapFilePath));
				displayScoreBoard(grid);
				mapPanel.setGrid(grid);
				resetGameState();
				mapPanel.repaint();
				GameLogManager.getInstance().addWaveLog(1, "New wave started");
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}
	
	/**
	 * displays some user interface components 
	 * @param grid the grid object
	 */
	public void displayScoreBoard(Grid grid) {
		if (scoreBoardDialog != null) {
			scoreBoardDialog.dispose();
		}
		scoreBoardDialog = new ScoreBoard(grid);
		scoreBoardDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		scoreBoardDialog.setSize(500, 500);
		scoreBoardDialog.setVisible(true);
	}
	
	/**
	 * saves game into a file
	 */
	protected void saveGame() {
		JFileChooser saveFile = new JFileChooser();

		if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String fileName = saveFile.getSelectedFile().getAbsolutePath();
			GameStateManager game = new GameStateManager(mapPanel.getGridMap(),
					mapPanel.getWaveNumber(), mapFilePath);
			GameStateManager.save(fileName, game);
		}
	}
	
	/**
	 * loads game from a file
	 */
	protected void loadGame() {
		JFileChooser openFile = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
			String fileName = openFile.getSelectedFile().getAbsolutePath();
			GameStateManager game = GameStateManager.load(fileName);
			this.mapFilePath = game.getMapLocation();
			mapPanel.setGrid(game.getMap());
			mapPanel.setGameInfo(game);
			mapPanel.resetSize(getMapPanelDimention());
			mapPanel.repaint();
		}

	}
	
	/**
	 * 
	 */
	private void resetGameState() {
		mapPanel.setTowers(new Tower[(mapPanel.getGrid()).getWidth()][(mapPanel
				.getGrid()).getHeight()]);
		mapPanel.getBank().resetCurrentBalance();
		mapPanel.resetSize(getMapPanelDimention());
	}
	
	/**
	 * 
	 */
	public void disableSaveLoadMenu() {
		loadGameMenutItem.setEnabled(false);
		saveGameMenuItem.setEnabled(false);
	}
	
	/**
	 * 
	 */
	public void enableSaveLoadMenu() {
		loadGameMenutItem.setEnabled(true);
		saveGameMenuItem.setEnabled(true);
	}
	
	/**
	 * 
	 * @return valied path of a map
	 */
	public String getMapFilePath() {
		return mapFilePath;
	}
}
