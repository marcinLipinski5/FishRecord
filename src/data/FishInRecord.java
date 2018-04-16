package data;

import javax.xml.bind.annotation.XmlElement;

import utils.CurrentDate;

//This class is creating objects which are added to record database
public class FishInRecord extends Fish {
	

	String size;
	String weight;
	Boolean relased = true;
	Boolean protectionPeriod = true;
	String date;
	
	public FishInRecord () {
		
	}
	
	public FishInRecord(String species, String size, String weight, Boolean relased, Boolean protectionPeriod ) {
		super(species);
		setSize(size);
		setWeight(weight);
		setRelased(relased);
		setProtectionPeriod(protectionPeriod);
		setDate(new CurrentDate().getCurrentDate());
		
	}

	@XmlElement(name = "Date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@XmlElement(name = "Size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@XmlElement(name = "Weight")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@XmlElement(name = "Relased")
	public Boolean getRelased() {
		return relased;
	}

	public void setRelased(Boolean relased) {
		this.relased = relased;
	}

	@XmlElement(name = "ProtectionPeriod")
	public Boolean getProtectionPeriod() {
		return protectionPeriod;
	}

	public void setProtectionPeriod(Boolean protectionPeriod) {
		this.protectionPeriod = protectionPeriod;
	}
	
	

}
