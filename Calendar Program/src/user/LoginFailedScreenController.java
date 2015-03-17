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
import javafx.scene.layout.AnchorPane;

public class LoginFailedScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	AnchorPane mainPane;
	
	@FXML
	private Button tryAgainButton;
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ENTER" || code.toString() == "BACK_SPACE" || code.toString() == "ESCAPEE" || code.toString() == "LEFT" ){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else{
			event.consume();
		}
	}
	
	public void handleTryAgainButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.loginID, Main.loginScreen);
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

}