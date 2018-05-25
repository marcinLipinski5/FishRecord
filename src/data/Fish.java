package data;

import javax.xml.bind.annotation.XmlElement;

public abstract class Fish {
	
	String species;
	
	public Fish(String species) {
		setSpecies(species);
	}
	
	public Fish() {	
	}

	public void setSpecies(String species) {
		this.species = species;
	}
	
	@XmlElement(name = "Species")
	public String getSpecies() {
		return species;
	}
}
