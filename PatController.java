package ass;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatController {
	@FXML private TextField patID; //get nodes from fxml file according to id
    @FXML private TextField patName; 
    @FXML private TextField patDisease; 
    @FXML private ComboBox<String> patSex; 
    @FXML private ComboBox<String> patAdmitStatus; 
    @FXML private TextField patAge;
    @FXML private Button addPatient; 
    @FXML private TextArea existingPatient; 
	@FXML private Label systime;

	@FXML
	private void initialize() {
		SysTime.showTime(systime);
		
		// Add options to the ComboBox
		patSex.getItems().addAll("Male","Female");
		patAdmitStatus.getItems().addAll("Admitted","Discharge","Transferring");
		
		// Populate the existing patient list
		populateExistingPatients();
	}
	
	private void populateExistingPatients() {           //Populate the TextArea with data from the HospitalManagement class
        Patient[] patients = HospitalManagement.getPatients();  //method from main class
        StringBuilder sb = new StringBuilder();

        // Loop through the Patients array
        for (Patient pat : patients) {
            // Check for null entries in the array
            if (pat != null) {
                // Use the showPatientInfo() method from the Patient class
                sb.append(pat.showPatientInfo()).append("\n");
            }
        }
        
        // Set string to the TextArea
        existingPatient.setText(sb.toString());
    }
	
	@FXML  //add new patient entry
    private void addNewPatient(ActionEvent event) {
        // get values from all fields
        String id = patID.getText();
        String name = patName.getText();
        String disease = patDisease.getText();
        String sex = patSex.getValue();
        String admitStatus = patAdmitStatus.getValue();
        String age = patAge.getText();
        
        if (validation.isEmpty(patID, "Patient ID")) return;
        if (validation.isEmpty(patName, "Name") || !validation.isAlphabetic(patName, "Name")) return;
        if (validation.isEmpty(patDisease, "Disease") || !validation.isAlphabetic(patDisease, "Disease")) return;
        if (validation.isEmpty(patSex, "Sex")) return;
        if (validation.isEmpty(patAdmitStatus, "Admit Status")) return;
        if (validation.isEmpty(patAge, "Age") || !validation.isNumeric(patAge, "Age")) return;
        clearFields();
        
        Patient newPat = new Patient();
        newPat.newPatient(id, name, disease, sex, admitStatus, Integer.parseInt(age));
        if(HospitalManagement.addPatient(newPat)) {
        	validation.addSuccessAlert("patient successfully added.");
        }
        else
        	validation.addFailAlert("You have reached the maximum number of patients added.");
        
        populateExistingPatients();
        clearFields();
    }
    
    private void clearFields() {
        patID.clear();
        patName.clear();
        patDisease.clear();
        patSex.getSelectionModel().clearSelection();
        patAdmitStatus.getSelectionModel().clearSelection();
        patAge.clear();
    }
    
    //back to main menu
    public void menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	
}
