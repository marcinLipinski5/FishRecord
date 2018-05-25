package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/gui/MainPane.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
			stage.setTitle("Record of caught fish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
