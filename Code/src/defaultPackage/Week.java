package defaultPackage;

import java.util.ArrayList;
import java.util.Date;

//the starting Monday is safed as date
public class Week extends Date{
	
	int numberInSemester;
	ArrayList<LearningPhase> learningPhases;
	
	public Week(int year,int month, int date, int numberInSemester) {
		this(getMonday(year, month, date),numberInSemester);
	}
	
	public Week(Date startMonday, int numberInSemester) {
		super (startMonday.getYear(),startMonday.getMonth(),startMonday.getDate());
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
			if(learningPhases.get(i).getSubject() == subject) {
				outPut += learningPhases.get(i).getLearned();
			}
		}
		return outPut;
	}

	public void addLearningPhase(LearningPhase currentLearningPhase) {
		learningPhases.add(currentLearningPhase);
	}
	
	/**
	 * 
	 * @return 0 when Day is included in this week and when not the numbers of Weeks from here where p is included
	 */
	public int dayIncluded(Date p) {
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
	 * @param tag
	 * @return The Monday of the week where tag is included
	 */
	private static Date getMonday(Date tag) {
		//Montag finden
				Date Monday;
				int day = tag.getDay();
				
				//Da in der Date Klasse Sonntag an stelle 0 ist rücken wir hiermit den Montag auf stelle null
				if(day == 0)// wenn sonntag = 6
					day = 6;
				else //initial für tag um eins nach hinten schieben um montag an 0 zu haben
					day -= 1;
				
				Monday = new Date(tag.getYear(),tag.getMonth(),tag.getDate()-day);
				return Monday;
	}
	
	private static Date getMonday(int year,int month, int date) {
		Date temp = new Date(year,month,date);
		return getMonday(temp);
	}
}
