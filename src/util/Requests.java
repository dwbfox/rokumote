/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Requests {

	
	public String host;
	public int port; 
	
	private String _baseUrl = "";
	private enum _ENDPOINTS { KEYDOWN, STARTAPP }
	
	private static final String KEYDOWN = "/keydown";
	private static final String KEYUP = "/keyup";
	private static final String STARTAPP = "/startapp";
	
	public Requests() {
		_baseUrl = "http://"  + host;
	}
	
	public boolean sendKey(String key) {
		endpoint = "/keydown";
		requestUrl = _baseUrl + endpoint + "/"
	}
	
	public String makeRequest()
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}


	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}


	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

}
