package test.core.domain.warriors;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.warriorservices.TowerFactory;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towertype.AncientTower;
import core.domain.warriors.defenders.towers.towertype.KingTower;
import core.domain.warriors.defenders.towers.towertype.ModernTower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;

public class FactoriesTest {
	private String modern;
	private String ancient;
	private String king;
	TowerFactory towerFactory;

	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@AfterClass
	public static void tearDownAfterClass(){
	}

	@Before
	public void setUp(){

		try {
			modern = ModernTower.class.getSimpleName();
			ancient = AncientTower.class.getSimpleName();
			king = KingTower.class.getSimpleName();
			towerFactory = new TowerFactory();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testGetTower() {
		try {
			Tower modernObj = towerFactory.getTower("ModernTower");
			Tower ancientObj = towerFactory.getTower("AncientTower");
			Tower kingObj = towerFactory.getTower("KingTower");
			String modernExpected = modernObj.getClass().getSimpleName();
			String ancientExpected = ancientObj.getClass().getSimpleName(); 
			String kingExpected = kingObj.getClass().getSimpleName();

			// assert part
			assertEquals(modernExpected, modern);
			assertEquals(ancientExpected, ancient);
			assertEquals(kingExpected, king);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test 
	public void testGetDecoratedTower(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);

			Tower tower = towerFactory.getDecoratedTower(expDecorated.objectDetials());

			// assert part
			assertEquals(expDecorated.cost(), tower.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetTowerByLevelThree(){
		try {
			Tower tower = towerFactory.getTower("ModernTower", TowerLevel.three);

			// expected
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			
			// assert part
			assertEquals(expDecorated.cost(), tower.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetTowerByLevelTwo(){
		try {
			Tower tower = towerFactory.getTower("ModernTower", TowerLevel.two);

			// expected
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			
			// assert part
			assertEquals(expDecorated.cost(), tower.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetTowerByLevelOne(){
		try {
			Tower tower = towerFactory.getTower("ModernTower", TowerLevel.one);

			// expected
			Tower expected = towerFactory.getTower("ModernTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			
			// assert part
			assertEquals(expDecorated.cost(), tower.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetRange(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireRange(expected);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 1;
			assertEquals((Double)expecteRange, (Double)range);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGet2levelRange(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireRange(expected);
			lst.add(expDecorated);
			expDecorated = new FireRange(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 2;
			assertEquals((Double)expecteRange, (Double)range);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGet3levelRange(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireRange(expected);
			lst.add(expDecorated);
			expDecorated = new FireRange(expDecorated);
			lst.add(expDecorated);
			expDecorated = new FireRange(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 3;
			assertEquals((Double)expecteRange, (Double)range);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGet2LevelSpeed(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireSpeed(expected);
			lst.add(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 2;
			assertEquals((Double)expectedSpeed, (Double)speed);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGetSpeed(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireSpeed(expected);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 1;
			assertEquals((Double)expectedSpeed, (Double)speed);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGet3LevelSpeed(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireSpeed(expected);
			lst.add(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			lst.add(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 3;
			assertEquals((Double)expectedSpeed, (Double)speed);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGetPower(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FirePower(expected);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 1;
			assertEquals((Double)expectePower, (Double)power);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGet2LevelPower(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FirePower(expected);
			lst.add(expDecorated);
			expDecorated = new FirePower(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 2;
			assertEquals((Double)expectePower, (Double)power);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testGet3LevelPower(){
		try {
			Tower expected = towerFactory.getTower("ModernTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FirePower(expected);
			lst.add(expDecorated);
			expDecorated = new FirePower(expDecorated);
			lst.add(expDecorated);
			expDecorated = new FirePower(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 3;
			assertEquals((Double)expectePower, (Double)power);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
