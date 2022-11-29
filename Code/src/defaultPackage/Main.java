package defaultPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
	static File folder1,file1;
	static Interface study;
	public static void main(String[] args) {
		loadFiles();
		study = new Interface(file1);

		end:
		while(true) {
			System.out.println("\nwas möchten sie tuen\n");
			System.out.println("1 : Lern Phase starten");
			System.out.println("2 : Lern Phase beenden");
			System.out.println("3 : neues Semester Hinzufuegen");
			System.out.println("4 : neues Fach Hinzufuegen");
			System.out.println("5 : Wochen Ziel für ein Fach ändern");
			System.out.println("6 : Zeige Wochen Fortschritt");
			System.out.println("7 : Daten Speichern");
			System.out.println("8 : Program abrechen");
			
			
			int auswahl = abfrageInt("",1,8);
			switch(auswahl) {
			case 1:
				startLearningPhase();
				break;
			case 2:
				finishLearningPhase();
				break;
			case 3:
				addNewSemester();
				break;
			case 4:
				addNewSubject();
				break;
			case 5:
				changeWeekGoal();
				break;
			case 6:
				showWeekProgress();
				break;
			case 7:
				safeData();
				break;
			case 8:
				break end;
			
			
			}
		}
	
	}
	

	
	
	//Methods to use interface
	
	public static void startLearningPhase() {
		System.out.println("\nfür welches Fach möchten Sie lernen?\n");
		Subject[] subjectOptions = study.getCurrentSubjects();
		for(int i = 0 ; i<subjectOptions.length;i++) {
			System.out.println(i+1+" : "+ subjectOptions[i].getSubjectName());
		}
		int selection = abfrageInt("", 1, subjectOptions.length);
		study.startLearningPhase(subjectOptions[selection-1].getSubjectName());
	}
	
	private static void finishLearningPhase() {
		if(study.finishLearningPhase()) {
			System.out.println("\nLernPhase wurde beendet");
		}else {
			System.out.println("\nes gibt keine Lern Phase zum beenden");
		}
			
	}
	
	private static void addNewSemester() {
		System.out.println("Geben sie das Start-Datum des Semesters in Folgender Syntax an: Jahr-Monat-Tag");
		 Date start = null;
		 Date end=null;
		boolean inputRight = false;
		while(!inputRight) {
			 String input= abfrageString("");
			 String[] inputSplitted = input.split("-");
			
			 try {
				 start = new Date(Integer.parseInt(inputSplitted[0])-1900,Integer.parseInt(inputSplitted[1]),Integer.parseInt(inputSplitted[2]));
				 inputRight = true;
			 }
			 catch(NumberFormatException e) {
				 System.err.println("geben sie das Datum in folgendem Format an Jahr-Monat-Tag");
				 inputRight = false;
			 }
		}
		
		System.out.println("Geben sie das End-Datum des Semesters in Folgender Syntax an: Jahr-Monat-Tag");
		 inputRight = false;
		while(!inputRight) {
			 String input= abfrageString("");
			 String[] inputSplitted = input.split("-");
			 try {
				 end = new Date(Integer.parseInt(inputSplitted[0])-1900,Integer.parseInt(inputSplitted[1]),Integer.parseInt(inputSplitted[2]));
				 inputRight = true;
			 }
			 catch(NumberFormatException e) {
				 System.err.println("geben sie das Datum in folgendem Format an Jahr-Monat-Tag");
				 inputRight = false;
			 }
		}
		
		study.addSemester(abfrageInt("Um Welches Semester Handelt es sich"), start, end);
		
	}
	
	private static void addNewSubject() {
		study.addSubject(abfrageInt("Zu welchem Semester gehört das Fach"), abfrageString("wie heißt dasFach"));
		
	}
	
	private static void changeWeekGoal() {
		Subject [] subjects = study.getSubjects();
		
		System.out.println("Für welches Fach möchten Sie das Lernziel anpassen?\n");
		
		for(int i = 0;i<subjects.length;i++) {
			System.out.println(i+1+" : "+ subjects[i].getSubjectName());
		}
		
		int auswahl = abfrageInt("", 1, subjects.length);
		
		study.setWeekGoal(subjects[auswahl-1].getSemester(), subjects[auswahl-1].getSubjectName(), abfrageInt("Wie viele Minuten möchten sie pro woche für "+subjects[auswahl-1].getSubjectName()+" lernen?"));
	}
	
	private static void showWeekProgress() {
		String[][] progress = study.getWeekProgress();
		System.out.println();
		for(String[] p: progress) {
			System.out.println(p[0]+": Lernziel: "+p[1]+" Minuten ; gelernt: "+Integer.parseInt(p[2])+" Minuten");
		}
	}
	
	private static void safeData() {
		study.safeData(file1);
	}

	public static String abfrageString(String i) {
	    String outPut = "";
	    
		try {
	        System.out.println(i);
	        
	        BufferedReader in = new BufferedReader(
	                              new InputStreamReader(System.in)
	                            );
	        outPut = in.readLine();
	       }
	      catch(IOException e) {
	      }
	      catch(NumberFormatException e) {
	        System.out.println("Da ist wohl etwas schiefgelaufen\nprobieren Sie es erneut");
	        System.out.println();
	        outPut = abfrageString(i);
	      }
		
		return outPut;
	}
	
	
	
	public static int abfrageInt(String i) {
	    int output = 0;
	    
		try {
	        System.out.println(i);
	        
	        BufferedReader in = new BufferedReader(
	                              new InputStreamReader(System.in)
	                            );
	        String input = in.readLine();
	        output = Integer.valueOf(input);
	       }
	      catch(IOException e) {
	      }
	      catch(NumberFormatException e) {
	        System.out.println("Da ist wohl etwas schiefgelaufen\nprobieren Sie es erneut");
	        System.out.println();
	        output = abfrageInt(i);
	      }
		
		return output;
	}
	
	public static int abfrageInt(String i,int min, int max) {
	    int output = 0;
	    
		try {
	        System.out.println(i);
	        
	        BufferedReader in = new BufferedReader(
	                              new InputStreamReader(System.in)
	                            );
	        String input = in.readLine();
	        output = Integer.valueOf(input);
	       }
	      catch(IOException e) {
	      }
	      catch(NumberFormatException e) {
	        System.out.println("Da ist wohl etwas schiefgelaufen\nprobieren Sie es erneut");
	        System.out.println();
	        output = abfrageInt(i);
	      }
		if(!(min<= output && output<= max))
			return abfrageInt("Ihre zahl muss sich wischen "+ min +" und "+max+" befinden",min,max);
		return output;
	}
	
	private static void loadFiles() {
		folder1 = new File("data");
		file1 = new File("data/data.txt");
		
		if(folder1.exists()) {
			System.out.println("Ordner existiert");
		}else {
			folder1.mkdirs();
		}
		
		if(file1.exists()) {
			System.out.println("Datei existiert");
		}else {
			try {
				file1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	try {
		Scanner sc = new Scanner(file1);
		
		while(sc.hasNext()) {
			System.out.print(sc.next()+" ");
		}
		sc.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
}
