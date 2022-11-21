package defaultPackage;
import java.util.ArrayList;
import java.util.Date;

public class Semester extends Date{
	int semester;
	ArrayList<Subject> subjects;
	Date semesterEnd;
	Week weeks[];
	

	
	/**
	 * @param semester
	 * @param semesterStart
	 * @param semesterEnd
	 * 
	 * Erstellt ein Object der Klasse Semester in welchen gespeicher wird 
	 * um welhces Semester es sich handelt und jede Woche für das Semester 
	 * angelegt wird
	 */
	public Semester(int semester, Date semesterStart, Date semesterEnd) {
		super();
		setTime(semesterStart.getTime());
		this.semester = semester;
		this.semesterEnd = semesterEnd;
		Date startMonday = getMonday(semesterStart);
		weeks = new Week[berechnungWochenAnzahl(semesterStart, semesterEnd)];
		for(int i = 0; i<weeks.length; i++) {
			weeks[i] = new Week(startMonday,i);
			startMonday.setDate(startMonday.getDate()+7);
		}
		subjects = new ArrayList<Subject>();
	}
	
	public Semester(int semester,Subject[] subjects, Date semesterStart, Date semesterEnd) {
		new Semester(semester,semesterStart,semesterEnd);
		for(Subject i: subjects) {
			this.subjects.add(i);
		}
	}
	
	/**
	 *  Sets the Week of the Subject
	 * @param subjectName - name of the subject the weekGoal needs to be set
	 * @param weekGoal - number of minutes that should be learned in one Week
	 * @return true when weekGoal could be set and false when not
	 */
	public boolean setWeekGoal(String subjectName,int weekGoal) {
		Subject currentSubject = findSubject(subjectName);
		if(currentSubject != null) {
		currentSubject.setWeekGoal(weekGoal);
		return true;}
		return false;
	}
	
	
	
	//Getter/Setter
	
	/**
	 * adds p to subjects
	 * @param p
	 */
	public void addSubject(Subject p) {
		subjects.add(p);
	}
	
	public int getSemester() {
		return semester;
	}
	
	/**
	 * @param Date to find Week where it belongs to
	 * @return the Week where p is in and null when the week isn't in this Semester
	 */
	public Week getWeek(Date p) {
		int weeksAppart = weeks[0].dayIncluded(p);
		if(weeksAppart>= 0 && weeksAppart < weeks.length) { //Date is in this Semester
			return weeks[weeksAppart];
		}
		return null;
	}
	
	/**
	 * 
	 * @return all subjects in a array
	 */
	public Subject[] getSubjects() {
		Subject[] outPut = new Subject[subjects.size()];
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = subjects.get(i);
		}
		return outPut;
	}
	
	//private Methods
	
	/**
	 * @param tag
	 * @return denn Montag welcher in der gleichen woche liegt wie tag
	 */
	private Date getMonday(Date tag) {
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
	
	/**
	 * @param semesterStart
	 * @param semesterEnd
	 * @return anzahl ganzer Wochen im angegeben Zeitraum
	 */
	private int berechnungWochenAnzahl(Date semesterStart, Date semesterEnd) {
		Date startMonday = getMonday(semesterStart);
		Date endMonday = getMonday(semesterEnd);
		int anzahl = 0;
		
		while(startMonday.compareTo(endMonday)<= 0) {
			startMonday.setDate(startMonday.getDate()+7);
			anzahl ++;
		}
		return anzahl;
	}
	
	/**
	 * finds the subject with the same name
	 * @param subjectName
	 * @return subject if existent and when not null
	 */
	private Subject findSubject(String subjectName) {
		for(int i = 0; i< subjects.size();i++) {
			if(subjects.get(i).getSubjectName().equals(subjectName))
				return subjects.get(i);
		}
		return null;
	}
	
	//Methods to safe and load Data
	
	/**
	 * returns a 3 dimensional Array of the Data in this Object
	 * in the following order
	 * [0][0][0] = SEB;semester;semesterStart;semesterEnd;amount of Subjects;amount of Weeks
	 * [1][0][0] = Subject
	 * [1][0][1] = Subject
	 * [1][0][2] = Subject
	 * [1][0][3] = Subject
	 * ...
	 * [2][0] = Week
	 * [2][2] = Week
	 * [2][3] = Week
	 * 
	 * @return
	 */
	public String[][][] dataToString() {
		String[][][] outPut = new String[3][][];
		outPut[0] = new String[1][1];
		
		outPut[0][0][0] = "SEB"+";"+semester+";"+getTime()+";"+semesterEnd.getTime()+";"+subjects.size()+";"+weeks.length;
		
		
		//Subjects
		outPut[1] = new String[1][subjects.size()];
		for(int i = 0 ; i<subjects.size();i++) {
			outPut[1][0][i] = subjects.get(i).DataToString();
		}
		
		//Weeks
		outPut[2] = new String[weeks.length][];
		
		for(int i = 0; i<weeks.length;i++) {
			outPut[2][i] = weeks[i].dataToString();
		}
		return outPut;
	}
	
	/**
	 * returns a 3 dimensional Array of the Data in this Object
	 * in the following order
	 * [0][0][0] = SEB;semester;semesterStart;semesterEnd;amount of Subjects;amount of Weeks
	 * [1][0][0] = Subject
	 * [1][0][1] = Subject
	 * [1][0][2] = Subject
	 * [1][0][3] = Subject
	 * ...
	 * [2][0] = Week
	 * [2][2] = Week
	 * [2][3] = Week
	 * 
	 * @return
	 */
	public Semester(String[][][] data) {
		super();
		String[] dataSplitted = data[0][0][0].split(";");
		semester = Integer.parseInt(dataSplitted[1]);
		setTime(Long.parseLong(dataSplitted[2]));
		semesterEnd = new Date();
		semesterEnd.setTime(Long.parseLong(dataSplitted[2]));
		
		//Creating subjects
		subjects = new ArrayList<Subject>();
		for(int i = 0; i< data[1][0].length;i++) {
			subjects.add(new Subject(data[1][0][i], false));
		}
		
		//Creating Weeks
		weeks = new Week[data[2].length];
		
		for(int i = 0; i< weeks.length;i++) {
			weeks[i] = new Week(data[2][i]);
			//adding LearningPhases of this week to the subjects
			LearningPhase[] temp = weeks[i].getLearningPhases();
			for(int li = 0 ; li<temp.length;li++) {
				for(int si = 0; si<subjects.size();si++) {
					if(subjects.get(si).getSubjectName().equals(temp[li].getSubjectName())) {
						subjects.get(si).addLearningPhase(temp[li]);
						break;
					}
				}
			}
		}
		
	}
	
	//Methodes for testing
	public void addLearningPhase(String subjectName, Date start, Date end) {
		Subject temp = findSubject(subjectName);
		LearningPhase tempL = new LearningPhase(subjectName, start, end);
		temp.addLearningPhase(tempL);
		getWeek(start).addLearningPhase(tempL);
	}
	
}
