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
	
	@FXML
	Button viewGroupsButton;
	
	public void handleCreateAppointmentButtonAction(ActionEvent event) {
		mainController.setScreen(Main.appointmentID);
	}
	
	public void handleCreateGroupButtonAction(ActionEvent event) {
		mainController.setScreen(Main.groupID);
	}
	
	public void handleViewGroupsButtonAction(ActionEvent event) {
		mainController.setScreen(Main.viewGroupsID);
	}
	
	public void handleSignOutButtonAction(ActionEvent event){
		mainController.setScreen(Main.loginID);
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
