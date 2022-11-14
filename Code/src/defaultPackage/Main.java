package defaultPackage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		LearningPhase test = new LearningPhase();
		try {
			Thread.sleep(2300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.finish();
		
		System.out.println(test.getLearned());
		
	}
	
	

	
}
