package defaultPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LearningPhase {

	Date started;
	Date ended;
	long learned;// Time learned in Seconds
	ArrayList<String> notes;
	String subject;
	
	public LearningPhase(String subject) {
		started = getAktDate();
		notes = new ArrayList<String>();
		this.subject = subject;
	}
	
	public LearningPhase(String subject, Date start, Date end) {
		this.subject = subject;
		started = start;
		ended = end;
		learned =  (end.getTime()-start.getTime())/1000;
	}
	
	public void finish() {
		ended = getAktDate();
		learned =  (ended.getTime()-started.getTime())/1000;
		
	}

	public void addNote(String note) {
		notes.add(note);
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
		String[] dataSplitted = data.split(";");
		
		started = new Date();
		started.setTime(Long.parseLong(dataSplitted[1]));
		
		ended = new Date();
		ended.setTime(Long.parseLong(dataSplitted[2]));
		
		learned = Long.parseLong(dataSplitted[3]);
		
		subject = dataSplitted[4];
	}
	
	/**
	 * outPuts data of learning Phase in the following order LP:started;ended;learned;subject
	 * @return data of object
	 */
	public String dataToString() {
		return "LP;"+started.getTime()+";"+ended.getTime()+";"+learned+";"+subject;
	}
}
