package defaultPackage;
import java.util.ArrayList;

public class Subject {
	double finalGrade;
	int weekGoal; //safes the learning goal in Minutes
	int learnedThisWeek; //how much was learnd this weak in minutes
	String subjectName;
	ArrayList<LearningPhase> learningPhases;
	int semester ;
	
	
	Subject(String subjectName , int semester){
		learningPhases = new ArrayList<LearningPhase>();
		this.subjectName = subjectName;
		this.semester = semester;
		
	}
	
	
	//getter and setter
	public String getSubjectName() {return subjectName;}
	public void setWeekGoal(int goal) { weekGoal = goal; }
	public int getSemester() {return semester;}
	public int getWeekGoal() {return weekGoal;}
	
	//Methodes to safe and load Data
	
	/**
	 * 
	 * @param data in this order SUB;finalgrade;weekGoal;learnedThisWeek;subjectName
	 * @param p is irrelevant and just to adresse the right konstructor
	 */
	Subject(String data){
		learningPhases = new ArrayList<LearningPhase>();
		//SUB;finalgrade;weekGoal;learnedThisWeek;subjectName
		String[] dataSplitted = data.split(";");
		finalGrade = Double.parseDouble(dataSplitted[1]);
		weekGoal = Integer.parseInt(dataSplitted[2]);
		learnedThisWeek = Integer.parseInt(dataSplitted[3]);
		subjectName = dataSplitted[4];
		semester = Integer.parseInt(dataSplitted[5]);
		
	}
	
	/**
	 * gives back a String with the following order
	 * SUB;finalgrade;weekGoal;learnedThisWeek;subjectName;semester
	 * 
	 * @return Array of Strings with the data
	 */
	public String dataToString() {
		String outPut = "SUB;"+finalGrade+";"+weekGoal+";"+learnedThisWeek+";"+subjectName+";"+semester;
		return outPut;
	}
	
	/**
	 * Method should just be used when data is loaded
	 * @param p
	 */
	public LearningPhase addLearningPhase(LearningPhase p) {
		learningPhases.add(p);
		return p;
	}



	
}
