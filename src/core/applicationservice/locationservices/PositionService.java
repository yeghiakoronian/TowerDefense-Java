package core.applicationservice.locationservices;

import infrastructure.loggin.Log4jLogger;
import core.domain.waves.Position;

public class PositionService {
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * 
	 * @param p
	 *            first position
	 * @param q
	 *            goal position
	 * @return the distance between two places
	 */
	public double getDistance(Position p, Position q) {
		double result = 0;
		try {
			result = Math.sqrt(Math.pow((p.getX() - q.getX()), 2)
					+ Math.pow((p.getY() - q.getY()), 2));

		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return result;
	}

	/**
	 * 
	 * @param source first position
	 *            
	 * @param destination goal position
	 *            
	 * @param range the range that is used as a scale
	 *            
	 * @return true, if position q is in the range
	 */
	public boolean isInRange(Position source, Position destination, int range) {
		try {
			return ((destination.getY() <= source.getY() + range && destination
					.getY() >= source.getY() - range) && (destination.getX() <= source
					.getX() + range && destination.getX() >= source.getX()
					- range));

		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return false;
	}
}
