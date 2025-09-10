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

public class MedController {

	@FXML
	private TextField medName; // get nodes from fxml file according to id
	@FXML
	private TextField manufacturer;
	@FXML
	private TextField expiryDate;
	@FXML
	private TextField medCost;
	@FXML
	private TextField medUnits;
	@FXML
	private TextArea existingMeds;
	@FXML
	private Label systime;
	@FXML
	private void initialize() {
		// Populate the existing med list
		populateExistingMeds();
		SysTime.showTime(systime);
	}

	private void populateExistingMeds() { // Populate the TextArea with data from the HospitalManagement class
		Medicine[] meds = HospitalManagement.getMedicines(); // method from main class
		StringBuilder sb = new StringBuilder();

		// Loop through the medicine array
		for (Medicine med : meds) {
			// Check for null entries in the array
			if (med != null) {
				sb.append(med.showMedicineInfo()).append("\n");
			}
		}

		// Set string to the TextArea
		existingMeds.setText(sb.toString());
	}

	@FXML // add new medicine entry
	private void addNewMed(ActionEvent event) {
		// get values from all fields
		String name = medName.getText();
		String manu = manufacturer.getText();
		String date = expiryDate.getText();
		String cost = medCost.getText();
		String units = medUnits.getText();

		if (!validation.isAlphabetic(medName, "Name"))
			return;
		if (!validation.isAlphabetic(manufacturer, "Manufacturer"))
			return;
		if (!validation.isValidDate(expiryDate, "Date"))
			return;
		if (!validation.isPositiveInteger(medCost, "Cost"))
			return;
		if (!validation.isPositiveInteger(medUnits, "Units"))
			return;
		clearFields();

		Medicine nMed = new Medicine();
		nMed.newMedicine(name, manu, date, Integer.parseInt(cost), Integer.parseInt(units));
		if (HospitalManagement.addMedicine(nMed)) {
			validation.addSuccessAlert("Medicine successfully added.");
			populateExistingMeds();
        	NavigationPrompt.showPostAddPrompt(event);
            clearFields();
		} else
			validation.addFailAlert("You have reached the maximum number of medicines added.");

		populateExistingMeds();
		clearFields();
	}

	private void clearFields() {
		medName.clear();
		manufacturer.clear();
		expiryDate.clear();
		medCost.clear();
		medUnits.clear();
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