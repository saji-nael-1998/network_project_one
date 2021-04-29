package network_1;

public class Driver {
	public static void main(String args[]) {
		try {
			// create a webserver
			WebServer webServer = new WebServer(9000);
			// start the webserver on port 9000
			webServer.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
