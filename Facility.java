package ass;

public class Facility {
	private String facility;
	
	
	public void newFacility(String facility) {
		this.facility=facility;
	}
	
	public void showFacilityInfo() {
		System.out.printf("[%s]", facility);
	}
	
	public void changeFacilityInfo(String value) {
		facility=value;
	}
	
}
