package ass;

public class Security extends Staff {
	private int yearsExp;
	
	public void newStaff(String id, String name, String designation, String sex, int salary, int yearsExp) {
		super.newStaff(id, name, designation, sex, salary);
		this.yearsExp =  yearsExp;
	}
	
	@Override
	public String showStaffInfo() {
		String string1 = super.showStaffInfo();
		return String.format(string1," [%d]", yearsExp);
	}
}
