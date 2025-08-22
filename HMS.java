package ass;

public class HMS {
	
	public static void main(String args[]) {
		Doctor[] doctorList = new Doctor[25];
		Patient[] patientList = new Patient[100];
		Lab[] labList = new Lab[20];
		Facility[] facilityList = new Facility[20];
		Medicine[] medicineList = new Medicine[100];
		Staff[] staffList = new Staff[100];
		
	    doctorList[0] = new Doctor(); 
	    doctorList[0].newDoctor("604", "Cheng Chu Yang", "Cardiologist", "9-5", "MD", 101);
	    
	    doctorList[1] = new Doctor(); 
	    doctorList[1].newDoctor("D002", "Dr. Bob", "Surgery", "10-6", "MS", 202);
	    
	    doctorList[2] = new Doctor(); 
	    doctorList[2].newDoctor("315", "Liew Zheng Jack", "Neurologist", "8-4", "PhD", 331);
	}
	
}
