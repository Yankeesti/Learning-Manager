package defaultPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LearningPhase extends TimePeriod{
	long learned;// Time learned in Seconds
	String subject;
	long paused; // Time paused in Seconds
	TimePeriod currentBreak;
	
	public LearningPhase(String subject) {
		super(getAktDate());
		this.subject = subject;
	}
	
	public LearningPhase(String subject, Date start, Date end) {
		super(start,end);
		this.subject = subject;
		learned =  (end.getTime()-start.getTime())/1000;
	}
	
	public void finish() {
		setEndTime(getAktDate());
		learned =  getDiffrence()/1000;
		
	}
	
	public long getLearned() {
		return learned;
	}

	public boolean startBreak() {
		if(currentBreak != null) {
			currentBreak = new TimePeriod(getAktDate());
		return true;}
		return false;
	}
	
	public boolean endBreak() {
		if(currentBreak != null) {
			currentBreak.setEndTime(getAktDate());
		paused += currentBreak.getDiffrence()/1000;
		currentBreak = null;
		return true;}
		return false;
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
		super(data);
		String[] dataSplitted = data.split(";");
		learned = Long.parseLong(dataSplitted[3]);
		subject = dataSplitted[4];
		paused = Long.parseLong(dataSplitted[5]);
		if(getEndDate() == null) {
			if(dataSplitted[6] != "-1") {
				currentBreak = new TimePeriod(Long.parseLong(dataSplitted[6]));
			}
		}
		
	}
	
	/**
	 * outPuts data of learning Phase in the following order LP:started;ended;learned;subject;paused;pausStarted
	 * when ended is = -1 the phase didn't end yet
	 * @return data of object
	 */
	public String dataToString() {
		long endedTime;
		long startedPaus;
		if(endDate != null)
			endedTime = endDate.getTime();
		else
			endedTime = -1;
		
		if(currentBreak == null) {
			startedPaus = -1;
		}else
			startedPaus = currentBreak.getTime();
		if(endedTime == -1)
		return "LP;"+getTime()+";"+endedTime+";"+learned+";"+subject+";"+paused+";"+startedPaus; // Learning Phase is not ended yet --> Break is possible
		return "LP;"+getTime()+";"+endedTime+";"+learned+";"+subject+";"+paused; //Learning Phase already ended --> no possible Break
		
	}

	public boolean ended() {
		return (endDate != null);
	}
}
