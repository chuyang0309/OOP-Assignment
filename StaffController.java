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

public class StaffController {
	@FXML private TextField staffID; //get nodes from fxml file according to id
    @FXML private TextField staffName; 
    @FXML private TextField staffDesignation; 
    @FXML private ComboBox<String> staffSex; 
    @FXML private TextField staffSalary;
    @FXML private Button addStaff; 
    @FXML private TextArea existingStaff; 
	@FXML private Label systime;

	@FXML
	private void initialize() {
		SysTime.showTime(systime);
		
		// Add options to the ComboBox
		staffSex.getItems().addAll("Male","Female");
		
		// Populate the existing staff list
		populateExistingStaffs();
	}
	
	private void populateExistingStaffs() {           //Populate the TextArea with data from the HospitalManagement class
        Staff[] Staffs = HospitalManagement.getStaffs();  //method from main class
        StringBuilder sb = new StringBuilder();

        // Loop through the Staffs array
        for (Staff staff : Staffs) {
            // Check for null entries in the array
            if (staff != null) {
                // Use the showStaffInfo() method from the Staff class
                sb.append(staff.showStaffInfo()).append("\n");
            }
        }
        
        // Set string to the TextArea
        existingStaff.setText(sb.toString());
    }
	@FXML  //add new Staff entry
    private void addNewStaff(ActionEvent event) {
        // get values from all fields
        String id = staffID.getText();
        String name = staffName.getText();
        String designation = staffDesignation.getText();
        String sex = staffSex.getValue();
        String salary = staffSalary.getText();
        
        if (validation.isEmpty(staffID, "Staff ID")) return;
        if (validation.isEmpty(staffName, "Name") || !validation.isAlphabetic(staffName, "Name")) return;
        if (validation.isEmpty(staffDesignation, "Designation") || !validation.isAlphabetic(staffDesignation, "Designation")) return;
        if (validation.isEmpty(staffSex, "Sex")) return;
        if (validation.isEmpty(staffSalary, "Salary") || !validation.isNumeric(staffSalary, "Salary")) return;
        clearFields();
        
        Staff[] staffs = HospitalManagement.getStaffs(); 
        for (Staff staff : staffs) {
            // check for duplicate staff
            if (staff != null && staff.getId().equalsIgnoreCase(id)) {
                validation.addFailAlert("Staff with ID '" + id + "' already exists. Please use a unique ID.");
                return; // return if duplicate is found
            }
        }
        
        Staff newstaff = new Staff();
        newstaff.newStaff(id, name, designation, sex, Integer.parseInt(salary));
        if(HospitalManagement.addStaff(newstaff)) {
        	validation.addSuccessAlert("Staff successfully added.");
        }
        else
        	validation.addFailAlert("You have reached the maximum number of Staffs added.");
        
        populateExistingStaffs();
        clearFields();
    }
    
    private void clearFields() {
        staffID.clear();
        staffName.clear();
        staffDesignation.clear();
        staffSex.getSelectionModel().clearSelection();
        staffSalary.clear();
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
