package ass;

public class Patient {
	private String id, name, disease ,sex,admitStatus;
	private int age;
	
	public void newPatient(String id, String name, String disease, String sex, String admitStatus, int age) {
		this.id = id;
		this.name = name;
		this.disease = disease;
		this.sex = sex;
		this.admitStatus = admitStatus;
		this.age = age;
	}

	public void showPatientInfo() {
		System.out.printf("[%s] [%s] [%s] [%s] [%s] [%d]", id, name, disease, sex, admitStatus, age);
	}
	
	public void changePatientInfo(String selection, String value) {
		switch (selection) {
		case "name":
			this.name = value;
			break;
		case "disease":
			this.disease = value;
			break;
		case "sex":
			this.sex = value;
			break;
		case "age":
			this.age = Integer.parseInt(value);
			break;
		}
	}

	public void updateStatus(String status) {
		this.admitStatus = status;
	}
	
	public void findPatientById(String id) {
		if(this.id == id) {showPatientInfo();}
	}
}
