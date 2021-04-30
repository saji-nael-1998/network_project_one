package network_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class WebServer {

	private int webServerPort;
	private ServerSocket webServerSocket;

	public WebServer(int webServerPort) {
		this.webServerPort = webServerPort;

	}

	public void start() {

		System.out.println("Starting web server on port : " + webServerPort);

		try {
			// create a thread
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						// start the web server
						webServerSocket = new ServerSocket(webServerPort);
						System.out.println("Started web server on port : " + webServerPort);

						waitingForRequests();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			thread.start();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	private void waitingForRequests() {

		try {
			while (true) {
				System.out.println("Waiting for connection");
				// get the request
				Socket client = webServerSocket.accept();
				// post the response
				postResponse(client);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

	private void postResponse(Socket client) throws Exception {
		// read get request
		String requestedFile = readClientRequest(client);
		// send response
		writeClientResponse(client, requestedFile);
	}

	private String readClientRequest(Socket client) throws IOException {

		System.out.println("Request from : " + client + "\n");
		// read get request
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

		String str = ".";
		// get stats line
		String statusLine = in.readLine();
		System.out.println(statusLine);
		while (!str.equals("")) {
			str = in.readLine();
			// print request
			System.out.println(str);
		}
		String split[] = statusLine.split(" ");
		// send the name of requested file
		return split[1];
	}

	private void writeClientResponse(Socket client, String requestedFile) throws Exception {
		// check which file required
		if (requestedFile.equals("/") || requestedFile.equals("/index.html"))

		{ // set webPagees
			WebPages webPages = new WebPages();
			String homePage = webPages.readPageHTMLFile("index.html");
			PrintWriter out = new PrintWriter(client.getOutputStream());
			// Send the headers
			String response = "HTTP/1.0 200 OK" + "\n";
			response += "Connection: Keep-Alive" + "\n";
			response += "Date:" + new Date() + " GMT" + "\n";
			response += "Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.1e-fips PHP/5.4.16" + "\n";
			response += "Last-Modified:" + new Date() + " GMT" + "\n";
			response += "Content-Type: text/html" + "\n";

			out.println(response);
			// this blank line signals the end of the headers
			out.println("");
			// Send the HTML page
			out.println(homePage);
			out.flush();
			client.close();
			System.out.println(response);
			System.out.println("=============================================================");
			System.out.println(requestedFile + " Response:");
			System.out.println(response);
			System.out.println("=============================================================");
		} else if (requestedFile.equals("/SortPrice"))

		{

			PrintWriter out = new PrintWriter(client.getOutputStream());
			// Send the headers
			String response = "HTTP/1.0 200 OK" + "\n";
			response += "Connection: Keep-Alive" + "\n";
			response += "Date:" + new Date() + " GMT" + "\n";
			response += "Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.1e-fips PHP/5.4.16" + "\n";
			response += "Last-Modified:" + new Date() + " GMT" + "\n";
			response += "Content-Type: text/html" + "\n";

			out.println(response);
			// this blank line signals the end of the headers
			out.println("");
			// Send the HTML page
			WebPages webPages = new WebPages();
			String sortNamePage = webPages.readPhonePage("price");
			out.println(sortNamePage);
			out.flush();
			client.close();
			System.out.println("=============================================================");
			System.out.println(requestedFile + " Response:");
			System.out.println(response);
			System.out.println("=============================================================");
		} else if (requestedFile.equals("/SortName"))

		{

			PrintWriter out = new PrintWriter(client.getOutputStream());
			// Send the headers
			String response = "HTTP/1.0 200 OK" + "\n";
			response += "Connection: Keep-Alive" + "\n";
			response += "Date:" + new Date() + " GMT" + "\n";
			response += "Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.1e-fips PHP/5.4.16" + "\n";
			response += "Last-Modified:" + new Date() + " GMT" + "\n";
			response += "Content-Type: text/html" + "\n";

			out.println(response);
			// this blank line signals the end of the headers
			out.println("");
			// Send the HTML page
			WebPages webPages = new WebPages();
			String sortNamePage = webPages.readPhonePage("name");
			out.println(sortNamePage);
			out.flush();
			client.close();
			System.out.println("=============================================================");
			System.out.println(requestedFile + " Response:");
			System.out.println(response);
			System.out.println("=============================================================");
		} else if (requestedFile.equals("/photo2.png"))

		{	// read image
			File theFile;
			String file = "photo2.png";
			PrintStream printStream = new PrintStream(client.getOutputStream());
			theFile = new File(file);
			FileInputStream fileInputStream = new FileInputStream(theFile);
			//read as bytes
			byte[] theData = new byte[(int) theFile.length()];
			fileInputStream.read(theData);
			fileInputStream.close();
			// Send the headers
			String response = "HTTP/1.0 200 OK" + "\\r\\n";
			response += "Connection: Keep-Alive" + "\\r\\n";
			response += "Date:" + new Date() + " GMT" + "\\r\\n";
			response += "Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.1e-fips PHP/5.4.16" + "\\r\\n";
			response += "Last-Modified:" + new Date() + " GMT" + "\\r\\n";
			response += "Content-type: image/png\r\n";
			printStream.print(response);
			printStream.print("\r\n");
			// End of headers
			// Send file data
			printStream.write(theData);
			printStream.close();
			client.close();

			System.out.println("=============================================================");
			System.out.println(requestedFile + " Response:");
			System.out.println(response);
			System.out.println("=============================================================");
		} else if (requestedFile.equals("/photo1.jpg"))

		{
			// read image
			File theFile;
			String file = "photo1.jpg";
			PrintStream printStream = new PrintStream(client.getOutputStream());
			theFile = new File(file);
			FileInputStream fileInputStream = new FileInputStream(theFile);
			//read as bytes
			byte[] theData = new byte[(int) theFile.length()];
			fileInputStream.read(theData);
			fileInputStream.close();
			// Send the headers
			String response = "HTTP/1.0 200 OK" + "\r\n";
			response += "Connection: Keep-Alive" + "\r\n";
			response += "Date:" + new Date() + " GMT" + "\r\\n";
			response += "Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.1e-fips PHP/5.4.16" + "\r\n";
			response += "Last-Modified:" + new Date() + " GMT" + "\r\n";
			response += "Content-type: image/jpg\r\n";
			printStream.print(response);
			printStream.print("\r\n");
			// End of headers
			// Send file data
			printStream.write(theData);
			printStream.close();
			client.close();

			System.out.println("=============================================================");
			System.out.println(requestedFile + " Response:");
			System.out.println(response);
			System.out.println("=============================================================");
		} else {

			PrintWriter out = new PrintWriter(client.getOutputStream());
			// Send the headers
			String response = "HTTP/1.0 404" + "\n";
			response += "Connection: Keep-alive" + "\n";
			response += "Date:" + new Date() + " GMT" + "\n";
			response += "Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.1e-fips PHP/5.4.16" + "\n";
			response += "Last-Modified:" + new Date() + " GMT" + "\n";
			response += "Content-Type: text/html" + "\n";

			out.println(response);
			// this blank line signals the end of the headers
			out.println("");
			// Send the HTML page
			WebPages webPages = new WebPages();
			String errorPage = webPages.sendErrorPage(client.getLocalAddress().toString(), client.getLocalPort());
			out.println(errorPage);
			out.flush();
			client.close();
			System.out.println("=============================================================");
			System.out.println("Error Response:");
			System.out.println(response);
			System.out.println("=============================================================");
		}

	}

}