package network_1;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Driver {

	public static void main(String[] args) {
		int portNum = 9038;

		createConnection(portNum);

	}

	public static void createConnection(int portNum) {
		try {
			System.out.println("start..........");
			final ServerSocket server = new ServerSocket(portNum);
			System.out.println("start connection.............");
			while (true) {
				final Socket socket = server.accept();
				// sending response
				OutputStream outPutStream = socket.getOutputStream();
				homePageRequest(outPutStream);
				outPutStream.close();
				socket.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String readRequest(Socket socket) {
		String request = "";
		try {

		} catch (Exception e) {
			System.out.println(e);
		}
		return request;
	}

	public static void homePageRequest(OutputStream outPutStream) {
		try {
			PrintWriter printWriter = new PrintWriter(outPutStream);

			String text = readHTMLFile();
			printWriter.println("HTTP/1.0 200 OK");
			printWriter.println("Content-Length: " + text.length());
			printWriter.println("Content-Type: text/html");
			printWriter.println("");
			printWriter.println(text);
			
			printWriter.flush();
			System.out.println("sent......................");
			File f = new File("pics/photo1.jpg");
			BufferedImage image = ImageIO.read(f);
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", b);
			outPutStream.write(b.toByteArray());
			outPutStream.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String readHTMLFile() throws Exception {
		String text = "";
		File file = new File("files\\index.html");
		Scanner reader = new Scanner(file);
		while (reader.hasNextLine()) {
			text += reader.nextLine() + "\r\n";
		}
		return text;
	}

}
