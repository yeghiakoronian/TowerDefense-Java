package core.applicationservice.mapservices.connectivity;

import core.domain.waves.Position;

/**
 * The Interface IWeightedUnionPathCompression.
 * it is defined to use in ioc
 * @version 0.1
 */
public interface IWeightedUnionPathCompression {

	/**
	 * Initialize.
	 *
	 * @param n the n
	 * @param m the m
	 */
	public void initialize(int n, int m);

	/**
	 * Connected.
	 *
	 * @param p the p
	 * @param q the q
	 * @return true, if successful
	 */
	public boolean connected(Position p, Position q);

	/**
	 * Root.
	 *
	 * @param pKey the key
	 * @return the int
	 */
	public int root(int pKey);

	/**
	 * Union.
	 *
	 * @param p the p
	 * @param q the q
	 */
	public void union(Position p, Position q);

}