package core.applicationservice.mapservices.connectivity.imp;

import java.io.BufferedReader;

import infrastructure.loggin.Log4jLogger;
import core.applicationservice.mapservices.connectivity.IConnectivityService;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

/**
 * <b>The Class ConnectivityService.</b>
 * in this class connectivity service was added to check if there is any path between start and end 
 * @author Team5
 * @version 0.1
 */
public class ConnectivityService implements IConnectivityService {

	@SuppressWarnings("unused")
	private static BufferedReader br;
	/**
	 * application logger definition
	 */
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * <b>
	 * Check for validating if there is any path between start and end 
	 * in this method we transform our grid that was built as GridCellType
	 * into an graph and after that we make a union between each nodes of graph that has a relation to their neighbors.
	 * </b>
	 * @param start as Position that represent the entrance point
	 * @param end as Position that represent the exit point of aliens
	 * @param matrix as GridCellContentType
	 * @return boolean it is true if there is a path
	 */
	public boolean isTherePath(Position start, Position end,
			GridCellContentType[][] matrix) {
		try {
			WeightedUnionPathCompression compression = new WeightedUnionPathCompression();
			compression.initialize(matrix.length, matrix[0].length);
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (isRight(matrix, i, j)) {
						if (isPath(matrix, i, j + 1))
							compression.union(new Position(i, j), new Position(
									i, j + 1));
					}
					if (isDown(matrix, i, j)) {
						if (isPath(matrix, i + 1, j))
							compression.union(new Position(i, j), new Position(
									i + 1, j));
					}
				}
			}
			return (compression.connected(start, end));

		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
			return false;
		}
	}

	/**
	 * 
	 * @param matrix the matrix that we want for validating
	 * @param i as int that shows the index of the node that we want to check if there is any neighbor in right or not
	 * @param j as int that shows the index of the node that we want to check if there is any neighbor in right or not
	 * @return boolean that shows there is a connected neighbor in right place
	 */
	public boolean isRight(GridCellContentType[][] matrix, int i, int j) {
		return ((matrix[i][j] == GridCellContentType.PATH
				|| matrix[i][j] == GridCellContentType.ENTRANCE 
				|| matrix[i][j] == GridCellContentType.EXIT)
				&& j + 1 < matrix[0].length);
	}
	/**
	 * {@link #isRight(GridCellContentType[][], int, int)}
	 * @param matrix that is the grid 
	 * @param i index of actual node
	 * @param j index of actual node
	 * @return true , if it has right connection
	 */
	public boolean isDown(GridCellContentType[][] matrix, int i, int j) {
		return ((matrix[i][j] == GridCellContentType.PATH
				|| matrix[i][j] == GridCellContentType.ENTRANCE 
				|| matrix[i][j] == GridCellContentType.EXIT)
				&& i + 1 < matrix.length);
	}
	/**
	 * it can check is this node is part of path or not
	 * @param matrix grid info
	 * @param i actual node index
	 * @param j actual node index
	 * @return true, if it has Down connection  
	 */
	public boolean isPath(GridCellContentType[][] matrix, int i, int j) {
		return (matrix[i][j] == GridCellContentType.PATH
				|| matrix[i][j] == GridCellContentType.ENTRANCE
				|| matrix[i][j] == GridCellContentType.EXIT);
	}
}
