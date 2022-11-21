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
//		
		Date start = new Date(2022, 11, 2, 10, 0);
		Date end = new Date(2022, 11, 2, 13, 0);
		study.addLearningPhase(3, "Elektrotechnik", start, end);
		study.safeData(file1);
		
		System.out.println("Hallo");
//		while(true) {
//			System.out.println("was möchten sie tuen\n");
//			System.out.println("1 : Lern Phase starten");
//			System.out.println("2 : Lern Phase beenden");
//			System.out.println("3 : neues Semester Hinzufuegen");
//			System.out.println("4 : neues Fach Hinzufuegen");
//			System.out.println("5 : WochenZiel für ein Fach ändern");
//			System.out.println("6 : Aktuelles Semester ändern");
//			System.out.println("7 : Daten Speichern");
//			
//			int auswahl = abfrageInt("");
//			switch(auswahl) {
//			case 1:
//				startLearningPhase();
//				break;
//			case 2:
//				break;
//			case 3:
//				break;
//			case 4:
//				break;
//			case 5:
//				break;
//			case 6:
//				setSemester();
//				break;
//			case 7:
//				break;
//			
//			
//			}
//		}
	
	}
	
	//Methods to use interface
	
	public static void startLearningPhase() {
		//check weather current Semester is set
		if(!study.isCurrentSemesterSet()) {
			System.out.println("Du musst zur erst festlegen in welchem Semester du dich aktuell befindest");
			setSemester();
		}
		System.out.println("\nfür welches Fach möchten Sie lernen?");
		
		
	}
	
	private static void setSemester() {
		abfrageInt("für welches semester lernenst du momentan?");
		study.setCurrentSemester(abfrageInt(""));
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
			return abfrageInt("Ihre zahl muss sich wischen "+ min +" und "+max+" befinden");
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
