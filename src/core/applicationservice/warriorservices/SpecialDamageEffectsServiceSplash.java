package core.applicationservice.warriorservices;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import core.applicationservice.mapservices.MapCellService;
import core.contract.MapConstants;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.waves.Position;
import core.domain.waves.Wave;

public class SpecialDamageEffectsServiceSplash {

	private HashMap<Critter, Integer> burningCritters = new HashMap<Critter, Integer>();
	private MapCellService mapCellService = new MapCellService();

	public void addBurningCritter(Critter target, int repeatTimes) {
		burningCritters.put(target, repeatTimes);
	}
	
	public void splash(Tower defender, Critter target, Wave wave, int power, Position[] path) {
//		int power = getTowerPropertyBasedOnLevel(defender);

		int gridX = path[target.getCurrentPosition()].getX();
		int gridY = path[target.getCurrentPosition()].getY();
		Position pixel = mapCellService.convertCellToPixel(new Position(gridX, gridY));

		int len = (4) * MapConstants.UNIT_SIZE;
		int cornerX = pixel.getX() - MapConstants.UNIT_SIZE
				- (MapConstants.UNIT_SIZE / 2);
		int cornerY = pixel.getY() - MapConstants.UNIT_SIZE
				- (MapConstants.UNIT_SIZE / 2);
		Rectangle areaOfEffect = new Rectangle(cornerX, cornerY, len, len);

		ArrayList<Critter> critters = new ArrayList<Critter>();
		critters.addAll(wave.getAliens());
		for (Critter c : wave.getAliens()) {
			int gX = path[c.getCurrentPosition()].getX();
			int gY = path[c.getCurrentPosition()].getY();
			Position p = mapCellService.convertCellToPixel(new Position(gX, gY));
			int l = (1) * MapConstants.UNIT_SIZE;
			int cX = (p.getX());
			int cY = p.getY();
			Rectangle critterRect = new Rectangle(cX, cY, l, l);

			if (critterRect.intersects(areaOfEffect)) {
				c.setLife(c.getLife() - power);
			}

		}
	}
}
