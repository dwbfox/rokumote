package util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Remote extends Requests {

	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	public enum Direction { DOWN, UP, LEFT, RIGHT, BACK }
	
	public static final String DOWN = "Down";
	public static final String UP = "up";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	
	public Remote(String ip, int port) {
		setHost(ip);
		setPort(port);
		logger.log(Level.INFO, "New Roku instance:  " + this.getHost() + ":" + this.getPort());

	}
	
	public boolean startApp(String channelID) {
		return false;
	}
	
	/**
	 * Returns the list of apps
	 * that are on the current Roku device
	 * @return a string array containing the apps
	 */
	public String[] getApps() {
		return null;
	}
	
	public boolean sendCommand(String command) {
		return false;
	}
	
	public boolean sendDirection(Direction keyDirection) {
		logger.log(Level.INFO, "Sending direction: " + keyDirection);
		return false;
	}
	

}
