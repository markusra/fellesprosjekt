package appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ManageAppointmentsScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Pane mainPane;
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ENTER" || code.toString() == "BACK_SPACE" || code.toString() == "ESCAPE" || code.toString() == "LEFT" ){
   			//Oppdater appointment
        	mainController.setScreen(Main.viewDayID, Main.viewDayScreen);
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

