package core.domain.warriors.defenders.towers.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.contract.DefenderConstants;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.waves.Position;
/**
 * <b>this class used to implement decorator pattern for Towers
 * that is one of the feature that are used for towers</b>
 * @author Team5
 *@version 0.1
 */
@SuppressWarnings("serial")
public class FirePower extends TowerFeatureDecorator {

	private Tower tower;
	public Tower getTower() {
		return tower;
	}
	/**
	 * Get tower and create a critter map positions
	 * @param tower tower that we want to decorate
	 */
	public FirePower(Tower tower) {
		this.tower = tower;
		crittersLocation = new HashMap<Critter, Position>();
	}
	/**
	 * in this method we will use to get decorated object description
	 * @return description of tower as String that contains all features and the tower type
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.tower.getDescription() + ",FirePower";
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that can  representative of a feature
	 */
	@Override
	public String display() {
		return this.tower.display();
	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower</b>
	 * <code>
	 *  return DefenderConstants.FIRE_POWER + this.tower.cost();
	 * </code>
	 */
	@Override
	public long cost() {
		return DefenderConstants.FIRE_POWER + this.tower.cost();
	}
	/**
	 * <b>this method can get a precise details about the contributer object to create a decorated tower</b>
	 */
	@Override
	public List<Tower> objectDetials() {
		List<Tower> details = new ArrayList<Tower>();
		try {
			details.addAll(tower.objectDetials());
			details.add(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return details;
	}
	
}
