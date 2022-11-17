package defaultPackage;

import java.util.ArrayList;

public class Interface {

	ArrayList<Semester> semesters;
	ArrayList<Subject> subjects;
	Week currentWeek;
	ArrayList<String> subjectNames;
	
	//there is only one learning phase possible at the time
	LearningPhase currentLearningPhase;  
	
	
	/**
	 * starts a learning phase if subject is a existent subject 
	 * an no learning phase is started yet.
	 * @param subject
	 * @return true when learning Phase can be started (no other learning phase is started and subject is a existent Subject)
	 */
	public boolean startLearningPhase(String subject) {
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
		
		currentLearningPhase = currentSubject.startLearningPhase();
		
		return true;
	}
}
