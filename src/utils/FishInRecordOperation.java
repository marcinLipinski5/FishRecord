package utils;

import data.FishDatabase;
import data.FishInDatabase;
import data.FishInRecord;
import data.FishRecord;


public class FishInRecordOperation {
	FishDatabase fishDatabase = new FishDatabase();
	private int currentDay;
	private int currentMonth;
	private int protectionPeriodStartDay;
	private int protectionPeriodStartMonth;
	private int protectionPeriodEndDay;
	private int protectionPeriodEndMonth;
	private double minimumSize;
	private String protectionPeriodInformation;
	private String protectionSizeInformation;
	private String fishAdded;


	public void createNewRecord(String species, String size, String weight, Boolean isRelased) {
		FishRecord fishRecord = new FishRecord();
		protectionPeriodInformation = null;
		protectionSizeInformation = null;
		fishAdded = null;

	
		fishRecord.add(new FishInRecord(species, size, weight, isRelased, protectionPeriodCheck(species)));
		setAddedFish(species, size, weight, isRelased, protectionPeriodCheck(species));
		
		if (!isRelased) {
			if (protectionPeriodCheck(species)) {
				setProtectionPeriodInformation(species, protectionPeriodStartDay, protectionPeriodStartMonth,
						protectionPeriodEndDay, protectionPeriodEndMonth);
			}
			if (parseToDouble(size) < minimumSize) {
				setProtectionSizeInformation(species, minimumSize);
			}
		}
	}


	public Boolean protectionPeriodCheck(String species) {
		splitCurrentDate();
		getProtectionValues(species);
		Boolean check = true;
		if (protectionPeriodStartMonth <= protectionPeriodEndMonth) {
			if (currentMonth < protectionPeriodStartMonth || protectionPeriodEndMonth < currentMonth) {
				check = false;
			} else if ((currentMonth >= protectionPeriodStartMonth || protectionPeriodEndMonth >= currentMonth)
					&& (protectionPeriodStartMonth != protectionPeriodEndMonth)) {
				if (currentMonth == protectionPeriodStartMonth) {
					if (currentDay < protectionPeriodStartDay) {
						check = false;
					}

				}
				if (currentMonth == protectionPeriodEndMonth) {
					if (currentDay > protectionPeriodEndDay) {
						check = false;
					}
				}
			} else if ((currentMonth > protectionPeriodStartMonth || protectionPeriodEndMonth > currentMonth)
					&& (protectionPeriodStartMonth != protectionPeriodEndMonth)) {
				check = false;
			}
		} else if (protectionPeriodStartMonth > protectionPeriodEndMonth) {
			if (currentMonth <= protectionPeriodEndMonth || currentMonth >= protectionPeriodStartMonth) {
				if (currentMonth == protectionPeriodStartMonth) {
					if (currentDay < protectionPeriodStartDay) {
						check = false;
					}

				}
				if (currentMonth == protectionPeriodEndMonth) {
					if (currentDay > protectionPeriodEndDay) {
						check = false;
					}
				}

			} else if (currentMonth > protectionPeriodEndMonth || currentMonth < protectionPeriodStartMonth) {
				check = false;
			}
		}

		return check;
	}


	public void getProtectionValues(String species) {

		for (FishInDatabase fish : fishDatabase.getFishDatabase()) {
			if (species.equals(fish.getSpecies())) {
				splitProtectionPeriodStart(fish.getProtectionPeriodStart());
				splitProtectionPeriodEnd(fish.getProtectionPeriodEnd());
				minimumSize = parseToDouble(fish.getMinimumSize());

			}
		}
	}


	public void splitProtectionPeriodStart(String date) {
		protectionPeriodStartDay = parseToInt(splitDate(date)[0]);
		protectionPeriodStartMonth = parseToInt(splitDate(date)[1]);
	}

	public void splitProtectionPeriodEnd(String date) {
		protectionPeriodEndDay = parseToInt(splitDate(date)[0]);
		protectionPeriodEndMonth = parseToInt(splitDate(date)[1]);
	}

	public void splitCurrentDate() {
		CurrentDate currentDate = new CurrentDate();
		String date = currentDate.getCurrentDate().toString();
		currentDay = parseToInt(splitDate(date)[0]);
		currentMonth = parseToInt(splitDate(date)[1]);

	}


	public Integer parseToInt(String string) {
		int number;
		try {
			number = Integer.parseInt(string);

		} catch (NumberFormatException ex) {
			number = 0;
		}
		return number;
	}


	public Double parseToDouble(String string) {
		double number;
		try {
			number = Double.parseDouble(string);
		} catch (NumberFormatException ex) {
			number = 0;
		}
		return number;
	}

	
	public String[] splitDate(String date) {
		String[] parts = date.split("/");

		return parts;
	}


	public void setProtectionPeriodInformation(String species, int ppsd, int ppsm, int pped, int ppem) {
		protectionPeriodInformation = "This fish " + "(" + species + ")" + " is under protection now. "
				+ "Protection period runs from: " + ppsd + "/" + ppsm + " to: " + pped + "/" + ppem
				+ " You must relase it.\n";
	}


	public void setProtectionSizeInformation(String species, Double size) {
		protectionSizeInformation = "This fish (" + species + ") is smaller than minimum size: " + size
				+ "cm. You mast relase it.\n";
	}

	public String getProtectionPeriodInformation() {
		return protectionPeriodInformation;
	}

	public String getProtectionSizeInformation() {
		return protectionSizeInformation;
	}

	
	public void setAddedFish(String species, String size, String weight, Boolean isRelased, Boolean protectionPeriod) {
		fishAdded = species + ", " + size + ", " + weight + ". Was relased: " + isRelased
				+ ". Was under protection period: " + protectionPeriod + "\n";
	}

	public String getAddedFish() {
		return fishAdded;
	}

}
