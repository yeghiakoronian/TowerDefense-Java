package test.core.applicationservice.mapservices;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationservice.mapservices.MapManager;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;

public class MapManagerTest {

	private static String fileName;
	private static Grid originalGrid;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		String path = ".\\";
		fileName = "test.fil";

		originalGrid = new Grid();
		originalGrid.setSize(10, 10);
		originalGrid.setCell(0, 5, GridCellContentType.ENTRANCE);
		for(int i=1; i<9; i++){
			originalGrid.setCell(i, 5, GridCellContentType.PATH);
		}
		originalGrid.setCell(9, 5, GridCellContentType.EXIT);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {

	}

	@Test
	public void testMapSaveLoad() {
		
		MapManager mapManager = new MapManager();
		mapManager.saveMapIntoFle(originalGrid, fileName);

		Grid loadedGrid = new Grid();
		loadedGrid = mapManager.loadMapFromFile(fileName);
		
		Assert.assertEquals(originalGrid.getWidth(), loadedGrid.getWidth());
		Assert.assertEquals(originalGrid.getHeight(), loadedGrid.getHeight());
		for(int i =0; i<originalGrid.getWidth();i++){
			for(int j =0; j<originalGrid.getHeight();j++){
				Assert.assertEquals(originalGrid.getCell(i, j), loadedGrid.getCell(i, j));
			}
		}
	}

	@Test
	public void testPlayLog(){
		originalGrid.addPlayLog(new Date().toString(), 200);
		MapManager mapManager = new MapManager();
		mapManager.saveMapIntoFle(originalGrid, fileName);

		Grid loadedGrid = new Grid();
		loadedGrid = mapManager.loadMapFromFile(fileName);
		
		Assert.assertTrue(originalGrid.comparePlayLog(loadedGrid));
	}
}
