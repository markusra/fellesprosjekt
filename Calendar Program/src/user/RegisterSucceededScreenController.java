package user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;

public class RegisterSucceededScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Button backToSignInButton;
	
	@FXML
	public void handleBackToSignInButton (ActionEvent event) throws IOException {
		mainController.setScreen(Main.loginID, Main.loginScreen);
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
