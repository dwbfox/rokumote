/**
 * 
 */
package roku;

import util.Remote;
import util.Remote.Direction;

public class Main {

	
	public static void main(String[] args) {
		
		Remote roku = new Remote("192.168.1.104", 80);
		roku.sendDirection(Direction.UP);
	}

}
