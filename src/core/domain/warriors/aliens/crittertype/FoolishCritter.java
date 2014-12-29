package core.domain.warriors.aliens.crittertype;

import core.contract.MapConstants;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.behaviourimp.RegularMove;
import core.domain.waves.Position;

/**
 * <b>this type of critters can not decide by themself and by game strategy and AI Algorithms</b>
 * @author Team5
 * @version 0.1
 */
@SuppressWarnings("serial")
public class FoolishCritter extends Critter {
	RegularMove regularMove;
	
	/**
	 * Instantiates a new foolish critter.
	 * @param initalPosition init position 
	 * @param path correct phat in the map
	 */
	public FoolishCritter(Position initalPosition, Position[] path) {
		this.regularMove = new RegularMove(path, initalPosition);
		setMovingBehaviour(regularMove);
		this.description = "FoolishCritter";
		setPath(path);
		this.setLife(500);
	}
	/**
	 * Return regular critter movement
	 * @return regularMove
	 */
	public RegularMove getRegularMove() {
		return regularMove;
	}

	/**
	 * <b>Move method will be used in the second build to display critters and it is not complete yet</b>
	 */
	@Override
	public String display() {
		return MapConstants.FOOLISH_CRITTER_IMG;
		
	}

	/**
	 * <b>this method is used for bulid 2 and by this method can return life  for the tower </b>
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 5;
	}

}
