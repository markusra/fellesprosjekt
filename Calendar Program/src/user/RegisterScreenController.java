package user;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import json.JsonArray;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;

public class RegisterScreenController implements Initializable, ControllerInterface {
	
	
	ScreensController mainController;
	
	@FXML
	AnchorPane mainPane;
	@FXML
	TextField firstNameField;
	@FXML
	ImageView firstNameInfo;
	@FXML
	TextField lastNameField;
	@FXML
	ImageView lastNameInfo;
	@FXML
	TextField emailField;
	@FXML
	ImageView emailInfo;
	@FXML
	TextField usernameField;
	@FXML
	ImageView usernameInfo;
	@FXML
	PasswordField passwordField;
	@FXML
	ImageView passwordInfo;
	@FXML
	PasswordField confirmPasswordField;
	@FXML
	Button registerButton;
	@FXML
	Button backToSignInButton;
	
	
	@FXML
	private void handleRegisterButtonAction (ActionEvent event) throws UnknownHostException, IOException {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String email = emailField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		
		if (isValidName(firstName) && isValidName(lastName) && isValidEmail(email) && isValidUsername(username) && isValidPassword(password) && isValidPassword(confirmPasswordField.getText()) && confirmPasswordField.getText().equalsIgnoreCase(passwordField.getText())) {
			TCPClient client = new TCPClient();
			//TODO add sjekk mot database for aa sjekke at brukernavn/epost ikke er opptatt
			
			String serverReply = client.customQuery(ServerCodes.CreateUser, "'" + username + "', '" + password + "', '" + email + "', '" + firstName + "', '" + lastName + "'");
			String[] answer= serverReply.split("#");
			
			if (! answer[1].contains("duplicateEntry")) {
				mainController.setScreen(Main.registerSucceededID, Main.registerSucceededScreen);			
			}
			
		}
	}
	
	@FXML
	public void keyHandler(KeyEvent event) throws UnknownHostException, IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "BACK_SPACE"){
        	mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "B"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "ESCAPE"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "ENTER" || code.toString() == "R"){
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String email = emailField.getText();
			String username = usernameField.getText();
			String password = passwordField.getText();
			
			
			if (isValidName(firstName) && isValidName(lastName) && isValidEmail(email) && isValidUsername(username) && isValidPassword(password) && isValidPassword(confirmPasswordField.getText()) && confirmPasswordField.getText().equalsIgnoreCase(passwordField.getText())) {
				TCPClient client = new TCPClient();
				//TODO add sjekk mot database for aa sjekke at brukernavn/epost ikke er opptatt
				
				String serverReply = client.customQuery(ServerCodes.CreateUser, "'" + username + "', '" + password + "', '" + email + "', '" + firstName + "', '" + lastName + "'");
				String[] answer= serverReply.split("#");
				
				if (! answer[1].contains("duplicateEntry")) {
					mainController.setScreen(Main.registerSucceededID, Main.registerSucceededScreen);			
				}
				
			}
		}else{
			event.consume();
		}
	}
	
	@FXML
	public void handleBackToSignInButtonAction (ActionEvent event) throws IOException {
		mainController.setScreen(Main.loginID, Main.loginScreen);
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mainPane.setFocusTraversable(true);
		
		Tooltip.install(firstNameInfo, new Tooltip("First name can only contain english letters and spaces! Is case insensitive and is not required to register."));
		Tooltip.install(lastNameInfo, new Tooltip("Last name can only contain english letters and spaces! Is case insensitive and is not required to register."));
		Tooltip.install(emailInfo, new Tooltip("Email has to be on the form \"yourEmail@hostname.domain\" .Email can only contain english letters and the characters '.' and '@'! Is case insensitive."));
		Tooltip.install(usernameInfo, new Tooltip("Username can only contain english letters and numbers! Is case insensitive."));
		Tooltip.install(passwordInfo, new Tooltip("Password can only contain english letters, characters and numbers! Is case insensitive."));
		
		firstNameField.setStyle("-fx-border-color: #79ff75");
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
		
		lastNameField.setStyle("-fx-border-color: #79ff75");
		lastNameField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidName(newValue)) {
					lastNameField.setStyle("-fx-border-color: #79ff75");
				}
				else {
					lastNameField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
				}
			};
		});
		
		
		emailField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
		emailField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidEmail(newValue)) {
					emailField.setStyle("-fx-border-color: #79ff75");
				}
				else {
					emailField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
				}
			};
		});
		
		
		usernameField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
		usernameField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidUsername(newValue)) {
					usernameField.setStyle("-fx-border-color: #79ff75");
				}
				else {
					usernameField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
				}
			};
		});
		
		
		passwordField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
		passwordField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidPassword(newValue)) {
					passwordField.setStyle("-fx-border-color: #79ff75");
				}
				else {
					passwordField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
				}
				confirmPasswordField.clear();
			};
		});
		
		
		confirmPasswordField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
		confirmPasswordField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidPassword(newValue)) {
					if (newValue.equalsIgnoreCase(passwordField.getText()) && !passwordField.getText().isEmpty()) {					
						confirmPasswordField.setStyle("-fx-border-color: #79ff75");
					}
					else {					
						confirmPasswordField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
					}					
				}
				else {
					confirmPasswordField.setStyle("-fx-border-color: red; -fx-background-color: #ffbbbb");
				}
			};
		});
	}
	
	
	private boolean isValidName (String name) {
		char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if((!Character.isLetter(c) && !Character.isWhitespace(c) && c != '-') || c == '\u00E6' || c == '\u00C6' || c == '\u00F8' || c == '\u00D8' || c == '\u00E5' || c == '\u00C5' || chars.length > 255) {
	            return false;
	        }
	    }
	    return true;
	}
	
	
	private boolean isValidEmail (String email) {
		char[] chars = email.toCharArray();
		for (char c : chars) {
			if((!Character.isLetterOrDigit(c) && c != '.' && c != '@') || c == '\u00E6' || c == '\u00C6' || c == '\u00F8' || c == '\u00D8' || c == '\u00E5' || c == '\u00C5' || chars.length > 255) {
				return false;
			}
		}
		if (email.indexOf('@') == email.lastIndexOf('@') && !email.isEmpty() && email.indexOf('@') > 0 && email.lastIndexOf('.') > email.indexOf('@')+1 && email.lastIndexOf('.') < email.lastIndexOf(email.charAt(email.length()-1))) {
			return true;			
		}
		else {
			return false;
		}
	}
	
	private boolean isValidUsername (String username) {
		char[] chars = username.toCharArray();
		for (char c : chars) {
			if(!Character.isLetterOrDigit(c) || c == '\u00E6' || c == '\u00C6' || c == '\u00F8' || c == '\u00D8' || c == '\u00E5' || c == '\u00C5' || chars.length > 255) {
				return false;
			}
		}
		if (!username.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
		
		
	private boolean isValidPassword (String password) {
		char[] chars = password.toCharArray();
		for (char c : chars) {
			if(c == '\u00E6' || c == '\u00C6' || c == '\u00F8' || c == '\u00D8' || c == '\u00E5' || c == '\u00C5' || chars.length > 255) {
				return false;
			}
		}
		if (!password.isEmpty()) {
			return true;			
		}
		else {
			return false;
		}
	}
}
