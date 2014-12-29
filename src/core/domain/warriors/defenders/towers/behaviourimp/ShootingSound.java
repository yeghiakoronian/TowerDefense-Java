package core.domain.warriors.defenders.towers.behaviourimp;

import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;
/**
 * <b>this is used for strategy design pattern
 * this class is defined to implement the one of the shooting sound behavior of a tower
 * that is a kind of shooting sound</b>
 * @author Team5
 *@version 0.1
 */
public class ShootingSound implements SoundBehaviour {
	/**
	 * this sound should to play during shooting by a tower
	 */
	@Override
	public void sound() {
		System.out.println("I have shooting sound!");

	}

}
