package ass;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HospitalManagement extends Application {

	private static Doctor[] doctors = new Doctor[25];
	private static Patient[] patients = new Patient[100];
	private static Medicine[] medicines = new Medicine[100];
	private static Lab[] labs = new Lab[20];
	private static Facility[] facilities = new Facility[20];
	private static Staff[] staff = new Staff[100];
	private static int docCount = 0, patCount = 0, medCount = 0, labCount = 0, facCount = 0, staffCount = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root =  FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene menu = new Scene(root);
			primaryStage.setScene(menu);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
    	initializeSampleData(); 
        launch(args);
    }
    
    private static void initializeSampleData() {
        // Create Doctor objects
        doctors[0] = new Doctor();
        doctors[1] = new Doctor();
        doctors[2] = new Doctor();
        
		doctors[0].newDoctor("604", "Cheng Chu Yang", "Student", "0800-1800", "Undergraduate", 1);
		doctors[1].newDoctor("514", "Yeap Yun Ting", "Student", "0800-1800", "Undergraduate", 2);
		doctors[2].newDoctor("001", "Ali", "Cardiologist", "0800-1800", "Phd", 3);
		
		docCount+=3;
		
		//Create labs
        labs[0] = new Lab();
        labs[1] = new Lab();
        labs[2] = new Lab();
        
        labs[0].newLab("X-Ray", 80);
        labs[1].newLab("Blood Test", 30);
        labs[2].newLab("Urine Test", 40);
		
		labCount+=3;
		
		//Medicine
	    medicines[0] = new Medicine();
	    medicines[1] = new Medicine();
	    medicines[2] = new Medicine();
	    
	    // Initialize medicines using newMedicine() method
	    medicines[0].newMedicine("Paracetamol", "PharmaCorp", "2025-12-31", 15, 100);
	    medicines[1].newMedicine("Amoxicillin", "MediHealth", "2024-06-30", 45, 50);
	    medicines[2].newMedicine("Ibuprofen", "QuickRelief", "2026-03-15", 25, 75);
	    
	    medCount += 3;
	}
    
    public static Doctor[] getDoctors() {
        return doctors;
    }
    public static Lab[] getLabs() {
        return labs;
    }
    public static Medicine[] getMedicines() {
        return medicines;
    }
    
    
    
    public static boolean addDoctor(Doctor newDoc) {  
    	if(docCount<25) {   //check for array size limit
            doctors[docCount]=newDoc;
            docCount++;
            return true;
    	}
    	else
    		return false;
    }
    
    public static boolean addLab(Lab newLab) {  
    	if(labCount<20) {   //check for array size limit
            labs[labCount]=newLab;
            labCount++;
            return true;
    	}
    	else
    		return false;
    }
    
    public static boolean addMedicine(Medicine newMed) {  
    	if(medCount<100) {   //check for array size limit
            medicines[medCount]=newMed;
            medCount++;
            return true;
    	}
    	else
    		return false;
    }
}