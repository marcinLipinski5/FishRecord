package data;

import javax.xml.bind.annotation.XmlElement;

//Class Angler is creating an angler personal data
public class Angler {
	
	
	private String name;
	private String surname;
	private String idNumber;
	
	public Angler() {
		
	}
	
	public Angler (String name, String surname, String idNumber) {
		setName(name);
		setSurname(surname);
		setIdNumber(idNumber);
	}
	
	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "Surname")
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@XmlElement(name = "IdNumber")
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}
