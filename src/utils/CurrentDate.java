package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDate {
	Calendar cal = Calendar.getInstance();
	public String getCurrentDate() {	
		SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
		return  currentDate.format(cal.getTime());
	}	
}
