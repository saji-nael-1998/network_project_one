package network_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Scanner;

public class EncoderImage {
	public String readFile(String file) throws FileNotFoundException {
		String text = "";
		Scanner scanenr = new Scanner(new File(file));
		while (scanenr.hasNextLine()) {
			String line = scanenr.nextLine();
			if (line.contains("<img")) {

				String image = encoder(getName(line));

				text += image + "\n";

			} else {
				text += line + "\n";
			}

		}

		return text;
	}

	private String getName(String line) {
		int startSingleQuote = line.indexOf("'");
		int endSingleQuote = line.lastIndexOf("'");
		return line.substring(startSingleQuote + 1, endSingleQuote);
	}

	public String encoder(String imagePath) throws FileNotFoundException {

		String base64Image = "";
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		imagePath = imagePath.replace(".", "_");
		String split[] = imagePath.split("_");
		String type = split[1];
		String convertedImageElement = "";
		if (type.equals("jpg")) {

			convertedImageElement = "<img src='data:image/jpeg;base64," + base64Image + "'>";

		} else if (type.equals("png")) {

			convertedImageElement = "<img src='data:image/png;base64," + base64Image + "'>";

		}
		return convertedImageElement;
	}
}
