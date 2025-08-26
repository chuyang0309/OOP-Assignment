package ass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DoctorController {

    @FXML private TextField docID; //get nodes from fxml file according to id
    @FXML private TextField docName; 
    @FXML private TextField docSpecialist; 
    @FXML private ComboBox<String> docWorkTime; 
    @FXML private TextField docQualification; 
    @FXML private TextField docRoom;
    @FXML private Button addDoctor; 
    @FXML private TextArea existingDoctors; 

    @FXML
    private void initialize() { 
        // Add options to the ComboBox
        docWorkTime.getItems().addAll("0800-1200", "1200-1600", "1600-2000", "0800-1800 (Full Day)");
        
        // Populate the existing doctors list
        populateExistingDoctors();
    }
    
    private void populateExistingDoctors() {           //Populate the TextArea with data from the HospitalManagement class
        Doctor[] doctors = HospitalManagement.getDoctors();  //method from main class
        StringBuilder sb = new StringBuilder();

        // Loop through the doctors array
        for (Doctor doc : doctors) {
            // Check for null entries in the array
            if (doc != null) {
                // Use the showDoctorInfo() method from the Doctor class
                sb.append(doc.showDoctorInfo()).append("\n");
            }
        }
        
        // Set string to the TextArea
        existingDoctors.setText(sb.toString());
    }
    
    @FXML  //add new doctor entry
    private void addNewDoctor(ActionEvent event) {
        // get values from all fields
        String id = docID.getText();
        String name = docName.getText();
        String specialist = docSpecialist.getText();
        String workTime = docWorkTime.getValue();
        String qualification = docQualification.getText();
        String room = docRoom.getText();
        
        if (validation.isEmpty(docID, "Doctor ID")) return;
        if (validation.isEmpty(docName, "Name")) return;
        if (!validation.isAlphabetic(docName, "Name")) return; 
        if (validation.isEmpty(docSpecialist, "Specialist")) return;
        if (!validation.isAlphabetic(docSpecialist, "Specialist")) return; 
        if (validation.isEmpty(docWorkTime, "Work Time")) return;
        if (validation.isEmpty(docQualification, "Qualification")) return;
        if (!validation.isAlphabetic(docQualification, "Qualification")) return; 
        if (validation.isEmpty(docRoom, "Room Number")) return;
        if (!validation.isNumeric(docRoom, "Room Number")) return;

        System.out.println("Doctor successfully added!");
        clearFields();
        
        // 2. For now, we'll just print the values to the console to verify
        System.out.println("--- New Doctor Added ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Specialist: " + specialist);
        System.out.println("Work Time: " + workTime);
        System.out.println("Qualification: " + qualification);
        System.out.println("Room: " + room);
        System.out.println("------------------------");
        
        // TODO: In the future, you would create a new Doctor object here and add it to your list
        // For example:
        // Doctor newDoc = new Doctor();
        // newDoc.newDoctor(id, name, specialist, workTime, qualification, Integer.parseInt(room));
        // HospitalManagement.addDoctor(newDoc); // You would need to create this method
        // populateExistingDoctors(); // Refresh the text area

        // 3. Clear the input fields after adding
        clearFields();
    }
    
    private void clearFields() {
        docID.clear();
        docName.clear();
        docSpecialist.clear();
        docWorkTime.getSelectionModel().clearSelection();
        docQualification.clear();
        docRoom.clear();
    }
}