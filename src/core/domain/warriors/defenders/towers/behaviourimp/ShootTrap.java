package core.domain.warriors.defenders.towers.behaviourimp;

import core.domain.warriors.defenders.towers.behaviours.ShootingBehaviour;
/**
 * <b> this is used for strategy design pattern
 * this class is defined to implement the one of the shooting behavior of a tower
 * that is a kind of weapon that tower will be able to use in during the game</b>
 * @author Team5
 * @version 0.1
 */
public class ShootTrap implements ShootingBehaviour {
	/**
	 * <b>this sound method not implemented now but we make the skeleton of the class for the second build</b>
	 */
	@Override
	public void shoot() {
		System.out.println("I use traps");

	}

}
