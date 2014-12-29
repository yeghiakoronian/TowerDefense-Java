package test.core.applicationservice.mapservices;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.mapservices.connectivity.IConnectivityService;
import core.applicationservice.mapservices.connectivity.imp.ConnectivityService;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public class ConnectivityTest {

	private GridCellContentType[][] matrix; 
	
	@BeforeClass
	public static void setUpBeforeClass(){
		try {
			System.out.println("start of Connectivity Test");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@AfterClass
	public static void tearDownAfterClass(){
		try {
			System.out.println("end of Connectivity Test");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Before
	public void setUp(){
		try {

			MatrixUtility utility = new MatrixUtility();
			matrix = utility.matrixCellType("matrix.txt", 4, 8);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testIsTherePath() {
		try {
			IConnectivityService service = new ConnectivityService();
			assertTrue(service.isTherePath(new Position(0, 0), new Position(3, 7), matrix));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
