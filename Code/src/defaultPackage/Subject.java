package defaultPackage;
import java.util.ArrayList;

public class Subject {
	double finalGrade;
	int weekGoal; //safes the learning goal in Minutes
	int learnedThisWeek; //how much was learnd this weak in minutes
	String subjectName;
	ArrayList<Homework> homework;
	ArrayList<LearningPhase> learningPhases;
	ArrayList<Appointment> appointments;
	LearningPhase aktLPhase;
	
	
	Subject(String subjectName){
		homework = new ArrayList<Homework>();
		learningPhases = new ArrayList<LearningPhase>();
		appointments = new ArrayList<Appointment>();
		aktLPhase = null;
		this.subjectName = subjectName;
		
	}
	
	
	
	public LearningPhase startLearningPhase() {
		aktLPhase = new LearningPhase(subjectName);
		learningPhases.add(aktLPhase);
		return aktLPhase;
	}
	/**
	 * 
	 * @return true when AktLPhase is not null
	 */
	public boolean endLearningPhase() {
		if(aktLPhase == null)return false;
		aktLPhase.finish();
		return true;
	}
	
	/**
	 * Adds a Note to aktLPhase 
	 * @return true when AktLPhase is not null
	 */
	public boolean addNote(String note) {
		if(aktLPhase == null)return false;
		aktLPhase.addNote(note);
		return true;
	}
	
	/**
	 * adds a Appointment to the Subject
	 * @param a
	 */
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	public Appointment[] getAppointments() {
		Appointment[] output = new Appointment[appointments.size()];
		
		for(int i = 0; i<output.length;i++) {
			output[i] = appointments.get(i);
		}
		return output;
	}
	/**
	 * updates the learned Time
	 */
	private void upDateLearned() {
		// TODO Auto-generated method stub
		
	}
	
	
	//getter and setter
	public String getSubjectName() {return subjectName;}
	public void setWeekGoal(int goal) { weekGoal = goal; }
	
	//Methodes to safe and load Data
	
	/**
	 * 
	 * @param data in this order SUB;finalgrade;weekGoal;learnedThisWeek;subjectName
	 * @param p is irrelevant and just to adresse the right konstructor
	 */
	Subject(String data,boolean p){
		homework = new ArrayList<Homework>();
		learningPhases = new ArrayList<LearningPhase>();
		appointments = new ArrayList<Appointment>();
		aktLPhase = null;
		//SUB;finalgrade;weekGoal;learnedThisWeek;subjectName
		String[] dataSplitted = data.split(";");
		finalGrade = Double.parseDouble(dataSplitted[1]);
		weekGoal = Integer.parseInt(dataSplitted[2]);
		learnedThisWeek = Integer.parseInt(dataSplitted[3]);
		subjectName = dataSplitted[4];
		
	}
	
	/**
	 * gives back a String with the following order
	 * SUB;finalgrade;weekGoal;learnedThisWeek;subjectName
	 * 
	 * @return Array of Strings with the data
	 */
	public String DataToString() {
		String outPut = "SUB;"+finalGrade+";"+weekGoal+";"+learnedThisWeek+";"+subjectName;
		return outPut;
	}
	
	/**
	 * Method should just be used when data is loaded
	 * @param p
	 */
	public LearningPhase addLearningPhase(LearningPhase p) {
		learningPhases.add(p);
		upDateLearned();
		return p;
	}



	
}
