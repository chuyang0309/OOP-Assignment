package ass;

public class AdmissionStaff extends Staff{
	private String department;

	public void newStaff(String id, String name, String designation, String sex, int salary, String department) {
		super.newStaff(id, name, designation, sex, salary);
		this.department = department;
	}

	@Override
	public String showStaffInfo() {
		String string1 = super.showStaffInfo();
		return String.format(string1, " [%s]", department);
	}
}
