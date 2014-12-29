package ui.game;

import infrastructure.loggin.Log4jLogger;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.towerdesign.InspectionPanel;
import core.applicationservice.gameservices.GameLogManager;
import core.applicationservice.gameservices.GameStateManager;
import core.applicationservice.informerservices.imp.DefenderInformer;
import core.applicationservice.locationservices.PositionService;
import core.applicationservice.mapservices.MapCellService;
import core.applicationservice.mapservices.MapManager;
import core.applicationservice.mapservices.pathfinder.PathService;
import core.applicationservice.warriorservices.SpecialDamageEffectsServiceBurn;
import core.applicationservice.warriorservices.SpecialDamageEffectsServiceSplash;
import core.applicationservice.warriorservices.TowerFactory;
import core.applicationservice.warriorservices.WaveFactory;
import core.contract.DefenderConstants;
import core.contract.WaveConstants;
import core.domain.account.BankManager;
import core.domain.account.LifeManager;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
import core.domain.maps.GridMap;
import core.domain.maps.VisualGrid;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.behaviourimp.RegularMove;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;
import core.domain.waves.Wave;

/**
 * @author Team 5 This Class is a Jpanel that gets rendered as a layer in
 *         LayeredMapPanel class. It is responsible for tasks related to Towers
 *         and Critters on the ui side.
 */

@SuppressWarnings("serial")
public class LayeredMapPanelOtherItems extends JPanel implements Observer,
		Runnable {

	private Tower[][] towers;
	private int x, y;
	private BankManager bank;
	private long availFunds;
//	private Point mapTopLeft;
//	private Point mapButtomRight;
	private GridMap grid;
	private InspectionPanel inspection;
	public Thread critterT, mapT;
	private Cell cell;
	private Position[] path;
	private Wave wave;
	private int waveNumber = 1;

	private Icon[] critterImage;

	private boolean waveStarted;

	private DefenderInformer informer;
	private Tower defender;
	private Critter target;

	private Map<Tower, Critter> defenderTargetPair;

	private GameInfoPanel gameInfoPanel;

	private List<Critter> currentWaveAlienList;

	private MainFrame mainFrame;
	private static final Log4jLogger logger = new Log4jLogger();

	private LifeManager life = LifeManager.getInstance();

	private int bulletCounter = 0;
	private int sleepLength = 10;
	private HashMap<Tower, Integer> shootingSchedule = new HashMap<Tower, Integer>();

	private SpecialDamageEffectsServiceBurn burnEffect = new SpecialDamageEffectsServiceBurn();
	private SpecialDamageEffectsServiceSplash splashEffect = new SpecialDamageEffectsServiceSplash();
	
	private MapCellService mapCellService = new MapCellService();

	/**
	 * @param dimension
	 *            height and width of the panel
	 * @param gameInfoPanel
	 *            a reference to the instance of the game info panel that
	 *            appears on top of the game ui (life, bank, wave info)
	 * @param mainFrame
	 *            a reference to the instance of the main frame that contains ui
	 *            components.
	 */
	public LayeredMapPanelOtherItems(Dimension dimension,
			GameInfoPanel gameInfoPanel, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameInfoPanel = gameInfoPanel;
		this.grid = new GridMap(1, 1);
		this.bank = BankManager.getInstance();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());

		mapT = new Thread(this);
		mapT.start();
		setOpaque(false);
		setDimension(dimension);
		critterImage = new Icon[WaveConstants.WAVE_SIZE];
		informer = new DefenderInformer();
		defenderTargetPair = new HashMap<Tower, Critter>();

		gameInfoPanel.setWave(waveNumber);
	}

	/**
	 * this re-initializes the grid when a map is loaded/
	 * 
	 * @param grid
	 *            the map that is loaded from a file
	 */
	public void setGrid(GridMap grid) {
		cell = new Cell();
		this.grid = grid;

		towers = new Tower[grid.getWidth()][grid.getHeight()];

	}

	/**
	 * Calculates the critters' starting point on the map in pixels.
	 * 
	 * @return initial position of the critters.
	 */
	protected Position calcCritterStartingPoint() {
		// temporarily until we find a better solution
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());
		// end
		return mapCellService.getCellPixel(grid.getEntranceLocation());
	}

	/**
	 * This method draws everything that needs to be drawn at this iteration.
	 * 
	 * @param g
	 *            Graphics component
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int initX = (int) mapCellService.getMapTopLeft().getX();
		int initY = (int) mapCellService.getMapTopLeft().getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth()
				* grid.getUnitSize(), initY + grid.getHeight()
				* grid.getUnitSize()));

		if (grid.getTowers() == null) {
			grid.setTowers(new Tower[1][1]);
		}
		for (int x = 0; x < grid.getWidth(); x++) {
			for (int y = 0; y < grid.getHeight(); y++) {
				int xCoordinate = grid.getUnitSize() * x + initX;
				int yCoordinate = grid.getUnitSize() * y + initY;
				if (grid.getCell(x, y) == GridCellContentType.TOWER) {
					cell.draw(g, grid.getCell(x, y), grid.getTowers(),
							xCoordinate, yCoordinate, x, y, true);
				}
			}
		}

		if (waveStarted) {
			for (int i = 0; i < currentWaveAlienList.size(); i++) {
				Position pos = ((RegularMove) (currentWaveAlienList.get(i)
						.getMovingBehaviour())).getPixelPosition();
				new CritterShape().draw(g, critterImage[i], pos.getX(),
						pos.getY(), currentWaveAlienList.get(i).getLife());
			}

			Iterable<Entry<Tower, Critter>> its = defenderTargetPair.entrySet();
			for (Entry<Tower, Critter> pairs : its) {
				Tower t2 = pairs.getKey();
				Critter c = pairs.getValue();
				if (isWithinRateOfFire(t2)) {
					PositionService positionService = new PositionService();
					TowerFactory factory = new TowerFactory();
					int range = factory.getRange(t2);
					if (positionService.isInRange(t2.getTowerPosition(),
							c.getPath()[c.getCurrentPosition()], range)) {
						new LineBullet()
								.draw(g, mapCellService.convertCellToPixel(t2
										.getTowerPosition()),
										mapCellService.convertCellToPixel(path[c
												.getCurrentPosition()]));
					}
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

	/**
	 * Displays the tower upgrade panel.
	 */
	private void towerUpgradePanels() {
		if (towers[x][y] != null) {

			if (inspection != null) {
				inspection.close();
				inspection = null;
			}
			inspection = new InspectionPanel(towers[x][y]);
			inspection.addObserver(this);
		}
	}

	/**
	 * This method adds a tower to the map and also displays in the UI. Before
	 * adding the tower it makes sure the user has enough money.
	 * 
	 * @param x 
	 *            x coordinate of the cell that towers is going to be placed.
	 * @param y
	 *            y coordinate of the cell that towers is going to be placed.
	 */
	private void addTower(int x, int y) {

		if (grid.getCell(x, y) == GridCellContentType.SCENERY) {
			TowerFactory factory = new TowerFactory();
			Tower tower;
			String towerType = SelectedTower.getTowerType();
			switch (towerType) {
			case DefenderConstants.MODERN_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
						TowerLevel.one);
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.ANCIENT_TOWER_TYPE,
						TowerLevel.one);
				break;
			case DefenderConstants.KING_TOWER_TYPE:
				tower = factory.getTower(DefenderConstants.KING_TOWER_TYPE,
						TowerLevel.one);
				break;

			default:
				tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
						TowerLevel.one);
			}

			tower.setShootingStrategy(DefenderConstants.NearToEnd_Strategy);
			if (tower.cost() < bank.getBalance() - bank.getCurrentBalance()) {
				bank.setCurrentBalance(tower.cost());
				availFunds = this.bank.getBalance()
						- this.bank.getCurrentBalance();
				gameInfoPanel.setBank((int) availFunds);
				towers[x][y] = tower;
				tower.setTowerPosition(new Position(x, y));
				grid.setCell(x, y, GridCellContentType.TOWER);
				grid.setTowers(towers);
				GameLogManager.getInstance().addTowerLog(waveNumber, tower,
						"Placement");
				informer.registerObserver((TowerFeatureDecorator) tower);
				((TowerFeatureDecorator) tower).addObserver(this);
				shootingSchedule.put(tower, 0);
				repaint();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"you don't have enough money :(", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}
			SelectedTower.setInstance(SelectedTower.getTowerType(),
					SelectedTower.getTower(), false);
		}
	}

	public void towerOperation() {

		boolean addTowerFlag = SelectedTower.getAddTowerFlag();
		if (addTowerFlag) {
			addTower(x, y);
		} else {
			towerUpgradePanels();
		}

	}

	/**
	 * When a mouse click occurs, this method converts the pixel location of the
	 * pointer to a cell location.
	 * 
	 * @param mouseX
	 *            x coordinate of mouse pointer
	 * @param mouseY
	 *            y coordinate of mouse pointer
	 */
	public void setCellLocation(int mouseX, int mouseY) {
		int i = (mouseX - (int) mapCellService.getMapTopLeft().getX() - 75 / 2)
				/ grid.getUnitSize();
		int j = (mouseY - (int) mapCellService.getMapTopLeft().getY() - 120) / grid.getUnitSize();
		this.x = i;
		this.y = j;
	}

	public Tower[][] getTowers() {
		return towers;
	}

	public GridMap getGrid() {
		return grid;
	}

	public void setTowers(Tower[][] towers) {
		this.towers = Arrays.copyOf(towers, towers.length);
	}

	public BankManager getBank() {
		return bank;
	}

	/**
	 * <b>This method updates the tower stats and the bank balance and removes
	 * the a tower from the map. It also performs the required actions after a
	 * target has been identified for a tower.</b>
	 * 
	 * @param arg1
	 *            is object is of type tower perform operation
	 * @param subject
	 *            observer object
	 */
	@Override
	public void update(Observable subject, Object arg1) {
		if (subject instanceof InspectionPanel) {
			notifiedByInspectionPanel();
		}

		// shoot
		if (subject instanceof TowerFeatureDecorator) {
			shootingOps((TowerFeatureDecorator) subject);
		}
	}

	private void shootingOps(TowerFeatureDecorator subject) {
		target = subject.getTarget();
		defender = subject.getDefender();

		Tower t2 = defender;
		Critter c = target;

		PositionService positionService = new PositionService();
		TowerFactory factory = new TowerFactory();
		int range = factory.getRange(t2);
		if (positionService.isInRange(t2.getTowerPosition(),
				c.getPath()[c.getCurrentPosition()], range)) {
			shoot(defender, target);
			removeDeadCritters();
		}
	}

	private void notifiedByInspectionPanel() {
		switch (inspection.getPerformedAction()) {
		case "Upgrade":
			upgradeTower();
			break;
		case "Sell":
			clearTower(x, y);
			break;
		default:
			break;
		}
	}

	/**
	 * This method does the tasks that happen when a critter is shot by a tower,
	 * like decreasing life.
	 * 
	 * @param defender
	 *            tower (shooter)
	 * @param target
	 *            critter (to be shot)
	 */
	private void shoot(Tower defender, Critter target) {
		defenderTargetPair.put(defender, target);
		if (shootingSchedule.get(defender) >= 100) {
			shootingSchedule.put(defender, 0);
		} else {
			shootingSchedule.put(defender, shootingSchedule.get(defender) + 1);
		}

		if (isWithinRateOfFire(defender)) {
			TowerFactory factory = new TowerFactory();
			String defenderType = factory
					.getDecoratedName(defender.getTowers());
			switch (defenderType) {
			case DefenderConstants.KING_TOWER_TYPE:
				shootAndBurn(defender, target);
				break;
			case DefenderConstants.MODERN_TOWER_TYPE:
				splashEffect.splash(defender, target, wave, getTowerPropertyBasedOnLevel(defender), path);
				break;
			case DefenderConstants.ANCIENT_TOWER_TYPE:
				PositionService positionService = new PositionService();
				int range = factory.getRange(defender);
				if (positionService.isInRange(defender.getTowerPosition(),
						target.getPath()[target.getCurrentPosition()], range))
					((RegularMove) target.getMovingBehaviour())
							.setFreezeTime(100);
				break;
			}
			try {
			} catch (Exception e2) {
				logger.writer("shooting :" + defender.Id + " --> " + target.Id
						+ "(" + target.getCurrentPosition() + ")", e2);

				System.out.println("shooting :" + defender.Id + " --> "
						+ target.Id + "(" + target.getCurrentPosition() + ")");
			}
		}

	}

	private void shootAndBurn(Tower defender, Critter target) {
		int power = getTowerPropertyBasedOnLevel(defender);
		target.setLife(target.getLife() - power);
		burnEffect.addBurningCritter(target, getTowerPropertyBasedOnLevel(defender) * sleepLength);
	}
	
	private void burn() {
		burnEffect.burn();
		removeDeadCritters();
	}


	private int getTowerPropertyBasedOnLevel(Tower defender) {
		switch (defender.getLevel()) {
		case one:
			return 1;
		case two:
			return 2;
		case three:
			return 3;
		}
		return 1;
	}

	private boolean isWithinRateOfFire(Tower defender) {
		int rateOfFire = getTowerPropertyBasedOnLevel(defender);

		if (shootingSchedule.get(defender) <= rateOfFire * sleepLength * 3) {
			return true;
		}
		return false;
	}

	/**
	 * This method is called when the user has finished upgrading a tower.
	 */
	private void upgradeTower() {
		towers[x][y] = inspection.getTower();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
		gameInfoPanel.setBank((int) availFunds);
		GameLogManager.getInstance().addTowerLog(waveNumber, towers[x][y],
				"Upgrade");
		repaint();
	}

	/**
	 * When a user sells a tower, this method get's called to remove it from the
	 * map.
	 * 
	 * @param x
	 *            x location of the tower to be removed
	 * @param y
	 *            y location of the tower to be removed.
	 */
	private void clearTower(int x, int y) {
		if ((x < grid.getWidth()) && (y < grid.getHeight())
				&& (grid.getCell(x, y) == GridCellContentType.TOWER)) {
			availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();
			gameInfoPanel.setBank((int) availFunds);

			GameLogManager.getInstance().addTowerLog(waveNumber, towers[x][y],
					"Sell");

			informer.removeObserver((TowerFeatureDecorator) towers[x][y]);
			defenderTargetPair.remove(towers[x][y]);
			shootingSchedule.remove(towers[x][y]);
			towers[x][y] = null;
			grid.setTowers(towers);
			grid.setCell(x, y, GridCellContentType.SCENERY);
			repaint();
		}
	}

	/**
	 * This method runs as long as the thread is running to update the screen
	 * and all properties of this class.
	 */
	public void run() {
		while (true) {
			System.out.print("");
			if (waveStarted) {
				updateBulletCounter();
				for (int j = 0; j < currentWaveAlienList.size(); j++) {
					Critter critter = currentWaveAlienList.get(j);
					if (critter.getCurrentPosition() != ((RegularMove) critter
							.getMovingBehaviour()).getPath().length - 1) {
						critter.performMovingBehaviour();
						int i = ((RegularMove) critter.getMovingBehaviour())
								.getCurrentPosition();
						Position p = path[i];
						critter.setCurrentPosition(i);
						if (towers[x][y] != null) {
							informer.setAlienPosition(p.getX(), p.getY(),
									critter, towers[x][y].getShootingStrategy());
						}
						burn();
					} else {
						System.out.println("At exit point.");
						updatePlayerLife(1);
						currentWaveAlienList.remove(j);
						if (isWaveComplete()) {
							waveCompleted();
						}

					}
				}
				repaint();
				try {
					Thread.sleep(sleepLength);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private void updateBulletCounter() {
		if (this.bulletCounter >= sleepLength * 100) {
			this.bulletCounter = 0;
		} else {
			this.bulletCounter++;
		}
	}

	/**
	 * This method checks the wave to see if there are any critters left in it.
	 * 
	 * @return true if there all critters are killed.
	 */
	private boolean isWaveComplete() {
		if (currentWaveAlienList.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method is called every time a critter is shot. it removes dead
	 * critters from the map.
	 */
	private void removeDeadCritters() {
		for (int i = 0; i < currentWaveAlienList.size(); i++) {
			Critter c = currentWaveAlienList.get(i);
			if (c.getLife() <= 0) {
				bank.addBalance(((long) c.lifeBooster()));
				availFunds = (long) (this.bank.getBalance() + c.lifeBooster());
				gameInfoPanel.setBank((int) availFunds);
				for (Tower[] t1 : towers) {
					for (Tower t : t1) {
						if (t != null) {
							Map<Critter, Position> map = ((TowerFeatureDecorator) t)
									.getCrittersLocation();

							map.remove(c);
							((TowerFeatureDecorator) t)
									.setCrittersLocation(map);
							System.out.println(c.Id);
						}
					}
				}
				currentWaveAlienList.remove(i);
				if (isWaveComplete()) {
					waveCompleted();
				}
			}
		}
	}

	private void waveCompleted() {
		System.out.println("Wave is completed!");
		GameLogManager.getInstance().addWaveLog(waveNumber, "Wave completed.");
		mainFrame.getGameControllerPanel().wavaCompleted(waveNumber++);
		GameLogManager.getInstance().addWaveLog(waveNumber, "New wave started");
		waveStarted = false;
		mainFrame.enableSaveLoadMenu();
	}

	/**
	 * when a critter reaches the end of the path, it decreases the player's
	 * life.
	 * 
	 * @param escapedCritters
	 *            the number of critters that have reached the exit point.
	 */
	private void updatePlayerLife(int escapedCritters) {

		life.setLife(life.getLife() - escapedCritters);
		System.out.println("life: " + life.getLife());
		gameInfoPanel.setLife(life.getLife());
		if (life.getLife() <= 0) {
			gameOver();
		}
	}

	private void gameOver() {
		saveScore();
		System.out.println("Game Over");
		mainFrame.displayScoreBoard(grid);
		JOptionPane.showMessageDialog(null, "Game Over");
		System.exit(0);
	}

	private void saveScore() {
		grid.addPlayLog(new Date().toString(), this.bank.getBalance()
				- this.bank.getCurrentBalance());

		VisualGrid vg = new VisualGrid((Grid) grid);
		removeTowers(vg);
		(new MapManager()).savePlayLog(vg, mainFrame.getMapFilePath());

	}

	private void removeTowers(VisualGrid vg) {
		for (int i = 0; i < vg.getWidth(); i++) {
			for (int j = 0; j < vg.getHeight(); j++) {
				if (vg.getCell(i, j) == GridCellContentType.TOWER) {
					vg.setCell(i, j, GridCellContentType.SCENERY);
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	public void pauseGame() {
		mapT.suspend();
	}

	protected Point getMapTopLeft() {
		return mapCellService.getMapTopLeft();
	}

	protected void setMapTopLeft(Point mapTopLeft) {
		mapCellService.setMapTopLeft(mapTopLeft);
	}

	protected Point getMapButtomRight() {
		return mapCellService.getMapButtomRight();
	}

	protected void setMapButtomRight(Point mapButtomRight) {
		mapCellService.setMapButtomRight(mapButtomRight);
	}

	protected void setDimension(Dimension mapPanelDimension) {
		setSize(mapPanelDimension);

	}

	public void performScene() {
	}

	/**
	 * This method is called when the user clicks on the "new wave" button.
	 */
	public void startFoolishWave() {
		mainFrame.disableSaveLoadMenu();
		WaveFactory waveFactory = new WaveFactory();
		Position[] path = new PathService().pathFinder(grid.getContent());
		wave = waveFactory.getWave("FoolishCritter",
				calcCritterStartingPoint(), path);

		ClassLoader classLoader = getClass().getClassLoader();
		File file;

		currentWaveAlienList = new ArrayList<>();
		for (int i = 0; i < wave.aliens.size(); i++) {
			currentWaveAlienList.add(wave.aliens.get(i));
		}

		for (int i = 0; i < currentWaveAlienList.size(); i++) {
			((RegularMove) (currentWaveAlienList.get(i).getMovingBehaviour()))
					.setFreezeTime(i * 100);

			file = new File(classLoader.getResource(
					currentWaveAlienList.get(i).display()).getFile());
			critterImage[i] = new ImageIcon(file.getPath());
		}
		waveStarted = true;
		GameLogManager.getInstance().addWaveLog(waveNumber,
				"Critters entered the map.");
	}

	@SuppressWarnings("deprecation")
	public void resumeGame() {
		mapT.resume();
	}

	public void setGameInfo(GameStateManager game) {
		this.waveNumber = game.getWaveNum();
		setTowers(grid.getTowers());
		this.bank.setBalance(game.getBalance());
		this.bank.setCurrentBalance(game.getCurrentBalance());
		this.availFunds = this.bank.getBalance()
				- this.bank.getCurrentBalance();
		life.setLife(game.getLife());
		gameInfoPanel.setLife(life.getLife());
		gameInfoPanel.setBank(availFunds);
		gameInfoPanel.setWave(waveNumber);
		for (Tower[] t : towers) {
			for (Tower tower : t) {
				if (tower != null) {
					informer.registerObserver((TowerFeatureDecorator) tower);
					((TowerFeatureDecorator) tower).addObserver(this);
					shootingSchedule.put(tower, 0);
				}
			}
		}
	}

	public int getWaveNumber() {
		return this.waveNumber;
	}

}
