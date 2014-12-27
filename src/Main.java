
import java.util.HashMap;
import java.util.Map;

import rokumote.Remote;
import rokumote.Remote.Channel;
import rokumote.utils.Button;

public class Main {

	
	public static void main(String[] args) {
		
		Remote roku = new rokumote.Remote("192.168.1.104", 8060);
		roku.enableDevMode();

	}

}
