package defaultPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LearningPhase extends TimePeriod{
	long learned;// Time learned in Seconds
	String subject;
	
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
	}
	
	/**
	 * outPuts data of learning Phase in the following order LP:started;ended;learned;subject
	 * when ended is = -1 the phase didn't end yet
	 * @return data of object
	 */
	public String dataToString() {
		long endedTime;
		if(endDate != null)
			endedTime = endDate.getTime();
		else
			endedTime = -1;
		return "LP;"+getTime()+";"+endedTime+";"+learned+";"+subject;
	}

	public boolean ended() {
		return (endDate != null);
	}
}
