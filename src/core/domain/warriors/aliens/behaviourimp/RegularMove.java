package core.domain.warriors.aliens.behaviourimp;

import core.contract.MapConstants;
import core.domain.warriors.aliens.behaviours.MovingBehaviour;
import core.domain.waves.Position;

/**
 * <b>this is used for strategy design pattern this class is defined to
 * implement the one of the moving behavior of a alien that is a kind of regular
 * move </b>
 * 
 * @author Team5
 * @version 0.1
 */
public class RegularMove implements MovingBehaviour, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7111897498315966069L;

	private Position[] path;
	private int xStep;
	private int yStep;
	private Position PixelPosition;
	private int currentPosition;
	private int freezeTime;

	/**
	 * Set PixelPosition and path for regular move
	 * 
	 * @param path
	 *            valid path
	 * @param initialPixel
	 *            initial path pixel
	 */
	public RegularMove(Position[] path, Position initialPixel) {
		this.setPixelPosition(initialPixel);
		this.setPath(path);
	}

	/**
	 * <b>this sound method not implemented now but we make the skeleton of the
	 * class for the second build</b>
	 */
	@Override
	public void move() {
		if (freezeTime <= 0) {
			int xPixel = PixelPosition.getX();
			int yPixel = PixelPosition.getY();
			int currPathCell = currentPosition;
			if (currPathCell != path.length - 1) {

				Position currCell = path[currPathCell];
				Position nextCell = path[currPathCell + 1];

				if (currCell.getY() == nextCell.getY()) {
					if (currCell.getX() + 1 == nextCell.getX()) {
						xPixel += 1;
						xStep += 1;
						if (xStep >= MapConstants.UNIT_SIZE - 1) {
							xStep = 0;
							currPathCell++;
						}

					} else if (currCell.getX() - 1 == nextCell.getX()) {
						xPixel -= 1;
						xStep += 1;
						if (xStep >= MapConstants.UNIT_SIZE - 1) {
							xStep = 0;
							currPathCell++;
						}
					}

				}
				if (currCell.getX() == nextCell.getX()) {
					if (currCell.getY() + 1 == nextCell.getY()) {
						yPixel += 1;
						yStep += 1;

					} else if (currCell.getY() - 1 == nextCell.getY()) {
						yPixel -= 1;
						yStep += 1;
					}
					if (yStep >= MapConstants.UNIT_SIZE - 1) {
						yStep = 0;
						currPathCell++;
					}
				}
			}
			this.PixelPosition = new Position(xPixel, yPixel);
			currentPosition = currPathCell;
		} else {
			if (freezeTime > 0) {
				freezeTime--;
			}
		}
	}

	/**
	 * Return pixel position
	 * 
	 * @return PixelPosition
	 */
	public Position getPixelPosition() {
		return PixelPosition;
	}

	/**
	 * Return current position in map
	 * 
	 * @return currentPosition
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Return path in array of positions
	 * 
	 * @return path
	 */
	public Position[] getPath() {
		return path;
	}

	/**
	 * Set freez time for ice tower
	 * 
	 * @param freezeTime
	 *            time for freezing a critter
	 */
	public void setFreezeTime(int freezeTime) {
		this.freezeTime = freezeTime;
	}

	private void setPath(Position[] path) {
		this.path = path;
	}

	private void setPixelPosition(Position pixelPosition) {
		PixelPosition = pixelPosition;
	}

	public int getFreezeTime() {
		return freezeTime;
	}

}
