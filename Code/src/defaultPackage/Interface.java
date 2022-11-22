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

	ArrayList<Semester> semesters;
	ArrayList<Subject> subjects;
	Week currentWeek;
	Semester currentSemester;
	
	//there is only one learning phase possible at the time
	LearningPhase currentLearningPhase;  
	
	
	Interface(){
		semesters = new ArrayList<Semester>();
		subjects = new ArrayList<Subject>();
		upDateWeek();
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
		Semester currentSemester = findSemester(semester);
		
		
		if(currentSemester != null) {
			Subject newSubject = new Subject(subjectName,semester);
			currentSemester.addSubject(newSubject);
			subjects.add(newSubject);
		}else
			System.err.println("Das angegebene Semester existiert nicht");
	}
	
	public void setWeekGoal(int semester, String subjectName, int weekGoal) {
		Semester currentSemester = findSemester(semester);
		
		if(currentSemester != null) {
			if(!currentSemester.setWeekGoal(subjectName, weekGoal)) 
				System.err.println("Das angegebene Fach existiert nicht");
		}else {
			System.err.println("Das angegebene Semester existiert nicht");
		}
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
		for(int i = 0; i<subjects.size();i++) {
			if(subjects.get(i).getSubjectName().equals(subject));
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
		((Week) currentWeek).addLearningPhase(currentLearningPhase);
		return true;
	}
	
	/**
	 *  When a Learning Phase is started it gets fineshed
	 * @return true when there is a curent Learning Phase to be finished and false when not
	 */
	public boolean finishLearningPhase() {
		if(currentLearningPhase == null) {
			return false;
		}
		currentLearningPhase.finish();
		currentLearningPhase = null;
		return true;
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
			if(currentWeek.dayIncluded(aktDate) == 0) {
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
	 * Returns all Subject of the Current Semester
	 * @return Array of all Subjects in the Current Semester
	 */
	public Subject[] getCurrentSubjects() {
		return currentSemester.getSubjects();
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
	
	private Semester findSemester(int semester) {
		for(int i = 0; i < semesters.size();i++) {
			if(semesters.get(i).getSemester() == semester)
			{
				return semesters.get(i);
			}
		}
		return null;
	}
	
	public Subject[] getSubjects() {
		Subject[] outPut = new Subject[subjects.size()];
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = subjects.get(i);
		}
		return outPut;
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
			Subject[] subjectsOfSemester = semesters.get(i).getSubjects();
			for(int subjectIndex = 0; subjectIndex < subjectsOfSemester.length;subjectIndex++) {
				subjects.add(subjectsOfSemester[subjectIndex]);
			}
		}
		upDateWeek();
	}
	
	//Methodes to test
	
	public void addLearningPhase(int semester, String subjectName, Date start, Date end) {
		Semester currentSemester = findSemester(semester);
		currentSemester.addLearningPhase(subjectName, start, end);
	}

	
	
}
