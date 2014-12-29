package core.domain.warriors.defenders.towers.towertype;


import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourimp.BulletShooting;
import core.domain.warriors.defenders.towers.behaviourimp.LineShooting;
import core.domain.warriors.defenders.towers.behaviourimp.MoveByMouse;
import core.domain.warriors.defenders.towers.behaviourimp.ShootWithWeapon;
import core.domain.warriors.defenders.towers.behaviourimp.ShootingSound;
/**
 * <b>this type of tower has ShootWithWeapon as a weapon and it has MoveByMouse and ShootingSound </b>
 * @author Team5
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ModernTower extends Tower {

	public ModernTower() {
		setMovingBehaviour(new MoveByMouse());
		setShootingBehaviour(new ShootWithWeapon());
		setSoundBehaviour(new ShootingSound());
		setBulletShootingBehaviour(new BulletShooting());
		setLineShootingBehaviour(new LineShooting());
		//super.crittersLocation = new HashMap<Critter, Position>();
		this.description = "ModernTower";
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that is representative of a tower
	 */
	@Override
	public String display() {
		return MapConstants.MODERN_TOWER_IMG;

	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower type that is a base tower</b>
	 * <code>
	 *  return DefenderConstants.MODERN_TOWER;
	 * </code>
	 */
	@Override
	public long cost() {
		return DefenderConstants.MODERN_TOWER;
	}

}
