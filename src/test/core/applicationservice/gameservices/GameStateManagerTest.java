package test.core.applicationservice.gameservices;

import static org.junit.Assert.*;
import core.domain.maps.GridCellContentType;
import core.domain.maps.GridMap;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.AncientTower;
import core.domain.warriors.defenders.towers.towertype.KingTower;
import core.domain.warriors.defenders.towers.towertype.ModernTower;

import org.junit.Test;

import core.applicationservice.gameservices.GameStateManager;

public class GameStateManagerTest {

	@Test
	public void test() {
		GridMap map = new GridMap(10, 10);
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				map.setCell(i, j, (i + j) % 2 == 0 ? GridCellContentType.PATH : GridCellContentType.SCENERY);
			}
		}
		Tower [][] towers = new Tower[10][10];
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if((i + j) % 2 != 0){
					if((i + j) % 3 == 0){
						towers[i][j] = new AncientTower();
					}
					else if((i + j) % 5 == 0){
						towers[i][j] = new ModernTower();
					}
					else{
						towers[i][j] = new KingTower();
					}
				}
			}	
		}
		map.setTowers(towers);
		
		int wave = 2;
		String path = ".\\";
		String fileName = "test.fil";
		GameStateManager stateManager = new GameStateManager(map, wave, path);
		GameStateManager.save(fileName, stateManager);
		GameStateManager loadedGame = GameStateManager.load(fileName);
		assertEquals(loadedGame.getBalance(), stateManager.getBalance());
		assertEquals(loadedGame.getCurrentBalance(), stateManager.getCurrentBalance());
		assertEquals(loadedGame.getLife(), stateManager.getLife());
		assertEquals(loadedGame.getMapLocation(), stateManager.getMapLocation());
		assertEquals(loadedGame.getWaveNum(), stateManager.getWaveNum());
		assertEquals(loadedGame.getMap().getHeight(), stateManager.getMap().getHeight());
		assertEquals(loadedGame.getMap().getWidth(), stateManager.getMap().getWidth());
		assertEquals(loadedGame.getMap().getUnitSize(), stateManager.getMap().getUnitSize());
		int width = loadedGame.getMap().getWidth();
		int height = loadedGame.getMap().getHeight();
		for(int x = 0; x < width; x++){ // checks all the scenery and path cells
			for(int y = 0; y < height; y++){
				assertEquals(loadedGame.getMap().getCell(x, y), stateManager.getMap().getCell(x, y));
			}
		}
		
		Tower[][] loadedTowers = loadedGame.getMap().getTowers();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if((i + j) % 2 == 0){
					assertEquals(loadedTowers[i][j], towers[i][j]); // both of the containers has to be null
					
				}
				else
				{
					assertEquals(loadedTowers[i][j].getTowerType(), towers[i][j].getTowerType());
					assertEquals(loadedTowers[i][j].getLevel(), towers[i][j].getLevel());
					assertEquals(loadedTowers[i][j].getShootingStrategy(), towers[i][j].getShootingStrategy());
					assertEquals(loadedTowers[i][j].getTowerPosition(), towers[i][j].getTowerPosition());
				}
			}
		}
	}

}
