package test.core.domain.warriors;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.warriorservices.TowerMarket;
import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towertype.AncientTower;
import core.domain.warriors.defenders.towers.towertype.ModernTower;

public class TowerCostCalTest {
	private AncientTower ancient;
	private ModernTower modern;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ancient = new AncientTower();
		modern = new ModernTower();
	}

	@Test
	public void testTowerTypeCost() {
		try {
			assertEquals(DefenderConstants.ANCIENT_TOWER, ancient.cost());
			assertEquals(DefenderConstants.MODERN_TOWER, modern.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testFireRangeCost() {
		try {
			long specialModern = DefenderConstants.MODERN_TOWER
					+ DefenderConstants.FIRE_RANGE;
			long specialAncient = DefenderConstants.ANCIENT_TOWER
					+ DefenderConstants.FIRE_RANGE;
			Tower mWithRange = new FireRange(modern);
			Tower aWithRange = new FireRange(ancient);
			assertEquals(specialModern, mWithRange.cost());
			assertEquals(specialAncient, aWithRange.cost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testFireSpeedCost() {
		try {
			long specialModern = DefenderConstants.MODERN_TOWER
					+ DefenderConstants.FIRE_SPEED;
			long specialAncient = DefenderConstants.ANCIENT_TOWER
					+ DefenderConstants.FIRE_SPEED;
			Tower mWithSpeed = new FireSpeed(modern);
			Tower aWithSpeed = new FireSpeed(ancient);
			assertEquals(specialModern, mWithSpeed.cost());
			assertEquals(specialAncient, aWithSpeed.cost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testFirePowerCost() {
		try {
			long specialModern = DefenderConstants.MODERN_TOWER
					+ DefenderConstants.FIRE_POWER;
			long specialAncient = DefenderConstants.ANCIENT_TOWER
					+ DefenderConstants.FIRE_POWER;
			Tower mWithPower = new FirePower(modern);
			Tower aWithPower = new FirePower(ancient);
			assertEquals(specialModern, mWithPower.cost());
			assertEquals(specialAncient, aWithPower.cost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComboFeature() {
		try {
			long specialModern = DefenderConstants.MODERN_TOWER
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED;
			long specialAncient = DefenderConstants.ANCIENT_TOWER
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED;
			// crate modern with three features
			Tower modernCombo = new FirePower(modern);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);

			// crate ancient with three features
			Tower ancientCombo = new FirePower(ancient);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);

			// assert part
			assertEquals(specialModern, modernCombo.cost());
			assertEquals(specialAncient, ancientCombo.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test
	public void testThreeTimesComboFeature() {
		try {
			long specialModern = DefenderConstants.MODERN_TOWER
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED;
			long specialAncient = DefenderConstants.ANCIENT_TOWER
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED;
			// crate modern with three features
			Tower modernCombo = new FirePower(modern);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			modernCombo = new FirePower(modernCombo);
			modernCombo = new FirePower(modernCombo);

			// crate ancient with three features
			Tower ancientCombo = new FirePower(ancient);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			ancientCombo = new FirePower(ancientCombo);
			ancientCombo = new FirePower(ancientCombo);

			// assert part
			assertEquals(specialModern, modernCombo.cost());
			assertEquals(specialAncient, ancientCombo.cost());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Test
	public void testObjectDetails() {
		try {
			List<String> modernExpected = Arrays.asList("ModernTower",
					"FirePower", "FireRange", "FireSpeed");
			List<String> ancientExpected = Arrays.asList("AncientTower",
					"FirePower", "FireRange", "FireSpeed");

			// crate modern with three features
			Tower modernCombo = new FirePower(modern);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			List<String> modernDetails = new ArrayList<String>();
			for (Tower detail : modernCombo.objectDetials()) {
				modernDetails.add(detail.getClass().getSimpleName());
			}

			// crate ancient with three features
			Tower ancientCombo = new FirePower(ancient);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			List<String> ancientDetails = new ArrayList<String>();
			for (Tower detail : ancientCombo.objectDetials()) {
				ancientDetails.add(detail.getClass().getSimpleName());
			}

			// assert part
			assertEquals(modernExpected, modernDetails);
			assertEquals(ancientExpected, ancientDetails);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testSell(){
		try {
			double specialModern = DefenderConstants.MODERN_TOWER
					+ DefenderConstants.FIRE_POWER
					+ DefenderConstants.FIRE_RANGE
					+ DefenderConstants.FIRE_SPEED;
			
			// crate modern with three features
			Tower modernCombo = new FirePower(modern);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			TowerMarket market = new TowerMarket();
			
			long sellResult = market.sellTower(modernCombo);
			long expected = (long) ((specialModern * DefenderConstants.Sell_Percentage)/100);
			assertEquals(expected, sellResult, 0.001);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
