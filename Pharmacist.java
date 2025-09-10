package ass;

public class Pharmacist extends Staff {
	private String licence;

	public void newStaff(String id, String name, String designation, String sex, int salary, String licence) {
		super.newStaff(id, name, designation, sex, salary);
		this.licence = licence;
	}

	@Override
	public String showStaffInfo() {
		String string1 = super.showStaffInfo();
		return String.format(string1, " [%s]", licence);
	}
}
