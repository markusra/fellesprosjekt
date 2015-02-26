package user;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {
		
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Hyperlink forgot;
	
	@FXML
	private ImageView imgUserError;
	
	@FXML
	private ImageView imgPassError;
	
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) throws UnknownHostException, IOException {
		TCPClient client = new TCPClient();
		if (client.validLogin(usernameField.getText(), passwordField.getText())) {
			System.out.println("Successful login!");
		} else {
			System.out.println("Error");
			
			imgUserError.setVisible(true);
			imgPassError.setVisible(true);
		}
		//Hvis feil blir usernameboksen eller passordboksen rød
	}
	
	@FXML
	private void handleForgotButtonAction (ActionEvent event) {
		
	}

}