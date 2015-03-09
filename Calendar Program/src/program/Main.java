package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	//Husk aa adde alle grensesnitt (.fxml filer) under screens, og lag et navn for den under ID.
	public final static String loginID = "LoginScreen";
	private final static String loginScreen = "/user/LoginScreen.fxml";
	
	public final static String registerID = "RegisterScreen";
	private final static String registerScreen = "/user/RegisterScreen.fxml";
	
	public final static String registerSucceededID = "RegisterSucceeded";
	private final static String registerSucceededScreen = "/user/RegisterSucceededScreen.fxml";	
	
	public final static String forgotID = "ForgotScreen";
	private final static String forgotScreen = "/user/ForgotScreen.fxml";
	
	public final static String loginFailedID = "LoginFailedScreen";
	private final static String loginFailedScreen = "/user/LoginFailedScreen.fxml";	
	
	public final static String mainPageID = "MainPageScreen";
	private final static String mainPageScreen = "/menu/MainPageScreen.fxml";
	
	public final static String appointmentID = "AppointmentScreen";
	private final static String appointmentScreen = "/appointment/AppointmentScreen.fxml";

	public final static String viewDayID = "ViewDayScreen";
	private final static String viewDayScreen = "/appointment/ViewDayAppointmentsScreen.fxml";

	public final static String manageAppointmentID = "ManageAppointmentScreen";
	private final static String manageAppointmentScreen = "/appointment/ManageAppointmentsScreen.fxml";
	
	public final static String appointmentStatusID = "ManageAppointmentScreen";
	private final static String appointmentStatusScreen = "/appointment/AppointmentStatusScreen.fxml";
	
	public final static String groupID = "GroupScreen";
	private final static String groupScreen = "/group/CreateGroupScreen.fxml";
	
	public final static String viewGroupsID = "ViewGroupsScreen";
	private final static String viewGroupsScreen = "/group/ViewGroupsScreen.fxml";
	
	public final static String manageGroupID = "ManageGroupScreen";
	private final static String manageGroupScreen = "/group/ManageGroupsScreen.fxml";
	
	public final static String groupCalendarID = "GroupCalendarScreen";
	private final static String groupCalendarScreen = "/group/GroupCalendarScreen.fxml";
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController screensController = new ScreensController();
		
		//Husk aa laste inn grensesnitt med navn her.
		screensController.loadScreen(loginID, loginScreen);
		screensController.loadScreen(registerID, registerScreen);
		screensController.loadScreen(registerSucceededID, registerSucceededScreen);
		screensController.loadScreen(forgotID, forgotScreen);
		screensController.loadScreen(loginFailedID, loginFailedScreen);
		screensController.loadScreen(mainPageID, mainPageScreen);
		screensController.loadScreen(appointmentID, appointmentScreen);
		//screensController.loadScreen(viewDayID, viewDayScreen);
		//screensController.loadScreen(manageAppointmentID, manageAppointmentScreen);
		//screensController.loadScreen(appointmentStatusID, appointmentStatusScreen);
		screensController.loadScreen(groupID, groupScreen);
		//screensController.loadScreen(viewGroupsID, viewGroupsScreen);
		//screensController.loadScreen(manageGroupID, manageGroupScreen);
		//screensController.loadScreen(groupCalendarID, groupCalendarScreen);
		
		screensController.setScreen(loginID);
		
		Group root = new Group();
		root.getChildren().addAll(screensController);
		Scene scene = new Scene(root, 1024, 576);
		
		//TODO: Må endre til bare "application.css" når produktet leveres
	    scene.getStylesheets().add("//src/application.css");
	    
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
