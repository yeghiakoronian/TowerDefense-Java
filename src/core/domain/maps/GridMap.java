package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;

/**
 * @author Team5
 *
 */

@SuppressWarnings("serial")
public class GridMap extends Grid {

	private Tower[][] towers;

	/**
	 * @param width
	 *            Map width
	 * @param height
	 *            Map height
	 */
	public GridMap(int width, int height) {
		super(width, height);
		towers = new Tower[width][height];
	}

	/**
	 * <b>Constructs a Map using a grid.</b>
	 * 
	 * @param grid
	 *            Grid object
	 */
	public GridMap(Grid grid) {
		super(grid);
		towers = new Tower[grid.getWidth()][grid.getHeight()];
	}

	/**
	 * <b>Used in Observer design pattern which updates the towers that are on
	 * the map.</b>
	 * 
	 * @param towers
	 *            list of towers on the grid
	 */
	public void setTowers(Tower[][] towers) {
		if (towers != null) {
			this.towers = Arrays.copyOf(towers, towers.length);
		}
	}

	/**
	 * <b>Draws the map on the screen.</b>
	 * 
	 * @param g
	 *            to paint the component
	 */
	public void draw(Graphics g) {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Color color = Color.green;
				switch (getCell(x, y)) {
				case PATH:
					color = MapConstants.PATH_COLOR;
					break;
				case SCENERY:
					color = MapConstants.SCENERY_COLOR;
					break;
				case ENTRANCE:
					color = MapConstants.ENTRANCE_COLOR;
					break;
				case EXIT:
					color = MapConstants.EXIT_COLOR;
					break;
				case TOWER:
					// color = towers[x][y].display();
					break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(), getUnitSize());

			}

		}

	}

	/**
	 * @return 2D array of Tower
	 */
	public Tower[][] getTowers() {
		return towers;
	}

}
