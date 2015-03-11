package appointment;

import javafx.beans.property.SimpleStringProperty;

public class Appointment {
	
	private SimpleStringProperty purpose;
	private SimpleStringProperty place;
	private SimpleStringProperty start;
	private SimpleStringProperty end;
	private int roomID;
	
	
	public Appointment(String purpose, String place, String start, String end) {
		this.purpose = new SimpleStringProperty(purpose);
		this.place = new SimpleStringProperty(place);
		this.start = new SimpleStringProperty(start);
		this.end = new SimpleStringProperty(end);
	}
	
	
	public void setPurpose(String purpose) {
		this.purpose.set(purpose);
	}
	public String getPurpose() {
		return purpose.get();
	}
	public String getPlace() {
		return place.get();
	}
	public void setPlace(String place) {
		this.place.set(place);;
	}
	public String getStart() {
		return start.get();
	}
	public void setStart(String start) {
		this.start.set(start);
	}
	public String getEnd() {
		return end.get();
	}
	public void setEnd(String end) {
		this.end.set(end);
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	

}
