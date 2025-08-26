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
        String text = field.getText();
        if (text == null || !text.matches("[a-zA-Z ]+")) {
            showAlert(fieldName + " must contain only alphabets and spaces!");
            return false;
        }
        return true;
    }

    // Common alert popup
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
