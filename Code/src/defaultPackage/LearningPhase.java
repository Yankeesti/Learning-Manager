package defaultPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LearningPhase extends Date{

	Date ended;
	long learned;// Time learned in Seconds
	String subject;
	
	public LearningPhase(String subject) {
		super();
		setTime(getAktDate().getTime());
		this.subject = subject;
	}
	
	public LearningPhase(String subject, Date start, Date end) {
		super();
		setTime(start.getTime());
		this.subject = subject;
		ended = end;
		learned =  (end.getTime()-start.getTime())/1000;
	}
	
	public void finish() {
		ended = getAktDate();
		learned =  (ended.getTime()-getTime())/1000;
		
	}
	
	public long getLearned() {
		return learned;
	}

	private Date getAktDate() {
		Date outPut = new Date();
		LocalDateTime now = LocalDateTime.now();  
		
		outPut.setYear(now.getYear()-1900);
		outPut.setMonth(now.getMonthValue());
		outPut.setHours(now.getHour());
		outPut.setMinutes(now.getMinute());
		outPut.setSeconds(now.getSecond());
		
		return outPut;
	}

	public String getSubjectName() {
		return subject;
	}
	
	//Methods to safe and load Data
	
	/**
	 * creates a learning phase with the given data
	 * p is irrelevant and just there to make sure the right Constructor is used
	 * @param data
	 */
	public LearningPhase(String data,boolean p) {
		super();
		String[] dataSplitted = data.split(";");
		
		setTime(Long.parseLong(dataSplitted[1]));
		
		long endedTime = Long.parseLong(dataSplitted[2]);
		if(endedTime != -1) {
		ended = new Date();
		ended.setTime(endedTime);}
		
		learned = Long.parseLong(dataSplitted[3]);
		
		subject = dataSplitted[4];
	}
	
	/**
	 * outPuts data of learning Phase in the following order LP:started;ended;learned;subject
	 * when ended is = -1 the phase didn't end yet
	 * @return data of object
	 */
	public String dataToString() {
		long endedTime;
		if(ended != null)
			endedTime = ended.getTime();
		else
			endedTime = -1;
		return "LP;"+getTime()+";"+endedTime+";"+learned+";"+subject;
	}
}
