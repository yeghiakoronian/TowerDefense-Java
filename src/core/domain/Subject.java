package core.domain;

import core.applicationservice.informerservices.Observer;
/**
 * Subject class for observer design pattern
 * 
 * @author Team 5
 *
 */
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public void notifyDefenders();
}
