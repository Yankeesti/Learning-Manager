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

	public String getSubject() {
		return subject;
	}
	
}
