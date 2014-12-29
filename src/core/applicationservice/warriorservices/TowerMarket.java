package core.applicationservice.warriorservices;

import infrastructure.loggin.Log4jLogger;
import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
/**
 * <b>this class is used for sell functionality</b> 
 * @author team 
 * @version 0.1
 */
public class TowerMarket {
	private static final Log4jLogger logger = new Log4jLogger();
	/**
	 * <b>this method calculate the sell value base on the decorated tower </b>
	 * @param tower the tower that player wants to sell
	 * @param <T> is type of tower
	 * @return sale value of a tower
	 */
	public <T extends Tower> long sellTower(T tower){
		long amount = 0;
		try {
				amount = (tower.cost() * DefenderConstants.Sell_Percentage)/100;
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return amount;
	}
}
