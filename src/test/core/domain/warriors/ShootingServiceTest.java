package test.core.domain.warriors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.warriorservices.ShootingService;
import core.applicationservice.warriorservices.TowerFactory;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.crittertype.FoolishCritter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;

public class ShootingServiceTest {
	private TowerFeatureDecorator tower;
	private TowerFactory factory;
	private Position[] path;
	private ShootingService shootingService;

	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@AfterClass
	public static void tearDownAfterClass(){
	}

	@Before
	public void setUp(){
		factory = new TowerFactory();
		Position[] p = {new Position(0,0), new Position(0,1),
				new Position(0,2), new Position(0,3),
				new Position(0,4), new Position(1,4),
				new Position(2,4), new Position(3,4),
				new Position(3,5), new Position(3,6),
				new Position(3,7)};
		path = p;
		
		this.tower = (TowerFeatureDecorator) factory.getTower("ModernTower", TowerLevel.one);
		((Tower)this.tower).setTowerPosition(new Position(1, 3));
		Critter critter1 = new FoolishCritter(new Position(0,0), path);
		Critter critter2 = new FoolishCritter(new Position(0,0), path);
		Critter critter3 = new FoolishCritter(new Position(0,0), path);
		critter1.setCurrentPosition(1);
		critter1.setLife(200);
		critter2.setCurrentPosition(3);
		critter2.setLife(250);
		critter3.setCurrentPosition(9);
		critter3.setLife(300);
		Map<Critter, Position> crittersLocation = new HashMap<Critter, Position>();
		crittersLocation.put(critter1, new Position(0,1));
		crittersLocation.put(critter2, new Position(0,3));
		crittersLocation.put(critter3, new Position(3,6));
		tower.setCrittersLocation(crittersLocation);
		this.shootingService = new ShootingService();
	}

	@Test
	public void testNearToStartCritter() {
		try {
			Critter expected = shootingService.nearToStartCritter(this.tower, this.path);
			int place = expected.getCurrentPosition();
			Position actualPosition = this.path[place];
			assertTrue(actualPosition.equals(new Position(0,1)));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testNearToEndCritter() {
		try {
			Critter expected = shootingService.nearToEndCritter(this.tower, this.path);
			int place = expected.getCurrentPosition();
			Position actualPosition = this.path[place];
			assertTrue(actualPosition.equals(new Position(3,6)));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testStrongestCritter() {
		try {
			Critter expected = shootingService.strongestCritter(this.tower);
			int actualLife = expected.getLife();
			assertEquals(actualLife, 300);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testWeakestCritter() {
		try {
			Critter expected = shootingService.weakestCritter(this.tower);
			int actualLife = expected.getLife();
			assertEquals(actualLife, 200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testNearestCritter() {
		try {
			Critter expected = shootingService.nearestCritter(this.tower);
			int place = expected.getCurrentPosition();
			Position actualPosition = this.path[place];
			assertTrue(actualPosition.equals(new Position(0,3)));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
