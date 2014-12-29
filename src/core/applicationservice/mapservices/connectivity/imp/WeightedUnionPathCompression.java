package core.applicationservice.mapservices.connectivity.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import infrastructure.loggin.Log4jLogger;
import core.applicationservice.mapservices.connectivity.IUnion;
import core.applicationservice.mapservices.connectivity.IWeightedUnionPathCompression;
import core.domain.waves.Position;

/**
 * @author Team5
 * in this case the order of growth is lg*n for the n nodes in union functionality.
 * lg* 65536 = lg* 2^16 = lg 2+lg 2^4 = 1+ lg2 + Lg 4= 1+1+lg2+lg2 =4 !
 */
@Component
public class WeightedUnionPathCompression implements IUnion, IWeightedUnionPathCompression {

	private int[] unionArray;
	private int[] unionSize;
	private Map<Position, Integer> unionMap;
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * <b>we initialize the empty one dimensional array that has n * m size and the nodes are initialized by their index
	 * like this a[0, 1, 2, 3, 4, 5, 6, 7, 8....] 
	 * </b>
	 * @param n the size of the array
	 * @param m the size of array
	 */
	@Override
	public void initialize(int n, int m){
		try {
			unionArray = new int[n * m];
			unionSize = new int[n * m];
			unionMap = new HashMap<>();
			int index = 0;
			for(int i = 0;i < n; i++){
				for(int j = 0; j < m; j++){
					unionArray[index] = index;
					unionSize[index] = 1;
					Position position = new Position(i, j);
					unionMap.put(position, index);
					index++;
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	/**
	 * <b>it can check two nodes have any connection or not</b>
	 * @param p a position on map
	 * @param q a second position on the grid
	 * @return true, if the both of them has a connection  
	 */
	@Override
	public boolean connected(Position p, Position q){
		int pKey = (Integer)unionMap.get(p);
		int qkey = unionMap.get(q);
		return root(pKey) == root(qkey);
	}
	/**
	 * <b>it can get the root of each graph</b>
	 * @param pKey index of node p 
	 * @return int that represent the root node of each graph
	 */
	@Override
	public int root(int pKey){
		try {
			while (pKey != unionArray[pKey]) {
				unionArray[pKey] = unionArray[unionArray[pKey]];
				pKey = unionArray[pKey];
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return pKey;
	}
	/**
	 * <b>it can make a union between two nodes of graph</b>
	 * @param p node p from array
	 * @param q node q from array
	 */
	@Override
	public void union(Position p, Position q){
		try {
			int pKey = (Integer)unionMap.get(p);
			int qkey = unionMap.get(q);
			int i = root(pKey);
			int j = root(qkey);
			if( i == j) return;
			if(unionSize[i] < unionSize[j]){
				unionArray[i] = j;
				unionSize[j] += unionSize[i];
			}else{
				unionArray[j] = i;
				unionSize[i] += unionSize[j];
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
}