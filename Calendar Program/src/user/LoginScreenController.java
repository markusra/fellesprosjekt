package user;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
		
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button forgotButton;
	
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) throws UnknownHostException, IOException {
		TCPClient client = new TCPClient();
		if (client.validLogin(usernameField.getText(), passwordField.getText())) {
			System.out.println("Successful login!");
			mainController.setScreen(Main.mainPageID);
		} else {
			mainController.setScreen(Main.loginFailedID);
		}
	}
	
	@FXML
	private void handleForgotButtonAction (ActionEvent event) {
		mainController.setScreen(Main.forgotID);
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				usernameField.requestFocus();
			}
		});
		InputStreamReader in = new InputStreamReader(getClass().getResourceAsStream("/resources/settings.cfg"));
		Scanner scanner = new Scanner(in);
		while (scanner.hasNextLine()) {
			if (scanner.nextLine().equalsIgnoreCase("true")) {
				usernameField.setText(scanner.nextLine());
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						passwordField.requestFocus();
					}
				});
			}
		}
		scanner.close();			
	}	
}
