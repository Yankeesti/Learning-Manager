package defaultPackage;

import java.util.Date;

public class Homework {
	Date deadline;
	String task;
	boolean done;
	StringBuffer notes;
	
	public Homework(Date deadline, String task) {
		this.deadline = deadline;
		this.task = task;
		done = false;
	}
	
	public void addNote(String note) {
		notes.append("\n" + note);
	}
	public void finish() {
		done = true;
	}
}
