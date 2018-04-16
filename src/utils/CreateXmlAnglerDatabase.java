package utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import data.AnglerDatabase;

//This class is sending angler personals to xml file
//Name of this file is creating in class "AddPersonalDataController"
public class CreateXmlAnglerDatabase {

	public void addAnglerToXML(AnglerDatabase AnglerDatabase, File file) {
		

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(data.AnglerDatabase.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(AnglerDatabase, System.out);
			jaxbMarshaller.marshal(AnglerDatabase, file);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

}