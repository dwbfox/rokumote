
import java.util.ArrayList;
import java.util.HashMap;

import rokumote.Remote;
import rokumote.Remote.Channel;
import rokumote.utils.Button;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		try {
			Remote roku = new rokumote.Remote("192.168.1.104", 8060);
			HashMap<String, Channel> channels = roku.getAppList();
			ArrayList<HashMap<String, Channel>> channelList = new ArrayList();
			channelList.add(channels);
			Scanner input = new Scanner(System.in);

			// A simple REPL to interact with the new API
			while (1==1) {
				System.out.print("Command: ");
				String in = input.nextLine();
				Button direction = null;
				
				switch (in) {
				case "down":
					direction = Button.DOWN;
					break;
				case "up":
					direction = Button.UP;
					break;
				case "left":
					direction = Button.LEFT;
					break;
				case "right":
					direction = Button.RIGHT;
					break;
				
				default: 
					System.out.println("Command not recognized.");
					break;
				}
				
				if (direction != null) {
					roku.sendDirection(direction);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
