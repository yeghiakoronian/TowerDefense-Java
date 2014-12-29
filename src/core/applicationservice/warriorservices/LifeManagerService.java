package core.applicationservice.warriorservices;

import infrastructure.loggin.Log4jLogger;
import core.domain.account.LifeManager;

public class LifeManagerService {
	private static final Log4jLogger logger = new Log4jLogger();
	/**
	 * Manage player life and add life to player 
	 * @param life that will be set for player
	 */
	public void LifeManager(long life){
		try {
			LifeManager manager = LifeManager.getInstance();
			manager.addLife(life);
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
	/**
	 * Reset player life
	 */
	public void resetLife(){
		try {
			LifeManager manager = LifeManager.getInstance();
			manager.resetCurrentLife();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
}
