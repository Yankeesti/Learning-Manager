package defaultPackage;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		Date eins = new Date(122, 9, 2);
		Date zwei = new Date(122, 10, 2);
		
		System.out.println(eins.toString());
		System.out.println(zwei.toString());
		
		System.out.println(berechnungWochenAnzahl(eins,zwei));
	}

	/**
	 * @param semesterStart
	 * @param semesterEnd
	 * @return anzahl ganzer Wochen im angegeben Zeitraum
	 */
	private static int berechnungWochenAnzahl(Date semesterStart, Date semesterEnd) {
		Date startMonday = getMonday(semesterStart);
		Date endMonday = getMonday(semesterEnd);
		
		
		int anzahl = 0;
		System.out.println(startMonday.compareTo(endMonday));
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
	private static Date getMonday(Date tag) {
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
