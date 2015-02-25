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
		System.out.println(usernameField.getText());
		System.out.println(passwordField.getText());
	}
	
	@FXML
	private void handleForgotButtonAction (ActionEvent event) {
		
	}
}