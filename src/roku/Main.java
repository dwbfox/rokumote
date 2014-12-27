/**
 * 
 */
package roku;

import util.Remote;

public class Main {

	
	public static void main(String[] args) {
		
		Remote roku = new Remote("192.168.1.104", 8060);
		roku.getAppList();
	}

}
