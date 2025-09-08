package ass;

public class Nurse extends Staff{
	private String shift;
	
	public void newStaff(String id, String name, String designation, String sex, int salary, String shift) {
		super.newStaff(id, name, designation, sex, salary);
		this.shift=shift;
	}
	
	@Override
	public String showStaffInfo() {
		String string1 = super.showStaffInfo();
		return String.format(string1," [%s]", shift);
	}
	
}
