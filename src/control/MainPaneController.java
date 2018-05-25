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
		

		addToRecordButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

				anglerDatabase.getAnglerDatabase().get(0);
		
				fishInRecordOperation.createNewRecord(getSpeciesChoiceBox(), getSizeTextField(), getWeightTextField(), getIsRelasedCheckBox() );
			
				setInfoTextArea(fishInRecordOperation.getAddedFish());
				setInfoTextArea(fishInRecordOperation.getProtectionPeriodInformation());
				setInfoTextArea(fishInRecordOperation.getProtectionSizeInformation());
				setInfoTextArea("----------------------------------------------------\n");
				
				createXmlRecordDatabase.saveRecord();
				}catch(IndexOutOfBoundsException ex) {
		
					messageNoPersonalDataLoaded();
				}
				
			}
		});

	
		clearButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sizeTextField.clear();
				weightTextField.clear();
			}
		});
		

		CurrentDate currentDate = new CurrentDate();
		dateLabel.setText(currentDate.getCurrentDate());
	
		personalDataLabel.setText("Please load personal data");


		setSpeciesChoiceBox();

	
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
		
		


		menuButtons();		

	}

	public void setInfoTextArea(String information) {
		if(information!=null) {
		infoTextArea.appendText(information);
		}
	}
	

	public void readPersonalData() {
		ReadXmlAnglerDatabase xmlAnglerDatabase = new ReadXmlAnglerDatabase();
		xmlAnglerDatabase.readFile();
	}


	public void setPersonalData() {
		String personals = null;

		if (anglerDatabase.getAnglerDatabase().get(0) != null) {
			personals = anglerDatabase.getAnglerDatabase().get(0).getName() + " "
					+ anglerDatabase.getAnglerDatabase().get(0).getSurname() + " "
					+ anglerDatabase.getAnglerDatabase().get(0).getIdNumber();

		} else {

			personals = "Pleace load proper personals data";
		}

		personalDataLabel.setText(personals);

	}


	public void menuButtons() {

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


		loadPersonalDataMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				readPersonalData();
				setPersonalData();

			}
		});

		loadRecordMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				readXmlRecordDatabase.readFile();
				readXmlRecordDatabase.printRecord();
				printMessage();

			}
		});

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
	

	public void printMessage() {
		for(String record: readXmlRecordDatabase.getMessage()) {
			setInfoTextArea(record);
		}
		
	}


	public void setSpeciesChoiceBox() {
		ArrayList<String> speciesList = new ArrayList<String>();

		speciesDatabaseOperation.setSpeciesStringList();
	
		for (String species : speciesDatabaseOperation.getSpeciesStringList()) {
			speciesList.add(species);
		}
	
		ObservableList<String> list = FXCollections.observableArrayList(speciesList);
		speciesChoiceBox.setItems(list);
		speciesChoiceBox.getSelectionModel().selectFirst();
	}
	
	
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