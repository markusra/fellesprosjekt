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
	
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) {
		System.out.println(usernameField.getText());
		password = "lol";
		TCPClient client = new TCPClient(username, password);
		try {
			client.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}