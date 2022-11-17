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
	
	
	//Getter and Setter
	
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
	
	/**
	 * adds p to subjects
	 * @param p
	 */
	public void addSubject(Subject p) {
		subjects.add(p);
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
	
	
}
