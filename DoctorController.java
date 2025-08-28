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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        docWorkTime.getItems().addAll("0800-1200", "1200-1600", "1600-2000", "0800-1800");
        
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
        clearFields();
        
        Doctor newDoc = new Doctor();
        newDoc.newDoctor(id, name, specialist, workTime, qualification, Integer.parseInt(room));
        if(HospitalManagement.addDoctor(newDoc)) {
        	validation.addSuccessAlert("Doctor successfully added.");
        }
        else
        	validation.addFailAlert("You have reached the maximum number of doctors added.");
        
        populateExistingDoctors();
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
    
    //back to main menu
    public void menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}