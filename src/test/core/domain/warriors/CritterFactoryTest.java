package test.core.domain.warriors;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.domain.warriors.aliens.crittertype.IntelligentCritter;
import core.domain.warriors.aliens.features.Resistance;

public class CritterFactoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testLifeBooster() {
		IntelligentCritter critter = new IntelligentCritter();
		Resistance resistance = new Resistance(critter);
		resistance = new Resistance(critter);
		resistance = new Resistance(critter);
		resistance = new Resistance(critter);
		
		// assert part
		int actual = (int)resistance.lifeBooster();
		int expected = 17;
		assertEquals(actual, expected);
	}

}
