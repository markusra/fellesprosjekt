package tests;

import java.util.Calendar;

public class TestMain {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		System.out.println(calendar.getTime());
		if (calendar.get(calendar.DATE+1) < calendar.getActualMaximum(calendar.DAY_OF_MONTH)) {
			calendar.set(calendar.get(calendar.DATE), calendar.get(calendar.DATE+1));
		}
		else {
			calendar.set(calendar.MONTH, calendar.MONTH+1);
			calendar.set(calendar.DAY_OF_MONTH, 1);
		}
		System.out.println(calendar.get(calendar.DATE+1));
		System.out.println(calendar.getTime());
	}

}