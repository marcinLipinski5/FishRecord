package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import data.FishInDatabase;
import data.FishDatabase;

public class ReadXmlSpeciesDatabase {
	File database = new File("database.xml");
	public static List<FishInDatabase> list = new ArrayList<>();

	public void readSpeciesDatabase() throws JAXBException, FileNotFoundException {

		if (database != null) {
			JAXBContext jaxbContext = JAXBContext.newInstance(data.FishDatabase.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			FishDatabase fishDatabase = fishDatabase = (FishDatabase) JAXBIntrospector
					.getValue(jaxbUnmarshaller.unmarshal(database));
			for (FishInDatabase fish : fishDatabase.getFishDatabase()) {
				list.add(new FishInDatabase(fish.getMinimumSize(), fish.getProtectionPeriodEnd(),
						fish.getProtectionPeriodStart(), fish.getSpecies()));
			}
		} else {
			System.out.println("test2");
		}
	}

	public List<FishInDatabase> getList() {
		return list;
	}
}
