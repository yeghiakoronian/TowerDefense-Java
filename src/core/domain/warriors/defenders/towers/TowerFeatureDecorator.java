package core.domain.warriors.defenders.towers;

import infrastructure.loggin.Log4jLogger;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import core.applicationservice.informerservices.Observer;
import core.applicationservice.locationservices.PositionService;
import core.applicationservice.warriorservices.ShootingService;
import core.applicationservice.warriorservices.TowerFactory;
import core.contract.DefenderConstants;
import core.domain.Subject;
import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;

/**
 * <b>This class is used for decorator design pattern for towers</b>
 * 
 * @author Team5
 * @version 0.1
 */
@SuppressWarnings("serial")
public abstract class TowerFeatureDecorator extends Tower implements Observer {
	private Critter target;
	public Map<Critter, Position> crittersLocation;
	public double nearestDistance;
	
	
	/** The logger that was implemented by log4j2 and the logger class is located in infrastructure
	 */
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * Return a map contain critters and their positions
	 * @return crittersLocation
	 */
	public Map<Critter, Position> getCrittersLocation() {
		return crittersLocation;
	}
	/**
	 * Set critter locations in the map in our Map data structure
	 * @param crittersLocation a key value pair for keeping critter in a range for a tower
	 */
	public void setCrittersLocation(Map<Critter, Position> crittersLocation) {
		crittersLocation = crittersLocation;
	}

	/**
	 * it is implemented for having observer design pattern
	 */
	public void alienUpdate(Position alienPosition, Critter critter,String shootingStrategy) {
		try {
			Map<Critter, Position> map = this.getCrittersLocation();
			Set<Entry<Critter, Position>> it =  map.entrySet();
			ShootingService service =  new ShootingService();
			PositionService positionService = new PositionService();
			TowerFactory factory = new TowerFactory();
			int range = factory.getRange(this);
			
			if(!positionService.isInRange(this.getTowerPosition(), 
					critter.getPath()[critter.getCurrentPosition()], range)){
				for (Entry<Critter, Position> entry : it) {
					if(entry.getKey() == critter){
						map.remove(entry.getKey());
						this.setCrittersLocation(map);
						break;
					}
				}
			}
			
			if(positionService.isInRange(this.getTowerPosition(), 
					critter.getPath()[critter.getCurrentPosition()], range) && critter.getLife() > 0){
				map.put(critter, alienPosition);
				this.setCrittersLocation(map);
				Critter c = null;
				if(shootingStrategy == null){
					this.setShootingStrategy( DefenderConstants.NearToEnd_Strategy);
					c = service.nearToStartCritter(this, critter.getPath());
				}else{
					switch (shootingStrategy) {
					case DefenderConstants.NearToEnd_Strategy:
						c = service.nearToEndCritter(this, critter.getPath());
						break;

					case DefenderConstants.NearToStart_Strategy:
						c = service.nearToStartCritter(this, critter.getPath());
						break;

					case DefenderConstants.Strangest_Strategy:
						c = service.strongestCritter(this);
						break;

					case DefenderConstants.Weakest_Strategy:
						c = service.weakestCritter(this);
						break;

					case DefenderConstants.NearToTower_Strategy:
						c = service.nearestCritter(this);
						break;
					}
				}
				if(c !=null){
					setTarget(c);
					setChanged();
					notifyObservers();
				}
			}else{
				for (Entry<Critter, Position> entry : it) {
					if(entry.getKey() == critter){
						map.remove(entry);
						this.setCrittersLocation(map);
						break;
					}
				}
			}

			
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		



	}
	/**
	 * remove dead critters from our wave
	 * @param critter critter
	 */
	public void removeDeadCritter(Critter critter) {
		try {
			crittersLocation.remove(critter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * <b>it is a signature of getting description of the tower that is means it
	 * can return tower type and the all its features as a String</b>
	 * 
	 * @return descrition of the tower
	 */
	public abstract String getDescription();
	/**
	 * 
	 * @param defenderInformer defender informer is added for deploy the observer pattern
	 */
	public void register(Subject defenderInformer) {
		this.subject = defenderInformer;
		this.subject.registerObserver(this);
	}
	/**
	 * Return target critter which tower has to shoot it
	 * @return target
	 */
	public Critter getTarget() {
		return target;
	}
	/**
	 * 
	 * @return TowerFeatureDecorator 
	 */
	public TowerFeatureDecorator getDefender() {
		return this;
	}
	/**
	 * Set target for shooting
	 * @param target critter that is calculated as a target
	 */
	public void setTarget(Critter target) {
		this.target = target;
	}

}
