package menu;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import appointment.Appointment;
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
import program.ControllerInterface;
import program.Main;
import program.ScreensController;


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
	TableView<Appointment> headerRow;
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
		tableViewFiller(headerRow);
		try {
			weekFiller(calendar, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		headerRow.getStylesheets().addAll(getClass().getResource("/css/show-tableview-header.css").toExternalForm());
		mondayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		tuesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		wednesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		thursdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		fridayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		saturdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		sundayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
	}
	
	
	private void weekFiller(Calendar calendar, int increment) throws IOException {
		int counter = 0;
		if (increment == 0) {
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			this.year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == 1) {
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			this.year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == -1) {
			calendar.add(Calendar.WEEK_OF_YEAR, increment-1);
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			this.year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		//TODO her maa appointment data lastes inn.
		getAppointmentData();
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
	
	
	private void getAppointmentData() throws IOException {
		TCPClient client = new TCPClient();
		System.out.println(client.customQuery(ServerCodes.GetAppointments, ScreensController.getUser().getUserID() + ", " + dateForAWeekMaker()));
		
		//TODO her må appointment creator kalles og observableAppointments list må cleares før du fyller inn.
	}
	
	
	private String dateForAWeekMaker() {
		String startDate = "";
		String endDate = "";
		startDate += calendar.get(Calendar.YEAR);
		if (calendar.get(Calendar.MONTH)+1 < 10) {
			startDate += 0;
			startDate += calendar.get(Calendar.MONTH)+1;
		}
		else {
			startDate+= calendar.get(Calendar.MONTH)+1;
		}
		if (calendar.get(Calendar.DATE) < 10) {
			startDate += 0;
			startDate += calendar.get(Calendar.DATE);
		}
		else {
			startDate += calendar.get(Calendar.DATE);
		}
		for (int i = 0; i < 4; i++) {
			startDate += 0;
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int counter = 0;
		while (counter < 7) {
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
		endDate += calendar.get(Calendar.YEAR);
		if (calendar.get(Calendar.MONTH)+1 < 10) {
			endDate += 0;
			endDate += calendar.get(Calendar.MONTH)+1;
		}
		else {
			endDate+= calendar.get(Calendar.MONTH)+1;
		}
		if (calendar.get(Calendar.DATE) < 10) {
			endDate += 0;
			endDate += calendar.get(Calendar.DATE);
		}
		else {
			endDate += calendar.get(Calendar.DATE);
		}
		for (int i = 0; i < 4; i++) {
			endDate += 0;
		}
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, date);
		
		return startDate + ", " + endDate;
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
		tableColumn.setSortable(false);
		return tableColumn;
	}
	
	
	private TableColumn<Appointment, Integer> tableColumnIntegerSpecifier(String name, String variableName) {
		TableColumn<Appointment, Integer> tableColumn = new TableColumn<Appointment, Integer>(name);
		tableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>(variableName));
		tableColumn.setPrefWidth(100);
		tableColumn.setResizable(false);
		return tableColumn;
	}
	
	
	@SuppressWarnings("unchecked")
	private void tableViewFiller(TableView<Appointment> tableView) {
		tableView.getColumns().clear();
		tableView.setItems(dayAppointmentFiller(observableAppointments, calendar));
		tableView.getColumns().addAll(tableColumnIntegerSpecifier("Hour", "startHour"), tableColumnIntegerSpecifier("Minute", "startMinute"), tableColumnStringSpecifier("Purpose", "purpose"), tableColumnStringSpecifier("Place", "place"));
		tableView.getColumns().get(0).setSortable(true);
		tableView.getColumns().get(1).setSortable(true);
		tableView.getSortOrder().add(tableView.getColumns().get(0));
		tableView.getSortOrder().add(tableView.getColumns().get(1));
 		tableView.getColumns().get(2).setPrefWidth(350);
 		tableView.getColumns().get(3).setPrefWidth(167);
 		tableView.getColumns().get(2).setResizable(false);
 		tableView.getColumns().get(3).setResizable(false);
 		tableView.getColumns().get(0).setSortable(false);
 		tableView.getColumns().get(1).setSortable(false);
	}
	
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "LEFT" || code.toString() == "DOWN"){
        	weekFiller(calendar, -1);
		}else if(code.toString() == "RIGHT" || code.toString() == "UP"){
			weekFiller(calendar, 1);
		}else if(code.toString() == "ESCAPE"){
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else if(code.toString() == "A"){
			mainController.setScreen(Main.appointmentID, Main.appointmentScreen);
		}else if(code.toString() == "G"){
			mainController.setScreen(Main.groupID, Main.groupScreen);
		}else if(code.toString() == "V"){
			mainController.setScreen(Main.viewGroupsID, Main.viewGroupsScreen);
		}else{
			event.consume();
		}
	}
	

	@FXML
	public void handleNextWeekButton(ActionEvent event) throws IOException {
		weekFiller(calendar, 1);
	}
	
	
	@FXML
	public void handlePreviousWeekButton(ActionEvent event) throws IOException {
		weekFiller(calendar, -1);
	}
	
	
	@FXML
	public void handleCreateAppointmentButtonAction(ActionEvent event) throws IOException {
		mainController.setScreen(program.Main.appointmentID, Main.appointmentScreen);
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
