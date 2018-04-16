package control;

import java.io.File;
import java.net.URL;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.Angler;
import data.AnglerDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import utils.CreateXmlAnglerDatabase;
import utils.ReadXmlAnglerDatabase;

public class AddPersonalDataController implements Initializable {

	// Get instance of all needed class
	AnglerDatabase anglerDatabase = new AnglerDatabase();
	CreateXmlAnglerDatabase createXmlAnglerDatabase = new CreateXmlAnglerDatabase();
	ReadXmlAnglerDatabase readXmlAnglerDatabase = new ReadXmlAnglerDatabase();
	
	//Creating list to keep angler personal data
	List<AnglerDatabase> list = new ArrayList<>();

	@FXML
	private TextField anglerNameTextField;

	@FXML
	private TextField anglerSurnameTextField;

	@FXML
	private TextField anglerIdNumberTextField;

	@FXML
	private Button saveAnglerDataButton;

	@FXML
	private Button loadAnglerDataButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/////////////////////////////////////////
		//////// saveAnglerDataButton start/////
		///////////////////////////////////////
		saveAnglerDataButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//clearing anglerDatabase array - only one angler can by write in single file
				anglerDatabase.getAnglerDatabase().clear();		
				//Adding angler to anglerDatabase. Angler class from package data is used in this place
				addAngler(anglerNameTextField.getText(), anglerSurnameTextField.getText(), anglerIdNumberTextField.getText());

			}
		});

	}//Initialize method ends

	//Adding angler to anglerDatabase. Angler class from package data is used in this place
	public void addAngler(String name, String surname, String idNumber) {
		anglerDatabase.add(new Angler(name, surname, idNumber));
		setFile();
	}

	//Selecting file to save angler personals from anglerDatabase
	public void setFile() {
		FileChooser fileChooser = new FileChooser();
		// Set filters
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		// Use predefined file name
		fileChooser.setInitialFileName(anglerNameTextField.getText() + "_" + anglerSurnameTextField.getText());
		//Show save file dialog window
		File file = fileChooser.showSaveDialog(null);
		//Parse anglerDatabase to xml file. Class CreateXmlAnglerDatabase from package utils is used
		if (file != null) {
			createXmlAnglerDatabase.addAnglerToXML(anglerDatabase, file);
		}
	}

}
