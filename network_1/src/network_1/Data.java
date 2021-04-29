package network_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Data {
	public SmartPhone[] readFile(String file) throws FileNotFoundException {
		SmartPhone[] smartPhone = new SmartPhone[5];
		Scanner scanenr = new Scanner(new File("data.txt"));
		int counter = 0;
		while (scanenr.hasNextLine()) {
			String line = scanenr.nextLine();
			String splitLine[] = line.split(" ");
			smartPhone[counter] = new SmartPhone(splitLine[0], Integer.parseInt(splitLine[1]));
			counter++;
		}
		return smartPhone;
	}

	public SmartPhone[] sortName(SmartPhone[] smartPhone) {
		
		for (int x = 0; x < smartPhone.length; x++) {
			for (int y = 0; y < smartPhone.length; y++) {
				if (smartPhone[x].getModel().compareTo(smartPhone[y].getModel()) < 1) {
					SmartPhone temp = new SmartPhone(smartPhone[x].getModel(), smartPhone[x].getPrice());
					// swapping
					smartPhone[x].setModel(smartPhone[y].getModel());
					smartPhone[x].setPrice(smartPhone[y].getPrice());
					// swapping
					smartPhone[y].setModel(temp.getModel());
					smartPhone[y].setPrice(temp.getPrice());
				}
			}

		}
		return smartPhone;
	}

	public SmartPhone[] sortPrice(SmartPhone[] smartPhone) {
		for (int x = 0; x < smartPhone.length; x++) {
			for (int y = 0; y < smartPhone.length; y++) {
				if (smartPhone[x].getPrice() < smartPhone[y].getPrice()) {
					SmartPhone temp = new SmartPhone(smartPhone[x].getModel(), smartPhone[x].getPrice());
					// swapping
					smartPhone[x].setModel(smartPhone[y].getModel());
					smartPhone[x].setPrice(smartPhone[y].getPrice());
					// swapping
					smartPhone[y].setModel(temp.getModel());
					smartPhone[y].setPrice(temp.getPrice());
				}
			}

		}
		return smartPhone;

	}

}
