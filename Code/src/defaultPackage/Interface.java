package defaultPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Interface {

	ArrayList<Semester> semesters;
	ArrayList<Subject> subjects;
	Week currentWeek;
	ArrayList<String> subjectNames;
	
	//there is only one learning phase possible at the time
	LearningPhase currentLearningPhase;  
	
	
	Interface(){
		semesters = new ArrayList<Semester>();
		subjects = new ArrayList<Subject>();
		subjectNames = new ArrayList<String>();
		upDateWeek();
	}
	
	/**
	 * starts a learning phase if subject is a existent subject 
	 * an no learning phase is started yet.
	 * @param subject
	 * @return true when learning Phase can be started (no other learning phase is started and subject is a existent Subject)
	 */
	public boolean startLearningPhase(String subject) {
		upDateWeek();
		//Checking weather learning phase can be started
		if(currentLearningPhase != null)
			//when there is already a LearningPhase started
			return false; 
		
		//Checking if subject is a existent subject
		boolean subjectExcistent = false;
		for(int i = 0; i<subjectNames.size();i++) {
			if(subjectNames.get(i).equals(subject));
			{subjectExcistent = true;
			break;
			}
		}
		//When subject isn't a Subject that is already existent
		if(!subjectExcistent)
			return false;
		
		Subject currentSubject = null;
		//starting learningPhase
		for(int i = 0; i< subjects.size();i++) {
			if(subjects.get(i).getSubjectName().equals(subject)) {
				currentSubject = subjects.get(i);
				break;}
		}
		
		//start learning phase and safe it
		currentLearningPhase = currentSubject.startLearningPhase();
		//add learning phase to Week
		currentWeek.addLearningPhase(currentLearningPhase);
		return true;
	}
	
	private void upDateWeek() {
		boolean weekRight = false;
		//test if new Week has begun
		if(currentWeek != null) {
			if(currentWeek.dayIncluded(getAktDate()) == 0) {
				weekRight = true;
			}
		}
		
		//find current week
		if(!weekRight)
			for(int i = 0; i<semesters.size();i++) {
				Week temp = semesters.get(i).getWeek(currentWeek);
				if(temp == null)
					continue;
				currentWeek = temp;
			}
	}
	/**
	 * 
	 * @return the current Date
	 */
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
}
