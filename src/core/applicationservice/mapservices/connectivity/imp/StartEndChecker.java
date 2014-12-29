package core.applicationservice.mapservices.connectivity.imp;

import infrastructure.loggin.Log4jLogger;
import core.applicationservice.mapservices.connectivity.IStartEndChecker;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

/**
 * this calss used as a service to chech some validation in map based on the position of start and end 
 * @author Team5
 * @version 0.1
 */
public class StartEndChecker implements IStartEndChecker {
	private static final Log4jLogger logger = new Log4jLogger();
	/**
	 * <b>it can used for check any two positions that were selected have overlap with others our not</b>
	 * @param first first node that was chosen
	 * @param second second node that was chosen
	 * @return boolean
	 */
	@Override
	public boolean hasOverlap(Position first, Position second){
		return first.equals(second);
	}
	/**
	 * <b>it can check if the node that was selected is located on the age of grid or not</b>
	 * @param width place of the edge
	 * @param height place of the edge
	 * @param position position that we need for validation 
	 * @return true, if node is on edge
	 */
	@Override
	public boolean isInEdge(int width, int height, Position position){
			if(condition(width, height, position)){
				return true;
			}else
				return false;
	}
	/**
	 * <b>it can check if there is any start point on grid or not</b>
	 * @param matrix grid info
	 * @return true, if it has a start point
	 */
	@Override
	public boolean hasStart(GridCellContentType[][] matrix){
		return nodeChecker(matrix, GridCellContentType.ENTRANCE);
	}
	/**
	 * <b>
	 * it can check if there is any start point on grid or not
	 * </b>
	 * @param matrix grid info
	 * @return true, if it has an end point
	 */
	@Override
	public boolean hasEnd(GridCellContentType[][] matrix) {
			return nodeChecker(matrix, GridCellContentType.EXIT);
	}
	/**
	 * <b>it defined as a method extract refactoring for condition </b>
	 * @param width
	 * @param height
	 * @param position
	 * @return true , if the condition is correct
	 */
	private boolean condition(int width, int height, Position position){
		return ((position.getX() == 0) || (position.getX() ==width))
				|| ((position.getY() == 0) || (position.getY() == height));
	}
	
	public boolean isNeighbor(Position start, Position end) {
		if (start.getX() + 1 == end.getX()) {
			return true;
		}
		if (start.getX() - 1 == end.getX()) {
			return true;
		}
		if (start.getY() + 1 == end.getY()) {
			return true;
		}
		if (start.getY() + 1 == end.getY()) {
			return true;
		}
		return false;
	}

	/**
	 * <b>it defined as a method extract refactoring </b>
	 * @param matrix grid info
	 * @param expected expected matrix
	 * @return true , if both of them are the same
	 */
	public boolean nodeChecker(GridCellContentType[][] matrix, GridCellContentType expected){
		boolean flag = false;
		try {
			for (GridCellContentType[] rows : matrix) {
				for (GridCellContentType type : rows) {
					if(type == expected)
						flag = true;
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return flag;
	}
	/**
	 * <b>grid size validation </b>
	 * @param height height of the grid
	 * @param width width of grid
	 * @return true , if the correct size was chosen
	 */
	@Override
	public boolean isCorrectSize(int height, int width) {
		return ((height >= 5 && height <= 30) && (width >= 5 && width <= 30));
	}
	
}
