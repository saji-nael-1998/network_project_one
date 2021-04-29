package network_1;

public class SmartPhone {
	private String model;
	private int price;

	public SmartPhone(String model, int price) {
		super();
		this.model = model;
		this.price = price;
		
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return model + " " + price + "\n";
	}

}
