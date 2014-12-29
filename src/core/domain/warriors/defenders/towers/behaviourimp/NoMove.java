package core.domain.warriors.defenders.towers.behaviourimp;

import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;

/**
 * <b> this is used for strategy design pattern
 *this class is defined to implement the one of the moving behavior that is disable moving behavior for tower
 * that is a kind of move by strategy</b>
 * @author Team5
 * @version 0.1
 */
public class NoMove implements MovingBehaviour {
	/**
	 * moving action of a tower during the game on the grid this tower should not to able by mouse or other ways on the grid
	 */
	@Override
	public void move() {
		System.out.println("I can not move!");

	}

}
