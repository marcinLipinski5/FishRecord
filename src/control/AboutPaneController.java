package control;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AboutPaneController implements Initializable {

    @FXML
    private Label appNameLabel;

    @FXML
    private TextArea abuotTextArea;

    @FXML
    private Label deweloperNameLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		appNameLabel.setText("Fish Record v.1.1");
		deweloperNameLabel.setText(" Marcin Lipiñski");
		String about = "This application is an electronic version of fish record which every angler in Poland have to keep in standard paper way.\n "
				+ "Main features:\n "
				+ "– Database of fish species which contains species name, minimum size and protective period. You can add new species using Edit -> Add Species.\n"
				+ " – Database of angler personal data. You can add your personals using Edit -> Set up personal data or load predefined personals from File -> Load personal data. \n"
				+ " – Database of the fish you caught. You can simply click Add to record button and save this to new file or choose File -> Load record to extend previous file.\n"
				+ " – This app will check for you protective period an minimum size of the fish you want to take home. You can forget about all these numbers and dates without worrying about fine! \n\n "
				+ "This is very early version of this application. My future plans: \n"
				+ " – Mobile and web version. \n"
				+ " – Generator of *.pdf report suitable to PZW table.\n"
				+ " – Automatic check angler localization using GPS system.\n"
				+ " – More user-friendly interface. ";
		abuotTextArea.appendText(about);
		
	}

}
