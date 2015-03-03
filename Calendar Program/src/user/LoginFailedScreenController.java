package user;

import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.ScreensController;
import javafx.fxml.Initializable;

public class LoginFailedScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
