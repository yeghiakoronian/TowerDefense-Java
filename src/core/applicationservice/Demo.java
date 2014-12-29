package core.applicationservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * this demo was implemented as aDemo for dependency injection and IOC for the build2
 * @author Team5
 * @version 1.0
 */
public class Demo {
	public static void main(String[] args) {
		   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
	        IAplication app = context.getBean(MyApplication.class);
	         
	        app.processMessage("Hi Mojtaba", "Team5@abc.com");
	         
	        //close the context
	        context.close();
	}

}
