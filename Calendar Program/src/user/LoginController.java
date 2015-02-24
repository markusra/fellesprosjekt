package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	private String username;
	private String password;
		
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button loginButton;
	
	public LoginController () {
		username = "";
		password = "";
	}
	
	public void initialize() {
		username = "yes";
		password =  "no";
	}
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) {
		username = usernameField.getText();
		System.out.println("2");
		password = "lol";
		System.out.println("3");
		//TCPClient client = new TCPClient(username, password);
	}
}