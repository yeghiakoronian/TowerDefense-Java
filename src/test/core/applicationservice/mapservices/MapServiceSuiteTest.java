package test.core.applicationservice.mapservices;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConnectivityTest.class, MapManagerTest.class, MapUtilityTest.class,
		StartEndCheckerTest.class, PathFinderTest.class })
public class MapServiceSuiteTest {

}
