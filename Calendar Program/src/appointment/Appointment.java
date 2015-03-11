package appointment;

public class Appointment {
	
	private String purpose;
	private String place;
	private String start;
	private String end;
	private int roomID;
	
	
	public Appointment(String purpose, String place, String start, String end) {
		setPurpose(purpose);
		setPlace(place);
		setStart(start);
		setEnd(end);
	}
	
	
	public void setPurpose(String purpose) {
		this.purpose=purpose;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	

}
