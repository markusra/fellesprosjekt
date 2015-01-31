package menu;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainPageScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Text weekNumber;
	
	@FXML
	Text mondayDate;
	
	@FXML
	Text tuesdayDate;
	
	@FXML
	Text wednesdayDate;
	
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
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		weekNumber.setText(Integer.toString(calendar.get(calendar.WEEK_OF_YEAR)));
		mondayDate.setText(Integer.toString(calendar.get(calendar.DATE)));
		tuesdayDate.setText(Integer.toString(calendar.get(calendar.DATE+1)));
		wednesdayDate.setText(Integer.toString(calendar.get(calendar.DATE+2)));
	}

}
