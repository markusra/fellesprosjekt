package user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import program.ControllerInterface;
import program.ScreensController;

public class RegisterScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
