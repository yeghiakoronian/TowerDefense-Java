package test.core.applicationservice.locationservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.locationservices.PositionService;
import core.domain.waves.Position;

public class PositionServiceTest {
	private Position p;
	private Position q;
	private PositionService positionService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp(){

		try {
			p = new Position(4, 5);
			q = new Position(8, 8);
			positionService = new PositionService();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testGetDistance() {
		try {
			double distance = positionService.getDistance(p, q);
			double expected = 5;
			assertEquals(distance, expected, 0.001);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testIsInRange(){
		try {
			assertTrue(positionService.isInRange(p, q, 6));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
