package test.core.applicationservice.warriorservices;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.mapservices.pathfinder.PathService;
import core.applicationservice.warriorservices.SpecialDamageEffectsServiceBurn;
import core.applicationservice.warriorservices.SpecialDamageEffectsServiceSplash;
import core.applicationservice.warriorservices.TowerFactory;
import core.applicationservice.warriorservices.WaveFactory;
import core.contract.DefenderConstants;
import core.domain.maps.GridCellContentType;
import core.domain.maps.GridMap;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.behaviourimp.RegularMove;
import core.domain.warriors.aliens.crittertype.FoolishCritter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;
import core.domain.waves.Wave;

public class SpecialDamageEffectsTest {
	private static GridMap grid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grid = new GridMap(10,10);
		grid.setSize(10, 10);
		grid.setCell(0, 5, GridCellContentType.ENTRANCE);
		for(int i=1; i<9; i++){
			grid.setCell(i, 5, GridCellContentType.PATH);
		}
		grid.setCell(9, 5, GridCellContentType.EXIT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {

	}

	@Test
	public void testBurningSpecialEffect() {
		PathService pathService = new PathService();
		FoolishCritter critter = new FoolishCritter(grid.getEntranceLocation(), pathService.pathFinder(grid.getContent()));
		int initLife = critter.getLife();
		
		SpecialDamageEffectsServiceBurn burningEffect = new SpecialDamageEffectsServiceBurn();
		burningEffect.addBurningCritter(critter, 1);
		burningEffect.burn();
		
		Assert.assertEquals(critter.getLife(), initLife-1);
		
	}

	
	@Test
	public void testSplashSpecialEffect(){
		TowerFactory factory = new TowerFactory();
		Critter[] critters = new Critter[3];
		
		
		WaveFactory waveFactory = new WaveFactory();
		Position[] path = new PathService().pathFinder(grid.getContent());
		Wave wave = waveFactory.getWave("FoolishCritter", new Position(9,7), path);
		
		critters[0] = wave.getAliens().get(0);
		critters[1] = wave.getAliens().get(1);
		critters[2] = wave.getAliens().get(2);
		
		int initLife = critters[0].getLife();
		
		Tower tower = factory.getTower(DefenderConstants.MODERN_TOWER_TYPE,
				TowerLevel.one);
		
		tower.setTowerPosition(new Position(5, 6));		
		critters[0].setCurrentPosition(3);
		critters[1].setCurrentPosition(4);
		critters[2].setCurrentPosition(9);
		
		SpecialDamageEffectsServiceSplash splashEffect = new SpecialDamageEffectsServiceSplash();
		
		splashEffect.splash(tower, critters[1], wave, 1, path);
		Assert.assertEquals(critters[0].getLife(), initLife-1);
		Assert.assertEquals(critters[1].getLife(), initLife-1);
		Assert.assertEquals(critters[2].getLife(), initLife);
	}
	
	@Test
	public void testFreezeSpecialEffect(){
		WaveFactory waveFactory = new WaveFactory();
		Position[] path = new PathService().pathFinder(grid.getContent());
		Wave wave = waveFactory.getWave("FoolishCritter", new Position(9,7), path);
		
		RegularMove critterMove = ((RegularMove) wave.getAliens().get(0).getMovingBehaviour());
		critterMove.setFreezeTime(100);
		critterMove.move();
		Assert.assertEquals(critterMove.getFreezeTime(), 99);
	}

}
