package rokumote.utils;

public enum Button { 

	DOWN("down"),
	UP("up"),
	LEFT("left"),
	RIGHT("right"),
	REPLAY("InstantReplay"),
	INFO("Info"),
	FORWARD("Fwd"),
	PLAY("Play"),
	REWIND("Rev"),
	BACK("Back"),
	HOME("Home"),
	ENTER("Enter"),
	SELECT("Select"),
	BACKSPACE("Backspace"),
	SEARCH("Search");
	
	private String value; 
	
	Button(String val) {
		this.value = val;
	}	
	public String value() {
		return this.value;
	}
	

}

