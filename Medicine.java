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
	
	public String showMedicineInfo() {
		return String.format("[%s] [%s] [%s] [%d] [%d]", name, manufacturer, expiryDate, cost, count);
	}
}
