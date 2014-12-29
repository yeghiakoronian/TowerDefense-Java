package core.applicationservice.mapservices;

import infrastructure.loggin.Log4jLogger;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;
/**
 * thos class are used for working with a map
 * @author Team5
 * @version 0.1
 */
public class MapUtility {
	private static final Log4jLogger logger = new Log4jLogger();
	/**
	 * <b>this method can find the enterance point </b>
	 * @param map grid info
	 * @return Position of the entrance from mapd 
	 */
	public Position getEnter(GridCellContentType[][] map){
		try {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == GridCellContentType.ENTRANCE)
						return new Position(i, j);
				}
			}
			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}
	/**
	 * <b>this method can find the exit point </b>
	 * @param map grid info
	 * @return Position of the entrance from exit 
	 */
	public Position getExit(GridCellContentType[][] map){
		try {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == GridCellContentType.EXIT)
						return new Position(i, j);
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}

}
