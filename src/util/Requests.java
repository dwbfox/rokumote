/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.Button;
import util.Endpoints;

/**
 * Handles the lower level HTTP
 * requests with the Roku box.
 * 
 * @author birud
 *
 */

public class Requests {
	
	public String host;
	public int port; 
	
	private String _baseUrl = "";

	private static final Logger logger = Logger.getLogger("Test");
	
	public Requests(String url, int portNum) {
		host = url;
		port = portNum;
		this._baseUrl = String.format("http://%s:%s/", host, Integer.toString(port));
	}
	
	
	/**
	 * Sends a simulated keypress to the Roku
	 * device.	
	 * @param keyDirection
	 * @return
	 */
	public boolean sendKey(Button keyDirection) {
		String requestUrl = _baseUrl  + Endpoints.KEYPRESS.getValue() + keyDirection.value();
		logger.log(Level.INFO, "SendKey: " + requestUrl);
		
		String response = _makeRequest(requestUrl, "POST", false);
		if (response == null) return false;
		
		return false;
	}
	
	
	/**
	 * A generic HTTP request class to make the low leve
	 * requests to the Roku device.
	 * 
	 * @param requestUrl the url to send the request
	 * @param method the HTTP method (e.g. GET, POST, HEAD, etc);
	 * @param returnData
	 * @return
	 */
	private String _makeRequest(String requestUrl, String method, boolean returnData) {
		try {
			String line;
			int responseCode;
			StringBuffer data = new StringBuffer();
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod(method);
			conn.setRequestProperty("Useragent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
			responseCode = conn.getResponseCode();
			
			logger.log(Level.INFO, "HTTP Response code: " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while ((line = in.readLine()) != null) {
				data.append(line);
			}
			return data.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "An error occured when making request: " + e.getMessage());
			return null;
		}
	}
	
	
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
