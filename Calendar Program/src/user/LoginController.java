package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
		
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	/*@FXML
	private Button loginButton;*/
	@FXML
	private Button forgotButton;
	
	
	@FXML
	private void handleLoginButtonAction (ActionEvent event) throws UnknownHostException, IOException {
		TCPClient client = new TCPClient();
		if (client.validLogin(usernameField.getText(), passwordField.getText())) {
			System.out.println("Successful login!");
			
			
		} else {
			System.out.println("Error");
		}
		//Hvis feil blir usernameboksen eller passordboksen rød
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
		/*File file = new File(getClass().getClassLoader().getResource("settings.txt").getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				usernameField.setText(scanner.nextLine());
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't get file!");
		}
		*/
	}
}