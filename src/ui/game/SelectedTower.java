package ui.game;

import core.domain.warriors.defenders.towers.Tower;
/**
 * 
 * @author team5
 *
 */
public class SelectedTower {

	private String towerType;
	private Tower tower;
	private boolean addTowerFlag;
	
	private static SelectedTower instance;
	
	/**
	 * public constructor of the class
	 */
	private SelectedTower(){
		
	}
	
	/**
	 * creates an instance of the class if it is not yet created
	 * @param towerType type of a tower needs to be created
	 * @param tower tower object 
	 * @param addTowerFlag tower is added flag
	 */
	public static void setInstance(String towerType, Tower tower, boolean addTowerFlag){
		if(instance == null){
			instance = new SelectedTower();
		}
		instance.towerType = towerType;
		instance.tower = tower;
		instance.addTowerFlag = addTowerFlag;
	}
	
	/**
	 * returns an instance of a class
	 * @return tiwer type
	 */
	public static String getTowerType(){
		if(instance == null){
			instance = new SelectedTower();
		}
		return instance.towerType;
	}
	
	/**
	 * returns an instance of a class
	 * @return instance of a tower 
	 */
	public static Tower getTower(){
		if(instance == null){
			instance = new SelectedTower();
		}
		return instance.tower;
	}
	
	/**
	 * returns an instance of a class
	 * @return returns true if tower is added
	 */
	public static boolean getAddTowerFlag(){
		if(instance == null){
			instance = new SelectedTower();
		}
		return instance.addTowerFlag;
	}

}
