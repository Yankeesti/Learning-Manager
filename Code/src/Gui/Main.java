package Gui;

import java.io.File;
import java.io.IOException;

import defaultPackage.Interface;

public class Main {
	static File folder1,file1;
	public static void main(String[] args) {
		loadFiles();
		FrameInterface p = new FrameInterface(new Interface(file1));

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
