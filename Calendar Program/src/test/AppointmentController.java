package test;

import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import appointment.Appointment;

import com.sun.org.apache.xml.internal.security.Init;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppointmentController implements Initializable{
	
	private Appointment model;
	
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
	private ComboBox roomField;
	@FXML
	private ListView invitedField;
	
	private String startTime;
	private String endTime;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new Appointment();
		model.setStart("00:00");
		model.setEnd("23:59");
		
		timeStart.setText(model.getStart());
		timeEnd.setText(model.getEnd());
		createListeners();
	}
	
	private void createListeners() {
		timeStart.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(timeStart.getText(), "(\\d){2}(:)(\\d){2}", timeStart, null, null) && isCorrectTimeSpan()) {
				System.out.println(timeStart.getText());
				System.out.println(timeEnd.getText());
				startTime=timeStart.getText();
				System.out.println("Tid ble satt");
			}
		});
		timeEnd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (validate(timeEnd.getText(), "(\\d){2}(:)(\\d){2}", timeEnd, null, null) && isCorrectTimeSpan()) {
				System.out.println(timeStart.getText());
				System.out.println(timeEnd.getText());
				endTime=timeEnd.getText();
				System.out.println("Tid ble satt");
			}
		});
	}
	
	private boolean validate(String value, String regex, TextField textField, DatePicker datePicker, LocalDate date) {
    	if(textField != null) {
    		//boolean isValid = value.matches(regex, Pattern.CASE_INSENSITIVE);
    		if (!value.isEmpty()) {
    			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        		Matcher m = p.matcher(value);
        		
        		boolean doesMatch = m.matches();
        		String style = doesMatch ? "-fx-border-width: 0; -fx-background-color: WHITE" : "-fx-border-color: red; -fx-border-width: 2; "
        				+ "-fx-background-color: #ffbbbb";
        		textField.setStyle(style);
        		textField.setPromptText("Ugyldig input!");
        		//System.out.println("regex --> " + doesMatch );
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
		if (validDate(dpStart.getValue()) && isCorrectTimeSpan()) {
			appointment.setPlace(txtPlace.getText());
			appointment.setPurpose(txtPurpose.getText());
			System.out.println(model.getStart());
			System.out.println(model.getEnd());
			
		} else {
			
		}
		validDate(dpStart.getValue());
		isCorrectTimeSpan();
	}
	
	private boolean validDate(LocalDate date) {
		LocalDate today = LocalDate.now();
		if (date == null) {
			dpStart.setStyle("-fx-border-color: RED; -fx-border-width: 2; -fx-background-color: #PINK");
			return false;
		}
		if (date.isBefore(today)) {
			dpStart.setStyle("-fx-border-color: RED; -fx-border-width: 2; -fx-background-color: #PINK");
			return false;
		}
		return true;
	}
	
	private boolean isCorrectTimeSpan() {
		if (Integer.parseInt(timeEnd.getText().replace(":", "")) > Integer.parseInt(timeStart.getText().replace(":", ""))) {
			return true;
		}
		timeStart.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		timeEnd.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: #ffbbbb; -fx-prompt-text-fill: #555555");
		return false;
	}
	

}
