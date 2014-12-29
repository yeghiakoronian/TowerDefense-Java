package core.applicationservice.informerservices;

import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;

/**
 * The Interface Observer.
 * we need this interface for implementing of observer design pattern from the scratch
 */
public interface Observer {
	
	/**
	 *
	 * @param position  as Position that represent the position of the wave's head
	 */
	public void waveUpdate(Position position);
	/**
	 * 
	 * @param position position of alein
	 * @param critter critter that move in the path
	 * @param shootingStrategy as a String define the shooting strategy to kill the aliens  such as nearest to end or start and so on.
	 */
	public void alienUpdate(Position position, Critter critter,String shootingStrategy);

}
