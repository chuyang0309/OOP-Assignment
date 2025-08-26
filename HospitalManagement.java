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
	private int docCount = 0, patCount = 0, medCount = 0, labCount = 0, facCount = 0, staffCount = 0;

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
	}
    
    public static Doctor[] getDoctors() {
        return doctors;
    }


	
}