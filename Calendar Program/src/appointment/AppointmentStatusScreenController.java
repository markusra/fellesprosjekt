package appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import json.JsonArray;
import json.JsonValue;

public class AppointmentStatusScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Pane mainPane;
	
	@FXML
	Button updateButton;
	@FXML
	Button editButton;
	@FXML
	Button back;
	
	@FXML
	Label title;
	@FXML
	Label purpose;
	@FXML
	Label place;
	@FXML
	Label date;
	@FXML
	Label from;
	@FXML
	Label to;
	
	@FXML
	ComboBox<String> attendField;
	@FXML
	ComboBox<String> alertField;
	
	TCPClient client;
	
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
		String deltar = "False";
		String varsel = "False";
		
		if (attendField.getValue().equals("Yes")) {
			deltar = "True";
		}
		
		if (alertField.getValue().equals("Yes")) {
			varsel = "True";
		}
		
		client.customQuery(ServerCodes.UpdateAppointmentMember, ScreensController.getUser().getUserID() + ", " + ScreensController.getAppointment().getAppointmentID() + ", " + deltar + ", " + varsel);
		
		client.disconnect();
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	

	@FXML
	public void handleEditButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.manageAppointmentsID, Main.manageAppointmentsScreen);
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
		String serverReply = "";
		
		try {
			client = new TCPClient();
			serverReply = client.customQuery(ServerCodes.GetAttendanceAlert, ScreensController.getUser().getUserID() + ", " + ScreensController.getAppointment().getAppointmentID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		int deltar = jsonArray.get(0).asObject().get( "deltar" ).asInt();
		int varsel = jsonArray.get(0).asObject().get( "varsel" ).asInt();
		int admin = jsonArray.get(0).asObject().get( "admin" ).asInt();
		
		if (deltar == 1) {
			attendField.setValue("Yes");
		}
		
		if (varsel == 1) {
			alertField.setValue("Yes");
		}
		
		if (admin == 1) {
			attendField.setDisable(true);
		} else {
			editButton.setDisable(true);
		}
		
		mainPane.setFocusTraversable(true);
		alertField.setStyle("-fx-font-size:30;");
		attendField.setStyle("-fx-font-size:30;");
		attendField.getStylesheets().addAll(getClass().getResource("/css/show-tableview-header.css").toExternalForm());
		alertField.getStylesheets().addAll(getClass().getResource("/css/show-tableview-header.css").toExternalForm());
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
		from.setText(ScreensController.getAppointment().getStartTime());
		to.setText(ScreensController.getAppointment().getEndTime());
	}
}

