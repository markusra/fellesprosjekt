package menu;

import java.net.URL;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainPageScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Button createAppointmentButton;
	
	
	private void handleCreateAppointmentButtonAction(ActionEvent event) {
		mainController.setScreen(Main.appointmentID);
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;	
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
	}

}
