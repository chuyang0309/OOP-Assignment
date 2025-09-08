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

	public String showPatientInfo() {
		return String.format("[%s] [%s] [%s] [%s] [%s] [%d]", id, name, disease, sex, admitStatus, age);
	}
	
	
}
