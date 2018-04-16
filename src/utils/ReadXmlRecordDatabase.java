package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import data.FishInRecord;
import data.FishRecord;
import javafx.stage.FileChooser;

//In this class recordDatabase is loaded from xml file
public class ReadXmlRecordDatabase {
	FishRecord fishRecord = new FishRecord();
	CreateXmlRecordDatabase createXmlRecordDatabase = new CreateXmlRecordDatabase();
	
	private static List<String> message = new ArrayList<String>();

	public void readFishRecord(File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(data.FishRecord.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FishRecord fishRecord = (FishRecord) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(file));
	
	}

	//Chose a file which contains recordDatabase
	public void readFile() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.XML)", "*.XML");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			try {
				createXmlRecordDatabase.setFile(file);
				readFishRecord(file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//Create information string about loaded fishRecord
	public void printRecord() {

		for (FishInRecord fish : fishRecord.getFishRecord()) {
			createMessage(fish.getDate() +" : " +fish.getSpecies() + ", " + fish.getSize() + "cm, " + fish.getWeight() + "kg\n");
			System.out.println(fish.getDate() +" : " +fish.getSpecies() + ", " + fish.getSize() + "cm, " + fish.getWeight() + "kg\n");	
		}

	}
	public void createMessage(String string) {
		message.add(string);
		
	}
	
	public List<String> getMessage(){
		return message;
	}
}
