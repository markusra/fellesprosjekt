package appointment;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AppointmentModel {
	
	private SimpleIntegerProperty ID;
	private SimpleStringProperty title;
	private SimpleStringProperty purpose;
	private SimpleStringProperty place;
	private SimpleIntegerProperty roomID;
	private SimpleIntegerProperty startHour;
	private SimpleIntegerProperty startMinute;
	private SimpleIntegerProperty endHour;
	private SimpleIntegerProperty endMinute;
	private SimpleIntegerProperty year;
	private SimpleIntegerProperty month;
	private SimpleIntegerProperty day;
	
	
	public AppointmentModel(int ID, String title, String purpose, String place, int roomID, Long startDate, Long endDate) {
		this.ID = new SimpleIntegerProperty(ID);
		this.title = new SimpleStringProperty(title);
		this.purpose = new SimpleStringProperty(purpose);
		this.place = new SimpleStringProperty(place);
		this.roomID = new SimpleIntegerProperty(roomID);
		this.startHour = new SimpleIntegerProperty(hourParser(startDate));
		this.startMinute = new SimpleIntegerProperty(minuteParser(startDate));
		this.endHour = new SimpleIntegerProperty(hourParser(endDate));
		this.endMinute = new SimpleIntegerProperty(minuteParser(endDate));
		this.year = new SimpleIntegerProperty(yearParser(startDate));
		this.month = new SimpleIntegerProperty(monthParser(startDate));
		this.day = new SimpleIntegerProperty(dayParser(startDate));
	}
	
	
	private int dateParser(Long date, int beginIndex, int endIndex) {
		return Integer.parseInt(Long.toString(date).substring(beginIndex, endIndex));
	}
	
	
	private int yearParser(Long date) {
		return dateParser(date, 0, 4);
	}
	
	
	private int monthParser(Long date) {
		return dateParser(date, 4, 6);
	}
	
	
	private int dayParser(Long date) {
		return dateParser(date, 6, 8);
	}
	
	
	private int hourParser(Long date) {
		return dateParser(date, 8, 10);
	}
	
	
	private int minuteParser(Long date) {
		return dateParser(date, 10, 12);
	}
	
	
	public int getAppointmentID() {
		return ID.get();
	}
	
	
	public void setAppointmentID(int appointmentID) {
		this.ID.set(appointmentID);
	}
	
	
	public String getTitle() {
		return title.get();
	}
	
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	
	public String getPurpose() {
		return purpose.get();
	}
	
	
	public void setPurpose(String purpose) {
		this.purpose.set(purpose);
	}
	
	
	public String getPlace() {
		return place.get();
	}
	
	
	public void setPlace(String roomName) {
		this.place.set(roomName);
	}
	
	
	public int getRoomID() {
		return roomID.get();
	}
	
	
	public void setRoomID(int roomID) {
		this.roomID.set(roomID);
	}
	
	
	public int getStartHour() {
		return startHour.get();
	}
	
	
	public void setStartHour(int startHour) {
		this.startHour.set(startHour);
	}
	
	
	public int getStartMinute() {
		return startMinute.get();
	}
	
	public void setStartMinute(int startMinute) {
		this.startMinute.set(startMinute);
	}
	
	
	public int getEndHour() {
		return endHour.get();
	}
	
	
	public void setEndHour(int endHour) {
		this.endHour.set(endHour);
	}
	
	
	public int getEndMinute() {
		return endMinute.get();
	}
	
	
	public void setEndMinute(int endMinute) {
		this.endMinute.set(endMinute);
	}
	
	
	public int getYear() {
		return year.get();
	}
	
	
	public void setYear(int year) {
		this.year.set(year);
	}
	
	
	public int getMonth() {
		return month.get();
	}
	
	
	public void setMonth(int month) {
		this.month.set(month);
	}
		
	
	public int getDay() {
		return day.get();
	}
	
	
	public void setDay(int day) {
		this.day.set(day);
	}
}
