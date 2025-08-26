package ass;
public class Medicine {
	private int cost, count;
	private String name, manufacturer, expiryDate;
	
	public void newMedicine(String name, String manufacturer, String expiryDate, int cost, int count) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.expiryDate = expiryDate;
		this.cost = cost;
		this.count = count;
	}
	
	public void showMedicineInfo() {
		System.out.printf("[%s] [%s] [%s] [%d] [%d]", name, manufacturer, expiryDate, cost, count);
	}
	
	public void changeMedicineInfo(String selection, String value) {
		switch (selection) {
		case "name":
			this.name = value;
			break;
		case "manufacturer":
			this.manufacturer = value;
			break;
		case "expiry date":
			this.expiryDate = value;
			break;
		case "cost":
			this.cost = Integer.parseInt(value);
			break;
		case "count":
			this.count = Integer.parseInt(value);
			break;
		}
	}
}
