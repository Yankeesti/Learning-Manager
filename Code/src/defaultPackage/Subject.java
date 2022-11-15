package defaultPackage;
import java.util.ArrayList;

public class Subject {
	double finalGrade;
	int weekGoal; //safes the learning goal in Minutes
	int learndThisWeek; //how much was learnd this weak in minutes
	ArrayList<Homework> homework;
	ArrayList<LearningPhase> learningPhases;
	ArrayList<Appointment> appointments;
	LearningPhase aktLPhase;
	String subjectName;
	Subject(String subjectName){
		homework = new ArrayList<Homework>();
		learningPhases = new ArrayList<LearningPhase>();
		appointments = new ArrayList<Appointment>();
		aktLPhase = null;
		this.subjectName = subjectName;
	}
	
	public void startLearningPhase() {
		aktLPhase = new LearningPhase(subjectName);
		learningPhases.add(aktLPhase);
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
	
	//getter and setter
	public void setWeekGoal(int goal) { weekGoal = goal; }
}
