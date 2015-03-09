package user;

import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ForgotScreenController implements Initializable, ControllerInterface {
	
	
	ScreensController mainController;
	
	
	@FXML
	private Button back;
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	
	@FXML
	public void handleBackButtonAction(ActionEvent event) {
		mainController.setScreen(Main.loginID);
	}
}