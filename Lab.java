package ass;

public class Lab {
	private String lab;
	private int cost;
	
	public void newLab(String lab, int cost) {
		this.lab = lab;
		this.cost = cost;
	}
	
	public String labList() {
		return String.format("[%s] [%d]", lab,cost);
	}
}
