package defaultPackage;

import java.util.Date;

public class TimePeriod extends Date{
	protected Date endDate;
	
	public TimePeriod(Date startDate, Date endDate) {
		this(startDate);
		this.endDate = new Date();
		this.endDate.setTime(endDate.getTime());
	}
	
	public TimePeriod(Date startDate) {
		super();
		setTime(startDate.getTime());
	}
	
	public TimePeriod(int startYear, int startMonth, int startDate) {
		super(startYear,startMonth,startDate);
	}
	
	public TimePeriod(int startYear, int startMonth, int startDate, int endYear, int endMonth, int endDate) {
		this(startYear,startMonth,startDate);
		this.endDate = new Date(endYear,endMonth,endDate);
	}
	
	
	/**
	 * 
	 * @param anotherDate - Date to compare
	 * @return the value 0 if the argument Date is in the Time Period; a value less than 0 if this Time Period is before the Date argument; and a value greater than 0 if this Time Period is after the Date argument.
	 */
	public int compareTo(Date anotherDate) {
		if((endDate != null && super.compareTo(anotherDate) <= 0 && endDate.compareTo(anotherDate) >= 0)||  super.compareTo(anotherDate)==0)
			return 0;
		
		if(super.compareTo(anotherDate) < 0)
			return -1;
		
		return 1;
		}
	
	/**
	 * 
	 * @param anotherTimePeriod - TimePeriod to compare
	 * @return the value 0 if the argument TimePeriod is in the Time Period; -1 if the Time PeriodArgument start is in this time Period but the ending is after this Time Period; -2 if the Time Period Argument is after this Time Period; 1 if the Time Period ends is in this time Period but the start is before this Time Period; 2 if the Time Period Argument is before this Time Period
	 */
	public int compareTo(TimePeriod anotherTimePeriod) {
		if(super.compareTo(anotherTimePeriod)<= 0 && endDate.compareTo(anotherTimePeriod.getEndDate())>=0 )
			return 0;
		
		if(super.compareTo(anotherTimePeriod)<= 0 && endDate.compareTo(anotherTimePeriod) >= 0)
			return -1;
		
		if(endDate.compareTo(anotherTimePeriod) < 0)
			return -2;
		
		if(super.compareTo(anotherTimePeriod.getEndDate())>0)
			return 2;
		
		return 1;
		
	}
	
	/**
	 * Sets this TimePeriod object to represent a point in time period that is time milliseconds after January 1, 1970 00:00:00 GMT.
	 * @param startTime - the number of milliseconds for the start Point
	 * @param endTime - the number of milliseconds for the end Point
	 */
	public void setTime(long startTime, long endTime) {
		setTime(startTime);
		if(endDate != null)
			endDate.setTime(endTime);
		else {
			endDate = new Date();
			endDate.setTime(endTime);
		}
		
	}
	
	public void setEndTime(long endTime) {
		if(endDate != null)
			endDate.setTime(endTime);
		else {
			endDate = new Date();
			endDate.setTime(endTime);
		}
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	
	
}
