package core.applicationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.contract.ITDLogger;

/**
 * this demo was implemented as aDemo for dependecy injection and IOC for the build2
 * @author Team5
 * @version 1.0
 */
@Component
public class MyApplication implements IAplication {
 
    
    /** The service. */
    private ITDLogger service;
   
    /**
     * Sets the service.
     *
     * @param svc the new service
     */
    @Autowired
    public void setService(ITDLogger svc){
        this.service=svc;
    }
     
    /* (non-Javadoc)
	 * @see core.applicationservice.IAplication#processMessage(java.lang.String, java.lang.String)
	 */
    @Override
	public boolean processMessage(String msg, String rec){
    	
        this.service.writer(msg, new Exception());
        return true;
    }
}