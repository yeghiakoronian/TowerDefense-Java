package core.domain.warriors.aliens.behaviourimp;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;
/**
 * <b> this is used for strategy design pattern
 *this class is defined to implement the one of the moving behavior of a alien
 * that is a kind of move by strategy</b>
 * @author Team5
 * @version 0.1
 */
public class MoveByStrategy implements MovingBehaviour {

	/**
	 * <b>this sound method not implemented now but we make the skeleton of the class for the second build</b>
	 */
	@Override
	public void move() {
		System.out.println("I can move by game's strategy!");

	}

}
