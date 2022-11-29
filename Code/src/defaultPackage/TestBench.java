package defaultPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Date;

public class TestBench {
	static File folder1,file1;
	static Interface study;
	public static void main(String[] args) throws FileNotFoundException {
//		loadFiles();
//		Date start = new Date(2021-1900, 9, 4);
//		Date end = new Date(2022-1900,2,21);
//		
//		study = new Interface();
//		study.addSemester(1,start, end);
//		
//		start = new Date(2022-1900, 3, 21);
//		end = new Date(2022-1900, 7, 15);
//		study.addSemester(2, start, end);
//		
//		start = new Date(2022-1900, 9, 19);
//		end = new Date(2023-1900, 2, 3);
//		study.addSemester(3, start, end);
//		
//		study.addSubject(3, "Elektrotechnik");
//		study.addSubject(3, "Englisch");
//		study.addSubject(3, "Digital Rechner");
//		
//		start = new Date(2022-1900, 11, 21, 10, 0);
//		end = new Date(2022-1900, 11, 21, 13, 0);
//		study.addLearningPhase(3, "Elektrotechnik", start, end);
//		
//		start = new Date(2022-1900, 11, 21, 14, 0);
//		end = new Date(2022-1900, 11, 21, 16, 0);
//		study.addLearningPhase(3, "Englisch", start, end);
//		
//		study.safeData(file1);
//		
		
		Date start = new Date(2022-1900, 1, 1);
		Date end = new Date(2023-1900, 1, 1);
		
		TimePeriod p = new TimePeriod(start,end);
		
		//0
		Date start2 = new Date(2022-1900, 1, 1);
		Date end2 = new Date(2023-1900, 1, 1);
		TimePeriod test = new TimePeriod(start2,end2);
		System.out.println(p.compareTo(test));
		
		//0
		start2 = new Date(2022-1900, 2, 1);
		end2 = new Date(2022-1900, 5, 1);
		test = new TimePeriod(start2,end2);
		System.out.println(p.compareTo(test));
		
		//1
		start2 = new Date(2021-1900, 2, 1);
		end2 = new Date(2022-1900, 5, 1);
		test = new TimePeriod(start2,end2);
		System.out.println(p.compareTo(test));
		
		//2
		start2 = new Date(2020-1900, 2, 1);
		end2 = new Date(2021-1900, 5, 1);
		test = new TimePeriod(start2,end2);
		System.out.println(p.compareTo(test));
		
		//-1
		start2 = new Date(2022-1900, 2, 1);
		end2 = new Date(2025-1900, 5, 1);
		test = new TimePeriod(start2,end2);
		System.out.println(p.compareTo(test));
		
		//-1
		start2 = new Date(2025-1900, 2, 1);
		end2 = new Date(2026-1900, 5, 1);
		test = new TimePeriod(start2,end2);
		System.out.println(p.compareTo(test));
		
		
		

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

}
