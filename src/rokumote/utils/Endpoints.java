package rokumote.utils;

public enum Endpoints {
	
	KEYPRESS("keypress/"),
	KEYDOWN("keydown/"),
	KEUP("keyup/"),
	APPLIST("query/apps"),
	CHANNEL("launch/");
	
	private String value;
	
	Endpoints(String endpoint) {
		this.value = endpoint;
	}
	
	public String getValue() {
		return this.value;
	}
}

