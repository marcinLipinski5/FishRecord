package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.FishInDatabase;
import data.FishDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import utils.ReadXmlSpeciesDatabase;
import utils.SpeciesDatabaseOperation;

public class AddFishPaneController implements Initializable {


	SpeciesDatabaseOperation databaseOperation = new SpeciesDatabaseOperation();
	FishDatabase database = new FishDatabase();
	ReadXmlSpeciesDatabase xmlDatabase = new ReadXmlSpeciesDatabase();

	private static List<FishInDatabase> list = new ArrayList<>();

	@FXML
	private TextField protectPeriodStartTextField;

	@FXML
	private TextField protectPeriodEndTextField;

	@FXML
	private TextField addSpeciesTextField;

	@FXML
	private TextField addMinimumSizeTextField;

	@FXML
	private Button addToDatabaseButton;

	@FXML
	private Button saveDatabaseButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		addToDatabaseButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (FishInDatabase fish : xmlDatabase.getList()) {
					list.add(fish);
				}

				addSpecies(addSpeciesTextField.getText(), addMinimumSizeTextField.getText(),
						protectPeriodStartTextField.getText(), protectPeriodEndTextField.getText());
				clearTextFields();
			}
		});

	
		saveDatabaseButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		
				if (!list.isEmpty()) {					
					databaseOperation.setList(list);	
					databaseOperation.sendToXml();		
					messageDatabaseSaved();				
				} else {
					messageNoObjectAddedError();		 
				}										

			}
		});
		
	}

	
	public void addSpecies(String species, String minSize, String protPerStart, String protPerEnd) {

		list.add(new FishInDatabase(species, minSize, protPerStart, protPerEnd));

	}

	public void clearTextFields() {
		addSpeciesTextField.clear();
		addMinimumSizeTextField.clear();
		protectPeriodStartTextField.clear();
		protectPeriodEndTextField.clear();
	}

	public void messageDatabaseSaved() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Database saving");
		alert.setContentText("Species succesfull saved to database");
		alert.showAndWait();
	}

	public void messageNoObjectAddedError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No species to save.");
		alert.setContentText("There is no species to save. Please create some species before saveing it");
		alert.showAndWait();
	}
}
