package myServlet;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.*;

/*
 * Christian Znidarsic
 * GetQuote class
 * 
 * The GetQuote class is called by the hikeQuoteGenerator
 * class to get quotes from the BhcUtils API. It also performs
 * error checking. 
 */

public class GetQuote {
	private static HikeType[] hikeNames;
	static HikeType hikeType;
	// create LocalDateTime object to get today's date
	private static LocalDateTime localDateTime;
	
	
	public static String getQuote(int year, int month, int day, int duration, String hikeName, int inputHikers) {
		localDateTime = LocalDateTime.now();
		hikeNames = HikeType.values();
		
		// convert the hikeName String to the corresponding HikeType object
		for (HikeType hike : hikeNames) {
			if (hike.toString().equals(hikeName)) {
				hikeType = hike;
			}
		}
		
		BookingDay bookingDay = new BookingDay(year, month, day);
		if (bookingDay.getValidation() != "VALID") {
			return bookingDay.getValidation();
		}
		else if (bookingDay.before(new BookingDay(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth()))) {
			return ("The date entered has passed.");
		}
		else {
			Rates rates = new Rates(hikeType);
			rates.setBeginDate(bookingDay);
			rates.setDuration(duration);
			
			if (inputHikers > rates.getMaxHikers()) {
				return ("Input number of hikers is too large. The hike \"" + hikeName + "\" allows up to " + rates.getMaxHikers() + " hikers.");
			}
			else {
				rates.setNumberHikers(inputHikers);
				
				Set<String> nameSet = new HashSet<String>(Arrays.asList(HikeType.getHikeNames()));
				
				if (rates.isValidDates()) {
					return ("Price: $" + String.valueOf(rates.getCost()));
				}
				else if (!nameSet.contains(hikeName)) {
					return ("The name " + "\"" + hikeName + "\"" + " is not a valid name.");
				}
				else {
					
					boolean in = false;
					for (int x : rates.getDurations()) {
						if (duration == x) {
							in = true;
							break;
						}
					}
					if (in) {
						return ("The hiking season begins on " + rates.getSeasonStartMonth() + "/"  + rates.getSeasonStartDay() + " and ends on " + rates.getSeasonEndMonth() + "/" + rates.getSeasonEndDay());
					}
					else {
						return ("The " + hikeName + " hike is only offered in the following durations: " + Arrays.toString(rates.getDurations()));
					}
				}
			}
		}	

	}
}
