package network_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WebPages {

	public String readPageHTMLFile(String fileName) throws Exception {
		String text = "";
		EncoderImage encode = new EncoderImage();
		text = encode.readFile(fileName);
		return text;
	}

	public String readPhonePage(String sort) throws FileNotFoundException {
		String text = "";
		Data data = new Data();
		SmartPhone[] smartPhone = data.readFile("data.txt");
		if (sort.equals("price")) {
			data.sortPrice(smartPhone);
			text = sendPhonePage(smartPhone);
		} else if (sort.equals("name")) {
			data.sortName(smartPhone);
			text = sendPhonePage(smartPhone);
		}

		return text;
	}

	public String sendPhonePage(SmartPhone[] smartPhone) throws FileNotFoundException {
		String text = "";
		Scanner scanenr = new Scanner(new File("smartphone.html"));
		while (scanenr.hasNextLine()) {
			String line = scanenr.nextLine();
			line = line.trim();
			if (line.equals("display data here")) {

				text += Arrays.toString(smartPhone) + "\n";

			} else {
				text += line + "\n";
			}
		}

		return text;
	}

	public String sendErrorPage(String ip, int port) throws FileNotFoundException {
		String text = "";
		Scanner scanenr = new Scanner(new File("error.html"));
		while (scanenr.hasNextLine()) {
			String line = scanenr.nextLine();
			line = line.trim();
			if (line.equals("print here")) {

				String data = "IP = " + ip + " Port = " + port + "\n";
				text += data + "\n";
			} else {
				text += line + "\n";
			}
		}

		return text;
	}

}
