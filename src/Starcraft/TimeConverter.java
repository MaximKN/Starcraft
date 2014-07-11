package Starcraft;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * 	This class formats time from number of seconds to the HH:mm:ss
 */

public class TimeConverter {
	public int seconds;
	public String outTimeFormat;

	public TimeConverter(int seconds){
		this.seconds = seconds;
		this.outTimeFormat = this.formatSeconds(seconds);
	}
	// Converting seconds to output format
	public String formatSeconds(int seconds) {
		int millis = this.seconds* 1000;
		Date date = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	public void printTime() {
		System.out.println(this.outTimeFormat);
	}
	// creates a new Timer with added seconds and a given Event.
	public TimeConverter getFutureTimer(int addSeconds){
		int currtime = this.seconds;
		return new TimeConverter(currtime+addSeconds);
	}
}