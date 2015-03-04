package appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import program.ControllerInterface;
import program.ScreensController;
import program.ServerCodes;
import user.TCPClient;
import appointment.Appointment;

import com.sun.org.apache.xml.internal.security.Init;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import json.JsonArray;
import json.JsonValue;

public class AppointmentScreenController implements Initializable, ControllerInterface{
	
	private Appointment model;
	private ScreensController mainController;
	
	
	@FXML
	private TextField txtPurpose;
	@FXML
	private TextField txtPlace;
	@FXML
	private DatePicker dpStart;
	@FXML
	private TextField timeStart;
	@FXML
	private TextField timeEnd;
	@FXML
	private ComboBox<String> roomField;
	@FXML
	private ListView<String> invitedField;
	@FXML
	private ListView<String> groupField;
	@FXML
	private TextField txtSize;
	@FXML
	private Label RoomLabel;
	
	private String startTime = null;
	private String endTime = null;
	private LocalDate date = null;
	private String size = null;
	
	
	private boolean valid=true;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new Appointment();
		roomField.setVisible(false);
		RoomLabel.setVisible(false);
		
		try {
			fetchData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timeStart.setText("00:00");
		timeEnd.setText("23:59");
		createListeners();
	}
	
	private void createListeners() {
		timeStart.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null) && isCorrectTimeSpan() 
					&& validTime() && validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null)) {
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
			}
		});
		timeEnd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null) && isCorrectTimeSpan() 
					&& validTime() && validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null)) {
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
			}
 		});
		txtSize.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(newValue, "[0-9]+", txtSize, null, null)) {
				if(!newValue.contains("0") && Integer.parseInt(newValue)>0) {
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
			};
		});
	}
	
	private boolean validate(String value, String regex, TextField textField, DatePicker datePicker, LocalDate date) {
    	if(textField != null) {
    		if (!value.isEmpty()) {
    			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        		Matcher m = p.matcher(value);
        		boolean doesMatch = m.matches();
        		String style = doesMatch ? "-fx-border-width: 0; -fx-background-color: WHITE" : "-fx-border-color: red; -fx-border-width: 2; "
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
	private void doConfirm() {
		Appointment appointment = new Appointment();
		if (valid) {
			appointment.setPlace(txtPlace.getText());
			appointment.setPurpose(txtPurpose.getText());
			System.out.println("Riktig");
			System.out.println(model.getStart());
			System.out.println(model.getEnd());
		} else {
			System.out.println("feil");
		}
	}
	
	private boolean isCorrectTimeSpan() {
		if (Integer.parseInt(timeEnd.getText().replace(":", "")) > Integer.parseInt(timeStart.getText().replace(":", ""))) {
			return true;
		}
		timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		return false;
	}
	
	private boolean validTime() {
		if (!timeStart.getText().matches("[0-2][0-3]:[0-5][0-9]") && !timeEnd.getText().matches("[0-1][0-9]:[0-5][0-9]")) {
			timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
			timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
			return false;
		} else {
			return true; 
		}
	}
	
	private void fetchData() throws IOException {
		//Henter brukere
		TCPClient client = new TCPClient();
		String serverReply = client.customQuery(ServerCodes.GetAllUsers, "'None'");
		
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		invitedField.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		List<String> userList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			String fornavn = value.asObject().get( "fornavn" ).asString();
			String etternavn = value.asObject().get( "etternavn" ).asString();
			String temp = fornavn + " " + etternavn;
			userList.add(temp);
		}
		ObservableList<String> items =FXCollections.observableArrayList (userList);
		invitedField.setItems(items);
		
		
		//Henter grupper
		serverReply = client.customQuery(ServerCodes.GetAllGroups, "'None'");
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
		roomField.setVisible(true);
		RoomLabel.setVisible(true);
		String end = endTime.substring(0, 2) + endTime.substring(3, 5);
		String start = startTime.substring(0, 2) + startTime.substring(3, 5);
		String dato = date.toString().substring(0, 4) + date.toString().substring(5, 7) + date.toString().substring(8, 9);
		
		String ready = dato + start + ", " + dato + end + ", " + size; 
		
		TCPClient client = new TCPClient();
		String serverReply = client.customQuery(ServerCodes.GetFilteredRooms, ready);
		String[] answer = serverReply.split("#");
	    answer = serverReply.split("#");
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		List<String> roomList = new ArrayList<>();
		
		for( JsonValue value : jsonArray ) {
			String gruppeNavn = value.asObject().get( "navn" ).asString();
			roomList.add(gruppeNavn);
		}
		
		ObservableList<String> myObservableList2 = FXCollections.observableList(roomList);
		roomField.setItems(myObservableList2);
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	

}