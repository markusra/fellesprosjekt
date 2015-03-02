package user;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
		
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
	private void handleLoginButtonAction (ActionEvent event) throws UnknownHostException, IOException, ParseException {
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
		mainController.setScreen(Main.forgotID);
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}