package menu;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import appointment.Appointment;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainPageScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	Calendar calendar = Calendar.getInstance();
	
	private ObservableList<Appointment> observableAppointments = FXCollections.observableArrayList();
	
	@FXML
	Pane mainPane;
	
	@FXML
	Button previousWeekButton2;
	@FXML
	Button nextWeekButton2;
	
	@FXML
	Text year;
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
	TableView<Appointment> tuesdayTable;
	@FXML
	TableView<Appointment> wednesdayTable;
	@FXML
	TableView<Appointment> thursdayTable;
	@FXML
	TableView<Appointment> fridayTable;
	@FXML
	TableView<Appointment> saturdayTable;
	@FXML
	TableView<Appointment> sundayTable;
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainPane.setFocusTraversable(true);
		appointmentCreator("Jobbmøte", "Gløshaugen", 201503120930L, 201503121030L);
		appointmentCreator("Pause", "Kantine", 201503121030L, 201503121145L);
		appointmentCreator("Legetime", "Sykehus", 201503120900L, 201503120930L);
		appointmentCreator("Intervju", "Møterom", 201503121200L, 201503121230L);
		appointmentCreator("Intervju", "Møterom", 201503161200L, 201503161230L);
		appointmentCreator("Familie gjenforening", "Hjemme", 201601040900L, 201601041000L);
		weekFiller(calendar, 0);
		/*tuesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		wednesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		thursdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		fridayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		saturdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		sundayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());*/
	}
	
	
	private void weekFiller(Calendar calendar, int increment) {
		int counter = 0;
		if (increment == 0) {
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == 1) {
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == -1) {
			calendar.add(Calendar.WEEK_OF_YEAR, increment-1);
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		while (counter < 7) {
			if (counter == 0) {
				mondayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				mondayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(mondayTable);
			}
			else if (counter == 1) {
				tuesdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				tuesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(tuesdayTable);
			}
			else if (counter == 2) {
				wednesdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				wednesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(wednesdayTable);
			}
			else if (counter == 3) {
				thursdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				thursdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(thursdayTable);
			}
			else if (counter == 4) {
				fridayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				fridayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(fridayTable);
			}
			else if (counter == 5) {
				saturdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				saturdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(saturdayTable);
			}
			else if (counter == 6) {
				sundayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				sundayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
				tableViewFiller(sundayTable);
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
	
	
	private void appointmentCreator(String purpose, String place, Long startDate, Long endDate) {
		observableAppointments.add(new Appointment(purpose, place, startDate, endDate));
	}
	
	
	private ObservableList<Appointment> dayAppointmentFiller(ObservableList<Appointment> observableAppointments, Calendar calendar) {
		ObservableList<Appointment> dayAppointments = FXCollections.observableArrayList();
		for (Appointment appointment : observableAppointments) {
			if (appointment.getYear() == calendar.get(Calendar.YEAR) && appointment.getMonth()-1 == calendar.get(Calendar.MONTH) && appointment.getDay() == calendar.get(Calendar.DATE)) {
				dayAppointments.add(appointment);
			}
		}
		return dayAppointments;
	}
	
	
	private TableColumn<Appointment, String> tableColumnStringSpecifier(String name, String variable) {
		TableColumn<Appointment, String> tableColumn = new TableColumn<Appointment, String>(name);
		tableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>(variable));
		return tableColumn;
	}
	
	
	@SuppressWarnings("unchecked")
	private TableColumn<Appointment, Integer> tableColumnIntegerSpecifier(String name, String variableName, String hourVariableName, String minuteVariableName) {
		TableColumn<Appointment, Integer> tableColumn = new TableColumn<Appointment, Integer>(name);
		TableColumn<Appointment, Integer> hourColumn = new TableColumn<Appointment, Integer>("Hour");
		TableColumn<Appointment, Integer> minuteColumn = new TableColumn<Appointment, Integer>("Minute");
		tableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>(variableName));
		hourColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>(hourVariableName));
		minuteColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>(minuteVariableName));
		tableColumn.getColumns().addAll(hourColumn, minuteColumn);
		return tableColumn;
	}
	
	
	@SuppressWarnings("unchecked")
	private void tableViewFiller(TableView<Appointment> tableView) {
		tableView.getColumns().clear();
		tableView.setItems(dayAppointmentFiller(observableAppointments, calendar));
		tableView.getColumns().addAll(tableColumnIntegerSpecifier("Start", "start", "startHour", "startMinute"), tableColumnIntegerSpecifier("End", "end", "endHour", "endMinute"), tableColumnStringSpecifier("Purpose", "purpose"), tableColumnStringSpecifier("Place", "place"));
		tableView.getSortOrder().add(tableView.getColumns().get(0).getColumns().get(0));
		tableView.getSortOrder().add(tableView.getColumns().get(0).getColumns().get(1));
	}
	
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "LEFT"){
        	weekFiller(calendar, -1);
		}else if(code.toString() == "RIGHT"){
			weekFiller(calendar, 1);
		}else if(code.toString() == "ESCAPE"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else{
			event.consume();
		}
	}
	

	@FXML
	public void handleNextWeekButton(ActionEvent event) {
		weekFiller(calendar, 1);
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
}
