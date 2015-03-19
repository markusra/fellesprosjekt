package menu;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import appointment.AppointmentModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import json.JsonArray;
import json.JsonValue;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;


public class MainPageScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	Calendar calendar = Calendar.getInstance();
	Timeline fiveSecondsWonder;
	Date date = new Date();
	
	TCPClient client;
	
	private ObservableList<AppointmentModel> observableAppointments = FXCollections.observableArrayList();
	private ArrayList<ArrayList<Integer>> attendingAndAdminList;
	
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
	TableView<AppointmentModel> headerRow;
	@FXML
	TableView<AppointmentModel> mondayTable;
	@FXML
	TableView<AppointmentModel> tuesdayTable;
	@FXML
	TableView<AppointmentModel> wednesdayTable;
	@FXML
	TableView<AppointmentModel> thursdayTable;
	@FXML
	TableView<AppointmentModel> fridayTable;
	@FXML
	TableView<AppointmentModel> saturdayTable;
	@FXML
	TableView<AppointmentModel> sundayTable;
	
	
	@FXML
	Label lblAppointmentsCount;
	
	int appointmentsCount = 0;
	
	@FXML
	Label lblGroupsCount;
	
	int groupsCount = 0;
	
	@FXML
	Label lblMembersCount;
	
	int membersCount = 0;
	
	@FXML
	Label lblUser;
	
	@FXML
	Button btnUpdate;
	
	long lastUpdatedTime = 0;
	
	private void startTimer() {

		fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
		    	
		    	int newAppointmentsCount = getAppointmentsCount();
		    	
		    	if (appointmentsCount < newAppointmentsCount) {
		    		int countDiff = (newAppointmentsCount - appointmentsCount);
		    		
		    		lblAppointmentsCount.setText(Integer.toString(countDiff));
		    		
		    		//appointmentsCount = newAppointmentsCount;
		    	}
		    	
		    	int newGroupsCount = getGroupsCount();
		    	System.out.println("groupsCount: " + groupsCount + ", newGroupsCount: " + newGroupsCount);
		    	if (groupsCount < newGroupsCount) {
		    		int countDiff2 = (newGroupsCount - groupsCount);
		    		
		    		lblGroupsCount.setText(Integer.toString(countDiff2));
		    		
		    		//groupsCount = newGroupsCount;
		    	}
		    	
		    	
		    	int newMembersCount = getMembersCount();
		    	
		    	if (membersCount < newMembersCount) {
		    		int countDiff3 = (newMembersCount - membersCount);
		    		
		    		lblMembersCount.setText(Integer.toString(countDiff3));
		    		
		    		//membersCount = newMembersCount;
		    	}
		    	
		    	 
		    	/*long timeMilli = date.getTime();
				  
				String serverReply = "";
				
				try {
					serverReply = client.customQuery(ServerCodes.GetUpdateStatus, "" + timeMilli);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] answer= serverReply.split("#");
				
				JsonArray jsonArray = JsonArray.readFrom( answer[1] );
				
				String didChange = jsonArray.get(0).asObject().get( "didChange" ).asString();
				  
				if (didChange.equals("Yes")) {
					System.out.println("Data har blitt oppdatert!");
					
					btnUpdate.setVisible(true);
					
				} else {
<<<<<<< HEAD
=======

>>>>>>> 192258b2aad02c25bc52b288d1bba93dc3d629eb
					btnUpdate.setVisible(false);
				}*/
		    }
		}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();
		
		
		/*timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				  
				
			    
			}
		}, 5000, 5000);*/
	}
	
	private void endTimer() {
		fiveSecondsWonder.stop();
	}
	
	private int getAppointmentsCount() {
		String serverReply = "";
		
		try {
			serverReply = client.customQuery(ServerCodes.GetAppointmentsCount, "'None'");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		int appointmentsCount = jsonArray.get(0).asObject().get( "count(avtaleID)" ).asInt();
		
		return appointmentsCount;
	}
	
	private int getGroupsCount() {
		String serverReply = "";
		
		try {
			serverReply = client.customQuery(ServerCodes.GetGroupsCount, "'None'");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		int groupsCount = jsonArray.get(0).asObject().get( "count(gruppeID)" ).asInt();
		
		return groupsCount;
	}
	
	private int getMembersCount() {
		String serverReply = "";
		
		try {
			serverReply = client.customQuery(ServerCodes.GetMembersCount, "'None'");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		String[] answer= serverReply.split("#");
		
		JsonArray jsonArray = JsonArray.readFrom( answer[1] );
		
		int membersCount = jsonArray.get(0).asObject().get( "count(brukerID)" ).asInt();
		
		return membersCount;
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;	
	}
	
	@FXML
	public void updateButtonPressed(ActionEvent event) throws IOException {
		lastUpdatedTime = date.getTime();
		mainController.setScreen(Main.mainPageID, Main.mainPageScreen);
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		btnUpdate.setVisible(false);
		
		try {
			client = new TCPClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainPane.setFocusTraversable(true);
		headerRow.getStylesheets().addAll(getClass().getResource("/css/show-tableview-header.css").toExternalForm());
		mondayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		tuesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		wednesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		thursdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		fridayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		saturdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		sundayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		try {
			tableViewFiller(headerRow);
			weekFiller(calendar, 0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblUser.setText(ScreensController.getUser().getName());
		
		
		appointmentsCount = getAppointmentsCount();
		groupsCount = getGroupsCount();
		membersCount = getMembersCount();
		
		startTimer();
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
		observableAppointments.clear();
		
		String serverReply = client.customQuery(ServerCodes.GetAppointments, ScreensController.getUser().getUserID() + ", " + dateForAWeekMaker());
		String[] answer = serverReply.split("#");

		JsonArray jsonArray = JsonArray.readFrom( answer[1] );

		try {
			for( JsonValue value : jsonArray ) {
				int appointmentID = value.asObject().get( "avtaleID" ).asInt();
				String title = value.asObject().get( "navn" ).asString();
				String purpose = value.asObject().get( "beskrivelse" ).asString();
				String place = value.asObject().get( "sted" ).asString();
				int roomID = value.asObject().get( "moteromID" ).asInt();
				long startDate = value.asObject().get( "start" ).asLong();
				long endDate = value.asObject().get( "slutt" ).asLong();
				
				appointmentCreator(appointmentID, title, purpose, place, roomID, startDate, endDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attendingAndAdminList = new ArrayList<ArrayList<Integer>>();
		
		String serverReply2 = client.customQuery(ServerCodes.GetAllAppointmentsForAdmin, Integer.toString(ScreensController.getUser().getUserID()));
		String[] answer2 = serverReply2.split("#");
		
		JsonArray jsonArray2 = JsonArray.readFrom( answer2[1] );
		
		try {
			for (JsonValue value : jsonArray2) {
				int appointmentID = value.asObject().get( "avtaleID" ).asInt();
				int admin = value.asObject().get( "admin" ).asInt();
				int deltar = value.asObject().get( "deltar" ).asInt();
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(appointmentID);
				list.add(admin);
				list.add(deltar);
				attendingAndAdminList.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	private void appointmentCreator(int appointmentID, String title, String purpose, String roomName, int roomID, Long startDate, Long endDate) {
		observableAppointments.add(new AppointmentModel(appointmentID, title, purpose, roomName, roomID, startDate, endDate));
	}
	
	
	private ObservableList<AppointmentModel> dayAppointmentFiller(ObservableList<AppointmentModel> observableAppointments, Calendar calendar) {
		ObservableList<AppointmentModel> dayAppointments = FXCollections.observableArrayList();
		for (AppointmentModel appointment : observableAppointments) {
			if (appointment.getYear() == calendar.get(Calendar.YEAR) && appointment.getMonth()-1 == calendar.get(Calendar.MONTH) && appointment.getDay() == calendar.get(Calendar.DATE)) {
				dayAppointments.add(appointment);
			}
		}
		return dayAppointments;
	}
	
	
	@SuppressWarnings("unchecked")
	private void rowStyleSetter(TableView<AppointmentModel> tableView) {
		for (Node n : tableView.lookupAll("TableRow")) {
			if (n instanceof TableRow) {
				if (!((TableRow<AppointmentModel>) n).isEmpty()) {
					TableRow<AppointmentModel> row = (TableRow<AppointmentModel>) n;
					for (int i = 0; i < attendingAndAdminList.size(); i++) {
						if (row.getItem().getAppointmentID() == attendingAndAdminList.get(i).get(0)) {
							if (attendingAndAdminList.get(i).get(1) == 1) {								
								row.setStyle("-fx-background-color: skyblue; -fx-accent: derive(-fx-control-inner-background, -40%); -fx-cell-hover-color: derive(-fx-control-inner-background, -40%);");						
							}
							else {
								if (attendingAndAdminList.get(i).get(2) == 0) {
									row.setStyle("");
								}
								else if (attendingAndAdminList.get(i).get(2) == 2) {
									row.setStyle("-fx-background-color: #ffbbbb; -fx-accent: derive(-fx-control-inner-background, -40%); -fx-cell-hover-color: derive(-fx-control-inner-background, -40%);");						
								}
								else if (attendingAndAdminList.get(i).get(2) == 1) {
									row.setStyle("-fx-background-color: palegreen; -fx-accent: derive(-fx-control-inner-background, -40%); -fx-cell-hover-color: derive(-fx-control-inner-background, -40%);");														
								}								
							}
						}
					}
				}
			}
		}
	}
	
	
	private TableColumn<AppointmentModel, String> tableColumnStringSpecifier(String name, String variable) {
		TableColumn<AppointmentModel, String> tableColumn = new TableColumn<AppointmentModel, String>(name);
		tableColumn.setCellValueFactory(new PropertyValueFactory<AppointmentModel, String>(variable));
		tableColumn.setSortable(false);
		return tableColumn;
	}
	
	
	@SuppressWarnings("unchecked")
	private void tableViewFiller(TableView<AppointmentModel> tableView) throws IOException {
		tableView.getColumns().clear();
		Label emptyLabel = new Label("There are no appointments on this day!");
		tableView.setPlaceholder(emptyLabel);
		tableView.setItems(dayAppointmentFiller(observableAppointments, calendar));
		tableView.getColumns().addAll(tableColumnStringSpecifier("Start", "startTime"), tableColumnStringSpecifier("Title", "title"), tableColumnStringSpecifier("Place", "place"));
		tableView.getColumns().get(0).setSortable(true);
		tableView.getSortOrder().add(tableView.getColumns().get(0));
 		tableView.getColumns().get(1).setPrefWidth(340);
 		tableView.getColumns().get(2).setPrefWidth(167);
 		tableView.getColumns().get(1).setResizable(false);
 		tableView.getColumns().get(2).setResizable(false);
 		tableView.getColumns().get(0).setSortable(false);
	    Platform.runLater(new Runnable() {
            @Override
            public void run() {
                rowStyleSetter(tableView);
            }
	    });
 		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
 		    @Override 
 		    public void handle(MouseEvent event) {
 		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
 		            Node node = ((Node) event.getTarget()).getParent();
 		            TableRow<AppointmentModel> row;
 		            if (node instanceof TableRow) {
 		                row = (TableRow<AppointmentModel>) node;
 		            } else {
 		                row = (TableRow<AppointmentModel>) node.getParent();
 		            }
 		            ScreensController.setAppointment(row.getItem());
 		            try {
 		            	endTimer();
 		            	mainController.setScreen(Main.appointmentStatusID, Main.appointmentStatusScreen);
					} catch (IOException e) {
						e.printStackTrace();
					}
 		        }
 		    }
 		});
	}
	
	
	@FXML
	public void keyHandler(KeyEvent event) throws IOException {
		KeyCode code = event.getCode();
        if(code.toString() == "LEFT" || code.toString() == "DOWN"){
        	weekFiller(calendar, -1);
        	lastUpdatedTime = date.getTime();
		}else if(code.toString() == "RIGHT" || code.toString() == "UP"){
			weekFiller(calendar, 1);
			lastUpdatedTime = date.getTime();
		}else if(code.toString() == "ESCAPE"){
			lastUpdatedTime = date.getTime();
			endTimer();
			mainController.setScreen(Main.loginID, Main.loginScreen);
		}else{
			event.consume();
		}
	}
	

	@FXML
	public void handleNextWeekButton(ActionEvent event) throws IOException {
		lastUpdatedTime = date.getTime();
		weekFiller(calendar, 1);
	}
	
	
	@FXML
	public void handlePreviousWeekButton(ActionEvent event) throws IOException {
		lastUpdatedTime = date.getTime();
		weekFiller(calendar, -1);
	}
	
	
	@FXML
	public void handleCreateAppointmentButtonAction(ActionEvent event) throws IOException {
		lastUpdatedTime = date.getTime();
		endTimer();
		mainController.setScreen(program.Main.appointmentID, Main.appointmentScreen);
	}
	
	
	@FXML
	public void handleCreateGroupButtonAction(ActionEvent event) throws IOException {
		lastUpdatedTime = date.getTime();
		endTimer();
		mainController.setScreen(Main.groupID, Main.groupScreen);
	}
	
	
	@FXML
	public void handleViewGroupsButtonAction(ActionEvent event) throws IOException {
		lastUpdatedTime = date.getTime();
		endTimer();
		mainController.setScreen(Main.viewGroupsID, Main.viewGroupsScreen);
	}
	
	
	@FXML
	public void handleSignOutButtonAction(ActionEvent event) throws IOException{
		lastUpdatedTime = date.getTime();
		endTimer();
		mainController.setScreen(Main.loginID, Main.loginScreen);
	}
}
