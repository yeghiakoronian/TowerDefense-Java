package core.domain.warriors.aliens.behaviourimp;

import core.domain.warriors.aliens.behaviours.SoundBehaviour;
/**
 * <b> this is used for strategy design pattern
 * this class is defined to implement the one of the sound behavior of a tower
 * that is a kind of dying wail sound</b>
 * @author Team5
 *@version 0.1
 */
public class DyingWailSound implements SoundBehaviour {

	/**
	 * this sound method not implemented now but we make the skeleton of the class for the second build
	 */
	@Override
	public void sound() {
		System.out.println("Ouch!");
	}
}
