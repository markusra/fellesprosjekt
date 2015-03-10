package user;

public class UserModel {
	
	
	private int userID;
	private String username;
	private String name;
	private String email;
	
	
	public UserModel(int userID, String username, String firstName, String lastName, String email) {
		this.userID = userID;
		this.username = username;
		this.name = firstName + " " + lastName;
		this.email = email;
	}
	
	
	public int getUserID() {
		return userID;
	}
	
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String firstName, String lastName) {
		name = firstName + " " + lastName;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
}
