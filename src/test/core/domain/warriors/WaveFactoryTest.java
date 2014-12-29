package test.core.domain.warriors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.warriorservices.WaveFactory;
import core.contract.WaveConstants;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.crittertype.FoolishCritter;
import core.domain.warriors.aliens.features.Resistance;
import core.domain.waves.Position;
import core.domain.waves.Wave;

public class WaveFactoryTest {
	WaveFactory testWave;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {


	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testWave = new WaveFactory();
	}

	@Test
	public void testFoolishFactory() {
		//initialize
		Wave foolishWave = testWave.getWave("FoolishCritter", new Position(0, 0), null);
		// aasert part
		assertEquals(10, foolishWave.aliens.size());
		for (Critter critter : foolishWave.aliens) {
			assertTrue(critter instanceof FoolishCritter);
		}

	}
	@Test
	public void testIntelligentFactory() {
		//initialize
		Wave intelligentWave = testWave.getWave("IntelligentCritter", new Position(0, 0), null);
		// aasert part
		assertEquals(10, intelligentWave.aliens.size());
		for (Critter critter : intelligentWave.aliens) {
			assertTrue(critter instanceof Resistance);
		}
	}
	@Test
	public void testWaveFactory() {
		Position[] path = {
				new Position(0, 0),new Position(0, 1),
				new Position(0, 2),new Position(0, 3),
				new Position(0, 4),new Position(0, 5),
				new Position(1, 9),new Position(1, 8),
				new Position(2, 16),new Position(2, 17),
				new Position(2, 18)
		};
		WaveFactory factory = new WaveFactory();
		Wave wave = factory.getWave("IntelligentCritter", new Position(0, 0), path);
		for (Critter c : wave.aliens) {
			assertTrue(c instanceof Resistance);
		}
		assertEquals(wave.aliens.size(),  WaveConstants.WAVE_SIZE);
	}


}
