package appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	Label roomField;
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
	
	@FXML
	ListView<String> lvAttending;
	
	@FXML
	ListView<String> lvNotAttending;
	
	@FXML
	ListView<String> lvNotAnswered;
	
	TCPClient client;
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ESCAPE"){
        	//Oppdater appointment
        	mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
		}else{
			event.consume();
		}
	}
	
	
	@FXML
	public void handleUpdateButtonAction(ActionEvent event) throws IOException {
		String deltar = "0";
		String varsel = "False";
		
		if (attendField.getValue().equals("Yes")) {
			deltar = "1";
		} else if (attendField.getValue().equals("No")) {
			deltar = "2";
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
		
		if (deltar == 0) {
			attendField.setValue("-");
		} else if (deltar == 1) {
			attendField.setValue("Yes");
		} else {
			attendField.setValue("No");
		}
		
		if (varsel == 1) {
			alertField.setValue("Yes");
		}
		
		if (admin == 1) {
			attendField.setDisable(true);
		} else {
			editButton.setDisable(true);
		}
		
		String serverReply2 = "";
		try {
			serverReply2 = client.customQuery(ServerCodes.GetAttendingUsers, "" + ScreensController.getAppointment().getAppointmentID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String[] answer2 = serverReply2.split("#");

		JsonArray jsonArray2 = JsonArray.readFrom( answer2[1] );
		
		List<String> attendUserList = new ArrayList<>();
		List<String> notAttendUserList = new ArrayList<>();
		List<String> notYetAnswered = new ArrayList<>();
		
		for( JsonValue value : jsonArray2 ) {
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn + " (" + brukernavn + ")";
			
			int deltar2 = value.asObject().get( "deltar" ).asInt();
			
			
			if (deltar2 == 0) {
				notYetAnswered.add(temp);
			} else if (deltar2 == 1) {
				attendUserList.add(temp);
			} else {
				notAttendUserList.add(temp);
			}
			
		}
		
		ObservableList<String> myObservableList = FXCollections.observableList(attendUserList);
		lvAttending.setItems(myObservableList);
		
		ObservableList<String> myObservableList2 = FXCollections.observableList(notAttendUserList);
		lvNotAttending.setItems(myObservableList2);
		
		ObservableList<String> myObservableList3 = FXCollections.observableList(notYetAnswered);
		lvNotAnswered.setItems(myObservableList3);
		
		mainPane.setFocusTraversable(true);
		
		alertField.setStyle("-fx-font-size:30;");
		attendField.setStyle("-fx-font-size:30;");
		lvAttending.setStyle("-fx-font-size:30;");
		lvNotAttending.setStyle("-fx-font-size:30;");
		lvNotAnswered.setStyle("-fx-font-size:30;");
		
		attendField.getStylesheets().addAll(getClass().getResource("/css/show-tableview-header.css").toExternalForm());
		alertField.getStylesheets().addAll(getClass().getResource("/css/show-tableview-header.css").toExternalForm());
		title.setText(ScreensController.getAppointment().getTitle());
		purpose.setText(ScreensController.getAppointment().getPurpose());
		place.setText(ScreensController.getAppointment().getPlace());
		roomField.setText(ScreensController.getAppointment().getRoom());
		
		
		
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

