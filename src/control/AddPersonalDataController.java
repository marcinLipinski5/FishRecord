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

	AnglerDatabase anglerDatabase = new AnglerDatabase();
	CreateXmlAnglerDatabase createXmlAnglerDatabase = new CreateXmlAnglerDatabase();
	ReadXmlAnglerDatabase readXmlAnglerDatabase = new ReadXmlAnglerDatabase();

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

		saveAnglerDataButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				anglerDatabase.getAnglerDatabase().clear();

				addAngler(anglerNameTextField.getText(), anglerSurnameTextField.getText(),
						anglerIdNumberTextField.getText());

			}
		});

	}
	
	public void addAngler(String name, String surname, String idNumber) {
		anglerDatabase.add(new Angler(name, surname, idNumber));
		setFile();
	}

	
	public void setFile() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialFileName(anglerNameTextField.getText() + "_" + anglerSurnameTextField.getText());
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			createXmlAnglerDatabase.addAnglerToXML(anglerDatabase, file);
		}
	}

}
