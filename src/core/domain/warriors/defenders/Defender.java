package core.domain.warriors.defenders;

import java.util.Observable;

import core.applicationservice.informerservices.Observer;
import core.domain.Subject;
import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;

/**
 * <b>this class is imply observer design patter for informing all towers about the critters'position </b>
 * @author Team5
 * @version 0.1
 */
public abstract class Defender extends Observable implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4980198702785542216L;
	protected Subject subject;
	/**
	 * it is a head position of the wave
	 */
	protected Position waveHeadPosition;
	public void setWaveHeadPosition(Position value){
		waveHeadPosition = value;
	}
	public Position getWaveHeadPosition(){
		return waveHeadPosition;
	}
	
	protected Position alienPosition;
	public void setAlienPosition(Position value){
		alienPosition = value;
	}
	public Position getAlienPosition(){
		return alienPosition;
	}
	/**
	 * it is implemented for having observer design pattern
	 * @param waveHeadPosition the head position of the wave
	 */
	public void waveUpdate(Position waveHeadPosition) {
		this.waveHeadPosition = waveHeadPosition;
	}
	

}
