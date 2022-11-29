package defaultPackage;

import java.util.ArrayList;
import java.util.Date;

//the starting Monday is safed as date
public class Week extends TimePeriod{
	
	int numberInSemester;
	ArrayList<LearningPhase> learningPhases;
	
	
	
	
	public Week(int year,int month, int date, int numberInSemester) {
		this(getMonday(year, month, date),numberInSemester);
	}
	
	public Week(Date startMonday, int numberInSemester) {
		super (startMonday.getYear(),startMonday.getMonth(),startMonday.getDate(),startMonday.getYear(),startMonday.getMonth(),startMonday.getDate()+7);
		setEndTime(getEndDate().getTime()-1);
		this.numberInSemester = numberInSemester;
		learningPhases = new ArrayList<LearningPhase>();
	}
	
	
	/**
	 * 
	 * @param subject
	 * @return how many seceonds were learned in this week for subject
	 */
	public long learnedFor(String subject){
		long outPut =0;
		for(int i = 0; i<learningPhases.size();i++) {
			if(learningPhases.get(i).getSubjectName().equals(subject)) {
				outPut += learningPhases.get(i).getLearned();
			}
		}
		return outPut;
	}

	public void addLearningPhase(LearningPhase learningPhase) {
		learningPhases.add(learningPhase);
	}
	
	
	/**
	 * Compares the Date p to the Week
	 * @return the Number of Weeks from this Week where p is Included
	 */
	public int compareTo(Date p) {
		p = getMonday(p);
		//same Week
		if(equals(p)) {
			return 0;
		}
		
		//Hours between booth days when Negativ p is before this week
		long diffrence = p.getTime()/3600000 - getTime()/3600000;
		
		int outPut =0;
		Date temp = new Date(getYear(),getMonth(),getDate());
		
		//p is after the start day of the Week
		if(diffrence > 0) {
			while(temp.compareTo(p)<0) {
				long timeOld = temp.getTime()/3600000; //safe old date
				temp.setDate(temp.getDate()+7); // one week further
				diffrence -= temp.getTime()/3600000 - timeOld;
				outPut ++;
			}
			//p is before start day of week
		}else {
			while(temp.compareTo(p)>0) {
				long timeOld = temp.getTime()/3600000; //safe old date
				temp.setDate(temp.getDate()-7); // one week further
				diffrence -= temp.getTime()/3600000 - timeOld;
				outPut --;
			}
		}
		
		
		return outPut;
	}
	
	/**
	 * returns runningLearningPhase
	 * @return if present running Learning Phase it will be returned else null
	 */
	public LearningPhase getRunningLearningPhase() {
		for(int i = 0; i<learningPhases.size();i++) {
			if(!learningPhases.get(i).ended()) {
				return learningPhases.get(i);
			}
		}
		return null;
	}
	
	private static Date getMonday(int year,int month, int date) {
		Date temp = new Date(year,month,date);
		return getMonday(temp);
	}
	
	//Methods to safe and load Data
	
	/**
	 * returns a String Array with the data of the Week in the following way
	 * [0] = "WEB";starting Monday;numberInSemester;Ammount of LearningPhases
	 * [1] = LearningPhase
	 * [2] = LearningPhase
	 * [3] = LearningPhase
	 * ...
	 * @return String Array with Data
	 */
	public String[] dataToString() {
		String[] outPut = new String[learningPhases.size()+1];
		outPut[0] = "WEB"+";"+getTime()+";"+numberInSemester+";"+learningPhases.size();
		for(int i = 0; i<learningPhases.size();i++) {
			outPut[i+1] = learningPhases.get(i).dataToString();
		}
		return outPut;
	}
	
	public Week(String[] data) {
		super(data[0]);
		Date endDate = new Date(getYear(),getMonth(),getDate()+7,0,0,-1);
		setEndTime(endDate);
		String[] dataSplitted = data[0].split(";");
		numberInSemester = Integer.parseInt(dataSplitted[2]);
		learningPhases = new ArrayList<LearningPhase>();
		for(int i = 1; i<data.length;i++) {
			learningPhases.add(new LearningPhase(data[i], false));
		}
		
		
	}
	
	public LearningPhase[] getLearningPhases() {
		LearningPhase[] outPut = new LearningPhase[learningPhases.size()];
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = learningPhases.get(i);
		}
		return outPut;
	}

	
}
