package utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import data.AnglerDatabase;
import data.FishRecord;
import javafx.stage.FileChooser;


public class CreateXmlRecordDatabase {
	FileChooser fileChooser = new FileChooser();
	AnglerDatabase anglerDatabase = new AnglerDatabase();
	FishRecord fishRecord = new FishRecord();
	
	
	private static File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		CreateXmlRecordDatabase.file = file;
	}


	public void sellectFile() {
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialFileName("Fish_record_" + anglerDatabase.getAnglerDatabase().get(0).getName() + "_"
				+ anglerDatabase.getAnglerDatabase().get(0).getSurname());
		File file = fileChooser.showSaveDialog(null);
		setFile(file);
		if (file != null) {
			addRecordToXML(fishRecord, file);
		}
	}


	public void addRecordToXML(FishRecord fishRecord, File file) {

		if (file == null) { 
			sellectFile();
		} else {

			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(data.FishRecord.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(fishRecord, System.out);
				jaxbMarshaller.marshal(fishRecord, file);
			} catch (JAXBException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	public void saveRecord() {
		Boolean error = false;
		try {
			fishRecord.getFishRecord().get(1); 
		}catch(IndexOutOfBoundsException ex) {
			sellectFile();						
			error = true;
		}
		if(!error) {
			addRecordToXML(fishRecord, file);	
		}
			
	}

}