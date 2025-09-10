package ass;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class NavigationPrompt {
	public static void showPostAddPrompt(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Operation Successful");
        alert.setHeaderText("What would you like to do next?");
        
        ButtonType addAnother = new ButtonType("Add Another");
        ButtonType mainMenu = new ButtonType("Main Menu");
        ButtonType exit = new ButtonType("Exit");
        
        alert.getButtonTypes().setAll(addAnother, mainMenu, exit);
        
        alert.showAndWait().ifPresent(buttonType -> {
            try {
                if (buttonType == addAnother) {
                    // this will do nothing
                } else if (buttonType == mainMenu) {
                    returnToMenu(event);
                } else if (buttonType == exit) {
                    Platform.exit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void returnToMenu(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(NavigationPrompt.class.getResource("menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
