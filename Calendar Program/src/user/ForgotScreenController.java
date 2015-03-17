package user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ForgotScreenController implements Initializable, ControllerInterface {
	
	
	ScreensController mainController;
	
	@FXML
	Pane mainPane;
	
	@FXML
	private Button back;
	
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ENTER" || code.toString() == "ESCAPE" || code.toString() == "BACK_SPACE" || code.toString() == "LEFT"){
        	mainController.setScreen(Main.loginID, Main.loginScreen);
		}else{
			event.consume();
		}
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mainPane.setFocusTraversable(true);
	}
	
	
	@FXML
	public void handleForgotUsernameButton(ActionEvent event) {
		//TODO legge til at en sender en forespørsel til server om aa sende epost til bruker med brukernavn
	}
	
	
	@FXML
	public void handleForgotPasswordButton(ActionEvent event) {
		//TODO legge til at en sender en forespørsel til server om aa sende epost til bruker med passord naar brukernavn har blitt skrevet inn
	}
	
	@FXML
	public void handleBackButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.loginID, Main.loginScreen);
	}
}