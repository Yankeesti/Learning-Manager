package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Interface {

	private ArrayList<Semester> semesters;
	private ArrayList<Subject> subjects;
	private Week currentWeek;
	private Semester currentSemester;
	private File currentFile;
	
	//there is only one learning phase possible at the time
	private LearningPhase currentLearningPhase;  
	
	
	Interface(){
		semesters = new ArrayList<Semester>();
		subjects = new ArrayList<Subject>();
		upDateWeek();
	}
	
	/**
	 * starts a learning phase if subject is a existent subject 
	 * an no learning phase is started yet.
	 * @param subject
	 * @return true when learning Phase can be started (no other learning phase is started and subject is a existent Subject)
	 */
	public boolean startLearningPhase(String subjectName) {
		upDateWeek();
		//Checking weather learning phase can be started
		if(currentLearningPhase != null)
			//when there is already a LearningPhase started
			return false; 
		
		//Checking if subject is a existent subject
		Subject subject = null;
		for(int i = 0; i<subjects.size();i++) {
			if(subjects.get(i).getSubjectName().equals(subjectName));
			{subject = subjects.get(i);
			break;
			}
		}
		//returns false when there is no subject with the Name subject Name
		if(subject == null)
			return false;
		
		currentLearningPhase = new LearningPhase(subjectName);
		
		//add LearningPhase to Semester Week and Subject
		
		subject.addLearningPhase(currentLearningPhase);
		
		getSemester(subject.getSemester()).addLearningPhase(currentLearningPhase);
		
		return true;
	}
	
	/**
	 *  When a Learning Phase is started it gets fineshed
	 * @return the time learned in Seconds an -1 if there isnt a learning Phase to finish
	 */
	public long finishLearningPhase() {
		if(currentLearningPhase == null) {
			return -1;
		}
		currentLearningPhase.finish();
		long outPut = currentLearningPhase.getDiffrence()/1000;
		
		currentLearningPhase = null;
		return outPut;
	}
	
	/**
	 * starts a Break in the current learningPhase
	 * @return true if action worked and false if not
	 */
	public boolean startBreak() {
		if(currentLearningPhase == null) {
			return false;
		}
		return currentLearningPhase.startBreak();
	}
	
	/**
	 * end LearningPhase Break
	 * @return time of break in second if there is no break to end -1
	 */
	public long endBreak() {
		if(currentLearningPhase == null) {
			return -1;
		}
		return currentLearningPhase.endBreak();
	}
	
	//Semester
	public void addSemester(int semester, Date semesterStart, Date semesterEnd) {
		Semester newSemester = new Semester(semester, semesterStart, semesterEnd); 
		semesters.add(newSemester);
	}
	
	//Semester
		public void addSemester(int semester,int startYear, int startMonth, int startDate,int endYear,int endMonth,int endDate) {
			Date start = new Date(startYear-1900, startMonth, startDate);
			Date end = new Date(endYear, endMonth, endDate);
			addSemester(semester,start,end);
		}
	
	//Subject
	/**
	 * Adds a new Subject to the given Semester
	 * @param semester - the semester number
	 * @param subjectName - the name of the subject
	 */
	public void addSubject(int semester, String subjectName) {
		Semester currentSemester = getSemester(semester);
		
		
		if(currentSemester != null) {
			Subject newSubject = new Subject(subjectName,semester);
			currentSemester.addSubject(newSubject);
			subjects.add(newSubject);
		}else
			System.err.println("Das angegebene Semester existiert nicht");
	}
	
	public void setWeekGoal(int semester, String subjectName, int weekGoal) {
		Semester currentSemester = getSemester(semester);
		
		if(currentSemester != null) {
			if(!currentSemester.setWeekGoal(subjectName, weekGoal)) 
				System.err.println("Das angegebene Fach existiert nicht");
		}else {
			System.err.println("Das angegebene Semester existiert nicht");
		}
	}
	
	/**
	 * 	Calculates the progress and gives out a array of String with the following order
	 * 	[0][0] = subject Name
	 * 	[0][1] = week Goal
	 * 	[0][2] = learned this Week
	 * 	
	 * 	[1][0] = subject Name
	 * 	[1][1] = week Goal
	 * 	[1][2] = learned this Week
	 * ...
	 * @return week Progress
	 */
	public String[][] getWeekProgress() {
		upDateWeek();
		Subject[] subjectsInSemester = currentSemester.getSubjects();
		
		String[][] outPut = new String[subjectsInSemester.length][3];
		
		for(int i =0 ; i<outPut.length;i++) {
			outPut[i][0] = subjectsInSemester[i].getSubjectName();
			outPut[i][1] = ""+subjectsInSemester[i].getWeekGoal();
			outPut[i][2] = ""+currentWeek.learnedFor(outPut[i][0])/60;
		}
		return outPut;
	}
	
	public Subject[] getSubjects() {
		Subject[] outPut = new Subject[subjects.size()];
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = subjects.get(i);
		}
		return outPut;
	}
	
	/**
	 * Returns all Subject of the Current Semester
	 * @return Array of all Subjects in the Current Semester
	 */
	public Subject[] getCurrentSubjects() {
		return currentSemester.getSubjects();
	}
	/**
	 * 
	 * @return true when there is a running learningPhase and false if not
	 */
	public boolean runningLearningPhase() {
		return currentLearningPhase != null;
	}
	

	
	
	
	/**
	 * Updates the current Week and Semester
	 * when there isn't a Week that includes the current date current week stays null
	 */
	private void upDateWeek() {
		Date aktDate = getAktDate();
		boolean weekRight = false;
		//test if new Week has begun
		if(currentWeek != null) {
			if(currentWeek.compareTo(aktDate) == 0) {
				weekRight = true;
			}
		}else 
		//find current week
		if(!weekRight)
			for(int i = 0; i<semesters.size();i++) {
				Week temp = semesters.get(i).getWeek(aktDate);
				if(temp == null)
					continue;
				currentWeek = temp;
				currentSemester = semesters.get(i);
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
	
	public Semester[] getSemesters() {
		Semester[] outPut = new Semester[semesters.size()];
		
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = semesters.get(i);
		}
		return outPut;
	}

	public LearningPhase getCurrentLearningPhase() {
		return currentLearningPhase;
	}
	
	public File getCurrentFile() {
		return currentFile;
	}
	
	//Methods to safe and load Data
	/**
	 * outPuts the data thats in the Object in the following way
	 * [0][0][0][0] = amount of Semesters
	 * [1] = Semester
	 * [2] = Semester
	 * [3] = Semester
	 * ...
	 * @return Data thats safed in the Object
	 */
	public String[][][][] dataToString(){
		String [][][][] outPut = new String[semesters.size()+1][][][];
		outPut[0] = new String[1][1][1];
		outPut[0][0][0][0] = ""+semesters.size();
		for(int i = 1; i< outPut.length;i++) {
			outPut[i] = semesters.get(i-1).dataToString();
		}
		return outPut;
	}
	
	/**
	 * safes the all data in file p
	 * @param p - file to safe data in
	 * @return true when data could been stored
	 */
	public boolean safeData(File p) {
	StringBuffer dataString = new StringBuffer();
		boolean output = false;
		String[][][][] data = dataToString();
		for(String[][][] a : data) {
			for(String[][] b :a ) {
				for(String[] c: b) {
					for(String d : c) {
						dataString.append(d+"\n");
					}
				}
			}
		}
		
		OutputStream stream = null;
		
		try {
			stream = new FileOutputStream(p);
			stream.write(dataString.toString().getBytes());
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	/**
	 * creates a Interface with the data in data
	 * @param data - file where data is stored
	 */
	public Interface(File data) {
		currentFile = data;
		semesters = new ArrayList<Semester>();
		subjects = new ArrayList<Subject>();
		//load data in to a String Array List
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String s;
			while((s = reader.readLine()) != null) {
				dataList.add(s);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int amountOfSemesters = Integer.parseInt(dataList.get(0));
		String[][][][] dataArray = new String[amountOfSemesters][][][];
		
		int lineIndex = 1;
		for(int semesterIndex = 0;semesterIndex<amountOfSemesters;semesterIndex++) {
			
			//write Semester Data in first Line of Array
			dataArray[semesterIndex] = new String[3][1][1];
			dataArray[semesterIndex][0][0][0] = dataList.get(lineIndex);
			String[] temp = dataArray[semesterIndex][0][0][0].split(";");
			dataArray[semesterIndex][1][0] 	= new String[Integer.parseInt(temp[4])];
			//Subjects
			lineIndex++;
			for(int subjectIndex = 0; subjectIndex <Integer.parseInt(temp[4]);subjectIndex++ ) {
				dataArray[semesterIndex][1][0][subjectIndex] = dataList.get(lineIndex);
				lineIndex++;
			}
			
			//Weeks
			dataArray[semesterIndex][2] 	= new String[Integer.parseInt(temp[5])][];
			for(int weekIndex = 0; weekIndex<dataArray[semesterIndex][2].length;weekIndex++) {
				String[] weekData = dataList.get(lineIndex).split(";");
				dataArray[semesterIndex][2][weekIndex] = new String[Integer.parseInt(weekData[3])+1];
				dataArray[semesterIndex][2][weekIndex][0] = dataList.get(lineIndex);
				lineIndex++;
				//bring learningPhase in to week
				for(int learningPhaseIndex = 1; learningPhaseIndex<dataArray[semesterIndex][2][weekIndex].length;learningPhaseIndex++) {
					dataArray[semesterIndex][2][weekIndex][learningPhaseIndex] = dataList.get(lineIndex);
					lineIndex++;
				}
			}
			}
		for(int i = 0; i<amountOfSemesters;i++) {
			semesters.add(new Semester(dataArray[i]));
			//add running learningPhase
			if(currentLearningPhase == null) {
				currentLearningPhase = semesters.get(i).getRunningLearningPhase();
			}
			Subject[] subjectsOfSemester = semesters.get(i).getSubjects();
			for(int subjectIndex = 0; subjectIndex < subjectsOfSemester.length;subjectIndex++) {
				subjects.add(subjectsOfSemester[subjectIndex]);
			}
		}
		upDateWeek();
	}
	
	private Semester getSemester(int semester) {
		for(int i = 0; i< semesters.size(); i++) {
			if(semesters.get(i).getSemester() == semester)
				return semesters.get(i);
		}
		return null;
	}
	
	//Methodes to test
	
	public void addLearningPhase(int semester, String subjectName, Date start, Date end) {
		Semester currentSemester = getSemester(semester);
		currentSemester.addLearningPhase(subjectName, start, end);
	}

	

	
	
}
