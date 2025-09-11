package ass;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HospitalManagement extends Application {
	private static Doctor[] doctors = new Doctor[25];
	private static Patient[] patients = new Patient[100];
	private static Medicine[] medicines = new Medicine[100];
	private static Lab[] labs = new Lab[20];
	private static Facility[] facilities = new Facility[20];
	private static Staff[] staffs = new Staff[100];
	private static int docCount = 0, patCount = 0, medCount = 0, labCount = 0, facCount = 0, staffCount = 0;

	// attribute for welcome page animation
	private int dotCount = 0;
    private Stage mainStage;
    private Timeline loadingTimeline;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainStage = primaryStage; 
        showWelcomePage(); 
    }

    private void showWelcomePage() throws Exception {
        Stage welcomeStage = new Stage();
        Parent root = FXMLLoader.load(HospitalManagement.class.getResource("welcomePage.fxml"));
        Scene welcomeScene = new Scene(root);
        welcomeStage.setScene(welcomeScene);
        welcomeStage.initStyle(StageStyle.UNDECORATED);
        welcomeStage.show();

        // load system date and time
        Label systime = (Label) root.lookup("#systime");
        SysTime.showTime(systime);
        
        // show loading animation
        Label loadingLabel = (Label) root.lookup("#loading");
        loadingTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            dotCount = (dotCount + 1) % 4;
            String dots = "";
            for (int i = 0; i < dotCount; i++) {
                dots += ".";
            }
            loadingLabel.setText("Loading" + dots);
        }));
        loadingTimeline.setCycleCount(Animation.INDEFINITE);
        loadingTimeline.play();

        //after delay proceed to main menu
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            welcomeStage.hide();
            showMenu(); 
        });
        delay.play();
    }

    private void showMenu() {
        if (loadingTimeline != null) {
            loadingTimeline.stop();
        }
        try {
            Parent menuRoot = FXMLLoader.load(HospitalManagement.class.getResource("menu.fxml"));
            Scene menu = new Scene(menuRoot);
            mainStage.setScene(menu);
            mainStage.setTitle("Hospital Management System");
            mainStage.show();
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
		
		// Create Patient objects
		patients[0] = new Patient();
		patients[1] = new Patient();
		patients[2] = new Patient();
		
		// Initialize each Patient object with sample data using the newPatient method
		patients[0].newPatient("P101", "John Smith", "Appendicitis", "Male", "Admitted", 25);
		patients[1].newPatient("P102", "Jane Doe", "Pneumonia", "Female", "Admitted", 45);
		patients[2].newPatient("P103", "Peter Jones", "Fractured Arm", "Male", "Discharged", 19);
		
		// Increment the patient counter by the number of new patients added
		    patCount += 3;
		    
		 // Create Facility objects
		facilities[0] = new Facility();
		facilities[1] = new Facility();
		facilities[2] = new Facility();
		
		// Initialize each Facility object with sample data using the newFacility method
		facilities[0].newFacility("Main Hospital");
		facilities[1].newFacility("Urgent Care Clinic");
		facilities[2].newFacility("Rehabilitation Center");
		
		// Increment the facility counter by the number of new facilities added
		facCount += 3;
	    
		// Create staff objects

		Nurse nurse1 = new Nurse();
		nurse1.newStaff("N731", "Sarah Johnson", "Registered Nurse", "Female", 4500, "Night Shift");
		staffs[0] = nurse1;
		
		Pharmacist pharmacist1 = new Pharmacist();
		pharmacist1.newStaff("P512", "Emily Chen", "Clinical Pharmacist", "Female", 6200, "LPH-12345");
		staffs[1] = pharmacist1;
		
		Security security1 = new Security();
		security1.newStaff("S404", "Mike Davis", "Security Guard", "Male", 2800, 5);
		staffs[2] = security1;
		
		staffCount += 3;
	}
    
    public static Doctor[] getDoctors() {
        return doctors;
    }
    public static Patient[] getPatients() {
        return patients;
    }
    public static Lab[] getLabs() {
        return labs;
    }
    public static Medicine[] getMedicines() {
        return medicines;
    }
   
    public static Facility[] getFacilities() {
        return facilities;
    }
    public static Staff[] getStaffs() {
        return staffs;
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
    
    public static boolean addPatient(Patient newPat) {  
    	if(patCount<100) {   //check for array size limit
            patients[patCount]=newPat;
            patCount++;
            return true;
    	}
    	else
    		return false;
    }
    
    public static boolean addFacility(Facility newFacility) {  
    	if(facCount<20) {   //check for array size limit
            facilities[facCount]=newFacility;
            facCount++;
            return true;
    	}
    	else
    		return false;
    }
    
    public static boolean addStaff(Staff newStaff) {  
    	if(staffCount<100) {   //check for array size limit
            staffs[staffCount]=newStaff;
            staffCount++;
            return true;
    	}
    	else
    		return false;
    }
    

}