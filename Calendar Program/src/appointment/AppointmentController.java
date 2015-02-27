package appointment;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppointmentController {
	
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
	
	@FXML
	private void doConfirm() {
		Appointment appointment = new Appointment();
		if (validDate(dpStart.getValue()) && isCorrectTimeSpan()) {
			appointment.setPlace(txtPlace.getText());
			appointment.setPurpose(txtPurpose.getText());
			
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
			System.out.println("RIKTIG INPUT");
			return true;
		}
		return false;
	}
	

}
