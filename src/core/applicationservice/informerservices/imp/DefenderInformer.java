package core.applicationservice.informerservices.imp;


import infrastructure.loggin.Log4jLogger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import core.applicationservice.informerservices.IDefenderInformer;
import core.applicationservice.informerservices.Observer;
import core.domain.Subject;
import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;


/**
 * <b>In this class we implement observer design pattern to inform all towers the wave's head position</b>
 * @author Team5
 * @version 0.1
 */
@Component
public class DefenderInformer implements Subject, IDefenderInformer, java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5999028712196865753L;
	/**
	 * <b>The wave head position.
	 * this member is the wave's head reperesenteter</b>
	 */
	private Position waveHeadPosition;
	private Critter critter;
	private Position alienPosition;
	private String shootingStrategy;
	/** <b>List of observer that contains the all observer for implementing our observer design pattern</b>
	 * 
	 */
	private List<Observer> observers;
	
	/** The logger that was implemented by log4j2 and the logger class is located in infrastructure
	 */
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * Instantiates a new defender informer.
	 */
	public DefenderInformer(){
		observers = new ArrayList<Observer>();
		alienPosition = new Position(0,0);
	}
	
	/**
	 * <b>
	 * this method can register all observers in observer's list
	 * </b>
	 * @param o Observer object
	 */
	@Override
	public void registerObserver(Observer o) {
		try {
			if( o !=null)
				observers.add(o);
			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}


	/**
	 * <b>
	 * this method can remove a observer from observer's list
	 * </b>
	 * @param o Observer object
	 */
	@Override
	public void removeObserver(Observer o) {
		try {
			if (o !=null)
				observers.remove(o);
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}

	
	/**
	 * <b>
	 * this method can force observers to update 
	 * and towers are informed the position of aliens by this method.
	 * 
	 * <code>
	 * for (Observer ob : observers) {
	 *			ob.waveUpdate(waveHeadPosition);
	 *		}
	 * </code>
	 * </b>
	 */
	@Override
	public void notifyObservers() {
		try {
			for (Observer ob : observers) {
				ob.waveUpdate(waveHeadPosition);
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		
	}
	
	/**
	 * <b>
	 * this method can force observers to update 
	 * and towers are informed the position of aliens by this method.
	 * 
	 * <code>
	 * for (Observer ob : observers) {
     *		ob.alienUpdate(alienPosition);
	 *		}
	 * </code>
	 * </b>
	 */
	@Override
	public void notifyDefenders() {
		try {
			for (Observer ob : observers) {
				ob.alienUpdate(alienPosition, critter, shootingStrategy);
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		
	}
	
	/**
	 * this method sets the wave'poisition
	 * @param x as integer 
	 * @param y as integer
	 */
	@Override
	public void setWavePosition(int x, int y){
		try {
			this.waveHeadPosition.setX(x);
			this.waveHeadPosition.setY(y);
			wavePositionChange();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	/**
	 * this method sets the wave'poisition
	 * @param x as integer 
	 * @param y as integer
	 */
	@Override
	public void setAlienPosition(int x, int y, Critter critter, String strategy){
		try {
			this.alienPosition.setX(x);
			this.alienPosition.setY(y);
			this.critter = critter;
			this.shootingStrategy = strategy;
			alienPositionChange();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * 
	 * if the x and y of the head changes the setPosition method will call this method tho notify the observers 
	 * that are towers
	 */
	@Override
	public void wavePositionChange(){
		try {
			notifyObservers();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	/**
	 * 
	 * if the x and y of the head changes the setPosition method will call this method tho notify the observers 
	 * that are towers
	 */
	@Override
	public void alienPositionChange(){
		try {
			notifyDefenders();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}

	/**
	 * it can return the current position of the wave's head
	 * @return Position 
	 */
	@Override
	public Position getWavePosition() {
		return waveHeadPosition;
	}
	/**
	 * it can return the current position of the alien's position
	 * @return Position, that is the position of critters
	 */
	@Override
	public Position getAlienPosition() {
		return alienPosition;
	}

}
