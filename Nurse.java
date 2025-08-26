package ass;

public class Nurse extends Staff{
	private String shift;
	
	public void newStaff(String id, String name, String designation, String sex, int salary, String shift) {
		super.newStaff(id, name, designation, sex, salary);
		this.shift=shift;
	}
	
	@Override
	public void showStaffInfo() {
		super.showStaffInfo();
		System.out.printf(" [%s]", shift);
	}
	
}
