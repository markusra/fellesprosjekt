package menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import appointment.Appointment;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MainPageScreenController implements Initializable, ControllerInterface, KeyListener {
	
	ScreensController mainController;
	Calendar calendar = Calendar.getInstance();
	
	private ObservableList<Appointment> observableAppointments;
	
	
	@FXML
	Button previousWeekButton2;
	@FXML
	Button nextWeekButton2;
	
	@FXML
	Text weekNumber;
	@FXML
	Text mondayDate;
	@FXML
	Text mondayMonth;
	@FXML
	Text tuesdayDate;
	@FXML
	Text tuesdayMonth;
	@FXML
	Text wednesdayDate;
	@FXML
	Text wednesdayMonth;
	@FXML
	Text thursdayDate;
	@FXML
	Text thursdayMonth;
	@FXML
	Text fridayDate;
	@FXML
	Text fridayMonth;
	@FXML
	Text saturdayDate;
	@FXML
	Text saturdayMonth;
	@FXML
	Text sundayDate;
	@FXML
	Text sundayMonth;
	
	@FXML
	Button createAppointmentButton;
	
	@FXML
	Button viewGroupsButton;
	
	@FXML
	TableView<Appointment> mondayTable;
	@FXML
	TableColumn<Appointment, String> mondayStartsTableColumn;
	@FXML
	TableColumn mondayEndsTableColumn;
	@FXML
	TableColumn mondayPurposeTableColumn;
	@FXML
	TableColumn mondayPlaceTableColumn;
	
	@FXML
	TableView<Appointment> tuesdayTable;
	@FXML
	TableColumn<String, String> tuesdayStartsTableColumn;
	@FXML
	TableColumn<String, String> tuesdayEndsTableColumn;
	@FXML
	TableColumn<String, String> tuesdayPurposeTableColumn;
	@FXML
	TableColumn<String, String> tuesdayPlaceTableColumn;
	
	@FXML
	TableView<Appointment> wednesdayTable;
	@FXML
	TableColumn<String, String> wednesdayStartsTableColumn;
	@FXML
	TableColumn<String, String> wednesdayEndsTableColumn;
	@FXML
	TableColumn<String, String> wednesdayPurposeTableColumn;
	@FXML
	TableColumn<String, String> wednesdayPlaceTableColumn;
	
	@FXML
	TableView<Appointment> thursdayTable;
	@FXML
	TableColumn<String, String> thursdayStartsTableColumn;
	@FXML
	TableColumn<String, String> thursdayEndsTableColumn;
	@FXML
	TableColumn<String, String> thursdayPurposeTableColumn;
	@FXML
	TableColumn<String, String> thursdayPlaceTableColumn;
	
	@FXML
	TableView<Appointment> fridayTable;
	@FXML
	TableColumn<String, String> fridayStartsTableColumn;
	@FXML
	TableColumn<String, String> fridayEndsTableColumn;
	@FXML
	TableColumn<String, String> fridayPurposeTableColumn;
	@FXML
	TableColumn<String, String> fridayPlaceTableColumn;
	
	@FXML
	TableView<Appointment> saturdayTable;
	@FXML
	TableColumn<String, String> saturdayStartsTableColumn;
	@FXML
	TableColumn<String, String> saturdayEndsTableColumn;
	@FXML
	TableColumn<String, String> saturdayPurposeTableColumn;
	@FXML
	TableColumn<String, String> saturdayPlaceTableColumn;
	
	@FXML
	TableView<Appointment> sundayTable;
	@FXML
	TableColumn<String, String> sundayStartsTableColumn;
	@FXML
	TableColumn<String, String> sundayEndsTableColumn;
	@FXML
	TableColumn<String, String> sundayPurposeTableColumn;
	@FXML
	TableColumn<String, String> sundayPlaceTableColumn;
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		weekFiller(calendar, 0);
		tuesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		wednesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		thursdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		fridayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		saturdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		sundayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		appointmentListFiller(mondayTable, observableAppointments, "Legetime", "Sykehus", "8:30", "9:00");
		appointmentListFiller(mondayTable, observableAppointments, "Jobb", "Arbeidsplassen min", "9:30", "14:00");
		appointmentListFiller(mondayTable, observableAppointments, "Jobb møte", "Arbeidsplassen min", "10:00", "10:30");
	}
	
	
	private void weekFiller(Calendar calendar, int increment) {
		int counter = 0;
		if (increment == 0) {
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
		}
		else if (increment == 1) {
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
		}
		else if (increment == -1) {
			calendar.add(Calendar.WEEK_OF_YEAR, increment-1);
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
		}
		while (counter < 7) {
			if (counter == 0) {
				mondayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				mondayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 1) {
				tuesdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				tuesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 2) {
				wednesdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				wednesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 3) {
				thursdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				thursdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 4) {
				fridayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				fridayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 5) {
				saturdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				saturdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 6) {
				sundayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				sundayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				if (calendar.get(Calendar.MONTH) == calendar.getActualMaximum(Calendar.MONTH)) {
					calendar.add(Calendar.YEAR, 1);
					calendar.set(Calendar.MONTH, 0);
				}
				else {
					calendar.add(Calendar.MONTH, 1);
				}
				calendar.set(Calendar.DATE, 1);
			}
			else {
				calendar.add(Calendar.DATE, 1);
			}
			counter++;
		}
	}
	
	
	private static String getMonthFromInt(int month) {
		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return monthNames[month];
	}
	
	private void appointmentListFiller(TableView<Appointment> tableView, ObservableList<Appointment> observableList, String purpose, String place, String start, String end) {
		observableList = tableView.getItems();
		observableList.add(new Appointment(purpose, place, start, end));
	}
	
	
	private void tableColumnFiller(TableColumn<Appointment, String>tableColumn, String name, String variable) {
		tableColumn = new TableColumn(name);
		tableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>(variable));
	}
	
	
	private void tableViewFiller(TableView tableView, TableColumn start, TableColumn end, TableColumn purpose, TableColumn place) {
		tableView.setItems(observableAppointments);
		tableView.getColumns().addAll(start, end, purpose, place);
	}
	
	
	@FXML
	public void handleNextWeekButton(ActionEvent event) {
		weekFiller(calendar, 1);
	}
	
	
	@FXML
	public void handleKeyPressed(KeyEvent event){
		int keyCode = event.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT){
			weekFiller(calendar, -1);
		}
		else if(keyCode == KeyEvent.VK_RIGHT){
			weekFiller(calendar, -1);
		}
		else{
			System.out.println("Pressed: " + KeyEvent.getKeyText(keyCode));
			event.consume();
		}
	}	
	
	
	@FXML
	public void handlePreviousWeekButton(ActionEvent event) {
		weekFiller(calendar, -1);
	}
	
	
	@FXML
	public void handleCreateAppointmentButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.appointmentID, Main.appointmentScreen);
	}
	
	
	@FXML
	public void handleCreateGroupButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.groupID, Main.groupScreen);
	}
	
	
	@FXML
	public void handleViewGroupsButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(Main.viewGroupsID, Main.viewGroupsScreen);
	}
	
	
	@FXML
	public void handleSignOutButtonAction(ActionEvent event) throws IOException{
		mainController.setScreen(Main.loginID, Main.loginScreen);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
