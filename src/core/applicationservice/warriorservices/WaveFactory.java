package core.applicationservice.warriorservices;

import infrastructure.loggin.Log4jLogger;
import core.contract.WaveConstants;
import core.domain.warriors.aliens.crittertype.FoolishCritter;
import core.domain.warriors.aliens.crittertype.IntelligentCritter;
import core.domain.warriors.aliens.features.Resistance;
import core.domain.waves.Position;
import core.domain.waves.Wave;

public class WaveFactory {
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * it takes wave type name and it will create a type of wave
	 * @param waveType wave that contains a list of critter
	 * @param start start point
	 * @param path valid path
	 * @return Wave correct wave for walking 
	 * 
	 */
	public Wave getWave(String waveType,Position start, Position[] path) {
		Wave wave = new Wave();
		wave.setHeadPosition(start);
		try {
			if (waveType == null) {
				return null;
			}
			if (waveType.equalsIgnoreCase("FoolishCritter")) {
				for (int i = 0; i < WaveConstants.WAVE_SIZE; i++) {
					FoolishCritter critter = new FoolishCritter(start, path);critter.Id= (new Integer(i)).toString();
					wave.aliens.add(critter);
				}
				return wave;
			} else if (waveType.equalsIgnoreCase("IntelligentCritter")) {
				for (int i = 0; i < WaveConstants.WAVE_SIZE; i++) {
					IntelligentCritter critter = new IntelligentCritter();
					Resistance resistance = new Resistance(critter);
					wave.aliens.add(resistance);
				}
				return wave;
			} 
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}

}
