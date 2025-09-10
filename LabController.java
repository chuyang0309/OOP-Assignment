package ass;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LabController {

	@FXML
	private TextField lab; // get nodes from fxml file according to id
	@FXML
	private TextField labCost;
	@FXML
	private TextArea existingLabs;
	@FXML
	private Label systime;

	@FXML
	private void initialize() {
		// Populate the existing lab list
		populateExistingLabs();
		SysTime.showTime(systime);
	}

	private void populateExistingLabs() { // Populate the TextArea with data from the HospitalManagement class
		Lab[] labs = HospitalManagement.getLabs(); // method from main class
		StringBuilder sb = new StringBuilder();

		// Loop through the lab array
		for (Lab lab : labs) {
			// Check for null entries in the array
			if (lab != null) {
				sb.append(lab.labList()).append("\n");
			}
		}

		// Set string to the TextArea
		existingLabs.setText(sb.toString());
	}

	@FXML // add new lab entry
	private void addNewLab(ActionEvent event) {
		// get values from all fields
		String lText = lab.getText();
		String lCost = labCost.getText();

		if (!validation.isAlphabetic(lab, "Lab"))
			return;
		if (!validation.isPositiveInteger(labCost, "Lab Cost"))
			return;
		clearFields();

        Lab[] labs = HospitalManagement.getLabs(); 
        for (Lab lab: labs) {
            // check for duplicate lab
            if (lab != null && lab.getLab().equalsIgnoreCase(lText)) {
                validation.addFailAlert("Lab with name '" + lText + "' already exists. Please add a unique lab.");
                return; // return if duplicate is found
            }
        }
		
		Lab nLab = new Lab();
		nLab.newLab(lText, Integer.parseInt(lCost));
		if (HospitalManagement.addLab(nLab)) {
			validation.addSuccessAlert("Lab successfully added.");
			populateExistingLabs();
        	NavigationPrompt.showPostAddPrompt(event);
            clearFields();
		} else
			validation.addFailAlert("You have reached the maximum number of labs added.");

		populateExistingLabs();
		clearFields();
	}

	private void clearFields() {
		lab.clear();
		labCost.clear();

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