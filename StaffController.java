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
	@FXML
	private TextField staffID; // get nodes from fxml file according to id
	@FXML
	private TextField staffName;
	@FXML
	private ComboBox<String> staffDesignation;
	@FXML
	private ComboBox<String> staffSex;
	@FXML
	private TextField staffSalary;
	@FXML
	private Button addStaff;
	@FXML
	private TextArea existingStaff;
	@FXML
	private Label systime;
	@FXML
	private TextField staffShift; // for nurse
	@FXML
	private Label shiftLabel; // for nurse
	@FXML
	private TextField staffYearsExp; // for security
	@FXML
	private Label yearsExpLabel; // for security
	@FXML
	private TextField staffLicense; // for pharmacist
	@FXML
	private Label licenseLabel; // for pharmacist
	@FXML
	private TextField staffDepartment; // for admission
	@FXML
	private Label deptLabel; // for admission

	@FXML
	private void initialize() {
		SysTime.showTime(systime);

		// Add options to the ComboBox
		staffSex.getItems().addAll("Male", "Female");
		staffDesignation.getItems().addAll("Nurse", "Pharmacist", "Security", "Administrator");

		// Populate the existing staff list
		populateExistingStaffs();

		// Toggle the special fields from different extended class (nurse - shift,
		// security - YearsExp)
		toggleSpecialFields(null);
		staffDesignation.valueProperty().addListener((obs, oldVal, newVal) -> {
			toggleSpecialFields(newVal);
		});

	}

	private void populateExistingStaffs() { // Populate the TextArea with data from the HospitalManagement class
		Staff[] Staffs = HospitalManagement.getStaffs(); // method from main class
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

	// Toggle the special fields from different extended class (nurse - shift, security - YearsExp)
	private void toggleSpecialFields(String designation) {
		boolean isNurse = "Nurse".equals(designation);
		boolean isSecurity = "Security".equals(designation);
		boolean isPharmacist = "Pharmacist".equals(designation);
		boolean isAdmission = "Administrator".equals(designation);

		staffShift.setVisible(isNurse);
		shiftLabel.setVisible(isNurse);
		
		staffYearsExp.setVisible(isSecurity);
		yearsExpLabel.setVisible(isSecurity);
		
		staffLicense.setVisible(isPharmacist);
		licenseLabel.setVisible(isPharmacist);
		
		staffDepartment.setVisible(isAdmission);
		deptLabel.setVisible(isAdmission);
		
	

		clearSpecialFields();
	}

	private void clearSpecialFields() {
		staffShift.clear();
		staffYearsExp.clear();
		staffLicense.clear();
		staffDepartment.clear();
	}

	@FXML // add new Staff entry
	private void addNewStaff(ActionEvent event) {
		// get values from all fields
		String id = staffID.getText();
		String name = staffName.getText();
		String designation = staffDesignation.getValue();
		String sex = staffSex.getValue();
		String salary = staffSalary.getText();

		if (validation.isEmpty(staffID, "Staff ID"))
			return;
		if (!validation.isAlphabetic(staffName, "Name"))
			return;
		if (validation.isEmpty(staffDesignation, "Designation"))
			return;
		if (validation.isEmpty(staffSex, "Sex"))
			return;
		if (!validation.isPositiveInteger(staffSalary, "Salary"))
			return;

		Staff[] staffs = HospitalManagement.getStaffs();
		for (Staff staff : staffs) {
			// check for duplicate staff
			if (staff != null && staff.getId().equalsIgnoreCase(id)) {
				validation.addFailAlert("Staff with ID '" + id + "' already exists. Please use a unique ID.");
				return; // return if duplicate is found
			}
		}

		//add staff based on its extend class
		Staff newStaff;
		switch (designation) {
		case "Nurse":
			if (validation.isEmpty(staffShift, "Shift"))
				return;
			Nurse nurse = new Nurse();
			nurse.newStaff(id, name, designation, sex, Integer.parseInt(salary), staffShift.getText());
			newStaff = nurse;
			break;

		case "Security":
			if (validation.isEmpty(staffYearsExp, "Years of Experience")
					|| !validation.isPositiveInteger(staffYearsExp, "Years of Experience"))
				return;
			Security security = new Security();
			security.newStaff(id, name, designation, sex, Integer.parseInt(salary),
					Integer.parseInt(staffYearsExp.getText()));
			newStaff = security;
			break;

		case "Pharmacist":
			if (validation.isEmpty(staffLicense, "License Number"))
				return;
			Pharmacist pharmacist = new Pharmacist();
			pharmacist.newStaff(id, name, designation, sex, Integer.parseInt(salary), staffLicense.getText());
			newStaff = pharmacist;
			break;

		case "Administrator":
			if (validation.isEmpty(staffDepartment, "Department")
					|| !validation.isAlphabetic(staffDepartment, "Department"))
				return;
			AdmissionStaff admission = new AdmissionStaff();
			admission.newStaff(id, name, designation, sex, Integer.parseInt(salary), staffDepartment.getText());
			newStaff = admission;
			break;

		default:
			newStaff = new Staff();
			newStaff.newStaff(id, name, designation, sex, Integer.parseInt(salary));
		}
		
		clearFields();
		
		
		if (HospitalManagement.addStaff(newStaff)) {
			validation.addSuccessAlert("Staff successfully added.");
			populateExistingStaffs();
			NavigationPrompt.showPostAddPrompt(event);
			clearFields();
		} else
			validation.addFailAlert("You have reached the maximum number of Staffs added.");

	}

	private void clearFields() {
		staffID.clear();
		staffName.clear();
		staffDesignation.getSelectionModel().clearSelection();
		staffSex.getSelectionModel().clearSelection();
		staffSalary.clear();
		
		clearSpecialFields();
	}

	// back to main menu
	public void menu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
