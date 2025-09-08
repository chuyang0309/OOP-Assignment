package ass;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FacController {
	@FXML private TextField facility;
    @FXML private Button addFacility; 
    @FXML private TextArea existingFacility; 
	@FXML private Label systime;

	@FXML
	private void initialize() {
		SysTime.showTime(systime);
		
		// Populate the existing Facility list
		populateExistingFacilities();
	}
	
	private void populateExistingFacilities() {           //Populate the TextArea with data from the HospitalManagement class
        Facility[] Facilities = HospitalManagement.getFacilities();  //method from main class
        StringBuilder sb = new StringBuilder();

        // Loop through the Facilities array
        for (Facility Fac : Facilities) {
            // Check for null entries in the array
            if (Fac != null) {
                // Use the showFacilityInfo() method from the Facility class
                sb.append(Fac.showFacilityInfo()).append("\n");
            }
        }
        
        // Set string to the TextArea
        existingFacility.setText(sb.toString());
    }
	
	@FXML  //add new Facility entry
    private void addNewFacility(ActionEvent event) {
        // get values from all fields
        String fac = facility.getText();
        
        if (validation.isEmpty(facility, "Facility")) return;
        clearFields();
        
        Facility newFac = new Facility();
        newFac.newFacility(fac);
        if(HospitalManagement.addFacility(newFac)) {
        	validation.addSuccessAlert("Facility successfully added.");
        }
        else
        	validation.addFailAlert("You have reached the maximum number of Facilitys added.");
        
        populateExistingFacilities();
        clearFields();
    }
    
    private void clearFields() {
	    facility.clear();
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


