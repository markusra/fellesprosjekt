package appointment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AppointmentModel {
	
	private SimpleIntegerProperty ID;
	private SimpleStringProperty title;
	private SimpleStringProperty purpose;
	private SimpleStringProperty place;
	private SimpleIntegerProperty roomID;
	private SimpleStringProperty startTime;
	private SimpleStringProperty endTime;
	private SimpleIntegerProperty year;
	private SimpleIntegerProperty month;
	private SimpleIntegerProperty day;
	private SimpleStringProperty room;
	
	
	public AppointmentModel(int ID, String title, String purpose, String place, int roomID, Long startDate, Long endDate, String room) {
		this.ID = new SimpleIntegerProperty(ID);
		this.title = new SimpleStringProperty(title);
		this.purpose = new SimpleStringProperty(purpose);
		this.place = new SimpleStringProperty(place);
		this.roomID = new SimpleIntegerProperty(roomID);
		this.startTime = new SimpleStringProperty(timeParser(startDate));
		this.endTime = new SimpleStringProperty(timeParser(endDate));
		this.year = new SimpleIntegerProperty(yearParser(startDate));
		this.month = new SimpleIntegerProperty(monthParser(startDate));
		this.day = new SimpleIntegerProperty(dayParser(startDate));
		this.room = new SimpleStringProperty(room);
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
	
	
	private String timeParser(Long date) {
		return date.toString().substring(8, 10) + ":" + date.toString().substring(10, 12);
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
	
	public void setRoom(String room) {
		this.room.set(room);
	}
	
	public String getRoom() {
		return room.get();
	}
	
	
	public void setRoomID(int roomID) {
		this.roomID.set(roomID);
	}
	
	
	public String getStartTime() {
		return startTime.get();
	}
	
	
	public void setStartTime(String startTime) {
		this.startTime.set(startTime);
	}
	
	
	public String getEndTime() {
		return endTime.get();
	}
	
	
	public void setEndTime(String endTime) {
		this.endTime.set(endTime);
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
