package ass;

public class Staff {
	private String id, name, designation, sex;
	private int salary;

	public void newStaff(String id, String name, String designation, String sex, int salary) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.sex = sex;
		this.salary = salary;
	}

	public String showStaffInfo() {
		return String.format("[%s] [%s] [%s] [%s] [%d]", id, name, designation, sex, salary);
	}

}
