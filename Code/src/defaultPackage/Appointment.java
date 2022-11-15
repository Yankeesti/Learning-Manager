package defaultPackage;

import java.util.Date;

public class Appointment {
	Date date;
	String description;
	
	public Appointment(Date date, String description) {
		this.date = date;
		this.description = description;
	}
	
	public Date getDate() {return date;}
	public String getDescribtion() {return description;}
}
