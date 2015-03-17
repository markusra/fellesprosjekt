package user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;

public class RegisterSucceededScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	AnchorPane mainPane;
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
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "BACK_SPACE"){
        	mainController.setScreen(Main.loginID, Main.loginScreen);
        }else if(code.toString() == "ENTER"){
        	mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "B"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "ESCAPE"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "LEFT"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else{
			event.consume();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		mainPane.setFocusTraversable(true);
	}

}
