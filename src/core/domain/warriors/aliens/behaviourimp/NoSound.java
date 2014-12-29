package core.domain.warriors.aliens.behaviourimp;

import core.domain.warriors.aliens.behaviours.SoundBehaviour;
/**
 * <b> this is used for strategy design pattern
 * this class is defined to implement the one of the sound behavior of a alien
 * that is a kind of no sound</b>
 * @author Team5
 *@version 0.1
 */
public class NoSound implements SoundBehaviour {
	
	/**
	 * <b>this sound method not implemented now but we make the skeleton of the class for the second build</b>
	 */ 
	@Override
	public void sound() {
		System.out.println("I don't have any sound!");

	}

}
