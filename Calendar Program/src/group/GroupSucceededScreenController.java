package group;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class GroupSucceededScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	AnchorPane mainPane;
	
	//Metode for backToMainPageButton
	@FXML
	public void handleBackToMainPageButton(ActionEvent event) throws IOException {
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "BACK_SPACE" || code.toString() == "ESCAPE" || code.toString() == "ENTER" || code.toString() == "LEFT" || code.toString() == "B"){
			mainController.setScreen(Main.mainPageID, Main.mainPageScreen);	
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
}

