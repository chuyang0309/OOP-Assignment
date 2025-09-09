package ass;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {

	@FXML private Label systime;

	public void initialize() {
		SysTime.showTime(systime);
	}
	
	// switch to diff menus depending on button
	public void docMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("docScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void labMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("labScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void patMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("patScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void facMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("facScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void staffMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("staffScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void medMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("medScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	
}
