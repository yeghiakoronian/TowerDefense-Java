package core.domain.warriors.aliens.crittertype;

import core.domain.warriors.aliens.Critter;

/**
 * <b>this type of critters can  decide by themself and by game strategy and AI Algorithms</b>
 * @author Team5
 * @version 0.1
 */
@SuppressWarnings("serial")
public class IntelligentCritter extends Critter {
	
	/**
	 * Instantiates a new intelligent critter.
	 */
	public IntelligentCritter() {
		this.description = "IntelligentCritter";
		this.setLife(7);
	}

	/**
	 * <b>Move method will be used in the second build to display critters and it is not complete yet</b>
	 */
	@Override
	public String display() {
		return "I am IntelligentCritter!";

	}

	/**
	 * <b>this method is used for bulid 2 and by this method can return life  for the tower </b>
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 7;
	}

}
