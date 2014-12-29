package ui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import core.domain.maps.Grid;

/**
 * <b>This object is used in map editor and game panel to draw the grid and also to visually reflect user actions on the grid.</b>
 * @author Team5
 *
 */
@SuppressWarnings("serial")
public class CanvaObject extends Canvas {

	Grid grid = null;
	Dimension dimension = new Dimension();
	Graphics imageGraphic = null;

	/**
	 * public constructore of the class
	 * @param newGrid new value for newGrid member varuable
	 */
	public CanvaObject(Grid newGrid) {
		grid = newGrid;
	}

	/**
	 * <b>Update</b>
	 * @param grid Grid object
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	};

	/**
	 * <b>This method draws the grid.</b>
	 * @param graphics The specified Graphics context
	 */
	@Override
	public void paint(Graphics graphics) {
		grid.draw(graphics);
	}

}
