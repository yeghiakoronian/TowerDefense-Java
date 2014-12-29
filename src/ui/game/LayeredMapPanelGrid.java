package ui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import core.domain.maps.GridMap;
import core.domain.warriors.defenders.towers.Tower;

/**
 * This class is a JPanel that contains the map. This map drawn only once (when it is loaded from file). This panel is one of the layers of the LayeredMapPanel. 
 * @author Team 5
 *
 */
@SuppressWarnings("serial")
public class LayeredMapPanelGrid extends JPanel {
	private Point mapTopLeft;
	private Point mapButtomRight;
	private GridMap grid;
	private Cell cell;
	

	/**
	 * @param dimension height and width of the panel.
	 */
	public LayeredMapPanelGrid(Dimension dimension) {
		this.grid = new GridMap(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		setOpaque(false);
		setDimension(dimension);
	}

	/**
	 * 
	 * @param grid new value of a grid
	 */
	public void setGrid(GridMap grid) {
		cell = new Cell();
		this.grid = grid;
	}

	/**
	 * This method draws the map on the screen when the repaint method is called. 
	 */
	public void paintComponent(Graphics g) {

		 super.paintComponent(g);

		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth()
				* grid.getUnitSize(), initY + grid.getHeight()
				* grid.getUnitSize()));

		if (grid.getWidth() > 1) {				
			for (int x = 0; x < grid.getWidth(); x++) {
				for (int y = 0; y < grid.getHeight(); y++) {
					int xCoordinate = grid.getUnitSize() * x + initX;
					int yCoordinate = grid.getUnitSize() * y + initY;
					if (grid.getTowers() == null) {
						grid.setTowers(new Tower[1][1]);
					}
					cell.draw(g, grid.getCell(x, y), grid.getTowers(),
							xCoordinate, yCoordinate, x, y, false);
				}
			}
		}

	}


	/**
	 * 
	 */
	@Override
	public Dimension getPreferredSize() {
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();

		return new Dimension(width, height);
	}

/**
 * 
 * @return 
 */
	protected Point getMapTopLeft() {
		return mapTopLeft;
	}
/**
 * 
 * @param mapTopLeft
 */
	protected void setMapTopLeft(Point mapTopLeft) {
		this.mapTopLeft = mapTopLeft;
	}
/**
 * 
 * @return
 */
	protected Point getMapButtomRight() {
		return mapButtomRight;
	}
/**
 * 
 * @param mapButtomRight
 */
	protected void setMapButtomRight(Point mapButtomRight) {
		this.mapButtomRight = mapButtomRight;
	}

/**
 * 
 * @return grid object
 */
	public GridMap getGrid() {
		return grid;
	}
/**
 * 
 * @param mapPanelDimension
 */
	protected void setDimension(Dimension mapPanelDimension) {
		setSize(mapPanelDimension);
		
	}

	

}
