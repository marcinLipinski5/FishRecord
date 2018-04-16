package control;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import data.AnglerDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import utils.CreateXmlRecordDatabase;
import utils.CurrentDate;
import utils.FishInRecordOperation;
import utils.ReadXmlAnglerDatabase;
import utils.ReadXmlRecordDatabase;
import utils.ReadXmlSpeciesDatabase;
import utils.SpeciesDatabaseOperation;

public class MainPaneController implements Initializable {

	//Get instance of all needed classes
	AnglerDatabase anglerDatabase = new AnglerDatabase();
	SpeciesDatabaseOperation speciesDatabaseOperation = new SpeciesDatabaseOperation();
	FishInRecordOperation fishInRecordOperation = new FishInRecordOperation();
	CreateXmlRecordDatabase createXmlRecordDatabase = new CreateXmlRecordDatabase();
	ReadXmlRecordDatabase readXmlRecordDatabase = new ReadXmlRecordDatabase();

	@FXML
	private MenuItem loadPersonalDataMenuButton;

	@FXML
	private MenuItem addPersonalDataMenuButton;

	@FXML
	private MenuItem addSpeciesMenuButton;

    @FXML
    private MenuItem loadRecordMenuButton;
    
    @FXML
    private MenuItem aboutMenuButton;
    
	@FXML
	private MenuBar menuPanel;

	@FXML
	private Label dateLabel;

	@FXML
	private Label personalDataLabel;

	@FXML
	private TextField sizeTextField;

	@FXML
	private TextField weightTextField;

	@FXML
	private CheckBox relasedCheckBoxYes;

	@FXML
	private CheckBox relasedCheckBoxNo;

	@FXML
	private ChoiceBox<String> speciesChoiceBox;

	@FXML
	private Button addToRecordButton;

	@FXML
	private Button clearButton;
	
    @FXML
    private TextArea infoTextArea;
    
    //Getters methods

	public String getSpeciesChoiceBox() {
		return speciesChoiceBox.getValue().toString();
	}

	public String getWeightTextField() {
		return weightTextField.getText();
	}

	public String getSizeTextField() {
		return sizeTextField.getText();
	}

	public Boolean getIsRelasedCheckBox() {
		//Check the value of relased yes/no check boxes
		Boolean temp = true;
		if(relasedCheckBoxYes.isSelected()) {
			temp = true;
		}else if(relasedCheckBoxNo.isSelected()) {
			temp = false;
		}
		return temp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initializeSpeciesDatabase();
		
		////////////////////////////////////////////
		////  addToRecordButton////////////////////
		//////////////////////////////////////////
		addToRecordButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
				//At first try to load angler personals to avoid adding fish to record before
				//initialize personal data
				anglerDatabase.getAnglerDatabase().get(0);
				//Sending strings written by user to method which is creating new FishInRecord object
				fishInRecordOperation.createNewRecord(getSpeciesChoiceBox(), getSizeTextField(), getWeightTextField(), getIsRelasedCheckBox() );
				//Send to information text area texts about fish which is now added
				setInfoTextArea(fishInRecordOperation.getAddedFish());
				setInfoTextArea(fishInRecordOperation.getProtectionPeriodInformation());
				setInfoTextArea(fishInRecordOperation.getProtectionSizeInformation());
				setInfoTextArea("----------------------------------------------------\n");
				//Parsing new FishInRecord object to fish record xml file
				createXmlRecordDatabase.saveRecord();
				}catch(IndexOutOfBoundsException ex) {
				//Show an error pop-up window where user try to save record before loading personals data
					messageNoPersonalDataLoaded();
				}
				
			}
		});

		////////////////////////////////////////////
		////clearButton////////////////////////////
		//////////////////////////////////////////	
		//This button is clearing all text fields. Its just for speed up adding to record proces.
		clearButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sizeTextField.clear();
				weightTextField.clear();
			}
		});
		
		////////////////////////////////////////////
		////dateLabel//////////////////////////////
		//////////////////////////////////////////
		//Setting actual date to the dateLabel
		CurrentDate currentDate = new CurrentDate();
		dateLabel.setText(currentDate.getCurrentDate());
		/////////////////////////////////////////////

		//Setting an inform on the personalDataLabel. This string will disappear after loading angler personals from file
		personalDataLabel.setText("Please load personal data");

		//////////////////////////////////////////////////////////////////
		////////////// Set species to speciesChoiceBox///////////////////
		////////////////////////////////////////////////////////////////
		setSpeciesChoiceBox();

		//////////////////////////////////////////////////////////////////
		////////////// Set relased check boxes///////////////////////////
		////////////////////////////////////////////////////////////////
		//Two methods under this comments are checking check boxes "yes/no" to avoid situation
		//where both are selected
		relasedCheckBoxYes.setSelected(true);
		relasedCheckBoxYes.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				relasedCheckBoxNo.setSelected(false);
				relasedCheckBoxYes.setSelected(true);
			}

		});

		relasedCheckBoxNo.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				relasedCheckBoxYes.setSelected(false);
				relasedCheckBoxNo.setSelected(true);
			}

		});
		
		

		////////////////////////////////////////////////////////////////
		////////////// Menu buttons////////////////////////////////////
		//////////////////////////////////////////////////////////////
		menuButtons();		

	}
	
	//Setting information to informatin text area
	public void setInfoTextArea(String information) {
		if(information!=null) {
		infoTextArea.appendText(information);
		}
	}
	
	//Reading angler personals from proper xml file
	public void readPersonalData() {
		ReadXmlAnglerDatabase xmlAnglerDatabase = new ReadXmlAnglerDatabase();
		xmlAnglerDatabase.readFile();
	}

	//Settings angler personals to personals data label
	public void setPersonalData() {
		String personals = null;
		//Where personals data are proper loaded and proper formatted personalsDataLabel
		//is getting this values
		if (anglerDatabase.getAnglerDatabase().get(0) != null) {
			personals = anglerDatabase.getAnglerDatabase().get(0).getName() + " "
					+ anglerDatabase.getAnglerDatabase().get(0).getSurname() + " "
					+ anglerDatabase.getAnglerDatabase().get(0).getIdNumber();

		} else {
			//In other case this message appears at personaldDataLabel
			personals = "Pleace load proper personals data";
		}

		personalDataLabel.setText(personals);

	}

	//This method is operate at buttons from menu
	public void menuButtons() {
		/// addSpeciesMenuButton///
		//This button is opening a new window AddFishPane.fxml
		//This allows to add new species to speciesDatabase
		addSpeciesMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("/gui/AddFishPane.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setTitle("Add species to database");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
		});
		
		/// addPersonalDataMenuButton///
		//This button is opening a new window AddPersonalDataPane.fxml
		//This allows to crate a new xml file with angler personal data
		addPersonalDataMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("/gui/AddPersonalDataPane.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setTitle("Set up personal data");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
		});

		///loadPersonalDataMenuButton///
		//This button allows to load angler personals from xml file
		loadPersonalDataMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				readPersonalData();
				setPersonalData();

			}
		});
		///////////////////////////////
		///This button allows to load fish record from xml file
		loadRecordMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				readXmlRecordDatabase.readFile();
				readXmlRecordDatabase.printRecord();
				printMessage();

			}
		});
		/// aboutMenuButton///
		//This button is opening a new window AboutPanefxml
		//This allows show about file
		aboutMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("/gui/AboutPane.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setTitle("About");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
		});
	}
	
	//This method is creating a message which contains info about all object loaded from xml record file
	public void printMessage() {
		for(String record: readXmlRecordDatabase.getMessage()) {
			setInfoTextArea(record);
		}
		
	}

	//In this method all species from speciesDatabase all loaded to memory
	public void setSpeciesChoiceBox() {
		ArrayList<String> speciesList = new ArrayList<String>();
		//Load all speciesDatabase to memory and create array which contains only name of the species
		speciesDatabaseOperation.setSpeciesStringList();
		//adding all name string to the list and
		for (String species : speciesDatabaseOperation.getSpeciesStringList()) {
			speciesList.add(species);
		}
		//and adding this strings to ChoiceBox chooses list
		ObservableList<String> list = FXCollections.observableArrayList(speciesList);
		speciesChoiceBox.setItems(list);
		speciesChoiceBox.getSelectionModel().selectFirst();
	}
	
	//Error which appear when personal data aren't loaded
	public void messageNoPersonalDataLoaded() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Load personal data.");
		alert.setContentText("Please first load personal data.");
		alert.showAndWait();
	}
	
	public void messageNoDatabaseFounded() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Load species database.");
		alert.setContentText("The file database.xml was not found. Make sure that this file is on main application catalog. You can also create your own database using Edit->Add species");
		alert.showAndWait();
	}
	
	public void initializeSpeciesDatabase() {
	try {	
		ReadXmlSpeciesDatabase xmlSpeciesDatabase = new ReadXmlSpeciesDatabase();
		xmlSpeciesDatabase.readSpeciesDatabase();
	} catch (JAXBException e) {
		messageNoDatabaseFounded();
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	}
	
}