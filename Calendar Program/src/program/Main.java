package program;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	//Husk aa adde alle grensesnitt (.fxml filer) under screens, og lag et navn for den under ID.
	public final static String loginID = "LoginScreen";
	public final static String loginScreen = "/user/LoginScreen.fxml";
	
	public final static String registerID = "RegisterScreen";
	public final static String registerScreen = "/user/RegisterScreen.fxml";
	
	public final static String registerSucceededID = "RegisterSucceeded";
	public final static String registerSucceededScreen = "/user/RegisterSucceededScreen.fxml";	
	
	public final static String forgotID = "ForgotScreen";
	public final static String forgotScreen = "/user/ForgotScreen.fxml";
	
	public final static String loginFailedID = "LoginFailedScreen";
	public final static String loginFailedScreen = "/user/LoginFailedScreen.fxml";	
	
	public final static String mainPageID = "MainPageScreen";
	public final static String mainPageScreen = "/menu/MainPageScreen.fxml";
	
	public final static String appointmentID = "AppointmentScreen";
	public final static String appointmentScreen = "/appointment/AppointmentScreen.fxml";

	public final static String appointmentSucceededID = "AppointmentSucceededScreen";
	public final static String appointmentSucceededScreen = "/appointment/AppointmentSucceededScreen.fxml";
	
	public final static String viewDayID = "ViewDayScreen";
	public final static String viewDayScreen = "/appointment/ViewDayAppointmentsScreen.fxml";

	public final static String manageAppointmentsID = "ManageAppointmentsScreen";
	public final static String manageAppointmentsScreen = "/appointment/ManageAppointmentsScreen.fxml";
	
	public final static String appointmentStatusID = "ManageAppointmentScreen";
	public final static String appointmentStatusScreen = "/appointment/AppointmentStatusScreen.fxml";
	
	public final static String groupID = "GroupScreen";
	public final static String groupScreen = "/group/CreateGroupScreen.fxml";
	
	public final static String groupSucceededID = "GroupSucceededScreen";
	public final static String groupSucceededScreen = "/group/CreateGroupSucceededScreen.fxml";
	
	public final static String viewGroupsID = "ViewGroupsScreen";
	public final static String viewGroupsScreen = "/group/ViewGroupsScreen.fxml";
	
	public final static String manageGroupID = "ManageGroupScreen";
	public final static String manageGroupScreen = "/group/ManageGroupsScreen.fxml";
	
	public final static String groupCalendarID = "GroupCalendarScreen";
	public final static String groupCalendarScreen = "/group/GroupCalendarScreen.fxml";
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController screensController = new ScreensController();
		
		//Husk aa laste inn grensesnitt med navn her.
		screensController.addScreen(loginID, loginScreen);
		screensController.addScreen(registerID, registerScreen);
		screensController.addScreen(registerSucceededID, registerSucceededScreen);
		screensController.addScreen(forgotID, forgotScreen);
		screensController.addScreen(loginFailedID, loginFailedScreen);
		screensController.addScreen(mainPageID, mainPageScreen);
		screensController.addScreen(appointmentID, appointmentScreen);
		screensController.addScreen(appointmentSucceededID, appointmentSucceededScreen);
		screensController.addScreen(viewDayID, viewDayScreen);
		screensController.addScreen(manageAppointmentsID, manageAppointmentsScreen);
		screensController.addScreen(appointmentStatusID, appointmentStatusScreen);
		screensController.addScreen(groupID, groupScreen);
		screensController.addScreen(groupSucceededID, groupSucceededScreen);
		screensController.addScreen(viewGroupsID, viewGroupsScreen);
		screensController.addScreen(manageGroupID, manageGroupScreen);
		screensController.addScreen(groupCalendarID, groupCalendarScreen);
		
		screensController.setScreen(loginID, loginScreen);
		
		Group root = new Group();
		root.getChildren().addAll(screensController);
		Scene scene = new Scene(root, 1024, 576);
		
		//TODO: Må endre til bare "application.css" når produktet leveres
	    scene.getStylesheets().add("/css/application.css");
	    
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		scene.getRoot().requestFocus();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
