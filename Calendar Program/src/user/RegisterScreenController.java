package user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;

public class RegisterScreenController implements Initializable, ControllerInterface {
	
	
	ScreensController mainController;
	
	
	@FXML
	TextField firstNameField;
	@FXML
	TextField lastNameField;
	@FXML
	TextField emailField;
	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	@FXML
	PasswordField confirmPasswordField;
	@FXML
	Button registerButton;
	@FXML
	Button backToSignInButton;
	
	
	@FXML
	public void handleRegisterButtonAction (ActionEvent event) {
	}
	
	
	@FXML
	public void handleBackToSignInButtonAction (ActionEvent event) {
		mainController.setScreen(Main.loginID);
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
