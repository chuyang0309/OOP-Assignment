package ass;

public class Facility {
	private String facility;
	
	
	public void newFacility(String facility) {
		this.facility=facility;
	}
	
	public String showFacilityInfo() {
		return String.format("[%s]", facility);
	}
	
	
}
