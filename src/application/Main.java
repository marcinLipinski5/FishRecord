package application;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import utils.ReadXmlSpeciesDatabase;

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
	//Try to read fish species database at first


		//Launch main frame
		launch(args);
		


	}
}
