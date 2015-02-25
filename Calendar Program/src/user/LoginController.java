package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
	private Hyperlink forgot;
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) {
		TCPClient client = new TCPClient();
		if (client.validLogin(usernameField.getText(), passwordField.getText())) {
			username = usernameField.getText();
			password = passwordField.getText();
		}
		//Hvis feil blir usernameboksen eller passordboksen rød
	}
	
	@FXML
	private void handleForgotButtonAction (ActionEvent event) {
		
	}

}