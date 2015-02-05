package introsde.storage.endpoint;
import java.net.InetAddress;
import java.net.UnknownHostException;

import introsde.storage.ws.PeopleImpl;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
	public static String SERVER_URL = "http://localhost";
	public static String PORT = "6903";
	public static String BASE_URL = "/ws/lfcoach";
	
	public static String getEndpointURL() throws UnknownHostException {
//		
//		String protocol = "http://";
//		String port = ":443/";
//		String hostname = InetAddress.getLocalHost().getHostAddress();
//		if (hostname.equals("127.0.0.1")) {
//			hostname = "localhost";
//		}
//		
//		return protocol+hostname+":"+PORT+BASE_URL;
		
		return SERVER_URL+":"+PORT+BASE_URL;
	}
 
	public static void main(String[] args) throws UnknownHostException {
		String endpointUrl = getEndpointURL();
		System.out.println("Starting People Service...");
		System.out.println("--> Published at = "+endpointUrl);
		Endpoint.publish(endpointUrl, new PeopleImpl());
    }
}

