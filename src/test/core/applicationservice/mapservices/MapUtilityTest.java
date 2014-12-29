package test.core.applicationservice.mapservices;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.mapservices.MapUtility;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public class MapUtilityTest {
	@SuppressWarnings("unused")
	private int[][] matrix;
	private GridCellContentType[][] cellTypeMatrix;

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
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testGetEnter() {
		try {
			MapUtility mapUtility = new MapUtility();
			Position enter =mapUtility.getEnter(cellTypeMatrix);
			
			Position expected = new Position(0, 0);
			 assertTrue(enter.equals(expected));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetExit(){
		try {
			MapUtility mapUtility = new MapUtility();
			Position exit =mapUtility.getExit(cellTypeMatrix);
			
			Position expected = new Position(3, 7);
			 assertTrue(exit.equals(expected));
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
