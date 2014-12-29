package test.core.applicationservice.mapservices;

import static org.junit.Assert.assertArrayEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.mapservices.pathfinder.PathService;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public class PathFinderTest {
	private int[][] matrix;
	private GridCellContentType[][] cellTypeMatrix;
	private PathService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		try {
			MatrixUtility utility = new MatrixUtility();
			matrix = utility.matrixReadre("matrix.txt", 4, 8);
			cellTypeMatrix = utility.matrixCellType("matrix.txt", 4, 8);
			service = new PathService();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testPathFinder() {
		try {
			Position[] expected = {new Position(0,0), new Position(0,1),
					new Position(0,2), new Position(0,3),
					new Position(0,4), new Position(1,4),
					new Position(2,4), new Position(3,4),
					new Position(3,5), new Position(3,6),
					new Position(3,7)};
			Position[] path = service.pathFinder(cellTypeMatrix);
			assertArrayEquals(null, expected, path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
