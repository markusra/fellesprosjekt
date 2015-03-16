package tests;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarTest {
	
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar();
		
		Scanner scanner = new Scanner(System.in);
		String ignore = "";
		
		while (! ignore.equals("n")) {
			System.out.println("Øke med 1? [y/n]: ");
				ignore = scanner.next();
				
				weekFiller(calendar, 0);
		}
	}
	
	private static void weekFiller(Calendar calendar, int increment) {
		int counter = 0;
		
		//System.out.println(dateForAWeekMaker());
		if (increment == 0) {
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
			System.out.println("Week of Year: " + Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			System.out.println("Year: " + Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == 1) {
			System.out.println("Week of Year: " + Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
		}
		else if (increment == -1) {
			calendar.add(Calendar.WEEK_OF_YEAR, increment-1);
			System.out.println("Week of Year: " + Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
		}
		
		while (counter < 7) {
			if (counter == 0) {
				System.out.println("Monday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//mondayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(mondayTable);
			}
			else if (counter == 1) {
				System.out.println("Tuesday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//tuesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(tuesdayTable);
			}
			else if (counter == 2) {
				System.out.println("Wednesday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//wednesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(wednesdayTable);
			}
			else if (counter == 3) {
				System.out.println("Thursday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//thursdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(thursdayTable);
			}
			else if (counter == 4) {
				System.out.println("Friday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//fridayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(fridayTable);
			}
			else if (counter == 5) {
				System.out.println("Saturday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//saturdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(saturdayTable);
			}
			else if (counter == 6) {
				System.out.println("Sunday: " + Integer.toString(calendar.get(Calendar.DATE)));
				//sundayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				//tableViewFiller(sundayTable);
			}
			if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				if (calendar.get(Calendar.MONTH) == calendar.getActualMaximum(Calendar.MONTH)) {
					calendar.add(Calendar.YEAR, 1);
					calendar.set(Calendar.MONTH, 0);
				}
				else {
					calendar.add(Calendar.MONTH, 1);
				}
				calendar.set(Calendar.DATE, 1);
			}
			else {
				calendar.add(Calendar.DATE, 1);
			}
			counter++;
		}
	}
	
	
	
	
}
