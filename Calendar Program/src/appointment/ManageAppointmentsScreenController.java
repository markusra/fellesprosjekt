package appointment;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import json.JsonArray;
import json.JsonValue;

public class ManageAppointmentsScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	TCPClient client;
	
	@FXML
	Pane mainPane;
	
	@FXML
	TextField txtPurpose;
	@FXML
	TextField txtDescription;
	@FXML
	TextField txtPlace;
	@FXML
	ComboBox<String> roomField;
	@FXML
	Label roomLabel;
	@FXML
	TextField txtSize;
	@FXML
	DatePicker dpStart;
	@FXML
	TextField timeStart;
	@FXML
	TextField timeEnd;
	@FXML
	ListView<String> invitedField;
	@FXML
	ListView<String> groupField;
	
	@FXML
	Button deleteButton;
	@FXML
	Button changeButton;
	@FXML
	Button backButton;
	
	private String startTime = null;
	private String endTime = null;
	private LocalDate date = null;
	private String size = null;
	private String dato = null;
	private String sendesStart = null;
	private String sendesEnd = null;
	private boolean valid = true;
	
	Map<String, Integer> availableUsers = new HashMap<String, Integer>();
	Map<String, Integer> availableRooms = new HashMap<String, Integer>();

	
	@FXML
	public void handleBackButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.appointmentStatusID, Main.appointmentStatusScreen);
	}
	
	
	@FXML
	public void handleDeleteButtonAction(ActionEvent event) throws IOException {
		client.customQuery(ServerCodes.DeleteAppointment, "" + ScreensController.getAppointment().getAppointmentID());
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "ESCAPE" ){
   			//Oppdater appointment
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
mainPane.setFocusTraversable(true);
		
		roomField.setDisable(true);
		
		//roomField.setVisible(false);
		//RoomLabel.setVisible(false);
		
		dpStart.setValue(LocalDate.now());
		date = dpStart.getValue();
		size = "1";
		
		invitedField.setStyle("-fx-font-size:30;");
		groupField.setStyle("-fx-font-size:30;");
		roomField.setStyle("-fx-font-size:30;");
		dpStart.setStyle("-fx-font-size:30;");
		
		try {
			client = new TCPClient();
			getAllUsers();
			fetchData();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	txtPurpose.requestFocus();
	        }
	    });
		
		txtPurpose.setText(ScreensController.getAppointment().getTitle());
		txtDescription.setText(ScreensController.getAppointment().getPurpose());
		txtPlace.setText(ScreensController.getAppointment().getPlace());
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
		dpStart.setValue(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
		timeStart.setText(ScreensController.getAppointment().getStartTime());
		timeEnd.setText(ScreensController.getAppointment().getEndTime());
		createListeners();
	}
	
	
	private void createListeners() {
		timeStart.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!(newValue.equals("")) && !(timeEnd.getText().equals("")) && validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null) && isCorrectTimeSpan() 
					&& validTime() && validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null)) {
				
				startTime=timeStart.getText();
				endTime=timeEnd.getText();
				valid = true;
				
				if ( (startTime!=null) && (endTime!=null) && (date!=null) && (size!=null)) {
					try {
						addRooms();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				valid = false;
				startTime = null;
			}
		});
		timeEnd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!(newValue.equals("")) && !(timeStart.getText().equals("")) && validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null) && isCorrectTimeSpan() 
					&& validTime() && validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null)) {
				endTime=timeEnd.getText();
				startTime=timeStart.getText();
				valid = true;
				if ( (startTime!=null) && (endTime!=null) && (date!=null) && (size!=null)) {
					try {
						addRooms();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				valid = false;
				endTime = null;
			}
 		});
		txtSize.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(newValue, "[0-9]+", txtSize, null, null)) {
				if(!newValue.equals("0") && Integer.parseInt(newValue)>0) {
					size=txtSize.getText();
					valid = true;
					if ( (startTime!=null) && (endTime!=null) && (date!=null) && (size!=null)) {
						try {
							addRooms();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					valid = false;
					txtSize.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
				}
			} else {
				valid = false;
				size = null;
			}
		});
		dpStart.valueProperty().addListener(new ChangeListener <LocalDate>(){
			public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate t, LocalDate t1){
				LocalDate today = LocalDate.now();
				if (today.compareTo(t1) > 0){
					dpStart.setStyle("-fx-control-inner-background: #FBB;");
					valid = false;
					if ( (startTime!=null) && (endTime!=null) && (date!=null) && (size!=null)) {
						try {
							addRooms();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					dpStart.setStyle("-fx-control-inner-background: white;");
					date = dpStart.getValue();
					valid = true;
				}
				
				dpStart.setStyle("-fx-font-size:30;");
			};
		});
	}
	
	
	private boolean validate(String value, String regex, TextField textField, DatePicker datePicker, LocalDate date) {
    	if(textField != null) {
    		if (!value.isEmpty()) {
    			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        		Matcher m = p.matcher(value);
        		boolean doesMatch = m.matches();
        		String style = doesMatch ? "" : "-fx-border-color: red; -fx-border-width: 2; "
        				+ "-fx-background-color: #ffbbbb";
        		textField.setStyle(style);
        		return doesMatch;
    		} else {
    			textField.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
    		}
    	}
    	if (datePicker != null) {
    		if (date != null) {
    			LocalDate today = LocalDate.now();
    			String style = !date.isBefore(today) ? "-fx-border-width: 0; -fx-background-color: WHITE" : "-fx-border-color: red; -fx-border-width: 2; "
        				+ "-fx-background-color: #ffbbbb";
        		datePicker.setStyle(style);
        		return true;
    		} else {
        		datePicker.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb");
    		}
		}
    	return false;
    }
	
	@FXML
	private void doConfirm() throws IOException {
		String rom = roomField.getValue();
		if (valid && rom != null ) {
			client.customQuery(ServerCodes.DeleteAppointment, "" + ScreensController.getAppointment().getAppointmentID());
			
			int romID = availableRooms.get(  rom );
			try {
				client.customQuery(ServerCodes.DeleteAppointment, "" + ScreensController.getAppointment().getAppointmentID());

				String serverReply = client.customQuery(ServerCodes.CreateAppointment, "'" + txtPurpose.getText() + "', '" + sendesStart + "', '" + sendesEnd + "', '" + txtDescription.getText() + "', '" + txtPlace.getText() + "', '" + romID + "'");
				ScreensController.getAppointment().setTitle(txtPurpose.getText());
				ScreensController.getAppointment().setPurpose(txtDescription.getText());
				ScreensController.getAppointment().setPlace(txtPlace.getText());				
				ScreensController.getAppointment().setStartTime(timeStart.getText());
				ScreensController.getAppointment().setEndTime(timeEnd.getText());
				ScreensController.getAppointment().setDay(dpStart.getValue().getDayOfMonth());					
				ScreensController.getAppointment().setMonth(dpStart.getValue().getMonthValue());
				ScreensController.getAppointment().setYear(dpStart.getValue().getYear());
				ScreensController.getAppointment().setDay(dpStart.getValue().getDayOfMonth());
				
				String[] answer = serverReply.split("#");

				JsonArray jsonArray = JsonArray.readFrom( answer[1] );
			
				int avtaleID = jsonArray.get(0).asObject().get( "lastInsertID" ).asInt();
				
				setMembers(avtaleID);
				mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} else {
			System.out.println("feil");
		}
	}
	
	
	private boolean isCorrectTimeSpan() {
		System.out.println("EndTime: " + Integer.parseInt(timeEnd.getText().replace(":", "")));
		System.out.println("StartTime: " + Integer.parseInt(timeStart.getText().replace(":", "")));
		
		if (Integer.parseInt(timeEnd.getText().replace(":", "")) > Integer.parseInt(timeStart.getText().replace(":", "")) && (Integer.parseInt(timeStart.getText().replace(":", "")) < 2400) && (Integer.parseInt(timeEnd.getText().replace(":", "")) < 2400)) {
			System.out.println("isCorrectTimeSpan!");
			return true;
		}
		
		timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		return false;
	}
	
	private boolean validTime() {
		if (!timeStart.getText().matches("[0-2][0-9]:[0-5][0-9]") && !timeEnd.getText().matches("[0-2][0-9]:[0-5][0-9]")) {
			timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
			timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
			return false;
		} else {
			return true; 
		}
	}
	
	
	private void fetchData() throws IOException {
		//Henter brukere
		String serverReply = client.customQuery(ServerCodes.GetAllUsers, "'None'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		invitedField.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		List<String> userList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			String temp = fornavn + " " + etternavn + " (" + brukernavn + ")";
			userList.add(temp);
		}
		ObservableList<String> items =FXCollections.observableArrayList (userList);
		invitedField.setItems(items);
		
		//Henter grupper
		serverReply = client.customQuery(ServerCodes.GetMemberGroups, "" + ScreensController.getUser().getUserID());
		answer = serverReply.split("#");
		jsonArray = JsonArray.readFrom( answer[1] );
		groupField.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		List<String> groupList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			String gruppeNavn = value.asObject().get( "navn" ).asString();
			groupList.add(gruppeNavn);
		}
		
		ObservableList<String> myObservableList = FXCollections.observableList(groupList);
	    groupField.setItems(myObservableList);
	    
	}
	
	
	private void addRooms() throws IOException {
		roomField.setDisable(false);
		
		String end = endTime.substring(0, 2) + endTime.substring(3, 5);
		String start = startTime.substring(0, 2) + startTime.substring(3, 5);
		dato = date.toString().substring(0, 4) + date.toString().substring(5, 7) + date.toString().substring(8, 10);
		sendesStart = dato + start;
		sendesEnd = dato + end;
		
		String ready = dato + start + ", " + dato + end + ", " + size; 
		
		String serverReply = client.customQuery(ServerCodes.GetFilteredRooms, ready);
		String[] answer = serverReply.split("#");
	    answer = serverReply.split("#");
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );

		List<String> roomList = new ArrayList<>();
		
		try {
			for( JsonValue value : jsonArray ) {
				String romNavn = value.asObject().get( "navn").asString();
				int romID = value.asObject().get( "moteromID").asInt();
				roomList.add(romNavn);
				
				availableRooms.put(romNavn, romID);
			}
			
		} catch (Exception e) {
			roomList.clear();
		}
		
		System.out.println("CountRooms:" + roomList.size());
	
		
		ObservableList<String> myObservableList2 = FXCollections.observableList(roomList);
		roomField.setItems(myObservableList2);
	}
	
	
	private void setMembers(int avtaleID) throws IOException {
		int brukerID = ScreensController.getUser().getUserID();
		
		try {
			client.customQuery(ServerCodes.CreateAppointmentMember, "'" + brukerID + "', '" + avtaleID + "', " + "True" + ", " + "True" + ", " + 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> brukerListe = invitedField.getSelectionModel().getSelectedItems();
		
		for (String member : brukerListe) {
			String[] memberArray = member.split("\\(");
			member = memberArray[1].substring(0, memberArray[1].length()-1);
			
			try {
				client.customQuery(ServerCodes.CreateAppointmentMember, "" + availableUsers.get(  member ) + ", " + avtaleID + ", " + "False" + ", " + "False" + ", " + 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ObservableList<String> gruppeListe = groupField.getSelectionModel().getSelectedItems();
		
		for (String gruppe : gruppeListe) {
			
			String serverReply = client.customQuery(ServerCodes.GetAllGroupMembers, "'" + gruppe + "'");
			String[] answer = serverReply.split("#");
		    answer = serverReply.split("#");
			JsonArray jsonArray = JsonArray.readFrom( answer[1] );
			
			for( JsonValue value : jsonArray ) {
				int fetched_brukerID = value.asObject().get( "brukerID").asInt();

				String serverReply2 = client.customQuery(ServerCodes.GetAppointmentMember, fetched_brukerID + ", " + avtaleID);
				String[] answer2 = serverReply2.split("#");
			    answer = serverReply2.split("#");
				JsonArray jsonArray2 = JsonArray.readFrom( answer2[1] );
				
				if (! jsonArray2.toString().contains("admin")) {
					client.customQuery(ServerCodes.CreateAppointmentMember, "" + fetched_brukerID + ", " + avtaleID + ", " + "False" + ", " + "False"  + ", " + 0);
				}	
			}	
		}	
	}

	
	private void getAllUsers() throws IOException {
		String serverReply = client.customQuery(ServerCodes.GetAllUsers, "'None'");
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		availableUsers.clear();
		for( JsonValue value : jsonArray ) {
			int  brukerID = value.asObject().get( "brukerID" ).asInt();
			String brukernavn = value.asObject().get( "brukernavn" ).asString();
			
			availableUsers.put(brukernavn, brukerID);
		}	
	}
}