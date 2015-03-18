package tests;

import java.text.ParseException;
import java.util.Date;

public class MilliSecondTest {
	public static void main(String[] args) throws ParseException {
		Date date = new Date();

	      long timeMilli = date.getTime();
	      System.out.println("Time in milliseconds using Date class: " + timeMilli);
	}
}
