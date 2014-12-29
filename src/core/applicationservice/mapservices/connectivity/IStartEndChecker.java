package core.applicationservice.mapservices.connectivity;

import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

/**
 * <b> using for dependency injection</b>
 * The Interface IStartEndChecker.
 * @version 0.1
 */
public interface IStartEndChecker {

	/**
	 * Checks for overlap.
	 *
	 * @param first the first node
	 * @param second the second node
	 * @return true, if successful
	 */
	public boolean hasOverlap(Position first, Position second);

	/**
	 * <b>Checks if is in edge.</b>
	 *
	 * @param width the width of grid
	 * @param height the height of grid 
	 * @param position the position of the node the we want to check
	 * @return true, if is in edge
	 */
	public boolean isInEdge(int width, int height, Position position);
	/**
	 * used for validation on design map
	 * @param matrix grid info
	 * @return true, if has start node
	 */
	public boolean hasStart(GridCellContentType[][] matrix);
	/**
	 *  used for validation on design map
	 * @param matrix grind info
	 * @return true, if has end
	 */
	public boolean hasEnd(GridCellContentType[][] matrix);
	/**
	 * check for size validation in grid design 
	 * @param height height of teh grid
	 * @param width with of the grid
	 * @return true if correct
	 */
	public boolean isCorrectSize(int height, int width);

}