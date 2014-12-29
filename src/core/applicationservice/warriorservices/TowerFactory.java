package core.applicationservice.warriorservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.hamcrest.text.StringEndsWith;

import infrastructure.loggin.Log4jLogger;
import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.account.LifeManager;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towertype.AncientTower;
import core.domain.warriors.defenders.towers.towertype.KingTower;
import core.domain.warriors.defenders.towers.towertype.ModernTower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.warriors.defenders.towers.towertype.TowerType;
/**
 * <b>this class is used as a application service to create a difrent type of factories by difrent features</b>
 * @author Team5
 * @version 0.1
 */
public class TowerFactory {
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * it takes tower type name and it will create a type of tower without any features
	 * @param towertype the name of tower
	 * @return Tower 
	 */
	public Tower getTower(String towertype) {
		if (towertype == null) {
			return null;
		}
		List<Tower> lst = new ArrayList<>();
		if (towertype.equalsIgnoreCase("ModernTower")) {
			ModernTower tower = new ModernTower();
			tower.setTowerType(TowerType.Modern);
			lst.add(tower);
			tower.setTowers(lst);
			int id = LifeManager.getInstance().getIdManager();
			tower.Id = (new Integer(id).toString());
			LifeManager.getInstance().setIdManager(id + 1);
			tower.setShootingStrategy(DefenderConstants.NearToEnd_Strategy);
			return tower;
		} else if (towertype.equalsIgnoreCase("AncientTower")) {
			AncientTower tower = new AncientTower();
			tower.setTowerType(TowerType.Ancient);
			lst.add(tower);
			tower.setTowers(lst);
			int id = LifeManager.getInstance().getIdManager();
			tower.Id = (new Integer(id).toString());
			LifeManager.getInstance().setIdManager(id + 1);
			tower.setShootingStrategy(DefenderConstants.NearToEnd_Strategy);
			return tower;
		} else if (towertype.equalsIgnoreCase("KingTower")) {
			KingTower tower = new KingTower();
			tower.setTowerType(TowerType.King);
			lst.add(tower);
			tower.setTowers(lst);
			int id = LifeManager.getInstance().getIdManager();
			tower.Id = (new Integer(id).toString());
			LifeManager.getInstance().setIdManager(id + 1);
			tower.setShootingStrategy(DefenderConstants.NearToEnd_Strategy);
			return tower;
		}
		return null;
	}
	/**
	 * it creates a tower by tower type name and tower level that can define the feature numbers as a default
	 * @param towertype the name of the tower
	 * @param level , it is a type of TowerLevel that is a enum type that has level one, two and three
	 * @return Tower
	 */
	public Tower getTower(String towertype, TowerLevel level) {
		Tower tower = getTower(towertype);
		try {
			switch (level.name()) {
			case "one":
				for (int i = 0; i < 1; i++)
					tower = getLevelOne(tower);
				tower.setLevel(TowerLevel.one);
				break;

			case "two":
				for (int i = 0; i < 2; i++)
					tower = getLevelOne(tower);
				tower.setLevel(TowerLevel.two);
				break;

			case "three":
				for (int i = 0; i < 3; i++)
					tower = getLevelOne(tower);
				tower.setLevel(TowerLevel.three);
				break;

			}
			
			int latestID = LifeManager.getInstance().getIdManager();
			tower.Id = (new Integer(latestID)).toString();
			LifeManager.getInstance().setIdManager(latestID);
			return tower;
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}

	/**
	 * it can create simple basic decorated tower in level one that means it has fire speed and range and power 1 1 
	 * @param tower
	 * @return Tower
	 */
	Tower getLevelOne(Tower tower) {
		try {
			List<Tower> lst = tower.getTowers();
			tower = new FirePower(tower);
			lst.add(tower);
			tower = new FireRange(tower);
			lst.add(tower);
			tower = new FireSpeed(tower);
			lst.add(tower);
			tower.setTowers(lst);
			return tower;
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}

	/**
	 * <b>create a decorated tower by list of contributors tower </b> 
	 * @param details, is a list of Tower that can have a contribute to create a decorated tower 
	 * @return Tower
	 */
	public Tower getDecoratedTower(List<Tower> details) {
		Tower baseTower = null;
		try {
			for (Tower tower : details) {
				if (tower.getClass() == ModernTower.class) {
					baseTower = new ModernTower();
					break;
				} else if (tower.getClass() == AncientTower.class) {
					baseTower = new AncientTower();
					break;
				} else if (tower.getClass() == KingTower.class) {
					baseTower = new KingTower();
					break;
				}
			}
			for (Tower tower : details) {
				switch (tower.getClass().getSimpleName()) {
				case "FireSpeed":
					if (baseTower != null)
						baseTower = new FireSpeed(baseTower);
					break;
				case "FirePower":
					if (baseTower != null)
						baseTower = new FirePower(baseTower);
					break;
				case "FireRange":
					if (baseTower != null)
						baseTower = new FireRange(baseTower);
					break;
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return baseTower;
	}
	/**
	 * <b> this method can get a list of contributor that can create a decorated tower with infinitive features
	 *  an it returns the list of key value pair that contains the features' name as a key and features' count as a value</b> 
	 * @param towerDetails is the list that we can get from tower by calling objectDetails that contains all features and base tower
	 * @return key valu pair of string and Integer
	 */
	public Map<String, Integer> getFeaturesCount(List<Tower> towerDetails) {
		Map<String, Integer> details = new HashMap<>();
		details.put("FirePower", 0);
		details.put("FireRange", 0);
		details.put("FireSpeed", 0);
		try {
			for (Object tower : towerDetails) {
				if (tower instanceof TowerFeatureDecorator) {
					String key = tower.getClass().getSimpleName();
					details.put(key, details.get(key) + 1);
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return details;
	}
	/**
	 * this method returns the fire range of tower
	 * @param tower that we need the fire range of it
	 * @return range of fire 
	 */
	public int getRange(Tower tower){
		try {
			Map<String, Integer> featuresCount = getFeaturesCount(tower.getTowers());
			int rangeCount = featuresCount.get("FireRange");
			return rangeCount;
			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return 0;
	}
	/**
	 * this method returns the fire Shooting speed of tower
	 * @param tower that we need the fire speed of it
	 * @return speed of fire 
	 */
	public int getSpeed(Tower tower){
		try {
			Map<String, Integer> featuresCount = getFeaturesCount(tower.getTowers());
			int speedCount = featuresCount.get("FireSpeed");
			return speedCount;
			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return 0;
	}
	/**
	 * this method returns the fire Shooting power of tower
	 * @param tower that we need the fire power of it
	 * @return power of fire 
	 */
	public int getPower(Tower tower){
		try {
			Map<String, Integer> featuresCount = getFeaturesCount(tower.getTowers());
			int powerCount = featuresCount.get("FirePower");
			return powerCount;
			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return 0;
	}
	/**
	 * 
	 * @param towerDetails the decorated tower base tower and all features
	 * @return string, base tower that is used for crate decorated tower
	 */
	public String getDecoratedName(List<Tower> towerDetails) {
		try {
			for (Object tower : towerDetails) {
				String key = tower.getClass().getSimpleName();
				if(key.equals(DefenderConstants.MODERN_TOWER_TYPE) ||
						key.equals(DefenderConstants.ANCIENT_TOWER_TYPE) ||
						key.equals(DefenderConstants.KING_TOWER_TYPE)){
					return key;
				}
			}

		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}
	/**
	 * <b>it used in update tower in our grid with freatures count</b>
	 * @param towertype name of tower
	 * @param speedCount speed feature count
	 * @param rangeCount range feature count
	 * @param powerCount power feature count
	 * @param tower the tower that has to upgrade 
	 * @return Tower 
	 */
	public Tower updateLevel(Tower tower, String towertype, int speedCount, int rangeCount,
			int powerCount) {
		String id = tower.Id;
		String strategy = tower.getShootingStrategy();
		TowerFactory factory = new TowerFactory();
		List<Tower> lst = tower.getTowers();
		int size = lst.size();
		for (int i = size - 1; i >= 0; i--) {
		    if(lst.get(i) instanceof TowerFeatureDecorator){
		        lst.remove(i);
		    }
		}
		Tower newTower = factory.getTower(towertype);
		for (int i = 0; i < powerCount; i++){
			newTower = new FirePower(newTower);
			lst.add(newTower);
		}
		for (int i = 0; i < rangeCount; i++){
			newTower = new FireRange(newTower);
			lst.add(newTower);
		}
		for (int i = 0; i < speedCount; i++){
			newTower = new FireSpeed(newTower);
			lst.add(newTower);
		}
		
		newTower.setTowers(lst);
		newTower.Id = id;
		newTower.setShootingStrategy(strategy);
		return newTower;
	}

}