package core.applicationservice.mapservices;

import java.awt.Point;

import core.contract.MapConstants;
import core.domain.waves.Position;

public class MapCellService {

	private Point mapTopLeft = new Point();
	private Point mapButtomRight = new Point();
	
	
	public Position getCellPixel(Position cell) {
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		Position pixel;
		if (cell != null) {
			pixel = new Position(
					(int) (initX + (cell.getX() * MapConstants.UNIT_SIZE)),
					(int) (initY + (cell.getY() * MapConstants.UNIT_SIZE)));

			return pixel;
		}
		return null;
	}

	/**
	 * This method converts a map cell to a pixel position on the screen
	 * 
	 * @param cell
	 *            a cell position on the grid
	 * @return pixel position on the screen
	 */
	public Position convertCellToPixel(Position cell) {
		Position pixel = getCellPixel(cell);
		if (pixel != null) {
			pixel.setX(pixel.getX() + MapConstants.UNIT_SIZE / 2);
			pixel.setY(pixel.getY() + MapConstants.UNIT_SIZE / 2);
			return pixel;
		}
		return null;
	}

	public Point getMapTopLeft() {
		return mapTopLeft;
	}


	public void setMapTopLeft(Point mapTopLeft) {
		this.mapTopLeft = mapTopLeft;
	}


	public Point getMapButtomRight() {
		return mapButtomRight;
	}


	public void setMapButtomRight(Point mapButtomRight) {
		this.mapButtomRight = mapButtomRight;
	}
	
	
}
