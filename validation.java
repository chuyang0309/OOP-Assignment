package ass;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class validation {

    // Check if a TextField is empty
    public static boolean isEmpty(TextField field, String fieldName) {
        if (field.getText() == null || field.getText().trim().isEmpty()) {
            showAlert(fieldName + " cannot be empty!");
            return true;
        }
        return false;
    }

    // Check if a ComboBox is empty
    public static <T> boolean isEmpty(ComboBox<T> comboBox, String fieldName) {
        if (comboBox.getValue() == null) {
            showAlert(fieldName + " must be selected!");
            return true;
        }
        return false;
    }

    // Check if a field contains only numbers
    public static boolean isNumeric(TextField field, String fieldName) {
        try {
            Integer.parseInt(field.getText());
            return true;
        } catch (NumberFormatException e) {
            showAlert(fieldName + " must be a number!");
            return false;
        }
    }

    // Check if a field contains only alphabets and spaces
    public static boolean isAlphabetic(TextField field, String fieldName) {
    	if (isEmpty(field, fieldName)) return false;
    	
        String text = field.getText();
        if (text == null || !text.matches("[a-zA-Z ]+")) {
            showAlert(fieldName + " must contain only alphabets and spaces!");
            return false;
        }
        return true;
    }
    
    public static boolean isValidDate(TextField field, String fieldName) {
    	if (isEmpty(field, fieldName)) return false;
    	
        String dateStr = field.getText();
        // Validate DD-MM-YYYY format
        String dateFormat = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(20)\\d{2}$";
        
        if (dateStr == null || !dateStr.matches(dateFormat)) {
            showAlert(fieldName + " must be a valid date + in DD-MM-YYYY format!");
            return false;
        }
        return true;
    }

    public static boolean isPositiveInteger(TextField field, String fieldName) {
    	if (isEmpty(field, fieldName)) return false;
        if (!isNumeric(field, fieldName)) return false;
        
        int value = Integer.parseInt(field.getText());
        if (value < 0) {
            showAlert(fieldName + " must be a positive number!");
            return false;
        }
        return true;
    }
    
    public static boolean isAlphaNumeric(TextField field, String fieldName) {
    	if (isEmpty(field, fieldName)) return false;
    	
        String text = field.getText();
        if (text == null || !text.matches("[a-zA-Z0-9]+")) {
            showAlert(fieldName + " must contain only letters and numbers!");
            return false;
        }
        return true;
    }
    
    // Common alert popup
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void addSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Success!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void addFailAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Failed!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
