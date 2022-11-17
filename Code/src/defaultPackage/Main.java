package defaultPackage;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
	
		Week p = new Week(2022,11,22,3);
		
		Date temp = new Date(2022,10,18);
		
		System.out.println(p.dayIncluded(temp));
		
		
	}
	
	

	
}
