package core.applicationservice.informerservices;

import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;

/**
 * <b>
 * the interfaces that DefenderInformer calss have to implement for dependency injection 
 * for the futures build
 * </b>
 * @author Team5
 * @version 0.1
 */
public interface IDefenderInformer {


	public void registerObserver(Observer o);

	/**
	 * 
	 * this signature can show removing one of tower or defender from observers'list of defenders
	 * @param o the object the you want to remove from the observers'list
	 */
	public void removeObserver(Observer o);

	/**
	 * Signature of the method of notify defenders about the latest position of wave 
	 */
	public void notifyObservers();

	/**
	 * set the wave's head position
	 * @param x as integer 
	 * @param y as integer
	 */
	public void setWavePosition(int x, int y);
	/**
	 * set the alien's position
	 * @param x as integer 
	 * @param y as integer
	 * @param critter critter object reference
	 * @param strategy strategy for shooting
	 */
	public void setAlienPosition(int x, int y, Critter critter, String strategy);
	
	/**
	 * <b>signature of the position changes</b>
	 */
	public void wavePositionChange();
	/**
	 * <b>signature of the position changes</b>
	 */
	public void alienPositionChange();

	/**
	 * this signature represent the way of getting position of the wave's head
	 * @return Position 
	 */
	public Position getWavePosition();
	/**
	 * this signature represent the way of getting position of the wave's head
	 * @return Position 
	 */
	public Position getAlienPosition();

}