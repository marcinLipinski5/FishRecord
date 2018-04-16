package utils;

import java.util.ArrayList;
import java.util.List;

import data.FishInDatabase;
import data.FishDatabase;

//This class contain all methods and variables necessaries to operate speciesDatabase
public class SpeciesDatabaseOperation {
	FishDatabase dataBase = new FishDatabase();
	private static List<FishInDatabase> list = new ArrayList<>();
	private static List<String> speciesStringDatabase =  new ArrayList<>();
	int calc=0;
	
	public FishDatabase createFish() {
		
		calc = list.size() - 1;
		if (true) {
		dataBase.add(list.get(calc));
		}
		return dataBase;
	}
	
	public void sendToXml() {
		CreateXmlSpeciesDatabase create = new CreateXmlSpeciesDatabase();
		create.addToXML(createFish());
	}

	 public void add(FishInDatabase list) {
	        SpeciesDatabaseOperation.list.add(list);
	        createFish();
	    }
	 
	    public void setList(List<FishInDatabase> list) {
	        SpeciesDatabaseOperation.list = list;
	    }
	    
	    public void setSpeciesStringList() {
	    	for(FishInDatabase fish: dataBase.getFishDatabase()) {
	    		speciesStringDatabase.add(fish.getSpecies());
	    	}
	    }
	    
	    public List<String> getSpeciesStringList(){
	    	return speciesStringDatabase;
	    }
}
