package core.applicationservice.mapservices.connectivity;

import core.domain.waves.Position;


/**
 * The Interface IUnion.
 */
public interface IUnion {
	
	/**
	 * Union.
	 *
	 * @param p the p
	 * @param q the q
	 */
	public void union(Position p, Position q);
	
	/**
	 * Initialize.
	 * initialize a matrix that we can transform our grid into.
	 * @param n the n
	 * @param m the m
	 */
	public void initialize(int n, int m);
	
	/**
	 * Connected. checkin for validation part in design map
	 *
	 * @param p the p
	 * @param q the q
	 * @return true, if successful
	 */
	public boolean connected(Position p, Position q);
}
