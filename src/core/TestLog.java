package core;

import infrastructure.loggin.Log4jLogger;

public class TestLog {

	private static final Log4jLogger logger = new Log4jLogger();
	
	public static void main(String[] args) {
		
		TestLog t = new TestLog();
		t.test();
	}
	public  void test(){
		try {
			throw new ArithmeticException();
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
	}
}
