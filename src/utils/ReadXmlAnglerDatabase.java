package utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import data.AnglerDatabase;
import javafx.stage.FileChooser;

public class ReadXmlAnglerDatabase {

	AnglerDatabase anglerData = new AnglerDatabase();

	public void readAnglerDatabase(File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(data.AnglerDatabase.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		AnglerDatabase anglerDatabase = (AnglerDatabase) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(file));
	}

	public void readFile() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.XML)", "*.XML");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			try {
				readAnglerDatabase(file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
}
