package util;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main Rokumote class 
 * that interfaces with Roku's
 * HTTP API.
 * 
 * @author birud
 *
 */
public class Remote extends Requests {

	private HashMap appList = new HashMap();
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());


	public Remote(String ip, int port) {
		super(ip, port);
		logger.log(Level.INFO, "New Roku instance:  " + this.getHost() + ":" + this.getPort());

	}
	
	public boolean startApp(String channelID) {
		return false;
	}
	
	public boolean sendDirection(Button keyDirection) {
		logger.log(Level.INFO, "Sending direction: " + keyDirection.value());
		return this.sendKey(keyDirection);
	}


}
