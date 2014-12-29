package core.domain.warriors.aliens;

import java.util.UUID;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;
import core.domain.warriors.aliens.behaviours.SoundBehaviour;
import core.domain.waves.Position;


/**
 * this class is the abstract class that has all information about critters and it is ready to 
 * use in decorated critters and for having different critters by different behaviors(Strategy Design pattern)
 * 
 * @author Team5
 * @version 0.1
 */
public abstract class Critter implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7151914372955282259L;
	
	
	/** The Id. */
	public String Id= UUID.randomUUID().toString();
	private int currentPosition;
	private Position[] path;
	private Position initialPixel;


	/** The moving behaviour. */
	transient MovingBehaviour movingBehaviour;

	/** The sound behaviour. */
	transient SoundBehaviour soundBehaviour;

	private int life;
	/**
	 * return critters life
	 * 
	 * @return life
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * set critters life
	 *  
	 * @param life life of player
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * return critter current position
	 * 
	 * @return currentPosition
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * set critter current position
	 * 
	 * @param currentPosition current position 
	 */
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * Sets the moving behavior.
	 *
	 * @param movingBehaviour the new moving behavior
	 */
	public void setMovingBehaviour(MovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	
	/**
	 * return movingBehaviour of a critters
	 * 
	 * @return movingBehaviour
	 */
	public MovingBehaviour getMovingBehaviour() {
		return this.movingBehaviour;
	}

	/**
	 * Sets the sound behavior.
	 * 
	 * @param soundBehaviour the new sound behavior
	 */
	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}


	/**
	 * get initialPixel of a critter
	 * 
	 * @return position 
	 */
	public Position getInitialPixel() {
		return initialPixel;
	}
	
	/**
	 * Set initalPixel for a critter
	 * 
	 * @param initialPixel initilaziation 
	 */
	public void setInitialPixel(Position initialPixel) {
		this.initialPixel = initialPixel;
	}

	/**
	 * Perform moving behavior.
	 */
	public void performMovingBehaviour(){
		movingBehaviour.move();
	}

	/**
	 * Perform sound behavior.
	 */
	public void performSoundBehaviour(){
		soundBehaviour.sound();
	}

	/** The description. */
	protected String description;

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String value) {
		description = value;
	}

	/**
	 * return path as a position object contain x and y as actual position on the grid
	 * 
	 * @return path
	 */
	public Position[] getPath() {
		return path;
	}

	/**
	 * Set a valid path for critters
	 * 
	 * @param path correct path
	 */
	public void setPath(Position[] path) {
		if(path != null)
			this.path = path;
	}

	public abstract String display();

	public abstract double lifeBooster();
}
