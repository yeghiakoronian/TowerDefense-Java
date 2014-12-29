package ui.game;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

import core.applicationservice.gameservices.GameStateManager;
import core.domain.account.BankManager;
import core.domain.maps.Grid;
import core.domain.maps.GridMap;
import core.domain.warriors.defenders.towers.Tower;

/**
 * @author Team 5
 * This class is JLayeredPane that has two layers, one for map and one for towers and critters.
 */
public class LayeredMapPanel extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	public Thread mapT;
	private GridMap grid;
	
	private Point mapTopLeft;
	private Point mapButtomRight;
	
	
	private LayeredMapPanelGrid gridLayer;
	private LayeredMapPanelOtherItems otherItemsLayer;
	private GameInfoPanel gameInfoPanel;

	/**
	 * 
	 * @return game information panel
	 */
	public GameInfoPanel getGameInfoPanel() {
		return gameInfoPanel;
	}

	/**
	 * set the game information panel
	 * @param gameInfoPanel - game information panel
	 */
	public void setGameInfoPanel(GameInfoPanel gameInfoPanel) {
		this.gameInfoPanel = gameInfoPanel;
	}

	/**
	 * represents some of the components of user interface
	 * @param dimension - map panel dimension
	 * @param gameInfoPanel - game information panel
	 * @param mainFrame - the main frame
	 */
	public LayeredMapPanel(Dimension dimension, GameInfoPanel gameInfoPanel, MainFrame mainFrame) {
		this.gameInfoPanel = gameInfoPanel;
		this.grid = new GridMap(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		
		gridLayer = new LayeredMapPanelGrid(dimension);
		otherItemsLayer = new LayeredMapPanelOtherItems(dimension, gameInfoPanel, mainFrame);
		add(gridLayer,new Integer(1));
		add(otherItemsLayer,new Integer(2));
	}

	/**
	 * 
	 * @return other items panel
	 */
	public LayeredMapPanelOtherItems getOtherItemsPanel(){
		return otherItemsLayer;
	}
	
	/**
	 * sets a new value of a grid
	 * @param  grid new grid is being used in a game 
	 */
	public void setGrid(GridMap grid) {
		this.grid = grid;
		gridLayer.setGrid(grid);
		otherItemsLayer.setGrid(grid);
	}

	/**
	 * 
	 * @return current grid
	 */
	public Grid getGrid() {
		return gridLayer.getGrid();
	}

	/**
	 * 
	 * @return current map
	 */
	public GridMap getGridMap() {
		return otherItemsLayer.getGrid();
	}

	/**
	 * 
	 * @param towers new tower to be set
	 */
	public void setTowers(Tower[][] towers) {
		otherItemsLayer.setTowers(towers);		
	}

	/**
	 * 
	 * @return bank amount
	 */
	public BankManager getBank() {
		return otherItemsLayer.getBank();
	}

	/**
	 * This method calculates the starting point of the map on the screen in pixels to draw the map right in the center of the screen.
	 * @param mapPanelDimension dimension of the map panel. 
	 * @return a point in pixel that represents the top-left corner of the map on the screen.
	 */
	private Point calcMapStartingPoint(Dimension mapPanelDimension) {
		int initX = 0;
		int initY = 0;

		int panelWidth = (int) mapPanelDimension.getWidth();
		int panelHeight = (int) mapPanelDimension.getHeight();
		int mapWidth = grid.getWidth() * grid.getUnitSize();
		int mapHeight = grid.getHeight() * grid.getUnitSize();

		if (panelWidth > grid.getWidth() * grid.getUnitSize()) {
			initX = (panelWidth - mapWidth) / 2;

		}
		if (panelHeight > grid.getWidth() * grid.getUnitSize()) {

			initY = (panelHeight - mapHeight) / 2;
		}

		return new Point(initX, initY);
	}
	
	/** This method calculates the bottom-right corner of the map on the screen in pixels to know the boundary of the map on the screen.
	 * @param mapPanelDimension dimension of the map panel.
	 * @return a point in pixel that represents the bottom-right corner of the map on the screen.
	 */
	private Point calcMapButtomRight(Dimension mapPanelDimension){
		int initX = 0;
		int initY = 0;

		int panelWidth = (int) mapPanelDimension.getWidth();
		int panelHeight = (int) mapPanelDimension.getHeight();
		int mapWidth = grid.getWidth() * grid.getUnitSize();
		int mapHeight = grid.getHeight() * grid.getUnitSize();

		if (panelWidth > grid.getWidth() * grid.getUnitSize()) {
			initX = ((panelWidth - mapWidth) / 2)+mapWidth;

		}
		if (panelHeight > grid.getWidth() * grid.getUnitSize()) {

			initY = ((panelHeight - mapHeight) / 2)+mapHeight;
		}

		return new Point(initX, initY);
	}
	
	/**
	 * computes the size of a grid by given width and height 
	 */
	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

	/**
	 * 
	 * @return top left corner of a map
	 */
	public Point getMapTopLeft() {
		return mapTopLeft;
	}
	/**
	 * 
	 * @param mapTopLeft sets top left corner of a map
	 */
	private void setMapTopLeft(Point mapTopLeft) {
		this.mapTopLeft = mapTopLeft;
	}
	
	/**
	 * 
	 * @return map buttem right corner of a map
	 */
	public Point getMapButtomRight() {
		return mapButtomRight;
	}
	
	/**
	 * sets buttem right corner of a map
	 * @param mapButtomRight buttem right corner of a map
	 */
	private void setMapButtomRight(Point mapButtomRight) {
		this.mapButtomRight = mapButtomRight;
	}


	/**
	 * When a map is loaded, this method is called to resize the panel and its layers.
	 * @param mapPanelDimension width and height of the map in cell numbers.
	 */
	public void resetSize(Dimension mapPanelDimension) {
		Point mapTopLeft = calcMapStartingPoint(mapPanelDimension);
		Point mapBottomRight = calcMapButtomRight(mapPanelDimension);
		
		gridLayer.setMapTopLeft(mapTopLeft);
		gridLayer.setMapButtomRight(mapBottomRight);
		gridLayer.setDimension(mapPanelDimension);

		otherItemsLayer.setMapTopLeft(mapTopLeft);
		otherItemsLayer.setMapButtomRight(mapBottomRight);
		otherItemsLayer.setDimension(mapPanelDimension);
		otherItemsLayer.calcCritterStartingPoint();
		
	}

	/**
	 * 
	 * @param game information of a game
	 */
	public void setGameInfo(GameStateManager game) {
		otherItemsLayer.setGameInfo(game);
		
	}

	/**
	 * 
	 * @return current wave number
	 */
	public int getWaveNumber() {
		return otherItemsLayer.getWaveNumber();
	}
}
