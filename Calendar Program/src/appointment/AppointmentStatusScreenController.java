package appointment;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class AppointmentStatusScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Pane mainPane;
	
	@FXML
	Button updateButton;
	@FXML
	Button back;
	
	@FXML
	Label title;
	@FXML
	TextArea purpose;
	@FXML
	Label place;
	@FXML
	Label date;
	@FXML
	Label from;
	@FXML
	Label to;
	
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ENTER" || code.toString() == "ESCAPE" || code.toString() == "LEFT"){
        	//Oppdater appointment
        	mainController.setScreen(Main.viewDayID, Main.viewDayScreen);
		}else{
			event.consume();
		}
	}
	
	
	@FXML
	public void handleUpdateButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	
	@FXML
	public void handleBackButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainPane.setFocusTraversable(true);
		title.setText(ScreensController.getAppointment().getTitle());
		purpose.setText(ScreensController.getAppointment().getPurpose());
		place.setText(ScreensController.getAppointment().getPlace());
		String day = "";
		String month = "";
		String year = "";
		if (ScreensController.getAppointment().getDay() < 10) {
			day += "0" + ScreensController.getAppointment().getDay();
		}
		else {
			day += ScreensController.getAppointment().getDay();
		}
		if (ScreensController.getAppointment().getMonth() < 10) {
			month += "0" + ScreensController.getAppointment().getMonth();
		}
		else {
			month += ScreensController.getAppointment().getMonth();
		}
		year += ScreensController.getAppointment().getYear();
		date.setText(day + "." + month + "." + year);
		if (ScreensController.getAppointment().getStartMinute() < 10) {
			from.setText((ScreensController.getAppointment().getStartHour()) + ":0" + (ScreensController.getAppointment().getStartMinute()));			
		}
		else {
			from.setText((ScreensController.getAppointment().getStartHour()) + ":" + (ScreensController.getAppointment().getStartMinute()));
		}
		if (ScreensController.getAppointment().getEndMinute() < 10) {
			to.setText((ScreensController.getAppointment().getEndHour()) + ":0" + (ScreensController.getAppointment().getEndMinute()));			
		}
		else {
			to.setText((ScreensController.getAppointment().getEndHour()) + ":" + (ScreensController.getAppointment().getEndMinute()));
		}
	}
}

