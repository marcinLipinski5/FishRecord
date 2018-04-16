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

	// Get instance of all needed class
	SpeciesDatabaseOperation databaseOperation = new SpeciesDatabaseOperation();
	FishDatabase database = new FishDatabase();
	ReadXmlSpeciesDatabase xmlDatabase = new ReadXmlSpeciesDatabase();

	// Create arrayList which contains current added fish
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
		/////////////////////////////////////////
		//////// addToDatabaseButton start//////
		///////////////////////////////////////
		addToDatabaseButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// Reading existing species database
				for (FishInDatabase fish : xmlDatabase.getList()) {
					list.add(fish);
				}

				// Creating new species. Class "FishInDatabase" is used in this place.
				addSpecies(addSpeciesTextField.getText(), addMinimumSizeTextField.getText(),
						protectPeriodStartTextField.getText(), protectPeriodEndTextField.getText());
				clearTextFields();
			}
		});

		/////////////////////////////////////////
		//////// saveDatabaseButton start///////
		///////////////////////////////////////
		saveDatabaseButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Check the current list of fish species. 
				if (!list.isEmpty()) {					//If species was added to database (add to database button pressed
					databaseOperation.setList(list);	//species is added to current list and
					databaseOperation.sendToXml();		//send to species database xml file.
					messageDatabaseSaved();				//Inform message pop-up window
				} else {
					messageNoObjectAddedError();		//If the species wasn't added to database first (addToDatabase 
				}										//wasn't pressed first) the error pop-up window is shown

			}
		});
		
	}//End of the initialize method

	//Adding species to database. FishInDatabase class from data package is used in this method.
	public void addSpecies(String species, String minSize, String protPerStart, String protPerEnd) {

		list.add(new FishInDatabase(species, minSize, protPerStart, protPerEnd));

	}

	//Clearing all text field to make app more friendly for the users
	public void clearTextFields() {
		addSpeciesTextField.clear();
		addMinimumSizeTextField.clear();
		protectPeriodStartTextField.clear();
		protectPeriodEndTextField.clear();
	}

	//Message in pop-up window which is shown after successful saved fish species to xml database file
	public void messageDatabaseSaved() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Database saving");
		alert.setContentText("Species succesfull saved to database");
		alert.showAndWait();
	}

	//Messege in pop-up window which is shown where attempt to save fish species fail
	public void messageNoObjectAddedError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No species to save.");
		alert.setContentText("There is no species to save. Please create some species before saveing it");
		alert.showAndWait();
	}
}
