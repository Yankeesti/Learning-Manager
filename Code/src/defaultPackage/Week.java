package defaultPackage;

import java.util.ArrayList;
import java.util.Date;

public class Week {
	Date startMonday;
	int numberInSemester;
	ArrayList<LearningPhase> learningPhases;
	
	public Week(Date startMonday, int numberInSemester) {
		this.startMonday = startMonday;
		this.numberInSemester = numberInSemester;
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
}
