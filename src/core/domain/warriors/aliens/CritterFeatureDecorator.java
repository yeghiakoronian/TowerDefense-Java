package core.domain.warriors.aliens;

/**
 * <b>This class is used for decorator design pattern
 * it is not completed like towers it has to be completed in build 2</b>
 * @author Team5
 * @version 0.1
 */
@SuppressWarnings("serial")
public abstract class CritterFeatureDecorator extends Critter {
	/**
	 * string definition of decorated object that contains the creator and the all features
	 */
	public abstract String getDescription();
	
}
