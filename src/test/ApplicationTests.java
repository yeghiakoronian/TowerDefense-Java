package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.core.applicationservice.gameservices.GameStateManagerSuiteTest;
import test.core.applicationservice.locationservices.LocationSuiteTest;
import test.core.applicationservice.mapservices.MapServiceSuiteTest;
import test.core.applicationservice.warriorservices.SpecialDamageSuiteTest;
import test.core.domain.warriors.TowersSuiteTest;

@RunWith(Suite.class)
@SuiteClasses({ TowersSuiteTest.class, MapServiceSuiteTest.class,
		LocationSuiteTest.class, SpecialDamageSuiteTest.class,
		GameStateManagerSuiteTest.class })
public class ApplicationTests {
	
}
