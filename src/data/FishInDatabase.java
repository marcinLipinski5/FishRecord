package data;

import javax.xml.bind.annotation.XmlElement;

//This class is creating objects which are added to species database
public class FishInDatabase extends Fish {

	private String minimumSize;
	private String protectionPeriodStart;
	private String protectionPeriodEnd;

	public FishInDatabase() {

	}

	public FishInDatabase(String species, String minimumSize, String protectionPeriodStart,
			String protectionPeriodEnd) {
		super(species);
		setMinimumSize(minimumSize);
		setProtectionPeriodStart(protectionPeriodStart);
		setProtectionPeriodEnd(protectionPeriodEnd);

	}

	@XmlElement(name = "MinimumSize")
	public String getMinimumSize() {
		return minimumSize;
	}

	public void setMinimumSize(String minimumSize) {
		this.minimumSize = minimumSize;
	}

	@XmlElement(name = "ProtectionPeriodStart")
	public String getProtectionPeriodStart() {
		return protectionPeriodStart;
	}

	public void setProtectionPeriodStart(String protectionPeriodStart) {
		this.protectionPeriodStart = protectionPeriodStart;
	}

	@XmlElement(name = "ProtectionPeriodEnd")
	public String getProtectionPeriodEnd() {
		return protectionPeriodEnd;
	}

	public void setProtectionPeriodEnd(String protectionPeriodEnd) {
		this.protectionPeriodEnd = protectionPeriodEnd;
	}

}
