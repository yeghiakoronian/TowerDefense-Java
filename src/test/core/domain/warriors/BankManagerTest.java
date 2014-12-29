package test.core.domain.warriors;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.domain.account.BankManager;

public class BankManagerTest {

	private BankManager bankManager;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp(){
		try {
			bankManager = BankManager.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Test
	public void testAddCurrentBalance(){
		bankManager.resetCurrentBalance();
		BankManager manager = new BankManager();
		manager.resetCurrentBalance();
		long actualBalance = bankManager.getCurrentBalance();
		long expected = manager.getCurrentBalance();
		//assert Part
		assertEquals(expected, actualBalance);
	}
	@Test
	public void testAddBalance(){
		BankManager manager = new BankManager();
		long actualBalance = bankManager.getBalance();
		long expected = manager.getBalance();
		//assert Part
		assertEquals(expected, actualBalance);
	}
//	@Test
//	public void testResetBalance(){
//		lifeManager.addLife(10);
//		LifeManager manager = new LifeManager();
//		manager.addLife(10);
//		int actualLife = lifeManager.getLife();
//		int expected = manager.getLife();
//		//assert Part
//		assertEquals(expected, actualLife);
//	}
}
