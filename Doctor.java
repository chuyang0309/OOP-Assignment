package ass;
public class Doctor {
	private String id, name, specialist, workTime, qualification;
	private int room;
	
	public void newDoctor(String id, String name, String specialist, String workTime, String qualification, int room) {
		this.id = id;
		this.name = name;
		this.specialist = specialist;
		this.workTime = workTime;
		this.qualification = qualification;
		this.room = room;
	}
	
	public String showDoctorInfo() {
		return String.format("[%s] [%s] [%s] [%s] [%d]", id, name, specialist, workTime, room);
	}
}
