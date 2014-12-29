package core.domain.maps;

import java.awt.Color;
import java.awt.Graphics;

import core.contract.MapConstants;

@SuppressWarnings("serial")
/**
 * 
 * @author Team5
 *
 */
public class VisualGrid extends Grid {

/**
 * 
 * @param grid initializes grid
 */
	public VisualGrid(Grid grid) {
		super(grid);

	}
/**
 * 
 * @param width  width of grid
 * @param height height of grid
 */
	public VisualGrid(int width, int height) {
		super(width, height);
	}
/**
 * 
 * @param width  width of grid
 * @param height height of grid
 * @param cellType type of cell path scenery entry point 
 */
	public VisualGrid(int width, int height, GridCellContentType cellType) {
		super(width, height, cellType);
	}
/**
 * @param g  to draw graphics
 */
	@SuppressWarnings("incomplete-switch")
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
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(),
						getUnitSize());

			}

		}

	}

}
