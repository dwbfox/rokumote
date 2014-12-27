package util;

public enum Endpoints {
	
	KEYPRESS("keypress/"),
	KEYDOWN("keydown/"),
	KEUP("keyup/");
	
	private String value;
	
	Endpoints(String endpoint) {
		this.value = endpoint;
	}
	
	public String getValue() {
		return this.value;
	}
}

