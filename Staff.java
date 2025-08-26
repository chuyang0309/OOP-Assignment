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

	public void showStaffInfo() {
		System.out.printf("[%s] [%s] [%s] [%s] [%d]", id, name, designation, sex, salary);
	}

	public void changeStaffInfo(String selection, String value) {
		switch (selection) {
		case "name":
			this.name = value;
			break;
		case "desingnation":
			this.designation = value;
			break;
		case "sex":
			this.sex = value;
			break;
		case "salary":
			this.salary = Integer.parseInt(value);
			break;
		}
	}

	public void findStaffById(String id) {
		if(this.id == id) {showStaffInfo();}
	}
}
