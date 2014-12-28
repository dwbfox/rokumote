package rokumote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import rokumote.utils.Endpoints;
import rokumote.utils.Button;



/**
 * The main Rokumote class 
 * that interfaces with Roku's
 * HTTP API.
 * 
 * @author birud
 *
 */
public class Remote {

	public class Channel {
		
		public final String name;
		public final String type;
		public final String version;		
		public final int id;

		public Channel(String name, int id, String version, String type) {
			this.name = name;
			this.type = type;
			this.version = version;
			this.id = id;
		}
	}
	
	public String host;
	public int port; 
	private HashMap<String, Channel> _appList = new HashMap<String, Channel>();
	private final Logger _logger = Logger.getLogger(this.getClass().getCanonicalName());
	private final String _baseUrl;

	
	public Remote(String ip, int portNum) {
		setHost(ip);
		setPort(portNum);
		_logger.log(Level.INFO, "New Roku instance:  " + this.getHost() + ":" + this.getPort());
		this._baseUrl = String.format("http://%s:%s/", host, Integer.toString(port));
	}
	
	/**
	 * Launches the specified channel on the Roku
	 * device.
	 * @param 
	 * 		channelID the integer ID of the channel to launch.
	 * 
	 * @return 
	 * 		true of the request ends successfully (HTTP 202/200) 
	 * 		false on all other errors.
	 */
	public boolean launchChannel(int channelID) {
		String requestUrl = _baseUrl  + Endpoints.CHANNEL.getValue() + channelID;
		return _makeRequest(requestUrl, "POST");
	}
	
	/**
	 * Sends the simulated remote button press to the 
	 * selected Roku device.
	 * 
	 * @param direction 
	 * 			one of the Button constants
	 * @see Button
	 * @return 
	 * 			true of the request ends successfully (HTTP 202/200) 
	 * 			false on all other errors.
	 */
	public boolean sendDirection(Button direction) {
		String requestUrl = _baseUrl  + Endpoints.KEYPRESS.getValue() + direction.value();
		_logger.log(Level.INFO, "Sending keypress: " + requestUrl);	 
		return _makeRequest(requestUrl, "POST");
	}
	
	/**
	 * Sends out a key combination to enable
	 * the developer mode screen on the 
	 * selected Roku device.
	 * 
	 * @return void
	 */
	public void enableDevMode() {
		sendDirection(Button.HOME);
		sendDirection(Button.HOME);
		sendDirection(Button.HOME);
		sendDirection(Button.UP);
		sendDirection(Button.UP);
		sendDirection(Button.RIGHT);
		sendDirection(Button.LEFT);
		sendDirection(Button.RIGHT);
		sendDirection(Button.LEFT);
		sendDirection(Button.RIGHT);
	}
	
	/**
	 * Returns a hash map of all channels 
	 * on the current Roku device
	 * 
	 * @return HashMap<String, Channel>
	 * 			 where the key is the string name of the channel
	 *  		 and the Channel is an object containing the id,
	 * 			 name, version and type of the channel.
	 */
	public HashMap<String, Channel> getAppList() {
		String requestUrl = _baseUrl + Endpoints.APPLIST.getValue();
		String data = _makeRequestData(requestUrl, "GET");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
			InputSource source = new InputSource(new StringReader(data));
			Document xmlDoc = docBuilder.parse(source);
			
			NodeList appElements = xmlDoc.getElementsByTagName("app");
		
			for (int i = 0; i < appElements.getLength(); ++i) {
				Node appElement = appElements.item(i);
				String appName = appElement.getTextContent();
				int appID = Integer.parseInt(appElement.getAttributes().getNamedItem("id").getNodeValue());
				String appType = appElement.getAttributes().getNamedItem("type").getNodeValue();
				String appVersion = appElement.getAttributes().getNamedItem("version").getNodeValue();
				_appList.put(appName, new Channel(appName, appID, appVersion, appType));
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			_logger.log(Level.SEVERE, "Failed to parse XML: " + e.getMessage());
			return null;
		}
		return _appList;
	}
	
	
	public int getPort() {
		return port;
	}

	
	public String getHost() {
		return host;
	}
	
	
	public void setHost(String host) {
		this.host = host;
	}
	
	
	public void setPort(int port) {
		this.port = port;
	}
	
	
	/**
	 * Get st
	 * @param requestUrl
	 * @param method
	 * @return
	 */
	private String _makeRequestData(String requestUrl, String method) {
		try {
			String line;
			StringBuffer data = new StringBuffer();
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod(method);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
			reader.close();
			return data.toString();
							
		} catch (Exception e) {
			_logger.log(Level.WARNING, "An error occured when making request: " + e.getMessage());
			return null;
		}	
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
	private boolean _makeRequest(String requestUrl, String method) {
		try {
			int responseCode;
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod(method);
			responseCode = conn.getResponseCode();
			_logger.log(Level.INFO, "HTTP Response code: " + responseCode);
			
			return true;
			
		} catch (Exception e) {
			_logger.log(Level.WARNING, "An error occured when making request: " + e.getMessage());
			return false;
		}
	}
	
}
