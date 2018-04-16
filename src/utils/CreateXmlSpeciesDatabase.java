package utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import data.FishDatabase;

//This class is sending speciesDatabase to predefined xml file
public class CreateXmlSpeciesDatabase {
	
	File database = new File("database.xml");


	public void addToXML(FishDatabase dataBase) {
		

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(data.FishDatabase.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(dataBase, System.out);
			jaxbMarshaller.marshal(dataBase, database);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

}
