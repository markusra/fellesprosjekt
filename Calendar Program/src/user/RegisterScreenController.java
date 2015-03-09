package user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
		firstNameField.setText("null");
		lastNameField.setText("null");
		firstNameField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidName(newValue)) {
					firstNameField.setStyle("-fx-border-color: #79ff75");
				}
				else {
					firstNameField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
				}
			};
		});
	}
	
	
	private boolean isValidName (String name) {
		char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if(!Character.isLetter(c) || c == 'æ' || c == 'ø' || c == 'å' || chars.length > 255) {
	            return false;
	        }
	        else {
	        	return true;
	        }
	    }
	    return false;
	}
}
