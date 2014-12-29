package core.applicationservice.warriorservices;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import core.domain.warriors.aliens.Critter;

public class SpecialDamageEffectsServiceBurn {

	private HashMap<Critter, Integer> burningCritters = new HashMap<Critter, Integer>();

	public void addBurningCritter(Critter target, int repeatTimes) {
		burningCritters.put(target, repeatTimes);
	}
	
	public void burn() {
		Iterable<Entry<Critter, Integer>> its = burningCritters.entrySet();
		for (Entry<Critter, Integer> pairs : its) {
			Critter critter = pairs.getKey();
			critter.setLife(critter.getLife()-1);
			pairs.setValue(pairs.getValue()-1);
		}
		
		for(Iterator<Map.Entry<Critter, Integer>> it = burningCritters.entrySet().iterator(); it.hasNext(); ) {
		      Map.Entry<Critter, Integer> entry = it.next();
		      if(entry.getValue().equals(0)) {
		        it.remove();
		      }
		    }
		

	}
}
