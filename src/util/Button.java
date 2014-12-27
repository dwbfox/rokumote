package util;

public enum Button { 

	DOWN("down"),
	UP("up"),
	LEFT("left"),
	RIGHT("right"),
	REPLAY("replay"),
	INFO("info"),
	FORWARD("fwd"),
	PLAY("play"),
	REWIND("rwd");
	
	private String value; 
	
	Button(String val) {
		this.value = val;
	}	
	public String value() {
		return this.value;
	}
	

}

